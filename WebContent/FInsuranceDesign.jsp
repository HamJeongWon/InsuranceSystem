<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> ���� �⺻ ���� �Է� ȭ�� </title>
</head>
<body> 
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />

	<div align = "center" >
    	<form action = ./FInsuranceDesgin method="post">
			<jsp:include page="/incl/BasicInsuranceDesign.jsp" />
			
			<h3> �� ���� </h3>
			<table> 
				<thead>	
					<tr><td align = "center" colspan = "2"> ���� ���� </td></tr>
				</thead>
				<tbody>          		
					<tr>
	            		<td> ���� ���� ����� : </td>
	            		<td> <input type="text" name="directGuaranteedAmount" size = "30"> </td>
	          		</tr>
	          		<tr>
	            		<td style="vertical-align:top"> ���� ���� ���� ����  :  </td>
	            		<td><textarea name="directGuaranteedContent" cols="70" rows="5" placeholder="���ؿ� ���� ���� ���뿡 ���� �ۼ��Ͽ� �ֽʽÿ�."></textarea></td>
	          		</tr>
          		</tbody>
        	</table>
        	
        	<table> 
				<thead>	
					<tr><td align = "center" colspan = "2"> �ҹ� ���� </td></tr>
				</thead>
				<tbody>          		
					<tr>
	            		<td> �ҹ� ���� ����� : </td>
	            		<td> <input type="text" name="fireGuaranteedAmount" size = "30"> </td>
	          		</tr>
	          		<tr>
	            		<td style="vertical-align:top"> �ҹ� ���� ���� ����  :  </td>
	            		<td><textarea name="fireGuaranteedContent" cols="70" rows="5" placeholder="���ؿ� ���� ���� ���뿡 ���� �ۼ��Ͽ� �ֽʽÿ�."></textarea></td>
	          		</tr>
          		</tbody>
        	</table>
        	
			<table> 
				<thead>	
					<tr><td align = "center" colspan = "2"> �ǳ� ���� </td></tr>
				</thead>
				<tbody>          		
					<tr>
	            		<td> �ǳ� ���� ����� : </td>
	            		<td> <input type="text" name="refugeGuaranteedAmount" size = "30"> </td>
	          		</tr>
	          		<tr>
	            		<td style="vertical-align:top"> �ǳ� ���� ���� ����  :  </td>
	            		<td><textarea name="refugeGuaranteedContent" cols="70" rows="5" placeholder="���ؿ� ���� ���� ���뿡 ���� �ۼ��Ͽ� �ֽʽÿ�."></textarea></td>
	          		</tr>
          		</tbody>
        	</table>
      		<input type="submit" value="����ϱ�">
      		<input type="reset" value="�ٽþ���">
    	</form>
    </div>
	

<jsp:include page="/incl/Footer.jsp" />
	</body>
</html>