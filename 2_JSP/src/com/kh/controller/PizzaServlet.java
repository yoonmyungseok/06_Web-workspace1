package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/confirmPizza.do")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//컨트롤러 작성 STEP
		//요청시 전달값들 뽑아서 -> DB로 요청 처리 ->결과 받기 ->응답뷰 지정
		//1) POST방식일 경우 인코딩 처리
		//request.setCharacterEncoding("UTF-8");
		
		//2) 요청시 전달값 뽑기 데이터 가공 처리 =>변수 및 VO 객체에 담아두기
		//(request의 parameter 영역으로부터 뽑아내기)
		//request.getParameter("키값") : String (밸류값을 리턴해줌)
		//request.getparameterValues("키값"): String[] (밸류값들을 리턴해줌)

		//주문자 정보에 대해서 전달값 뽑기
		//이름(userName), 전화번호(phone), 주소(address), 요청사항(message)
		String userName=request.getParameter("userName"); //"홍길동"
		String phone=request.getParameter("phone"); // "01012345678"
		String address=request.getParameter("address"); // "서울시 영등포구"
		String message=request.getParameter("message"); //"일회용품은 빼주세요" / " "
		
		//주문정보에 대해서 전달값 뽑기
		//피자(pizza), 토핑(topping)-여러개가능, 사이드(side)-여러개가능, 결제방식(payment)
		String pizza=request.getParameter("pizza"); //"콤비네이션피자"
		String[] toppings=request.getParameterValues("topping"); //["고구마무스", "치즈토핑"] / null
		String[] sides=request.getParameterValues("side"); // ["콜라","피클"] / null
		String payment=request.getParameter("payment"); // "card"/"cash"
		
		//3)요청 처리
		//(보통의 흐름: Servlet->Service->DAO->DB)
		//=>요청처리를 DB에서 한다는 가정 하에 내가 주문한 것들에 대해서 가격을 매기기
		int price=0; //결제금액을 담아둘 변수
		
		//피자금액 지정
		switch(pizza) {
		case "콤비네이션피자": price+=5000; break;
		case "치즈피자": price+=6000; break;
		case "포테이토피자": //price+=7000; break; //생략가능
		case "고구마피자": price+=7000; break;
		case "불고기피자": price+=8000; break;
		}
		
		//토핑금액 지정
		if(toppings!=null) {
			for(int i=0; i<toppings.length; i++) {
				switch(toppings[i]) {
				case "고구마무스":
				case "콘크림무스":price+=1500;break;
				case "파인애플토핑":
				case "치즈토핑":price+=2000;break;
				case "치즈바이트":
				case "치즈크러스트":price+=3000;break;
				}
			}
		}
		
		//사이드금액 지정
		if(sides!=null) {
			for(int i=0; i<sides.length; i++) {
				switch(sides[i]) {
				case "콜라":
				case "사이다":price+=2000;break;
				case "갈릭소스":
				case "핫소스":price+=300;break;
				case "피클":
				case "파마산치즈가루":price+=500;break;
				}
			}
		}
		//출력
		/*
		System.out.println("userName: "+userName);
		System.out.println("phone: "+phone);
		System.out.println("address: "+address);
		System.out.println("message: "+message);
		System.out.println("pizza: "+pizza);
		System.out.println("toppings: "+String.join(", ", toppings));
		System.out.println("sides: "+String.join(", ", sides));
		System.out.println("payment: "+payment);
		System.out.println("price: "+price);
		*/
		
		//4)요청처리 후 사용자가 보게 될 응답페이지를 만들기(자바 코드로 만들거나 jsp에게 위임하거나)
		
		//jsp에게 위임하는 방식으로 응답페이지 지정
		//4_1) 응답페이지에서 필요한 데이터 담기(request의 attribute 영역)
		//request.setAttribute("키", 밸류);
		request.setAttribute("userName", userName);
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);
		request.setAttribute("message", message);
		request.setAttribute("pizza", pizza);
		request.setAttribute("toppings", toppings);
		request.setAttribute("sides", sides);
		request.setAttribute("payment", payment);
		request.setAttribute("price", price);
		
		//4_2) 응답할 뷰를 선택하면서 RequestDispatcher 객체 생성
		RequestDispatcher view=request.getRequestDispatcher("views/05_PizzaPayment.jsp");
		
		//4_3) 선택된 뷰가 사용자에게 보여질 수 있도록 request, response를 넘겨주면서 포워딩
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
