var dataText1 = null;
$(document).ready(function () {
			
	
	$(".datepicker").datepicker({
		
		onSelect: function(dateText, inst) {
			var date = $.datepicker.parseDate(inst.settings.dateFormat || $.datepicker._defaults.dateFormat, dateText, inst.settings);
			 dateText1 = $.datepicker.formatDate("yy-m-d", date, inst.settings);
			 var day1 = $(".datepicker").datepicker('getDate').getDate();                 
	            var month1 = $(".datepicker").datepicker('getDate').getMonth() + 1;             
	            var year1 = $(".datepicker").datepicker('getDate').getFullYear();
	            var total = year1 + "-" + month1 + "-" + day1;
		}
	});
	$(".datepicker").datepicker("setDate", new Date);
	
	
	
		$("#submitStaff").on('click', function()
				{

			callSaveStaffAjax();
		});
		$("#cancelStaff").on('click', function()
				{

			processToMainStaffPage();
		})
		
});

function processToMainStaffPage()
{
	 window.location.href = '/inventory/listStaffPage';
}

function callSaveStaffAjax()
{
	var staffname = document.getElementById("staffname").value;
	var staffcode = document.getElementById("staffcode").value;
	var phone = document.getElementById("phone").value;
	var address =  document.getElementById("address").value;
	var email = document.getElementById("email").value;
	var day1 = $(".datepicker").datepicker('getDate').getDate();                 
    var month1 = $(".datepicker").datepicker('getDate').getMonth() + 1;             
    var year1 = $(".datepicker").datepicker('getDate').getFullYear();
    var fullDate = year1 + "-" + month1 + "-" + day1;
	if (staffname == "" || staffcode ==""||phone =="" || address =="" || email =="")
	{
		alert("Please input all filed required");
	}
	else
	{
	var staff = {
			"staffname" : staffname,
			"staffcode" : staffcode,
			"phone" : phone,
			"address" : address,
			"birth" : fullDate,
			"email":email
		};
	
	var data = {};
	data[0] = JSON.stringify(staff);
	$.ajax({
		type : "post",
		url: '/inventory/addStaff',
		data : data,
		dataType: 'json',
		success: function(data) {
			if(data.error == 1){
				// Set input user and password = ""
				//$("#messageOrg").html("<p style='color: green; font-weight: bold;''>Save organisation successfully.</p>");
				alert("Add new Staff Success");
			}
			else {
				//$("#messageOrg").html("<p>Error when save organisation.</p>");
				alert("Add new Staff Fail");
			}
		}
	});
	}

}

