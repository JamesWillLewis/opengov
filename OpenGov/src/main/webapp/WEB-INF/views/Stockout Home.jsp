<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="layout.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>	
	<head>
	
		<title>Stock-out Home</title>
	
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
			
			div#locationContainer div#supplyContainer{
				height: 400px; 
				margin: 0 auto;
				min-width:800px;
			}
			.google-map-canvas,
			.google-map-canvas *{ .box-sizing(content-box); }
	</style>
	
	<!--  script type="text/javascript" 
    	 src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
 -->
	<script type="text/javascript"
		src="resources/js/maploader.js"> </script>
		
		<script src="resources/js/jquery-1.10.2.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
     <script src="resources/js/highcharts.js"></script>
    <script src="resources/js/locationDrillDown.js"></script>
	
</head>

<body onload="loadMap(-30.145127) loadLocationDrillDown()">

           <div class="container">
            
            <div class="row" >
            <div class="panel panel-default">
            <div class="panel panel-heading"><h3>Showing Stock-outs For:</h3></div>
            <div class="panel panel-body">
            <div class="col-lg-3">
            <div class="panel panel-primary">
            <div class="panel-heading">Filter Results:</div>
  			<div class="panel-body">
				<form class="role-horizontal" role="form">
    				<div class="form-group">
						<label for="select">Province</label>
      					<select id="select" class="form-control input-sm">
        				<option>All Provinces</option>
        				<option>Recent Stockouts</option>
        				<option>All Stockouts</option>
      					</select>
    				</div>
    				<div class="form-group">
						 <label for="select">Districts</label>
      					<select id="select" class="form-control input-sm">
        				<option>All Districts</option>
        				<option>Recent Stockouts</option>
        				<option>All Stockouts</option>
      					</select>
    				</div>
    				<div class="form-group">
						 <label for="select">Towns</label>
      					<select id="select" class="form-control input-sm">
        				<option>All Towns</option>
        				<option>Recent Stockouts</option>
        				<option>All Stockouts</option>
      					</select>
    				</div>
    				
    				 <div class="form-group">
						<label for="select">Supply Depot</label>
      					<select id="select" class="form-control input-sm">
        				<option>All Depots</option>
        				<option>Recent Stockouts</option>
        				<option>All Stockouts</option>
      					</select>
    				</div>
    				
    				    <div class="form-group">
						<label for="select">Supplier</label>
      					<select id="select" class="form-control input-sm">
        				<option>All Suppliers</option>
        				<option>Recent Stockouts</option>
        				<option>All Stockouts</option>
      					</select>
    				</div>

    				<div class="form-group">
						<label for="select">Medicine Categories</label>
      					<select id="select" class="form-control input-sm">
        				<option>Select Category..</option>
        				<option>Recent Stockouts</option>
        				<option>All Stockouts</option>
      					</select>
    				</div>

    				<button class="btn btn-success btn-lg">Find Stockouts
    				<span class="glyphicon glyphicon-search"></span>
    				</button>
    				</form>
    				</div>
					</div>	
            		</div>
            		
            <div class="col-lg-9">
            <div class="panel panel-default">
            
            <div class="panel-heading">

            	<ul class="nav nav-pills nav-justified" id="pills">
					<li class="active"><a href="#google-map-canvas" data-toggle="pill">Map View</a></li>
  					<li class=""><a href="#locationInfo" data-toggle="pill">Location</a></li>
  					<li class=""><a href="#supplyInfo" data-toggle="pill" onclick="loadSupplierChart()">Supplier</a></li>
  					<li class=""><a href="#depotInfo" data-toggle="pill">Supply Depot</a></li>
				</ul>
            </div>
            
            	 <div class="panel-body" style="height:520px;">
            	<div class="pill-content">
            	
            	<div id="google-map-canvas" class="pill-pane active">
				<div id="map_container"></div>
				</div>
            	
            	  	<div  id="locationInfo" class="pill-pane"><h4>Location</h4>
  					<div id="locationContainer"></div>
  					</div>
  				
  				
  				<div id="supplyInfo" class="pill-pane" ><h4>Supplier</h4>
  				<div id="supplyContainer"></div>
  				</div>
  				
  				<div  id="depotInfo" class="pill-pane"><h4>Supply Depot</h4>
  				</div>
  				
  				
			</div>
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
 
  				<div  id="stockInfo"><h4>Stockout Info</h4>
  					<table class="table table-bordered table-hover">
  			  		<tr>
        				<th>Province</th>
        				<th>Town</th>
        				<th>Facility</th>
        				<th>Supply Depot</th>
        				<th>Supplier</th>
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

    
            <hr><footer><p>&copy; Company 2013</p></footer>
            </div>

             </body>


             </html>
         
