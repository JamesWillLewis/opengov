$('#provinceSelect').change(function(){

	$('.combobox-container').empty();
	$('.combobox-container').remove();
	
	var $wrapper = $('<select id="facilityCombobox" class="form-control input-sm">',{
	    'class':'wrapper'
	}).appendTo('#provinceDiv');
		 
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
                	$('<option>',{ 
                	}).text(value).appendTo($wrapper);
                	
                });
                ($wrapper).combobox();
            }
        });
	});

$('#medicineClassSelect').change(function(){

	//$('.combobox-container').empty();
	//$('.combobox-container').remove();
	$('#medicineCombobox').empty();
	//var $wrapper = $('<select id="medicineCombobox" class="form-control input-sm">',{
	//    'class':'wrapper'
	//}).appendTo('#medicineDiv');
		 
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
                //($wrapper).combobox();
            }
        });
	});
