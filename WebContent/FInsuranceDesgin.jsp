<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>First Page</title>
</head>
<body>
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />

  <h2> 게 시 판 </h2>
  <br> <br>
 
<jsp:include page="/incl/InsuranceDesign.jsp" />


     <center>
        	<input type="submit" value="등록하기" >
        	<input type="reset" value="다시쓰기" >
      </center>

<jsp:include page="/incl/Footer.jsp" />

</body>
</html>