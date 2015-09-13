package com.shreshta.oas.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shreshta.oas.bean.UserBean;
import com.shreshta.oas.dao.UpdatDao;

/**
 * Servlet implementation class MarkAttendanceController
 */
public class MarkAttendanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProccess(request,response);
	}

	private void doProccess(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		//System.out.println(session);
		RequestDispatcher dispatcher = null;
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		UpdatDao updateDao = new UpdatDao();
		ArrayList< UserBean> userList = updateDao.getAllUsers();
		if(!userList.isEmpty()){
			session.setAttribute("userList", userList);
			dispatcher = session.getServletContext().getRequestDispatcher("/markAttendance.jsp");
			dispatcher.forward(request, response);
				
		}
		else{
			dispatcher = session.getServletContext().getRequestDispatcher("/facultyHome.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProccess(request,response);
	}

}
