<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("EUC-KR");  %>
<%@ page import="Insurance.CarInsurance"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> ȭ�纸�� ������ </title>
</head>
<body> 
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />
<%CarInsurance insurance = (CarInsurance)request.getAttribute("CInsurance"); %>

<section class="site-section bg-light" id="contact-section">

      <div class="container">
      
        <div class="row mb-5">
          <div class="col-12 text-center">
            <h2 class="text-black h1 site-section-heading">�ڵ������� ����Ϸ�!</h2>
            <p class="lead">������ �ڵ��������� �����Դϴ�.</p>
          </div>
        </div>

            <div class="p-5 bg-white" style = "margin:auto; max-width: 700px;">
              
              <h2 class="h4 text-black mb-5" align = "center"> ���� �⺻ ���� </h2> 
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceID"> ���� ID </label>
                  <p class="text-primary"><%= insurance.getInsuranceID() %><p>
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceName"> ���� �̸� </label> 
                   <p class="text-primary"><%= insurance.getInsuranceName() %><p>
                </div>
              </div>

              <div class="row form-group">          
                <div class="col-md-12">
                  <label class="text-black" for="insuranceFee">�����</label> 
                   <p class="text-primary"><%= insurance.getInsuranceFee() %><p>
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceManual">���� ����</label> 
                  <p class="text-primary"><%= insurance.getInsuranceManual() %><p>
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceSalesManual">�Ǹ� �޴���</label> 
                  <p class="text-primary"><%= insurance.getInsuranceSalesManual() %><p>
                </div>
              </div>

              <br>
              <br>
              
              <h2 class="h4 text-black mb-5" align = "center"> ���� �� ���� </h2> 
              
 				<p class="lead" align = "center" >�빰 ���</p>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceSalesManual">����</label>   
					<p class="text-primary"><%= insurance.getGoodsIndemnification().getSeparation() %><p>		  
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="goodsGuaranteeLimit"> �빰 ��� ���� �ѵ� </label> 
					<p class="text-primary"><%= insurance.getGoodsIndemnification().getProvisionLimit() %><p>	
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="goodsGuaranteeContent"> �빰 ��� ���� ���� </label> 
                  	<p class="text-primary"><%= insurance.getGoodsIndemnification().getGuaranteeContent() %><p>	
  				</div>
              </div>
              
              <br>
              <p class="lead" align = "center" >���� ���</p>

              <div class="row form-group">
                <div class="col-md-12">  
                  <label class="text-black" for="insuranceSalesManual">����</label>                  
					<p class="text-primary"><%= insurance.getPersonalIndemnification().getSeparation() %><p>				  
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="personalProvisionLimit"> ���� ��� ���� �ѵ� </label> 
					<p class="text-primary"><%= insurance.getPersonalIndemnification().getProvisionLimit() %><p>	
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="personalGuaranteeContent"> ���� ��� ���� ���� </label> 
                  	<p class="text-primary"><%= insurance.getPersonalIndemnification().getGuaranteeContent() %><p>	
                </div>
              </div>
              
             <br>
             <p class="lead" align = "center" >�ڱ� ���� ����</p>
             
              <div class="row form-group">
                <div class="col-md-12">  
                  <label class="text-black" for="insuranceSalesManual">����</label>   
				  <p class="text-primary"><%= insurance.getSelfVehicleDamage().getSeparation() %><p>			  
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="SubscriptionFeeForInjury"> �ڱ� ���� ���� �λ� ���Աݾ� </label> 
				  <p class="text-primary"><%= insurance.getSelfVehicleDamage().getSubscriptionFeeForInjury() %><p>		
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="SubscriptionFeeForAccidentalInjuries"> �ڱ� ���� ���� ��� ���������� ���Աݾ� </label> 
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
          <h2 class="text-white">�������� ���ư���</h2>
          </div>
        </div>
      </div>  
    </a>
    
		<jsp:include page="/incl/Footer.jsp" />
	</body>
</html>