package com.command;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.EmployeeBean;
import com.dao.EmployeeDAO;

public class WorkingHoursCommand implements Command{
	
  public void execute(HttpServletRequest request,
                        HttpServletResponse response)
                    throws ServletException, IOException{
    	
	
	  EmployeeBean employeeBean = new EmployeeBean();
      mapToEmployeeBean (request, employeeBean);
                      
      int hours = getWorkingHours(employeeBean);
      request.setAttribute("hours", hours);
      request.setAttribute("empId", request.getParameter("empId"));
      ServletContext context = request.getSession().getServletContext();
      context.getRequestDispatcher("/totalHours.jsp").forward(request, response);
     
    }

  

	private void mapToEmployeeBean(HttpServletRequest request, EmployeeBean empBean) {
  
        empBean.setEmpId(request.getParameter("empId"));
    }
    
    private int getWorkingHours(EmployeeBean empBean){
    	
    	EmployeeDAO dao = new EmployeeDAO(); 
    	return dao.getWorkingHours(empBean);    	
    }
}
