<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>центр управления экзаменом</title>
	<style media="all" type="text/css">@import "css/all.css";</style>
	<script type="text/javascript" src="javascript.js"></script>
</head>
<body>

    
<div >

<table border="1"
style="background: #A9A9A9;
	 				text-transform: uppercase;
	 				text-shadow: 1px 1px 10px white;
	 				color: black;
  					font-weight: bold;
  					padding: 20px;
  					font-family: Verdana, Arial, Helvetica, sans-serif;
  					font-size: xx-small;">
 <tr>
	  <th width=10% valign=3Dtop>	  	
	  </th>
	  <th width=4% valign=3Dtop >
	  	<b>Дать<br/>команду<br/>№</b>
	  </th>
	  <th width=10% valign=3Dtop>
	  	<b>Статус</b>
	  </th>
	  <th width=8% valign=3Dtop>
	  	<b>результат</b>
	 	<%-- <b>раскрыть</br>результат</b>--%>
	  </th>
	  <th width=20% valign=3Dtop>
	  	ФИО
	  </th>
	  <th width=10% >
	  	Категория
	  </th>	  
	  <th>
	  	Выбрать</br>билет
	  </th>
	  <th width=5%>
	  	<!-- Время</br>старта -->
	  </th>
	  <th width=5%>
	  	<!--  Прошло</br>время -->
	  </th>  
 </tr>
 <tr>
 	<td rowspan="100">
	 	<form action="<c:url value="/managerTest"/>" method="POST">
	 		<input type="submit" name="update" value="Обновить"/>
 		</form>
 	</td>
 	<td colspan="8"/>
 </tr>
 <c:forEach var="ses" items="${managerka.sessions}">
 
 	<form action="<c:url value="/managerTest"/>" method="POST">
		<input type="hidden" name="studentId" value="${ses.session_id}"/>
		<input type="hidden" name="addrIp" value="${ses.addrIp}"/>
		<input type="hidden" name="stat" value="${ses.stat}"/>
	
 <tr style="background: #efefef;
	 				text-transform: uppercase;
	 				text-shadow: 1px 1px 10px white;
	 				color: black;
  					font-weight: bold;
  					padding: 20px;
  					font-family: Verdana, Arial, Helvetica, sans-serif;
  					font-size: xx-small;">
 	
 	<td> 		
 		<c:choose>	
	 		<c:when test="${ses.stat==0}">
	 			<label>включите ${ses.session_num}-й компьютер</label>
	 		</c:when>
	 		<c:when test="${ses.stat==1}">
	 			Остановить?</br>
	 			<input type="submit" name="session_num" value="${ses.session_num}"/>
	 		</c:when>
	 		<c:when test="${ses.stat==2}">
	 			Сбросить параметры?</br>
	 			<input type="submit" name="session_num" value="${ses.session_num}"/>
	 		</c:when>
	 		<c:when test="${ses.stat==3}">
	 			Сбросить параметры?</br>
	 			<input type="submit" name="session_num" value="${ses.session_num}"/>
	 		</c:when>
	 		<c:when test="${ses.stat==4}">
	 			<!-- Старт?</br>
	 			-->
	 			<input hidden="true" type="submit" name="session_num" value="${ses.session_num}"/>
	 			 
	 		</c:when>
 		</c:choose>
 		
 		
 	</td>
 	<td > 	
	 	<c:choose>	
	 		<c:when test="${ses.stat==0}">
	 			<label style="background: #efefef;
	 				text-transform: uppercase;
	 				text-shadow: 1px 1px 10px white;
	 				color: black;
  					font-weight: bold;
  					padding: 20px;
  					font-family: Verdana, Arial, Helvetica, sans-serif;
  					font-size: xx-small;">Не подключен</label>
	 		</c:when>
	 		<c:when test="${ses.stat==1}">
	 			<label style="background: #efff00;
	 				text-transform: uppercase;
	 				text-shadow: 1px 1px 10px white;
	 				color: black;
  					font-weight: bold;
  					padding: 20px;
  					font-family: Verdana, Arial, Helvetica, sans-serif;
  					font-size: xx-small;">Решает..</label>
	 		</c:when>
	 		<c:when test="${ses.stat==2}">
	 			<label style="background: #32CD32;
	 				text-transform: uppercase;
	 				text-shadow: 1px 1px 10px white;
	 				color: black;
  					font-weight: bold;
  					padding: 10px;
  					font-family: Verdana, Arial, Helvetica, sans-serif;
  					font-size: xx-small;">Сдал!</br>
	 			Укажите билет</label>
	 		</c:when>
	 		<c:when test="${ses.stat==3}">
	 			<label 
	 				style="background: #FF4500;
	 				text-transform: uppercase;
	 				text-shadow: 1px 1px 10px white;
	 				color: black;
  					font-weight: bold;
  					padding: 10px;
  					font-family: Verdana, Arial, Helvetica, sans-serif;
  					font-size: xx-small;">Не сдал.</br>
	 			напечатать результат?-></label>
	 		</c:when>
	 		<c:when test="${ses.stat==4}">
	 			<label style="background: #efefef;
	 				text-transform: uppercase;
	 				text-shadow: 1px 1px 10px white;
	 				color: black;
  					font-weight: bold;
  					padding: 20px;
  					font-family: Verdana, Arial, Helvetica, sans-serif;
  					font-size: xx-small;">Укажите билет</label>
	 		</c:when>
 		</c:choose>
 	</td>
 	<td>
 		<!-- <input type="submit" name="showResult" value="↨"/> -->
 		<c:choose>	
	 		<c:when test="${ses.stat==1}">
	 			
	 		</c:when>
	 		<c:when test="${ses.stat==2}">
	 			<button type="submit" name="printResult" value="${ses.addrIp}">
				    <img src="image/easyprint.png" alt="" style="vertical-align:middle"> 
				    печать результата
				</button>
	 		</c:when>
	 		<c:when test="${ses.stat==3}">
	 			<button type="submit" name="printResult" value="${ses.addrIp}">
				    <img src="image/easyprint.png" alt="" style="vertical-align:middle"> 
				    печать результата
				</button>
	 		</c:when>	 
 		</c:choose> 		
 	</td>
 	<td>
 		<!-- введите ФИО студента -->
 		<c:choose>	
	 		<c:when test="${ses.stat==1}">
	 			<label>${ses.FIO}</label>
	 			<input type="text" name="FIO" value="${ses.FIO}" hidden="">
	 		</c:when>	 		
	 		<c:when test="${ses.stat==2}">
	 			<label>${ses.FIO}</label>
	 			<input type="text" name="FIO" value="${ses.FIO}" hidden="">
	 		</c:when>
	 		<c:when test="${ses.stat==3}">
	 			<label>${ses.FIO}</label>
	 			<input type="text" name="FIO" value="${ses.FIO}" hidden="">
	 		</c:when>	 			 
	 		<c:when test="${ses.stat==4}">
	 			Укажите ФИО следующего курсанта и нажмите 'Enter' для старта ${ses.session_num}-го компьютера 
	 			<input type="text" name="FIO" width=95%>
	 			
	 		</c:when>	 		
 		</c:choose> 	
 	</td>
 	<td> 		
 	<%--
 					<c:choose>
                        <c:when test="${ses.cat==0}">
                            <label></label><input type="radio${ses.session_id}" name="category" value="AB" checked/>AB</label>
	 						<label></label><input type="radio${ses.session_id}" name="category" value="CD"/>CD</label>
                        </c:when>
                        <c:when test="${ses.cat==1}">
                            <label></label><input type="radio${ses.session_id}" name="category" value="AB"/>AB</label>
	 						<label></label><input type="radio${ses.session_id}" name="category" value="CD" checked/>CD</label>
                        </c:when>
                    </c:choose>
 	 --%>
 		<h4>AB</h4>
	 	<input type="hidden" name="cat" value="${ses.cat}"/>
 		
 	</td>
 	<td>
 	<c:choose>	
 		<c:when test="${ses.stat!=4}">
	 		<c:forEach var="bilet" items="${managerka.bilets}">
		 		<c:if test="${bilet.biletId==ses.bilet_id}">
		 			<c:out value="${bilet.nameBilet}"/>
		 		</c:if>		 		
			</c:forEach>		 		
	 	</c:when>	
 		<c:when test="${ses.stat==4}">
 		<p>
 			<select name="bilet_id">
            	<c:forEach var="bilet" items="${managerka.bilets}">
                	<c:choose>
						<c:when test="${bilet.biletId==ses.bilet_id}">
                        	<option value="${bilet.biletId}" selected><c:out value="${bilet.nameBilet}"/></option>
                        </c:when>
                        <c:otherwise>
                        	<option value="${bilet.biletId}"><c:out value="${bilet.nameBilet}"/></option>
                    	</c:otherwise>
                	</c:choose>
            	</c:forEach>
        	</select>
		</p>
		</c:when>
		</c:choose>
 	</td>
 	<td>
 		<label>
 			
 		</label>
 	</td>
 	<td>
 		<label>
 		
 		</label>
 	</td>
 </tr>
 <tr><td colspan="8"/></tr>
 </form>
 </c:forEach>
 
 
 
</table>
	<%--
	<form action="<c:url value="/managerTest"/>" method="POST">
		<input type="submit" name="create" value="добавить"/>
	</form>
	 --%>
</div>

</body>

</html>


