<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import ="Contract.Contract"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>청약서</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />
<% Contract contract = (Contract)request.getAttribute("contract"); %>

<section class="site-section bg-light" id="contact-section">
      <div class="container">
   		<div class="row mb-5">
			<div class="col-12 text-center">
				<h2 class="text-black h1 site-section-heading">청약서 출력</h2>
			</div>
		</div>
		
			 <div class="row form-group">
               <div class="col-md-12">
                  <label class="text-black">계약 ID </label> 
                  	<%=  contract.getContractID()%>              	
               </div>         
              </div>
			
			 <div class="row form-group">
               <div class="col-md-12">
                  <label class="text-black">계약 만료일 </label> 
                  	<%=  contract.getContractExpirationDate()%>              	
               </div>         
              </div>
              
               <div class="row form-group">
               <div class="col-md-12">
                  <label class="text-black">월 납입 금액 </label> 
                  	<%=  contract.getPaymentAmount() + "원"%>              	
               </div>         
              </div>
              
               <div class="row form-group">
               <div class="col-md-12">
                  <label class="text-black">납입날짜 </label> 
                  	<%=  contract.getPaymentDate()%>              	
               </div>         
              </div>
                      
		      <div class="row form-group">
               <div class="col-md-12">
                  <label class="text-black">납입방식 </label> 
                  	<%=      contract.getPaymentType()%>              	
               </div>         
              </div>
              
              <div class="row form-group">
               <div class="col-md-12">
                  <label class="text-black">개인정보 보호 기간 </label> 
                  	<%=  contract.getPersonalInformationRetentionPeriod()%>              	
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
