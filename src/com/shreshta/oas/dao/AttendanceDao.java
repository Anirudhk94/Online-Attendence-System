package com.shreshta.oas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.sql.Statement;
import com.shreshta.oas.bean.AttendanceBean;
import com.shreshta.oas.bean.UserBean;

public class AttendanceDao {
	private final String SUCCESS = "success";
	private final String FAILURE = "failure";
	public String addAttendance(ArrayList<AttendanceBean> studentAttendanceList	){
		String status = FAILURE;
		try{
			
			Connection con = DatabaseConnection.getDatabaseConnection();
			String query = "insert into attendance values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			for(AttendanceBean attendance : studentAttendanceList){
				pst.setString (1,attendance.getFaculty());
				pst.setString(2, attendance.getStudent());
				pst.setString(3, attendance.getDate());
				pst.setString(4, attendance.getStatus());
				pst.addBatch();
			}
			int[] resultArray = pst.executeBatch();
			for(int result : resultArray){
				status = SUCCESS;
				if(result < 0){
					status = FAILURE;
					break;
				}
				
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return status;
	}
	public ArrayList<AttendanceBean> getStdAttendanceList(UserBean userBean){
		ArrayList<AttendanceBean> attendanceList = new ArrayList<AttendanceBean>();
		try{
			String query = null;
			if(userBean == null)
				query = "select *from attendance";
			else 
				query = "select *from attendance where faculty='" + userBean.getName() + "'";
			Connection con = DatabaseConnection.getDatabaseConnection();
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()){
				AttendanceBean attendance = new AttendanceBean();
				attendance.setDate(rs.getString("date_c"));
				attendance.setFaculty(rs.getString("faculty"));
				attendance.setStatus(rs.getString("status"));
				attendance.setStudent(rs.getString("student"));
				attendanceList.add(attendance);
			}
		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return attendanceList;
	}
	public ArrayList<AttendanceBean> todayAttendanceList(UserBean userBean){
		ArrayList<AttendanceBean> attendanceList = new ArrayList<AttendanceBean>();
		try{
			Date presentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(presentDate);
			String query = null;
			query = "select *from attendance where faculty='" + userBean.getName() + "'" +" and date_c='" + date + "'";
			Connection con = DatabaseConnection.getDatabaseConnection();
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()){
				AttendanceBean attendance = new AttendanceBean();
				attendance.setDate(rs.getString("date_c"));
				attendance.setFaculty(rs.getString("faculty"));
				attendance.setStatus(rs.getString("status"));
				attendance.setStudent(rs.getString("student"));
				attendanceList.add(attendance);
			}
		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return attendanceList;
	}
	public String updateAttendance(ArrayList<AttendanceBean> studentAttendanceList	){
		String status = FAILURE;
		try{
			
			Connection con = DatabaseConnection.getDatabaseConnection();
			String query = "update attendance set status = ? where student = ?";
			PreparedStatement pst = con.prepareStatement(query);
			for(AttendanceBean attendance : studentAttendanceList){
				pst.setString(1, attendance.getStatus());
				pst.setString(2, attendance.getStudent());
				
				pst.addBatch();
			}
			int[] resultArray = pst.executeBatch();
			for(int result : resultArray){
				status = SUCCESS;
				if(result < 0){
					status = FAILURE;
					break;
				}
				
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return status;
	}
	public ArrayList<AttendanceBean> getAttendanceRecordList(UserBean userBean){
		ArrayList<AttendanceBean> attendanceList = new ArrayList<AttendanceBean>();
		try{
			
			String query = null;
			query = "select *from attendance where student='" + userBean.getName() + "'";
			Connection con = DatabaseConnection.getDatabaseConnection();
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()){
				AttendanceBean attendance = new AttendanceBean();
				attendance.setDate(rs.getString("date_c"));
				attendance.setFaculty(rs.getString("faculty"));
				attendance.setStatus(rs.getString("status"));
				attendance.setStudent(rs.getString("student"));
				attendanceList.add(attendance);
			}
		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return attendanceList;
	}
}
