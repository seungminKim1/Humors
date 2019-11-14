package humor.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import comm.Action;
import comm.ActionForward;
import humor.Bean.MemberBoardBean;
import humor.DAO.BoardDAO;

public class BoardAddAction implements Action{

	public ActionForward execute (HttpServletRequest request,HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		MemberBoardBean boarddata = new MemberBoardBean();
		ActionForward forward = new ActionForward();
		
		String realFolder = "";
		String saveFolder = "boardupload";
		
		int filesize = 5*1024*1024;
		
		realFolder = request.getRealPath(saveFolder);
		
		boolean result = false;
		
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder,filesize,"utf-8",new DefaultFileRenamePolicy());
			boarddata.setBoard_id(multi.getParameter("board_id"));
			boarddata.setBoard_subject(multi.getParameter("board_subject"));
			boarddata.setBoard_content(multi.getParameter("board_content"));
			boarddata.setBoard_file(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			boarddata.setBoard_like(0);
			
			result = boarddao.boardInsert(boarddata);
			
			if(result == false) {
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			
			forward.setRedirect(true);
			forward.setPath("./BoardList.bo");
			return forward;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
