
<%@page import="com.shreshta.oas.bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
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
	
	<li><a  href="attendanceStatus.jsp">Show Attendance </a></li></ul>
	</div>   </div>
	  <!-- side bar ends here  -->   
	  <div class="inner">    




<!-- Start define a form here-->
<!-- form_action is defined as login. Check Controller.java and LoginCommand.java class -->


  <form name="addEmp" method="post" >
  <input type="hidden" name="form_action" value="insert">
		<h2 align = 'center'>View All Users</h2><br>
		<%
			ArrayList<UserBean> userList = (ArrayList<UserBean>) session.getAttribute("userList");
		%>
		<table align = 'center' cellspacing = '0'>
			

					<Tr>
						<TH>User ID </TH>
						<TH>Name </TH>
						<th>Designation</TH>
					
					</Tr>
	<%for(UserBean user : userList){ %>
					<TR>
	<TD><%=user.getUserId() %></TD>
						<TD><%=user.getName() %></TD>
						<TD><%=user.getRole() %></TD>
						
					</TR>
	<%} %>
				</table>
	</form>
     <!-- End define a form here-->
 
 </div> 
</div>
 <div class="footer">
       <a href="adminHome.jsp">Home</a> | <a href="logout.jsp">Logout</a>    
        
    </div><!-- footer ends here  -->
    </div>
</body>
</html>