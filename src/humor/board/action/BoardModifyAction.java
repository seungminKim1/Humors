package humor.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.Action;
import comm.ActionForward;
import humor.Bean.MemberBoardBean;
import humor.DAO.BoardDAO;

public class BoardModifyAction implements Action{
	public ActionForward execute (HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		boolean result = false;
		
		int num = Integer.parseInt(request.getParameter("board_num"));
		
		BoardDAO boarddao = new BoardDAO();
		MemberBoardBean boarddata = new MemberBoardBean();
		
		boolean usercheck = boarddao.isBoardWriter(num, request.getParameter("board_id"));
		if(usercheck == false) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("location.href='./BoardList.bo';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		try {
			boarddata.setBoard_num(num);
			boarddata.setBoard_subject(request.getParameter("board_subject"));
			boarddata.setBoard_content(request.getParameter("board_content"));
			result = boarddao.boardModify(boarddata);
			if(result == false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			forward.setRedirect(true);
			forward.setPath("./BoardDetailAction.bo?num="+boarddata.getBoard_num());
			return forward;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
