<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add New StockCheck</title>
<link rel="stylesheet"
	href="<c:url value="./resources/css/bootstrap.css" /> " type="text/css">
<link rel="stylesheet"
	href="<c:url value="./resources/css/style.css" /> " type="text/css" />
<script type="text/javascript"
	src="http://www.jqueryscript.net/demo/jQuery-Plugin-For-Calculation-Form-Calculation-Table-Calx/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="http://www.jqueryscript.net/demo/jQuery-Plugin-For-Calculation-Form-Calculation-Table-Calx/jquery-calx-1.1.8.js"></script>
<link
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/themes/ui-darkness/jquery-ui.min.css"
	rel="stylesheet" />
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script>
<script src="./resources/js/addStockCheck.js"></script>
<script type="text/javascript">
	function loadData(currentRow) {
		var html = '<tr class="data">\
			    <td><select id="A'+currentRow+'" ><c:forEach items="${productList}" var="current"><option  value="<c:out value="${current.stockProductId}"></c:out>" stock-id="<c:out value="${current.stockId}"></c:out>" product-id="<c:out value="${current.productId}"></c:out>" ><c:out value="${current.nameStockProduct}"></c:out></option></c:forEach></select></td>\
			    <td><select id="V'+currentRow+'" ><c:forEach items="${productUnitList}" var="current"><option  value="<c:out value="${current.unitName}"></c:out> "><c:out value="${current.unitName}"></c:out></option></c:forEach></select></td>\
			    <td><input type="text" id="B'+currentRow+'" value="" data-format=" 0,0[.]00" /></td>\
			    <td><input type="text" id="C'+currentRow+'" value="" data-format="0" /></td>\
			    <td><input type="text" id="D'
				+ currentRow
				+ '" value="" data-format="0[.]00 %" /></td>\
			    <td><input type="text" id="E'
				+ currentRow
				+ '" value="" data-format=" 0,0[.]00" data-formula="($B'
				+ currentRow
				+ '*$C'
				+ currentRow
				+ ')*(1-$D'
				+ currentRow
				+ ')" /></td>\
			    <td><input type="button" value="remove" class="btn-remove" /></td>\
			</tr>'
		return html;
	}
</script>
<script type="text/javascript">
	
</script>

<style>
td {
	padding: 5px 10px;
	border: solid 1px black;
}

th td {
	background: #ddd;
}

input[type="text"] {
	width: 100%;
}
</style>

</head>

<body>
	<div id="container">
		<div id="menu">
			<ul id= "menu_list">
      <li> <a class="menu_item" href="http://localhost:8080/inventory/listStaffPage" > Home</a> </li>
      <li> <a class="menu_item" href="http://localhost:8080/inventory/contactTeam" > Contact</a> </li>
      <li> <a class="menu_item" href="http://localhost:8080/inventory/aboutTeam" > About</a> </li>
    </ul>
		</div>
		<div id="main_container">
			<div id="main">
				<div id="left_column_container" align="center">
					 <ul class="sub_nav_list">
          <li> <a class="sub_nav_item" href="http://localhost:8080/inventory/listStaffPage "> <span class="sub_nav_item_text">Home</span> </a> </li>
          <li> <a id= "listStock" class="sub_nav_item" href="http://localhost:8080/inventory/listStockPage"> <span class="sub_nav_item_text"> List Stock </span></a> </li>
          <li> <a  id="listStaff" class="sub_nav_item" href="http://localhost:8080/inventory/listStaffPage"> <span class="sub_nav_item_text">Staff Manager</span> </a> </li>
          <li> <a id ="listProvider" class="sub_nav_item" href="http://localhost:8080/inventory/listProviderPage"> <span class="sub_nav_item_text">Provider List</span> </a> </li>
		  <li> <a id="listCustomer" class="sub_nav_item" href="http://localhost:8080/inventory/listCustomerPage"> <span class="sub_nav_item_text">Customer List</span> </a> </li>
		  <li> <a id ="listTransfer" class="sub_nav_item" href="http://localhost:8080/inventory/listStockTransferPage"> <span class="sub_nav_item_text">Transfer History</span> </a> </li>   
          <li> <a id="listInward" class="sub_nav_item" href=" http://localhost:8080/inventory/listStockInwardPage"> <span class="sub_nav_item_text">Inward History</span> </a> </li>
          <li> <a id="listOutward"class="sub_nav_item" href=" http://localhost:8080/inventory/listStockOutwardPage"> <span class="sub_nav_item_text">Outward History</span> </a> </li>
		  <li> <a id="listCheckStock"class="sub_nav_item" href="http://localhost:8080/inventory/listStockCheckPage"> <span class="sub_nav_item_text">Check Stock History</span> </a> </li>
          <li> <a id="addStock" class="sub_nav_item" href="http://localhost:8080/inventory/addstockPage"> <span class="sub_nav_item_text">Add Stock</span> </a> </li>
          <li> <a id="addInward" class="sub_nav_item" href="http://localhost:8080/inventory/pageInsertStockInward"> <span class="sub_nav_item_text">Add Stock Inward</span> </a> </li>
          <li> <a id="addOutward" class="sub_nav_item" href="http://localhost:8080/inventory/addstockoutwardPage"> <span class="sub_nav_item_text">Add Stock Outward</span> </a> </li>
          <li> <a id="addTransfer" class="sub_nav_item" href="http://localhost:8080/inventory/addstockTransferPage"> <span class="sub_nav_item_text">Add Transfer </span> </a> </li>
          <li> <a id="addCheckStock" class="sub_nav_item" href="http://localhost:8080/inventory/addNewStockCheck"> <span class="sub_nav_item_text">Add Check Stock</span> </a> </li>
          <li> <a id="addProduct" class="sub_nav_item" href="http://localhost:8080/inventory/addproductpage"> <span class="sub_nav_item_text">Add Product</span> </a> </li>
          <li> <a id="addStaff" class="sub_nav_item" href="http://localhost:8080/inventory/addstaffPage"> <span class="sub_nav_item_text">Add Staff</span> </a> </li>
          <li> <a id="addProvider" class="sub_nav_item" href="http://localhost:8080/inventory/addproviderPage"> <span class="sub_nav_item_text">Add Provider</span> </a> </li>
          <li> <a id="addCustomer" class="sub_nav_item" href="http://localhost:8080/inventory/addcustomerPage"> <span class="sub_nav_item_text">Add Customer</span> </a> </li>
          <li> <a id="logout" class="sub_nav_item" href="http://localhost:8080/inventory/loginPage"> <span class="sub_nav_item_text">Log Out</span> </a> </li>
        </ul>
				</div>
				<div id="right_column_container" width=100% align="left">

					<form name="fr_move" method="post" autocomplete="off">
						<input type="hidden" name="se_ordertype" value="5"> <input
							type="hidden" name="hi_orderid" value="">
						<table border="2">
							<tbody>
								<tr>
									<td><h2 align="center">
											<font color="#FF0000" size="+2"
												face="Arial, Arial, Helvetica"><em>Add New Stock
													Transfer </em></font>
										</h2></td>
								</tr>
								<tr>
									<td><table border="0">
											<tbody>
												<tr>
													<td>Code Stock Check:</td>
													<td><input id="stockcheck" type="text"
														name="stock_name"></td>

												</tr>
												<tr>
													<td>Stock Name:</td>
													<td><select id="stock">
															<c:forEach items="${stockList}" var="current1">
																<option
																	value="<c:out value="${current1.stockId}"></c:out> ">
																	<c:out value="${current1.stockName}">
																	</c:out>
																</option>
															</c:forEach>
													</select></td>
												</tr>
												<tr>
													<td>Staff Name:</td>
													<td><select id="staff">
															<c:forEach items="${staffList}" var="current1">
																<option
																	value="<c:out value="${current1.staffId}"></c:out> ">
																	<c:out value="${current1.staffName}">
																	</c:out>
																</option>
															</c:forEach>
													</select></td>
												</tr>
												<tr>
													<td>OrderDate:</td>
													<td>
														<!--    <input type="hidden" name="hd_OrderDate" ,="" id="hd_OrderDate" value="0">-->

														<input class="datepicker" type="text">
													</td>
												</tr>
												<tr>
													<td>State Stock Check</td>
													<td><select id="state" size="1">
															<option value="true">Active</option>
															<option value="false">Waiting Active</option>
													</select></td>
												</tr>

												<tr>
													<td valign="top">Additional Notes:</td>
													<td valign="top"><textarea id="note" rows="4"
															name="ta_ordernotes" cols="40"></textarea></td>
												</tr>
											</tbody>
										</table></td>
								</tr>
								<!--<tr>
          <td height="23">
            
          </td>
        </tr>-->

								<tr>
									<td>
										<table  border="1" id="checkTable" class="table table-striped table-hover  table-bordered">
				<thead>
					<tr>
						<th>Product</th>
						<th>Quantity</th>

					</tr>
				</thead>
				<tbody>
					<!--<c:forEach items="${listCar}" var="current">
						<tr>
							<td><c:out value="${current.car}" /></td>
						<%-- 	<td><c:out value="${current.dateSave}" /></td> --%>
							<td><input type="text" id="A'+currentRow+'" value="${current.car}"  data-id="<c:out value="${current.name}"></c:out>" readonly></td>
							<td class='deleterow'><div
									class='glyphicon glyphicon-remove'></div></td>
							<td class='downloadrow'><div
									class='glyphicon glyphicon-edit'></div></td>
						</tr>
					</c:forEach>-->
				</tbody>

			</table>
									</td>
								</tr>

								<!--SAVE button-->
								<tr align="left">
									<td align="left"><br> <br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
										value=" Submit " id="submitStockCheck">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
										value=" Cancel " id="cancelStockCheck" /> <br> &nbsp;
										<input type="hidden" id="hi_confirm_cancel"
										name="hi_confirm_cancel" value="1"> <input
										type="hidden" id="hi_confirm_cancel_message"
										name="hi_confirm_cancel_message"
										value="This &#39;Move Inventory&#39; entry will be discarded."></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div id="footer">
					<div id="footer_text">
						<div align="center">
							Online Inventory Management Solution - Products made by GayTeam:<br>
							Nguyễn Hoàng Vũ<br> Nguyễn Văn Quang<br> Huỳnh Văn Hoa
							Xuân<br>
						</div>
					</div>
				</div>
			</div>
</body>
</html>
