<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Insurance.Insurance"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매 매뉴얼 확인하기</title>
</head>
<body>
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />
		

<section class="site-section bg-light" id="contact-section" style = "padding-top : 200px;"> 
	<div class="container">
	<div class="row mb-5">
		<div class="col-12 text-center">
		<h2 class="text-black h1 site-section-heading">메뉴얼 확인하기</h2>
			<br>
			<br>

			<table class = "table table-striped">
				<thead>
					<tr>
						<td>보험 id</td>
						<td>보험 이름</td>
						<td>판매 매뉴얼</td>
						<td>상품 매뉴얼</td>
					</tr>
				</thead>	
				<tbody>
				<% Vector<Insurance> VecInsurance = (Vector<Insurance>)request.getAttribute("VecInsurance");
					for(Insurance insu : VecInsurance) {
					%>
					<tr><td><%=  insu.getInsuranceID()%></td>
					<td><%=  insu.getInsuranceName()%></td>							
					<td> <form action= ./Menual1 method=POST> 
					 	 <input type="hidden" name="InsuranceID" value =<%= insu.getInsuranceID()%>>
					 	 <input type="hidden" name="InsuranceName" value =<%= insu.getInsuranceName()%>>
					 	 <input type="submit" name = "menual"  class="btn btn-primary" value="판매 메뉴얼 조회">
					 	 </form>
					 	 </td>
			 		<td> <form action= ./Menual1 method=POST>
			 			 <input type="hidden" name="InsuranceID" value = <%=insu.getInsuranceID() %>>
					 	 <input type="hidden" name="InsuranceName" value = <%=insu.getInsuranceName() %>>
					 	 <input type="submit" name = "menual"  class="btn btn-primary" value="상품 메뉴얼 조회">
					 	 </form>
					 	 </td></tr>
						
				<%	
				}
				%>			
				</tbody>
			</table>
		 
		</div>
	</div>
  </div>
 </section>
		
	
	<jsp:include page="/incl/Footer.jsp" />
	<jsp:include page="/incl/staticFooter.jsp" />
</body>
</html>