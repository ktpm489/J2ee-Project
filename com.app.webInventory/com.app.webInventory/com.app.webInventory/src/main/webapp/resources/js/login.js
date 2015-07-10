$(document).ready(function () {

		$("#login").on('click', function()
				{

			callProcessLoginAjax();
		})
		
});
function processToMainPage()
{
	 window.location.href = '/inventory/listStockInwardPage';
};
function callProcessLoginAjax()
{
	var user = document.getElementById("user").value;
	var pass = document.getElementById("pass").value;
	
	if (user == "" || pass =="")
	{
		alert("Please input all filed required");
	}
	else
	{
	var userdata = {
			"user" : user,
			"pass" : pass
		};
	
	var data = {};
	data[0] = JSON.stringify(userdata);
	$.ajax({
		type : "post",
		url: '/inventory/processLogin',
		data : data,
		dataType: 'json',
		success: function(data) {
			if(data.error == 1){
				
				alert("Login Success");
				 processToMainPage();
			}
			else {
				//$("#messageOrg").html("<p>Error when save organisation.</p>");
				alert("Please Login Again ");
			}
		}
	});
	}

}