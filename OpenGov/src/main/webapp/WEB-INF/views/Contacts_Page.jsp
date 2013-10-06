<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="layout.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>Contacts</title>
	 	
	 	<link href="resources/css/bootstrap-stock.min.css" rel="stylesheet" media="screen"/>
 		<script src="resources/js/jquery-1.10.2.min.js"></script>
    	<script src="resources/js/bootstrap.min.js"></script>
    	
</head>

<body>
	<div class="container">
	<h3>Contact Information for Facilites</h3>
		<table class="table table-bordered">
		
  			  		<tr>
        				<th>Province</th>
        				<th>Town/District</th>
        				<th>Facility Name</th>
        				<th>Contact Number</th>
    		</tr>
    		<!-- 	<c:forEach var="contact"  items="${contactsList}" >
    		<tr>
        	<td>${contact.province}</td>
        	<td>${contact.town}</td>
        	<td>${contact.facility}</td>
        	<td>${contact.contactNumber}</td>
    		</tr>
</c:forEach> -->
	</table>
	</div>
</body>

</html>