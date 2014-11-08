<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Registration Page</title>
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
	
	<body bgcolor="#3498db">
	
		<center> 
			<h1>Registo</h1>
			
			<form action="RegistServlet" method="POST" id="login_form" onsubmit="return validateForm()">
				<p> Username <input type="text" name="username" id="username" /></p>
				<p> Password <input type="password" name="password" id="password" /></p>
				<p> Name <input type="text" name="name" id="name" /></p>
				<p> Email <input type="email" name="email" id="email" /></p>
				<input type="SUBMIT" value="Regist Account"/>
			</form>
			
			<br/>
			<br/>
				
			<a style="color:black" href="Login.jsp">Already have an account? <strong>Sign In</strong></a>
		
		</center>
	</body>
</html>