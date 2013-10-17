<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Facilities</title>
</head>
<body>

	<table>
		<tr>
			<th>UID</th>
			<th>Supply Depot</th>
			<th>Official Name</th>
			<th>Local Name (Alias)</th>
			<th>Town</th>
			<th>District</th>
			<th>Province</th>
			<th>Type</th>
			<th>Latitude</th>
			<th>Longitude</th>
			<th>Contact No.</th>
			<th>Email</th>
		</tr>

		<c:forEach items="${results}" var="res">
			<tr>
				<td>${res.uid}</td>
				<td>Unspecified</td>
				<td>${res.officialDOHName}</td>
				<td>${res.localName}</td>
				<td>${res.town}</td>
				<td>${res.district}</td>
				<td>${res.province}</td>
				<td>${res.facilityType.readable}</td>
				<td>${res.latitudeDecimalDegress}</td>
				<td>${res.longitudeDecimalDegrees}</td>
				<td>${res.contactNumber}</td>
				<td>${res.emailAddress}</td>
			
				<td><a href="<c:url value="/sows/admin/facilities/${res.uid}"/>">Edit</a></td>
				<td><a
					href="<c:url value="/sows/admin/facilities/${res.uid}/delete"/>">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

	<%--For displaying Previous link except for the 1st page --%>
	<c:if test="${currentPage != 1}">
		<td><a
			href="<c:url value="/sows/admin/facilities?page=${currentPage - 1}"/>">Prev</a></td>
	</c:if>

	<%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<td>${i}</td>
					</c:when>
					<c:otherwise>
						<td><a
							href="<c:url value="/sows/admin/facilities?page=${i}"/>">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>

	<%--For displaying Next link --%>
	<c:if test="${currentPage lt noOfPages}">
		<td><a
			href="<c:url value="/sows/admin/facilities?page=${currentPage + 1}"/>">Next</a></td>
	</c:if>

	<br>
	<br>
	<a href="<c:url value="/sows/admin/facilities/new"/>">Add new
		Facility</a>


</body>
</html>