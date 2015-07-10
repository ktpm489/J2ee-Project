$(document).ready(function () {

		$("#submitProduct").on('click', function()
				{

			callSaveProductAjax();
		})
		$("#cancelAddProduct").on('click', function()
				{

			processToMainProductPage();
		})
		
});

function processToMainProductPage()
{
	 window.location.href = '/inventory/listProductPage';
}


function callSaveProductAjax()
{
	var nameProduct = document.getElementById("nameProduct").value;
	var e = document.getElementById("productType");
	var productTypeId = e.options[e.selectedIndex].value;
	var e1 = document.getElementById("provider");
	var providerId = e1.options[e1.selectedIndex].value;
	var e2 = document.getElementById("productUnit");
	var productunitId = e2.options[e2.selectedIndex].value;
	var oriPrice = document.getElementById("oriPrice").value;
	var purPrice =  document.getElementById("purPrice").value;
	var orgSource = document.getElementById("source").value;
	var descript =  document.getElementById("descript").value;
	var min = document.getElementById("min").value;
	var max = document.getElementById("max").value;
	var e3 = document.getElementById("staff");
	var staff = e3.options[e3.selectedIndex].value;
	if (nameProduct == "" || productTypeId ==""||providerId =="" || productunitId =="" || oriPrice ==""||purPrice ==""||orgSource ==""||descript ==""||min ==""||max ==""||staff =="")
	{
		alert("Please input all filed required");
	}
	else
	{
	var product = {
			"nameProduct" : nameProduct,
			"productTypeId" : productTypeId,
			"providerId" : providerId,
			"productunitId" : productunitId,
			"oriPrice" : oriPrice,
			"purPrice" : purPrice,
			"orgSource" : orgSource,
			"descript" : descript,
			"min" : min,
			"max" : max,
			"staff" : staff
		};
	
	var data = {};
	data[0] = JSON.stringify(product);
	$.ajax({
		type : "post",
		url: '/inventory/addProduct',
		data : data,
		dataType: 'json',
		success: function(data) {
			if(data.error == 1){
				// Set input user and password = ""
				//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
				alert("Add new Product Success");
			}
			else {
				//$("#messageOrg").html("<p>Error when save organisation.</p>");
				alert("Add new Product Fail");
			}
		}
	});
	}

}

