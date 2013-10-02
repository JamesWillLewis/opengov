<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>	
		<meta name="viewport" content="width=device-width, initial-scale=1.0">	
 		<link href="resources/css/bootstrap-stock.min.css" rel="stylesheet" media="screen"/>
</head>


<body>
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
          <li class="active"><a href="#">Home</a></li> 
          <li><a href="reportStockouts">Report Stock-out</a></li>
           <li><a href="#contact">Contacts</a></li>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
            <ul class="dropdown-menu">
            <li><a href="#">Action</a></li> 
            <li><a href="#">Another action</a></li> 
            <li><a href="#">Something else here</a></li> 
            <li class="divider"></li>
            <li class="dropdown-header">Nav header</li> 
            <li><a href="#">Separated link</a></li> 
            <li><a href="#">One more separated link</a></li>
            </ul>
            </li>
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
            <div class="jumbotron">
            <div class="container">
            <h1>Welcome to the Stockout Management Site</h1>
            <p>Use this site to search for stock-outs and stock information all over South Africa, or alternatively,
            you can choose to report a stock-out.</p>
            <p><a class="btn btn-primary btn-lg">Learn more &raquo;</a></p>
            </div>
            </div>
 </body>
</html>