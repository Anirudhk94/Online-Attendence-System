package com.shreshta.oas.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shreshta.oas.bean.UserBean;
import com.shreshta.oas.dao.LoginDao;

public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
				doProcess(req,resp);
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("supp!");
		try{
		doProcess(req,resp);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		RequestDispatcher dispatcher = null;
		UserBean userBean = new UserBean();
		userBean.setUserId(req.getParameter("userId"));
		userBean.setPassword(req.getParameter("password"));
		
		
		
		LoginDao loginDao = new LoginDao();
		if("success".equals(loginDao.checkUser(userBean))){
			session.setAttribute("userBean", userBean);
			if("admin".equals(userBean.getRole())){
				dispatcher = session.getServletContext().getRequestDispatcher("/adminHome.jsp");
				dispatcher.forward(req, resp);
				
			}
			else if("faculty".equals(userBean.getRole())){
				dispatcher = session.getServletContext().getRequestDispatcher("/facultyHome.jsp");
				dispatcher.forward(req, resp);
				
			}
			else if("student".equals(userBean.getRole())){
				dispatcher = session.getServletContext().getRequestDispatcher("/studentHome.jsp");
				dispatcher.forward(req, resp);
				
			}
			else{
				dispatcher = session.getServletContext().getRequestDispatcher("/login.jsp");
				dispatcher.forward(req, resp);
				
			}
		}
		else{
			dispatcher = session.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(req, resp);
		}
			
		
		
		
	}

	

}
