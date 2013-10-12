
$('#provinceSelect').change(function(){
	
	$('#districtSelect').empty();
	$('#districtSelect').append($('<option>').text('All Districts').attr('value', 'all'));
	if ($(this).val() == 'all'){
		$('#townSelect').empty();
		$('#townSelect').append($('<option>').text('All Towns').attr('value', 'all'));	
	}
	else{
      var param={province:$(this).val()};
        $.ajax({
            type:'GET',
            url:'getdistricts',
            data:param,
            headers: {
                Accept: 'application/json'
            },datatype: 'json',
            success:function(data){
                //append options to list
            	
                $.each(data, function(i, value) {
                    $('#districtSelect').append($('<option>').text(value).attr('value', value));
                });
            }

        });
	}});

$('#districtSelect').change(function(){
	
	$('#townSelect').empty();
	$('#townSelect').append($('<option>').text('All Towns').attr('value', 'all'));
	if ($(this).val() != 'all'){
      var param={district:$(this).val()};
        $.ajax({
            type:'GET',
            url:'gettowns',
            data:param,
            headers: {
                Accept: 'application/json'
            },datatype: 'json',
            success:function(data){
                //append options to list
            	
                $.each(data, function(i, value) {
                    $('#townSelect').append($('<option>').text(value).attr('value', value));
                });
            }

        });
	}});