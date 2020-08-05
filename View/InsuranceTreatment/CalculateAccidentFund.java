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
import DAO.accidentDAO;
import DAO.insuranceDAO;
import DAO.subscriptionDAO;

/**
 * Servlet implementation class CalculateAccidentFund
 */
@WebServlet("/CalculateAccidentFund")
public class CalculateAccidentFund extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private accidentDAO accidentDAO;
	private insuranceDAO insuranceDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculateAccidentFund() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		this.insuranceDAO = new insuranceDAO();
		this.accidentDAO = new accidentDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");
		String action = request.getParameter("action");
		String url = null;
		
		System.out.println(action);
		
		if(action.equals("showAccidentID")) {
			request.setAttribute("accidentIDVector", accidentDAO.showAllAccidentIDFromCalculateAccidentFund());
			url = "/CalculateAccidentFund.jsp";
		}
		else if(action.equals("showAccidentInformation")) {
			int accidentID = Integer.parseInt(request.getParameter("accidentID"));
			Accident accident = this.accidentDAO.findAccident(accidentID);
			if(accident.getInsurancePremium() != 0 && accident.getInsurancePremiumCause() != null) {
				//이미 보험금이 산출되었을 경우
				url = "/error.jsp";
			}else {
				request.setAttribute("maxInsurancePremium",this.insuranceDAO.getInsuranceFee(accident.getInsuranceID()));
				request.setAttribute("accident", accident);
				url = "/InsertCalculateAccidentFund.jsp";
			}
		}
		else if(action.equals("insertCalculateAccidentFund")) {
			int accidentID = Integer.parseInt(request.getParameter("accidentID"));
			Accident accident = this.accidentDAO.findAccident(accidentID);

			String insurancePremium =request.getParameter("insurancePremium");
			String insurancePremiumCause = request.getParameter("insurancePremiumCause");
			
			if(insurancePremium=="" || insurancePremiumCause=="") {
				response.sendRedirect("incl/alert.jsp");
				return;
				
			}else {
				accident.setInsurancePremium(Integer.parseInt(insurancePremium));
				accident.setInsurancePremiumCause(insurancePremiumCause);

				this.accidentDAO.insertInsurancePayment(accident, accidentID);
				request.setAttribute("accident", accident);
				url = "ResultCalculateAccidentFund.jsp";
			}
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

}
