<%@page import="com.shreshta.oas.bean.AttendanceBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shreshta.oas.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Online Attendance System</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
</head>
<body>

<div class="main">

<div class="header-top">
  <div class="right login-top"><!--<a href="#">Login</a> | <a href="#">Forgot Password</a>--></div>
  </div>
  
    <div class="clearfix">&nbsp;</div>


<div class="header menu">
    	
   <a href="facultyHome.jsp">Home</a> | <a href="logout.jsp">Logout</a>
        
   
    
    <div class="inner-content">
       <%ArrayList<AttendanceBean> studentAttendanceList = new ArrayList<AttendanceBean>();
       	studentAttendanceList =(ArrayList<AttendanceBean>) session.getAttribute("studentAttendanceList");%>  
   	 <!-- side bar starts here  -->   
	<div class="sidebar left">
			<div class="glossymenu">
			<h3>Manage profile</h3>
	<ul><li><a  href="editProfile.jsp">Edit profile</a></li>
	</ul>
			
			<h3>Manage Attendance</h3>
			<ul>
			<%if(studentAttendanceList == null){ %>
				<li><a href="MarkAttendanceController">Mark attendance</a></li><%} %>
				<li><a href="attendanceStatus.jsp">Check Status</a></li>
				<li><a href="ModifyAttendance">Modify attendance</a></li>
			</ul>
				</div>   </div>
	  <!-- side bar ends here  --> 
	  <%UserBean userBean = (UserBean)request.getSession().getAttribute("userBean") ;%>   
	  <div class = inner>
	  <h1>Welcome <%=userBean.getName() %></h1>
			
	  </div>
</div>
<div class="footer">
        <a href="facultyHome.jsp">Home</a> | <a href="logout.jsp">Logout</a>
        </div>
    </div>
</body>
</html>