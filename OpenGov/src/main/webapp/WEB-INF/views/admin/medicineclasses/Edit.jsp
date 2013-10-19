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
<title>Insert title here</title>
</head>
<body>
<%@ include file="../../Security.jsp" %>
	<form:form action="update" method="POST" modelAttribute="stockout">

		<label for="facility"> Facility: </label>
		<form:select path="facilityUID" items="${facilities}" itemLabel="officialDOHName" itemValue="uid"/>
		<br> 

		<label for="product"> Product: </label>
		<form:select path="productUID" items="${products}" itemLabel="displayName" itemValue="product.uid"/>
		<br>

		<input type="submit" value="Save" />

	</form:form>


</body>
</html>