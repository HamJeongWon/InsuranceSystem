<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기존 고객 가입 고객 id 입력</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />
	
	<div align="center">
		<form action= ./InsertExistingCus method= POST>

			<table>
				<tbody>
					<tr>
						<td style="vertical-align: top">가입할 기존 고객id을 입력하세요  &nbsp;</td>
						<td><input type="text" name="CustomerID"> &nbsp;</td>
				 		<td>
							<select name="InsuranceType">
       						 <option value="Fire">화재</option>
       						 <option value="Car">자동차</option>
        					 <option value="ActualCost">실비</option>
   							</select>
						</td>
						<td>&nbsp;<input type="submit" value="확인"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div> 


	<div>
	<table align="center">

				<thead>
					<tr>
					<!-- 보험명도 같이 출력되기 수정 -->
						<td>고객 id</td>
					</tr>
				</thead>
				<tbody>
					<% Vector<Integer> VecCustomer = (Vector<Integer>)request.getAttribute("VecCustomer");
					for(Integer id : VecCustomer) {
					%><tr><td>
					<%=  id%></td><td>
				<%	
				}
				%>
				</tbody>
			</table>
	</div> 
	
	<jsp:include page="/incl/Footer.jsp" />
</body>
</html>