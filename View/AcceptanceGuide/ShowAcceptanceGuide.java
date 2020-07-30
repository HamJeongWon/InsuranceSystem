package AcceptanceGuide;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Acceptance.AcceptanceGuide;
import DAO.acceptanceDAO;
import Insurance.Insurance;

@WebServlet("/ShowAcceptanceGuide")
public class ShowAcceptanceGuide extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ShowAcceptanceGuide() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");
		String url = null;
		
		acceptanceDAO acceptanceDAO = new acceptanceDAO();
        
        Vector<AcceptanceGuide> fireGuide = acceptanceDAO.searchAcceptanceForInsurance((Insurance.InsuranceType.Fire).toString());    
        Vector<AcceptanceGuide> carGuide = acceptanceDAO.searchAcceptanceForInsurance((Insurance.InsuranceType.Car).toString());
        Vector<AcceptanceGuide> actualCostGuide = acceptanceDAO.searchAcceptanceForInsurance((Insurance.InsuranceType.ActualCost).toString());
	
		request.setAttribute("fireGuide", fireGuide);
		request.setAttribute("carGuide", carGuide);
		request.setAttribute("actualCostGuide", actualCostGuide);
		url = "/ShowAcceptanceGuide.jsp";
		
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);

	}
}