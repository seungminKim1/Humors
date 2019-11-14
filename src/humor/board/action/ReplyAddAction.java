package humor.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import comm.Action;
import comm.ActionForward;
import humor.Bean.ReplyBean;
import humor.DAO.BoardDAO;

public class ReplyAddAction implements Action{
	public ActionForward execute (HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("ReplyAddAction IN");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		BoardDAO  boarddao = new BoardDAO();
		ReplyBean rb = new ReplyBean();
		boolean result = false;
		
		rb.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		rb.setReply_id((String)session.getAttribute("id"));
		rb.setReply_content(request.getParameter("reply_content"));
		
		result = boarddao.registReply(rb);
		if(result == false) {
			System.out.println("replyadd fail");
			return null;
		}
		
		System.out.println("replyadd com");
		
		forward.setRedirect(false);
		forward.setPath("/BoardDetailAction.bo?num="+rb.getBoard_num());
		return forward;
		
	}
}
