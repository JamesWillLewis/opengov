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

<title>Admin Home Page</title>
</head>
<%@ include file="../layout.jsp" %>

<body>

<div class="container">
<%@ include file="../Security.jsp" %>

	<div class="panel panel-default">
    	<div class="panel panel-heading"><h3>Administrator Home</h3></div>
        <div class="panel panel-body">
     <div class="row">
     <div class="col-lg-3">
     <%@ include file="Admin Menu.jsp" %>  
	</div>
		<div class="col-lg-9">
		<Table class="table">
			<tr>
			<td>
            <h2>Stock-out Service</h2>
            <p>Check and edit stockouts</p>
            <br><br>
            <p><a class="btn btn-primary btn-sm" href="admin/stockouts">Go to Stock-out Service &raquo;</a></p>
            </td>
            
            
			<td>
            <h2>Facility Service</h2>
            	<p>Check add, edit and delete medical facilities</p>
            	<p><a class="btn btn-primary btn-sm" href="admin/facilities"> Go To Facility Service &raquo;</a></p>
            </td>
           
           
			<td>
            <h2>Product Service</h2>
            <p>Check add, edit and delete listed products</p>
            <br>
            <p>
            <a class="btn btn-primary btn-sm" href="admin/products">Go To Product Service &raquo;</a>
            </p>
            </td>
            
     
			<td>
            <h2>Medicine Service</h2>
            <p>Check add, edit and delete medicines that define many products</p>
            <p>
            <a class="btn btn-primary btn-sm" href="admin/medicines">Go To Medicine Service &raquo;</a>
            </p>
            </td>
		</tr>
			<tr>
			<td>
            <h2>Medicine-Class Service</h2>
            <p>Check, add and Remove Medicine Classes that define a medicine</p>
            <p><a class="btn btn-primary btn-sm" href="admin/medicineclasses">Go to Medicine Class Service &raquo;</a></p>
            </td>
            <td>
            <h2>Issue Service</h2>
            	<p>Check and edit issues assosciated with stock-outs</p>
            	<p><a class="btn btn-primary btn-sm" href="admin/issues"> Go To Issue Service &raquo;</a></p>
            </td>
            <td>
            <h2>Assignment Service</h2>
            <p>Check assignments of staff members to issues. Add and edit assignments</p>
            <p>
            <a class="btn btn-primary btn-sm" href="admin/assignments">Go To Assignment Service &raquo;</a>
            </p>
            </td>
            <td>
            <h2>Staff Member Service</h2>
            <p>Check staff member roster, add new staff members and edit details</p>
            <p>
            <a class="btn btn-primary btn-sm" href="admin/staffmembers">Go To Staff Member Service &raquo;</a>
            </p>
           </td>
           </tr>
           </Table>
		</div>
</div>
</div>
</div>
</div>
</body>

</html>