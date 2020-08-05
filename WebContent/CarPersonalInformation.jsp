<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");  %>
<%@page import="Insurance.Insurance"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> 기존 고객의 자동차 보험 가입 </title>
</head>
<body> 
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />

<% 	 Integer CustomerID = Integer.parseInt(request.getParameter("CustomerID")); %>

<section class="site-section bg-light" id="contact-section">

      <div class="container">
      
        <div class="row mb-5">
          <div class="col-12 text-center">
            <h2 class="text-black h1 site-section-heading">자동차 보험 가입</h2>
            <p class="lead">자동차 보험에 필요한 고객 정보 입력</p>
          </div>
        </div>
        
         <div>
            <form action= ./PersonalInfInsurance method= POST class="p-5 bg-white" style = "margin:auto; max-width: 700px;">
              
              <h2 class="h4 text-black mb-5" align = "center"> 자동차 보험 </h2> 
				
			 <div class="row form-group">
               <div class="col-md-12">
                  <label class="text-black" for="insuranceName">자동차 보험 이름 </label> 
              		<select name="insuranceName"  class="form-control">

   					</select>
               </div>         
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="carNumber"> 차 번호 </label> 
                  <input type="text" name ="carNumber" class="form-control">
                </div>
              </div>

              <div class="row form-group">          
                <div class="col-md-12">
                  <label class="text-black" for="carType">자동차 종류</label> 
                    <input type="text" name ="carNumber" class="form-control">
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="carCareer">운전 경력</label> 
                 <input type="Number" name ="carCareer" class="form-control" placeholder="년">
                 </div>
              </div>
              
              <div class="row form-group">          
                <div class="col-md-12">
                  <label class="text-black" for="licenseType">면허 종류</label> 
                    <input type="text" name ="licenseType" class="form-control">
                </div>
              </div>
              
              <br>    
                     
              <div class="row form-group">
                <div class="col-md-12">
                  <input type="hidden" name="CustomerID" value= <%= CustomerID %>>
                  <input type="hidden" name="InsuranceTypeForInsert" value="Car">
                  <input type="submit" value="입력 완료" class="btn btn-primary btn-md text-white" style="float: right;">
                  <input type="reset" value="다시 작성" class="btn btn-primary btn-md text-white">
                </div>
              </div>

            </form>
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