var currentRow = 0;
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
	   
		
		
		$("#button1").on('click', function() {

			//var text = $("#datepicker").value;
			//alert("Value" + dateText1);
		})
$('#submitStockInward').click(function(){
	    	
	    	callSaveStockInwardAjax();
	    });
	    
			
			$("#cancelStockInward").on('click', function()
					{

				processToMainInwardPage();
			});
});
	   
	function processToMainInwardPage()
	{
		 window.location.href = '/inventory/listStockInwardPage';
	}

	function callSaveStockInwardAjax()
	{
		
		
		var totalprice = $("#calx #F1").text();
    	var totalAmount = $("#calx #G1").text();
    	var e = document.getElementById("supplier");
    	var supplier = e.options[e.selectedIndex].value;
    	var e1 = document.getElementById("staff");
    	var staff = e1.options[e1.selectedIndex].value;
    	var day1 = $(".datepicker").datepicker('getDate').getDate();                 
        var month1 = $(".datepicker").datepicker('getDate').getMonth() + 1;             
        var year1 = $(".datepicker").datepicker('getDate').getFullYear();
        var fullDate = year1 + "-" + month1 + "-" + day1;
        var note =  document.getElementById("note").value;
        var stockinward =  document.getElementById("stockinward").value;
        var e2 = document.getElementById("stock");
    	var stock = e2.options[e2.selectedIndex].value;
    	var e3 = document.getElementById("state");
    	var state = e3.options[e3.selectedIndex].value;
    	if (stockinward == "" )
    	{
    		alert("Please input all filed required");
    	}else
    	{
        var addStockInwarddata = {
				"totalprice" : totalprice,
				"totalAmount" : totalAmount,
				"supplier" : supplier,
				"staff" : staff,
				"date" : fullDate,
				"stock" : stock,
				"note":note,
				"stockinward":stockinward,
				"state":state
			};
    	var TableData = new Array();
		var i = 1;
		$('#calx tr.data').each(function(row, tr)
				{
			var productID = "#A"+ i;
			var unitName = "#V" + i;
			var itemPrice = "#B" + i;
			var quantity = "#C" + i;
			var discount = "#D" +i;
			var subtotal = "#E" +i;
			TableData[row]={
		        "productId" :$(productID).val()
		        , "unitName" : $(unitName).val()
		        , "itemPrice" : $(itemPrice).val()
		        , "quantity" : $(quantity).val()
		        , "discount" : $(discount).val()
		        , "subtotal" : $(subtotal).val()
		        , "unitName" : $(unitName).val()
		        , "stockId" : stock
			}
			i++;
		});
		alert( "Gia tri" +TableData);
		
		var data = {};
		data[0] = JSON.stringify(addStockInwarddata);
		data[1] = JSON.stringify(TableData);
		$.ajax({
			type : "post",
			url: '/inventory/addStockInward',
			data : data,
			dataType: 'json',
			success: function(data) {
				if(data.error == 1){
					// Set input user and password = ""
					//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
					alert("Add new StockInward Success");
				}
				else {
					//$("#messageOrg").html("<p>Error when save organisation.</p>");
					alert("Add new StockInward Fail");
				}
			}
		});
    	}
}

	
	
	