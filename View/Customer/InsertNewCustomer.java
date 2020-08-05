package Customer;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import DAO.insuranceDAO;
import Insurance.Insurance.InsuranceType;
import DAO.customerDAO;

@WebServlet("/InsertNewCus")
public class InsertNewCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	insuranceDAO insuranceDAO; 
	customerDAO customerDAO;
	
    public InsertNewCustomer() {
        super(); 
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.insuranceDAO = new insuranceDAO();
		this.customerDAO = new customerDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int newCustomerID = insuranceDAO.SelectMaxID("customerID", "Customer") + 1;
		request.setAttribute("newCustomerID", newCustomerID);
		RequestDispatcher disp = request.getRequestDispatcher("/InsertNewCus1.jsp");
		disp.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerID = Integer.parseInt(request.getParameter("customerID"));
		System.out.println(customerID);
		Customer customer = new Customer();	
		customer.setCustomerID(customerID);
		customer.setCustomerName(request.getParameter("customerName"));
		customer.setPhoneNum(request.getParameter("phoneNum"));
		
		customerDAO.insertCustomer(customer);
		
		PersonalInformation personalInformation = new PersonalInformation();		
		//personalInformation.setJob(PersonalInformation.Job.valueOf(request.getParameter("job")));
		personalInformation.setJob(PersonalInformation.Job.valueOf("soldier"));
		personalInformation.setAccidentHistory(request.getParameter("accidentHistory"));
		personalInformation.setAccountNumber(Integer.parseInt(request.getParameter("accountNumber")));	
		personalInformation.setProperty(Integer.parseInt(request.getParameter("property")));
		personalInformation.setResidentRegistrationNumber(request.getParameter("residentRegistrationNumber"));
		
		String sex = request.getParameter("sex");
			if(sex.equals("M")) {personalInformation.setGender(true);}
			else {personalInformation.setGender(false);}		
			
		customerDAO.insertPersonalInformation(personalInformation, customerID);
		
		Vector<InsuranceType> VecInsuranceType = new Vector<InsuranceType>();
		
		request.setAttribute("CustomerID", customerID);
		request.setAttribute("VecInsuranceType", VecInsuranceType);
		
		RequestDispatcher disp = request.getRequestDispatcher("/InsertExistingCus2.jsp");
		disp.forward(request, response);	
	}

}
