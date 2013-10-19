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
		
<title>Staff members</title>
</head>
<%@ include file="../../layout.jsp" %>
<body>
	<div class="container">
	
<%@ include file="../../Security.jsp" %>
	
	<div class="panel panel-default">
    	<div class="panel panel-heading"><h3>Administrator Staff Members View</h3>
    	</div>
        <div class="panel panel-body">
     <div class="row">
     <div class="col-lg-3">
    	 <%@ include file="../Admin Menu.jsp" %>  
	</div>
	<div class="col-lg-8">
	<div class="panel panel-default">
    	<div class="panel panel-heading"><<h3>List of Staff Members</h3></div>
        <div class="panel panel-body">
        <form action="<c:url value="/sows/admin/staffmembers/new"/>" >
    		<button type="submit" class="btn btn-primary">
    		<span class="glyphicon glyphicon-plus"></span>
    		Add New Staff Member</button></form>
	
	<table class="table table-bordered">
	 
		<tr>
			<th>UID</th>
			<th>Staff Code</th>
			<th>Name</th>
			<th>Surname</th>
		</tr>

		<c:forEach items="${staffmembers}" var="res">
			<tr>
				<td>${res.uid}</td>
				<td>${res.staffCode}</td>
				<td>${res.name}</td>
				<td>${res.surname}</td>
				<td><a href="<c:url value="/sows/admin/staffmembers/${res.uid}"/>">Edit    <span class="glyphicon glyphicon-edit"></span></a></td>
				<td><a onclick="return confirm('Are you sure you wish to delete this staffmember?')"
					href="<c:url value="/sows/admin/staffmembers/${res.uid}/delete"/>">Delete    <span class="glyphicon glyphicon-remove"></span></a></td>
			</tr>
		</c:forEach>
		
	</table>
	<div class="row">
	

	<%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <div class="col-lg-8">
	<ul class="pagination">
	<%--For displaying Previous link except for the 1st page --%>
	<c:if test="${currentPage != 1}">
		<li><a
			href="<c:url value="/sows/admin/staffmembers?page=${currentPage - 1}"/>">&laquo;</a></li>
	</c:if>
	
			<c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<li class="active"><a href="#">${i}<span class="sr-only">(current)</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="<c:url value="/sows/admin/staffmembers?page=${i}"/>">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<%--For displaying Next link --%>
		<c:if test="${currentPage lt noOfPages}">
		<li><a
			href="<c:url value="/sows/admin/staffmembers?page=${currentPage + 1}"/>">&raquo;</a></li>
		</c:if>
	</ul>
	</div>
	</div>
	
	</div>
	
</div>


</div>
</div>
</div>
</div>

</div>
</body>
</html>

