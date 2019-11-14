package humor.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm.Action;
import comm.ActionForward;
import humor.Bean.MemberBean;
import humor.DAO.MemberDAO;

public class MemberViewAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		String id = (String)session.getAttribute("id");
		if(id == null) {
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}else if (!id.equals("admin")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자가 아닙니다.');");
			out.println("location.href='./BoardList.bo';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		String viewId = request.getParameter("id");
		member = memberdao.getDetailMember(viewId);
		
		if(member==null) {
			System.out.println("회원 정보 보기 실패");
			return null;
		}
		
		request.setAttribute("member", member);
		
		forward.setRedirect(false);
		forward.setPath("./Member/member_info.jsp");
		return forward;
	}
}
