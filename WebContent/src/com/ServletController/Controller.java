package com.ServletController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.command.Command;
import com.command.DeleteCommand;
import com.command.InitCommand;
import com.command.LoginCommand;
import com.command.LoginTimeCommand;
import com.command.LogoutTimeCommand;
import com.command.UpdateCommand;
import com.command.UploadCommand;
import com.command.WorkInfoChartCommand;
import com.command.WorkingHoursCommand;
import com.command.WriteCommand;
import com.command.ViewAllCommand;

import java.util.Map;
import java.util.HashMap;

public class Controller extends HttpServlet {

	 private Map commands = new HashMap();

	    @Override
	    public void init() throws ServletException{
	        super.init();
	        
	       	        
	        this.commands.put("init",  new InitCommand());
	        this.commands.put("write", new WriteCommand());
	        this.commands.put("login", new LoginCommand());
	        this.commands.put("update", new UpdateCommand());
	        this.commands.put("viewAll", new ViewAllCommand());
	        this.commands.put("delete", new DeleteCommand());
	        this.commands.put("upload", new UploadCommand());
	        this.commands.put("loginTime", new LoginTimeCommand());
	        this.commands.put("logoutTime", new LogoutTimeCommand());
	        this.commands.put("totalHours", new WorkingHoursCommand());
	        this.commands.put("workInfo", new WorkInfoChartCommand());
	    }

	   
	    /** 
	     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	     */
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	      processCommand(request, response);

	    } 

	      /** 
	     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	     */
	    public void processCommand(HttpServletRequest  request,
	                               HttpServletResponse response)
	                           throws ServletException, IOException{

	        String formAction = request.getParameter("form_action");

	        if(null == formAction){
	            formAction = "upload";
	        }

	        Command command = (Command)commands.get(formAction);

	        if(null == command){
	            throw new IllegalArgumentException(
	                "No command for form action: " + formAction);
	        }
	        command.execute(request, response);
	    }
	  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	    /** 
	     * Handles the HTTP <code>GET</code> method.
	     * @param request servlet request
	     * @param response servlet response
	     * @throws ServletException if a servlet-specific error occurs
	     * @throws IOException if an I/O error occurs
	     */
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        processRequest(request, response);
	    } 

	    /** 
	     * Handles the HTTP <code>POST</code> method.
	     * @param request servlet request
	     * @param response servlet response
	     * @throws ServletException if a servlet-specific error occurs
	     * @throws IOException if an I/O error occurs
	     */
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        processRequest(request, response);
	    }

	    /** 
	     * Returns a short description of the servlet.
	     * @return a String containing servlet description
	     */
	    @Override
	    public String getServletInfo() {
	        return "Short description";
	    }// </editor-fold>
}

