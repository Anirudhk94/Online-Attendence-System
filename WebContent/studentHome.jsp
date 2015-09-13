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
<!-- top header ends here  -->

<!-- header starts here  --><div class="header menu">
    	
   <a href="studentHome.jsp">Home</a> | <a href="logout.jsp">Logout</a>
        
    </div><!-- header ends here  -->
    <!-- middle content starts here  -->
    <%@page import="com.shreshta.oas.bean.UserBean"%>
    <div class="inner-content">
        
   	 <!-- side bar starts here  -->   
	<div class="sidebar left">
			<div class="glossymenu">
			<h3>Manage profile</h3>
	<ul><li><a  href="editProfile.jsp">Edit profile</a></li>
	</ul>
			
			<h3>Student Management</h3>
	<ul><li><a  href="StudentStatusController">Attendance status</a></li>
	</ul>
	</div>   </div>
	<%UserBean userBean = (UserBean)request.getSession().getAttribute("userBean"); %>
	  <!-- side bar ends here  -->    
	  <div class = inner>
	  <h1>Welcome <%=userBean.getName() %></h1>
			
	  </div>
</div>
<div class="footer">
        <a href="studentHome.jsp">Home</a> | <a href="logout.jsp">Logout</a>
        </div>
    </div>
</body>
</html>