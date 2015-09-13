package com.shreshta.oas.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public static Connection getDatabaseConnection(){
		System.out.println("wow");
		Connection con = null;
		try{
			
			//Class.forName("oracle.jdbc.driver.OracleDriver"); --use it for Oracle Database server
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//jdbc:oracle:thin:@localhost:1521:xe --url for Oracle Database server
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","password");
			System.out.println("Connected to server"+con);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return con;
		
	}

}
