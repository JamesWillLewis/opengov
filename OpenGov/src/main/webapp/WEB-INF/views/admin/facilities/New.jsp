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
		
<title>Facilities</title>
</head>
<%@ include file="../../layout.jsp" %>
<body>
	<div class="container">
	<%@ include file="../../Security.jsp" %>
	<div class="panel panel-default">
    	<div class="panel panel-heading"><h3>Administrator Add Facilities View</h3></div>
        <div class="panel panel-body">
     <div class="row">
     <div class="col-lg-3">
    	 <%@ include file="../Admin Menu.jsp" %>  
	</div>
	<div class="col-lg-8">
	<div class="panel panel-default">
    	<div class="panel panel-heading"><h3>Add Facility</h3></div>
        <div class="panel panel-body">
	
			<form:form action="add" method="POST" modelAttribute="facilitywrapper"
			onsubmit="return confirm('Are you sure you want to add a new Facility?')">
				
				<div class="row form form-group">
				<label class="control-label col-lg-2" for="facility"> Local Name of Facility: </label>
				<div class=col-lg-6>
				<form:input type="text" class="form-control input-sm" id="inputName" 
    							placeholder="Enter Facility Name" path="localName"></form:input>
		 				 		<font color="red"><form:errors path="localName" cssclass="error"></form:errors></font>
		 		</div>
		 		</div>
		 		
		 			<div class="row form form-group">
				<label class="control-label col-lg-2" for="facility">Official Name of Facility: </label>
				<div class=col-lg-6>
				<form:textarea type="text" class="form-control input-sm" id="inputName" 
    							placeholder="Enter Facility Name" path="officialName"></form:textarea>
		 		<font color="red"><form:errors path="officialName" cssclass="error"></form:errors></font>
		 		
		 		</div>
		 		</div>
		 		
		 		<div class="row form form-group">
				<label class="control-label col-lg-2" for="facility">Enter Co-ordinate Location: </label>
				<div class=col-lg-3>
				<form:input type="text" class="form-control input-sm" id="inputName" 
    							placeholder="Enter latitude" path="latitude"></form:input>
		 		<font color="red"><form:errors path="latitude" cssclass="error"></form:errors></font>
		 		</div>
		 		<div class=col-lg-3>
				<form:input type="text" class="form-control input-sm" id="inputName" 
    							placeholder="Enter latitude" path="longitude"></form:input>
		 		<font color="red"><form:errors path="longitude" cssclass="error"></form:errors></font>
		 		</div>
		 		</div>
		 		
		 			<div class="row form form-group">
				<label class="control-label col-lg-2" for="facility">Contact Number: </label>
				<div class=col-lg-6>
				<form:input type="text" class="form-control input-sm" id="inputName" 
    							placeholder="Enter Number" path="contactNumber"></form:input>
    		<font color="red"><form:errors path="contactNumber" cssclass="error"></form:errors></font>
    							
		 		</div>
		 		</div>
		 		
				<div class="row form form-group">
				<label class="control-label col-lg-2" for="facility">Email: </label>
				<div class=col-lg-6>
				<form:input type="text" class="form-control input-sm" id="inputName" 
    							placeholder="Enter Email Address" path="emailAddress"></form:input>
		 		<font color="red"><form:errors path="emailAddress" cssclass="error"></form:errors></font>

		 		</div>
		 		</div>
				
				<div class="row form form-group row-padding">
				<label class="control-label col-lg-2" for="product"> Select Province: </label>
				<div class="col-lg-6">
					<form:select id="provinceSelect" class="form-control input-sm" path="province" items="${provinces}">	
					<form:option value=" ">Select Province </form:option>
					<form:options items="${provinces}"></form:options>
					</form:select>
					</div>
					<font color="red"><form:errors path="province" cssclass="error"></form:errors></font>
				</div>
				
			<div class="row form form-group row-padding">
				<label class="control-label col-lg-2" for="districts"> Select District: </label>
				<div class="col-lg-6">
					<form:select id="districtSelect" class="form-control input-sm" path="district">
					<c:forEach items="${districts}" var="dist">			
							<form:option value="${dist}"><c:out value="${dist}"/></form:option>
								</c:forEach>
					</form:select>
					</div>
					<font color="red"><form:errors path="district" cssclass="error"></form:errors></font>
				</div>
				
				<div class="row form form-group row-padding">
				<label class="control-label col-lg-2" for="towns"> Select Town: </label>
				<div class="col-lg-6">
					<form:select id="townSelect" class="form-control input-sm" path="town">	
						<c:forEach items="${towns}" var="town">			
							<form:option value="${town}"><c:out value="${town}"/></form:option>
								</c:forEach>
					</form:select>
					</div>
					<font color="red"><form:errors path="town" cssclass="error"></form:errors></font>
				</div>
				
				<div class="row form form-group row-padding">
				<label class="control-label col-lg-2" for="product"> Select Clinic Type: </label>
				<div class="col-lg-6">
					<form:select class="form-control input-sm" path="type">
					<c:forEach var="aType" items="${types}">
        		<form:option value="${aType}"><c:out value="${aType}"/></form:option>
    		</c:forEach>
					</form:select>
					</div>
					<font color="red"><form:errors path="type" cssclass="error"></form:errors></font>
				</div>
				
				
		<div class="row form container row-padding">	
		<button class="btn btn-success btn-lg text-center" type="submit" value="Save">Add Facility<span class="glyphicon glyphicon-saved"></span></button>
		</div>
	</form:form>
	

	</div>
	
</div>


</div>
</div>
</div>
</div>

</div>
	<script type="text/javascript" src="../../../resources/js/FacilityFormHandler.js"></script>
</body>

</html>


