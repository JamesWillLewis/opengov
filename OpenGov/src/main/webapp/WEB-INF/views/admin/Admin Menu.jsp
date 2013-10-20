     	<script language="javascript">    
			function setActive() {
			if ($(document).find("title").text() == "Assignments"){
              document.getElementById("assignments").className += " active";}
			else if ($(document).find("title").text() == "Facilities"){
	             document.getElementById("facilities").className += " active";}
			else if ($(document).find("title").text() == "Issues"){
	             document.getElementById("issues").className += " active";}
			else if ($(document).find("title").text() == "Medicine Classes"){
	             document.getElementById("medicineclasses").className += " active";}
			else if ($(document).find("title").text() == "Products"){
	             document.getElementById("products").className += " active";}
			else if ($(document).find("title").text() == "Medicines"){
	             document.getElementById("medicines").className += " active";}
			else if ($(document).find("title").text() == "Staff Members"){
	             document.getElementById("staffmembers").className += " active";}
			else if ($(document).find("title").text() == "Stockouts"){
	             document.getElementById("stockouts").className += " active";}
           }
     	</script>

<div class="panel panel-primary">
    <div class="panel panel-heading"><h4>Administrator Services</h4></div>
    <ul class="nav nav-pills nav-stacked">
	<li id="stockouts"><a href="<c:url value="/sows/admin/stockouts"/>">Stock-outs</a></li>
	<li id="facilities"><a href="<c:url value="/sows/admin/facilities"/>">Facilities</a></li>
	<li id="products"><a href="<c:url value="/sows/admin/products"/>">Products</a></li>
	<li id="medicines"><a href="<c:url value="/sows/admin/medicines"/>">Medicines</a></li>
	<li id="medicineclasses"><a href="<c:url value="/sows/admin/medicineclasses"/>">Medicine Classes</a></li>
	<li id="issues"><a href="<c:url value="/sows/admin/issues"/>">Issues</a></li>
	<li id="assignments"><a href="<c:url value="/sows/admin/assignments"/>">Assignments</a></li>
	<li id="staffmembers"><a href="<c:url value="/sows/admin/staffmembers"/>">Staff Members</a><li>
	</ul>
	</div>
