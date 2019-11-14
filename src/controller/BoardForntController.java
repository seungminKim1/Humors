package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.Action;
import comm.ActionForward;
import humor.board.action.BoardAddAction;
import humor.board.action.BoardDeleteAction;
import humor.board.action.BoardDetailAction;
import humor.board.action.BoardListAction;
import humor.board.action.BoardModifyAction;
import humor.board.action.BoardModifyView;
import humor.board.action.BoardReplyAction;
import humor.board.action.BoardReplyView;
import humor.board.action.LikeAddAction;
import humor.board.action.ReplyAddAction;

/**
 * Servlet implementation class BoardForntController
 */
@WebServlet("*.bo")
public class BoardForntController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardForntController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	System.out.println("doProcess");
    	request.setCharacterEncoding("utf-8");
    	String RequestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println(command);
    	ActionForward forward = null;
    	Action action = null;
    	
    	if(command.equals("/BoardWrite.bo")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("./Board/qna_board_write.jsp");
    	}else if (command.equals("/BoardReplyAction.bo")) {
    		action = new BoardReplyView();
    		try{
    			forward = action.execute(request,response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/BoardModify.bo")) {
    		action = new BoardModifyView();
    		try{
    			forward = action.execute(request,response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/BoardAddAction.bo")) {
    		action = new BoardAddAction();
    		try{
    			forward = action.execute(request,response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/BoardReplyView.bo")) {
    		action = new BoardReplyAction();
    		try{
    			forward = action.execute(request,response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/BoardModifyAction.bo")) {
    		action = new BoardModifyAction();
    		try{
    			forward = action.execute(request,response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/BoardDeleteAction.bo")) {
    		action = new BoardDeleteAction();
    		try{
    			forward = action.execute(request,response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/BoardList.bo")) {
    		action = new BoardListAction();
    		try{
    			forward = action.execute(request,response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/BoardDetailAction.bo")) {
    		action = new BoardDetailAction();
    		try{
    			forward = action.execute(request,response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/ReplyAddAction.bo")) {
    		action = new ReplyAddAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/LikeAddAction.bo")) {
    		action = new LikeAddAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dispatch = request.getRequestDispatcher(forward.getPath());
    			dispatch.forward(request,response);
    		}
    	}
    }
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
