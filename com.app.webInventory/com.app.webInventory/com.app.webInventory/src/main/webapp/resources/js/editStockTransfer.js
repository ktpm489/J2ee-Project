var nameStock = "";
var valueStock  = "";
var listProduct = null;
var valueSelected = null;
$(document).ready(function(){
	 var dataText1 = "";
	
		
         $('#submitchangeTransferEdit').click(function(){
	    	
        	 callSaveEditStockTransferAjax();
	    });
	    
         $('#Print').click(function(){
 	    	
        	 callSavePrintStockTransferAjax();
	    });
	    
         
			
			$("#cancelchangeTransferEdit").on('click', function()
					{

				processToMainStockTransferPage();
			});
});
	   
	function processToMainStockTransferPage()
	{
		 window.location.href = '/inventory/listStockTransferPage';
	}

	function callSaveEditStockTransferAjax()
	{
		
		
		var transfercode = document.getElementById("transfercode").value;
        var e3 = document.getElementById("state");
    	var state = e3.options[e3.selectedIndex].value;
    	if (transfercode == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editStockTransferData = {
				"transfercode" : transfercode,
				"state":state
			};	
		var data = {};
		data[0] = JSON.stringify(editStockTransferData);
		$.ajax({
			type : "post",
			url: '/inventory/editStockTransferData',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit Stock Transfer Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert(" Edit Stock Transfer Fail");
				}
			}
		});
    		}
}
	

	function callSavePrintStockTransferAjax()
	{
		
		
		var transfercode = document.getElementById("transfercode").value;
        var e3 = document.getElementById("state");
    	var state = e3.options[e3.selectedIndex].value;
    	if (transfercode == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editStockTransferData = {
				"transfercode" : transfercode,
				"state":state
			};	
		var data = {};
		data[0] = JSON.stringify(editStockTransferData);
		$.ajax({
			type : "post",
			url: '/inventory/editStockTransferDataPrint',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit Stock Transfer Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert(" Edit Stock Transfer Fail");
				}
			}
		});
    		}
}
	