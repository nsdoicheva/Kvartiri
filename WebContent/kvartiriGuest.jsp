<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="source.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="UTF-8">
<title>Софийски Дупки</title>
<head>
<link rel="stylesheet" href="s.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript" src="myScript.js">
	
</script>
<link href="https://fonts.googleapis.com/css?family=Lobster"
	rel="stylesheet">

</head>
<body>
	<%
		String user = null;
		String profile = null;
		if (session.getAttribute("username") == null) {
			response.sendRedirect("kvartiriGuest.jsp");
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
				<li><a class="active" href="indexUser.jsp">Начало</a></li>
				<li><a href="kvartiri.jsp">Квартири</a></li>
				<li><a href="agencies.jsp">Агенции</a></li>
				<li><a href="newAd.jsp">Добави обява</a></li>
				<li class="login"><a href="login.html">Вход</a> </a></li>
				<li class="icon"><a href="javascript:void(0);"
					style="font-size: 15px;" onclick="responsiveTopnav()">☰</a></li>
			</ul>

		</div>

		<div class="content">
			<table
				style="margin-top: 90px; border: 1px solid #adbbce box-shadow: 2px 2px 2px #515151; font-size: 20px;">
				<tr>
					<th style="background-color: #bbdeed;">Име:</th>
					<th style="background-color: #bbdeed;">Квартал:</th>
					<th style="background-color: #bbdeed;">Брой стаи:</th>
					<th style="background-color: #bbdeed;">Квадратура:</th>
					<th style="background-color: #bbdeed;">Цена на месец:</th>
				</tr>
				<%
					for (Ad ad : AdDAO.getInstance().getAllAdsFromDB()) {
				%>
				<tr>
					<td style= " height: 70px; color: #04004b; text-align: center; font-size: 18px; background-color: #d3dbe8;"><%=ad.getName()%></td>a
					<td style= " height: 70px; color: #04004b; text-align: center; font-size: 18px; background-color: #d3dbe8;"><%=ad.getNeighborhood()%></td>
					<td style= " height: 70px; color: #04004b; text-align: center; font-size: 18px; background-color: #d3dbe8;"><%=ad.getNumberOfRooms()%></td>
					<td style= " height: 70px; color: #04004b; text-align: center; font-size: 18px; background-color: #d3dbe8;"><%=ad.getSquareMeters()%></td>
					<td style= " height: 70px; color: #04004b; text-align: center; font-size: 18px; background-color: #d3dbe8;"><%=ad.getPricePerMonth()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>
</body>
</html>