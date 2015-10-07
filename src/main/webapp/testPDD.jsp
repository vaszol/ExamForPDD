<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
    <head>
        <title>ПДД</title>
        <style media="all" type="text/css">@import "css/all.css";</style>
        <script type="text/javascript">
<!--

function validate_form ( )
{
	valid = true;

        if ( document.set_variant.variant.value == "0" )
        {
                alert ( "Не выбран ни один вариант! \n Выбери вариант ответа - нажми на текст с ответом!" );
                valid = false;
        }

        return valid;
}

//-->
</script>
    </head> 
    <body>
        <form name="set_variant" action="<c:url value="/TestPDD"/>" method="POST" onsubmit="return validate_form ( );">        
        	<input type="hidden" name="voprosId" value="${test.voprosId}"/>
        	<input type="hidden" name="biletId" value="${test.biletId}"/>
        	<input type="hidden" name="vopname" value="${test.vopname}"/>   
        	<div>      	
        		
        		
        		<div><img class="image" alt="нет картинки" src="image/${test.picture}"></div>        		
        		<div class="voptext" onmousedown="return false" onselectstart="return false"><h2>${test.voprosText}</h2></div>
        		<div class="variant" onmousedown="return false" onselectstart="return false">
        		 <%-- 
					<p><input type="radio" name="variant" value="1" id="labeled_1"/>
						<label for="labeled_1">${test.otvetText1}</label></p>
					<p><input type="radio" name="variant" value="2" id="labeled_2"/>
						<label for="labeled_2">${test.otvetText2}</label></p>
					<p><input type="radio" name="variant" value="3" id="labeled_3"/>
						<label for="labeled_3">${test.otvetText3}</label></p>
					<p><input type="radio" name="variant" value="4" id="labeled_4"/>
						<label for="labeled_4">${test.otvetText4}</label></p>
					<p><input type="radio" name="variant" value="5" id="labeled_5"/>
						<label for="labeled_5">${test.otvetText5}</label></p>
					<p><input type="radio" name="variant" value="6" id="labeled_6"/>
						<label for="labeled_6">${test.otvetText6}</label></p>
					 --%>
					<c:if test="${test.otvetText1!=''}">
						<p><input type="radio" name="variant" value="1" id="labeled_1"/>
						<label for="labeled_1">${test.otvetText1}</label></p>
                    </c:if>
					<c:if test="${test.otvetText2!=''}">
						<p><input type="radio" name="variant" value="2" id="labeled_2"/>
						<label for="labeled_2">${test.otvetText2}</label></p>
                    </c:if>
                    <c:if test="${test.otvetText3!=''}">
						<p><input type="radio" name="variant" value="3" id="labeled_3"/>
						<label for="labeled_3">${test.otvetText3}</label></p>
                    </c:if>
                    <c:if test="${test.otvetText4!=''}">
						<p><input type="radio" name="variant" value="4" id="labeled_4"/>
						<label for="labeled_4">${test.otvetText4}</label></p>
                    </c:if>
                    <c:if test="${test.otvetText5!=''}">
						<p><input type="radio" name="variant" value="5" id="labeled_5"/>
						<label for="labeled_5">${test.otvetText5}</label></p>
                    </c:if>
					<c:if test="${test.otvetText6!=''}">
						<p><input type="radio" name="variant" value="6" id="labeled_6"/>
						<label for="labeled_6">${test.otvetText6}</label></p>
                    </c:if>
					<input type="radio" name="variant" value="0" checked="checked" hidden="true">										
        		</div>
        		</br>
        		<div class="confirm"><input type="submit" value="подтвердить" name="OK"/></div>
        		<br/>
        		<br/>
        		<br/>
        	</div>
        </form>
        </br>
        <p hidden="true">Билет№ ${test.biletId} , вопросId: ${test.voprosId} , совершено ошибок: ${test.error} , вопрос: ${test.vopname}</p>
    </body>
</html>