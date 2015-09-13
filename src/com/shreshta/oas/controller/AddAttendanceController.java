package com.shreshta.oas.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shreshta.oas.bean.AttendanceBean;
import com.shreshta.oas.bean.UserBean;
import com.shreshta.oas.dao.AttendanceDao;
import com.shreshta.oas.dao.UpdatDao;

/**
 * Servlet implementation class AddAttendanceController
 */
public class AddAttendanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(false);
		UserBean faculty = (UserBean) session.getAttribute("userBean");
		UpdatDao updateDao = new UpdatDao();
		ArrayList<UserBean> studentList = updateDao.getStudentList();
		ArrayList<AttendanceBean> studentAttendanceList = new ArrayList<AttendanceBean>();
		
		for(UserBean student : studentList){
			AttendanceBean attendance = new AttendanceBean();
			attendance.setStatus(request.getParameter(student.getUserId()));
			attendance.setDate(getTodayDate());
			attendance.setFaculty(faculty.getName());
			attendance.setStudent(student.getName());
			studentAttendanceList.add(attendance);
		}
		
		AttendanceDao attendanceDao = new AttendanceDao();
		if("success".equals(attendanceDao.addAttendance(studentAttendanceList))){
			session.setAttribute("studentAttendanceList", studentAttendanceList);
			dispatcher = session.getServletContext().getRequestDispatcher("/facultyHome.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			dispatcher = session.getServletContext().getRequestDispatcher("/markAttendance.jsp");
			dispatcher.forward(request, response);
		}

	}

	private String getTodayDate() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date presentDate = new Date();
		return sdf.format(presentDate);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

}
