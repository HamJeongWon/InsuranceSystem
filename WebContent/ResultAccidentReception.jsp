<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="Accident.Accident"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ResultAccidentReception</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />
	<%Accident accident = (Accident)request.getAttribute("accident"); %>


	<section class="site-section bg-light" id="contact-section">
		<c>
		<div class="container">
			<div class="row mb-5">
				<div class="col-12 text-center">
					<h2 class="text-black h1 site-section-heading">��� ���� �Է� ���</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-7 mb-5">


					<form action="main.jsp" class="p-5 bg-white"> 


						<h2 class="h4 text-black mb-5">��� ������ �Է� ���</h2>

						<div class="row form-group">
							<div class="col-md-12">
								<label class="text-black" for="accidentID">���ID</label>
								${accident.accidentID}
							</div>
						</div>

						<div class="row form-group">
							<div class="col-md-12">
								<label class="text-black" for="accidentDate">���¥</label>
								${accident.accidentDate}
							</div>
						</div>

						<div class="row form-group">
							<div class="col-md-12">
								<label class="text-black" for="accidentTime">���ð�</label>
								${accident.accidentTime}
							</div>
						</div>



						<div class="row form-group">
							<div class="col-md-12">
								<label class="text-black" for="accidentCause">������</label>
								${accident.accidentCause}
							</div>
						</div>

						<div class="row form-group">
							<div class="col-md-12">
								<label class="text-black" for="accidentLocation">������</label>
								${accident.accidentLocation}
							</div>
						</div>

						<div class="row form-group">
							<div class="col-md-12">
								<label class="text-black" for="expertOpinion">������ �Ұ߼�</label>
								${accident.expertOpinion}
							</div>
						</div>

						<div class="row form-group">
							<div class="col-md-12">
								<input type="submit" value="����ȭ������"
									class="btn btn-primary btn-md text-white">
							</div>
						</div>
				</div>
				<div class="col-md-5">

					<div class="p-4 mb-3 bg-white">
						<p class="mb-0 font-weight-bold">Address</p>
						<p class="mb-4">203 Fake St. Mountain View, San Francisco,
							California, USA</p>

						<p class="mb-0 font-weight-bold">Phone</p>
						<p class="mb-4">
							<a href="#">+1 232 3235 324</a>
						</p>

						<p class="mb-0 font-weight-bold">Email Address</p>
						<p class="mb-0">
							<a href="#">youremail@domain.com</a>
						</p>

					</div>

				</div>

			</div>
		</div>
		</c>
	</section>

	</form>


	<jsp:include page="/incl/Footer.jsp" />
</body>
</html>