package InsuranceDesign;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.insuranceDAO;
import Insurance.CarInsurance;
import Insurance.Goods_Personal;
import Insurance.SelfVehicleDamage;
import Insurance.Goods_Personal.GSeparation;
import Insurance.Insurance.InsuranceType;
import Insurance.SelfVehicleDamage.SSeparation;

/**
 * Servlet implementation class InsuranceDesgin
 */
@WebServlet("/CInsuranceDesgin")
public class CInsuranceDesgin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CInsuranceDesgin() {
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
		
		CarInsurance insurance = new CarInsurance();
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
        insurance.setInsuranceType(InsuranceType.Car);
        insurance.setInsuranceManual(request.getParameter("insuranceManual"));
        insurance.setInsuranceSalesManual(request.getParameter("insuranceSalesManual"));
        
        
        // 자동차 보험 상세 정보를 DB에 저장
        
        Goods_Personal goods, pesonal;  
        goods = new Goods_Personal();
        
        switch(request.getParameter("goodsSeparation")){   
        case "Death" :
        	goods.setSeparation(GSeparation.Death);
        	break; 	
        case "Injury" :
        	goods.setSeparation(GSeparation.Injury);
        	break;
        case "Aftereffect" :
        	goods.setSeparation(GSeparation.Aftereffect);
        	break;	
        }
        goods.setProvisionLimit(Integer.parseInt(request.getParameter("goodsGuaranteeLimit")));
        goods.setGuaranteeContent(request.getParameter("goodsGuaranteeContent"));
        insurance.setGoodsIndemnification(goods);
        
        pesonal = new Goods_Personal();
        switch(request.getParameter("personalSeparation")){   
        case "Death" :
        	pesonal.setSeparation(GSeparation.Death);
        	break; 	
        case "Injury" :
        	pesonal.setSeparation(GSeparation.Injury);
        	break;
        case "Aftereffect" :
        	pesonal.setSeparation(GSeparation.Aftereffect);
        	break;	
        }
        pesonal.setProvisionLimit(Integer.parseInt(request.getParameter("personalProvisionLimit")));
        pesonal.setGuaranteeContent(request.getParameter("personalGuaranteeContent"));
        insurance.setPersonalIndemnification(pesonal);

        SelfVehicleDamage selfVehicleDamage = new SelfVehicleDamage();
        
        switch (request.getParameter("selfVehicleSeparation")) {
		case "SelfBodyAccident":
			selfVehicleDamage.setSeparation(SSeparation.SelfBodyAccident);
			break;
		case "CarInjury":
			selfVehicleDamage.setSeparation(SSeparation.CarInjury);
			break;
		default:
			break;
		}
        selfVehicleDamage.setSubscriptionFeeForInjury(Integer.parseInt(request.getParameter("SubscriptionFeeForInjury")));
        selfVehicleDamage.setSubscriptionFeeForAccidentalInjuries(Integer.parseInt(request.getParameter("SubscriptionFeeForAccidentalInjuries")));
        insurance.setSelfVehicleDamage(selfVehicleDamage);
  
		insuranceDao.InsertInsurance(insurance);
		insuranceDao.InsertCarInsurance(insurance);
		
        response.sendRedirect("main.jsp");	
	}
}
