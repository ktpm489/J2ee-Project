$(document).ready(function () {
			
	
		$("#submitProvider").on('click', function()
				{

			callSaveProviderAjax();
		});
		$("#cancelProvider").on('click', function()
				{

			processToMainProviderPage();
		})
		
});

function processToMainProviderPage()
{
	 window.location.href = '/inventory/listProviderPage';
}

function callSaveProviderAjax()
{
	var provider = document.getElementById("provider").value;
	var phone = document.getElementById("phone").value;
	var address =  document.getElementById("address").value;
	var email = document.getElementById("email").value;
	var web = document.getElementById("web").value;
	var descript = document.getElementById("descript").value;
	if (provider == "" || phone ==""||address =="" || address =="" || email =="" || web=="")
	{
		alert("Please input all filed required");
	}
	else
	{
	var provider = {
			"provider" : provider,
			"phone" : phone,
			"descript" : descript,
			"address" : address,
			"web" : web,
			"email":email
		};
	
	var data = {};
	data[0] = JSON.stringify(provider);
	$.ajax({
		type : "post",
		url: '/inventory/addProvider',
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

