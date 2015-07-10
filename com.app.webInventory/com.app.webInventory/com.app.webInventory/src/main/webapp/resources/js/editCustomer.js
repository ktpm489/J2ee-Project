var nameStock = "";
var valueStock  = "";
var listProduct = null;
var valueSelected = null;
$(document).ready(function(){
	 var dataText1 = "";
		
         $('#editCustomer').click(function(){
	    	
        	 callSaveEditCustomerAjax();
	    });
	    
			
			$("#cancelCustomer").on('click', function()
					{

				processToMainCustomerPage();
			});
});
	   
	function processToMainCustomerPage()
	{
		 window.location.href = '/inventory/listCustomerPage';
	}

	function callSaveEditCustomerAjax()
	{
		
		
		var customername = document.getElementById("customer_name").value;
		var phone = document.getElementById("phone_number").value;
		var address = document.getElementById("address").value;
		var email = document.getElementById("email").value;
    	if (customername == "" || phone == "" || address == "" || email == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editCustomerData = {
				"customername" : customername,
				"phone" : phone,
				"address" : address,
				"email": email
				
			};	
		var data = {};
		data[0] = JSON.stringify(editCustomerData);
		$.ajax({
			type : "post",
			url: '/inventory/editCustomerData',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit Customer Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Edit Customer Fail");
				}
			}
		});
    		}
}
	


	
	