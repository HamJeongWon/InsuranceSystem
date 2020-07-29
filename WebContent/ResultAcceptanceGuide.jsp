<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("EUC-KR");  %>
<%@ page import="Acceptance.AcceptanceGuide"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> 화재보험 설계결과 </title>
</head>
<body> 
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />
<%AcceptanceGuide Acceptance = (AcceptanceGuide)request.getAttribute("Acceptance"); %>

<section class="site-section bg-light" id="contact-section">

      <div class="container">
      
        <div class="row mb-5">
          <div class="col-12 text-center">
            <h2 class="text-black h1 site-section-heading">인수지침서 설계완료!</h2>
            <p class="lead">설계한 인수지침서의 정보입니다.</p>
          </div>
        </div>

		<div class="p-5 bg-white" style = "margin:auto; max-width: 800px;">
          <div class="row form-group">
			<div class="col-md-4 mb-3 mb-md-0" >
               <label class="text-black" for="acceptanceGuideID">인수지침서 ID</label>
               <p class="text-primary"><%= Acceptance.getAcceptanceID() %><p>
	           </div>
	           <div class="col-md-4">
	             <label class="text-black" for="insuranceID">보험 ID</label>
	             <p class="text-primary"><%= Acceptance.getInsuranceID() %><p>
	           </div>
	 			      	<div class="col-md-4">
	             <label class="text-black" for="riskEvaluation">위험 평가</label>
	             <p class="text-primary"><%= Acceptance.getRiskEvaluation() %><p>
	           </div>
	           <div class="col-md-12">
	             <label class="text-black" for="ScamCase"> 위험 사례 </label>
	             <p class="text-primary"><%= Acceptance.getScamCase() %><p>
             </div>
	       </div>
         </div>
    </section>

    <a href="main.jsp" class="bg-primary py-5 d-block">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md10">
          <h2 class="text-white">메인으로 돌아가기</h2>
          </div>
        </div>
      </div>  
    </a>
    
		<jsp:include page="/incl/Footer.jsp" />
	</body>
</html>