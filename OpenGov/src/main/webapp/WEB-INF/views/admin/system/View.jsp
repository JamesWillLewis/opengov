<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>System Settings</title>
</head>
<body>

	<h1>System settings:</h1>
	
	<form:form action="system/updateSettings" method="POST" modelAttribute="params">
		<label>Send periodic notifications: </label>
		<form:select path="period" >
			<form:options items="${allPeriods}" />
		</form:select>
		<br>
		<label>Enable USSD service: </label>
		<form:checkbox path="enableUSSDService"/>
		<br>
		
		<form:button type="submit"> Update </form:button>
	</form:form> 

	<h1>Users with administrator permissions:</h1>
	<table class="table table-bordered">

		<tr>
			<th>User-name</th>
		</tr>

		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.username}</td>
			</tr>
		</c:forEach>

	</table>

	<h1>Notification mailing list:</h1>
	<table class="table table-bordered">

		<tr>
			<th>Name</th>
			<th>Email Address</th>
		</tr>

		<c:forEach items="${mailingEntries}" var="mailingEntry">
			<tr>
				<td>${mailingEntry.name}</td>
				<td>${mailingEntry.address}</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>