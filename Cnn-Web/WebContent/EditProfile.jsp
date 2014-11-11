<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="common.User"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>CNN - Edit Profile</title>
		
		<!-- BOOTSTRAP -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<link href="bootstrap/css/font-awesome.min.css" rel="stylesheet" media="screen"/>
		<link href="bootstrap/css/style.css" rel="stylesheet" media="screen"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script language="javascript" type="text/javascript" src="bootstrap/js/dropdown.js"></script>
		
		<!-- VERIFICAÇÃO DOS CAMPOS DO FORMULÁRIO -->
		<script type="text/javascript" language="javascript">
            function validateForm() {
                if (document.getElementById("password").value == ""
                		&& document.getElementById("name").value == ""
                		&& document.getElementById("email").value == "") {
                    alert("All the fields are empty");
                    return false;
                }
                else{
                    document.getElementById("login_form").submit();
                }
            }
        </script>
	</head>
	
	<body>
	
		<!-- HEADER -->
		<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<!-- Logo CNN -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-menu">
						<i class="icon-reorder"></i>
					</button>
					<a class="navbar-brand" href="Menu.jsp"><img src="bootstrap/img/cnn_logo.gif"/></a>
				</div>
				<!-- Informação do utilizador -->
				<%  
					User userdata = (User) session.getAttribute("user");
		        %>
				<div class="btn-group nav navbar-nav navbar-right">
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
				    	Logged as <strong><%= userdata.getUsername() %></strong> <span class="caret"></span>
				  	</button>
				  	<ul class="dropdown-menu" role="menu">
					    <li><a href="EditProfile.jsp">Edit profile</a></li>
					    <li><a href="DeleteAccount.jsp">Delete account</a></li>
					    <li class="divider"></li>
					    <li><a href="Logout.jsp">Logout</a></li>
				  	</ul>
				</div>
			</div>
		</nav>
	
		<center> 
			<h1>Edit profile</h1>
			
			<form action="EditProfileServlet" method="POST" id="login_form" onsubmit="return validateForm()">
				<p> Password <input type="password" name="password" id="password" /></p>
				<p> Name <input type="text" name="name" id="name" /></p>
				<p> Email <input type="email" name="email" id="email" /></p>
				<input type="SUBMIT" value="Ok"/>
			</form>
			<br>
			<%
				if(request.getParameter("success") != null){
					if(request.getParameter("success").equals("1")){
			%>
						<div class="alert alert-success" role="alert">Profile updated!</div>
			<%
					}
					else if(request.getParameter("success").equals("0")){
			%>
						<div class="alert alert-danger" role="alert">Failed to edit profile: email already exists!</div>
			<%
					}
				}
			%>
		
		</center>
	</body>
</html>