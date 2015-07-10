var nameStock = "";
var valueStock  = "";
var listProduct = null;
var valueSelected = null;
$(document).ready(function(){
	 var dataText1 = "";
	    
		
         $('#submitStockOutwardEdit').click(function(){
	    	
        	 callSaveEditStockOutwardAjax();
	    });
         $('#Print').click(function(){
 	    	
        	 callSavePrintStockOutwardAjax();
	    });
         
			
			$("#cancelStockOutwardEdit").on('click', function()
					{

				processToMainOutwardPage();
			});
});
	   
	function processToMainOutwardPage()
	{
		 window.location.href = '/inventory/listStockOutwardPage';
	}

	function callSaveEditStockOutwardAjax()
	{
		
		
		var stockoutward = document.getElementById("outward_code").value;
        var e3 = document.getElementById("state");
    	var state = e3.options[e3.selectedIndex].value;
    	if (stockoutward == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editStockOutwardData = {
				"stockoutward" : stockoutward,
				"state":state
			};	
		var data = {};
		data[0] = JSON.stringify(editStockOutwardData);
		$.ajax({
			type : "post",
			url: '/inventory/editStockOutwardData',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit Stock Outward Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Edit Stock Outward Fail");
				}
			}
		});
    		}
}

	
	function callSavePrintStockOutwardAjax()
	{
		
		
		var stockoutward = document.getElementById("outward_code").value;
        var e3 = document.getElementById("state");
    	var state = e3.options[e3.selectedIndex].value;
    	if (stockoutward == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editStockOutwardData = {
				"stockoutward" : stockoutward,
				"state":state
			};	
		var data = {};
		data[0] = JSON.stringify(editStockOutwardData);
		$.ajax({
			type : "post",
			url: '/inventory/editStockOutwardDataPrint',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit Stock Outward Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Edit Stock Outward Fail");
				}
			}
		});
    		}
}
	