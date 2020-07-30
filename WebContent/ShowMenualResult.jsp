<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴얼 결과 출력 페이지</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />

	<div align="center">
			
	<%
	String Content = (String)request.getAttribute("Content");
	%>
	<h3>메뉴얼 </h3> <br>
	<%= Content %>
	</div>

	<jsp:include page="/incl/Footer.jsp" />
</body>
</html>