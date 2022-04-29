<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h2{color: red;}
	span{font-weight:bold; font-size:13px;}
	#name{color:orange;}
	#age{color:yellow;}
	#city{color:green;}
	#height{color:blue;}
	#gender{color:navy;}
	li{color:purple}
</style>
</head>
<body>
	<% 
		//이 구문을 스크립틀릿 이라고 해서 html 문서 내에 자바 코드를 쓸 수 있는 영역
		//현재 이 jsp에서 필요로 하는 데이터들 뽑아낼 것(수하물 찾기)
		//=>request의 attribute 영역으로부터 뽑아내기
		//request.getAttribute("키") : Object (그에 해당하는 value 값)
		//Object 형식에서 내가 받고자하는 자료형으로 강제형변환 해서 변수에 담아두면
		//이 jsp 페이지 내에서는 해당 변수를 자유자재로 사용 가능하다
		String name=(String)request.getAttribute("name");
		int age=(int)request.getAttribute("age");
		String city=(String)request.getAttribute("city");
		double height=(double)request.getAttribute("height");
		String gender=(String)request.getAttribute("gender");
		String[] foods=(String[])request.getAttribute("foods");
		
	%>
	<h2>개인정보 응답화면</h2>
	
	<span id="name"><%= name  %></span>님은
	<span id="age"><%= age %></span>살이며,
	<span id="city"><%= city %></span>에 살며,
	<span id="height"><%= height %></span> 이고,
	<% if(gender==null){%>
		선택을 안했습니다. <br>
	<% }else{ %>
		<% if(gender.equals("M")) {%>
		<span id="gender">남자</span>입니다. <br>
		<%} else{ %>
		<span id="gender">여자</span>입니다. <br>
		<%} %>
	<%} %>
	좋아하는 음식은
	<% if(foods==null){ %>
		없습니다.
	<%}else{ %>
		<ul>
		<% for(int i=0; i<foods.length; i++){ %>
			<li><%=foods[i] %></li>
		<%} %>
		</ul>
	<%} %>
	
</body>
</html>