<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>тест окончен!</title>
	<style media="all" type="text/css">@import "css/all.css";</style>
</head>
<body>     
	<div class="result">        	
    	<div><img class="image" alt="нет картинки" src="image/${result.picture}"></div> 
        <p><h3>Билет№ ${result.biletId} , совершено ошибок: ${result.error}</h3></p>       		
        </br>  
              
	</div>
	<div class="result">
	<form class="result" action="<c:url value="/TestOver"/>" method="POST">
			<input type="hidden" name="isDone" value="${result.isDone}"/>
			<table border="6">
			
				<tr>
					<th>Вопрос №</th>
					<c:forEach var="res" items="${result.results}">						
		            	<td width="12"><h4><c:out value="${res.voprosNum}"/></h4></td>
		            </c:forEach>
				</tr>        
		        <tr>
					<th>Правильный</br> ответ</th>
					<c:forEach var="res" items="${result.results}">
		            	<td><c:out value="${res.trueOtvet}"/></td>
		            </c:forEach>
				</tr>          
		        <tr>
					<th>Выбранный</br> Вариант</th>
					<c:forEach var="res" items="${result.results}">
		            	<td><c:out value="${res.otvet}"/></td>
					</c:forEach>
				</tr>
			</table>
		</form>	  
	</div>

<div class="result">    	
    	
              <!-- данную форму убрать после запуска админки -->        
	<form action="<c:url value="/startExam"/>" method="POST">		 
		<div class="btn">
			<p><h4>Тест завершен, ознакомтесь с результатом, после чего сообщите администратору.</h4></p>
			<input type="submit" value="Обновить страницу" name="Add"/>
		</div>
		<input type="hidden" name="SessionName" value="ext3"/>	
	</form>
	</div>


</body>
</html>
