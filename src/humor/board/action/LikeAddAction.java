package humor.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.Action;
import comm.ActionForward;
import humor.DAO.BoardDAO;

public class LikeAddAction implements Action {
	public ActionForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO dao = new BoardDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		int result = dao.likeAdd(num);
		if(result == 0 ) {
			System.out.println("likeadd fail");
			return null;
		}
		System.out.println(num);
		System.out.println("likeadd com");
		forward.setRedirect(true);
		forward.setPath("./BoardDetailAction.bo?num="+num);
		return forward;
		
	}
}
