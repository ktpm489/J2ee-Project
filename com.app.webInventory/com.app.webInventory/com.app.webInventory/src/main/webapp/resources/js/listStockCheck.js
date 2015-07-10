var id =  null;
var nameFile = null;
$(window).load(
	function() {
	$(".deleterow").on("click", function() 
					{
		
						var $killrow = $(this).parent('tr');
						//$killrow.addClass("danger");
						var state = $(this).closest("tr").find('td:eq(4)').text();
						id = $(this).closest("tr").find('td:eq(0)').text();
						//var disc = $(this).closest('td').next().find('input').val();
						//var disc1 = $(this).closest("tr").find('td:eq(1)').find('input').attr('data-id');
						//alert("id" + id );
						if (state == "Active")
						{
							alert("You can't delete stock check when it actived");
						}else
						{	
						var result1 = confirm("Do you want to delete it?");
						if (result1 == true) 
						{
							$killrow.fadeOut(2000, function() 
							{
								deleteStockCheckAjax();
								$(this).remove();
								
							});
						}
						
						}
					
				});

		   $("#addnewCheckStock").on("click",function()
				{
			     processToInsertStockCheckPage();
			  });
		   $(".editrow").on("click", function() 
					{
		
						var $killrow = $(this).parent('tr');
						//$killrow.addClass("danger");
						var state = $(this).closest("tr").find('td:eq(4)').text();
						id = $(this).closest("tr").find('td:eq(0)').text();
						//alert("id" + id );		
						editStockCheckAjax();

						
					
				});
		
});

function processToInsertStockCheckPage()
{
	 window.location.href = '/inventory/addNewStockCheck';
}
function processToEditStockCheckPage()
{
	 window.location.href = '/inventory/editStockCheckPage';
}


function deleteStockCheckAjax()
{

	$.ajax({
		type : "post",
		url : "/inventory/deleteStockCheck",
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

function editStockCheckAjax()
{

	$.ajax({
		type : "post",
		url : "/inventory/editStockCheck",
		cache : false,
		data : "&id=" + id,
		dataType : "json",
		success : function(data) {
			if(data.error == 1){
				// Set input user and password = ""
				//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
				processToEditStockCheckPage();
			}
			else {
				//$("#messageOrg").html("<p>Error when save organisation.</p>");
				alert("Add new StockOutward Fail");
			}
		},
		error : function() 
		{
			//alert('Error while request..');
		}
	});
}