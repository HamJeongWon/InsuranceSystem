package InsuranceDesign;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.insuranceDAO;

@WebServlet("/InsuranceList")
public class InsuranceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public InsuranceList() {
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
        insuranceDAO insuranceDAO = new insuranceDAO();
        
		request.setAttribute("AllInsurance", insuranceDAO.searchInsuranceIDandName());

		RequestDispatcher disp = request.getRequestDispatcher("InsuranceList.jsp");
		disp.forward(request, response);
	}
}

