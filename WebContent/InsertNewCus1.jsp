<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 고객 가입</title>
</head>
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />
<body>
	<% Integer newCustomerID = (Integer)request.getAttribute("newCustomerID"); %>
<script>
var patt = new RegExp("[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}");
var res = patt.test( $("#tlno").val());

if( !patt.test( $("#tlno").val()) ){
    alert("전화번호를 정확히 입력하여 주십시오.");
    return false;
}

</script>

<section class="site-section bg-light" id="contact-section" style = "padding-top : 200px;">

      <div class="container">
      
        <div class="row mb-5">
          <div class="col-12 text-center">
            <h2 class="text-black h1 site-section-heading">신규 고객 가입</h2>
            <p class="lead">신규 고객 정보 입력</p>
          </div>
        </div>
        
         <div>
            <form action= ./InsertNewCus method= POST class="p-5 bg-white" style = "margin:auto; max-width: 700px;">
              
              <h2 class="h4 text-black mb-5" align = "center"> 정보 입력 </h2> 
				
			 <div class="row form-group">
               <div class="col-md-12">
                  <label class="text-black">고객ID:&nbsp;</label> 
                  	<%=  newCustomerID%>
              		<input type="hidden" name="customerID"  value = <%= newCustomerID%> class="form-control">

               </div>         
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black"> 고객 이름 </label> 
                  <input type="text" name ="customerName" class="form-control">
                </div>
              </div>

              <div class="row form-group">          
                <div class="col-md-12">
                  <label class="text-black">고객 연락처</label> 
                   <input type="tel" class="form-control" name="phoneNum" id="tlno" placeholder="00*-000*-0000" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" maxlength="13">
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black">직업 선택</label>
                  <select name ="job" class="form-control">
        
                  </select>               
                 </div>                 
                 
                <div class="col-md-12">
                  <label class="text-black">사고 이력</label>
                  <input type="text" name ="accidentHistory" class="form-control">             
                 </div>
                 
                 <div class="col-md-12">
                  <label class="text-black">계좌번호</label>
                  <input type="Number" name ="accountNumber" class="form-control">             
                 </div>
                 
      			 <div class="col-md-12">
                  <label class="text-black">성별 &nbsp; &nbsp;</label>                
                   <label class="text-black">
				    <input type="radio" class="option-input radio"  value = "M" name="sex" checked />
				   	남자&nbsp; 
				  </label>
				  <label class="text-black">
				    <input type="radio" class="option-input radio"  value = "W" name="sex" />
				   	여자&nbsp; 
				  </label>	      
                 </div>
                               
                 
                 <div class="col-md-12">
                  <label class="text-black">자산</label>
                  <input type="Number" name ="property" class="form-control">             
                 </div>
                 
                 <div class="col-md-12">
                  <label class="text-black">주민번호</label>
                  <input type="text" name ="residentRegistrationNumber" class="form-control">             
                 </div>            
              </div>
              <br>    
                     
              <div class="row form-group">
                <div class="col-md-12">
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
			<jsp:include page="/incl/staticFooter.jsp" />
	</body>
</html>
