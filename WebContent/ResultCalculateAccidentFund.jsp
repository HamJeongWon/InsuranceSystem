<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="Accident.Accident"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>사고내용 입력결과</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />
	<%Accident accident = (Accident)request.getAttribute("accident"); %>


	<section class="site-section bg-light" id="contact-section" style = "padding-top : 200px;">
		<c>
		<div class="container">
			<div class="row mb-5">
				<div class="col-12 text-center">
					<h2 class="text-black h1 site-section-heading">사고 내용 입력 결과</h2>
				</div>
			</div>



			<div class="p-5 bg-white" style="margin: auto; max-width: 700px;">


				<h2 class="h4 text-black mb-5">사고 내용 입력 결과</h2>

				<jsp:include page="/incl/showAccidentBasicInformation.jsp" />

				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="insurancePremium">보장액:</label>
						${accident.insurancePremium}
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="insurancePremiumCause">금액 결정 사유:</label>
						${accident.insurancePremiumCause}
					</div>
				</div>




				<div class="row form-group">
					<div class="col-md-12">
						<form action="main.jsp">
							<input type="submit" value="확인"
								class="btn btn-primary btn-md text-white" style="float: left;">
						</form>

						<form
							action=./PaymentAccidentFund?action=insertPaymentAccidentFund
							method="post">
							<input type="hidden" name="accidentID"
								value=<%=accident.getAccidentID() %>> <input
								type="submit" value="결정보험금지급하기"
								class="btn btn-primary btn-md text-white" style="float: right;">
						</form>
					</div>
				</div>
			</div>
		</div>



		</c>
	</section>

	<jsp:include page="/incl/Footer.jsp" />
	<jsp:include page="/incl/staticFooter.jsp" />
</body>
</html>