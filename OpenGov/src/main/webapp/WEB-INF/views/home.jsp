<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
		
		<title>OpenGov Home</title>

 	<link type="text/css" href="resources/css/bootstrap.min.css" rel="stylesheet"/>
 	
	</head>
	
<html>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
<div class="container">
<div class="navbar-header">
<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
  <span class="icon-bar"></span>
  <span class="icon-bar"></span>
  <span class="icon-bar"></span>
   </button> 
   <a class="navbar-brand" href="#">
     <span class="glyphicon glyphicon-home"></span> OpenGov </a>
 </div>
     <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
          <li class="active">
          <a href="#">Home</a></li>
           <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a>
            </li>
            </ul>
       </div><!--//-->
    </div>
  </div>
            <div class="container">
            <div class="row row-offcanvas row-offcanvas-right">
            <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>
            <div class="jumbotron">
            <h1>OpenGov Management Portal </h1>
            <p>Welcome to the OpenGov portal.
				This portal provides both a GUI front-end for management tasks, as well as the RESTful
				web-service API for all services (such as StockOut) available within the OpenGov system.
			</p>
			<P>  The time on the server is ${serverTime}. </P>
            </div>
            
            <div class="row">
            <div class="col-6 col-sm-6 col-lg-4">
            <h2>Stock-out Web Services</h2>
            <p>The stock-out website allows you to report and track stockouts occurring all over South Africa, and provides a stockout management interface with detailed statistical information for priviledged members</p>
            <p><a class="btn btn-primary" href="sows/stockouthome">Continue to Stock-Out Web-Site &raquo;</a></p>
            </div>
            <div class="col-6 col-sm-6 col-lg-4">
            <h2>Future Service</h2>
            	<p>This space is reserved for future services added to the OpenGov portal</p>
            	<p><a class="btn btn-primary" href="#"> Link to Site &raquo;</a></p>
            </div>
            <div class="col-6 col-sm-6 col-lg-4">
            <h2>Future Service</h2>
            <p>This space is reserved for future services added to the OpenGov portal</p>
            <p>
            <a class="btn btn-primary" href="#">Link to Site &raquo;</a>
            </p>
            </div>
            </div>
            </div>
            <div style="margin-top:55px"></div>
            <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
            <div class="well sidebar-nav">
            <ul class="nav">
            <li>External Links</li> 
            <li><a href="http://pubs.cs.uct.ac.za/honsproj/cgi-bin/view/2013/lewis_siedentopf.zip/New%20StockOut%20Site/index.html">Stockout Project Website</a></li>
            <li class="active">
           	Other Links</li> 
            <li><a href="#">Link</a></li>  
            <li><a href="#">Link</a></li>
             <li><a href="#">Link</a></li>
               </ul>
               </div>
               </div>
               </div>
               <hr>
               <footer><p>Stockout Honours Project 2013</p></footer>
               </div>
            <!--     <script src="../../assets/js/jquery.js"></script>-->
               <script src="resources/js/bootstrap.min.js"></script>
               </body>
</html>
