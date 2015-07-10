var nameStock = "";
var valueStock  = "";
var listProduct = null;
var valueSelected = null;
$(document).ready(function(){
	 var dataText1 = "";
		
         $('#editStaff').click(function(){
	    	
        	 callSaveEditStaffAjax();
	    });
	    
			
			$("#cancelStaff").on('click', function()
					{

				processToMainStaffPage();
			});
});
	   
	function processToMainStaffPage()
	{
		 window.location.href = '/inventory/listStaffPage';
	}

	function callSaveEditStaffAjax()
	{
		
		
		var staffname = document.getElementById("staffname").value;
		var phone = document.getElementById("phone").value;
		var address = document.getElementById("address").value;
		var email = document.getElementById("email").value;
    	if (staffname == "" || phone == "" || address == "" || email == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editStaffData = {
				"staffname" : staffname,
				"phone" : phone,
				"address" : address,
				"email": email
				
			};	
		var data = {};
		data[0] = JSON.stringify(editStaffData);
		$.ajax({
			type : "post",
			url: '/inventory/editStaffData',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit Staff Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Edit Staff Fail");
				}
			}
		});
    		}
}
	


	
	