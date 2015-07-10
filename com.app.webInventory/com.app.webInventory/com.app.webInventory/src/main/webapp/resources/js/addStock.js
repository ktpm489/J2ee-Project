$(document).ready(function () {

		$("#stockSubmit").on('click', function()
				{

			callSaveStockAjax();
		})
		$("#stockCancel").on('click', function()
				{

			processToMainStockPage();
		})
		
});

function processToMainStockPage()
{
	 window.location.href = '/inventory/listStockPage';
};


function callSaveStockAjax()
{
	var stockname = document.getElementById("stock_name").value;
	var address = document.getElementById("address").value;
	var e1 = document.getElementById("staff");
	var staff = e1.options[e1.selectedIndex].value;
	var e2 = document.getElementById("state");
	var state = e2.options[e2.selectedIndex].value;
	var e3 = document.getElementById("stocktype");
	var stocktype = e3.options[e3.selectedIndex].value;
	var note =  document.getElementById("note").value;
	
	if (stockname == "" || address =="")
	{
		alert("Please input all filed required");
	}
	else
	{
	var stockdata = {
			"stockname" : stockname,
			"address" : address,
			"staff" : staff,
			"state" : state,
			"stocktype" : stocktype,
			"note" : note
		};
	
	var data = {};
	data[0] = JSON.stringify(stockdata);
	$.ajax({
		type : "post",
		url: '/inventory/addStock',
		data : data,
		dataType: 'json',
		success: function(data) {
			if(data.error == 1){
				// Set input user and password = ""
				//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
				alert("Add new Stock Success");
			}
			else {
				//$("#messageOrg").html("<p>Error when save organisation.</p>");
				alert("Add new Stock Fail");
			}
		}
	});
	}

}

