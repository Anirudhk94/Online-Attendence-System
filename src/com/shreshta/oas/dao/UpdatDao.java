package com.shreshta.oas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.shreshta.oas.bean.UserBean;

public class UpdatDao {
	private final String SUCCESS = "success";
	private final String FAILURE = "failure";
	public String updateProfile(UserBean userBean){
		String status = FAILURE;
		try{
			Connection con = DatabaseConnection.getDatabaseConnection();
			String query = "update users set name=?,phonenumber=? where userId=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, userBean.getName());
			pst.setString(2, userBean.getPhoneNumber());
			pst.setString(3, userBean.getUserId());
			int numberOfColumnsUpdated = pst.executeUpdate();
			if(numberOfColumnsUpdated > 0){
				status = SUCCESS;
				System.out.println(userBean.getUserId());
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return status;
		
	}
	public ArrayList<UserBean> getAllUsers(){
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		try{
			Connection con = DatabaseConnection.getDatabaseConnection();
			String query = "select *from users where userId not like ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,"admin");
			ResultSet rs = pst.executeQuery();
			while (rs.next()){
				UserBean user = new UserBean();
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				user.setPhoneNumber(rs.getString("phoneNumber"));
				user.setUserId(rs.getString("userId"));
				userList.add(user);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return userList;
		
	}
	public ArrayList<UserBean> getStudentList(){
		ArrayList<UserBean> userBean = getAllUsers();
		ArrayList<UserBean> studentList = new ArrayList<UserBean>();
		for(UserBean user : userBean ){
			if(user.getRole().equals("student"))
				studentList.add(user);
		}
		return studentList ;
	}
	public String addUser(UserBean userBean){
		String status = FAILURE;
		try{
			Connection con = DatabaseConnection.getDatabaseConnection();
			String query = "insert into users values(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, userBean.getName());
			pst.setString(2, userBean.getUserId());
			pst.setString(3, userBean.getPassword());
			pst.setString(4, userBean.getRole());
			pst.setString(5, userBean.getPhoneNumber());
			int numberOfColumnsUpdated = pst.executeUpdate();
			if(numberOfColumnsUpdated > 0){
				status = SUCCESS;
				System.out.println(userBean.getUserId());
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return status;
		
	}
	
}
