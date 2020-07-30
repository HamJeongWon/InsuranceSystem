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

/**
 * Servlet implementation class FundAccidentFund
 */
@WebServlet("/PaymentAccidentFund")
public class PaymentAccidentFund extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private accidentDAO accidentDAO;
	private insuranceDAO insuranceDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentAccidentFund() {
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
			request.setAttribute("accidentIDVector", accidentDAO.showAllAccidentIDFromPaymentAccidentFund());
			url = "/PaymentAccidentFund.jsp";
		}
		else if(action.equals("showAccidentInformation")) {
			int accidentID = Integer.parseInt(request.getParameter("accidentID"));
			Accident accident = this.accidentDAO.findAccident(accidentID);

			request.setAttribute("accident", accident);
			url = "/ResultPaymentAccidentFund.jsp";

		}
		else if(action.equals("insertPaymentAccidentFund")) {
			int accidentID = Integer.parseInt(request.getParameter("accidentID"));
			Accident accident = this.accidentDAO.findAccident(accidentID);

			accident.setPayInsurancePremium(true);
			this.accidentDAO.updatePayInsurancePremium(accident);
			url = "main.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

}
