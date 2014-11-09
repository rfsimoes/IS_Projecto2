<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="common.User"%>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CNN - Main Menu</title>
	</head>
	<body>
		<h1>Welcome to CNN News</h1>
		
		<jsp:include page="auth_verification.jsp"></jsp:include>
		
		<%  
			User userdata = (User) session.getAttribute("user");
        %>
		
		<div>
            <a href="AllNews.jsp">
                List all the news
            </a>
        </div>
        <div>
            <a href="AuthorNews.jsp">
                List news from an author
            </a>
        </div>
        <div>
            <a href="DateNews.jsp">
                List news more recent than a date
            </a>
        </div>
        <div>
            <a href="HighlightNews.jsp">
                List news which highlights contains a word
            </a>
        </div>
        <br>
        <br> 
        <pre>Logged as <strong><%= userdata.getUsername() %></strong></pre>
		
	</body>
</html>