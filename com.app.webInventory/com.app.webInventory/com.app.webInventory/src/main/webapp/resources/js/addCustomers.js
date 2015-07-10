$(document).ready(function () {
			
	
		$("#submitCustomer").on('click', function()
				{

			callSaveCustomerAjax();
		});
		$("#cancelCustomer").on('click', function()
				{

			processToMainCustomerPage();
		})
		
});

function processToMainCustomerPage()
{
	 window.location.href = '/inventory/listCustomerPage';
}

function callSaveCustomerAjax()
{
	var customer = document.getElementById("customer").value;
	var phone = document.getElementById("phone").value;
	var address =  document.getElementById("address").value;
	var email = document.getElementById("email").value;
	var web = document.getElementById("web").value;
	var descript = document.getElementById("descript").value;
	if (customer == "" || phone ==""||address =="" || address =="" || email =="" || web=="")
	{
		alert("Please input all filed required");
	}
	else
	{
	var customerdata = {
			"customer" : customer,
			"phone" : phone,
			"descript" : descript,
			"address" : address,
			"web" : web,
			"email":email
		};
	
	var data = {};
	data[0] = JSON.stringify(customerdata);
	$.ajax({
		type : "post",
		url: '/inventory/addCustomer',
		data : data,
		dataType: 'json',
		success: function(data) {
			if(data.error == 1){
				// Set input user and password = ""
				//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
				alert("Add new Provider Success");
			}
			else {
				//$("#messageOrg").html("<p>Error when save organisation.</p>");
				alert("Add new Provider Fail");
			}
		}
	});
	}

}

