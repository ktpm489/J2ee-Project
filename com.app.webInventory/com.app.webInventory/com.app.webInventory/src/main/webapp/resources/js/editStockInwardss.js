var nameStock = "";
var valueStock  = "";
var listProduct = null;
var valueSelected = null;
$(document).ready(function(){
	 var dataText1 = "";
	    $('#stock').on('change', function (e) {
	        var optionSelected = $("option:selected", this);
	       // valueSelected  = this.value;
	        valueStock  = optionSelected.val();
	        nameStock = optionSelected.text();
	        //alert("Value" + valueStock);
	        callDataAjax();
	    
	    });
		
         $('#submitStockInwardEdit').click(function(){
	    	
        	 callSaveEditStockInwardAjax();
	    });
         
         $('#Print').click(function(){
 	    	
        	 callSavePrintStockInwardAjax();
	    });
        
			
			$("#cancelStockInwardEdit").on('click', function()
					{

				processToMainInwardPage();
			});
});
	   
	function processToMainInwardPage()
	{
		 window.location.href = '/inventory/listStockInwardPage';
	}

	function callSaveEditStockInwardAjax()
	{
		
		
		var stockedit = document.getElementById("inward_code").value;
        var e3 = document.getElementById("state");
    	var state = e3.options[e3.selectedIndex].value;
    	if (stockedit == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editStockEditData = {
				"stockedit" : stockedit,
				"state":state
			};	
		var data = {};
		data[0] = JSON.stringify(editStockEditData);
		$.ajax({
			type : "post",
			url: '/inventory/editStockInwardData',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit Stock Inward Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Edit Stock Inward Fail");
				}
			}
		});
    		}
}
	
function callDataAjax()
{
		var stockInfo = {
				"name" : nameStock,
				"value" : valueStock
			};

		var data = {};
		data[0] = JSON.stringify(stockInfo);
		$.ajax({
			type : "post",
			url: '/inventory/updatedataStockCheck',
			data : data,
			dataType: 'json',
			success: function(data) {
				// Delete all row first
				//$('#checkTable tr').slice(1).remove();
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit Stock Check Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Edit Stock Check Fail");
				}
				
	
			}
		});

}
function drawTable(data) {
    for (var i = 0; i < data.length; i++) {
        drawRow(data[i]);
    }
}

function drawRow(rowData) {
    var row = $("<tr />")
    $("#checkTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td>" + rowData.nameproductId + "</td>"));
    row.append($("<td>" + rowData.quantity + "</td>"));
   
}



function callSavePrintStockInwardAjax()
{
	
	
	var stockedit = document.getElementById("inward_code").value;
    var e3 = document.getElementById("state");
	var state = e3.options[e3.selectedIndex].value;
	if (stockedit == "")
	{
		alert("Please edit full filed required");
	}
	else
		{
    var editStockEditData = {
			"stockedit" : stockedit,
			"state":state
		};	
	var data = {};
	data[0] = JSON.stringify(editStockEditData);
	$.ajax({
		type : "post",
		url: '/inventory/editStockInwardDataPrint',
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




	
	