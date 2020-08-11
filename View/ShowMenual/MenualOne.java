package ShowMenual;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.insuranceDAO;
import Insurance.Insurance;

@WebServlet("/Menual1")
public class MenualOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	insuranceDAO insuranceDAO;  
	
    public MenualOne() {
        super();
    }  

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.insuranceDAO = new insuranceDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");	

        Vector<Insurance> VecInsurance = this.insuranceDAO.searchInsuranceIDandName();
        request.setAttribute("VecInsurance", VecInsurance);       	
      
		RequestDispatcher disp = request.getRequestDispatcher("/ShowMenual.jsp");
		disp.forward(request, response);
	}

}
