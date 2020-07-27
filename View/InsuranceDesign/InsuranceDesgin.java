package InsuranceDesign;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.insuranceDAO;
import Insurance.ActualCostInsurance;
import Insurance.CarInsurance;
import Insurance.DamageInformation;
import Insurance.FireInsurance;
import Insurance.Goods_Personal;
import Insurance.Injury;
import Insurance.Insurance;
import Insurance.SelfVehicleDamage;
import Insurance.Goods_Personal.GSeparation;
import Insurance.Insurance.InsuranceType;
import Insurance.SelfVehicleDamage.SSeparation;

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
		
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=euc-kr");
        insuranceDAO insuranceDao = new insuranceDAO();
        Insurance insurance = null;
        String url = null;
        
        String insuranceType = request.getParameter("action");
		switch(insuranceType) {
		 case "fire" : 
			 insurance = new FireInsurance();
			 break;
		 case "car" : 
			 insurance = new CarInsurance();
			 break;
		 case "actualCost" : 
			 insurance = new ActualCostInsurance();
			 break;
		}

        // 보험 기본 정보를 DB에 저장
        
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
        
        switch(insuranceType) {
        
        case "fire" :    // 화재 보험 상세 정보를 DB에 저장      
            insurance.setInsuranceType(InsuranceType.Fire);
            
            DamageInformation directDamage, fireDamage, refugeDamage;
            
            directDamage = new DamageInformation();
            
            int directGuaranteedAmount = Integer.parseInt(request.getParameter("directGuaranteedAmount"));
            directDamage.setDamageGuaranteedAmount(directGuaranteedAmount);
            
            String directGuaranteedContent = request.getParameter("directGuaranteedContent");
            directDamage.setDamageGuaranteedContent(directGuaranteedContent);
            
            ((FireInsurance) insurance).setDirectDamage(directDamage);
            
            fireDamage = new DamageInformation();
            
            int fireGuaranteedAmount = Integer.parseInt(request.getParameter("fireGuaranteedAmount"));
            fireDamage.setDamageGuaranteedAmount(fireGuaranteedAmount);
            
            String fireGuaranteedContent = request.getParameter("fireGuaranteedContent");
            fireDamage.setDamageGuaranteedContent(fireGuaranteedContent);
            
            ((FireInsurance) insurance).setFireDamage(fireDamage);
            
            refugeDamage = new DamageInformation();
            
            int refugeGuaranteedAmount = Integer.parseInt(request.getParameter("refugeGuaranteedAmount"));
            refugeDamage.setDamageGuaranteedAmount(refugeGuaranteedAmount);
            
            String refugeGuaranteedContent = request.getParameter("refugeGuaranteedContent");
            refugeDamage.setDamageGuaranteedContent(refugeGuaranteedContent);
            
            ((FireInsurance) insurance).setRefugeDamage(refugeDamage);
            	
    		insuranceDao.InsertInsurance(insurance);
    		insuranceDao.InsertFireInsurance((FireInsurance) insurance);
    		
			request.setAttribute("FInsurance", insurance);
			url = "/ResultFInsuranceDesign.jsp";
    		
        	break;
        	
        case "car" :  // 자동차 보험 상세 정보를 DB에 저장
        	insurance.setInsuranceType(InsuranceType.Car);
        	
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
            ((CarInsurance) insurance).setGoodsIndemnification(goods);
            
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
            ((CarInsurance) insurance).setPersonalIndemnification(pesonal);

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
            ((CarInsurance) insurance).setSelfVehicleDamage(selfVehicleDamage);
      
    		insuranceDao.InsertInsurance(insurance);
    		insuranceDao.InsertCarInsurance((CarInsurance) insurance);
    		
			request.setAttribute("CInsurance", insurance);
			url = "/ResultCInsuranceDesign.jsp";
			
        	break;
        	
        case "actualCost" :  // 실비 보험 상세 정보를 DB에 저장
        	insurance.setInsuranceType(InsuranceType.ActualCost);
        	
            Injury hospitalization, outpatient;
            
            hospitalization = new Injury();
            
            hospitalization.setProvisionFee(Integer.parseInt(request.getParameter("hospitalizationFee")));
            hospitalization.setProvisionReason(request.getParameter("hospitalizationReason"));
            ((ActualCostInsurance) insurance).setInjuryHospitalization(hospitalization);
            
            outpatient = new Injury();
            
            outpatient.setProvisionFee(Integer.parseInt(request.getParameter("outpatientFee")));
            outpatient.setProvisionReason(request.getParameter("outpatientReason"));
            ((ActualCostInsurance) insurance).setInjuryOutpatient(outpatient);
      
    		insuranceDao.InsertInsurance(insurance);
    		insuranceDao.InsertActualCostInsurance((ActualCostInsurance) insurance);
    		
			request.setAttribute("AInsurance", insurance);
			url = "/ResultAInsuranceDesign.jsp";
        	break;
        	
        default :
    		break;
        }
        ServletContext context = getServletContext();
		RequestDispatcher disp = context.getRequestDispatcher(url);
		disp.forward(request, response);
	}
}
