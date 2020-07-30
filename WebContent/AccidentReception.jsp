<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>AccidentReception</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />
<section class="site-section bg-light" id="contact-section">
	<div class="container">
	
	<div class="row mb-5">
				<div class="col-12 text-center">

					<h2 class="text-black h1 site-section-heading">��� ���� �ۼ��ϱ�</h2>
					<br>
					<br>

	<table border="1" width="500" height="100" align="center">
		<tr align="center">
			<th>��ID</th>
			<th>����ID</th>
			<th>��ȸ</th>
		</tr>
		<tr align="center">

			<%
				Vector<Integer> idVector = new Vector<Integer>();

				idVector = (Vector<Integer>) request.getAttribute("IDVector");
				if (idVector != null) {
					//no = showacceptanceapprove���� ���� ������ index

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
						type="submit" value="��ȸ">
						</form>
			</td>
			
			<%
				}
					}
				}
			%>


		</tr>
	</table>
	</div>
	</div>
	
</div>
</section>


	<jsp:include page="/incl/Footer.jsp" />

</body>
</html>