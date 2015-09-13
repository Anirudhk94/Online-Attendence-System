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
 * Servlet implementation class UpdateProfileController
 */
public class UpdateProfileController extends HttpServlet {
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

	private void doProcess(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false);
		RequestDispatcher dispatcher = null;
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		userBean.setName(req.getParameter("name"));
		userBean.setPhoneNumber(req.getParameter("phoneNumber"));
		UpdatDao updateDao = new UpdatDao();
		if("success".equals(updateDao.updateProfile(userBean))){
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
		
		}
		else{
			dispatcher = session.getServletContext().getRequestDispatcher("/editProfile.jsp");
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
