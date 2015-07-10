<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>About Team </title>
<link rel="stylesheet"
	href="<c:url value="./resources/css/bootstrap.css" /> " type="text/css">
<link rel="stylesheet"
	href="<c:url value="./resources/css/style_about.css" /> " type="text/css" />
<style>
body {
	background: #32A3F6;
	font-family: 'Roboto', sans-serif;
}
</style>
</head>

<body>
<div id ="menu">
 <ul id= "menu_list">
      <li> <a class="menu_item" href="http://localhost:8080/inventory/listStaffPage" > Home</a> </li>
      <li> <a class="menu_item" href="http://localhost:8080/inventory/contactTeam" > Contact</a> </li>
      <li> <a class="menu_item" href="http://localhost:8080/inventory/aboutTeam" > About</a> </li>
    </ul>
</div>
<div id="general">
  <div id="content">
  <hr />
  <h2>Liên hệ trực tiếp </h2>
       <p><span>Nếu bạn có vấn đề gì cần liên hệ trực tiếp với tôi, vui lòng xem thông tin bên dưới:</span> </p>
       <p>Điện Thoại:0166 2757 342 (Gặp Quang)</p>
       <p>Nick yahoo: onlyyou_talentboy@yahoo.com  </p>
       <p>Email:<a href="mailto:onlymepro@gmail.com"> onlymepro@gmail.com</a>  </p>
</div>
</body>
</html>
