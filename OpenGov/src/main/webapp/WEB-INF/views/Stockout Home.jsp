<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="layout.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>	
	<head>
	
		<title>Stock out Home Page</title>
	
		<meta name="viewport" content="width=device-width, initial-scale=1.0">	
 		<link href="resources/css/bootstrap-stock.min.css" rel="stylesheet" media="screen"/>
		
	<style type="text/css">
			div#map_container{
				margin-left:auto; 
				margin-right:auto; 
				text-align:center;
				width:97%;
				height:500px;
				
				margin-top:10px;
				margin-bottom:10px;
			}
			.google-map-canvas,
			.google-map-canvas *{ .box-sizing(content-box); }
	</style>
	
<script type="text/javascript" 
     src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
 
<script type="text/javascript"
	src="resources/js/maploader.js"> </script>
	</head>

<body onload="loadMap(-30.145127)">

           <div class="container">
            
            <div class="row" >
            <div class="panel panel-default">
            <div class="panel panel-heading"><h3>Search For:</h3></div>
            <div class="panel panel-body">
            <div class="col-lg-3">
            <div class="panel panel-primary">
            <div class="panel-heading">Filter Results:</div>
  			<div class="panel-body">
				<form class="role-horizontal" role="form">
					<div class="form-group">
						 <label for="select">By Category</label>
      					<select id="select" class="form-control input-sm">
        				<option>Select Category..</option>
        				<option>Recent Stockouts</option>
        				<option>All Stockouts</option>
      					</select>
    				</div>
    				<div class="form-group">
						 <label for="select">By Province</label>
      					<select id="select" class="form-control input-sm">
        				<option>Select Province..</option>
        				<option>Recent Stockouts</option>
        				<option>All Stockouts</option>
      					</select>
    				</div>
    				<div class="form-group">
						 <label for="select">By Town</label>
      					<select id="select" class="form-control input-sm">
        				<option>Select Town..</option>
        				<option>Recent Stockouts</option>
        				<option>All Stockouts</option>
      					</select>
    				</div>
    				<div class="form-group">
						 <label for="select">By Facility</label>
      					<select id="select" class="form-control input-sm">
        				<option>Select Facility..</option>
        				<option>Recent Stockouts</option>
        				<option>All Stockouts</option>
      					</select>
    				</div>
    				<button class="btn btn-success btn-lg">Go</button>
    				</form>
    				</div>
					</div>	
            		</div>
            		
            <div class="col-lg-9">
            <div class="panel panel-default">
				<div id="google-map-canvas">
				<div id="map_container"></div>
				</div>
				</div>
				</div>
				<div class="clearfix"></div>
			</div>
			</div>
			</div>
            </div>
            
            
            <div class="row">
            <div class="container">
            <div class="panel panel-default">
            <div class="panel panel-heading"><h2>Results of Query:</h2></div>
            <div class="panel panel-body">
            <table class="table table-bordered">
  			  <tr>
        		<th>Province</th>
        		<th>Town</th>
        		<th>Facility</th>
        		<th>Medicine Name</th>
        		<th>Brand Name</th>
        		<th>Stock-out Status</th>
    		</tr>
    			<c:forEach var="result"  items="${stockoutResult}" >
    		<tr>
        	<td>${result.province}</td>
    		</tr>
</c:forEach>
			</table>
			</div>
			</div>
			</div>
			</div>
            <hr><footer><p>&copy; Company 2013</p></footer>
            </div>
            <script src="../../assets/js/jquery.js"></script>
             <script src="../../dist/js/bootstrap.min.js"></script> 
             </body>
             </html>
             <script language="javascript">
             <!--bmi_SafeAddOnload(bmi_load,"bmi_orig_img",0);//-->
	</script>
