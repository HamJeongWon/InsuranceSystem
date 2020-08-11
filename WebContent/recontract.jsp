<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import ="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재계약 하기</title>
</head>
	<% 
	   Integer contractID = (Integer)request.getAttribute("contractID"); 
	   Integer customerID = (Integer)request.getAttribute("customerID");
	   Date paymentDate = (Date)request.getAttribute("paymentDate");
	   Date contractExpirationDate = (Date)request.getAttribute("contractExpirationDate");
	   Integer accountNumber = (Integer)request.getAttribute("accountNumber");
	%>
	
<!-- <script>
function check(){
	frm = document.frm;
	if(frm.paymentDate.value==""){
		
	}else if(frm.accountNumber.value==""){
		frm.accountNumber.value == accountNumber;
	}else if(frm.paymentDate.value==""){
		
	}
}
</script> -->
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />


<section class="site-section bg-light" id="contact-section">

      <div class="container">
      
        <div class="row mb-5">
          <div class="col-12 text-center">
            <h2 class="text-black h1 site-section-heading">재계약 진행하기</h2>
            <p class="lead">계약 정보 수정</p>
          </div>
        </div>
        
         <div>
            <form action= Recontract?action=finish method= POST name = frm class="p-5 bg-white" style = "margin:auto; max-width: 700px;">
              
              <h2 class="h4 text-black mb-5" align = "center">계약 정보 수정 </h2> 
			
			<div class="row form-group">
               <div class="col-md-12">
                  <label class="text-black">계약ID </label> 
                  	<%=  contractID%>
                  	<input type = hidden name = "contractID" value = <%= contractID%>>
               </div>         
              </div>
              
			 <div class="row form-group">
               <div class="col-md-12">
                  <label class="text-black">고객ID </label> 
                  	<%=  customerID%>          
                  	<input type = hidden name = "customerID" value = <%= customerID%>>	
               </div>         
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black">납입 날짜 </label> 
                  <input type="date" name ="paymentDate" value= "<%= paymentDate%>" class="form-control">
                </div>
              </div>

              <div class="row form-group">          
                <div class="col-md-12">
                  <label class="text-black">납입방식</label>    
             	  <select name ="paymentType" class="form-control">
             	  	<option value="creditCard">신용카드</option>
					<option value="e_bancking">인터넷뱅킹</option>
					<option value="accountTransfer">계좌이체</option>
             	  </select>
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black">계약만료일</label>
                  <input type=date name ="contractExpirationDate" value= "<%= contractExpirationDate%>" class="form-control">            
                 </div>                 
               </div>
               
               <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black">계좌번호</label>
                  <input type="Number" name ="accountNumber" placeholder=<%= accountNumber%> value = <%= accountNumber%> class="form-control">             
                </div>                     	
               </div>
                       
              <div class="row form-group">
                <div class="col-md-12">
                  <input type="submit" value="입력 완료" class="btn btn-primary btn-md text-white" onclick = "check()" style="float: right;">
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
	
	
</body>
</html>