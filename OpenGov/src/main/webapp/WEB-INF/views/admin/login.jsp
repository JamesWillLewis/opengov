<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

	<div class="login">
		<h2>Log In</h2>
		<c:if test="${param.login_error == 1}">
			<div class="error">Login Failed: Invalid username or password</div>
		</c:if>
		<form id="signin" action="${pageContext.request.contextPath}/j_spring_security_check"
			method="POST">
			<label>UserName</label> <input type="text" name="j_username"
				tabindex="1" /> <label>Password</label> <input type="password"
				name="j_password" tabindex="2" />
			<button type="submit">Login</button>
		</form>
	</div>

</body>
</html>