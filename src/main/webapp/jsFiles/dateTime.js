
	$(function() {
		if($('.date_picker input').length > 0) {
			$('.date_picker input').datetimepicker({
				format : "DD/MM/YYYY"
				
			});
		}
		
	});
	
    $(function () {
		if($('.datetime_picker input').length > 0) {
			$('.datetime_picker input').datetimepicker({
				format : "LT"
			});
		}
        
    });
  
   
   
