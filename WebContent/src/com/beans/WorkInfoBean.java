package com.beans;

public class WorkInfoBean {

	
	private int workhours;
	private String date;
	private String empId;
		
	public void setEmpId(String empId){
		this.empId = empId;
	}
	
	public String getEmpId(){
		return empId;
	}
	
	public void setWorkHours(int workhours){
		this.workhours = workhours;
	}
	public void setDate(String date){
		this.date = date;
	}
	
	public int getWorkhours(){
		return this.workhours;
	}
	public String getDate(){
		return this.date;
	}
	
}
