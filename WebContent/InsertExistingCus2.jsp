<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="Insurance.Insurance"%>
<%@page import="Subscription.Subscription"%>
<%@page import="Insurance.Insurance.InsuranceType"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기존 고객의 가입할 보험 선택 </title>
<style>
	.visibility {
	display:none;
	}
</style>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />
	
	<% 	String url = "";
		String FireRadio = null;
		String CarRadio = null;
		String ActualRadio = null;
		int CustomerID = (Integer)request.getAttribute("CustomerID");
	  	Vector<InsuranceType> VecInsuranceType = (Vector<InsuranceType>)request.getAttribute("VecInsuranceType");

	  	for(InsuranceType insuranceType : VecInsuranceType){
		   if(insuranceType.equals(Insurance.InsuranceType.Fire)){
			   FireRadio = "visibility";
		   }else if(insuranceType.equals(Insurance.InsuranceType.Car)){
			   CarRadio = "visibility";
		   }else if(insuranceType.equals(Insurance.InsuranceType.ActualCost)){
			   ActualRadio = "visibility";
		   }
	   }  	
	   %>
<form action = "InsertExistingCus3.jsp" method = post style = "padding-top : 200px;">                          
	<section class="site-section bg-light" id="contact-section">
      <div class="container">   
		 <div class="row mb-5">
          <div class="col-12 text-center">
            <h2 class="text-black h1 site-section-heading">가입할 보험 타입 선택하기</h2>
            <p class="lead">고객의 보험 타입 선택에 따라 해당 보험 가입이 가능하되 기존의 가입한 보험과 같은 타입은 보험가입은 불가하다</p>
          </div>
        </div>
        
	   <h2 class="h4 text-black mb-5" align = "center"> 가입 가능한 보험 타입 </h2> 
              
              <div class="row form-group" align = "center">
                <div class="col-md-12"> 
                  <label class="text-black" for="InsuranceType">보험 타입&nbsp; &nbsp; </label>  
                  <label class="<%= FireRadio%>">
				    <input type="radio" class="option-input radio"  value = "Fire" name="InsuranceType" checked />
				   	화재 보험&nbsp; 
				  </label>
				  <label class="<%= CarRadio%>">
				    <input type="radio" class="option-input radio"  value = "Car" name="InsuranceType" />
				   	자동차 보험&nbsp; 
				  </label>	
				   <label class="<%= ActualRadio%>">
				    <input type="radio" class="option-input radio"  value = "ActualCost" name="InsuranceType" />
				   	실비 보험&nbsp; 
				  </label>			  
                </div>
              </div>
				
			  <div class="row form-group">
                <div class="col-md-12">
                </div>
              </div>
              <input type = "hidden" name= CustomerID value = <%= CustomerID %>>
              <input type="submit" value="가입 시작" class="btn btn-primary btn-md text-white" style="float: right;">
             </div>
            </section>
         </form>
	<jsp:include page="/incl/Footer.jsp" />
		<jsp:include page="/incl/staticFooter.jsp" />
</body>
</html>