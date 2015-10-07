<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
    <head>
        <title>Список студентов</title>
    </head>
 
    <body>
        <form action="<c:url value=""/>" method="POST">
            <table>
                <tr>
                    <td><input type="submit" name="getList" value="Вернуться"/></td>
                </tr>
            </table>
 
            <p/>
            <b>Список вопросов:</b>
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
                    <td><input type="submit" value="Edit" name="Редактировать вопрос"/></td>
                    <td><input type="submit" value="Otvetes" name="редактировать таблицу ответов"/></td>
                </tr>
            </table>
            
        </form>
    </body>
</html>