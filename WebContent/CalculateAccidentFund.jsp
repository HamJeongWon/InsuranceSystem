<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>show accidentID</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />

	
	<form action=./CalculateAccidentFund?action=showAccidentID method="post">
		<input type="submit" value="조회">
	</form>

	<table border="1" width="500" height="100" align="center">
		<tr align="center">
			<th>사고ID</th>
			<th>조회</th>
		</tr>
			<%
				Vector<Integer> accidentIDVector = new Vector<Integer>();

			accidentIDVector = (Vector<Integer>) request.getAttribute("accidentIDVector");
				if (accidentIDVector != null) {
					//no = showacceptanceapprove에서 받은 백터의 index

					for (int i = 0; i < accidentIDVector.size(); i++) {
			%>
		
		<tr align="center">
			<td><%=accidentIDVector.get(i)%></td>
			<td>
				<form action=./CalculateAccidentFund?action=showAccidentInformation method="post">
					<input type="hidden" name="accidentID" value=<%=accidentIDVector.get(i)%>> 
					<input type="submit" value="조회">
			</td>
			</form>
			<%
				}
				}
			%>


		</tr>
	</table>



	<jsp:include page="/incl/Footer.jsp" />

</body>
</html>