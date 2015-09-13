
<%@page import="com.shreshta.oas.bean.AttendanceBean"%>
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
			<div class="right login-top">
				<!--<a href="#">Login</a> | <a href="#">Forgot Password</a>-->
			</div>
		</div>

		<div class="clearfix">&nbsp;</div>
		<!-- top header ends here  -->

		<!-- header starts here  -->
		<div class="header menu">


			<a href="facultyHome.jsp">Home</a> | <a href="logout.jsp">Logout</a>

		</div>
		<!-- header ends here  -->
		<!-- middle content starts here  -->

		<div class="inner-content">

			<!-- side bar starts here   
	<div class="sidebar left">
			<div class="glossymenu">
			<h3>Manage profile</h3>
	<ul><li><a  href="editProfile.jsp">Edit profile</a></li>
	</ul>
			
			<h3>Manage Attendance</h3>
	<ul>
		<li><a  href="#">Check Status</a></li>
		<li><a  href="#">Update</a></li>
	</ul>
	</div>   </div>
	  <!-- side bar ends here  -->
			<div class="inner">




				<!-- Start define a form here-->
				<!-- form_action is defined as login. Check Controller.java and LoginCommand.java class -->


				<form name="addEmp" method="post" action="UpdateAttendanceController">
					<input type="hidden" name="form_action" value="insert">
					<%
			//ArrayList<UserBean> userList = (ArrayList<UserBean>) session.getAttribute("userList");
			ArrayList<AttendanceBean> studentAttendanceList = (ArrayList<AttendanceBean>) session.getAttribute("attList");		
		%>
					<table align='center' cellspacing='0'>


						<Tr>
							<TH>Name</TH>
							<th>Attendance</TH>

						</Tr>
						<% int i = 0;
							int j  = 100 ;
						for(AttendanceBean attendance : studentAttendanceList){
							
							
		%>
						<TR>
							<TD><%=attendance.getStudent() %></TD>
							<TD>
							<%if(attendance.getStatus().equals("present")){ %>
								<input type="radio" id="<%=i %>" name="<%=attendance.getStudent() %>"
								value="present" checked="checked"/> 
								<label for="<%=i %>">Present</label>
								<input type="radio" id="<%=j %>" name="<%=attendance.getStudent() %>" value="absent" />
								<label for="<%=j %>">Absent</label>
								<%}else{ %>
								<input type="radio" id="<%=i %>" name="<%=attendance.getStudent() %>"
								value="present"/> 
								<label for="<%=i %>"> Present</label> 
								<input type="radio" id="<%=j %>" name="<%=attendance.getStudent() %>" value="absent"  checked="checked" />
								<label for="<%=j %>">Absent</label>
								<%} %>
							</TD>





						</TR>
						<%  ++i;++j;}%>
						<tr>
							<td><input type="submit" name="buton_submit" value="Update" /></td>
							<td><input type="reset" name="button_reset" value="Reset" /></td>

						</tr>
					</table>

				</form>
				<!-- End define a form here-->

			</div>
		</div>
		<div class="footer">
			<a href="facultyHome.jsp">Home</a> | <a href="logout.jsp">Logout</a>

		</div>
		<!-- footer ends here  -->
	</div>
</body>
</html>