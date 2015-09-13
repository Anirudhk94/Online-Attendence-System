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



public class LogoutTimeCommand implements Command{
	
  public void execute(HttpServletRequest request,
                        HttpServletResponse response)
                    throws ServletException, IOException{
    	
	
	  EmployeeBean employeeBean = new EmployeeBean();
      mapToEmployeeBean (request, employeeBean);
                
        if ("success".equalsIgnoreCase(this.markOutTime(employeeBean))){
        
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/markSuccess.jsp").forward(request, response);
        }
        else{
        	  ServletContext context = request.getSession().getServletContext();
              context.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

  

	private void mapToEmployeeBean(HttpServletRequest request, EmployeeBean empBean) {
  
        empBean.setEmpId(request.getParameter("empId"));
    }
    
    private String markOutTime(EmployeeBean empBean){
    	
    	EmployeeDAO dao = new EmployeeDAO(); 
    	return dao.markOutTime(empBean);    	
    }
}
