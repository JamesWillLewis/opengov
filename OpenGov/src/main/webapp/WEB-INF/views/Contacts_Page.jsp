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
	 	<link href="../resources/css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet"  type="text/css"/>
 		<script src="../resources/js/jquery-1.10.2.min.js"></script>
    	<script src="../resources/js/bootstrap.min.js"></script>
    	<script src="../resources/js/jquery-ui-1.10.3.custom.min.js"></script>
    	
    	
</head>

<body>
	<div class="container">
	<%@ include file="Security.jsp" %>
	<h3>Contact Information for Facilites</h3>
	<br>
	<div class="row row-padding form-group">
						<label class="col-lg-1" for="select">Province</label>
      					<div class="col-lg-2">
      					<select id="provinceSelect" class="form-control input-sm">
      					<option value="all">Select Province..</option>
        				<c:forEach var="province"  items="${provinces}" >
        					<option value="${province}">${province}</option>
						</c:forEach>
      					</select>
      					</div>

    
						 <label class="col-lg-1" for="tags">Name of Facility</label>
      					<div class="ui-widget col-lg-4">
						<input id="tags" class="form-control input-sm" />
					</div>
					
					<button id="search" class="btn btn-success col-lg-2">Find Facility
    				<span class="glyphicon glyphicon-search"></span>
    				</button>
					<script>
    				$('#search').click(function(){
    			
    				window.location.href = "loadcontacts?facilityName=" + $('#tags').val() +
    						"&province=" + $('#provinceSelect').val();
    				return false;
    			
    		});	
    	</script>
    				</div>	
    				<br>
    
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
<script src="../resources/js/reportHandler.js"></script>
</html>