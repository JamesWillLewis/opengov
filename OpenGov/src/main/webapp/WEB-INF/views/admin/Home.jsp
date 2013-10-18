<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:set var="contextPath" scope="request" value="${pageContext.request.contextPath}" />

<html>
<head>

		<meta name="viewport" content="width=device-width, initial-scale=1.0">	
 		
 		<link href="../resources/css/bootstrap-stock.min.css" rel="stylesheet" type="text/css"/>
 		
		<script src="../resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../resources/js/jquery-1.10.2.min.js"></script>

<title>Admin Home Page</title>
</head>
<%@ include file="../layout.jsp" %>

<body>
<div class="container">
	<div class="panel panel-default">
    	<div class="panel panel-heading"><h3>Administrator Home</h3></div>
        <div class="panel panel-body">
     <div class="row">
     <div class="col-lg-3">
     <%@ include file="Admin Menu.jsp" %>  
</div>
</div>
</div>
</div>
</div>
</body>

</html>