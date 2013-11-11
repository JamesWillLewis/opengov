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
 		<link href="../../resources/css/bootstrap-stock.min.css" rel="stylesheet" type="text/css"/>		
		<script src="../../resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../../resources/js/jquery-1.10.2.min.js"></script>
		
<title>System Settings</title>
</head>
<%@ include file="../../layout.jsp" %>
<body>
	<div class="container">
	
<%@ include file="../../Security.jsp" %>
	
	<div class="panel panel-default">
    	<div class="panel panel-heading"><h3>Administrator Settings</h3>
    	</div>
        <div class="panel panel-body">
     <div class="row">
     <div class="col-lg-3">
    	 <%@ include file="../Admin Menu.jsp" %>  
	</div>
	<div class="col-lg-8">
	<div class="panel panel-default">
    	<div class="panel panel-heading"><h4>System Settings</h4></div>
        <div class="panel panel-body">
	
	<form:form action="system/updateSettings" method="POST" modelAttribute="params"
	 onsubmit="return confirm('Are you sure you want to Update Settings?')">
	
	<div class="form-group row row-padding">
		<label class="col-lg-4 control-label">Send periodic notifications: </label>
   					<div class="col-lg-4">
   						<form:select path="period" class="form-control input-sm">
						<form:options items="${allPeriods}" />
					</form:select>
   					</div>
   					
   					<!--  <c:if test="${ussd_message != null}">
						<div class="alert alert-success col-lg-8">
						"${ussd_message}"
						</div>
						<div class="alert alert-success col-lg-8">
						"${notification_message}"
						</div>
					</c:if>--> 
   					</div>
		
		
		<div class="form-group row row-padding">
		<div class="col-lg-4">
    		<label class="checkbox control-label">
    		  Enable USSD service 
      			<form:checkbox path="enableUSSDService"/>
    		</label>
    		</div>
		</div>
		
		<form:button type="submit" class="btn btn-success"> Update 
		<span class="glyphicon glyphicon-refresh"></span>
		</form:button>
		
	</form:form> 
	</div>
	</div>
	
	<div class="panel panel-default">
    	<div class="panel panel-heading"><h4>Users with administrator permissions</h4></div>
        <div class="panel panel-body">
        <form action="<c:url value="/sows/admin/system/newAdmin"/>" >
    		<button type="submit" class="btn btn-primary">
    		<span class="glyphicon glyphicon-plus"></span>
    		Add New Admin</button></form>
    		
	<table class="table table-bordered">

		<tr>
			<th>User-name</th>
		</tr>

		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.username}</td>
				<td><a onclick="return confirm('Are you sure you wish to delete this admin user?')"
					href="<c:url value="/sows/admin/system/${user.username}/deleteAdmin"/>">Delete    <span class="glyphicon glyphicon-remove"></span></a></td>
			
			</tr>
		</c:forEach>

	</table>
</div>
</div>

	<div class="panel panel-default">
    	<div class="panel panel-heading"><h4>Notification mailing list</h4></div>
        <div class="panel panel-body">
<form action="<c:url value="/sows/admin/system/newListMember"/>" >
    		<button type="submit" class="btn btn-primary">
    		<span class="glyphicon glyphicon-plus"></span>
    		Add New Mailing List Member</button></form>


	<table class="table table-bordered">

		<tr>
			<th>Name</th>
			<th>Email Address</th>
			
		</tr>

		<c:forEach items="${mailingEntries}" var="mailingEntry">
			<tr>
				<td>${mailingEntry.name}</td>
				<td>${mailingEntry.address}</td>
				<td><a onclick="return confirm('Are you sure you wish to delete this member?')"
					href="<c:url value="/sows/admin/system/${mailingEntry.name}/deleteMember"/>">Delete    <span class="glyphicon glyphicon-remove"></span></a></td>
			
			</tr>
		</c:forEach>

	</table>	
	
	</div>
	
</div>


</div>
</div>
</div>
</div>

</div>
</body>
</html>