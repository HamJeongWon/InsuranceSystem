package InsuranceTreatment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Accident.Accident;
import DAO.subscriptionDAO;
import DAO.insuranceDAO;
import DAO.accidentDAO;


@WebServlet("/AccidentReception")
public class AccidentReception extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private subscriptionDAO subscriptionDAO;
	private insuranceDAO insuranceDAO;
	private accidentDAO accidentDAO;

	public AccidentReception() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		this.subscriptionDAO = new subscriptionDAO();
		this.insuranceDAO = new insuranceDAO();
		this.accidentDAO = new accidentDAO();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");
		String action = request.getParameter("action");
		String url = null;
		
		//System.out.println(action);
		
		if(action.equals("showID")) {
			request.setAttribute("IDVector", subscriptionDAO.showSubscriptionCustomer());
			url = "/AccidentReception.jsp";
		}
		else if(action.equals("insertAccidentReception")) {
			int num = Integer.parseInt(request.getParameter("num"));
			int index = num - 2;
			 
			int customerID = subscriptionDAO.showSubscriptionCustomer().get(index+1);
			int insuranceID = subscriptionDAO.showSubscriptionCustomer().get(index);
			
			Accident accident = new Accident();
			
			
			int accidentID = this.insuranceDAO.SelectMaxID("accidentID", "Accident");
			if(accidentID == 0) {
				accidentID = 6000;
			}
			accidentID = accidentID+1;
			
			
			String accidentDate = request.getParameter("accidentDate");
			String accidentTime = String.valueOf(request.getParameter("accidentTime"));
			String accidentCause = request.getParameter("accidentCause");
			String accidentLocation = request.getParameter("accidentLocation");
			String expertOpinion = request.getParameter("expertOpinion");
			
			if(accidentDate =="" || accidentTime=="" || accidentCause=="" || accidentLocation == "" || expertOpinion == "") {
				response.sendRedirect("incl/alert.jsp");
				return;
				
			}else {
				accident.setInsuranceID(insuranceID);
				accident.setCustomerID(customerID);
				accident.setAccidentID(accidentID);
				accident.setAccidentDate(accidentDate);
				accident.setAccidentTime(accidentTime+":00");
				accident.setAccidentCause(accidentCause);
				accident.setAccidentLocation(accidentLocation);
				accident.setExpertOpinion(expertOpinion);

				this.accidentDAO.insertAccident(accident);
				request.setAttribute("accident", accident);
				url = "/ResultMentAccidentReception.jsp";
			}		
		}else if(action.equals("showResultAccidentReception")) {
			int accidentID = Integer.parseInt(request.getParameter("accidentID"));
			Accident accident = this.accidentDAO.findAccident(accidentID);
			System.out.println(accident.getAccidentCause());
			System.out.println(accident.getAccidentID());
			System.out.println(accident.getCustomerID());
			request.setAttribute("accident", accident);
			url = "/ResultAccidentReception.jsp";
			
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);


	}

}
