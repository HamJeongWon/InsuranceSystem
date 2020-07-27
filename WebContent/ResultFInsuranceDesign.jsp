<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("EUC-KR");  %>
<%@ page import="Insurance.FireInsurance"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> ȭ�纸�� ������ </title>
</head>
<body> 
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />
<%FireInsurance insurance = (FireInsurance)request.getAttribute("FInsurance"); %>
<%System.out.println(insurance.getInsuranceID()); %>

<%if (typeofjQuery == 'undefined') {%>

alert("����");

<%}else{%>

alert("����");

<%}%>

${insurance.insuranceID}

<section class="site-section bg-light" id="contact-section">

      <div class="container">
      
        <div class="row mb-5">
          <div class="col-12 text-center">
            <h2 class="text-black h1 site-section-heading">ȭ�纸�� ����Ϸ�!</h2>
            <p class="lead">������ ȭ�纸���� �����Դϴ�.</p>
            ${insurance}
          </div>
        </div>

            <div class="p-5 bg-white" style = "margin:auto; max-width: 700px;">
              
              <h2 class="h4 text-black mb-5" align = "center"> ���� �⺻ ���� </h2> 
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceID"> ���� ID </label> 
                  ${insurance.insuranceID}
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceName"> ���� �̸� </label> 
                  ${insurance.insuranceName}
                </div>
              </div>

              <div class="row form-group">          
                <div class="col-md-12">
                  <label class="text-black" for="insuranceFee">�����</label> 
                  ${insurance.insuranceFee}
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceManual">���� ����</label> 
                  ${insurance.insuranceManual}
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceSalesManual">�Ǹ� �޴���</label> 
                  ${insurance.insuranceSalesManual}
                </div>
              </div>

              <br>
              <br>
              
              <h2 class="h4 text-black mb-5" align = "center"> ���� �� ���� </h2> 
              
              <p class="lead" align = "center" >���� ����</p>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="directGuaranteedAmount">���� ���� �����</label> 
                  ${insurance.directDamage.damageGuaranteedAmount}
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="directGuaranteedContent">���� ���� ���� ����</label> 
                  ${insurance.directDamage.damageGuaranteedContent}
                </div>
              </div>
              
              <br>
              <p class="lead" align = "center" >�ҹ� ����</p>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="fireGuaranteedAmount"> �ҹ� ���� �����</label> 
                  ${insurance.fireDamage.damageGuaranteedAmount}
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="fireGuaranteedContent"> �ҹ� ���� ���� ����</label> 
                  ${insurance.fireDamage.damageGuaranteedContent}
                </div>
              </div>
              
             <br>
             <p class="lead" align = "center" >�ǳ� ����</p>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="refugeGuaranteedAmount">�ǳ� ���� �����</label> 
                ${insurance.refugeDamage.damageGuaranteedAmount}                
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="refugeGuaranteedContent">�ǳ� ���� ���� ����</label> 
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
          <h2 class="text-white">�������� ���ư���</h2>
          </div>
        </div>
      </div>  
    </a>
    
		<jsp:include page="/incl/Footer.jsp" />
	</body>
</html>