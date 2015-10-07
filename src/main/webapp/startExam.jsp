<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>начало экзамена</title>
	<style media="all" type="text/css">@import "css/all.css";</style>
</head>
<body>
	<div><img class="image2" alt="нет картинки" src="image/goscentr.gif"></div>
	<div class="btn">
	<table border="1">
		<tr>			
			<td>
				<h3>Тест по категории</br>
				 "A" и "B"</h3></br>
				Режим экзамен
			</td>
			<td></td>
			<td width="110">
				<form class="result" action="<c:url value="/startExam"/>" method="POST">		
					<input type="submit" value="Начать тест!" name="start"/>
				</form>
			</td>
		</tr>
	</table>
	</br>
	нажмите кнопку "начать тест", если тест не начался, то попросите администратора дать билет на ваш компьютер.
	</div>
	<!-- 
	<form action="<c:url value="/MainBilet.jsp"/>" method="POST">		
		<input type="submit" value="редактировать билеты" name="Add"/>	
	</form>
	
	<form action="<c:url value="/admin"/>" method="POST">		
		<input type="submit" value="Админка" name="Add"/>	
	</form>
	 -->
</body>
</html>