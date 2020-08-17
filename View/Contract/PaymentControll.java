package Contract;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.contractDAO;

@WebServlet("/PaymentControll")
public class PaymentControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
    contractDAO contractDAO;
    
    public PaymentControll() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.contractDAO = new contractDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");	
		
		Vector<String> unpaidVec = this.contractDAO.searchUnpaidCustomer();

		request.setAttribute("unpaidVec", unpaidVec);
		
		RequestDispatcher disp = request.getRequestDispatcher("/unpaidList.jsp");
		disp.forward(request, response);	
	}

}
