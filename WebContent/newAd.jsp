<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="source.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="UTF-8">
<title>Софийски Дупки</title>
<head>
 <link rel="stylesheet" href="s.css">
    <link rel="stylesheet" href="reg.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script type="text/javascript" src="myScript.js"> </script>
	<script type="text/javascript" src="validations.js"> </script>
	<script type="text/javascript" src="question.js"> </script>
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
</head>
<body>
	<%
		String user = null;
		String profile = null;
		if (session.getAttribute("username") == null) {
			response.sendRedirect("login.html");
		} else {
			user = (String) session.getAttribute("username");
			if (user.equals("admin")) {
				profile = "AdministratorProfile.jsp";
			} else {
				profile = "Profile.jsp";
			}
		}
	%>
	<div class="page-wrapper">

		<div class="logo">
			<table id="Logo_table" width="1331" height="330" border="0"
				cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3"><img src="images/logo_01.gif" width="1445"
						height="124" alt=""></td>
				</tr>
				<tr>
					<td rowspan="2"><img src="images/logo_02.gif" width="505"
						height="206" alt=""></td>
					<td><a href="indexUser.jsp"><img src="images/Home.gif"
							width="445" height="85" border="0" alt="index page link"></a></td>
					<td rowspan="2"><img src="images/logo_04.gif" width="495"
						height="206" alt=""></td>
				</tr>
				<tr>
					<td><img src="images/logo_05.gif" width="445" height="121"
						alt=""></td>
				</tr>
			</table>
			<ul class="topnav" id="myTopnav">
				<li><a href="indexUser.jsp">Начало</a></li>
				<li><a href="kvartiri.jsp">Квартири</a></li>
				<li><a href="agencies.jsp">Агенции</a></li>
				<li><a class="active" href="newAd.jsp">Добави обява</a></li>
				<li class="logoutUser"><a href="index.jsp">Изход</a></li>
				<li class="welcomeUser"><a href="<%=profile%>">Здравей, <%=user%>!
				</a></li>
				<li class="icon"><a href="javascript:void(0);"
					style="font-size: 15px;" onclick="responsiveTopnav()">☰</a></li>
			</ul>
	</div>
  <div class="register">
  <div class="register-triangle"></div>
  
  <h2 class="register-header">Добави обява</h2>

  <form class="register-container" action="NewAdServlet"  method="POST" onsubmit="return validateForm()">
  <input type="hidden" name="username" value="<%=user%>">  
    <div class="Note"> Име:</div>
    <p><input type="text" name ="name" id="name" placeholder="Име на обявата"></p>
    
    <div class="Note"> Адрес:</div>
    <p class="user"><input type="text" name="address" id="address" placeholder="Адрес"></p>
    
    <div class="Note"> Описание:</div>
    <p><input type="text" name="description" id="description" placeholder="Вашето описание"></p>
    
    <div class="Note"> Цена на месец:</div>
	<p><input type="text" name="price" id="price" placeholder="лв."></p>
	
    <div class="Note"> Квартал:</div>
	<p><input type="text" name="neighborhood" id= "neighborhood" placeholder="Квартал"></p>
	
    <div class="Note"> Тип:</div>
	<p><input type="text" name="type" id="type" placeholder="Нова"></p>
    
    <div class="Note"> Квадратура:</div>
	<p><input type="text" name="squareMeters" id="squareMeters" placeholder="кв.м."></p>
    
    <div class="Note"> Брой стаи:</div>
	<p><input type="text" name="rooms" id="rooms" placeholder="Брой стаи"></p>
    
	<a href="indexUser.jsp"> <input class="reg" type="submit" value="Публикувай"></a>
	
 </form>
</div>



</body>
</html>