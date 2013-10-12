<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="layout.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
		<script src="resources/js/bootstrap-combobox.js"></script>
	
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
		$(document).ready(function(){
			    $('#facilityCombobox').combobox();
			  });
		
       </script>
       
       
       
       
       
       <style type="text/css">
       	.row-padding{
       		padding-top:20px;
       	}
       	</style>

</head>
<body>
	<div class="container">
	<div class="panel panel-primary">
		<div class="panel panel-heading"><h4>Report a Stock-out<h4></div>
			<div class="panel panel-body">
			
				<form class="role-horizontal" role="form">
					
					<div class="row form-group">
					    <label class="control-label col-lg-2" for="inputName">* Name</label>
    					<div class="col-lg-6">
    						<input type="text" class="form-control input-sm" id="inputName" placeholder="Enter Name">
						</div>
					</div>
					
					<div class="row row-padding form-group">
					 <label class="control-label col-lg-2" for="select">* Designation</label>
     					<div class="col-lg-4">
     					<select id="select" class="form-control input-sm">
       						<option onclick=disableElement(inputDesignation)>Activist</option>
       						<option onclick=disableElement(inputDesignation)>Nurse</option>
     						<option onclick=disableElement(inputDesignation)>Healthcare Worker</option>
     						<option onclick=disableElement(inputDesignation)>DOH official</option>
     						<option onclick=enableElement(inputDesignation)>Other</option>
   						</select>
     					</div>
     				<div class="col-lg-5">	
      				<input type="text" class="form-control input-sm" 
      					id="inputDesignation" placeholder="Enter Designation" style="visibility:hidden">
      				</div>
      				</div>
      				
      				<div class="row row-padding form-group">
						<label class="col-lg-2" for="datepicker">Date of Occurrence</label>
						<div class="col-lg-4 input-group input-append date" id="datepicker" data-date="${date}" data-date-format="dd-mm-yyyy">
    						<span class="input-group-addon add-on"><i class="glyhicon glyphicon-calendar"></i></span>
    						<input class="form-control span2" type="text" value="${date}" readonly>
    					</div>
    				</div>
	
    				<div class="row row-padding form-group">
						<label class="col-lg-2" for="select">Province</label>
      					<div class="col-lg-4">
      					<select id="provinceSelect" class="form-control input-sm">
      					<option value="Western Cape">select</option>
        				<c:forEach var="province"  items="${provinces}" >
        					<option value="${province}">${province}</option>
						</c:forEach>
      					</select>
      					</div>
    				</div>
    				
    				<div class="row row-padding form-group">
						 <label class="col-lg-2" for="combobox">Name of Facility</label>
      					<div id="provinceDiv" class="col-lg-4">
      					<select id="facilityCombobox" class="form-control input-sm">
      					<c:forEach var="province"  items="${provinces}" >
        					<option value="${province}">${province}</option>
						</c:forEach>
      					</select>
      					</div>
    				</div>
    				
    						
    				
    				
    				
    				
    				<div class="row row-padding form-group">
					<label class="col-lg-2" for="inputPhone">Cellphone Number</label>
      				<div class="col-lg-4">
      					<input type="text" class="form-control input-sm" id="inputName" placeholder="">
    				</div>
    				</div>
    				
    				<div class="row row-padding form-group">
						<label class="col-lg-2" for="inputPhone">Email Address</label>
      					<div class="col-lg-4">
      					<input type="email" class="form-control input-sm" id="inputName" placeholder="">
    					</div>
    				</div>
				
					<div class="row row-padding form-group">
						<label class="col-lg-2" for="inputPhone">Reason/s for ocurrence</label>
      					<div class="col-lg-4">
      					<textarea class="form-control" rows="3" placeholder="Enter details here.."></textarea>
    					</div>
    				</div>
    				
			<div class="panel-group">
 			<c:set var="count" value="0" scope="page"/>
 			
 			<c:forEach var="category" items="${medicineCategories}">	
 				<div class="row row-padding">
 				<div class="col-lg-8">
 				<div class="panel panel-default">
 				
   				<div class="panel-heading">
   				<button type="button" class="btn btn-default btn-sm pull-left" data-toggle="collapse" data-target="#collapse${count}">
   				 	<span class="glyphicon glyphicon-plus"></span>
				</button>
     			<h4 class="panel-title clearfix">${category}</h4>	
   				</div>
   				<div id="collapse${count}" class="panel-collapse collapse">
     				<div class="panel-body">
     					 <div class="checkbox">
        				<label>
          					<input type="checkbox"> medicine1
        				</label>
        				</div>
   					</div>
   					
   					<textarea class="form-control pull-right" rows="2" placeholder="Special details/requirements"></textarea>
   					
   				</div>
   				</div>
   				<c:set var="count" value="${count + 1}" scope="page"/>
   				</div>
   				</div>
   </c:forEach>
   			</div>			
    		
    		<div class="container row-padding">	
    		<button type="button" class="btn btn-success btn-lg">
    		Submit Report
    		<span class="glyphicon glyphicon-saved"></span> 
    		</button>	
    		</div>
				</form>
				
			</div>
	</div>
	</div>
	<script src="resources/js/reportHandler.js"></script> 
</body>
</html>