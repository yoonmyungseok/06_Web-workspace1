<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%--errorPage="error500.jsp"--%>
<%@ page import="java.util.ArrayList, java.util.Date" %>
<%--page 지시어의 경우 왠만해서는 한줄 내에 작성하는 것을 권장하지만 
import할 클래스들이 늘어날 경우에는 import 속성 부분만 따로 빼서 작성하는 것을 더 권장--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>page 지시어</h1>
    <%
        //ArrayList 사용
        //지시어 부분에 import="java.util.ArrayList" 속성을 추가하면 오류가 사라짐
        ArrayList<String> list=new ArrayList<>();
        //add(e): 마지막 인덱스 자리에 e를 추가
        //add(index, e): 내가 원하는 인덱스 자리에 e를 추가
        list.add("Servlet"); //0번 인덱스
        list.add("JSP"); //1번 인덱스

        //Date 클래스 사용=>매개변수 없이 객체 생성시 현재날짜가 생성됨
        //지시어 부분에 import="java.util.Date" 속성을 추가하면 오류가 사라짐
        //=>import할 클래스가 여러개일 경우 ,(콤마)로 이어서 생성
        Date today=new Date();
    %>
	<p>
        리스트의 길이: <%=list.size()%><br>
        0번 인덱스의 값: <%=list.get(0)%><br>
        1번 인덱스의 값: <%=list.get(1)%><br>
        현재 날짜 및 시간: <%=today%><br>
    </p>
    <%=list.get(10)%>
</body>
</html>