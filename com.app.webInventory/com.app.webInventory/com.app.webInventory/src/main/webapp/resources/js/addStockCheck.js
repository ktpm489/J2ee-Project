var nameStock = "";
var valueStock  = "";
var listProduct = null;
var valueSelected = null;
$(document).ready(function(){
	 var dataText1 = "";
		$(".datepicker").datepicker({
			dateFormat: "dd-mm-yy",
			onSelect: function(dateText, inst) {
				var date = $.datepicker.parseDate(inst.settings.dateFormat || $.datepicker._defaults.dateFormat, dateText, inst.settings);
				 dateText1 = $.datepicker.formatDate("yy-m-d", date, inst.settings);
				//alert("Value" + dateText1);
			}
		});
		$(".datepicker").datepicker("setDate", new Date);
	    $('#stock').on('change', function (e) {
	        var optionSelected = $("option:selected", this);
	       // valueSelected  = this.value;
	        valueStock  = optionSelected.val();
	        nameStock = optionSelected.text();
	       // alert("Value" + valueStock);
	        callDataAjax();
	    
	    });
		
         $('#submitStockCheck').click(function(){
	    	
        	 callSaveStockCheckAjax();
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

	function callSaveStockCheckAjax()
	{
		
		
		var stockcheck = document.getElementById("stockcheck").value;
    	var e = document.getElementById("stock");
    	var stock = e.options[e.selectedIndex].value;
    	var e1 = document.getElementById("staff");
    	var staff = e1.options[e1.selectedIndex].value;
    	var day1 = $(".datepicker").datepicker('getDate').getDate();                 
        var month1 = $(".datepicker").datepicker('getDate').getMonth() + 1;             
        var year1 = $(".datepicker").datepicker('getDate').getFullYear();
        var fullDate = year1 + "-" + month1 + "-" + day1;
        var note =  document.getElementById("note").value;
        var e3 = document.getElementById("state");
    	var state = e3.options[e3.selectedIndex].value;
    	if (stockcheck == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var addStockCheckData = {
				"stockcheck" : stockcheck,
				"stock" : stock,
				"staff" : staff,
				"fullDate" : fullDate,
				"note" : note,
				"state":state
			};	
		var data = {};
		data[0] = JSON.stringify(addStockCheckData);
		$.ajax({
			type : "post",
			url: '/inventory/addStockCheck',
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
			url: '/inventory/dataStockCheck',
			data : data,
			dataType: 'json',
			success: function(data) {
				// Delete all row first
				$('#checkTable tr').slice(1).remove();
				if (data.length == 0)
				{
					alert("Stock has no product");
				}
				else
				{
					drawTable(data);
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
	
	