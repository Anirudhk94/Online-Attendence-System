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
import com.shreshta.oas.dao.AttendanceDao;

/**
 * Servlet implementation class UpdateAttendanceController
 */
public class UpdateAttendanceController extends HttpServlet {
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
		ArrayList<AttendanceBean> updatedAttList = new ArrayList<AttendanceBean>();
		ArrayList<AttendanceBean> attList = (ArrayList<AttendanceBean>)session.getAttribute("attList");
		for(AttendanceBean attendance : attList){
			AttendanceBean updateAtt = attendance;
			updateAtt.setStatus(request.getParameter(attendance.getStudent()));
			updatedAttList.add(updateAtt);
			
		}
		RequestDispatcher dispatcher = null;
		AttendanceDao dao = new AttendanceDao();
		if("success".equals(dao.updateAttendance(updatedAttList))){
			session.setAttribute("studentAttendanceList",updatedAttList);
			dispatcher = request.getRequestDispatcher("/facultyHome.jsp");
			dispatcher.forward(request, response);
			
		}
		else{
			dispatcher = request.getRequestDispatcher("/modifyAttendance.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

}
