var nameStock = "";
var valueStock  = "";
var listProduct = null;
var valueSelected = null;
$(document).ready(function(){
	 var dataText1 = "";
		
	   
		
         $('#submitStockCheck').click(function(){
	    	
        	 callSaveEditStockCheckAjax();
	    });
	    
         $('#Print').click(function(){
 	    	
        	 callSavePrintStockCheckAjax();
	    });
			
			$("#cancelStockCheck").on('click', function()
					{

				processToMainCheckPage();
			});
});
	   
	function processToMainCheckPage()
	{
		 window.location.href = '/inventory/listStockCheckPage';
	}

	function callSaveEditStockCheckAjax()
	{
		
		
		var stockcheck = document.getElementById("stockcheck").value;
        var e3 = document.getElementById("state");
    	var state = e3.options[e3.selectedIndex].value;
    	if (stockcheck == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editStockCheckData = {
				"stockcheck" : stockcheck,
				"state":state
			};	
		var data = {};
		data[0] = JSON.stringify(editStockCheckData);
		$.ajax({
			type : "post",
			url: '/inventory/editStockCheckData',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit StockCheck Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Edit StockCheck Fail");
				}
			}
		});
    		}
}
	
function callSavePrintStockCheckAjax()
	{
		
		
		var stockcheck = document.getElementById("stockcheck").value;
        var e3 = document.getElementById("state");
    	var state = e3.options[e3.selectedIndex].value;
    	if (stockcheck == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editStockCheckData = {
				"stockcheck" : stockcheck,
				"state":state
			};	
		var data = {};
		data[0] = JSON.stringify(editStockCheckData);
		$.ajax({
			type : "post",
			url: '/inventory/editStockCheckDataPrint',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Add new StockCheck Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Add new StockCheck Fail");
				}
			}
		});
    		}
}


	
	