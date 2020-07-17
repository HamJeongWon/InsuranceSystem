package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet("/InsuranceDesgin")
public class InsuranceDesgin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsuranceDesgin() {
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
		
		Insurance insurance = new Insurance();
		insuranceDAO insuranceDao = new insuranceDAO();
		
        response.setContentType("text/html; charset=euc-kr");
        PrintWriter out = response.getWriter();
        
		int insuranceID = insuranceDao.SelectMaxID("insuranceID", "Insurance");
		if (insuranceID == 0) { insuranceID = 1000; }
		insuranceID = insuranceID + 1;
		insurance.setInsuranceID(insuranceID);
        
        String insuranceName = request.getParameter("insuranceName");
        insurance.setInsuranceName(insuranceName);
        
        int insuranceFee = Integer.parseInt(request.getParameter("insuranceFee"));
        insurance.setInsuranceFee(insuranceFee);
        
        String insuranceManual = request.getParameter("insuranceManual");
        insurance.setInsuranceManual(insuranceManual);
        
        String insuranceSalesManual = request.getParameter("insuranceSalesManual");
        insurance.setInsuranceSalesManual(insuranceSalesManual);
        	
		insuranceDao.InsertInsurance(insurance);
		
        out.println("<html>");
        out.println("<head><title> 결과 </title></head>");
        out.println("<body><center>");
        out.println("<h2> 결과 </h2>");
        out.println("<HR>");
        out.println("보험 ID : " + insuranceID);
        out.println("보험명 : " + insuranceName);
        out.println("보험료 : " + insuranceFee);
        out.println("보험설명서 : " + insuranceManual);
        out.println("판매설명서 : " + insuranceSalesManual);
        out.println("</body></html>");
        
//        response.sendRedirect("main.jsp");
				
	}
}
