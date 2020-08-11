<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="Insurance.Insurance"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기존 고객의 가입할 페이지로 전달하기 </title>
</head>

<body>
	<% 	
		String InsuranceType = request.getParameter("InsuranceType");
		String CustomerID = request.getParameter("CustomerID");
		String url = "";
		
		if(InsuranceType.equals("Fire")){
			url = "FirePersonalInformation.jsp";
		}else if(InsuranceType.equals("Car")){
			url = "CarPersonalInformation.jsp";
		}else if(InsuranceType.equals("ActualCost")){
			url = "LifePersonalInformation.jsp";
		}
		
	%> 
	asdf
<%-- 	<jsp:forward page="<%=url %>">
		<jsp:param name="CustomerID" value= "<%= CustomerID%>"/>
	</jsp:forward> --%>

</body>
</html>