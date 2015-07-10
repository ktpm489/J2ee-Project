<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Stock</title>
<link rel="stylesheet" href="<c:url value="./resources/css/style.css" /> " type="text/css"/>
<script src="./resources/js/jquery-1.9.1.js"></script>
<script src="./resources/js/addStock.js"></script>
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
        <td><h2 align="center"><font color="#FF0000" size="+2" face="Arial, Arial, Helvetica"><em>Stock</em></font></h2></td>
      </tr>
      <tr>
        <td><table border="0">
            <tbody>
              <!--   <tr>
                 
                  <td width="25%">TrackingNumber:</td>
                  <td width="50%"><input type="text" name="tb_viewableid" value="">&nbsp;*optional</td>
                 
                </tr>-->
              <tr>
                <td>Stock Name:</td>
                <td><input id="stock_name" type="text" name="stock_name" ></td>
              </tr>
              <tr>
                <td>Address:</td>
                <td><input id="address" type="text" name="address" ></td>
              </tr>
              <tr>
                <td>Manager Name:</td>
                 <td><select id="staff" ><c:forEach items="${stafflist}" var="current">
 <option  value="<c:out value="${current.staffId}">
 </c:out> "><c:out value="${current.staffName}"></c:out>
 </option></c:forEach></select></td>
                  <td></td>
              </tr>
              <tr>
                <td>Stock Type:</td>
                   <td><select id="stocktype" ><c:forEach items="${liststock}" var="current">
 <option  value="<c:out value="${current.stockTypeId}">
 </c:out> "><c:out value="${current.stockTypeName}"></c:out>
 </option></c:forEach></select></td>
                  <td></td>
              </tr>
              <tr>
                <td>State:</td>
                <td><select id="state" name="state" size="1" onChange="">
                   <option value="true">Active</option>
                    <option value="false">Waiting Active</option>
                  </select></td>
              </tr>
              <tr>
                <td valign="top"> Notes:</td>
                <td valign="top"><textarea rows="4" id="note" name="note" cols="40"></textarea></td>
              </tr>
            </tbody>
          </table></td>
      </tr>
      <!--<tr>
          <td height="23">
            
          </td>
        </tr>--> 
      
      <!--SAVE button-->
      <tr align="left">
        <td align="left"><br>
          <br>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" value="Submit " id="stockSubmit">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" value="Cancel" id="stockCancel">
          <br>
          &nbsp;
          <input type="hidden" id="hi_confirm_cancel" name="hi_confirm_cancel" value="1">
          <input type="hidden" id="hi_confirm_cancel_message" name="hi_confirm_cancel_message" value="This &#39;Move Inventory&#39; entry will be discarded."></td>
      </tr>
    </tbody>
  </table>
</form>
      </div>
    </div>
  </div>
  <div id ="footer">
    <div id="footer_text">
      <div align="center"> Online Inventory Management Solution - Products made by GayTeam: <br>
        Nguyễn Hoàng Vũ <br>
        Nguyễn Văn Quang <br>
        Huỳnh Văn Hoa Xuân <br>
      </div>
    </div>
  </div>
</div>
</body>
</html>

