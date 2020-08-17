package Contract;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Contract.Contract.PaymentType;
import Customer.ActualCost;
import Customer.Car;
import Customer.PersonalInformation;
import DAO.contractDAO;
import DAO.customerDAO;
import DAO.subscriptionDAO;
import DAO.insuranceDAO;
import InsuranceSubscription.InsuranceSubscription;

@WebServlet("/MakeContract")
public class MakeContract extends HttpServlet {
	private static final long serialVersionUID = 1L;
	contractDAO contractDAO;
	customerDAO customerDAO;
	subscriptionDAO subscriptionDAO;
	InsuranceSubscription insuranceSubscription;
	insuranceDAO insuranceDAO;

	public MakeContract() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.contractDAO = new contractDAO();
		this.customerDAO = new customerDAO();
		this.insuranceDAO = new insuranceDAO();
		this.subscriptionDAO = new subscriptionDAO();
		this.insuranceSubscription = new InsuranceSubscription();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=euc-kr");

		String action = request.getParameter("action");
		String url = "";

		if (action.equals("List")) {
			url = "MakeContract1.jsp";

			Vector<Integer> IDVector = subscriptionDAO.showSubscriptionCustomer2();
			request.setAttribute("IDVector", IDVector);

		} else if (action.equals("Make")) {
			url = "MakeContract2.jsp";

			int contractID = customerDAO.SelectMaxID("contractID", "Contract") + 1;
			int insuranceID = Integer.parseInt(request.getParameter("insuranceID"));
			int customerID = Integer.parseInt(request.getParameter("customerID"));
			int paymentAmout = 0;

			
			  float insuranceFee = insuranceDAO.getInsuranceFee(insuranceID); String
			  insuranceType = customerDAO.getInsuranceType(insuranceID).toString();
			  PersonalInformation personalInformation =
			  customerDAO.findPersonalInformation2(customerID);
			  
			  if(insuranceType.equals("Car")) { Car car = customerDAO.getCar(customerID);
			  if(car == null) { url = "Error.jsp"; }else {
			  car.setJob(personalInformation.getJob());
			  car.setAccidentHistory(personalInformation.getAccidentHistory());
			  car.setAccountNumber(personalInformation.getAccountNumber());
			  car.setGender(personalInformation.getGender());
			  car.setProperty(personalInformation.getProperty());
			  car.setResidentRegistrationNumber(personalInformation.
			  getResidentRegistrationNumber());
			  
			  paymentAmout = (int)insuranceSubscription.CalculatePremiumRateCar(car,
			  insuranceFee); }
			  
			  }else if(insuranceType.equals("Fire")) { float buildingPrice =
			  customerDAO.getBuildingPrice(customerID); if(buildingPrice == 0) { url =
			  "Error.jsp"; }else { paymentAmout =
			  (int)insuranceSubscription.CalculatePremiumRateOfFire(buildingPrice,
			  insuranceFee); }
			  
			  }else { ActualCost actualCost = customerDAO.getActualCost(customerID);
			  if(actualCost == null) { url = "Error.jsp"; }else {
			  actualCost.setJob(personalInformation.getJob());
			  actualCost.setAccidentHistory(personalInformation.getAccidentHistory());
			  actualCost.setAccountNumber(personalInformation.getAccountNumber());
			  actualCost.setGender(personalInformation.getGender());
			  actualCost.setProperty(personalInformation.getProperty());
			  actualCost.setResidentRegistrationNumber(personalInformation.
			  getResidentRegistrationNumber());
			  
			  paymentAmout =
			  (int)insuranceSubscription.CalculatePremiumRateActual(actualCost,
			  insuranceFee);
			  
			  } }
			 
			request.setAttribute("contractID", contractID);
			request.setAttribute("customerID", customerID);
			request.setAttribute("insuranceID", insuranceID);
			request.setAttribute("paymentAmout", paymentAmout);

		} else if (action.equals("Finish")) {
			url = "MakeContract3.jsp";

			int insuranceID = Integer.parseInt(request.getParameter("insuranceID"));
			int customerID = Integer.parseInt(request.getParameter("customerID"));
			int contractID = Integer.parseInt(request.getParameter("contractID"));
			int paymentAmout = Integer.parseInt(request.getParameter("paymentAmout"));
			String contractExpirationDate = request.getParameter("contractExpirationDate");
			String paymentDate = request.getParameter("paymentDate");
			int paymentPeriod = Integer.parseInt(request.getParameter("paymentPeriod"));
			PaymentType paymentType = PaymentType.valueOf(request.getParameter("paymentType"));

			SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date JcontractExpirationDate = null;
			try {
				JcontractExpirationDate = (java.util.Date) SDF.parse(contractExpirationDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			java.util.Date JpaymentDate = null;
			try {
				JpaymentDate = (java.util.Date) SDF.parse(paymentDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(JcontractExpirationDate);
			cal.add(Calendar.YEAR, 3);
			java.util.Date JPersonalInformationRetentionPeriod = (java.util.Date) cal.getTime();

			Contract contract = new Contract();
			contract.setContractID(contractID);
			contract.setCustomerID(customerID);
			contract.setContractExpirationDate(JcontractExpirationDate);
			contract.setPaymentAmount(paymentAmout);
			contract.setPaymentDate(JpaymentDate);
			contract.setPaymentPeriod(paymentPeriod);
			contract.setPaymentStatus(true);
			contract.setPaymentType(paymentType);
			contract.setPersonalInformationRetentionPeriod(JPersonalInformationRetentionPeriod);
			this.contractDAO.createContract(contract);
			this.subscriptionDAO.insertContratIDtoSubscription(contractID, customerID, insuranceID);
			
			request.setAttribute("contract", contract);
		}

		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

}
