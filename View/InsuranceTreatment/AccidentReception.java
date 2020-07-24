package InsuranceTreatment;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
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
		// TODO Auto-generated method stub
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
			int index = Integer.parseInt(request.getParameter("index"));
			 
			int customerID = subscriptionDAO.showSubscriptionCustomer().get(index+1);
			int insuranceID = subscriptionDAO.showSubscriptionCustomer().get(index);
			
			Accident accident = new Accident();
			accident.setInsuranceID(insuranceID);
			accident.setCustomerID(customerID);
			
			int accidentID = this.insuranceDAO.SelectMaxID("accidentID", "Accident");
			if(accidentID == 0) {
				accidentID = 6000;
			}
			accidentID = accidentID+1;
			accident.setAccidentID(accidentID);
			
			String accidentDate = request.getParameter("accidentDate");
			accident.setAccidentDate(accidentDate);
			
			String accidentTime = String.valueOf(request.getParameter("accidentTime"));
			accident.setAccidentTime(accidentTime+":00");
			
			String accidentCause = request.getParameter("accidentCause");
			accident.setAccidentCause(accidentCause);
			
			String accidentLocation = request.getParameter("accidentLocation");
			accident.setAccidentLocation(accidentLocation);
			
			String expertOpinion = request.getParameter("expertOpinion");
			accident.setExpertOpinion(expertOpinion);
			
			//만약 request.get파라미터가 하나라도 null값일 경우 에러처리 해야함...에러처리는 좀 나중에
			
			
			this.accidentDAO.insertAccident(accident);
			request.setAttribute("accident", accident);
			url = "/ResultAccidentReception.jsp";
			
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
		

	}

}
