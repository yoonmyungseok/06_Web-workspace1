<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    //5)필요한 데이터를 뽑기(request의 attribute 영역으로부터)
    //request.getAttribute("키값"): Object(타입에 상관없는 밸류값)
    //=>다형성에 의해서 Down Casting 해줘야 함
    String userName=(String)request.getAttribute("userName");
    String phone=(String)request.getAttribute("phone");
    String address=(String)request.getAttribute("address");
    String message=(String)request.getAttribute("message");
    String pizza=(String)request.getAttribute("pizza");
    String[] toppings=(String[])request.getAttribute("toppings");
    String[] sides=(String[])request.getAttribute("sides");
    String payment=(String)request.getAttribute("payment");
    int price=(int)request.getAttribute("price");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%--6) 표현식을 이용하여 응답화면 완성시키기 --%>
    <h1>피자 결제 페이지</h1>
    <h2>주문 내역</h2>
    <h4>[주문자 정보]</h4>
    <ul>
        <li>성함: <%= userName %></li>
        <li>전화번호: <%=phone %></li>
        <li>주소: <%=address %></li>
        <%if(message.equals("")) {%>
        <li>요청사항: 없음</li>
        <%}else{ %>
        <li>요청사항: <%=message %></li>
        <%} %>
    </ul>
    <br>
    <h4>[주문 정보]</h4>
    <ul>
        <li>피자: <%=pizza %></li>
        <%if(toppings==null){ %>
        <li>토핑: 선택안함</li>
        <%}else{ %>
        <li>토핑: <%=String.join(",",toppings) %></li>
        <%} %>
        <%if(sides==null){ %>
        <li>사이드: 선택안함</li>
        <%}else{ %>
        <li>사이드: <%=String.join(",",sides) %></li>
        <%} %>
        <%if(payment.equals("cash")) {%>
        <li>결제방식: 현금결제</li>
        <%}else{ %>
        <li>결제방식: 카드결제</li>
        <%} %>
    </ul>
    <br>
    <h3>위와 같이 주문하셨습니다</h3>
    <h2>총 가격: <%=price %></h2>
</body>
</html>