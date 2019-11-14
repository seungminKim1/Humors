package humor.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.Action;
import comm.ActionForward;
import humor.Bean.MemberBoardBean;
import humor.Bean.ReplyBean;
import humor.DAO.BoardDAO;

public class BoardDetailAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		BoardDAO boarddao = new BoardDAO();
		MemberBoardBean boarddata = new MemberBoardBean();
		List replydata = new ArrayList();
		int num = Integer.parseInt(request.getParameter("num"));
		boarddao.setReadCountUpdate(num);
		boarddata = boarddao.getDetail(num);
		replydata = boarddao.replyView(num);
		if(boarddata == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		
		request.setAttribute("boarddata", boarddata);
		request.setAttribute("replydata", replydata);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./Board/qna_board_view.jsp");
		return forward;
	}
}
