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
<input type="submit" value="��ȸ" align="center">
</form>
<table border="1" width="500" height="100" align="center">
<tr align="center">
<%if(request.getParameter("IDVector")!=null){ %>
<th>��ID</th>
<th>����ID</th>
<th>��ȸ</th>
</tr>
<tr align="center">
<td>1</td>
<td>1</td>
<td>1</td>
</tr>
</table>
<%}else{%>
<h1>����</h1>
<%} %>
<jsp:include page="/incl/Footer.jsp" />

</body>
</html>