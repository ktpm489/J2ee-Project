<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Stock Inward</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"
	href="<c:url value="./resources/css/bootstrap.css" /> " type="text/css">
<link rel="stylesheet"
	href="<c:url value="./resources/css/style.css" /> " type="text/css" />
<script type="text/javascript" src="./resources/js/jquery-1.9.1.js"></script>
<script type="text/javascript"  src="./resources/js/editStockTransfer.js"></script>
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
<div id = "container">
<div id ="menu">
 <ul id= "menu_list">
      <li> <a class="menu_item" href="http://localhost:8080/inventory/listStaffPage" > Home</a> </li>
      <li> <a class="menu_item" href="http://localhost:8080/inventory/contactTeam" > Contact</a> </li>
      <li> <a class="menu_item" href="http://localhost:8080/inventory/aboutTeam" > About</a> </li>
    </ul>
</div>
<div id="main_container">
<div id ="main">
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
  <div id="right_column_container" width=100% align="left" >
    <form name="fr_move" method="post" action="https://live.anywhereerp.com/index.php?page=move&action=save&submitid=1092" autocomplete="off">
      <input type="hidden" name="se_ordertype" value="5">
      <input type="hidden" name="hi_orderid" value="">
      <table border="2">
        <tbody>
          <tr>
            <td><h2 align="center"><font color="#FF0000" size="+2" face="Arial, Arial, Helvetica"><em>Stock Transfer Edit</em></font></h2></td>
          </tr>
          <tr>
            <td><table border="0">
                <tbody>
                  <tr>
                    <td>Transfer Code:</td>
                    <td><input id="transfercode" type="text" name="" value="${stocktransfer}" /></td>
                  </tr>
                  <tr>
                    <td>Staff Name:</td>
                    <td><input id="staff_name" type="text" name=""  value="${staffname}"readonly /></td>
                  </tr>
                  <tr>
                    <td> Date Transfer: </td>
                    <td><input id="date_transfer" type="text" name="" value="${datetransfer}" readonly /></td>
                  </tr>
				   <tr>
                   <td>State Stock Transfer:</td>
					<td><select id="state" size="1">
															<c:forEach items="${stateList}" var="current">
															<option value="<c:out value="${current.value}">
                									 			</c:out> ">
														   <c:out value="${current.nameValue}"></c:out></option>
														  </c:forEach>
															<option value="true">Active</option>
															<option value="false">Waiting Active</option>
				    </select></td>
                  </tr>
				  <tr>
                    <td> Total Quantity: </td>
                    <td><input id="totalquantity" type="text" name="" value="${totalquantity}" readonly /></td>
                  </tr>
				  <tr>
                    <td> Total Price: </td>
                    <td><input id="totalprice" type="text" name="" value="${totalprice}" readonly /></td>
                  </tr>
                  <tr>
                    <td valign="top"> Description: </td>
                    <td valign="top"><textarea id="description"  rows="4" name="ta_ordernotes" cols="40" readonly> ${note}</textarea></td>
                  </tr>
                </tbody>
              </table></td>
          </tr>
          <tr> 
            <td>
		<table  border="1" id="checkTable" class="table table-striped table-hover  table-bordered">
				<thead>
								<tr>
									<th>Product</th>
									<th>From Stock </th>
									<th>To Stock </th>
									<th>Total Number</th>
									<th>Price</th>
									<th>Discount</th>
									<th>Total Amount</th>
								</tr>
							 </thead>
							<tbody>
								<c:forEach items="${stockTransferList}" var="current">
									<tr>
										<td><c:out value="${current.nameproductId}"/></td>
										<td id="testid"><c:out value="${current.namestockFromId}"/></td>
										<td id="te"><c:out value="${current.namestockToId}" /></td>
										<td><c:out value="${current.numberTransfer}" /></td>
										<td><c:out value="${current.price}" /></td>
										<td><c:out value="${current.discount}" /></td>
										<td><c:out value="${current.amount}" /></td>
									</tr>
								</c:forEach>
							</tbody>
		</table>
		</td>
          </tr>
          
          <!--SAVE button-->
          <tr align="left">
            <td align="left"><br>
              <br>
              &nbsp;&nbsp;&nbsp;
              <input type="button" value=" Submit Change " id ="submitchangeTransferEdit">
              &nbsp;&nbsp;&nbsp;
              <input type="button" value=" Cancel"  id ="cancelchangeTransferEdit">
                &nbsp; &nbsp;
              <input type="button" id="Print" name="hi_confirm_cancel" value="Print">
              <br>
            
              <input type="hidden" id="hi_confirm_cancel_message" name="hi_confirm_cancel_message" value="This &#39;Move Inventory&#39; entry will be discarded."></td>
          </tr>
        </tbody>
      </table>
    </form>
  </div>
  <div id ="footer">
    <div id="footer_text">
      <div align="center">Online Inventory Management Solution - Products made by :<br>
        Nguyễn Hoàng Vũ<br>
        Nguyễn Văn Quang<br>
        Huỳnh Văn Hoa Xuân<br>
      </div>
    </div>
  </div>
</div>
</body>
</html>
