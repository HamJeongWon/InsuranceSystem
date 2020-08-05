package AcceptanceGuide;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Acceptance.AcceptanceGuide;
import Acceptance.AcceptanceGuide.RiskEvaluation;
import DAO.acceptanceDAO;
import Insurance.Insurance;


/**
 * Servlet implementation class InsuranceDesgin
 */
@WebServlet("/AcceptanceGuideDesign")
public class AcceptanceGuideDesign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptanceGuideDesign() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");
        
        String url = null;
        
        acceptanceDAO acceptanceDAO = new acceptanceDAO();
        AcceptanceGuide acceptanceGuide = new AcceptanceGuide();
        
        int acceptanceID = acceptanceDAO.SelectMaxID("acceptanceID", "Acceptance");
		if (acceptanceID == 0) { acceptanceID = 5000; }
		acceptanceID = acceptanceID + 1;
		acceptanceGuide.setAcceptanceID(acceptanceID);
		
		acceptanceGuide.setScamCase(request.getParameter("ScamCase"));
		
		acceptanceGuide.setRiskEvaluation(AcceptanceGuide.RiskEvaluation.valueOf(request.getParameter("RiskEvaluation")));
		
		acceptanceGuide.setInsuranceID(Integer.parseInt(request.getParameter("InsuranceID")));
		
		acceptanceDAO.InsertAcceptanceGuide(acceptanceGuide);
		
		request.setAttribute("AcceptanceGuide", acceptanceGuide);
		url = "/ShowAcceptanceGuide";

        ServletContext context = getServletContext();
		RequestDispatcher disp = context.getRequestDispatcher(url);
		disp.forward(request, response);
	}
}