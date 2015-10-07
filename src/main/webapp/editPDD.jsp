<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Введите заголовок</title>
	<style type="text/css">
	<!--
		@page { margin: 2cm }
		p { margin-bottom: 0.25cm; line-height: 120% }
		td p { margin-bottom: 0cm }
	-->
	</style>

</head>
<body lang="ru-RU" dir="ltr">

<table width="80%" cellpadding="4" cellspacing="0">
	<col width="154*">
	<col width="51*">
	<col width="51*">
	<tr valign="top">
		<td width="480px" height="180px"
		style="border-top: 1px solid #000000; border-bottom: 1px solid #000000;
		border-left: 1px solid #000000; border-right: none ">
			<img alt="Ответ без картинки" src="image/zG1ewLM.jpg">
			<input type="file" name="image" accept="image/*,image/jpeg" value="picture">
			</p>
		</td>
		<td rowspan="8" width="20%" 
			style="border-top: 1px solid #000000; border-bottom: 1px solid #000000;
			border-left: 1px solid #000000; border-right: none;
			padding-top: 0.1cm; padding-bottom: 0.1cm;
			padding-left: 0.1cm; padding-right: 0cm">
			<p>Билеты</p>
			<div style="height: 450px; width: 50px; overflow-y:scroll">
				<div>
					<button type="button" value="b1">1</button>
					<button type="button" value="b2">2</button>
					<button type="button" value="b3">3</button>
					<button type="button" value="b4">4</button>
					<button type="button" value="b5">5</button>
					<button type="button" value="b6">6</button>
					<button type="button" value="b7">7</button>
					<button type="button" value="b8">8</button>
					<button type="button" value="b9">9</button>
					<button type="button" value="b10">10</button>
					<button type="button" value="b11">11</button>
					<button type="button" value="b12">12</button>
					<button type="button" value="b13">13</button>
					<button type="button" value="b14">14</button>
					<button type="button" value="b15">15</button>
					<button type="button" value="b16">16</button>
					<button type="button" value="b17">17</button>
					<button type="button" value="b18">18</button>
					<button type="button" value="b19">19</button>
					<button type="button" value="b20">20</button>
					<button type="button" value="b21">21</button>
					<button type="button" value="b22">22</button>
					<button type="button" value="b23">23</button>
					<button type="button" value="b24">24</button>
					<button type="button" value="b25">25</button>
					<button type="button" value="b26">26</button>
					<button type="button" value="b27">27</button>
					<button type="button" value="b28">28</button>
					<button type="button" value="b29">29</button>
					<button type="button" value="b30">30</button>
					<button type="button" value="b31">31</button>
					<button type="button" value="b32">32</button>
					<button type="button" value="b33">33</button>
					<button type="button" value="b34">34</button>
					<button type="button" value="b35">35</button>
					<button type="button" value="b36">36</button>
					<button type="button" value="b37">37</button>
					<button type="button" value="b38">38</button>
					<button type="button" value="b39">39</button>
					<button type="button" value="b40">40</button>
				</div>
			</div>			
		</td>
		<td rowspan="8" width="20%" 
			style="border: 1px solid #000000; padding: 0.1cm">
			<p>вопросы</p>
			<div style="height: 450px; width: 50px; overflow-y:scroll">
				<div>
					<button type="button" value="v1">1</button>
					<button type="button" value="v2">2</button>
					<button type="button" value="v3">3</button>
					<button type="button" value="v4">4</button>
					<button type="button" value="v5">5</button>
					<button type="button" value="v6">6</button>
					<button type="button" value="v7">7</button>
					<button type="button" value="v8">8</button>
					<button type="button" value="v9">9</button>
					<button type="button" value="v10">10</button>
					<button type="button" value="v11">11</button>
					<button type="button" value="v12">12</button>
					<button type="button" value="v13">13</button>
					<button type="button" value="v14">14</button>
					<button type="button" value="v15">15</button>
					<button type="button" value="v16">16</button>
					<button type="button" value="v17">17</button>
					<button type="button" value="v18">18</button>
					<button type="button" value="v19">19</button>
					<button type="button" value="v20">20</button>
				</div>
			</div>			
		</td>
	</tr>
	
	<tr valign="top">
		<td width="480px"
			style="border-top: none; 
			border-bottom: 1px solid #000000; border-left: 1px solid #000000; 
			border-right: none; padding-top: 0cm; padding-bottom: 0.1cm; 
			padding-left: 0.1cm; padding-right: 0cm">
			
			<form action="<c:url value="/editPDD"/>" method="POST">			
            <input type="hidden" name="studentId" value="${vopros.voprosId}"/>
            <table>
                <tr>
                    <td>Вопрос:</td>
                    <td></td>
                    <td><input type="text" name="vopros" value="${vopros.vopros}" size="100"/></td>
                </tr>
                <tr><p> </p></tr>
                <tr>
                    <td>ответ 1:</td>
                    <td><input type="radio" name="set" value="0" checked></input></td>
                    <td><input type="text" name="otvet1" value="${vopros.otvet1}" size="100"/></td>
                </tr>
                <tr>
                    <td>ответ 2:</td>
                    <td><input type="radio" name="set" value="0" checked></input></td>
                    <td><input type="text" name="otvet2" value="${vopros.otvet2}" size="100"/></td>
                </tr>
                <tr>
                    <td>ответ 3:</td>
                    <td><input type="radio" name="set" value="0" checked></input></td>
                    <td><input type="text" name="otvet3" value="${vopros.otvet3}" size="100"/></td>
                </tr>
                <tr>
                    <td>ответ 4:</td>
                    <td><input type="radio" name="set" value="0" checked></input></td>
                    <td><input type="text" name="otvet1" value="${vopros.otvet1}" size="100"/></td>
                </tr>
                <tr>
                    <td>ответ 5:</td>
                    <td><input type="radio" name="set" value="0" checked></input></td>
                    <td><input type="text" name="otvet2" value="${vopros.otvet2}" size="100"/></td>
                </tr>
                <tr>
                    <td>ответ 6:</td>
                    <td><input type="radio" name="set" value="0" checked></input></td>
                    <td><input type="text" name="otvet3" value="${vopros.otvet3}" size="100"/></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><input type="submit" value="OK" name="OK"/></td>
                    <td><input type="submit" value="Cancel" name="Cancel"/></td>
                </tr>
            </table>
        </form>
		</td>
	</tr>	
</table>
</body>
</html>



