<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
		<h4><security:authorize access="isAuthenticated()">
                        Logged in as user <b><security:authentication
				property="principal.username" /></b>,
                        Click to <a
			href="<c:url value='/j_spring_security_logout' />" class="sign_ic">Logout</a>
	</security:authorize></h4>