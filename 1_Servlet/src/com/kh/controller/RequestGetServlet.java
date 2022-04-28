package com.kh.controller; //패키지 선언부(나의 위치를 알려줌)

//import 선언부(다른 클래스들을 가져다 쓰겠다)
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 */
@WebServlet("/test1.do") // 어노테이션 (현재 url 맵핑 주소를 알려줌)
public class RequestGetServlet extends HttpServlet { //RequestGetServlet 클래스 영역 시작
	
	//필드부(필드들이 모여있음)
	private static final long serialVersionUID = 1L; //상수필드
	
	//생성자부(생성자들이 모여있음)
	//생성자특: 클래스명이랑 똑같음
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() { //매개변수가 없는 기본생성자
        super();
        // TODO Auto-generated constructor stub
    }
    
    //메소드부(메소드들이 모여있음)
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //doGet 메소드 영역 시작
		//System.out.println("잘 실행되나???");
		/*
		 * GET 방식으로 요쳥을 했다면 해당 이 doGet 메소드가 내부적으로 호출됨
		 * 
		 * doGet 메소드의 
		 * 첫번째 매개변수인 HttpServletRequest 타입의 request에는
		 * 사용자가 요청시 전달한 내용물들이 담겨서 전달됨(사용자가 입력한 값들, 요청전송방식, 요청한 사용자의 ip 주소 등)
		 * 
		 * 두번째 매개변수인 HttpServletResponse 타입 response에는
		 * 이 메소드에서 사용자의 요청 처리 후 응답을 할 때 필요한 객체가 만들어져서 전달됨(필요한 메소드만 잘 호출해서 씀)
		 * 
		 * 우선, 요청을 처리하기 위해 요청시 전달된 값을 뽑는다(==사용자가 뷰에서 입력한 값들)
		 * request로 부터 뽑을 예정(request의 parameter 영역 안에 존재)
		 * =>key-value 세트로 담겨있음 (name속성값-value값)
		 * 
		 * 따라서 request의 parameter 영역으로부터 전달된 데이터를 뽑는 메소드를 호출해야 함( 총 2가지)
		 * 1. request.getParameter("키값"); : String타입(그에 해당하는 value값 리턴)
		 * =>무조건 문자열 형으로 반환됨
		 * 
		 * 2. request.getParameterValues("키값"); : String[] 타입 (그에 해당되는 value 값들 리턴)
		 * =>하나의 key값으로 여러 개의 value 값들을 받을 경우 문자열 배열 형태로 반환됨 (checkbox)
		 * 
		 */
		String name=request.getParameter("name"); // "이름" / " "
		String gender=request.getParameter("gender"); //"M" / "F" / NULL
		//라디오버튼의 경우 체크된 것이 하나도 없을 경우 null이 넘어옴
		
		int age=Integer.parseInt(request.getParameter("age")); // "10" -> 10 
		// Wrapper로 파싱=>주의사항 : 숫자타입이 아닌값을 가져오면 NumberFormatException 오류가 발생할 수 있음
		String city=request.getParameter("city"); // "서울시" 
		double height = Double.parseDouble(request.getParameter("height")); // "161" -> 161.0
		
		//체크박스와 같은 복수개의 정보를 받을 경우에는 배열로 받아야 함
		String[] foods=request.getParameterValues("food"); // ["한식", "중식"] / null
		//체크박스의 경우 체크된 것이 하나도 없을 경우 null이 넘어옴
		
		//출력
		System.out.println("name: "+name);
		System.out.println("gender: "+gender);
		System.out.println("age: "+age);
		System.out.println("city: "+city);
		System.out.println("height: "+height);
		if(foods==null) {
			System.out.println("foods: 없음");
		}else {
			//배열에 있는 모든 값들을 구분자를 이용해서 하나의 문자열로 연이어주는 메소드
			//String.join("구분자", 배열명);
			System.out.println("foods: "+String.join(", " , foods));
			/*
			System.out.print("foods: ");
			for(String i: foods) {
				System.out.println(i);
			}
			*/
		}
		//이 뽑아낸 것들을 가지고 요청 처리를 해야함 (DB와 상호작용)
		//MVC 패턴 상에서의 보통의 흐름
		//View->Controller (==Servlet) -> Service -> DAO -> DB
		//		<-					   <-			<-	 <-
		//int result=new MemberService().inserMember(name, gender, age, city, height, foods);
		
		/*
		 * if(result>0){
		 * 	성공화면 지정
		 * }else{
		 * 	실패화면 지정
		 * }
		 */
		//위와 같은 요청 처리를 다 했다는 가정 하에 사용자가 보게 될 "응답페이지"를 만들어서 전달해보자
		
		/*
		 * 요청에 대한 응답 페이지를 반환하는 방법 (2가지)
		 * 1. 자바를 이용하는 방법: Java 코드 내에 html을 작성하는 방법
		 * 2. JSP를 이용하는 방법: html 코드 내에 java를 작성하는 방법
		 */
		
		/*
		 * 1. 자바를 이용하는 방법
		 * 장점: Java 코드 내에 html 태그들을 작성하기 때문에 반복문이나 조건문, 자바에서 제공하는 유용한 메소드들을 활용할 수 있다
		 * 단점: 코드가 복잡해지고 길어짐
		 * 		혹시라도 후에 응답페이지 내용이 바뀌게 될 경우 자바 코드를 수정해야 한다
		 * 		(수정된 내용을 다시 반영시키고자 한다면 서버를 재실행 시켜야 한다)
		 */
		
		//response 객체를 통해 사용자에게 html (응답화면)을 전달
		//1) 이제부터 내가 출력할 내용물은 문서형태의 html 형식이고
		//	인코딩 방식은 utf-8이라는 것을 지정
		response.setContentType("text/html; charset=utf-8");
		
		//2) 응답하고자 하는 사용자(요청을 했었던 사용자)와의 스트림(통로)을 생성
		PrintWriter out=response.getWriter();
		
		//3) 생성된 스트림을 통해서 응답 html 구문을 한줄씩 출력
		//println() / printf() / print()
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println("h2{color:red;}");
		out.println("#name{color: orange;}");
		out.println("#age{color: yellow;}");
		out.println("#city{color: green;}");
		out.println("#height{color:blue;}");
		out.println("#gender{color:navy;}");
		out.println("li{color:purple;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인정보 응답 화면</h2>");
		out.printf("<span id='name'>%s</span> 님은, ", name);
		out.printf("<span id='age'>%d</span> 살이며, ", age);
		out.printf("<span id='city'>%s</span>에 살며, ", city);
		out.printf("<span id='height'>%.1f</span>cm이고, ", height);
		out.print("성별은 ");
			if(gender==null) {
				out.print("선택을 안했습니다.<br>");
			}else{
				if(gender.equals("M")) {
					out.print("<span id='gender'>남자</span>");
				}else {
					out.print("<span id='gender'>여자</span>");
				}
			}
		out.print("좋아하는 음식은 ");
		if(foods==null) {
			out.print("없습니다");
		}else {
			out.println("<ul>");
			for(int i=0; i<foods.length; i++) {
				out.printf("<li>%s</li>", foods[i]);
			}
			out.println("</ul>");
		}
		out.println("</body>");
		out.println("</html>");
		

	}//doGet 메소드 영역 끝

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //doPost 메소드 영역 시작
		// TODO Auto-generated method stub
		doGet(request, response);
	}//doPost 메소드 영역 끝

}//RequestGetServlet 클래스 영역 끝
