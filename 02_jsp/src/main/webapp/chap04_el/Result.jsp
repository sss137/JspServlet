<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>height : ${height}</div>
<div>height > 180 : ${height gt 180}</div>

<div>weight : ${param.weight}</div>
<%-- 파라미터 타입이 스트링이라 직접적으로 숫자의 크기 비교가 불가.
     따라서 캐스팅을 해야 연산이 가능한데 캐스팅 방법은 주로 밑과 같이 함 --%>
<div>weight > 100 : ${param.weight - 100 gt 0}</div>


</body>
</html>