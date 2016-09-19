<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="source.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<table border="0">
			<tr>
				<th></th>
				<th>Име</th>
				<th>Квартал</th>
				<th>Брой стаи</th>
				<th>Квадратни метра</th>
				<th>Цена</th>
				<th>Дата на добавяне:</th>
			</tr>
			<% for(Ad ad : Administrator.getInstance().getAllUsersFromDB()){  %>
				<tr>
					<td><%= u.getName() %></td>a
					<td><%= u.getUserName() %></td>
					<td><%= u.getPassword() %></td>
					<td><%= u.getAddress() %></td>
					
						<form action="#" method="POST">
							<input type="submit" value="View profile">
							<input type="hidden" value="<%= u.getName() %>">
						</form>
					</td>
				</tr>
			<% }  %>
		</table>
		
		<img src="http://ia.media-imdb.com/images/M/MV5BNDg1NTU2OWEtM2UzYi00ZWRmLWEwMTktZWNjYWQ1NWM1OThjXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg">
	</body>
</html>