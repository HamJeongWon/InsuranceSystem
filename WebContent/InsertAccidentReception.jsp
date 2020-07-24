<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>InsertAccidentReception</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />

	<%
		int num = Integer.parseInt(request.getParameter("num"));
		int index = num - 2;
	%>
	<section class="site-section bg-light" id="contact-section">
		<div class="container">
			<div class="row mb-5">
				<div class="col-12 text-center">
					<h2 class="text-black h1 site-section-heading">사고 내용 입력</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-7 mb-5">


					<form action=./AccidentReception?action=insertAccidentReception
						class="p-5 bg-white" method="post">

						<input type="hidden" name="index" value=<%=index %>>
						<h2 class="h4 text-black mb-5">사고 내용을 입력하세요</h2>

						<div class="row form-group">
							<div class="col-md-6 mb-3 mb-md-0">
								<label class="text-black" for="accidentDate">사고날짜</label> <input
									type="date" id="accidentDate" class="form-control"
									name="accidentDate">
							</div>
							<div class="col-md-6">
								<label class="text-black" for="accidentTime">사고시간</label> <input
									type="time" id="accidentTime" class="form-control"
									name="accidentTime">
							</div>
						</div>



						<div class="row form-group">
							<div class="col-md-12">
								<label class="text-black" for="accidentCause">사고원인</label>
								<textarea name="accidentCause" id="accidentCause" cols="30"
									rows="7" class="form-control"
									placeholder="사고원인을 작성해 주세요."></textarea>
							</div>
						</div>

						<div class="row form-group">
							<div class="col-md-12">
								<label class="text-black" for="accidentLocation">사고장소</label>
								<textarea name="accidentLocation" id="accidentLocation"
									cols="30" rows="7" class="form-control"
									placeholder="사고장소를 작성해 주세요."></textarea>
							</div>
						</div>
						
							<div class="row form-group">
							<div class="col-md-12">
								<label class="text-black" for="expertOpinion">전문가 소견서</label>
								<textarea name="expertOpinion" id="expertOpinion" cols="30"
									rows="7" class="form-control"
									placeholder="전문가 소견서를 작성해 주세요."></textarea>
							</div>
						</div>

						<div class="row form-group">
							<div class="col-md-12">
								<input type="submit" value="등록하기"
									class="btn btn-primary btn-md text-white">
									<input type="reset" value="다시쓰기"
									class="btn btn-primary btn-md text-white">
							</div>
						</div>


					</form>
				</div>
				 <div class="col-md-5">
            
            <div class="p-4 mb-3 bg-white">
              <p class="mb-0 font-weight-bold">Address</p>
              <p class="mb-4">203 Fake St. Mountain View, San Francisco, California, USA</p>

              <p class="mb-0 font-weight-bold">Phone</p>
              <p class="mb-4"><a href="#">+1 232 3235 324</a></p>

              <p class="mb-0 font-weight-bold">Email Address</p>
              <p class="mb-0"><a href="#">youremail@domain.com</a></p>

            </div>
            
          </div>

			</div>
		</div>
	</section>

	</form>
	<jsp:include page="/incl/Footer.jsp" />
</body>
</html>