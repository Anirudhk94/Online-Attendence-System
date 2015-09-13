package com.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import com.beans.EmployeeBean;


public class InitCommand implements Command{
  public void execute(HttpServletRequest request,
                        HttpServletResponse response)
                    throws ServletException, IOException{

        request.setAttribute("EmployeeBean", new EmployeeBean());

        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/view.jsp").forward(request, response);
    }
}
