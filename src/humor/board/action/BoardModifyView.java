package humor.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.Action;
import comm.ActionForward;
import humor.Bean.MemberBoardBean;
import humor.DAO.BoardDAO;

public class BoardModifyView implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		BoardDAO boarddao = new BoardDAO();
		MemberBoardBean boarddata = new MemberBoardBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		boarddata = boarddao.getDetail(num);
		
		if(boarddata == null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공");
		
		request.setAttribute("boarddata", boarddata);
		forward.setRedirect(false);
		forward.setPath("./Board/qna_board_modify.jsp");
		return forward;
	}
}
