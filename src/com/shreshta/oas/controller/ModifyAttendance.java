package com.shreshta.oas.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shreshta.oas.bean.AttendanceBean;
import com.shreshta.oas.bean.UserBean;
import com.shreshta.oas.dao.AttendanceDao;

/**
 * Servlet implementation class ModifyAttendance
 */
public class ModifyAttendance extends HttpServlet {
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
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		AttendanceDao attendanceDao	= new AttendanceDao();
		ArrayList<AttendanceBean> studentAttendanceList = new ArrayList<AttendanceBean>();
		studentAttendanceList = attendanceDao.todayAttendanceList(userBean);
		session.setAttribute("attList", studentAttendanceList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/modifyAttendance.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProccess(request,response);
	}

}
