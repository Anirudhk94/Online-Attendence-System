package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import com.dao.EmployeeDAO;
import com.beans.EmployeeBean;


public class ViewAllCommand  implements Command{
	  public void execute(HttpServletRequest request,
	                        HttpServletResponse response)
	                    throws ServletException, IOException{

		
	    	Collection<EmployeeBean>  empBeans = new ArrayList<EmployeeBean>();
	    	empBeans = this.selectUsers();
          
	        if (!empBeans.isEmpty()){
	               	request.setAttribute("empBeans", empBeans);
	                request.setAttribute("empId", request.getParameter("empId"));
	                ServletContext context = request.getSession().getServletContext();
	                context.getRequestDispatcher("/viewAllUsers.jsp").forward(request, response);
	        }
	        else{
	        	  ServletContext context = request.getSession().getServletContext();
	              context.getRequestDispatcher("/login.jsp").forward(request, response);
	        }
	    }

	    
	    private Collection<EmployeeBean> selectUsers(){
	    	
	    	EmployeeDAO dao = new EmployeeDAO(); 
	    	return dao.selectUsers();    	
	    }
	}
