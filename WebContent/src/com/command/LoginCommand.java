package com.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;


import com.dao.EmployeeDAO;
import com.beans.EmployeeBean;



public class LoginCommand implements Command{

	public static int count = 0;
  public void execute(HttpServletRequest request,
                        HttpServletResponse response)
                    throws ServletException, IOException{

	
	  HttpSession session = request.getSession(false);
		  
	  if(!session.isNew())
	  {
		  String empId = request.getParameter("empId");
		  String empIdOld = (String)session.getAttribute("empId");
		  
		  if(empIdOld != null && empId.equals(empIdOld) )
			  count += 1 ;
	  }
    	
	  	EmployeeBean employeeBean = new EmployeeBean();
        mapToEmployeeBean (request, employeeBean);
                
        if ("success".equalsIgnoreCase(this.checkUser(employeeBean))){
        
        	String empId = request.getParameter("empId");
        	session.setAttribute("empId", empId);
        	String fileName = "/home/tsuser/Desktop/EmployeeAttendance/WebContent/Store"+employeeBean.getEmpId()+".jpg";
        	request.setAttribute("fileName", fileName);
        	request.setAttribute("employeeBean", employeeBean);
        	request.setAttribute("count", count);
        	 ServletContext context = request.getSession().getServletContext();
             context.getRequestDispatcher("/welcomeUser.jsp").forward(request, response);
        }else
        {
        	 ServletContext context = request.getSession().getServletContext();
             context.getRequestDispatcher("/login.jsp").forward(request, response);	
        }
               
    }

    

	private void mapToEmployeeBean(HttpServletRequest request, EmployeeBean empBean) {
					
		
		empBean.setEmpId(request.getParameter ("empId"));
        empBean.setPassword( request.getParameter("password"));
	}
    
    private String checkUser(EmployeeBean empBean){
    	
    	EmployeeDAO dao = new EmployeeDAO(); 
    	return dao.checkUser(empBean);    	
    }
}
