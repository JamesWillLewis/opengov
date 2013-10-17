
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
                    text: 'Stockouts per Province'
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
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: Location </td>' +
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
                    data: stockouts

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

                
       
        }
        
 

    });
	
   
};


function loadTimeGraph() {
    $('#timeContainer').highcharts({
        chart: {
            type: 'area'
        },
        title: {
            text: 'US and USSR nuclear stockpiles'
        },
        subtitle: {
            text: 'Source: <a href="http://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf">'+
                'thebulletin.metapress.com</a>'
        },
        xAxis: {
            labels: {
                formatter: function() {
                    return this.value; // clean, unformatted number for year
                }
            }
        },
        yAxis: {
            title: {
                text: 'Nuclear weapon states'
            },
            labels: {
                formatter: function() {
                    return this.value / 1000 +'k';
                }
            }
        },
        tooltip: {
            pointFormat: '{series.name} produced <b>{point.y:,.0f}</b><br/>warheads in {point.x}'
        },
        plotOptions: {
            area: {
                pointStart: 1940,
                marker: {
                    enabled: false,
                    symbol: 'circle',
                    radius: 2,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            }
        },
        series: [{
            name: 'USA',
            data: [null, null, null, null, null, 6 , 11, 32, 110, 235, 369, 640,
                1005, 1436, 2063, 3057, 4618, 6444, 9822, 15468, 20434, 24126,
                27387, 29459, 31056, 31982, 32040, 31233, 29224, 27342, 26662,
                26956, 27912, 28999, 28965, 27826, 25579, 25722, 24826, 24605,
                24304, 23464, 23708, 24099, 24357, 24237, 24401, 24344, 23586,
                22380, 21004, 17287, 14747, 13076, 12555, 12144, 11009, 10950,
                10871, 10824, 10577, 10527, 10475, 10421, 10358, 10295, 10104 ]
        }, {
            name: 'USSR/Russia',
            data: [null, null, null, null, null, null, null , null , null ,null,
            5, 25, 50, 120, 150, 200, 426, 660, 869, 1060, 1605, 2471, 3322,
            4238, 5221, 6129, 7089, 8339, 9399, 10538, 11643, 13092, 14478,
            15915, 17385, 19055, 21205, 23044, 25393, 27935, 30062, 32049,
            33952, 35804, 37431, 39197, 45000, 43000, 41000, 39000, 37000,
            35000, 33000, 31000, 29000, 27000, 25000, 24000, 23000, 22000,
            21000, 20000, 19000, 18000, 18000, 17000, 16000]
        }]
    });
};
