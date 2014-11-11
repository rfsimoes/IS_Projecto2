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
	<title>CNN News - All the news</title>
	
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
					    <li><a href="DeleteAccount.jsp">Delete account</a></li>
					    <li class="divider"></li>
					    <li><a href="Logout.jsp">Logout</a></li>
				  	</ul>
				</div>
			</div>
		</nav>
	
		<h1>All the news</h1>
		
		<%  
        	NewsBeanRemote newsbean = (NewsBeanRemote) session.getAttribute("newsBean");
	        
	        List<String> regioes = new ArrayList<String>();
	        List<News> allNews = newsbean.getNews();
	        
	        for(int i=0; i<allNews.size();i++){
	        	if(!regioes.contains(allNews.get(i).getRegion())){
	        		regioes.add(allNews.get(i).getRegion());
	        	}
	        }
	        
	        for(int j=0; j<regioes.size(); j++){
	        	List<News> news = newsbean.newsSortedByDate(regioes.get(j));
        %>
				<h3><%= regioes.get(j) %></h3>
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
		        <% } %>
        
	</body>
</html>