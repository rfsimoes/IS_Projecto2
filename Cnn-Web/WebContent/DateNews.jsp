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
		<title>CNN News - News more recent than</title>
		
		<!-- BOOTSTRAP -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<link href="bootstrap/css/font-awesome.min.css" rel="stylesheet" media="screen"/>
		<link href="bootstrap/css/style.css" rel="stylesheet" media="screen"/>
		
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
				<!-- Informação de Login -->
				<%  
					User userdata = (User) session.getAttribute("user");
		        %>
				<div class="nav navbar-nav navbar-right">
					<pre>Logged as <strong><%= userdata.getUsername() %></strong></pre>
					<a href="Logout.jsp">Logout</a>
				</div>
			</div>
		</nav>
		
		<% 
			String date = session.getAttribute("date").toString();
		%>
		<h1>News more recent than <%= date %></h1>
		
		<%  
        	NewsBeanRemote newsbean = (NewsBeanRemote) session.getAttribute("newsBean");
	        
	        List<String> regioes = new ArrayList<String>();
	        List<News> newsDate = newsbean.newsMoreRecentThan(date);
	        
	        for(int i=0; i<newsDate.size();i++){
	        	if(!regioes.contains(newsDate.get(i).getRegion())){
	        		regioes.add(newsDate.get(i).getRegion());
	        	}
	        }
	        
	        for(int j=0; j<regioes.size(); j++){
	        	List<News> newsDateRegion = newsbean.newsMoreRecentThan(date, regioes.get(j));
        %>
				<h3><%= regioes.get(j) %></h3>
				<table border="1" align="center">
		            <tr>
		                <th>Title</th>
		                <th>url</th>
		            </tr>
		            <%  	
		            	for(int i=0; i<newsDateRegion.size(); i++){ 
		            %>
		            <tr>
		                <td> <%= newsDateRegion.get(i).getTitle() %> </td>
		                <td align="center"> <%= newsDateRegion.get(i).getUrl() %> </td>
		            </tr>
		            <%
		                }
		            %>
		        </table>
		        <% } %>
        
	</body>
</html>