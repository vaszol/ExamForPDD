<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
    <head>
        <title>Редактор вопроса</title>
    </head>
 
    <body>
        <form action="<c:url value="/vopros"/>" method="POST">    
        	
        	<input type="hidden" name="voprosId" value="${vopros.voprosId}"/>
        	<input type="hidden" name="biletId" value="${vopros.biletId}"/>
        	<input type="hidden" name="vopname" value="${vopros.vopname}"/>
        	       
        	 <table> 
	        	 <tr>
	        	 	<td>
	        	 	<table>                
                <tr>                  
                    <td>номер билета:</td>
                    <td>${vopros.biletId}</td>
                    <td>Вопрос № ${vopros.vopname}</td>
                </tr>                
                <tr>
                    <td>Вопрос:</td>
                    <td></td>
                    <td><input type="text" name="voprosText" value="${vopros.voprosText}"/></td>
                </tr>
                <tr>
                    <td>картинка:</td><td></td><td><input type="text" name="picture" value="${vopros.picture}"/></td>
                </tr>
                <tr>
                    <td>из темы:</td><td></td><td><input type="text" name="theme" value="${vopros.theme}"/></td>
                </tr>       
                
                 <tr>
                    <td>ответ 1:</td>
                    <td><input type="radio" name="set" value="otv1" checked/></td>
                    <td><input type="text" name="otvetText1" value="${vopros.otvetText1}" size="100"/></td>
                </tr>
                <tr>
                    <td>ответ 2:</td>
                    <td><input type="radio" name="set" value="otv2" checked/></td>
                    <td><input type="text" name="otvetText2" value="${vopros.otvetText2}" size="100"/></td>
                </tr>
                <tr>
                    <td>ответ 3:</td>
                    <td><input type="radio" name="set" value="otv3" checked/></td>
                    <td><input type="text" name="otvetText3" value="${vopros.otvetText3}" size="100"/></td>
                </tr>
                <tr>
                    <td>ответ 4:</td>
                    <td><input type="radio" name="set" value="otv4" checked/></td>
                    <td><input type="text" name="otvetText4" value="${vopros.otvetText4}" size="100"/></td>
                </tr>
                <tr>
                    <td>ответ 5:</td>
                    <td><input type="radio" name="set" value="otv5" checked/></td>
                    <td><input type="text" name="otvetText5" value="${vopros.otvetText5}" size="100"/></td>
                </tr>
                <tr>
                    <td>ответ 6:</td>
                    <td><input type="radio" name="set" value="otv6" checked/></td>
                    <td><input type="text" name="otvetText6" value="${vopros.otvetText6}" size="100"/></td>
                </tr>                        
                      
            </table>
	        	 	</td>
	        	 	<td>
	        	 	
	        	 	</td>	        	 	
	        	 </tr>
        	 </table>
            
            
            
            
            <table>
                <tr>
                    <td><input type="submit" value="OK" name="OK"/></td>
                    <td><input type="submit" value="Cancel" name="Cancel"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>