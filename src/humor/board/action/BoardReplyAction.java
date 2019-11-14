package humor.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.Action;
import comm.ActionForward;
import humor.Bean.MemberBoardBean;
import humor.DAO.BoardDAO;

public class BoardReplyAction implements Action{
	public ActionForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardReplyAction IN");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		BoardDAO boarddao = new BoardDAO();
		MemberBoardBean boarddata = new MemberBoardBean();
		int result = 0;
		
		boarddata.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		boarddata.setBoard_id(request.getParameter("board_id"));
		boarddata.setBoard_subject(request.getParameter("board_subject"));
		boarddata.setBoard_content(request.getParameter("board_content"));
		boarddata.setBoard_file(null);
		boarddata.setBoard_re_ref(Integer.parseInt(request.getParameter("board_re_ref")));
		boarddata.setBoard_re_lev(Integer.parseInt(request.getParameter("board_re_lev")));
		boarddata.setBoard_re_seq(Integer.parseInt(request.getParameter("board_re_seq")));
		
		result = boarddao.boardReply(boarddata);
		if(result == 0) {
			System.out.println("reply fail");
			return null;
		}
		System.out.println(result);
		System.out.println("reply com");
		
		forward.setRedirect(true);
		forward.setPath("./BoardDetailAction.bo?num="+result);
		return forward;
	}
}
