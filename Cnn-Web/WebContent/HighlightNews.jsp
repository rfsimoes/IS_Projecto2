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
		<h1>News which highlights contains: murder</h1>
		
		<jsp:include page="auth_verification.jsp"></jsp:include>
		
		<%  
        	User userdata = (User) session.getAttribute("user");

	        NewsBeanRemote newsbean = (NewsBeanRemote) session.getAttribute("newsBean");
	        
	        List<String> regioes = new ArrayList<String>();
	        List<News> allNews = newsbean.getNews();
	        List<News> highlightNews = new ArrayList<News>();
	        
	        for(int i=0; i<allNews.size();i++){
	        	for(int j=0; j<allNews.get(i).getHighlights().size(); j++){
	        		if(allNews.get(i).getHighlights().get(j).contains("murder")){
	        			highlightNews.add(allNews.get(i));
	        			if(!regioes.contains(allNews.get(i).getRegion())){
	    	        		regioes.add(allNews.get(i).getRegion());
	    	        	}
	        		}
	        	}
	        }
	        
	        for(int j=0; j<regioes.size(); j++){
        %>
				<h3><%= regioes.get(j) %></h3>
				<table border="1" align="center">
		            <tr>
		                <th>Title</th>
		                <th>url</th>
		            </tr>
		            <%  	
		            	for(int i=0; i<highlightNews.size(); i++){ 
		            		if(highlightNews.get(i).getRegion().equals(regioes.get(j))){
		            %>
		            <tr>
		                <td> <%= highlightNews.get(i).getTitle() %> </td>
		                <td align="center"> <%= highlightNews.get(i).getUrl() %> </td>
		            </tr>
		            <%
		            		}
		                }
		            %>
		        </table>
		        <% } %>
        
        <br>
        <br> 
        <pre>Logged as <strong><%= userdata.getUsername() %></strong></pre>
        
	</body>
</html>