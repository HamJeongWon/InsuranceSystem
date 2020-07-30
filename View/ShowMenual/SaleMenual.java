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
public class SaleMenual extends HttpServlet {
	private static final long serialVersionUID = 1L;
	insuranceDAO insuranceDAO;  
	
    public SaleMenual() {
        super();
    }  

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.insuranceDAO = new insuranceDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//vector로 갖고 통쨰로 넘기기
		Vector<Insurance> VecInsurance = this.insuranceDAO.searchInsuranceIDandName();
		request.setAttribute("VecInsurance", VecInsurance);
		RequestDispatcher disp = request.getRequestDispatcher("/ShowSalesMenual.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");	
        
		String Content = "";
		
		int InsuranceID = Integer.parseInt(request.getParameter("InsuranceID"));
		if (insuranceDAO.searchInsuranceIDforManual(InsuranceID) == false) {
			Content = "해당하는 보험id가 존재하지 않습니다";
		} else {
			Content = insuranceDAO.searchInsuranceSalesManual(InsuranceID);
			if(Content.isEmpty()) {
				Content = "해당하는 보험의 메뉴얼이 존재하지 않습니다";
			}
		}
		request.setAttribute("Content", Content);
		RequestDispatcher disp = request.getRequestDispatcher("/ShowMenualResult.jsp");
		disp.forward(request, response);
	}

}
