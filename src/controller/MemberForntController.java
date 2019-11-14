package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import comm.*;
import humor.member.action.MemberDeleteAction;
import humor.member.action.MemberJoinAction;
import humor.member.action.MemberListAction;
import humor.member.action.MemberLoginAction;
import humor.member.action.MemberLogoutAction;
import humor.member.action.MemberViewAction;

/**
 * Servlet implementation class MemberForntController
 */
@WebServlet("*.me")
public class MemberForntController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberForntController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doProcess(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    	System.out.println("member");
    	String RequestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println(command);
    	ActionForward forward = null;
    	Action action = null;
    	String id = null;
    	HttpSession session = request.getSession();
    	if(command.equals("/MemberLogin.me")) {
    		System.out.println("MemberLogin.me IN");
    		forward = new ActionForward();
    		forward.setRedirect(true);
    		forward.setPath("./Member/loginForm.jsp");
    		System.out.println(forward.getPath());
    	}else if (command.equals("/MemberJoin.me")) {
    		System.out.println("MemberJoin.me In");
    		forward = new ActionForward();
    		forward.setRedirect(true);
    		forward.setPath("./Member/joinForm.jsp");
    		System.out.println(forward.getPath());
    	}else if (command.equals("/MemberLoginAction.me")) { //MemberLoginAction.me
    		action = new MemberLoginAction();
    		try {
    			forward = action.execute(request,response);
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/MemberLogout.me")) {
    		System.out.println("MemberLogout.me IN");
    		action = new MemberLogoutAction();
    		try {
    			forward = action.execute(request,response);
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/MemberJoinAction.me")) {
    		action = new MemberJoinAction();
    		try {
    			forward = action.execute(request,response);
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/MemberListAction.me")) {
    		action = new MemberListAction();
    		try {
    			forward = action.execute(request,response);
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/MemberViewAction.me")) {
    		action = new MemberViewAction();
    		try {
    			forward = action.execute(request,response);
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if (command.equals("/MemberDeleteAction.me")) {
    		action = new MemberDeleteAction();
    		try {
    			forward = action.execute(request,response);
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

}
