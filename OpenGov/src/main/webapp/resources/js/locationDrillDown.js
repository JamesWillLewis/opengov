
function loadLocationDrillDown() {
	
	
	
	var locations = new Array();
	var stockouts = new Array();
	var medicines = new Array();
	var medStockouts = new Array();
	var chartData = new Array();
	
	var param={province:$('#provinceSelect').val(),district:$('#districtSelect').val(),
			town:$('#townSelect').val(),medicineCat:$('#medicineSelect').val()};
	
	var province = "Western Cape";
    
	$.ajax({
        type:'GET',
        url:'getgraphdata',
        data:param,
        headers: {
            Accept: 'application/json'
        },datatype: 'json',
        success:function(data){
        	
            $.each(data.locations, function(i, value) {
                locations.push(value);
            });
            $.each(data.locationStockouts, function(i, value) {
                stockouts.push(value);
            });
            
            
            $('#locationContainer').highcharts({
                chart: {
                    type: 'column',
                    width: 800,
                    height:450
                },
                title: {
                    text: 'Stockouts per Location'
                },
                xAxis: {
                	title:{text:'Locations'
                		},
                    categories: locations
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Number of stockouts'
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}:Location</td>' +
                        '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    data: stockouts,
                    name:locations
                }]
            });

            
            $.each(data.medicines, function(i, value) {
                medicines.push(value);
            });
            $.each(data.medicineStockouts, function(i, value) {
                    medStockouts.push(value);
                });
            
            for(var k=0;k<medicines.length;k++){
            	var point = [];
                point.push(medicines[k]);
                point.push(medStockouts[k]);
            	chartData.push(point);
            }
            
                $('#medicineContainer').highcharts({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        width:700,
                        height:450
                    },
                    title: {
                        text: 'Percentage of Stockouts of Medicines'
                    },
                    tooltip: {
                	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                color: '#000000',
                                connectorColor: '#000000',
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: 'Stockouts per Medicine',
                        data: chartData
                    }]
                });
                
                $('#tableData').find("tr:gt(0)").remove();
                
                $.each(data.allStockouts, function(i, value) {
	            	var row = "";
	            	row = '<tr><td>' + value.province + '</td><td>'
	            	+ value.town + '</td><td>'
	            	+ value.facility +'</td><td>' 
	            	+ value.medicineClass +'</td><td>' 
	            	+ value.medicineName +'</td><td>' 
	            	+ value.brandName +'</td><td>'
	            	+ value.dateOfFirstIssue + '</td><td>' 
	            	+ value.stockoutStatus +'</td></tr>';
	            	
	            $('#tableData').append(row);
	            });

                
       
              var latlng = new google.maps.LatLng(-30.145127,24.693603);
           	
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
               
               $.each(data.markers, function(i, value) {
                   
               	var marker = new google.maps.Marker({
               			
               	      position: new google.maps.LatLng(value.latitude,value.longitude),
               	      map: map, 
               	      title: value.identifier
               	    }); 
              });
        
                
                
        }
        
 

    });
	
   
};

