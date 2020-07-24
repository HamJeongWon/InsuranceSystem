<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>InsuranceSystem</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />

	<section class="site-section" id="work-section">
		<div class="container">
			<div class="row mb-5 justify-content-center">
				<div class="col-md-8 text-center">
					<h2 class="text-black h1 site-section-heading text-center">상품 설계하기</h2>
					<p class="lead">화재보험, 자동차보험, 실비보험을 설계 가능</p>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6 col-lg-4">
					<a href="FInsuranceDesign.jsp" class="media-1" data-fancybox="gallery">
						<img src="images/img_1.jpg" alt="Image" class="img-fluid">
						<div class="media-1-content">
							<h2>화재보험 설계</h2>
							<span class="category">Fire Insurance Design</span>
						</div>
					</a>
				</div>
				<div class="col-md-6 col-lg-4">
					<a href="CInsuranceDesign.jsp" class="media-1" data-fancybox="gallery">
						<img src="images/img_2.jpg" alt="Image" class="img-fluid">
						<div class="media-1-content">
							<h2>자동차보험 설계</h2>
							<span class="category">Car Insurance Design</span>
						</div>
					</a>
				</div>
				<div class="col-md-6 col-lg-4">
					<a href="AInsuranceDesign.jsp" class="media-1" data-fancybox="gallery">
						<img src="images/img_3.jpg" alt="Image" class="img-fluid">
						<div class="media-1-content">
							<h2>실비보험 설계</h2>
							<span class="category">ActualCost Insurance Design</span>
						</div>
					</a>
				</div>

			</div>
		</div>
	</section>

	<jsp:include page="/incl/Footer.jsp" />
</body>
</html>