<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="Acceptance.AcceptanceGuide"%>
<%@ page import="Customer.Customer"%>
<%@ page import="Insurance.Insurance"%>
<%@ page import="Customer.Building"%>
<%@ page import="Customer.Car"%>
<%@ page import="Customer.ActualCost"%>
<%@ page import="Customer.PersonalInformation"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�μ��ɻ��ϱ�</title>
<jsp:include page="/incl/staticHeader.jsp" />
<jsp:include page="/incl/Header.jsp" />
<%
	int insuranceID = (int) request.getAttribute("insuranceID");
	AcceptanceGuide acceptanceGuide = (AcceptanceGuide) request.getAttribute("acceptanceGuide");
	Customer customer = (Customer) request.getAttribute("customer");
	PersonalInformation personalInformation = (PersonalInformation) request.getAttribute("personalInformation");
%>
</head>
<body>
	<section class="site-section bg-light" id="contact-section" style = "padding-top : 200px;">
		<c>
		<div class="container">
			<div class="row mb-5">
				<div class="col-12 text-center">
					<h2 class="text-black h1 site-section-heading">�μ��ɻ��ϱ�</h2>
				</div>
			</div>

			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="acceptanceID">�μ�ID:</label>
					${acceptanceGuide.acceptanceID}
				</div>
			</div>

			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="scamCase">�����:</label>
					${acceptanceGuide.scamCase}
				</div>
			</div>

			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="riskEvaluation">������:</label>
					${acceptanceGuide.riskEvaluation}
				</div>
			</div>

			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="customerID">��ID:</label>
					${customer.customerID }
				</div>
			</div>


			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="customerName">���̸�:</label>
					${customer.customerName }
				</div>
			</div>

			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="phoneNum">����ȭ��ȣ:</label>
					${customer.phoneNum }
				</div>
			</div>


			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="accidentHistory">�����Ż���̷�:</label>
					${personalInformation.accidentHistory}
				</div>
			</div>


			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="accountNumber">�����¹�ȣ:</label>
					${personalInformation.accountNumber}
				</div>
			</div>

			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="accidentHistory">������:</label>
					${personalInformation.job}
				</div>
			</div>

			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="accidentHistory">�����:</label>
					${personalInformation.property}
				</div>
			</div>

			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="accidentHistory">���ֹε�Ϲ�ȣ:</label>
					${personalInformation.residentRegistrationNumber}
				</div>
			</div>

			<%
		switch ((Insurance.InsuranceType) request.getAttribute("insuranceType")) {
		case Fire:
			Building building = (Building) request.getAttribute("insurance");%>
			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="buildingAddress">�ǹ��ּ�:</label>
					<%=building.getBuildingAddress()%>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="buildingPrice">�ǹ�����:</label>
					<%=building.getBuildingPrice() %>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="buildingScale">�ǹ��Ը�:</label>
					<%=building.getBuildingScale() %>
				</div>
			</div>
			<%break;
		case Car:
			Car car = (Car) request.getAttribute("insurance");%>
			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="carNumber">�� ��ȣ:</label>
					<%= car.getCarNumber() %>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="carType">�� ����:</label>
					<%=car.getCarType() %>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="drivingCareer">�������:</label>
					<%=car.getDrivingCareer() %>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="licenseType">��������:</label>
					<%=car.getLicenseType() %>
				</div>
			</div>




			<%	break;
		case ActualCost:
			ActualCost actualCost = (ActualCost) request.getAttribute("insurance");%>
			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="diseaseHistory">����:</label>
					<%=actualCost.getDiseaseHistory() %>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="familyHistory">��������:</label>
					<%=actualCost.getFamilyHistory() %>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-12">
					<label class="text-black" for="bloodType">������:</label>
					<%=actualCost.getBloodType() %>
				</div>
			</div>
			<%break;
		}
	%>


			<div class="row form-group">
				<div class="col-md-12">
					<form
						action=./AcceptInsuranceSubscription?action=acceptInsuranceSubscription
						method="post">
						<input type="hidden" name="customerID" value=<%=customer.getCustomerID()%>>
						<input type="hidden" name="insuranceID" value=<%=insuranceID%>>
						<input type="submit" value="����"
							class="btn btn-primary btn-md text-white" style="float: left;">
					</form>
					<form
						action=./AcceptInsuranceSubscription?action=declineInsuranceSubscription
						method="post">
						<input type="hidden" name="customerID" value=<%=customer.getCustomerID()%>>
						<input type="hidden" name="insuranceID" value=<%=insuranceID%>>
						<input type="submit" value="�ź�"
							class="btn btn-primary btn-md text-white" style="float: left;">
					</form>
				</div>
			</div>

		</div>
		</c>
	</section>

	<jsp:include page="/incl/Footer.jsp" />
		<jsp:include page="/incl/staticFooter.jsp" />
</body>
</html>