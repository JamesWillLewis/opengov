<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="layout.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report a Stockout</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">	
 		
 		<link href="resources/css/bootstrap-stock.min.css" rel="stylesheet" media="screen"/>
 		 		<link href="resources/css/datepicker.css" rel="stylesheet"  type="text/css"/>
 				<script src="resources/js/jquery-1.10.2.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
		<script src="resources/js/bootstrap-datepicker.js"></script>
	
	<script language="javascript">
           
		function enableElement(theId) { 
             document.getElementById(theId.id).style.visibility = "visible";
           }
		function disableElement(theId){
			document.getElementById(theId.id).style.visibility = "hidden";	
		}
		
		$( document ).ready(function() {
		    $('#datepicker').datepicker();
		});
		
       </script>

</head>
<body>
	<div class="container">
	<div class="panel panel-primary">
		<div class="panel panel-heading">Report a Stock-out</div>
			<div class="panel panel-body">
				<form class="role-horizontal" role="form">
					<div class="form-group">
					    <label for="inputName">* Name</label>
    					<input type="text" class="form-control input-sm" id="inputName" placeholder="Enter Name">
					</div>
					<div class="form-group">
						 <label for="select">* Designation</label>
      					<select id="select" class="form-control input-sm">
        				<option onclick=disableElement(inputDesignation)>Activist</option>
        				<option onclick=disableElement(inputDesignation)>Nurse</option>
        				<option onclick=disableElement(inputDesignation)>Healthcare Worker</option>
        				<option onclick=disableElement(inputDesignation)>DOH official</option>
        				<option onclick=enableElement(inputDesignation)>Other</option>
      					</select>
      					<input type="text" class="form-control input-sm" 
      					id="inputDesignation" placeholder="Enter Designation" style="visibility:hidden">
      				</div>
						  	<label for="datepicker">Date of First Occurrence</label>
						     <div class="input-group input-append date" id="datepicker" data-date="05-10-2013" data-date-format="dd-mm-yyyy">
    							<span class="input-group-addon add-on"><i class="glyhicon glyphicon-calendar"></i></span>
    							<input class="form-control span2" type="text" value="05-10-2013" readonly>
    							</div>
    	
	
    					<div class="form-group">
						<label for="select">Province</label>
      					<select id="select" class="form-control input-sm">
        				<option>Gauteng</option>
        				<option>Western Cape</option>
        				<option>etc</option>
      					</select>
    				</div>
    				<div class="form-group">
						 <label for="select">Name of Facility</label>
      					<select id="select" class="form-control input-sm">
        				<option>Facility 1</option>
        				<option>etc</option>
      					</select>
    				</div>
    				<div class="form-group">
						 <label for="inputPhone">Cellphone Number</label>
      					<input type="text" class="form-control input-sm" id="inputName" placeholder="">
    				</div>
    				<div class="form-group">
						 <label for="inputPhone">Email Address</label>
      					<input type="text" class="form-control input-sm" id="inputName" placeholder="">
    				</div>
				</form>
			</div>
	</div>
	</div>
</body>
</html>