<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    h1{color:red;}
</style>
</head>
<body>
    <%
    //오늘 날짜 구하기: Date 객체 기본생성자로 생성
    Date date=new Date();
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy년 MM월 dd일");
    String today=sdf.format(date);
    %>
    <h4><%= today %></h4>
    
</body>
</html>