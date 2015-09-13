package com.shreshta.oas.bean;

import java.io.Serializable;

public class AttendanceBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String faculty;
	private String student;
	private String date;
	private String status ;
	
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
