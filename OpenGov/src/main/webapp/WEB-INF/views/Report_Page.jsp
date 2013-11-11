<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="layout.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Report a Stockout</title>

	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">	
 		
 		<link href="../resources/css/bootstrap-stock.min.css" rel="stylesheet" media="screen"/>
 		<link href="../resources/css/datepicker.css" rel="stylesheet"  type="text/css"/>
 		<link href="../resources/css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet"  type="text/css"/>
 		<script src="../resources/js/jquery-1.10.2.min.js"></script>
    	<script src="../resources/js/bootstrap.min.js"></script>
		<script src="../resources/js/bootstrap-datepicker.js"></script>
		<script src="../resources/js/jquery-ui-1.10.3.custom.min.js"></script>
		
	
	<script language="javascript">  
		function enableElement(theId) { 
             document.getElementById(theId.id).style.visibility = "visible";
           }
		function disableElement(theId){
			document.getElementById(theId.id).style.visibility = "hidden";	
		}
		$( document ).ready(function() {
		    $('#datepicker').datepicker();
		    
		   $('#addMedicine').click(function() {
		    	var medicine = $('#medicineCombobox').val();
		    	$('<option>',{ 
            	}).text(medicine).attr('value', medicine).appendTo('#medicineList');
		    	
		    });
		    
		    $('#removeMedicine').click(function() {
		    	$('#medicineList option:selected').remove();
		    });
		});
		
		function enableAllItems(){
			$("#medicineList option").each(function(i){
		        $(this).attr('selected', 'selected');
		    });
		}
	
       </script>
       
       
       
       
       
       <style type="text/css">
       	.row-padding{
       		padding-top:20px;
       	}
       	</style>

</head>
<body>
	<div class="container">
	<%@ include file="Security.jsp" %>
	<div class="panel panel-primary">
		<div class="panel panel-heading"><h4>Report a Stock-out<h4></div>
			<div class="panel panel-body">
			
				<form:form action="processform" method="POST"  modelAttribute="publicStockoutReport" class="role-horizontal" role="form">

				
					<c:if test="${message != null}">
					<div class="row form-group">
						<div class="alert alert-success col-lg-9 center-block">
						Stock-out/s of "${Medicine}" have successfully been reported by "${Name}"
						</div>
						</div>
					</c:if> 
					       
					<div class="row form-group">
					    <label class="control-label col-lg-2" for="inputName">* Name 
					    </label>
    					<div class="col-lg-6">	
    						<form:input type="text" class="form-control input-sm" id="inputName" 
    							placeholder="Enter Name" path="Name"></form:input>
						</div>
						<font color="red"><form:errors path="Name" cssclass="error"></form:errors></font>
						</br>
					</div>
					
					<div class="row row-padding form-group">
					 <label class="control-label col-lg-2" for="select">* Designation</label>
     					<div class="col-lg-4">
     					<form:select path="Designation" id="select" class="form-control input-sm">
       						<form:option value="Activist" onclick='disableElement(inputDesignation)'>Activist</form:option>
       						<form:option value="Nurse" onclick='disableElement(inputDesignation)'>Nurse</form:option>
     						<form:option value="Healthcare Worker" onclick='disableElement(inputDesignation)'>Healthcare Worker</form:option>
     						<form:option value="DOH official" onclick='disableElement(inputDesignation)'>DOH official</form:option>
     						<form:option value="Other" onclick='enableElement(inputDesignation)'>Other</form:option>
   						</form:select>
     					</div>
     				<div class="col-lg-5">	
      				<form:input path="Designation" type="text" class="form-control input-sm" 
      					id="inputDesignation" placeholder="Enter Designation" style="visibility:hidden"></form:input>
      				</div>
      				</div>
      				
      				<div class="row row-padding form-group">
						<label class="col-lg-2" for="datepicker">Date of Occurrence</label>
						<div class="col-lg-4 input-group input-append date" id="datepicker" data-date="${date}" data-date-format="dd/mm/yyyy">
    						<span class="input-group-addon add-on"><i class="glyhicon glyphicon-calendar"></i></span>
    						<form:input class="form-control span2" type="text" value="${date}" readonly="true" path="dateOfOccurence"></form:input>
    						<font color="red"><form:errors path="dateOfOccurence" cssclass="error"></form:errors></font>
    					</div>
    				</div>
	
    				<div class="row row-padding form-group">
						<label class="col-lg-2" for="select">Province</label>
      					<div class="col-lg-4">
      					<select id="provinceSelect" class="form-control input-sm">
      					<option>Select Province..</option>
        				<c:forEach var="province"  items="${provinces}" >
        					<option value="${province}">${province}</option>
						</c:forEach>
      					</select>
      					</div>
    				</div>
    				
    				<div class="row row-padding form-group">
						 <label class="col-lg-2" for="tags">Name of Facility</label>
      					<div class="ui-widget col-lg-4">
						<form:input path="facilityName" id="tags" class="form-control input-sm" />
					</div>
      					<font color="red"><form:errors path="facilityName" cssclass="error"></form:errors></font>
						</br>
    				</div>	
    				
    				
    				
    				
    				<div class="row row-padding form-group">
					<label class="col-lg-2" for="inputPhone">Cellphone Number</label>
      				<div class="col-lg-4">
      					<form:input path="cellNumber" type="text" class="form-control input-sm" id="inputName" placeholder=""></form:input>
    				</div>
    				<font color="red"><form:errors path="cellNumber" cssclass="error"></form:errors></font>
						</br>
    				</div>
    				
    				<div class="row row-padding form-group">
						<label class="col-lg-2" for="inputPhone">Email Address</label>
      					<div class="col-lg-4">
      					<form:input path="emailAddress" type="email" class="form-control input-sm" id="inputName" placeholder=""></form:input>
    					</div>
    					<font color="red"><form:errors path="emailAddress" cssclass="error"></form:errors></font>
						</br>
    				</div>
				
					<div class="row row-padding form-group">
						<label class="col-lg-2" for="inputPhone">Reason/s for ocurrence</label>
      					<div class="col-lg-4">
      					<form:textarea path="reasonForOccurrence" class="form-control" rows="3" placeholder="Enter details here.."></form:textarea>
    					</div>
    				</div>
    				
			<div class="panel-group">
 			<c:set var="count" value="0" scope="page"/>
 				<div class="row row-padding">
 				<div class="col-lg-9">
 				<div class="panel panel-default">
 				
   				<div class="panel-heading">
      			<h4 class="panel-title clearfix">Select Medicines To Report</h4>	
   				</div>
     				<div class="panel-body">
     				<div class="form-group row row-padding">
     				<div class="col-lg-3">
						 <label for="medicineClassSelect">Select Medicine Category</label>
      					<select id="medicineClassSelect" class="form-control input-sm">
      					<c:set var="count" value="0" scope="page"/>
        				<c:forEach var="category"  items="${medicineCategories}">
        					<option value="${count}">${category.uid}</option>
        					<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
      					</select>
      					</div>
      					
      				<div class="col-lg-5">
      				<label for="medicineCombobox">Select Medicine</label>
      					<div id="medicineDiv">
      					<select id="medicineCombobox" class="form-control input-sm">
      					<c:forEach var="medicine" items="${medicineCategories[0].medicines}">
      						<c:forEach var="product" items="${medicine.products}">
							<option value="${product.name} ${product.description}">${product.name} ${product.description}</option>
							</c:forEach>
							</c:forEach>
      					</select>
      					</div>
      				</div>
      				
      				<div class="col-lg-2 clearfix">
      					<button id="addMedicine" style="margin-top:15px;" type="button" class="btn btn-default">
   				 		<span class="glyphicon glyphicon-plus"></span>
   				 			Add To List
						</button>
      				</div>
    				</div>
   					
   					
   					<div class="form-group row row-padding">
   					<div class="col-lg-8">
   						<label for="medicineList">List of Medicines</label>
   							<form:select id="medicineList" multiple="multiple" class="form-control" path="selectedMedicines">
							<c:forEach var="med" items="${medicineSelection}">
							<option value="${med}">${med}</option>
							</c:forEach>
							</form:select>
						<font color="red"><form:errors path="selectedMedicines" cssclass="error"></form:errors></font>
						</br>
   					</div>
   					
   					<div class="col-lg-2">
   						<button id="removeMedicine" style="margin-top:15px;" type="button" class="btn btn-default">
   				 		<span class="glyphicon glyphicon-minus"></span>
   				 			Remove From List
						</button>
   					</div>
   					</div>
   					
   					<div class="form-group row row-padding">
   					<div class="col-lg-8">
   						<textarea class="form-control" rows="2" placeholder="Special details/requirements"></textarea>
   					</div>
   					</div>
   				</div>
   				</div>
   				</div>
   				</div>
   			</div>			
    		
    		
    		
    		<div class="container row-padding">	
    		<button onclick="enableAllItems()" type="submit" value="Submit Report" class="btn btn-success btn-lg">
    		Submit Report
    		<span class="glyphicon glyphicon-saved"></span>
    		</button>
    		</div>
				</form:form>
				
			</div>
	</div>
	</div>
	<script src="../resources/js/reportHandler.js"></script> 
</body>
</html>