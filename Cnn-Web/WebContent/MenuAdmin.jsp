<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="common.User"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>CNN - Main Menu</title>
		
		<!-- BOOTSTRAP -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<link href="bootstrap/css/font-awesome.min.css" rel="stylesheet" media="screen"/>
		<link href="bootstrap/css/style.css" rel="stylesheet" media="screen"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script language="javascript" type="text/javascript" src="bootstrap/js/dropdown.js"></script>
		
		<!-- Confirmação da eliminação de conta de um utilizador -->
		<script type="text/javascript" language="javascript">
			function confirmar(username){
		    	var txt;
		        var r = confirm("Are you sure you want to delete "+username+"'s account?\nPress Ok to delete, Cancel to quit!");
		        if (r == true) {
		            txt = "Ok";
		            document.location.href="DeleteAccountServlet?admin="+username;
		        } else {
		            txt = "Cancel";
		        }
		    }
		</script>
	</head>
	
	
	<body>
		<jsp:include page="auth_verification.jsp"></jsp:include>
	
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
		
		
		<!-- CONTEUDO -->
		<center>
			<h2>Welcome to CNN News</h2>
			<h4>User's management</h4>
			<br>
			<div class="container">
				
				<!-- Tabela com utilizadores -->
				<table class="table table-striped">
					<tr>
						<th>Username</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
					<%
						pageContext.setAttribute("usersList", session.getAttribute("utilizadores"));
					%>
					<c:forEach var="utilizador" items="${usersList}" varStatus="status">
						<tr>
							<td>${utilizador.username}</td>
							<td><a href="AdminEdit.jsp?user=${utilizador.username}"><span class="glyphicon glyphicon-pencil"></span></a></td>
							<td><a onclick="return confirmar('${utilizador.username}')"><span class="glyphicon glyphicon-trash"></span></a></td>
						</tr>
					</c:forEach>
				</table>
				
				<!-- Criar novo utilizador -->
				<a href="CreateUser.jsp"><button type="button" class="btn btn-primary">Create new user</button></a>
			</div>
		</center>
	</body>
</html>