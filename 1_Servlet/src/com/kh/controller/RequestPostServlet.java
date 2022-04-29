package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("잘 실행되나?");
		//요청 시 전달된 값들은 request의 parameter 영역에 담겨있음
		//request.getParameter("키값"): String형의 value 1개 뽑기
		//request.getParameterValues("키값"): String[] 형의 value 여러개 뽑기
		
		//POST 방식으로 요청받은 값들은 기본적으로 인코딩 설정이 ISO-8859-1임
		//=>항상 POST 방식으로 요청받은 값들을 "뽑아내기 이전에" UTF-8 설정을 미리 해줘야 함
		
		request.setCharacterEncoding("utf-8");
		
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
			System.out.println("foods: "+String.join("/ " , foods));

			//요청 처리: Service - DAO - SQL문 실행(DB와 상호작용)
			//int result=new MemberService().insertMember(name, gender, age, city, height, foods);
			//if(result>0): 성공
			//else:실패
		}
		//위의 요청 처리를 다 했다는 가정 하에 사용자가 보게 될 응답페이지를 출력
		
		/*
		 * 응답 페이지 출력하는 방법
		 * 1. 자바를 이용하는 방법: 자바 코드 내에 html 태그를 기술
		 * 2. JSP를 이용하는 방법: html 코드 내에 자바 코드를 기술
		 */
		
		//단, 위임하기 전에 그 응답화면(jsp파일)에서 필요로 하는 데이터들을 수하물로 붙이기
		//=>request 객체에 담기(request의 attribute 영역에 키-밸류 세트로)
		//request.setAttribute("키","밸류");
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height",height);
		request.setAttribute("gender", gender);
		request.setAttribute("foods", foods);
		
		//응답 페이지를 만드는 과정을 JSP에게 위임(떠넘기기)
		//위임 시 필요한 객체: RequestDispatcher
		//1)응답하고자 하는 뷰 (jsp파일)을 선택하면서 객체를 생성
		RequestDispatcher view=request.getRequestDispatcher("views/responsePage.jsp");
		
		//2) 포워딩
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		//System.out.println("잘 실행되나 테스트"); 
		//POST 방식으로 요청시 내부적으로 getPost라는 메소드가 호출됨
		//=>그런데 자동완성으로 doGet 메소드를 호출하는 꼴
		//=>앞으로는 POST방식으로 요청하더라도 doGet 메소드에서 처리할 코드를 작성할 예정
	}

}
