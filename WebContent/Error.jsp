<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />
	
	<div align="center">
			
	<%= (String)request.getAttribute("message") %>
	
	</div>
	
	<jsp:include page="/incl/Footer.jsp" />
</body>
</html>