<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 가입 신청하기</title>
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
	<div style = "padding-top : 100px;">
	<table align="center">
		<tr>
			<td><a href="./InsertExistingCus">기존 고객 정보 입력</a></td>	
			<td><a href="./InsertNewCus">새로운 고객 정보 입력</a></td>
		</tr>
	</table>
	</div>

	<jsp:include page="/incl/Footer.jsp" />
	<jsp:include page="/incl/staticFooter.jsp" />
</body>
</html>