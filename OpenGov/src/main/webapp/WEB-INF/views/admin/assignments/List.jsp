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
<title>Stockouts</title>
</head>
<body>

	<table>
		<tr>
			<th>UID</th>
			<th>Name</th>
			<th>Description</th>
			<th>Medicine</th>
			<th>Supplier</th>
			<th>Dosage</th>
			<th>Volume</th>
			<th>Price w/VAT</th>
		</tr>

		<c:forEach items="${results}" var="res">
			<tr>
				<td>${res.uid}</td>
				<td>${res.name}</td>
				<td>${res.description}</td>
				<td>${res.medicine.name}</td>
				<td>N/A</td>
				<td>N/A</td>
				<td>${res.volume}</td>
				<td>${res.priceInclVAT}</td>
			
				<td><a href="<c:url value="/sows/admin/products/${res.uid}"/>">Edit</a></td>
				<td><a
					href="<c:url value="/sows/admin/products/${res.uid}/delete"/>">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

	<%--For displaying Previous link except for the 1st page --%>
	<c:if test="${currentPage != 1}">
		<td><a
			href="<c:url value="/sows/admin/products?page=${currentPage - 1}"/>">Prev</a></td>
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
							href="<c:url value="/sows/admin/products?page=${i}"/>">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>

	<%--For displaying Next link --%>
	<c:if test="${currentPage lt noOfPages}">
		<td><a
			href="<c:url value="/sows/admin/products?page=${currentPage + 1}"/>">Next</a></td>
	</c:if>

	<br>
	<br>
	<a href="<c:url value="/sows/admin/products/new"/>">Create new
		Stockout</a>


</body>
</html>