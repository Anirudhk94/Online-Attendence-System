
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.shreshta.oas.bean.AttendanceBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shreshta.oas.bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Online Attendance System</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="Javascript/jss.js"></script>

</head>
<body>
	<script type="text/javascript">
function goBack(){
	window.history.back();
}


</script>

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

			<a href="studentHome.jsp">Home</a> | <a href="logout.jsp">Logout</a>

		</div>
		<!-- header ends here  -->
		<!-- middle content starts here  -->

		<div class="inner-content">

			<!-- side bar starts here    
	<div class="sidebar left">
			<div class="glossymenu">
			
			<h3>Admin Management</h3>
	<ul><li><a  href="#">Add User</a></li>
	<li><a  href="#">List All Users</a></li>
	<li><a  href="#">Show Attendance </a></li></ul>
	</div>   </div>
	  <!-- side bar ends here  -->
			<div class="inner">




				<!-- Start define a form here-->
				<!-- form_action is defined as login. Check Controller.java and LoginCommand.java class -->

				<form name="addEmp" method="post" onsubmit="return validate()">
					<input type="hidden" name="form_action" value="insert">
					<h2 align='center'>Attendance status</h2>
					<br>

					<table align='center' cellspacing='0'>
					<%
					ArrayList<AttendanceBean> attRecordList = new ArrayList<AttendanceBean>();
					attRecordList = (ArrayList<AttendanceBean>)request.getSession().getAttribute("studentRecords") ;
					%>
					<tr>
					<th>Date</th>
					<th>Attendance</th>
					</tr>
					<%for(AttendanceBean studentAttendance : attRecordList){%>
						<tr>
							<td><%=studentAttendance.getDate() %></td>
							<td><%=studentAttendance.getStatus() %></td>
						</tr>
					<%}%>
						
					

					</table>



				</form>


				<!-- End define a form here-->

			</div>
		</div>
		<div class="footer">
			<a href="studentHome.jsp">Home</a> | <a href="logout.jsp">Logout</a>

		</div>
		<!-- footer ends here  -->
	</div>
</body>
</html>