var id =  null;
var nameFile = null;
$(window).load(
	function() {
	$(".deleterow").on("click", function() 
					{
		
						var $killrow = $(this).parent('tr');
						//$killrow.addClass("danger");
						var state = $(this).closest("tr").find('td:eq(6)').text();
						id = $(this).closest("tr").find('td:eq(0)').text();
						//var disc = $(this).closest('td').next().find('input').val();
						//var disc1 = $(this).closest("tr").find('td:eq(1)').find('input').attr('data-id');
					//	alert("id" + id );
						if (state == "Active")
						{
							alert("You can't delete stock transfer when it actived");
						}else
						{	
						var result1 = confirm("Do you want to delete it?");
						if (result1 == true) 
						{
							$killrow.fadeOut(2000, function() 
							{
								deleteStockTransferAjax();
								$(this).remove();
								
							});
						}
						
						}
					
				});

		   $("#addnewStockTransfer").on("click",function()
				{
			     processToInsertStockTransferPage();
			  });
		   $(".editrow").on("click", function() 
					{
		
						var $killrow = $(this).parent('tr');
						//$killrow.addClass("danger");
						var state = $(this).closest("tr").find('td:eq(5)').text();
						id = $(this).closest("tr").find('td:eq(0)').text();
						//alert("id" + id );		
						editStockTransferAjax();

						
					
				});
		
});

function processToInsertStockTransferPage()
{
	 window.location.href = '/inventory/addstockTransferPage';
}
function processToEditStockTransferPage()
{
	 window.location.href = '/inventory/editStockTransferPage';
}
function deleteStockTransferAjax()
{

	$.ajax({
		type : "post",
		url : "/inventory/deleteStockTransfer",
		cache : false,
		data : "&id=" + id,
		dataType : "json",
		success : function() {
			alert('OK');
		},
		error : function() 
		{
			//alert('Error while request..');
		}
	});
}

function editStockTransferAjax()
{

	$.ajax({
		type : "post",
		url : "/inventory/editStockTransferForward",
		cache : false,
		data : "&id=" + id,
		dataType : "json",
		success : function(data) {
			if(data.error == 1){
				// Set input user and password = ""
				//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
				processToEditStockTransferPage();
			}
			else {
				//$("#messageOrg").html("<p>Error when save organisation.</p>");
				alert("Forward StockTransfer Fail");
			}
		},
		error : function() 
		{
			//alert('Error while request..');
		}
	});
}