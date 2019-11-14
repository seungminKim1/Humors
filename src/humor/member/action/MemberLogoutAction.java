package humor.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm.Action;
import comm.ActionForward;

public class MemberLogoutAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("MemberLogoutAction IN");
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		session.invalidate();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);
		return forward;
	}
}
