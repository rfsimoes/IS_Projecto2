<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.News"%>
<%@page import="common.User"%>
<%@page import="ejbs.NewsBeanRemote"%>
<%@page import="javax.naming.InitialContext" %>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CNN News - Author News</title>
	</head>
	<body>
		<jsp:include page="auth_verification.jsp"></jsp:include>
		
		<%
			String author = session.getAttribute("author").toString();
		%>
	
		<h1>News from author: <%= author %></h1>
		
		
		<%  
        	User userdata = (User) session.getAttribute("user");

	        NewsBeanRemote newsbean = (NewsBeanRemote) session.getAttribute("newsBean");
	        
	        List<String> regioes = new ArrayList<String>();
	        List<News> newsAuthor = newsbean.newsFromAuthor(author);
	        
	        for(int i=0; i<newsAuthor.size();i++){
	        	if(!regioes.contains(newsAuthor.get(i).getRegion())){
	        		regioes.add(newsAuthor.get(i).getRegion());
	        	}
	        }
	        
	        for(int j=0; j<regioes.size(); j++){
	        	List<News> newsAuthorRegion = newsbean.newsFromAuthor(author, regioes.get(j));
        %>
				<h3><%= regioes.get(j) %></h3>
				<table border="1" align="center">
		            <tr>
		                <th>Title</th>
		                <th>url</th>
		            </tr>
		            <%  	
		            	for(int i=0; i<newsAuthorRegion.size(); i++){ 
		            %>
		            <tr>
		                <td> <%= newsAuthorRegion.get(i).getTitle() %> </td>
		                <td align="center"> <%= newsAuthorRegion.get(i).getUrl() %> </td>
		            </tr>
		            <%
		                }
		            %>
		        </table>
		        <% } %>
        
        <br>
        <br> 
        <pre>Logged as <strong><%= userdata.getUsername() %></strong></pre>
        
	</body>
</html>