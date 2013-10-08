<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>	
		<meta name="viewport" content="width=device-width, initial-scale=1.0">	
 		<link href="resources/css/bootstrap-stock.min.css" rel="stylesheet" media="screen"/>
		
		<script type="text/javascript" 
     	src="resources/js/jquery-1.10.2.min.js"></script>
     	    
     	<script language="javascript">    
			function setActive() {
			if ($(document).find("title").text() == "Report a Stockout"){
              document.getElementById("Report_Page").className += " active";}
			else if ($(document).find("title").text() == "Stock-out Home"){
	             document.getElementById("Stockout Home").className += " active";}
			else if ($(document).find("title").text() == "Contacts"){
	             document.getElementById("Contacts_Page").className += " active";}
            }
     	</script>

</head>


<body onload=setActive()>
<div class="navbar navbar-default navbar-fixed-top">
<div class="container">
<div class="navbar-header"><button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button> 
          <a class="navbar-brand" href="#">Stockout</a>
          </div>
          <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
          <li id="Stockout Home"><a href="stockoutHome">Home</a></li> 
          <li id="Report_Page"><a href="reportStockouts">Report Stock-out</a></li>
           <li id="Contacts_Page"><a href="loadContacts">Contacts</a></li>
            </ul>
            <form class="navbar-form navbar-right">
            <div class="form-group">
            <input type="text" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
            <input type="password" placeholder="Password" class="form-control"></div>
            <button type="submit" class="btn btn-success">Sign in</button>
            </form>
            </div><!--//-->
            </div>
            </div>
            <div class="jumbotron masthead">
            <div class="container">
            <div class="page-header">
             <h2 ><strong>Stock-out Management Site</strong></h2>
             <p><small>Empowering the public to search for and report stock-outs all over South Africa</small></p>
            </div>
            <p><a class="btn btn-primary btn-lg">Learn more &raquo;</a></p>
            </div>
            </div>
 </body>
</html>