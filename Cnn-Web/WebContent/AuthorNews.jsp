<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.News"%>
<%@page import="common.User"%>
<%@page import="ejbs.NewsBeanRemote"%>
<%@page import="javax.naming.InitialContext" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>CNN News - Author News</title>
		
		<!-- BOOTSTRAP -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
		<link href="bootstrap/css/font-awesome.min.css" rel="stylesheet" media="screen"/>
		<link href="bootstrap/css/style.css" rel="stylesheet" media="screen"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script language="javascript" type="text/javascript" src="bootstrap/js/dropdown.js"></script>
		<script language="javascript" type="text/javascript" src="bootstrap/js/transition.js"></script>
		<script language="javascript" type="text/javascript" src="bootstrap/js/collapse.js"></script>
		<script language="javascript" type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
		<script language="javascript" type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	
		<!--  -->
		<script type="text/javascript" language="javascript">
			function confirmar(){
		    	var txt;
		        var r = confirm("Are you sure you want to delete your account?\nPress Ok to delete, Cancel to quit!");
		        if (r == true) {
		            txt = "Ok";
		            document.location.href="DeleteAccountServlet";
		        } else {
		            txt = "Cancel";
		        }
		    }
		</script>
	
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
					<a class="navbar-brand" href="Menu.jsp"><img src="bootstrap/img/cnn_logo.gif"/></a>
				</div>
				<!-- Informa��o do utilizador -->
				<%  
					User userdata = (User) session.getAttribute("user");
		        %>
				<div class="collapse navbar-collapse navbar-menu">
					<div class="nav navbar-nav navbar-right">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#US">US</a></li>
							<li><a href="#AFRICA">AFRICA</a></li>
							<li><a href="#ASIA">ASIA</a></li>
							<li><a href="#EUROPE">EUROPE</a></li>
							<li><a href="#LATINAMERICA">LATIN AMERICA</a></li>
							<li><a href="#MIDDLEEAST">MIDDLE EAST</a></li>
							<li>
								<div class="btn-group">
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
								    	Logged as <strong><%= userdata.getUsername() %></strong> <span class="caret"></span>
								  	</button>
								  	<ul class="dropdown-menu" role="menu">
									    <li><a href="EditProfile.jsp">Edit profile</a></li>
									    <li><a onclick="return confirmar()">Delete account</a></li>
									    <li class="divider"></li>
									    <li><a href="Logout.jsp">Logout</a></li>
								  	</ul>
							  	</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</nav>
		
		
		<%
			String author = session.getAttribute("author").toString();
		%>
		<h1>News from author: <%= author %></h1>
		
		
		<%  
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
	        	pageContext.setAttribute("newsList", newsAuthorRegion);
        %>
				<!-- CONTEUDO -->
        		<!-- Regi�o -->
				<c:set var="regiao" value="<%= regioes.get(j) %>"/>
				<h1 id="<%= regioes.get(j) %>">${regiao}</h1>
				<div class="panel-group" id="accordion">
					<!-- Not�cias -->
				    <c:forEach var="news" items="${newsList}" varStatus="status">
						<div class="panel panel-default">
							<div class="panel-heading">
								<!-- T�tulo da not�cia -->
								<h3 class="panel-title">
								    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse${news.region}${status.index}">
								    	<c:out value="${news.title}"/>
								    </a>
								</h3>
							</div>
							<div id="collapse${news.region}${status.index}" class="panel-collapse collapse">
								<div class="panel-body">
									<!-- Autor(es) -->
									<c:set var="numAuthors" value="${fn:length(news.authors)}"/>
									<c:if test="${numAuthors > 0}">
 							  			by 
								  		<c:choose>
											<c:when test="${numAuthors==1}">
												<b><c:out value="${news.authors[0].name}"/></b>,
											</c:when>
											<c:when test="${numAuthors==2}">
												<b><c:out value="${news.authors[0].name}"/></b> and <b><c:out value="${news.authors[1].name}"/></b>,
											</c:when >
											<c:otherwise>
												<b><c:out value="${news.authors[0].name}"/></b>, <b><c:out value="${news.authors[1].name}"/></b> and <b>c:out value="${news.authors[2].name}"/></b>,
											</c:otherwise>
										</c:choose>
									</c:if>
									<!-- Data -->
 									on 
 									<fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${news.date}" />
									<br><br>
									<!-- Highlights -->
									<ul>
										<c:forEach var="highlight" items="${news.highlights}" varStatus="hlstatus">
							    			<li><c:out value="${highlight}"/></li>
							    		</c:forEach>
								    </ul>
									<!-- Foto -->
							    	<div class="row"> 
									  	<div class="col-xs-5"> 
									  		<c:if test="${news.photoURL != ''}">
								  				<a href="${news.photoURL}" class="thumbnail" target="_blank">
										      		<img src="${news.photoURL}"/>
										    	</a>
									  		</c:if>
								  		</div>
									</div>
									<!-- Video -->
									<div class="video">
										<c:if test="${news.videoURL != ''}">
								  			<a class="btn btn-primary btn-lg" role="button" href="${news.videoURL}" target="_blank"><span class="glyphicon glyphicon-film"></span> Video</a>
								  		</c:if>
									</div>
									<br>
									<!-- Texto da not�cia -->
									<div class="texto">
										<c:out value="${news.text}"/>
									</div>
									<br>
									<!-- Fonte -->
									<div class="source">
										Source: <a href="${news.url}" style="color: #FF0000" target="_blank">CNN</a>
									</div>
								</div>
							</div>
						</div>
						
					</c:forEach>
				</div>
		        <% } %>
        
	</body>
</html>