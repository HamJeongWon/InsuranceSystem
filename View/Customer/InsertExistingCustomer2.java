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

import Customer.ActualCost.BloodType;
import Customer.ActualCost.DiseaseHistory;
import Customer.Car.CarType;
import Customer.Car.LicenseType;
import DAO.insuranceDAO;
import DAO.customerDAO;
import DAO.subscriptionDAO;
import Insurance.Insurance;
import Subscription.Subscription;

@WebServlet("/InsertExistingCus2")
public class InsertExistingCustomer2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    customerDAO customerDAO;
    insuranceDAO insuranceDAO;
    subscriptionDAO subscriptionDAO;
    
    public InsertExistingCustomer2() {
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
        String url = "";
        Vector<Insurance> InsuVec = new  Vector<Insurance>();
        
        int CustomerID = Integer.parseInt(request.getParameter("CustomerID"));
        String InsuranceType = request.getParameter("InsuranceType");	
        
		if(InsuranceType.equals("Fire")){
			url = "/FirePersonalInformation.jsp";
			InsuVec = this.insuranceDAO.InsuranceNameVector("Fire");
			
		}else if(InsuranceType.equals("Car")){
			url = "/CarPersonalInformation.jsp";
			InsuVec = this.insuranceDAO.InsuranceNameVector("Car");
			
			Vector<CarType> VecCarType = new Vector<CarType>();
			for(CarType carType : Car.CarType.values()) {
				VecCarType.add(carType);
			}
			
			Vector<LicenseType> VecLicenseType = new Vector<LicenseType>();
			for(LicenseType licenseType : Car.LicenseType.values()) {
				VecLicenseType.add(licenseType);
			}
			
			request.setAttribute("carType", VecCarType);	
			request.setAttribute("licenseType", VecLicenseType);	
			
		}else if(InsuranceType.equals("ActualCost")){
			url = "/LifePersonalInformation.jsp";
			InsuVec = this.insuranceDAO.InsuranceNameVector("ActualCost");
			
			Vector<BloodType> VecBloodType = new Vector<BloodType>();
			for(BloodType bloodType : ActualCost.BloodType.values()) {
				VecBloodType.add(bloodType);
			}
			
			Vector<DiseaseHistory> VecDiseaseHistory = new Vector<DiseaseHistory>();
			for(DiseaseHistory diseaseHistory : ActualCost.DiseaseHistory.values()) {
				VecDiseaseHistory.add(diseaseHistory);
			}			
			request.setAttribute("VecBloodType", VecBloodType);	
			request.setAttribute("VecDiseaseHistory", VecDiseaseHistory);			
		}
		request.setAttribute("InsuVec", InsuVec);	
		request.setAttribute("CustomerID", CustomerID);
		
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);	
		}	
}
