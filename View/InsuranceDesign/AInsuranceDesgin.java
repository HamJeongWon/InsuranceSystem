package InsuranceDesign;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.insuranceDAO;
import Insurance.ActualCostInsurance;
import Insurance.Injury;
import Insurance.Insurance.InsuranceType;

/**
 * Servlet implementation class InsuranceDesgin
 */
@WebServlet("/AInsuranceDesgin")
public class AInsuranceDesgin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AInsuranceDesgin() {
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
		
		ActualCostInsurance insurance = new ActualCostInsurance();
		insuranceDAO insuranceDao = new insuranceDAO();
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");
        
        // 보험 기본 정보를 DB에 저장
        
		int insuranceID = insuranceDao.SelectMaxID("insuranceID", "Insurance");
		if (insuranceID == 0) { insuranceID = 1000; }
		insuranceID = insuranceID + 1;
		
		insurance.setInsuranceID(insuranceID);     
        insurance.setInsuranceName(request.getParameter("insuranceName"));  
        insurance.setInsuranceFee(Integer.parseInt(request.getParameter("insuranceFee")));
        insurance.setInsuranceType(InsuranceType.ActualCost);
        insurance.setInsuranceManual(request.getParameter("insuranceManual"));
        insurance.setInsuranceSalesManual(request.getParameter("insuranceSalesManual"));
        
        
        // 실비 보험 상세 정보를 DB에 저장
        
        Injury hospitalization, outpatient;
        
        hospitalization = new Injury();
        
        hospitalization.setProvisionFee(Integer.parseInt(request.getParameter("hospitalizationFee")));
        hospitalization.setProvisionReason(request.getParameter("hospitalizationReason"));
        insurance.setInjuryHospitalization(hospitalization);
        
        outpatient = new Injury();
        
        outpatient.setProvisionFee(Integer.parseInt(request.getParameter("outpatientFee")));
        outpatient.setProvisionReason(request.getParameter("outpatientReason"));
        insurance.setInjuryOutpatient(outpatient);
  
  
		insuranceDao.InsertInsurance(insurance);
		insuranceDao.InsertActualCostInsurance(insurance);
		
        response.sendRedirect("main.jsp");	
	}
}
