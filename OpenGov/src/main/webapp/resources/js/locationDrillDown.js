$(function loadLocationDrillDown() {
    
        var colors = Highcharts.getOptions().colors,
            categories = ['Western Cape', 'Eastern Cape', 'Gauteng', 'KZN', 'Limpopo'],
            name = 'Provinces',
            data = [{ 
                y: 55.11,
                color: colors[0],
                drilldown: {
                   name: 'MSIE versions',
                   categories: ['MSIE 8.0', 'MSIE 6.0', 'MSIE 7.0', 'MSIE 9.0'],
                   level: 1, 
                   data: [{
                       y: 33.06,
                       drilldown: {
                           level: 2,
                           name: 'drilldown next level',
                           categories: ['a', 'b', 'c'],
                           data: [23,54,47],
                           color: colors[0]
                       }
                   }, 10.85, 7.35, 2.41],
                   color: colors[0]
                }
             }, {
                    y: 21.63,
                    color: colors[1],
                    drilldown: {
                        name: 'Eastern Cape Towns',
                        categories: ['district1', 'district2', 'district3', 'district4', 'district5'],
                        data: [0.20, 0.83, 1.58, 13.12, 5.43],
                        color: colors[1]
                    }
                }, {
                    y: 11.94,
                    color: colors[2],
                    drilldown: {
                        name: 'Gauteng Towns',
                        categories: ['district1', 'district2', 'district3', 'district4', 'district5',
                                     'district6', 'district7', 'district8'],
                        data: [0.12, 0.19, 0.12, 0.36, 0.32, 9.91, 0.50, 0.22],
                        color: colors[2]
                    }
                }, {
                    y: 7.15,
                    color: colors[3],
                    drilldown: {
                        name: 'KZN Towns',
                        categories: ['district1', 'district2', 'district3', 'district4', 'district5'],
                        data: [4.55, 1.42, 0.23, 0.21, 0.20, 0.19, 0.14],
                        color: colors[3]
                    }
                }, {
                    y: 2.14,
                    color: colors[4],
                    drilldown: {
                        name: 'Limpopo Towns',
                        categories: ['district1', 'district2', 'district3'],
                        data: [ 0.12, 0.37, 1.65],
                        color: colors[4]
                    }
                }];
    
        function setChart(name, categories, data, color) {
			chart.xAxis[0].setCategories(categories, false);
			chart.series[0].remove(false);
			chart.addSeries({
				name: name,
				data: data,
				color: color || 'white'
			}, false);
			chart.redraw();
        }
    
        var chart = $('#locationContainer').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Stock-outs per Province'
            },
            subtitle: {
                text: 'Click the columns to view towns/districts. Click again to view provinces.'
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                title: {
                    text: 'Total percent of stockouts'
                }
            },
            plotOptions: {
                column: {
                    cursor: 'pointer',
                    point: {
                        events: {
                            click: function() {
                                var drilldown = this.drilldown;
                                if (drilldown) { // drill down
                                    setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                                } else { // restore
                                    setChart(name, categories, data);
                                }
                            }
                        }
                    },
                    dataLabels: {
                        enabled: true,
                        color: colors[0],
                        style: {
                            fontWeight: 'bold'
                        },
                        formatter: function() {
                            return this.y +'%';
                        }
                    }
                }
            },
            tooltip: {
                formatter: function() {
                    var point = this.point,
                        s = this.x +':<b>'+ this.y +'% of Stockouts</b><br/>';
                    if (point.drilldown) {
                        s += 'Click to view '+ point.category +' towns/districts';
                    } else {
                        s += 'Click to return to provinces';
                    }
                    return s;
                }
            },
            series: [{
                name: name,
                data: data,
                color: 'white'
            }],
            exporting: {
                enabled: false
            }
        })
        .highcharts(); // return chart
    });




$(function loadSupplierChart() {
    $('#supplyContainer').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'Percentage of Out of Stock Medicines Distributed by Supplier'
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
            name: 'Medicines per Supplier',
            data: [
                ['supplier1',   45.0],
                ['supplier2',       26.8],
                ['supplier3',   11.0],
                ['supplier4',    8.5],
                ['supplier5',     6.2],
                ['supplier6',   0.7]
            ]
        }]
    });
});