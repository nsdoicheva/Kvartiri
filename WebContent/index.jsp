<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="source.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="UTF-8">
    <title>Софийски Дупки</title>
    <link rel="stylesheet" href="s.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script type="text/javascript" src="myScript.js"> </script>
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
	<link rel="icon" href="https://lh3.googleusercontent.com/o5tY_XTksE10Wlfq-JwZ6ZMPfs0ta72bXqeWSnBqz4YfgOrt683_IyDppJcmeRX4N4M=w170">
</head>
<body>
<%
 session.invalidate();
%>
<div class="page-wrapper">

<div class ="logo"> 
<table id="Logo_table" width="1331" height="330" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="3"><img src="images/logo_01.gif" width="1445" height="124" alt=""></td>
	</tr>
	<tr>
        	<td rowspan="2"><img src="images/logo_02.gif" width="505" height="206" alt=""></td>
		<td><a href="indexUser.jsp"><img src="images/Home.gif" width="445" height="85" border="0" alt="index page link"></a></td>
		<td rowspan="2"><img src="images/logo_04.gif" width="495" height="206" alt=""></td>
	</tr>
	<tr>
		<td><img src="images/logo_05.gif" width="445" height="121" alt=""></td>
	</tr>
</table>
<ul class="topnav" id="myTopnav">
  <li><a class="active" href="indexUser.jsp">Начало</a></li>
  <li><a href="kvartiri.jsp">Квартири</a></li>
  <li><a href="agencies.jsp">Агенции</a></li>
  <li><a href="newAd.jsp">Добави обява</a></li>
  <li class="login"><a href="login.html">Вход</a>
</li>
  <li class="icon">
    <a href="javascript:void(0);" style="font-size:15px;" onclick="responsiveTopnav()">☰</a>
  </li>
</ul>

</div>

<div class="content">

</div>

<!--
<aside> 
<div class = "search">
<input type="text" name="search" placeholder="Search..">
</div>
</aside>
-->





</div>



</body>
</html>