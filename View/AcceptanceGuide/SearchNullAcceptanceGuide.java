package AcceptanceGuide;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.insuranceDAO;
import Insurance.Insurance;


/**
 * Servlet implementation class InsuranceDesgin
 */
@WebServlet("/SearchNullAcceptanceGuide")
public class SearchNullAcceptanceGuide extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchNullAcceptanceGuide() {
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
        insuranceDAO insuranceDAO = new insuranceDAO();
        
        String url = null;
	
		request.setAttribute("nullAcceptanceInsuranceID", insuranceDAO.SearchNullAcceptanceInsuranceID());
	
		url = "/AcceptanceGuideList.jsp";

        ServletContext context = getServletContext();
		RequestDispatcher disp = context.getRequestDispatcher(url);
		disp.forward(request, response);
	}
}