package AcceptanceGuide;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Acceptance.AcceptanceGuide;
import DAO.acceptanceDAO;
import Insurance.Insurance;


/**
 * Servlet implementation class InsuranceDesgin
 */
@WebServlet("/ShowAcceptanceGuide")
public class ShowAcceptanceGuide extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAcceptanceGuide() {
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
		
		request.setCharacterEncoding("EUC-KR");
        response.setContentType("text/html; charset=UTF-8");
        
        String url = null;
        
        acceptanceDAO acceptanceDAO = new acceptanceDAO();
        
        Vector<AcceptanceGuide> fireGuide = acceptanceDAO.searchAcceptanceForInsurance((Insurance.InsuranceType.Fire).toString());    
        Vector<AcceptanceGuide> carGuide = acceptanceDAO.searchAcceptanceForInsurance((Insurance.InsuranceType.Car).toString());
        Vector<AcceptanceGuide> actualCostGuide = acceptanceDAO.searchAcceptanceForInsurance((Insurance.InsuranceType.ActualCost).toString());
	
		request.setAttribute("fireGuide", fireGuide);
		request.setAttribute("carGuide", carGuide);
		request.setAttribute("actualCostGuide", actualCostGuide);
		url = "/ShowAcceptanceGuide.jsp";

        ServletContext context = getServletContext();
		RequestDispatcher disp = context.getRequestDispatcher(url);
		disp.forward(request, response);
	}
}