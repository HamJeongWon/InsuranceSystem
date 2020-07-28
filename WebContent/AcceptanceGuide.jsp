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
	
	    <section class="site-section">
      <div class="container">
        <div class="row">
          <div class="col-md-6 col-lg-4">
            <div class="p-3 box-with-humber">
              <div class="number-behind">01.</div>
              <h2 class="text-primary">인수지침서 보기</h2>
              <p class="mb-4">각 보험별로 위험평가와 사기사례가 작성되있는 지침서로 인수 승인을 할 때 참고한다.</p>
              <ul class="list-unstyled ul-check primary">
                <li>화재보험 인수지침서</li>
                <li>자동차보험 인수지침서</li>
                <li>실비보험 인시지침서</li>
              </ul>
              <p><a href="ShowAcceptanceGuide.jsp">인수지침서 보기</a></p>
            </div>
          </div>

          <div class="col-md-6 col-lg-4">
            <div class="p-3 box-with-humber">
              <div class="number-behind">02.</div>
              <h2 class="text-primary">인수지침서 설계</h2>
              <p class="mb-4">새로 설계한 보험의 위험평가나 비슷한 보험의 사기사례를 작성하여 지침서로 만들수 있다.</p>
              <ul class="list-unstyled ul-check primary">
                <li>화재보험 인수지침서</li>
                <li>자동차보험 인수지침서</li>
                <li>실비보험 인시지침서</li>
              </ul>
			  <p><a href="AcceptanceGuideDesign.jsp">인수지침서 설계</a></p>
            </div>
          </div>
        </div>
      </div>
    </section>

	<jsp:include page="/incl/Footer.jsp" />
</body>
</html>