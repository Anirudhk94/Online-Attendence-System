package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import com.dao.EmployeeDAO;
import com.beans.EmployeeBean;



public class DeleteCommand implements Command{
  public void execute(HttpServletRequest request,
                        HttpServletResponse response)
                    throws ServletException, IOException{
	  
	 
     String empIdToDelete = request.getParameter("notify") ;    
     
     
      if ("success".equalsIgnoreCase(this.deleteUser(empIdToDelete))){
    	
    	  EmployeeBean employeeBean = new EmployeeBean();
    	  employeeBean.setEmpId(request.getParameter("empId"));
        
                    
            if ("success".equalsIgnoreCase(this.getUserInfo(employeeBean))){
            	
            	request.setAttribute("employeeBean", employeeBean);
            	request.setAttribute("empId", request.getParameter("empId"));
            	ServletContext context = request.getSession().getServletContext();
            	context.getRequestDispatcher("/welcomeUser.jsp").forward(request, response);
            }
            else
            {
           	  ServletContext context = request.getSession().getServletContext();
              context.getRequestDispatcher("/login.jsp").forward(request, response);
            }
      }
      else{
      	  ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/login.jsp").forward(request, response);
      }
  }
  
 
      private String getUserInfo(EmployeeBean empBean){
      	
      	EmployeeDAO dao = new EmployeeDAO(); 
      	return dao.getUserInfo(empBean);    	
      }
  
      
  private String deleteUser(String empId){
  	
  	EmployeeDAO dao = new EmployeeDAO(); 
  	return dao.deleteUser(empId);    	
  }
}
