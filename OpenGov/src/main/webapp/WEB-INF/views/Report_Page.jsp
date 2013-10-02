<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="layout.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report a Stockout</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">	
 		<link href="resources/css/bootstrap-stock.min.css" rel="stylesheet" media="screen"/>

</head>
<body>
	<div class="container">
	<div class="panel panel-primary">
		<div class="panel panel-heading">Report a Stock-out</div>
			<div class="panel panel-body">
				<form class="role-horizontal" role="form">
					<div class="form-group">
					    <label for="inputName">* Name</label>
    					<input type="text" class="form-control input-sm" id="inputName" placeholder="Enter Name">
					</div>
					<div class="form-group">
						 <label for="select">* Designation</label>
      					<select id="select" class="form-control input-sm">
        				<option>Activist</option>
        				<option>Nurse</option>
        				<option>Healthcare Worker</option>
        				<option>DOH official</option>
      					</select>
      				<div class="form-group">
      					<label for="currDate">Date of Report</label>
						<div class="input-append date" id="dp3" data-date="12-02-2012" data-date-format="dd-mm-yyyy">      				</div>
    				</div>
				</form>
			</div>
	</div>
	</div>
</body>
</html>