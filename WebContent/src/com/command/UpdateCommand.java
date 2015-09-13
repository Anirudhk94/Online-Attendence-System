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



public class UpdateCommand implements Command{
	
  public void execute(HttpServletRequest request,
                        HttpServletResponse response)
                    throws ServletException, IOException{
    	
	
	  EmployeeBean employeeBean = new EmployeeBean();
        mapToEmployeeBean (request, employeeBean);
                
        if ("success".equalsIgnoreCase(this.updateUser(employeeBean))){
        
        	request.setAttribute("employeeBean", employeeBean);
        	 request.setAttribute("empId", request.getParameter("empId"));
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/welcomeUser.jsp").forward(request, response);
        }
        else{
        	  ServletContext context = request.getSession().getServletContext();
              context.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

  

	private void mapToEmployeeBean(HttpServletRequest request, EmployeeBean empBean) {
        empBean.setFirstName(request.getParameter("firstName"));
        empBean.setLastName( request.getParameter("lastName"));
        empBean.setEmail(request.getParameter("email"));
        empBean.setPassword(request.getParameter("password"));
        empBean.setEmpId(request.getParameter("empId"));
    }
    
    private String updateUser(EmployeeBean empBean){
    	
    	EmployeeDAO dao = new EmployeeDAO(); 
    	return dao.updateUser(empBean);    	
    }
}
