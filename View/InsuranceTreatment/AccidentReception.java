package InsuranceTreatment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.subscriptionDAO;


@WebServlet("/AccidentReception")
public class AccidentReception extends HttpServlet {
	private static final long serialVersionUID = 1L;
	subscriptionDAO subscriptionDAO;

	public AccidentReception() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		this.subscriptionDAO = new subscriptionDAO();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("IDVector", subscriptionDAO.showSubscriptionCustomer());
		RequestDispatcher disp = request.getRequestDispatcher("/AccidentReception.jsp");
		disp.forward(request, response);
		System.out.println(subscriptionDAO.showSubscriptionCustomer().get(0));

	}

}
