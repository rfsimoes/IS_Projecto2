<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="common.User"%>

<%  
	User user = (User) session.getAttribute("user");
	if (user == null){
%>
		<jsp:forward page="/Login.jsp?unauthorized=1"></jsp:forward>
<%
	}
%>