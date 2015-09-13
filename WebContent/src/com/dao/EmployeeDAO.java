package com.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;

import com.dao.DBConnectionManager;

import com.beans.EmployeeBean; 
import com.beans.WorkInfoBean;



public class EmployeeDAO {
	
		static final String SUCCESS = "success";
		static final String FAILURE = "failure";

		
		public String deleteUser(String id) 
		{
			String result = null;
			Connection con = DBConnectionManager.getJDBCConnection();		
			Statement st = null;
						
			try{
				st = con.createStatement();
		
				int row = st.executeUpdate("delete from employee where empId = '"+ id+"'") ;
				if(row != 0){
					result = SUCCESS;
				}else{
					result = FAILURE;
				}
			}	
			catch (SQLException ex){
				
				DBConnectionManager.rollbackJDBCConnection(con);
				result = FAILURE;
				//	log.error(ex);
			}
			finally
			{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(st);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;
		}

		public Collection<EmployeeBean> selectUsers()
		{
			Collection<EmployeeBean> users = new ArrayList<EmployeeBean> ();
			String result = null;
			
			//Create a Database Connection
			Connection con = DBConnectionManager.getJDBCConnection();		
			Statement st = null;
			ResultSet rs = null;
		
			
			try{
				st = con.createStatement();
		
				rs = st.executeQuery("select * from employee") ;
						
				while (rs.next()){
					EmployeeBean empBean = new EmployeeBean();
					empBean.setFirstName(rs.getString("firstName"));
					empBean.setLastName(rs.getString("lastName"));
					empBean.setEmail(rs.getString("email"));
					empBean.setEmpId(rs.getString("empId"));		
					users.add(empBean);
					
				}
				
								
			}catch (SQLException ex){
				
				DBConnectionManager.rollbackJDBCConnection(con);
				//	log.error(ex);
			}
			finally
			{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(st);
				DBConnectionManager.closeJDBCConnection(con);
			}
			
					
			return users;
			
		}
		public String createUser(EmployeeBean newUser){
			
			String result = null;
			PreparedStatement stmtInsert = null;
			
			//Create a Database Connection
			Connection con = DBConnectionManager.getJDBCConnection();
			try{
				con.setAutoCommit(false);
						
				StringBuffer sbInsert = new StringBuffer();
				String date = "";
				
						
				sbInsert.append("INSERT INTO ");
				//TABLE_NAME
				sbInsert.append("employee");
				sbInsert.append(" ( firstName, lastName, email, password,dateOfJoin ) ");
				sbInsert.append(" VALUES (");
				sbInsert.append(" ?, ?, ?, ?,?) ");
				
				stmtInsert = con.prepareStatement(sbInsert.toString());
				
				stmtInsert.setString(1, newUser.getFirstName());
				stmtInsert.setString(2, newUser.getLastName());
				stmtInsert.setString(3, newUser.getEmail());
				stmtInsert.setString(4, newUser.getPassword());
				stmtInsert.setString(5, getTodayDate() );
				
				
				int rows = stmtInsert.executeUpdate();
				
				if (rows != 1)
				{
					result = FAILURE;
					throw new SQLException(
						"executeUpdate return value: "
						+ rows);
				}	
				result = SUCCESS;				
			}catch (SQLException ex){
				result = FAILURE;
				DBConnectionManager.rollbackJDBCConnection(con);
				//	log.error(ex);
			}
			finally
			{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(stmtInsert);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;	
			
		}

		
		public String getUserInfo(EmployeeBean user)
		{
			String result = null;
				
			//Create a Database Connection
			Connection con = DBConnectionManager.getJDBCConnection();
		
			Statement st = null;
			ResultSet rs = null;
		
			
			try{
				st = con.createStatement();
		
				rs = st.executeQuery("select * from employee l where l.empId = '"+user.getEmpId()+"'") ;
						
				while (rs.next()){
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setEmail(rs.getString("email"));
					user.setEmpId(rs.getString("empId"));				
					
				}
				
				result = SUCCESS;				
			}catch (SQLException ex){
				result = FAILURE;
				DBConnectionManager.rollbackJDBCConnection(con);
				//	log.error(ex);
			}
			finally
			{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(st);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;	
		}
		
		public String checkUser(EmployeeBean user)
		{
			String result = null;
				
			//Create a Database Connection
			Connection con = DBConnectionManager.getJDBCConnection();
		
			Statement st = null;
			ResultSet rs = null;
		
			
			try{
				st = con.createStatement();
		
				rs = st.executeQuery("select * from employee l where l.empId = '"+user.getEmpId() +
						"' and l.password = '"+ user.getPassword() + "'") ;
						
				while (rs.next()){
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setEmail(rs.getString("email"));
					user.setEmpId(rs.getString("empId"));			
					
					
					File image = new File("/home/tsuser/Desktop/EmployeeAttendance/WebContent/Store"+user.getEmpId()+".jpg");
					FileOutputStream fos = new FileOutputStream(image);
					byte[] buffer = new byte[256];
							
					// Get the binary stream of our BLOB data
					//
					
					InputStream is = rs.getBinaryStream("image");
				
					if(is != null){
						while (is.read(buffer) > 0) {
						fos.write(buffer);
					}}
					 
					fos.close();
					 
					 
				}
				
				result = SUCCESS;				
			}catch (SQLException ex){
				result = FAILURE;
				DBConnectionManager.rollbackJDBCConnection(con);
				//	log.error(ex);
			}catch (Exception e){
				result = FAILURE;
				e.printStackTrace();
			}
			finally
			{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(st);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;	
		}
		
		public String updateUser(EmployeeBean mbUser) {
			// TODO Auto-generated method stub
			
			
			String result = null;
			PreparedStatement stmtUpdate = null;
			
			//Create a Database Connection
			Connection con = DBConnectionManager.getJDBCConnection();
			try{
				con.setAutoCommit(false);
						
				StringBuffer sbUpdate = new StringBuffer();
				
				sbUpdate.append("Update employee SET ");
				
				sbUpdate.append(" firstName = '" + mbUser.getFirstName() + "', ");
				sbUpdate.append(" lastName = '" + mbUser.getLastName() + "', ");
				sbUpdate.append(" email = '" + mbUser.getEmail() + "', ");	
				sbUpdate.append(" password = '" + mbUser.getPassword() + "' ");
				sbUpdate.append(" where empId = '" + mbUser.getEmpId()  + "'" );
				
				stmtUpdate = con.prepareStatement(sbUpdate.toString());
				
				int rows = stmtUpdate.executeUpdate();
				
				if (rows != 1)
				{
					result = FAILURE;
					throw new SQLException(
						"executeUpdate return value: "
						+ rows);
				}	
				result = SUCCESS;
				DBConnectionManager.commitJDBCConnection(con);
			}catch (SQLException ex){
				result = FAILURE;
				DBConnectionManager.rollbackJDBCConnection(con);
			//	log.error(ex);
			}
			finally
			{
				DBConnectionManager.closeStatement(stmtUpdate);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;	
			

		}
		
		
		public String getTodayDate()
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			return (dateFormat.format(date));
				
		}
		
		public String uploadFile(FileItem file, String empId) {
		
			String result = null;
			PreparedStatement stmtUpdate = null;
			
			//Create a Database Connection
			Connection con = DBConnectionManager.getJDBCConnection();
			try{
				con.setAutoCommit(false);
						
				StringBuffer sbUpdate = new StringBuffer();
				
	
			//	FileInputStream fis = new FileInputStream(file);
				

				sbUpdate.append("Update employee SET image = ? where empId = '"+ empId+ "'");
							
				stmtUpdate = con.prepareStatement(sbUpdate.toString());
				

		//		stmtUpdate.setBinaryStream(1, (InputStream)fis, (int)(file.length()));
			
				stmtUpdate.setBinaryStream(1, (InputStream)file.getInputStream(), (int)(file.getSize()));
				int rows = stmtUpdate.executeUpdate();
				file.delete();
				if (rows != 1)
				{
					result = FAILURE;
					throw new SQLException(
						"executeUpdate return value: "
						+ rows);
				}	
				result = SUCCESS;
				DBConnectionManager.commitJDBCConnection(con);
			}catch (SQLException ex){
				result = FAILURE;
				DBConnectionManager.rollbackJDBCConnection(con);
			//	log.error(ex);
			}catch(Exception e){}
			finally
			{
				DBConnectionManager.closeStatement(stmtUpdate);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;	
		}
		
		public String markOutTime(EmployeeBean user) {
			// TODO Auto-generated method stub
			
			String result = FAILURE;
			PreparedStatement stmtUpdate = null;
			
			//Create a Database Connection
			Connection con = DBConnectionManager.getJDBCConnection();
			try{
				con.setAutoCommit(false);
						
				StringBuffer sbUpdate = new StringBuffer();
				/*
				 * SELECT DateDiff( Date( loginTime ) , CurDate( ) )FROM `attendance` WHERE 1 
				 */
				if(checkTimeMarked(user) ){
					sbUpdate.append("Update attendance SET ");
					
					sbUpdate.append(" logoutTime = '" +getTodayDate()+ "' ");
					sbUpdate.append(" where empId = '" + user.getEmpId()  + "'" );
					sbUpdate.append(" and DateDiff( (loginTime ), CurDate( ) )=0");
					
					stmtUpdate = con.prepareStatement(sbUpdate.toString());
					
								
					int rows = stmtUpdate.executeUpdate();
					
					if (rows != 1)
					{
						result = FAILURE;
						throw new SQLException(
							"executeUpdate return value: "
							+ rows);
					}	
					result = SUCCESS;
					DBConnectionManager.commitJDBCConnection(con);
				}else{
					result = SUCCESS;
				}
				
			}catch (SQLException ex){
				result = FAILURE;
				DBConnectionManager.rollbackJDBCConnection(con);
			//	log.error(ex);
			}
			finally
			{
				DBConnectionManager.closeStatement(stmtUpdate);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;	
			

		}
		
		public String markInTime(EmployeeBean user) {
			// TODO Auto-generated method stub
			
			
			String result = null;
			PreparedStatement stmtInsert = null;
			
			//Create a Database Connection
			Connection con = DBConnectionManager.getJDBCConnection();
			try{
				con.setAutoCommit(false);
						
				StringBuffer sbInsert = new StringBuffer();
				
				// check if login time is already marked
				
				if(!checkTimeMarked(user))
				{
				sbInsert.append("INSERT INTO ");
				//TABLE_NAME
				sbInsert.append("attendance");
				sbInsert.append(" ( empId, loginTime ) ");
				sbInsert.append(" VALUES (");
				sbInsert.append(" ?, ?) ");
				
				stmtInsert = con.prepareStatement(sbInsert.toString());
				
				stmtInsert.setString(1, user.getEmpId());
				stmtInsert.setString(2, getTodayDate());
				
				int rows = stmtInsert.executeUpdate();
				
				if (rows != 1)
				{
					result = FAILURE;
					throw new SQLException(
						"executeUpdate return value: "
						+ rows);
				}	
				result = SUCCESS;
				DBConnectionManager.commitJDBCConnection(con);
				}
				else{
					result = SUCCESS;
				}
			}catch (SQLException ex){
				result = FAILURE;
				DBConnectionManager.rollbackJDBCConnection(con);
			//	log.error(ex);
			}
			finally
			{
				DBConnectionManager.closeStatement(stmtInsert);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;	
			

		}

		public boolean checkTimeMarked(EmployeeBean user) {
			// TODO Auto-generated method stub
			
			boolean result = false;
			
			//Create a Database Connection
			Connection con = DBConnectionManager.getJDBCConnection();
		
			Statement st = null;
			ResultSet rs = null;
		
			
			try{
				st = con.createStatement();
		
				rs = st.executeQuery("SELECT * FROM `attendance` WHERE empId = '" + user.getEmpId()+"'"+
						"AND DateDiff( (loginTime ), CurDate( ) )=0") ;
						
				while(rs.next()){
					result = true;			
				}
				
					 
			
			}catch (SQLException ex){
				result = false;
				DBConnectionManager.rollbackJDBCConnection(con);
				//	log.error(ex);
			}catch (Exception e){
				result = false;
				e.printStackTrace();
			}
			finally
			{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(st);
				DBConnectionManager.closeJDBCConnection(con);
			}
		
			return result;
		}
		
		public int getWorkingHours(EmployeeBean user)
		{
			int hours = 0;
		/*	SELECT TIME_TO_SEC( TIMEDIFF( logoutTime, loginTime ) )
			FROM attendance
			WHERE id = '1'  */
			
			Connection con = DBConnectionManager.getJDBCConnection();
			
			Statement st = null;
			ResultSet rs = null;
		
			
			try{
				st = con.createStatement();
		
				rs = st.executeQuery("SELECT SUM( TIME_TO_SEC( TIMEDIFF( logoutTime, loginTime ))  ) "+
						" FROM attendance "+
						" WHERE empId = '" + user.getEmpId() +"'") ;
						
				while(rs.next()){
					hours = rs.getInt(1);			
					hours = hours/3600;
				}
				
					 
			
			}catch (SQLException ex){
				hours = 0;
				DBConnectionManager.rollbackJDBCConnection(con);
				//	log.error(ex);
			}catch (Exception e){
				hours = 0;
				e.printStackTrace();
			}
			finally
			{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(st);
				DBConnectionManager.closeJDBCConnection(con);
			}
			
			return hours;
		}



		  	/*
	    	 * SELECT TIMEDIFF( logoutTime, loginTime ) AS mytime, empId, Date( loginTime )
	FROM attendance
	WHERE empId = '1'
	    	 */
			public Collection<WorkInfoBean> empWorkInfo(EmployeeBean user){
			{
				Collection<WorkInfoBean> workInfo = new ArrayList<WorkInfoBean> ();
								
				//Create a Database Connection
				Connection con = DBConnectionManager.getJDBCConnection();		
				Statement st = null;
				ResultSet rs = null;
			
				
				try{
					st = con.createStatement();
			
					rs = st.executeQuery("SELECT TIMEDIFF( logoutTime, loginTime ) AS mytime,"+
										" empId, Date( loginTime )as date FROM attendance " +
										" WHERE empId = '" + user.getEmpId() +"'") ;
							
					while (rs.next()){
						WorkInfoBean workBean = new WorkInfoBean();
						workBean.setWorkHours(rs.getInt("mytime"));
						workBean.setDate(rs.getString("date"));
						workBean.setEmpId(rs.getString("empId"));
						workInfo.add(workBean);
						
					}
					
									
				}catch (SQLException ex){
					
					DBConnectionManager.rollbackJDBCConnection(con);
					//	log.error(ex);
				}
				finally
				{
					DBConnectionManager.commitJDBCConnection(con);
					DBConnectionManager.closeStatement(st);
					DBConnectionManager.closeJDBCConnection(con);
				}
				
						
				return workInfo;
				
			}
			
		}
		
		
}
		

	