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
 * Servlet implementation class ViewAllUsersController
 */
public class ViewAllUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			 {
		// TODO Auto-generated method stub
		try{
			doProcess(req,resp);
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false);
		//System.out.println(session);
		RequestDispatcher dispatcher = null;
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		UpdatDao updateDao = new UpdatDao();
		ArrayList< UserBean> userList = updateDao.getAllUsers();
		if(!userList.isEmpty()){
			session.setAttribute("userList", userList);
			dispatcher = session.getServletContext().getRequestDispatcher("/listAllUsers.jsp");
			dispatcher.forward(req, resp);
				
		}
		else{
			dispatcher = session.getServletContext().getRequestDispatcher("/adminHome.jsp");
			dispatcher.forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try{
		doProcess(req,resp);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
