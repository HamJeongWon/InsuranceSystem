package InsuranceSubscription;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Acceptance.AcceptanceGuide;
import Customer.ActualCost;
import Customer.Building;
import Customer.Car;
import Customer.Customer;
import DAO.subscriptionDAO;
import DAO.acceptanceDAO;
import DAO.contractDAO;
import DAO.customerDAO;
/**
 * Servlet implementation class AcceptInsuranceSubscription
 */
@WebServlet("/AcceptInsuranceSubscription")
public class AcceptInsuranceSubscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private subscriptionDAO subscriptionDAO;
	private acceptanceDAO acceptanceDAO;
    private contractDAO contractDAO;
	private customerDAO customerDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptInsuranceSubscription() {
        super();
        // TODO Auto-generated constructor stub
    }
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		this.subscriptionDAO = new subscriptionDAO();
		this.acceptanceDAO = new acceptanceDAO();
		this.contractDAO = new contractDAO();
		this.customerDAO = new customerDAO();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");
		String action = request.getParameter("action");
		String url = null;
		if(action.equals("showID")) {
			request.setAttribute("IDVector", subscriptionDAO.showAcceptanceAprove());
			url = "/InsuranceSubscription.jsp";

		}else if(action.equals("showCustomerInformation")) {
			int num = Integer.parseInt(request.getParameter("num"));
			int index = num - 2;
			int insuranceID = subscriptionDAO.showAcceptanceAprove().get(index);
			int customerID = subscriptionDAO.showAcceptanceAprove().get(index+1);
			
		
			
			AcceptanceGuide acceptanceGuide = this.acceptanceDAO.findAcceptance(insuranceID);
			Customer customer = this.contractDAO.findCustomer(customerID);

			if (acceptanceGuide.getAcceptanceID() == 0) {
				acceptanceGuide.setAcceptanceID(5001);
			}
			request.setAttribute("insuranceID", insuranceID);
			request.setAttribute("acceptanceGuide", acceptanceGuide);
			request.setAttribute("customer", customer);
			request.setAttribute("personalInformation", this.customerDAO.findPersonalInformation(customer, insuranceID));
			request.setAttribute("insuranceType", this.customerDAO.getInsuranceType(insuranceID));
			
		
			// 보험 종류에 따라 고객 가장 아래 데이터 가져오기
			switch (this.customerDAO.getInsuranceType(insuranceID)) {
			case Fire: // 화재보험
				Building building = (Building) this.customerDAO.findPersonalInformation(customer, insuranceID);
				this.customerDAO.findBuildingCustomer(building, customerID);
				
				request.setAttribute("insurance", building);
				break;
			case Car:// 자동차보험
				Car car = (Car) this.customerDAO.findPersonalInformation(customer, insuranceID);
				this.customerDAO.findCarCustomer(car, customerID);
				
				request.setAttribute("insurance", car);
				break;
			case ActualCost:// 실비보험
				ActualCost actualCost = (ActualCost) this.customerDAO.findPersonalInformation(customer, insuranceID);
				this.customerDAO.findActualCostCustomer(actualCost, customerID);

				request.setAttribute("insurance", actualCost);
				break;
			}
			url = "JudgementInsuranceSubscription.jsp";
		}else if(action.equals("acceptInsuranceSubscription")) {
			int customerID = Integer.parseInt(request.getParameter("customerID"));
			int insuranceID = Integer.parseInt(request.getParameter("insuranceID"));

			this.subscriptionDAO.updateSubscriptionStatusFromWeb(customerID, insuranceID);
			url = "AcceptInsuranceSubscription.jsp";
		}else if(action.equals("declineInsuranceSubscription")) {
			int customerID = Integer.parseInt(request.getParameter("customerID"));
			int insuranceID = Integer.parseInt(request.getParameter("insuranceID"));

			this.subscriptionDAO.deleteSubscription(customerID, insuranceID);
			url = "DeclineInsuranceSubscription.jsp";
		}else if(action.equals("declineDone")) {
			String declineCause = request.getParameter("declineCause");
			if(declineCause == "") {
				response.sendRedirect("incl/alert.jsp");
				return;
			}else {
				url = "DeclineDoneInsuranceSubscription.jsp";
			}
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}



}
