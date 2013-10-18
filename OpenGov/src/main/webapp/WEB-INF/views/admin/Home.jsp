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
<title>Admin Home Page</title>
</head>
<body>

	<security:authorize access="isAuthenticated()">
                        Logged in as user <b><security:authentication
				property="principal.username" /></b>,
                        Click to <a
			href="<c:url value='/j_spring_security_logout' />" class="sign_ic">Logout</a>
	</security:authorize>

	<a href="<c:url value="/sows/admin/stockouts"/>">Stock-outs</a>
	<br>
	<a href="<c:url value="/sows/admin/facilities"/>">Facilities</a>
	<br>
	<a href="<c:url value="/sows/admin/products"/>">Products</a>
	<br>
	<a href="<c:url value="/sows/admin/medicines"/>">Medicines</a>
	<br>
	<a href="<c:url value="/sows/admin/medicineclasses"/>">Medicine
		Classes</a>
	<br>
	<a href="<c:url value="/sows/admin/issues"/>">Issues</a>
	<br>
	<a href="<c:url value="/sows/admin/assignments"/>">Assignments</a>
	<br>
	<a href="<c:url value="/sows/admin/staffmembers"/>">Staff Members</a>

</body>
</html>