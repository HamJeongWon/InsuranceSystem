<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Insurance.Insurance"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 메뉴얼 확인하기</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />
	
	<div align="center">
		<form action= ./Menual2 method=POST>

			<table>
				<tbody>
					<tr>
						<td style="vertical-align: top">확인하실 보험 상품의 id을 입력하세요</td>
						<td><input type="text" name="InsuranceID"></td>
						<td><input type="submit" value="확인"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
			<table border="1" align="center">
				<thead>
					<tr>
						<td>보험 id</td>
						<td>보험 이름</td>
					</tr>
				</thead>
	
				<tbody>
				<% Vector<Insurance> VecInsurance = (Vector<Insurance>)request.getAttribute("VecInsurance");
					for(Insurance insu : VecInsurance) {
					%><tr><td>
					<%=  insu.getInsuranceID()%></td><td>
					<%=  insu.getInsuranceName()%></td></tr>
					<br>
				<%	
				}
				%>
				</tbody>
			</table>
		
	
	<jsp:include page="/incl/Footer.jsp" />
</body>
</html>