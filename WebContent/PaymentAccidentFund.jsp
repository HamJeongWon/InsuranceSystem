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
	<section class="site-section bg-light" id="contact-section">
		<div class="container">

			<%
				Vector<Integer> accidentIDVector = new Vector<Integer>();

				accidentIDVector = (Vector<Integer>) request.getAttribute("accidentIDVector");
				if (accidentIDVector.size() != 0) {
			%>
			<div class="row mb-5">
				<div class="col-12 text-center">

					<h2 class="text-black h1 site-section-heading">결정 보험금 지급하기</h2>
					<br>
					<br>

					<table border="1" width="500" height="100" align="center">
						<tr align="center">
							<th>사고ID</th>
							<th>조회</th>
						</tr>
						<%
							for (int i = 0; i < accidentIDVector.size(); i++) {
						%>

						<tr align="center">
							<td><%=accidentIDVector.get(i)%></td>
							<td>
								<form
									action=./PaymentAccidentFund?action=showAccidentInformation
									method="post">
									<input type="hidden" name="accidentID"
										value=<%=accidentIDVector.get(i)%>> <input
										type="submit" value="조회">

								</form>
							</td>
							<%
								}
								} else {
							%>
							<div class="row mb-5">
								<div class="col-12 text-center">
									<h2 class="text-black h1 site-section-heading">보험금을 지급할
										사고번호가 존재하지 않습니다.</h2>
									<br> <br>
									<div class="row form-group">
										<div class="col-md-12">

											<form action="main.jsp">

												<input type="submit" value="메인으로"
													class="btn btn-primary btn-md text-white"
													style="display: inline-block;">
											</form>
											<br>
											<form action=./CalculateAccidentFund?action=showAccidentID method="post">
												<input type="submit" value="보험금산출하러가기"
													class="btn btn-primary btn-md text-white"
													style="display: inline-block;">
											</form>
										</div>
									</div>
								</div>
							</div>
							<%
								}
							%>

						</tr>
					</table>
				</div>

			</div>
	</section>
	<jsp:include page="/incl/Footer.jsp" />

</body>
</html>