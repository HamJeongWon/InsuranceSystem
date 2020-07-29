<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Insurance.CarInsurance"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 화재보험 설계결과 </title>
</head>
<body> 
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />
<%CarInsurance insurance = (CarInsurance)request.getAttribute("CInsurance"); %>

<section class="site-section bg-light" id="contact-section">

      <div class="container">
      
        <div class="row mb-5">
          <div class="col-12 text-center">
            <h2 class="text-black h1 site-section-heading">자동차보험 설계완료!</h2>
            <p class="lead">설계한 자동차보험의 정보입니다.</p>
          </div>
        </div>

            <div class="p-5 bg-white" style = "margin:auto; max-width: 700px;">
              
              <h2 class="h4 text-black mb-5" align = "center"> 보험 기본 정보 </h2> 
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceID"> 보험 ID </label>
                  <p class="text-primary"><%= insurance.getInsuranceID() %><p>
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceName"> 보험 이름 </label> 
                   <p class="text-primary"><%= insurance.getInsuranceName() %><p>
                </div>
              </div>

              <div class="row form-group">          
                <div class="col-md-12">
                  <label class="text-black" for="insuranceFee">보험료</label> 
                   <p class="text-primary"><%= insurance.getInsuranceFee() %><p>
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceManual">보험 설명서</label> 
                  <p class="text-primary"><%= insurance.getInsuranceManual() %><p>
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceSalesManual">판매 메뉴얼</label> 
                  <p class="text-primary"><%= insurance.getInsuranceSalesManual() %><p>
                </div>
              </div>

              <br>
              <br>
              
              <h2 class="h4 text-black mb-5" align = "center"> 보험 상세 정보 </h2> 
              
 				<p class="lead" align = "center" >대물 배상</p>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceSalesManual">구분</label>   
					<p class="text-primary"><%= insurance.getGoodsIndemnification().getSeparation() %><p>		  
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="goodsGuaranteeLimit"> 대물 배상 지급 한도 </label> 
					<p class="text-primary"><%= insurance.getGoodsIndemnification().getProvisionLimit() %><p>	
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="goodsGuaranteeContent"> 대물 배상 보장 내용 </label> 
                  	<p class="text-primary"><%= insurance.getGoodsIndemnification().getGuaranteeContent() %><p>	
  				</div>
              </div>
              
              <br>
              <p class="lead" align = "center" >대인 배상</p>

              <div class="row form-group">
                <div class="col-md-12">  
                  <label class="text-black" for="insuranceSalesManual">구분</label>                  
					<p class="text-primary"><%= insurance.getPersonalIndemnification().getSeparation() %><p>				  
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="personalProvisionLimit"> 대인 배상 지급 한도 </label> 
					<p class="text-primary"><%= insurance.getPersonalIndemnification().getProvisionLimit() %><p>	
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="personalGuaranteeContent"> 대인 배상 보장 내용 </label> 
                  	<p class="text-primary"><%= insurance.getPersonalIndemnification().getGuaranteeContent() %><p>	
                </div>
              </div>
              
             <br>
             <p class="lead" align = "center" >자기 차량 손해</p>
             
              <div class="row form-group">
                <div class="col-md-12">  
                  <label class="text-black" for="insuranceSalesManual">구분</label>   
				  <p class="text-primary"><%= insurance.getSelfVehicleDamage().getSeparation() %><p>			  
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="SubscriptionFeeForInjury"> 자기 차량 손해 부상 가입금액 </label> 
				  <p class="text-primary"><%= insurance.getSelfVehicleDamage().getSubscriptionFeeForInjury() %><p>		
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="SubscriptionFeeForAccidentalInjuries"> 자기 차량 손해 사고 후유장해의 가입금액 </label> 
				  <p class="text-primary"><%= insurance.getSelfVehicleDamage().getSubscriptionFeeForAccidentalInjuries() %><p>	
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