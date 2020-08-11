package Contract;

import java.io.IOException;
import java.sql.Date;
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
import Customer.PersonalInformation;
import DAO.contractDAO;
import DAO.customerDAO;

@WebServlet("/Recontract")
public class Recontract extends HttpServlet {
	private static final long serialVersionUID = 1L;
	contractDAO contractDAO;
	customerDAO customerDAO;
	
    public Recontract() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.contractDAO = new contractDAO();
		this.customerDAO = new customerDAO();
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
        String url = "";

		if(action.equals("start")) {
			url = "/recontract.jsp";
			int customerID = Integer.parseInt(request.getParameter("customerID"));
			int contractID = Integer.parseInt(request.getParameter("contractID"));
			
			PersonalInformation personalInformation = customerDAO.findPersonalInformation2(customerID);
			int accountNumber = personalInformation.getAccountNumber();
			Contract contract = contractDAO.searchContract(contractID);
			
			request.setAttribute("contractID", contractID);
			request.setAttribute("customerID", customerID);		
			request.setAttribute("paymentDate", contract.getPaymentDate());		
			request.setAttribute("paymentType", contract.getPaymentType());		
			request.setAttribute("contractExpirationDate", contract.getContractExpirationDate());		
			request.setAttribute("accountNumber", accountNumber);
			
		}else if(action.equals("finish")) {
			url = "/recontract2.jsp";
			int customerID = Integer.parseInt(request.getParameter("customerID"));
			int contractID = Integer.parseInt(request.getParameter("contractID"));
			
			String paymentDate = request.getParameter("paymentDate");
			PaymentType paymentType = PaymentType.valueOf(request.getParameter("paymentType"));
			String contractExpirationDate = request.getParameter("contractExpirationDate");
			int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
			
			SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	
			java.util.Date JpaymenDate = null;		
			try {
				JpaymenDate = (java.util.Date) SDF.parse(paymentDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			java.util.Date JContractExpirationDate = null;
			try {
				JContractExpirationDate = (java.util.Date) SDF.parse(contractExpirationDate);
			} catch (ParseException e) {	
				e.printStackTrace();
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(JContractExpirationDate);
			cal.add(Calendar.YEAR, 3);
			
			java.util.Date JPersonalInformationRetentionPeriod = (java.util.Date) cal.getTime();
	
			Contract contract = new Contract();		
			contract.setContractID(contractID);		
			contract.setPaymentDate(JpaymenDate);
			contract.setPaymentType(paymentType);
			contract.setContractExpirationDate(JContractExpirationDate);
			contract.setPersonalInformationRetentionPeriod(JPersonalInformationRetentionPeriod);
			
			customerDAO.updatePersonalInformation(customerID, accountNumber);
			contractDAO.updateContract(contract);
			
			request.setAttribute("accountNumber", accountNumber);
			request.setAttribute("customerID", customerID);
			request.setAttribute("contract", contract);
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);	
	}

}
