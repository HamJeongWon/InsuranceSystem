package InsuranceDesign;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.insuranceDAO;
import Insurance.DamageInformation;
import Insurance.FireInsurance;
import Insurance.Insurance.InsuranceType;

/**
 * Servlet implementation class InsuranceDesgin
 */
@WebServlet("/FInsuranceDesgin")
public class FInsuranceDesgin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FInsuranceDesgin() {
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
		
		FireInsurance insurance = new FireInsurance();
		insuranceDAO insuranceDao = new insuranceDAO();
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");
        
        // 보험 기본 정보를 DB에 저장
        
		int insuranceID = insuranceDao.SelectMaxID("insuranceID", "Insurance");
		if (insuranceID == 0) { insuranceID = 1000; }
		insuranceID = insuranceID + 1;
		insurance.setInsuranceID(insuranceID);
        
        String insuranceName = request.getParameter("insuranceName");
        insurance.setInsuranceName(insuranceName);
        
        int insuranceFee = Integer.parseInt(request.getParameter("insuranceFee"));
        insurance.setInsuranceFee(insuranceFee);
        
        
        insurance.setInsuranceType(InsuranceType.Fire);
        
        String insuranceManual = request.getParameter("insuranceManual");
        insurance.setInsuranceManual(insuranceManual);
        
        String insuranceSalesManual = request.getParameter("insuranceSalesManual");
        insurance.setInsuranceSalesManual(insuranceSalesManual);
        
        
        // 화재 보험 상세 정보를 DB에 저장
        
        DamageInformation directDamage, fireDamage, refugeDamage;
        
        directDamage = new DamageInformation();
        
        int directGuaranteedAmount = Integer.parseInt(request.getParameter("directGuaranteedAmount"));
        directDamage.setDamageGuaranteedAmount(directGuaranteedAmount);
        
        String directGuaranteedContent = request.getParameter("directGuaranteedContent");
        directDamage.setDamageGuaranteedContent(directGuaranteedContent);
        
        insurance.setDirectDamage(directDamage);
        
        fireDamage = new DamageInformation();
        
        int fireGuaranteedAmount = Integer.parseInt(request.getParameter("fireGuaranteedAmount"));
        fireDamage.setDamageGuaranteedAmount(fireGuaranteedAmount);
        
        String fireGuaranteedContent = request.getParameter("fireGuaranteedContent");
        fireDamage.setDamageGuaranteedContent(fireGuaranteedContent);
        
        insurance.setFireDamage(fireDamage);
        
        refugeDamage = new DamageInformation();
        
        int refugeGuaranteedAmount = Integer.parseInt(request.getParameter("refugeGuaranteedAmount"));
        refugeDamage.setDamageGuaranteedAmount(refugeGuaranteedAmount);
        
        String refugeGuaranteedContent = request.getParameter("refugeGuaranteedContent");
        refugeDamage.setDamageGuaranteedContent(refugeGuaranteedContent);
        
        insurance.setRefugeDamage(refugeDamage);
        	
		insuranceDao.InsertInsurance(insurance);
		insuranceDao.InsertFireInsurance(insurance);
		
        response.sendRedirect("main.jsp");	
	}
}
