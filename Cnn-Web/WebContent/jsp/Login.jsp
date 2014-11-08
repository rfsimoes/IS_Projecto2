<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body bgcolor="#3498db">
	

<center> 
	<h1 style="font-family:Viner Hand ITC; color:white; font-size:60px;" >Login Page </h1>
	
	<form action="LoginServlet" method="POST">
		<p style="color:black"> Username <input type="text" name="username" /></p>
		<p style="color:black"> Password <input type="password" name="password" /></p>
		<input type="SUBMIT" style="Segoe Marker" value="Login"/>
	</form>
		
	
	<br />
	<br />

	
	<a style="color:black" href="Regist.jsp"> Create new account!</a>




</center>
</body>
</html>