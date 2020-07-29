<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First Page</title>
</head>
<body>
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />

<section class="site-section" id="work-section">
      <div class="container">
        <div class="row mb-5 justify-content-center">
          <div class="col-md-8 text-center">
            <h2 class="text-black h1 site-section-heading text-center">보험회사</h2>
            <p class="lead">made by Kyung-Hyun Kim, seok-woo Jeong, jeong-won Ham </p>
          </div>
        </div>
      </div>
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-6 col-lg-4">
            <a href="" class="media-1" data-fancybox="gallery">
              <img src="images/img_1.jpg" alt="Image" class="img-fluid">
              <div class="media-1-content">
                <h2>상품설계하기</h2>
                <span class="category">Web Application</span>
              </div>
            </a>
          </div>
          <div class="col-md-6 col-lg-4">
            <a href="" class="media-1" data-fancybox="gallery">
              <img src="images/img_2.jpg" alt="Image" class="img-fluid">
              <div class="media-1-content">
                <h2>인수정책수립하기</h2>
                <span class="category">Branding</span>
              </div>
            </a>
          </div>
          <div class="col-md-6 col-lg-4">
            <a href="" class="media-1" data-fancybox="gallery">
              <img src="images/img_3.jpg" alt="Image" class="img-fluid">
              <div class="media-1-content">
                <h2>영업활동</h2>
                <span class="category">Website</span>
              </div>
            </a>
          </div>

          <div class="col-md-6 col-lg-4">
            <a href="" class="media-1" data-fancybox="gallery">
              <img src="images/img_4.jpg" alt="Image" class="img-fluid">
              <div class="media-1-content">
                <h2>인수심사하기</h2>
                <span class="category">Web Application</span>
              </div>
            </a>
          </div>
          <div class="col-md-6 col-lg-4">
            <a href="" class="media-1" data-fancybox="gallery">
              <img src="images/img_5.jpg" alt="Image" class="img-fluid">
              <div class="media-1-content">
                <h2>계약관리하기</h2>
                <span class="category">Branding</span>
              </div>
            </a>
          </div>
          <div class="col-md-6 col-lg-4">
            <a href="InsuranceTreatment.jsp" class="media-1" data-fancybox="gallery">
              <img src="images/img_6.jpg" alt="Image" class="img-fluid">
              <div class="media-1-content">
                <h2>보험처리하기</h2>
                <span class="category">Website</span>
              </div>
            </a>
          </div>

         
        </div>
      </div>
    </section>




<jsp:include page="/incl/Footer.jsp" />

</body>
</html>