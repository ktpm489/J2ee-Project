<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login Page</title>
<link rel="stylesheet" href="<c:url value="./resources/css/style.css" /> " type="text/css"/>
<script src="./resources/js/jquery-1.9.1.js"></script>
<script src="./resources/js/login.js"></script>
</head>
<body>
<div id = "container">
  <div id ="menu">
<ul id= "menu_list">
   <!--   <li> <a class="menu_item" href="http://localhost:8080/inventory/listStaffPage" > Home</a> </li>-->  
      <li> <a class="menu_item" href="http://localhost:8080/inventory/contactTeam" > Contact</a> </li>
      <li> <a class="menu_item" href="http://localhost:8080/inventory/aboutTeam" > About</a> </li>
    </ul>
  </ul>
  
   </div>
  <div id ="header"> </div>
  <div id ="main"> 
  <div class="login_container">
		 <div class="login-card">
    <h1>Log-in</h1><br>
  <form>
    <input type="text" id="user" placeholder="Username">
    <input type="password" id="pass" placeholder="Password">
    <input type="button"  id="login" value="Login">
  </form>
    
 <!--   <div class="login-help">
    <a href="#">Register</a> • <a href="#">Forgot Password</a>
  </div>-->
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
