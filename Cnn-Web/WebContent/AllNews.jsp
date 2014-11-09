<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="common.News"%>
<%@page import="common.User"%>
<%@page import="ejbs.NewsBeanRemote"%>
<%@page import="javax.naming.InitialContext" %>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CNN News - All the news</title>
	</head>
	<body>
		<h1>All the news</h1>
		
		<jsp:include page="auth_verification.jsp"></jsp:include>
		
		<%  
        	User userdata = (User) session.getAttribute("user");

	        NewsBeanRemote newsbean = (NewsBeanRemote) session.getAttribute("newsBean");

        	List<News> news = newsbean.newsSortedByDate();
        %>
		
		<table border="1" align="center">
            <tr>
                <th>Title</th>
                <th>url</th>
            </tr>
            <%  	
            	for(int i=0; i<news.size(); i++){ 
            %>
            <tr>
                <td> <%= news.get(i).getTitle() %> </td>
                <td align="center"> <%= news.get(i).getUrl() %> </td>
            </tr>
            <%
                } 
            %>
        </table>
        
        <br>
        <br> 
        <pre>Logged as <strong><%= userdata.getUsername() %></strong></pre>
        
	</body>
</html>