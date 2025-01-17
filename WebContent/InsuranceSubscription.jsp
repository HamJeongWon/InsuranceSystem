<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>인수심사하기</title>
</head>
<body>
	<jsp:include page="/incl/tableCSS.jsp" />
	<jsp:include page="/incl/staticHeader.jsp" />
	<jsp:include page="/incl/Header.jsp" />

	<svg class="hidden">
			<defs>
				<path id="tabshape" d="M80,60C34,53.5,64.417,0,0,0v60H80z" />
			</defs>
		</svg>
	<section class="site-section bg-light" id="contact-section" style = "padding-top : 200px;">
		<div class="container">
		<%	
		Vector<Integer> idVector = new Vector<Integer>();
		idVector = (Vector<Integer>) request.getAttribute("IDVector");
		if (idVector.size() != 0) { %>

			<div class="row mb-5">
				<div class="col-12 text-center">
					<h2 class="text-black h1 site-section-heading">인수심사하기</h2>
					<p class="lead">보험에 가입을 원하는 고객ID와 보험ID가 존재한다. 조회를 눌러 고객에 대한 인수
						심사를 하십시오.</p>
				</div>
			</div>
		</div>

		<div class="tabs tabs-style-shape">
			<nav>
				<ul>
					<li><a href="#section-shape-1"> <svg viewBox="0 0 80 60"
								preserveAspectRatio="none">
								<use xlink:href="#tabshape"></use></svg> <span>인수심사 해야 할 고객 리스트</span>
					</a></li>

				</ul>
			</nav>
			<div class="content-wrap">
				<section id="section-shape-1">
					<div class="container">
						<ul class="responsive-table">
							<li class="table-header">
								<div class="col col-1">보험 ID</div>
								<div class="col col-2">고객 ID</div>
								<div class="col col-3">조회</div>
							</li>
						<%
									//no = showacceptanceapprove에서 받은 백터의 index
									for (int no = 0; no < idVector.size(); no++) {
										if (no % 2 == 0) {
							%>
							<li class="table-row">
								<div class="col col-1" style="margin-top: 9px;">
									<%=idVector.get(no)%>
								</div> <%
 	} else {
 %>
								<div class="col col-2" style="margin-top: 9px;">
									<%=idVector.get(no)%>
								</div>

								<div class="col col-3">
									<form
										action=./AcceptInsuranceSubscription?action=showCustomerInformation
										method="post">
										<input type="hidden" name="num" value=<%=no + 1%>> <input
											type="submit" value="조회">
									</form>
								</div>
							</li>
							<%
								}
									}
							%>
						</ul>
					</div>
				</section>

			</div>
		</div>
		<%
			} else {
		%>
		<div class="row mb-5">
			<div class="col-12 text-center">
				<h2 class="text-black h1 site-section-heading">보험에 가입한 고객이 존재하지
					않습니다.</h2>
				<br> <br>
				<div class="row form-group">
					<div class="col-md-12">

						<!--  		<form action="main.jsp">

												<input type="submit" value="메인으로"
													class="btn btn-primary btn-md text-white"
													style="display: inline-block;">
											</form>
											<br>
											-->
						<form action=고객가입시킬servlet넣어야함 method="post">
							<input type="submit" value="고객등록하기"
								class="btn btn-primary btn-md text-white"
								style="display: inline-block;">
						</form>
					</div>
				</div>
			</div>
		</div>
		<%
			}
		%>




	</section>
	<script src="js/cbpFWTabs.js"></script>
	<script>
		(function() {

			[].slice.call(document.querySelectorAll('.tabs')).forEach(
					function(el) {
						new CBPFWTabs(el);
					});

		})();
	</script>

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