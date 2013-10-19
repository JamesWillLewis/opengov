<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">	
 		<link href="../../../resources/css/bootstrap-stock.min.css" rel="stylesheet" type="text/css"/>		
		<script src="../../../resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../../../resources/js/jquery-1.10.2.min.js"></script>
		
<title>Stockouts</title>
</head>
<%@ include file="../../layout.jsp" %>
<body>
	<div class="container">
	
	<%@ include file="../../Security.jsp" %>
	
	<div class="panel panel-default">
    	<div class="panel panel-heading"><h3>Administrator Stock-outs View</h3></div>
        <div class="panel panel-body">
     <div class="row">
     <div class="col-lg-3">
    	 <%@ include file="../Admin Menu.jsp" %>  
	</div>
	<div class="col-lg-8">
	<div class="panel panel-default">
    	<div class="panel panel-heading"><h3>Edit Stockouts</h3></div>
        <div class="panel panel-body">
	
			<form:form action="${uid}/update" method="POST" modelAttribute="stockout"
			onsubmit="return confirm('Are you sure you want to add a new stockout?')">
				
				<div class="row form form-group">
				<label class="control-label col-lg-2" for="facility"> Facility: </label>
				<div class=col-lg-6>
				<form:select class="form-control input-sm" path="facilityUID" items="${facilities}" itemLabel="officialDOHName" itemValue="uid"/>
		 		</div>
		 		</div>
				
				<div class="row form form-group row-padding">
				<label class="control-label col-lg-2" for="product"> Product: </label>
				<div class="col-lg-6">
					<form:select class="form-control input-sm" path="productUID">
					<c:forEach items="${products}" var="product">			
							<form:option value="${product.uid}"><c:out value="${product.name} ${product.description}"/></form:option>
								</c:forEach>
					</form:select></div>
				</div>
				
		<div class="row form container row-padding">	
		<button class="btn btn-success btn-lg text-center" type="submit" value="Save">Save<span class="glyphicon glyphicon-saved"></span></button>
		</div>
	</form:form>
	
	</div>
	
</div>


</div>
</div>
</div>
</div>

</div>
</body>
</html>


