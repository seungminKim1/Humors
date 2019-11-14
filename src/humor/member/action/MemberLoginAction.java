package humor.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm.Action;
import comm.ActionForward;
import humor.Bean.MemberBean;
import humor.DAO.MemberDAO;

public class MemberLoginAction implements Action {
	public ActionForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("LoginAction in");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		MemberDAO memberdata = new MemberDAO();
		MemberBean member = new MemberBean();
		
		int result = -1;
		
		member.setMember_id(request.getParameter("member_id"));
		member.setMember_pw(request.getParameter("member_pw"));
		result = memberdata.isMember(member);
		
		if(result == 0) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("location.href='./MemberLogin.me';");
			out.println("</script>");
			out.close();
			return null;
		}else if (result == -1) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.');");
			out.println("location.href='./MemberLogin.me';");
			out.println("</script>");
			out.close();
			return null;
		}
		session.setAttribute("id", member.getMember_id());
		
		forward.setRedirect(true);
		forward.setPath("./BoardList.bo");
		return forward;
	}
}
