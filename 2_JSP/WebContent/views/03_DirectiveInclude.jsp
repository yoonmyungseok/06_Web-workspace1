<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>include 지시어</h1>
    <h2>01_ScriptingElement.jsp 파일 include</h2>
    <div style="border:1px solid black; width:800px;height:300px;">
        <%@ include file="01_ScriptingElement.jsp" %>
    </div>
    포함한 jsp 상에 존재하는 변수를 가져다 쓸 수 있음 <br>
    1부터 100까지의 총 합계: <%= sum %>
    <h2>오늘 날짜</h2>
    <%@ include file="datePrint.jsp" %>
    include 지시어는 jsp 상의 모든 것들을 싹 다 가져온다고 보면 됨<br>
    =>자바 코드, html 요소들, css 속성들, 오류 
</body>
</html>