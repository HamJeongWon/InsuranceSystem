<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴얼 확인하기</title>
</head>
<style>
tr, td {
	padding: 15px;
}
</style>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />
<script>
</script>
	
	<table align="center">
		<tr>
			<td><a href="./Menual1">판매 매뉴얼 확인하기</a></td>	
			<td><a href="./Menual2">상품 매뉴얼 확인하기</a></td>
		</tr>
	</table>


	<jsp:include page="/incl/Footer.jsp" />
</body>
</html>