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

@WebServlet("/Menual3")
public class InsuranceMenual extends HttpServlet {
	private static final long serialVersionUID = 1L;
	insuranceDAO insuranceDAO;  
	
    public InsuranceMenual() {
        super();
    }  

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.insuranceDAO = new insuranceDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vector<Insurance> VecInsurance = this.insuranceDAO.searchInsuranceIDandName();
		request.setAttribute("VecInsurance", VecInsurance);
		RequestDispatcher disp = request.getRequestDispatcher("/ShowInsuranceMenual.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");	
        
		String Content = "";
		
		int InsuranceID = Integer.parseInt(request.getParameter("InsuranceID"));
		if (insuranceDAO.searchInsuranceIDforManual(InsuranceID) == false) {
			Content = "�ش��ϴ� ����id�� �������� �ʽ��ϴ�";
		} else {
			Content = insuranceDAO.searchInsuranceManual(InsuranceID);
			if(Content.isEmpty()) {
				Content = "�ش��ϴ� ������ �޴����� �������� �ʽ��ϴ�";
			}
		}
		request.setAttribute("Content", Content);
		RequestDispatcher disp = request.getRequestDispatcher("/ShowMenualResult.jsp");
		disp.forward(request, response);
	}

}
