<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Provider  </title>
<link rel="stylesheet"
	href="<c:url value="./resources/css/bootstrap.css" /> " type="text/css">
<link rel="stylesheet"
	href="<c:url value="./resources/css/style.css" /> " type="text/css" />
<script src="./resources/js/jquery-1.9.1.js"></script>
<script src="./resources/js/editProvider.js"></script>
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
      <div id="right_column_container" align="left" >
        <table border="2">
          <tbody>
            <tr>
              <td><H2 align="center"><font color="#FF0000" size="+2" face="Arial, Arial, Helvetica"><em>Edit Provider </em></font></H2></td>
            </tr>
            <tr>
              <td><table border="0">
                  <tbody>
                    <tr>
                  
                      <td >Provider Name:</td>
                      <td ><input id="provider_name" type="text" name="" value="${provider}"/></td>
   
                    </tr>
                    <tr>
              
                      <td>Phone number:</td>
                      <td><input id="phone_number" type="text" name="" value="${phone}" /></td>
            
                    </tr>
                    <tr>
                   
                      <td>Address:</td>
                      <td><input id="address" type="text" name="" value="${address}" /></td>
                     
                    </tr>
                    <tr>
                  
                      <td>Web site:</td>
                      <td><input id="website" type="text" name="" readonly="readonly" value="${web}"/></td>
                     
                    </tr>
                     <tr>
                  
                      <td>Email:</td>
                      <td><input id="email" type="text" name="" readonly="readonly" value="${email}"/></td>
                     
                    </tr>
                    <tr>
                   
                      <td valign="top">Description:</td>
                      <td valign="top"><textarea id="description"  name="" cols="40" readonly="readonly">${note}
                        </textarea></td>
                 
                    </tr>
                    
                  </tbody>
                </table></td>
            </tr>
            <tr>
              <td><br>
                <input type="button"  id="editProvider" value="Submit Change" />
                <input type="button"   id="cancelProvider" value="Cancel"/></td>
            </tr>
          </tbody>
        </table>
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