package com.shreshta.oas.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shreshta.oas.bean.UserBean;
import com.shreshta.oas.dao.UpdatDao;

/**
 * Servlet implementation class AddUserController
 */
public class AddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			doProcess(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			doProcess(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		UserBean userbean = new UserBean();
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(false);
		userbean.setName(request.getParameter("name"));
		userbean.setPassword(request.getParameter("password"));
		userbean.setPhoneNumber(request.getParameter("phoneNumber"));
		userbean.setRole(request.getParameter("role"));
		userbean.setUserId(request.getParameter("userId"));
		UpdatDao updateDao = new UpdatDao();
		if("success".equals(updateDao.addUser(userbean))){
			dispatcher = session.getServletContext().getRequestDispatcher("/adminHome.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			dispatcher = session.getServletContext().getRequestDispatcher("/adminHome.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
