package Customer;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.customerDAO;
import DAO.insuranceDAO;
import DAO.subscriptionDAO;

/**
 * Servlet implementation class InsertPersonalInforToInsurance
 */
@WebServlet("/PersonalInfInsurance")
public class InsertPersonalInforToInsurance extends HttpServlet {
	private static final long serialVersionUID = 1L;
    customerDAO customerDAO;
    subscriptionDAO subscriptionDAO;
    
    public InsertPersonalInforToInsurance() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.customerDAO = new customerDAO();
		this.subscriptionDAO = new subscriptionDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");	
           
        String InsuranceType = request.getParameter("InsuranceTypeForInsert");
        int CustomerID = Integer.parseInt(request.getParameter("CustomerID"));
        int InsuranceID = Integer.parseInt(request.getParameter("InsuranceID"));
        subscriptionDAO.insertSubscription(InsuranceID, CustomerID);	
    	
        if(InsuranceType.equals("Fire")) {	
            Building building = new Building();
            building.setBuildingAddress(request.getParameter("buildingAddress"));
            building.setBuildingPrice(Integer.parseInt(request.getParameter("buildingPrice")));
            building.setBuildingScale(request.getParameter("buildingScale"));       
            customerDAO.insertBuilding(building, CustomerID);
   
		}else if(InsuranceType.equals("Car")) {
			
		}else if(InsuranceType.equals("ActualCost")) {
			
		}
        response.sendRedirect("main.jsp");

	}
}