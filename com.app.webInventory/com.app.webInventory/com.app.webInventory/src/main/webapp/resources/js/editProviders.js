var nameStock = "";
var valueStock  = "";
var listProduct = null;
var valueSelected = null;
$(document).ready(function(){
	 var dataText1 = "";
		
         $('#editProvider').click(function(){
	    	
        	 callSaveEditProviderAjax();
	    });
	    
			
			$("#cancelProvider").on('click', function()
					{

				processToMainProviderPage();
			});
});
	   
	function processToMainProviderPage()
	{
		 window.location.href = '/inventory/listProviderPage';
	}

	function callSaveEditProviderAjax()
	{
		
		
		var provider = document.getElementById("provider_name").value;
		var phone = document.getElementById("phone_number").value;
		var address = document.getElementById("address").value;
		var email = document.getElementById("email").value;
    	if (provider == "" || phone == "" || address == "" || email == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editProviderData = {
				"provider" : provider,
				"phone" : phone,
				"address" : address,
				"email": email
				
			};	
		var data = {};
		data[0] = JSON.stringify(editProviderData);
		$.ajax({
			type : "post",
			url: '/inventory/editProviderData',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit Provider Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Edit Provider Fail");
				}
			}
		});
    		}
}
	


	
	