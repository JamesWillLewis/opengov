<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="layout.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>Contacts</title>
	 	
	 	<link href="../resources/css/bootstrap-stock.min.css" rel="stylesheet" media="screen"/>
 		<script src="../resources/js/jquery-1.10.2.min.js"></script>
    	<script src="../resources/js/bootstrap.min.js"></script>
    	
</head>

<body>
	<div class="container">
	<%@ include file="Security.jsp" %>
	<h3>Contact Information for Facilites</h3>
	<div style="height:1000px; overflow:auto;">
		<table class="table table-bordered table-striped table-condensed col-lg-12">
		
  			  		<tr>
        				<th>Province</th>
        				<th>District</th>
        				<th>Town</th>
        				<th>Facility Name</th>
        				<th>Official Name</th>
        				<th>Contact Number</th>
        				<th>Email address</th>
    		</tr>
    		<c:forEach var="facility"  items="${facilities}" >
    		<tr>
        	<td>${facility.province}</td>
        	<td>${facility.district}</td>
        	<td>${facility.town}</td>
        	<td>${facility.localName} ${facility.facilityType.readable}</td>
        	<td>${facility.officialDOHName} ${facility.facilityType.readable}</td>
        	<td>${facility.contactNumber}</td>
        	<td>${facility.emailAddress}</td>
    		</tr>
</c:forEach>
	</table>
	</div>
	</div>
</body>

</html>