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
 * Servlet implementation class StudentStatusController
 */
public class StudentStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		ArrayList<AttendanceBean> attList = new ArrayList<AttendanceBean>();
		AttendanceDao dao = new AttendanceDao();
		attList = dao.getAttendanceRecordList(userBean);
		session.setAttribute("studentRecords", attList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/showAttendance.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

}
