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

import DAO.insuranceDAO;
import DAO.customerDAO;
import DAO.subscriptionDAO;
import Insurance.Insurance;

@WebServlet("/InsertExistingCus")
public class InsertExistingCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    customerDAO customerDAO;
    insuranceDAO insuranceDAO;
    subscriptionDAO subscriptionDAO;
    
    public InsertExistingCustomer() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.customerDAO = new customerDAO();
		this.insuranceDAO = new insuranceDAO();
		this.subscriptionDAO = new subscriptionDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vector<Integer> VecCustomer= this.customerDAO.showAllCustomerIDList();
		request.setAttribute("VecCustomer", VecCustomer);
		RequestDispatcher disp = request.getRequestDispatcher("/InsertCustomerID.jsp");
		disp.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");	
        
        boolean flag = true;     
		int CustomerID = Integer.parseInt(request.getParameter("CustomerID"));
		Vector<Integer> VecInsuranceID = subscriptionDAO.getInsuranceID(CustomerID);
		String InsuranceType = request.getParameter("InsuranceType");
		
		Vector<Insurance> VecInsurance = null;
		String message = "";
		String url = "/";
		
		if(insuranceDAO.CheckIntData("customerID", "Customer", CustomerID) == false) {
			message = "해당하는 고객의 ID가 존재하지 않습니다.";	
			url = "/Error.jsp";		
		}	
		
		for(Integer id : VecInsuranceID) {
			if(customerDAO.getInsuranceType(id).toString().equals(InsuranceType)) {flag = false;}
		}		
		
		if(!flag) {
			message = "해당하는 고객은 이미 같은 타입의 보험에 가입한 고객입니다.";	
			url = "/Error.jsp";		
			
		}else {
			if(InsuranceType.equals("Fire")) {	
				VecInsurance= this.insuranceDAO.InsuranceNameVector("Fire");
				url = "/FirePersonalInformation.jsp";	
				
			}else if(InsuranceType.equals("Car")) {
				VecInsurance= this.insuranceDAO.InsuranceNameVector("Car");
				url ="/CarPersonalInformation.jsp";	
			
			}else if(InsuranceType.equals("ActualCost")) {
				VecInsurance= this.insuranceDAO.InsuranceNameVector("ActualCost");
				url = "/LifePersonalInformation.jsp";
			
			}
			else {
				message = "보험 타입을 선택하지 않았습니다.";		
				url = "/Error.jsp";		
			}	
		}	
		request.setAttribute("CustomerID", CustomerID);
		request.setAttribute("VecInsurance", VecInsurance);
		request.setAttribute("message", message);	
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);		
	}
}
