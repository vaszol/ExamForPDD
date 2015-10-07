<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Результат теста</title>
	<style media="all" type="text/css">@import "css/all.css";</style>
	
	<style media='print' type='text/css'>
		#navbar-iframe {display: none; height: 0px; visibility: hidden;}
		.noprint {display: none;}		
		body {background:#FFF; color:#000;}
		a {text-decoration: underline; color:#00F;}
	}
	</style>
</head>
<body>     
	<OBJECT ID="IEControl" WIDTH==0 HEIGHT=0 CLASSID="clsid:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>
	<div class="result">	
		<p><h4>ФИО: <input type="submit" name="update" value="${result.studentName}"/></h4></p>   
		    	    	 
        <p><h4>Билет№ ${result.biletId} , совершено ошибок: ${result.error}</h4></p>
		<!-- 
		<p><h4>Время начала: <c:out value="${startTime}"/></h4></p>
		<p><h4>Время завершения: <c:out value="${stopTime}"/></h4></p>
		 -->
        <p><h4>Время решения: <c:out value="${resultTime}"/></h4></p>
        <p><h4>результат:</h4></p>
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
	</br>
	</br>
	<span class="noprint">
		<!-- <input type="button" value="Печать" onclick="print()"></input> -->
		
		<button type="button"value="Вернуться к управлению" onclick="history.back();">
				    <img src="image/GoBack.png" alt="" style="vertical-align:middle"> 
				    Вернуться к управлению
				</button>
		
		<button type="button"value="Печать" onclick="print()">
				    <img src="image/easyprint.png" alt="" style="vertical-align:middle"> 
				    печать результата
				</button>
				
		
	</span>
	
</body>
</html>
