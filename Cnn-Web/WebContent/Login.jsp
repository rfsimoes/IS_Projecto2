<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login Page</title>
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
                }
                else{
                    document.getElementById("login_form").submit();
                }
            }
        </script>
	</head>
	<body bgcolor="#3498db">
		<center>
			<h1>Login</h1>
	
			<form action="LoginServlet" method="POST" id="login_form" onsubmit="return validateForm()">
				<p>
					Username <input type="text" name="username" id="username" />
				</p>
				<p>
					Password <input type="password" name="password" id="password" />
				</p>
				<input type="SUBMIT" value="Login" />
			</form>
			
			<br/><br/>
			
			<a style="color: black" href="Regist.jsp">Don't have an account? <strong>Sign Up</strong> now</a>

		</center>
	</body>
</html>