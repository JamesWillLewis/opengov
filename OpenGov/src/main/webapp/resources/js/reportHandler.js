$('#provinceSelect').change(function(){

	var availableTags = new Array();
			 
      var param={province:$(this).val()};
        $.ajax({
            type:'GET',
            url:'getfacility',
            data:param,
            headers: {
                Accept: 'application/json'
            },datatype: 'json',
            success:function(data){
                $.each(data, function(i, value) {
                	availableTags.push(value);
                });
      			 
   			 $( "#tags" ).autocomplete({
   			 source: availableTags
   			 });
   			 
            }
        });
	});

$('#medicineClassSelect').change(function(){

	$('#medicineCombobox').empty();
		 
      var param={medicineClassIndex:$(this).val()};
        $.ajax({
            type:'GET',
            url:'getmedicines',
            data:param,
            headers: {
                Accept: 'application/json'
            },datatype: 'json',
            success:function(data){
                $.each(data, function(i, value) {
                	$('<option>',{ 
                	}).text(value).attr('value', value).appendTo('#medicineCombobox');
                	
                });
                
                
            }
        });
	});
