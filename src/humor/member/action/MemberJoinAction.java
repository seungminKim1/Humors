package humor.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.Action;
import comm.ActionForward;
import humor.Bean.MemberBean;
import humor.DAO.MemberDAO;

public class MemberJoinAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("MemberJoinAction IN");
		
		ActionForward forward = new ActionForward();
		
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		
		boolean result = false;
		
		member.setMember_id(request.getParameter("member_id"));
		member.setMember_pw(request.getParameter("member_pw"));
		member.setMember_name(request.getParameter("member_name"));
		member.setMember_age(Integer.parseInt(request.getParameter("member_age")));
		
		result = memberdao.joinMember(member);
		
		if(result == false) {
			System.out.println("회원가입 실패");
			return null;
		}
		
		forward.setRedirect(true);
		forward.setPath("./MemberLogin.me");
		return forward;
	}
}
