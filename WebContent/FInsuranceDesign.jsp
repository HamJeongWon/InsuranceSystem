<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> 보험 기본 정보 입력 화면 </title>
</head>
<body> 
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />

	<div align = "center" >
    	<form action = ./FInsuranceDesgin method="post">
			<jsp:include page="/incl/BasicInsuranceDesign.jsp" />
			
			<h3> 상세 정보 </h3>
			<table> 
				<thead>	
					<tr><td align = "center" colspan = "2"> 직접 손해 </td></tr>
				</thead>
				<tbody>          		
					<tr>
	            		<td> 직접 손해 보장액 : </td>
	            		<td> <input type="text" name="directGuaranteedAmount" size = "30"> </td>
	          		</tr>
	          		<tr>
	            		<td style="vertical-align:top"> 직접 손해 보장 내용  :  </td>
	            		<td><textarea name="directGuaranteedContent" cols="70" rows="5" placeholder="손해에 대한 보장 내용에 대해 작성하여 주십시오."></textarea></td>
	          		</tr>
          		</tbody>
        	</table>
        	
        	<table> 
				<thead>	
					<tr><td align = "center" colspan = "2"> 소방 손해 </td></tr>
				</thead>
				<tbody>          		
					<tr>
	            		<td> 소방 손해 보장액 : </td>
	            		<td> <input type="text" name="fireGuaranteedAmount" size = "30"> </td>
	          		</tr>
	          		<tr>
	            		<td style="vertical-align:top"> 소방 손해 보장 내용  :  </td>
	            		<td><textarea name="fireGuaranteedContent" cols="70" rows="5" placeholder="손해에 대한 보장 내용에 대해 작성하여 주십시오."></textarea></td>
	          		</tr>
          		</tbody>
        	</table>
        	
			<table> 
				<thead>	
					<tr><td align = "center" colspan = "2"> 피난 손해 </td></tr>
				</thead>
				<tbody>          		
					<tr>
	            		<td> 피난 손해 보장액 : </td>
	            		<td> <input type="text" name="refugeGuaranteedAmount" size = "30"> </td>
	          		</tr>
	          		<tr>
	            		<td style="vertical-align:top"> 피난 손해 보장 내용  :  </td>
	            		<td><textarea name="refugeGuaranteedContent" cols="70" rows="5" placeholder="손해에 대한 보장 내용에 대해 작성하여 주십시오."></textarea></td>
	          		</tr>
          		</tbody>
        	</table>
      		<input type="submit" value="등록하기">
      		<input type="reset" value="다시쓰기">
    	</form>
    </div>
	

<jsp:include page="/incl/Footer.jsp" />
	</body>
</html>