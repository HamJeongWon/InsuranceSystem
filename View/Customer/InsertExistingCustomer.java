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
import Insurance.Insurance.InsuranceType;
import Subscription.Subscription;

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
		Vector<Subscription> VecSubscription= this.subscriptionDAO.SubscriptionVector();
		Vector<Insurance> VecInsurance = new Vector<Insurance>();
		
		for(Subscription Sub : VecSubscription) {
			Insurance insurance = insuranceDAO.InsuranceForID(Sub.getInsuranceID());
			VecInsurance.add(insurance);
		}
		
		request.setAttribute("VecSubscription", VecSubscription);
		request.setAttribute("VecInsurance", VecInsurance);
		
		RequestDispatcher disp = request.getRequestDispatcher("/InsertExistingCus1.jsp");
		disp.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");	
		int CustomerID = Integer.parseInt(request.getParameter("CustomerID"));
		Vector<InsuranceType> VecInsuranceType = this.subscriptionDAO.InsuranceTypeVector(CustomerID);
		
		request.setAttribute("CustomerID", CustomerID);
		request.setAttribute("VecInsuranceType", VecInsuranceType);
		
		RequestDispatcher disp = request.getRequestDispatcher("/InsertExistingCus2.jsp");
		disp.forward(request, response);	
		}	
}
