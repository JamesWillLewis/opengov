<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<c:set var="contextPath" scope="request" value="${pageContext.request.contextPath}" />

<html>
<head>

		<meta name="viewport" content="width=device-width, initial-scale=1.0">	
 		
 		<link href="../resources/css/bootstrap-stock.min.css" rel="stylesheet" type="text/css"/>
 		
		<script src="../resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../resources/js/jquery-1.10.2.min.js"></script>

<title>Login Page</title>
</head>
<%@ include file="../layout.jsp" %>

<body>

<div class="container">

	<h4><security:authorize access="isAuthenticated()">
                        Logged in as user <b><security:authentication
				property="principal.username" /></b>,
                        Click to <a
			href="<c:url value='/j_spring_security_logout' />" class="sign_ic">Logout</a>
	</security:authorize></h4>

	<div class="panel panel-default">
    	<div class="panel panel-heading"><h3>Login As Administator</h3></div>
        <div class="panel panel-body">
 
			<div class="login">
		<c:if test="${param.login_error == 1}">
			<div class="error">Login Failed: Invalid username or password</div>
		</c:if>
		<form id="signin" action="${pageContext.request.contextPath}/j_spring_security_check"
			method="POST">
			<div class="row row-padding form-group">
			<div class="col-lg-5">
			<label class="control-label">Enter User Name</label> 
			<input class="form-control" type="text" name="j_username" tabindex="1" />
			</div>
			</div>
			<div class="row row-padding form-group">
			<div class="col-lg-5">
			<label class="control-label">Enter Password</label> 
			<input class="form-control" type="password" name="j_password" tabindex="2" />
			</div>
			</div>
			<button class="btn btn-success btn-lg" type="submit">Login  
			<span class="glyphicon glyphicon-log-in"></span>
			</button>
		</form>
		</div>
</div>
</div>
</div>
</body>

</html>
