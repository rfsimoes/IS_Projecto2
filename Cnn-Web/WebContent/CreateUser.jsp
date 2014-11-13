<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="common.User"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>CNN - Create User</title>
		
		<!-- BOOTSTRAP -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<link href="bootstrap/css/font-awesome.min.css" rel="stylesheet" media="screen"/>
		<link href="bootstrap/css/style.css" rel="stylesheet" media="screen"/>
		
		<!-- VERIFICAÇÃO DOS CAMPOS DO FORMULÁRIO -->
		<script type="text/javascript" language="javascript">
            function validateForm() {
                if (document.getElementById("username").value == "") {
                    alert("Username field is empty");
                    document.getElementById("username").focus();
                    return false;
                } else  if (document.getElementById("password").value == "") {
                    alert("Password field is empty");
                    document.getElementById("password").focus();
                    return false;
                } else  if (document.getElementById("name").value == "") {
                    alert("Name field is empty");
                    document.getElementById("name").focus();
                    return false;
                } else  if (document.getElementById("email").value == "") {
                    alert("Email field is empty");
                    document.getElementById("email").focus();
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
					<a class="navbar-brand" href="#"><img src="bootstrap/img/cnn_logo.gif"/></a>
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
					    <li><a href="Logout.jsp">Logout</a></li>
				  	</ul>
				</div>
			</div>
		</nav>
	
		<center> 
			<h1>Create new user</h1>
			
			<form action="CreateUserServlet" method="POST" id="login_form" onsubmit="return validateForm()">
				<p> Username <input type="text" name="username" id="username" /></p>
				<p> Password <input type="password" name="password" id="password" /></p>
				<p> Name <input type="text" name="name" id="name" /></p>
				<p> Email <input type="email" name="email" id="email" /></p>
				<input type="SUBMIT" value="Create"/>
			</form>
			
			<br>
			
			<%
				if(request.getParameter("fail") != null){
			%>
					<div class="container">
						<div class="alert alert-danger" role="alert">Failed to create user: username/email already exists!</div>
					</div>
			<%
				}
			%>
		</center>
	</body>
</html>