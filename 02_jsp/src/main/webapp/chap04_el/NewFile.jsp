<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="chap04_el.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
  EL(Expression Language)
  1. JSP 페이지에서 자바코드를 사용하지 않고 간결하게 데이터를 출력할 수 있게 해주는 새로운 표현 언어입니다.
     ( <%= %> 또는 out.print() )
  2. HTML과 JSP코드의 혼재로 인한 코드 가독성 문제를 해결합니다.
  3. 서블릿 컨테이너(page, request, session, application)에 저장된 값을 쉽게 접근할 수 있도록 설계되었습니다.
  4. 일반 자바 변수는 EL문법을 사용할 수 없고 서블릿 컨테이너에 저장된 값만 EL문법을 사용할 수 있습니다.
  5. EL은 서블릿 컨테이너의 작은 영역부터 큰 영역으로 이동하면서 값을 찾습니다.
     page -> request -> session -> application
  6. 기본 문법
     ${값}
  7. EL 내장 객체를 활용할 수 있습니다.
        EL 내장 객체       설명                                                                EL 사용 예시
     ---------------------------------------------------------------------------------------------------------------------------------
     1) pageScope          현재 JSP 페이지에서 선언한 객체에 접근할 때 사용                    ${pageScope.객체}
     2) requestScope       하나의 요청(request) 동안 공유되는 객체에 접근할 때 사용            ${requestScope.객체}
     3) sessionScope       로그인 등 사용자별 세션 동안 공유되는 객체에 접근                   ${sessionScope.객체}
     4) applicationScope   웹 애플리케이션 전체에서 공유되는 전역 객체에 접근                  ${applicationScope.객체}
     5) param              사용자가 보낸 요청 파라미터(폼 입력값 등)에 접근                    ${param.파라미터이름}
     6) header             HTTP 요청 헤더 정보에 접근                                          ${header.헤더}
     7) cookie             브라우저가 보낸 쿠키값에 접근                                       ${cookie.쿠키이름.value}
     8) pageContext        JSP의 pageContext(인터페이스)의 객체(다른 영역 접근 가능)           ${pageContext}
     9) request            HttpServletRequest(인터페이스)의 객체(요청 관련 메서드 사용 가능)   ${pageContext.request.메소드}
     10) session           HttpSession(인터페이스)의 객체(세션 관련 메서드 사용 가능)          ${pageContext.session.메소드}
     11) servletContext    ServletContext(인터페이스)의 객체(서버 전체 범위 설정 등 접근)      ${pageContext.servletContext.메소드}
        (=application)
 --%>
 
 <%
  //서블릿 컨테이너 영역에 데이터 저장
  request.setAttribute("name", "bbb");
  session.setAttribute("name", "ccc");
  application.setAttribute("name", "ddd");
  pageContext.setAttribute("name", "aaa");
 %>
<div>${name}</div>
<div>${requestScope.name}</div>
<div>${sessionScope.name}</div>
<div>${applicationScope.name}</div>

<hr>

<%
  //객체와 EL
  Product product = new Product();
  product.setModel("리모컨");
  product.setPrice(10000);
  request.setAttribute("product", product);
%>
<div>${product}</div>
<div>${product.model}</div>
<div>${product.price}</div>
<div>${product["model"]}</div>
<div>${product["price"]}</div>

<hr>

<%
  //Map과 EL
  Map<String, Object> map = new HashMap<>();
  map.put("model", "텀블러");
  map.put("price", 30000);
  request.setAttribute("map", map);
%>
<div>${map}</div>
<div>${map.model}</div>
<div>${map.price}</div>

<hr>

<%
  //List와 EL
  List<Product> products = new ArrayList<>();
  products.add(product);
  request.setAttribute("products", products);
%>
<div>${products}</div>
<div>${products.get(0).model}</div>
<div>${products.get(0).price}</div>
<div>${products[0].model}</div>
<div>${products[0].price}</div>

<hr>

<%
  //연산에서 사용할 데이터
  request.setAttribute("a", 5);
  request.setAttribute("b", 2);
%>
<div>a + b = ${a + b}</div>
<div>a - b = ${a - b}</div>
<div>a * b = ${a * b}</div>
<div>a / b = ${a / b}</div>
<div>a / b = ${a div b}</div>
<div>a % b = ${a % b}</div>
<div>a % b = ${a mod b}</div>

<hr>

<div>a > b : ${a > b}, ${a gt b}</div>
<div>a > b : ${a >= b}, ${a ge b}</div>
<div>a < b : ${a < b}, ${a lt b}</div>
<div>a <= b : ${a <= b}, ${a le b}</div>
<div>a == b : ${a == b}, ${a eq b}</div>
<div>a != b : ${a != b}, ${a ne b}</div>

<hr>

<div>a == 5 && b == 5 : ${a == 5 && b == 5}, ${a eq 5 and b eq 5}</div>
<div>a == 5 || b == 5 : ${a == 5 || b == 5}, ${a eq 5 or b eq 5}</div>
<div>!(a == 5) : ${!(a == 5)}, ${not(a eq 5)}</div>

<hr>

<div>${a > 0 ? "양수" : "음수"}</div>

<hr>

<div>a와 b 문자열 연결(+는 지원이 안 되고, +=은 지원이 됩니다.): ${a += b}</div>

<hr>

<%
  //비어 있는 List
  request.setAttribute("list", new ArrayList<>());
%>
<div>List가 비어 있는가? ${empty List}</div>
<div>List가 비어 있지 않은가? ${not empty List}</div>

<hr>

<%-- request의 데이터 처리 방법 확인을 위한 링크 --%>
<a href="${pageContext.request.contextPath}/RequestDataHandle">요청</a>

</body>
</html>




