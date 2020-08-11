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

@WebServlet("/Menual2")
public class MenualTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	insuranceDAO insuranceDAO;  
	
    public MenualTwo() {
        super();
    }  

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.insuranceDAO = new insuranceDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");	

        int InsuranceID = Integer.parseInt(request.getParameter("InsuranceID"));
        String InsuranceName = request.getParameter("InsuranceName");			
        String MenualValue = request.getParameter("menual");
        String Content = "";
		
        if(MenualValue.equals("판매 메뉴얼 조회")) {
        	Content = insuranceDAO.searchInsuranceSalesManual(InsuranceID);
        		if(Content.isEmpty()) {
        			Content = "해당하는 상품의 판매 메뉴얼이 존재하지 않습니다";
        		}
        }else {
        	Content = insuranceDAO.searchInsuranceManual(InsuranceID);
        		if(Content.isEmpty()) {
        			Content = "해당하는 상품의 상품 메뉴얼이 존재하지 않습니다";
        		}
        }
        request.setAttribute("InsuranceID", InsuranceID);
    	request.setAttribute("InsuranceName", InsuranceName);
    	request.setAttribute("Content", Content);
	
		RequestDispatcher disp = request.getRequestDispatcher("/ShowMenualResult.jsp");
		disp.forward(request, response);
	}
}
