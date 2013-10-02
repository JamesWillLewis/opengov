  function loadMap(markerList) {
    
	var latlng = new google.maps.LatLng(-30.145127,24.693603);
    
	 var list = [
	     { lat: -30.145127 , lon: 24.693603, msg: "Here 1" },
	     { lat: -30.17 , lon: 24.7, msg: "Here 2"  },
	     { lat: -30.12 , lon: 24.64, msg: "Here 3" }
	 ];
	
    var myOptions = {
      zoom: 6,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.HYBRID,
	  mapTypeControlOptions: {
		style: google.maps.MapTypeControlStyle.DROPDOWN_MENU,
		position: google.maps.ControlPosition.TOP_RIGHT
	  }
    };
    var map = new google.maps.Map(document.getElementById("map_container"),myOptions);
    
    for (var i=0; i < list.length; i++) {
        
    	var marker = new google.maps.Marker({
    			
    	      position: new google.maps.LatLng(list[i].lat,list[i].lon),
    	      map: map, 
    	      title: list[i].msg
    	    }); 
    }
    
 
  }