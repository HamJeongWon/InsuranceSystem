package InsuranceDesign;

import Insurance.Insurance.InsuranceType;

public class InsuranceDesign {
	
	public InsuranceDesign() {
		
	}
	
	public Insurance CreateInsuranceContent() {
		
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
	}


}
