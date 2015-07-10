var nameStock = "";
var valueStock  = "";
var listProduct = null;
var valueSelected = null;
$(document).ready(function(){
	 var dataText1 = "";
		
         $('#submitStockChange').click(function(){
	    	
        	 callSaveEditStockAjax();
	    });
	    
			
			$("#cancelStockChange").on('click', function()
					{

				processToMainStockPage();
			});
});
	   
	function processToMainStockPage()
	{
		 window.location.href = '/inventory/listStockPage';
	}

	function callSaveEditStockAjax()
	{
		
		
		var stockname = document.getElementById("stock_name").value;
		var e3 = document.getElementById("state1");
    	var state = e3.options[e3.selectedIndex].value;
    	if (stockname == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editStockData = {
				"stockname" : stockname,
				"state":state
			};	
		var data = {};
		data[0] = JSON.stringify(editStockData);
		$.ajax({
			type : "post",
			url: '/inventory/editStockData',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit Stock Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Edit Stock Fail");
				}
			}
		});
    		}
}
	


	
	