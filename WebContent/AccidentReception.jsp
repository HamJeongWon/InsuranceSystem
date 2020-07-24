<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>

<!-- exception 처리
1. 조회눌러야 db에서 id가져오는데 바로 보여주고 싶음
2. 사고처리가능한 고객이 존재하지 않을 경우 예외처리 해주어야 함
 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>AccidentReception</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />

	
	<form action=./AccidentReception?action=showID method="post">
		<input type="submit" value="조회">
	</form>

	<table border="1" width="500" height="100" align="center">
		<tr align="center">
			<th>고객ID</th>
			<th>보험ID</th>
			<th>조회</th>
		</tr>
		<tr align="center">

			<%
				Vector<Integer> idVector = new Vector<Integer>();

				idVector = (Vector<Integer>) request.getAttribute("IDVector");
				if (idVector != null) {
					//no = showacceptanceapprove에서 받은 백터의 index

					for (int no = 0; no < idVector.size(); no++) {
						if (no % 2 == 0) {
			%>
		
		<tr align="center">
			<td><%=idVector.get(no)%></td>
			<%
				} else {
			%>
			<td><%=idVector.get(no)%></td>

			<td>
				<form action="InsertAccidentReception.jsp" method="post">
					<input type="hidden" name="num" value=<%=no + 1%>> <input
						type="submit" value="조회">
			</td>
			</form>
			<%
				}
					}
				}
			%>


		</tr>
	</table>



	<jsp:include page="/incl/Footer.jsp" />

</body>
</html>