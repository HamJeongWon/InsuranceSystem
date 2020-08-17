package Contract;

import java.io.IOException;
import java.util.Vector;
import DAO.contractDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FullContractControll")
public class FullContractControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	contractDAO contractDAO;
	
    public FullContractControll() {
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
		
		Vector<String> VecFullContract = this.contractDAO.searchFullContractCustomers();
		request.setAttribute("VecFullContract", VecFullContract);
		
		RequestDispatcher disp = request.getRequestDispatcher("/fullContractList.jsp");
		disp.forward(request, response);	
	}

}
