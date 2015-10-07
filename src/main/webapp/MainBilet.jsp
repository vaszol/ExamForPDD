<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
    <head>
        <title>Список студентов</title>
    </head>
 
    <body>
        <form action="<c:url value="/bilets"/>" method="POST">
            <table>
                <tr>                    
                    <td>Выбран:
                        <select name="biletId">
                            <c:forEach var="bilet" items="${form.bilets}">
                                <c:choose>
                                    <c:when test="${bilet.biletId==form.biletId}">
                                        <option value="${bilet.biletId}" selected><c:out value="${bilet.nameBilet}"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${bilet.biletId}"><c:out value="${bilet.nameBilet}"/></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="submit" name="getList" value="Обновить"/></td>
                </tr>
            </table>
 
            <p/>
            <b>Список вопросов в выбранном билете:</b>
            <br/>
            <table>
                <tr>
                    <th></th>
                    <th>№</th>
                    <th>Вопрос</th>
                    <th>картинка</th>
                    <th>тема</th>
                </tr>
                <c:forEach var="vopros" items="${form.voproses}">
                <tr>
                    <td><input type="radio" name="voprosId" value="${vopros.voprosId}"></td>
                    <td><c:out value="${vopros.vopname}"/></td>
                    <td><c:out value="${vopros.voprosText}"/></td>
                    <td><c:out value="${vopros.picture}"/></td>
                    <td><c:out value="${vopros.theme}"/></td>
                </tr>
                </c:forEach>
            </table>
                            
            <table>
                <tr>
                    <td><input type="submit" value="показать все вопросы" name="Voproses"/></td> 
                    <td><input type="submit" value="Редактировать вопрос" name="Edit"/></td>
                    <td><input type="submit" value="редактировать таблицу ответов" name="Otvetes"/></td> 
                </tr>
            </table>
            
        </form>
    </body>
</html>