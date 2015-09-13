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
    	
   <a href="adminHome.jsp">Home</a> | <a href="logout.jsp">Logout</a>
        
    </div><!-- header ends here  -->
    <!-- middle content starts here  -->
    
    <div class="inner-content">
        
   	 <!-- side bar starts here  -->   
	<div class="sidebar left">
			<div class="glossymenu">
			
			<h3>Admin Management</h3>
	<ul><li><a  href="addUser.jsp">Add User</a></li>
	<li><a  href="ViewAllUsersController">List All Users</a></li>
	<li><a  href="attendanceStatus.jsp">Show Attendance </a></li></ul>
	</div>   </div>
	  <!-- side bar ends here  -->    
	  <div class = inner>
	  <h1>Welcome Admin</h1>
			<!-- <p>	Most companies that sell computer-related software or hardware
				will also provide their clients access to comprehensive technical
				support. Some end users may find this kind of assistance necessary,
				depending upon their own experience and the product's ease of use.
				Help desk administrators provide this support.</p> -->
	  </div>
</div>
<div class="footer">
        <a href="adminHome.jsp">Home</a> | <a href="logout.jsp">Logout</a>
        </div>
    </div>
</body>
</html>