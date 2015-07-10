var currentRow = 0;
var listProduct = null;
var valueSelected = null;
$(document).ready(function(){
	 var dataText1 = "";
		$(".datepicker").datepicker({
			dateFormat: "dd-mm-yy",
			onSelect: function(dateText, inst) {
				var date = $.datepicker.parseDate(inst.settings.dateFormat || $.datepicker._defaults.dateFormat, dateText, inst.settings);
				 dateText1 = $.datepicker.formatDate("yy-m-d", date, inst.settings);
			//	alert("Value" + dateText1);
			}
		});
		$(".datepicker").datepicker("setDate", new Date);
	
	 $('#calx').calx();
	    
	    $('#add_item').click(function(){
			var $calx = $('#calx');
			currentRow++;
			var html =loadData(currentRow);
			$calx.append(html);
			/*$calx.append('<tr class="data">\
				    <td><select id="A'+currentRow+'" ><c:forEach items="${productList}" var="current"><option  value="<c:out value="${current.productId}"></c:out> "><c:out value="${current.productName}"></c:out></option></c:forEach></select></td>\
				    <td><select id="V'+currentRow+'" ><c:forEach items="${productUnitList}" var="current"><option  value="<c:out value="${current.unitId}"></c:out> "><c:out value="${current.unitName}"></c:out></option></c:forEach></select></td>\
				    <td><input type="text" id="B'+currentRow+'" value="" data-format="$ 0,0[.]00" /></td>\
				    <td><input type="text" id="C'+currentRow+'" value="" data-format="0" /></td>\
				    <td><input type="text" id="D'+currentRow+'" value="" data-format="0[.]00 %" /></td>\
				    <td><input type="text" id="E'+currentRow+'" value="" data-format="$ 0,0[.]00" data-formula="($B'+currentRow+'*$C'+currentRow+')*(1-$D'+currentRow+')" /></td>\
				    <td><input type="button" value="remove" class="btn-remove" /></td>\
				</tr>');*/
			//update total formula
			$('#F1').attr('data-formula','SUM($E1,$E'+currentRow+')');
			$('#G1').attr('data-formula','SUM($C1,$C'+currentRow+')');
			$calx.calx('refresh');
	    });

	    $('#calx').on('click', '.btn-remove', function(){
	    	
	    	$(this).parent().parent().remove();
	    	$('#calx').calx('refresh');
	    });
	    $('#stock').on('change', function (e) {
	        var optionSelected = $("option:selected", this);
	        valueSelected  = this.value;
	      //  alert("Value" + valueSelected);
	    
	    });
		
		
		$("#button1").on('click', function() {

			//var text = $("#datepicker").value;
		//	alert("Value" + dateText1);
		})
$('#submitStockTransfer').click(function(){
	    	
	    	callSaveStockTransferAjax();
	    });
	    
			
			$("#cancelStockTransfer").on('click', function()
					{

				processToMainStockTransferPage();
			});
});
	   
	function processToMainStockTransferPage()
	{
		 window.location.href = '/inventory/listStockTransferPage';
	}

	function callSaveStockTransferAjax()
{
		
		
		var totalprice = $("#calx #F1").text();
    	var totalAmount = $("#calx #G1").text();
    	var e = document.getElementById("state");
    	var state = e.options[e.selectedIndex].value;
    	var e1 = document.getElementById("staff");
    	var staff = e1.options[e1.selectedIndex].value;
    	var day1 = $(".datepicker").datepicker('getDate').getDate();                 
        var month1 = $(".datepicker").datepicker('getDate').getMonth() + 1;             
        var year1 = $(".datepicker").datepicker('getDate').getFullYear();
        var fullDate = year1 + "-" + month1 + "-" + day1;
        var note =  document.getElementById("note").value;
        var stocktransfer =  document.getElementById("stocktransfer").value;
        if (stocktransfer == "" )
    	{
    		alert("Please input all filed required");
    	}else
        {
        var addStockTransferdata = {
				"totalprice" : totalprice,
				"totalAmount" : totalAmount,
				"state" : state,
				"staff" : staff,
				"date" : fullDate,
				"note":note,
				"stocktransfer":stocktransfer
			};
		
        
    	var TableData = new Array();
		var i = 1;
		$('#calx tr.data').each(function(row, tr)
				{
			var productID = "#A"+ i;
			var unitName = "#V" + i;
			var stockTo = "#Z" +i;
			var itemPrice = "#B" + i;
			var quantity = "#C" + i;
			var discount = "#D" +i;
			var subtotal = "#E" +i;
			TableData[row]={
				"productstock" :$(productID).val()
		        ,"productId" :$(this).find(':selected').attr('product-id')
		        , "unitName" : $(unitName).val()
		        , "itemPrice" : $(itemPrice).val()
		        , "quantity" : $(quantity).val()
		        , "discount" : $(discount).val()
		        , "subtotal" : $(subtotal).val()
		        , "unitName" : $(unitName).val()
		        , "stockIdFrom" : $(this).find(':selected').attr('stock-id')
		        ,"stockIdTo" : $(stockTo).val()
			}
			i++;
		});
		//alert( "Gia tri" +TableData);
		
		var data = {};
		data[0] = JSON.stringify(addStockTransferdata);
		data[1] = JSON.stringify(TableData);
		$.ajax({
			type : "post",
			url: '/inventory/addStockTransfer',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Add new StockTransfer Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Add new StockTransfer Fail");
				}
			}
		});
        }
}
	
	
	