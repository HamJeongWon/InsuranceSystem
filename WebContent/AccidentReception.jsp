<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>AccidentReception</title>
</head>
<body>
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />

<form action="/AccidentReception" method="post">
<input type="submit" value="조회" align="center">
</form>
<table border="1" width="500" height="100" align="center">
<tr align="center">
<%if(request.getParameter("IDVector")!=null){ %>
<th>고객ID</th>
<th>보험ID</th>
<th>조회</th>
</tr>
<tr align="center">
<td>1</td>
<td>1</td>
<td>1</td>
</tr>
</table>
<%}else{%>
<h1>ㅇㅇ</h1>
<%} %>
<jsp:include page="/incl/Footer.jsp" />

</body>
</html>