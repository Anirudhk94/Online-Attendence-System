package com.shreshta.oas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shreshta.oas.bean.UserBean;

public class LoginDao {
	private final String SUCCESS = "success";
	private final String FAILURE = "failure";
	public String checkUser(UserBean userBean){
		String status = FAILURE;
		try{
			Connection con = DatabaseConnection.getDatabaseConnection();
			String query = "select * from users where UserId=? and password=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, userBean.getUserId());
			pst.setString(2, userBean.getPassword());
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				status = SUCCESS;
				System.out.println(status);
				userBean.setRole(rs.getString("role"));
				userBean.setName(rs.getString("name"));
				userBean.setPhoneNumber(rs.getString("phonenumber"));
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

}
