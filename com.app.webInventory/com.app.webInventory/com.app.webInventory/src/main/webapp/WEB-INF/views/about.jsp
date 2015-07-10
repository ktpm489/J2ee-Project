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
<div id="general_content">
<h1>Online Inventory Management Solution:</h1>
<hr />
<h4>Introduce:
</h4>
<p>   Quản lý kho trong doanh nghiệp là một viêc rất quan trọng đòi hỏi bộ phận quản lý phải tiến hành nhiều nghiệp vụ phức tạp, các doanh nghiệp không chỉ có mô hình tập trung mà còn tổ chức mô hình kho phân tán trên nhiều địa điểm trong phạm vi vùng, quốc gia, quốc tế. Quản lý thông nhất mô hình này trong kho không phải là việc đơn giản nếu chỉ sử dụng phương pháp kho truyền thống. 
<br>   Hệ thống quản lý kho là một giải pháp tiên tiến. Nó cung cấp cho các doanh nghiệp vừa và nhỏ phần mềm hỗ trợ trong việc quản lý vật tư trong kho, hỗ trợ  cho quản lý kho thực hiện những công việc cơ bản .<br>
</p>
<hr />
<p> Dự án Online Inventory Management Solution được thực hiện bởi nhóm 6 - SE325.F22 
</p>
<h4>Group 06:</h4>
</div>

</div>
<div id="info">

  <ul class="tabs">
    <li>
      <input type="radio" checked name="tabs" id="tab1">
      <label for="tab1">Hoàng Vũ </label>
      <div id="tab-content1" class="tab-content">
        <h3>Information: </h3>
        <div class="tab-left" > <img src="./resources/image/vu.png"  /> </div>
        <div class="tab-right" >
          <p>Full name: Nguyễn Hoàng Vũ<br>i
            Student Code: 11520489<br>
            Mission: The main coder<br>
            <br>
          </p>
        </div>
      </div>
    </li>
    <li>
      <input type="radio" name="tabs" id="tab2">
      <label for="tab2">Nguyễn Quang</label>
      <div id="tab-content2" class="tab-content">
        <h3>Information: </h3>
        <div class="tab-left" > <img src="./resources/image/quang.png"  /> </div>
        <div class="tab-right" >
           <p>Full name: Nguyễn Văn Quang<br>
            Student Code: 11520307<br>
            Mission: Leader, Tester, Web Design<br>
          </p>
        </div>
      </div>
    </li>
    <li>
      <input type="radio" name="tabs" id="tab3">
      <label for="tab3">Hoa Xuân</label>
      <div id="tab-content3" class="tab-content">
         <h3>Information: </h3>
        <div class="tab-left" > <img src="./resources/image/xuan.jpg"  /> </div>
        <div class="tab-right" >
          <p>Full name: Huỳnh Văn Hoa Xuân<br>
            Student Code: 11520496<br>
            Mission: Database design, coder<br>
            <br>
          </p>
        </div>
      </div>
    </li>
  </ul>
</div>
<div id ="footer">
    <div id="footer_text">
      <div align="center"> Online Inventory Management Solution - Products made by Group 06: <br>
        Nguyễn Hoàng Vũ <br>
        Nguyễn Văn Quang <br>
        Huỳnh Văn Hoa Xuân <br>
      </div>
    </div>
  </div>
</body>
</html>
