var nameStock = "";
var valueStock  = "";
var listProduct = null;
var valueSelected = null;
$(document).ready(function(){
	 var dataText1 = "";
		
         $('#SubmitChange').click(function(){
	    	
        	 callSaveEditProductAjax();
	    });
	    
			
			$("#Cancel").on('click', function()
					{

				processToMainProductPage();
			});
});
	   
	function processToMainProductPage()
	{
		 window.location.href = '/inventory/listProductPage';
	}

	function callSaveEditProductAjax()
	{
		
		
		var nameproduct = document.getElementById("name_product").value;
		var oriprice = document.getElementById("original_price").value;
		var purchaseprice = document.getElementById("purchase_price").value;
		var minimumnumber = document.getElementById("minimum_number").value;
		var maximumnumber = document.getElementById("maximum_number").value;
    	if (nameproduct == "" || oriprice == "" || purchaseprice == ""|| minimumnumber == ""|| maximumnumber == "")
    	{
    		alert("Please edit full filed required");
    	}
    	else
    		{
        var editProductData = {
				"nameproduct" : nameproduct,
				"oriprice" : oriprice,
				"purchaseprice" : purchaseprice,
				"minimumnumber" : minimumnumber,
				"maximumnumber":maximumnumber,
			
			};	
		var data = {};
		data[0] = JSON.stringify(editProductData);
		$.ajax({
			type : "post",
			url: '/inventory/editProductData',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Edit Product Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Edit Product Fail");
				}
			}
		});
    		}
}
	


	
	