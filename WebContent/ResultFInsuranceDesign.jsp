<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("EUC-KR");  %>
<%@ page import="Insurance.FireInsurance"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> 화재보험 설계결과 </title>
</head>
<body> 
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />
<%FireInsurance insurance = (FireInsurance)request.getAttribute("FInsurance"); %>
<%System.out.println(insurance.getInsuranceID()); %>

<%if (typeofjQuery == 'undefined') {%>

alert("없음");

<%}else{%>

alert("있음");

<%}%>

${insurance.insuranceID}

<section class="site-section bg-light" id="contact-section">

      <div class="container">
      
        <div class="row mb-5">
          <div class="col-12 text-center">
            <h2 class="text-black h1 site-section-heading">화재보험 설계완료!</h2>
            <p class="lead">설계한 화재보험의 정보입니다.</p>
            ${insurance}
          </div>
        </div>

            <div class="p-5 bg-white" style = "margin:auto; max-width: 700px;">
              
              <h2 class="h4 text-black mb-5" align = "center"> 보험 기본 정보 </h2> 
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceID"> 보험 ID </label> 
                  ${insurance.insuranceID}
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceName"> 보험 이름 </label> 
                  ${insurance.insuranceName}
                </div>
              </div>

              <div class="row form-group">          
                <div class="col-md-12">
                  <label class="text-black" for="insuranceFee">보험료</label> 
                  ${insurance.insuranceFee}
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceManual">보험 설명서</label> 
                  ${insurance.insuranceManual}
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceSalesManual">판매 메뉴얼</label> 
                  ${insurance.insuranceSalesManual}
                </div>
              </div>

              <br>
              <br>
              
              <h2 class="h4 text-black mb-5" align = "center"> 보험 상세 정보 </h2> 
              
              <p class="lead" align = "center" >직접 손해</p>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="directGuaranteedAmount">직접 손해 보장액</label> 
                  ${insurance.directDamage.damageGuaranteedAmount}
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="directGuaranteedContent">직접 손해 보장 내용</label> 
                  ${insurance.directDamage.damageGuaranteedContent}
                </div>
              </div>
              
              <br>
              <p class="lead" align = "center" >소방 손해</p>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="fireGuaranteedAmount"> 소방 손해 보장액</label> 
                  ${insurance.fireDamage.damageGuaranteedAmount}
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="fireGuaranteedContent"> 소방 손해 보장 내용</label> 
                  ${insurance.fireDamage.damageGuaranteedContent}
                </div>
              </div>
              
             <br>
             <p class="lead" align = "center" >피난 손해</p>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="refugeGuaranteedAmount">피난 손해 보장액</label> 
                ${insurance.refugeDamage.damageGuaranteedAmount}                
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="refugeGuaranteedContent">피난 손해 보장 내용</label> 
               	${insurance.refugeDamage.damageGuaranteedContent}                
               	</div>
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