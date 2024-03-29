<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="common.User"%>

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
		
		<!-- VERIFICAÇÃO DOS CAMPOS DO FORMULÁRIO -->
		<script type="text/javascript" language="javascript">
            function validateForm(campo) {
            	if(campo == 'author'){
	                if (document.getElementById("authorName").value == "") {
	                    alert("Author's name not defined");
	                    document.getElementById("authorName").focus();
	                    return false;
	                }
	                else{
	                    document.getElementById("login_form").submit();
	                }
	            }
            	else if(campo == 'word'){
            		if (document.getElementById("word").value == "") {
	                    alert("Word not defined");
	                    document.getElementById("word").focus();
	                    return false;
	                }
	                else{
	                    document.getElementById("login_form").submit();
	                }
            	}
            	else if(campo == 'date'){
            		if (document.getElementById("date").value == "") {
	                    alert("Date not defined");
	                    document.getElementById("date").focus();
	                    return false;
	                }
	                else{
	                    document.getElementById("login_form").submit();
	                }
            	}
            }
            
            // Confirmação da eliminação de conta
            function confirmar(){
            	var txt;
                var r = confirm("Are you sure you want to delete your account?\nPress Ok to delete, Cancel to quit!");
                if (r == true) {
                    txt = "Ok";
                    document.location.href="DeleteAccountServlet";
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
					    <li><a href="EditProfile.jsp">Edit profile</a></li>
					    <li><a onclick="return confirmar()">Delete account</a></li>
					    <li class="divider"></li>
					    <li><a href="Logout.jsp">Logout</a></li>
				  	</ul>
				</div>
			</div>
		</nav>
		
		
		<!-- CONTEUDO -->
		<center>
			<h2>Welcome to CNN News</h2>
			<h4>What do you want to do?</h4>
			
			<br>
			
			<div>
	            <a href="AllNewsServlet" style="display: inline-block; color: #0000ff; text-decoration: none">
	                List all the news
	            </a>
	        </div>
	        
	        <br>
	        
	        <form action="AuthorNewsServlet" method="POST" id="login_form" onsubmit="return validateForm('author')">
					<p>
						List news from <input type="text" name="authorName" id="authorName" placeholder="author's name"/>
						<input type="SUBMIT" value="Ok" />
					</p>
			</form>
	        
	        <br>
	        
	        <form action="DateNewsServlet" method="GET" id="login_form" onsubmit="return validateForm('date')">
					<p>
						List news more recent than <input type="datetime-local" name="date" id="date" />
						<input type="SUBMIT" value="Ok" />
					</p>
			</form>
	        
	        <br>
	        
			<form action="HighlightNewsServlet" method="POST" id="login_form" onsubmit="return validateForm('word')">
					<p>
						List news which highlights contains <input type="text" name="word" id="word" placeholder="word"/>
						<input type="SUBMIT" value="Ok" />
					</p>
			</form>
		</center>
	</body>
</html>