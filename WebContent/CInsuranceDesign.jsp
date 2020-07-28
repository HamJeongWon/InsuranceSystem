<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> 자동차보험 설계 </title>
<link rel="stylesheet" href="css/radioButton.css">
</head>
<body> 
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />

<section class="site-section bg-light" id="contact-section">

      <div class="container">
      
        <div class="row mb-5">
          <div class="col-12 text-center">
            <h2 class="text-black h1 site-section-heading">자동차보험 설계</h2>
            <p class="lead">자동차보험의 기본정보와 상세정보를 입력</p>
          </div>
        </div>

            <form action= ./InsuranceDesgin?action=car method="post" class="p-5 bg-white" style = "margin:auto; max-width: 700px;">
              
              <h2 class="h4 text-black mb-5" align = "center"> 보험 기본 정보 </h2> 

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceName"> 보험 이름 </label> 
                  <input type="text" name ="insuranceName" class="form-control">
                </div>
              </div>

              <div class="row form-group">          
                <div class="col-md-12">
                  <label class="text-black" for="insuranceFee">보험료</label> 
                  <input type="text" name="insuranceFee" class="form-control">
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceManual">보험 설명서</label> 
                  <textarea name="insuranceManual" id="insuranceManual" cols="30" rows="7" class="form-control" placeholder="이 보험에 대한 설명을 작성하여 주십시오."></textarea>
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="insuranceSalesManual">판매 메뉴얼</label> 
                  <textarea name="insuranceSalesManual" id="insuranceSalesManual" cols="30" rows="7" class="form-control" placeholder="이 보험의 판매 tip을 작성하여 주십시오."></textarea>
                </div>
              </div>

              <br>
              <br>
              
              <h2 class="h4 text-black mb-5" align = "center"> 보험 상세 정보 </h2> 
              
              <p class="lead" align = "center" >대물 배상</p>
              
              <div class="row form-group">
                <div class="col-md-12">  
                  <label>
				    <input type="radio" class="option-input radio" value = "Death" name="goodsSeparation" checked />
				   	사망&nbsp; 
				  </label>
				  <label>
				    <input type="radio" class="option-input radio" value = "Injury" name="goodsSeparation" />
				   	부상&nbsp; 
				  </label>	
				  <label>
				    <input type="radio" class="option-input radio" value = "Aftereffect" name="goodsSeparation" />
				   	후유장해&nbsp; 
				  </label>		  
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="goodsGuaranteeLimit"> 대물 배상 지급 한도 </label> 
                  <input type="text" name="goodsGuaranteeLimit" class="form-control">
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="goodsGuaranteeContent"> 대물 배상 보장 내용 </label> 
                  <textarea name="goodsGuaranteeContent" id="goodsGuaranteeContent" cols="30" rows="7" class="form-control" placeholder="대물 배상에 대한 보장 내용을 작성하십시오."></textarea>
                </div>
              </div>
              
              <br>
              <p class="lead" align = "center" >대인 배상</p>

              <div class="row form-group">
                <div class="col-md-12">  
                  <label>
				    <input type="radio" class="option-input radio" value = "Death" name="personalSeparation" checked />
				   	사망&nbsp; 
				  </label>
				  <label>
				    <input type="radio" class="option-input radio" value = "Injury" name="personalSeparation" />
				   	부상&nbsp; 
				  </label>	
				  <label>
				    <input type="radio" class="option-input radio" value = "Aftereffect" name="personalSeparation" />
				   	후유장해&nbsp; 
				  </label>		  
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="personalProvisionLimit"> 대인 배상 지급 한도 </label> 
                  <input type="text" name="personalProvisionLimit" class="form-control">
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="personalGuaranteeContent"> 대인 배상 보장 내용 </label> 
                  <textarea name="personalGuaranteeContent" id="personalGuaranteeContent" cols="30" rows="7" class="form-control" placeholder="대인 배상에 대한 보장 내용을 작성하십시오."></textarea>
                </div>
              </div>
              
             <br>
             <p class="lead" align = "center" >자기 차량 손해</p>
             
              <div class="row form-group">
                <div class="col-md-12">  
                  <label>
				    <input type="radio" class="option-input radio" value = "SelfBodyAccident" name="selfVehicleSeparation" checked />
				   	자기 신체 사고&nbsp; 
				  </label>
				  <label>
				    <input type="radio" class="option-input radio" value = "CarInjury" name="selfVehicleSeparation" />
				   	자동차 상해
				  </label>			  
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="SubscriptionFeeForInjury"> 자기 차량 손해 부상 가입금액 </label> 
                  <input type="text" name="SubscriptionFeeForInjury" class="form-control">
                </div>
              </div>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="SubscriptionFeeForAccidentalInjuries"> 자기 차량 손해 사고 후유장해의 가입금액 </label> 
                  <input type="text" name="SubscriptionFeeForAccidentalInjuries" class="form-control">
                </div>
              </div>
              
              <br>
              <br>
              
              <div class="row form-group">
                <div class="col-md-12">
                  <input type="submit" value="설계 완료" class="btn btn-primary btn-md text-white" style="float: right;">
                  <input type="reset" value="다시 작성" class="btn btn-primary btn-md text-white">
                </div>
              </div>

            </form>   
          </div>
    </section>
    
        <section class="site-section bg-light" id="contact-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-12 text-center">
            <h2 class="text-black h1 site-section-heading">Contact Us</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-md-7 mb-5">

            

            <form action="#" class="p-5 bg-white">
              
              <h2 class="h4 text-black mb-5">Contact Form</h2> 

              <div class="row form-group">
                <div class="col-md-6 mb-3 mb-md-0">
                  <label class="text-black" for="fname">First Name</label>
                  <input type="text" id="fname" class="form-control">
                </div>
                <div class="col-md-6">
                  <label class="text-black" for="lname">Last Name</label>
                  <input type="text" id="lname" class="form-control">
                </div>
              </div>

              <div class="row form-group">
                
                <div class="col-md-12">
                  <label class="text-black" for="email">Email</label> 
                  <input type="email" id="email" class="form-control">
                </div>
              </div>

              <div class="row form-group">
                
                <div class="col-md-12">
                  <label class="text-black" for="subject">Subject</label> 
                  <input type="subject" id="subject" class="form-control">
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="message">Message</label> 
                  <textarea name="message" id="message" cols="30" rows="7" class="form-control" placeholder="Write your notes or questions here..."></textarea>
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <input type="submit" value="Send Message" class="btn btn-primary btn-md text-white">
                </div>
              </div>

  
            </form>
          </div>
          <div class="col-md-5">
            
            <div class="p-4 mb-3 bg-white">
              <p class="mb-0 font-weight-bold">Address</p>
              <p class="mb-4">203 Fake St. Mountain View, San Francisco, California, USA</p>

              <p class="mb-0 font-weight-bold">Phone</p>
              <p class="mb-4"><a href="#">+1 232 3235 324</a></p>

              <p class="mb-0 font-weight-bold">Email Address</p>
              <p class="mb-0"><a href="#">youremail@domain.com</a></p>

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