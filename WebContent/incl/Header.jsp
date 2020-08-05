<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"><%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">

<header
	class="site-navbar py-4 bg-white js-sticky-header site-navbar-target"
	role="banner">

	<div class="container">
		<div class="row align-items-center">

			<div class="col-11 col-xl-2">
				<h1 class="mb-0 site-logo">
					<a href="main.jsp" class="text-black h2 mb-0">����ȸ��<span
						class="text-primary">.</span>
					</a>
				</h1>
			</div>
			
			
			<div class="col-12 col-md-10 d-none d-xl-block">
				<nav class="site-navigation position-relative text-right"
					role="navigation">
					
					<ul class="site-menu main-menu js-clone-nav mr-auto d-none d-lg-block">
						<li class="has-children"><a href="InsuranceDesign.jsp" class="nav-link">��ǰ�����ϱ�</a>
							<ul class="dropdown">
								<li><a href = "InsuranceList"> ���� ����Ʈ </a></li>
								<li><a href = "FInsuranceDesign.jsp"> ȭ�纸�� </a></li>
								<li><a href = "CInsuranceDesign.jsp">�ڵ�������</a></li>
								<li><a href = "AInsuranceDesign.jsp">�Ǻ���</a></li>
							</ul>
	
						</li>

						<li class="has-children"><a href="AcceptanceGuide.jsp" class="nav-link">�μ���å�����ϱ�</a>
							<ul class="dropdown">
								<li><a href="ShowAcceptanceGuide">�μ���ħ�� ����</a></li>
								<li><a href="SearchNullAcceptanceGuide">�μ���ħ�� ����</a></li>
							</ul></li>

						<li class="has-children"><a href="" class="nav-link">����Ȱ��</a>
							<ul class="dropdown">
								<li><a href="ShowMenual.jsp">�޴��� Ȯ���ϱ�</a></li>
								<li><a href="Subscription.jsp">��ǰ ���� ��û�ϱ�</a></li>
							</ul></li>


						<li><a href="" class="nav-link">�μ��ɻ��ϱ�</a></li>


						<li class="has-children"><a href="" class="nav-link">�������ϱ�</a>
							<ul class="dropdown">
								<li><a href="">���ΰ����ϱ�</a></li>
								<li><a href="">����������ϱ�</a></li>
								<li><a href="">��༭�ۼ��ϱ�</a></li>
							</ul></li>

						<li class="has-children"><a href="InsuranceTreatment.jsp" class="nav-link">����ó���ϱ�</a>
							<ul class="dropdown">
								<li><a href="AccidentReception?action=showID">������ۼ��ϱ�</a></li>
								<li><a href="CalculateAccidentFund?action=showAccidentID">��������� �����ϱ�</a></li>
								<li><a href="PaymentAccidentFund?action=showAccidentID">��������� �����ϱ�</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>


			<div class="d-inline-block d-xl-none ml-md-0 mr-auto py-3"
				style="position: relative; top: 3px;">
				<a href="#" class="site-menu-toggle js-menu-toggle text-black"><span
					class="icon-menu h3"></span></a>
			</div>

		</div>
	</div>

</header>
