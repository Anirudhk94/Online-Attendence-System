package com.command;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.EmployeeBean;
import com.beans.WorkInfoBean;
import com.dao.EmployeeDAO;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;


public class WorkInfoChartCommand implements Command{
	
  public void execute(HttpServletRequest request,
                        HttpServletResponse response)
                    throws ServletException, IOException{
    	
	
	  EmployeeBean employeeBean = new EmployeeBean();
      mapToEmployeeBean (request, employeeBean);
      
    
      
      Collection<WorkInfoBean> workInfo = getWorkInfo(employeeBean);      
      
      if ("success".equalsIgnoreCase(this.chartToFile(workInfo))){
    	  if ("success".equalsIgnoreCase(this.getUserInfo(employeeBean))){
	      request.setAttribute("workInfo", workInfo);
	      request.setAttribute("employeeBean", employeeBean);
	      request.setAttribute("empId", request.getParameter("empId"));
	      ServletContext context = request.getSession().getServletContext();
	      context.getRequestDispatcher("/workChart.jsp").forward(request, response);
      }}else
      {
    	  ServletContext context = request.getSession().getServletContext();
          context.getRequestDispatcher("/login.jsp").forward(request, response);
    	  
      }
    
     
    }

  

	private void mapToEmployeeBean(HttpServletRequest request, EmployeeBean empBean) {
  
        empBean.setEmpId(request.getParameter("empId"));
    }
    
    private Collection <WorkInfoBean> getWorkInfo(EmployeeBean empBean){
 
    	EmployeeDAO dao = new EmployeeDAO(); 
    	return dao.empWorkInfo(empBean);    	
    }
    
    
    public String chartToFile(Collection<WorkInfoBean> workInfo){
    	
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	
    	Iterator i = workInfo.iterator();
        while (i.hasNext()) {
        	WorkInfoBean item = (WorkInfoBean)i.next();
        	dataset.setValue(item.getWorkhours(),"Hours",item.getDate());
               
        }
   
    	JFreeChart chart = ChartFactory .createBarChart3D(
    	"Hours",
    	"Date",
    	"Work Hours",
    	dataset,
    	PlotOrientation.VERTICAL,true, true, false);
    	try{
    		ChartUtilities.saveChartAsJPEG(new File("/home/tsuser/Desktop/EmployeeAttendance/WebContent/chart.jpg"), chart, 400, 300);
    	}
    	catch (IOException e){
    		
	    	System.out.println("Problem in creating chart.");
	    	return "FAILURE";
    	}
    	
    	return "SUCCESS" ;
    	
    }
    
   public String getUserInfo(EmployeeBean employeeBean){
	   
	 	EmployeeDAO dao = new EmployeeDAO(); 
    	return dao.getUserInfo(employeeBean);    	
   }
		   
}
