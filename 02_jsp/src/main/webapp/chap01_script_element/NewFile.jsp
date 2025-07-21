<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 1. 주석: JSP 주석으로 클라이언트에 전달되지 않는 특징 --%>
<!-- (비교)HTML 주석: 클라이언트에 전달되고 Java 코드는 주석 처리하지 못하는 특징 -->

<%-- 2. Declaration(선언부): 멤버 변수, 메소드 선언에서 사용(JSP가 변환된 서블릿의 멤버) --%>
<%!
  int count = 0;                                   //멤버 변수( = 필드)
  public String method() {return "Hello World";}  //멤버 변수( = 필드)
%>

<%-- 3. 지시자(Directive): 서블릿 컨테이너에 정보를 전달하기 위해서 사용 --%>
<%@ page session="true" %>

<%-- 4. 스크립틀릿(Scriptlet): 자바 코드 실행을 위해서 사용 --%>
<%
  int count = 10;   //지역 변수
%>

<%-- 5. 표현식(Expression): 값(변수, 메소드 호출 등)을 문자열로 변환하여 브라우저에 출력하기 위해 사용 --%>
<%=this.count%>
<br>
<%=method()%>
<br>
<%=count%>
<br>


</body>
</html>