package com.command;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import com.beans.EmployeeBean;
import com.dao.EmployeeDAO;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.Iterator;
 
public class UploadCommand implements Command {
	private static final long serialVersionUID = -3208409086358916855L;
	 
	public void execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	 
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
	 
			try {
			List items = upload.parseRequest(request);
			Iterator iterator = items.iterator();
			File uploadedFile =null ;
			String empId = null;
			while (iterator.hasNext()) {
				
				FileItem item = (FileItem) iterator.next();	 
		
				if (!item.isFormField()) {
			/*		String fileName = item. getName();	 
					String path = "d:/";
					uploadedFile = new File(path + fileName);
					System.out.println(uploadedFile.getAbsolutePath());
					item.write(uploadedFile);*/
					uploadFileTODB(item , empId);
					
				}
				else{
					String formname = item.getFieldName();	 
					empId = item.getString();
									
					}
				
				}
			
			} catch (FileUploadException e) {
				e.printStackTrace();
				} catch (Exception e) {
				e.printStackTrace();
				}
				}
	}
	
	
	private void uploadFileTODB(FileItem file, String empId)
	{
		EmployeeDAO empObj = new EmployeeDAO();
		empObj.uploadFile(file, empId);
	}
}