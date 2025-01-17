package ContractManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import Contract.Contract;
import Contract.ContractListImpl;
import Contract.Contract.PaymentType;
import Customer.ActualCost;
import Customer.Car;
import Customer.Customer;
import Customer.CustomerListImpl;
import Customer.PersonalInformation;
import DAO.insuranceDAO;
import DAO.DAO;
import DAO.contractDAO;
import DAO.subscriptionDAO;
import DAO.customerDAO;
import InsuranceSubscription.InsuranceSubscription;
import InsuranceTreatment.InsuranceTreatment;

public class ContractManagement {
   
   public insuranceDAO insuranceDAO;
   private contractDAO contractDAO;
   private subscriptionDAO subscriptionDAO;
   private customerDAO customerDAO;
   public InsuranceSubscription insuranceSubscription;
   public InsuranceTreatment insuranceTreatment;

   public CustomerListImpl CustomerListImpl;
   public ContractListImpl contractListImpl;

   public ContractListImpl getContractListImpl() {
      return this.contractListImpl;
   }

   public ContractManagement() {
      this.contractListImpl = new ContractListImpl();
   }

   public void associate(InsuranceSubscription insuranceSubscription, InsuranceTreatment insuranceTreatment, DAO insuranceDAO, DAO contractDAO, DAO subscriptionDAO, DAO customerDAO) {
      this.insuranceDAO = (insuranceDAO)insuranceDAO;
      this.contractDAO = (contractDAO) contractDAO;
      this.subscriptionDAO = (subscriptionDAO) subscriptionDAO;
      this.customerDAO = (customerDAO) customerDAO;
      this.insuranceSubscription = insuranceSubscription;
      this.insuranceTreatment = insuranceTreatment;
      this.setCustomerListImpl();
   }

   public void setCustomerListImpl() {
      this.CustomerListImpl = insuranceSubscription.getCustomerListImpl();
      contractListImpl.setCustomerListImpl(this.CustomerListImpl);
   }
   
   public void WriteContractContent(Scanner sc) throws ParseException {
		int CustomerID = 0;
		int InsuranceID = 0;
		System.out.println("*******인수 승인 완료된 고객들 리스트*********");
		subscriptionDAO.showAllSubscription();
		System.out.println();
		System.out.println("계약서 작성을 원하는 보험ID와 고객ID을 입력하시오");
		
		while(true) {
			if(!sc.hasNextInt()) {
				sc.next();
				System.err.println("고객ID 숫자로 입력하시오");
			}else {
				InsuranceID = sc.nextInt();
				CustomerID = sc.nextInt();
			
				if(!(insuranceDAO.CheckIntData("customerID", "Subscription", CustomerID))||
						!(insuranceDAO.CheckIntData("InsuranceID", "Subscription", InsuranceID))) {
					System.err.println("해당하는 보험 ID나 고객ID가 존재하지 않습니다. 재입력하시오");
				}else {
					break;
				}			
			}
		}
			
		System.out.println("******보험 계약 내용 작성******");

		Contract contract = new Contract();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

		contract.setCustomerID(CustomerID);
			
		int newContractID = insuranceDAO.SelectMaxID("contractID", "Contract") + 1;
		System.out.println("1. 계약 ID는"+ newContractID + "입니다");
		contract.setContractID(newContractID);

		System.out.println("2. 계약 만료일을 (예:2010-01-01) 형식으로 입력하세요");
		Date ContractExpirationDate = SDF.parse(sc.next());
		contract.setContractExpirationDate(ContractExpirationDate);
			
		//납입료
		int paymentAmout = 0;
		float insuranceFee = insuranceDAO.getInsuranceFee(InsuranceID);
		String insuranceType = customerDAO.getInsuranceType(InsuranceID).toString();
		PersonalInformation personalInformation = customerDAO.findPersonalInformation2(CustomerID);
	
		if(insuranceType.equals("Car")) {
			Car car = customerDAO.getCar(CustomerID);	   
			if(car == null) {	
				System.err.println("Car에 해당 고객id가 존재하지 않음");
			}else {
				car.setJob(personalInformation.getJob());
				car.setAccidentHistory(personalInformation.getAccidentHistory());
				car.setAccountNumber(personalInformation.getAccountNumber());
				car.setGender(personalInformation.getGender());
				car.setProperty(personalInformation.getProperty());
				car.setResidentRegistrationNumber(personalInformation.getResidentRegistrationNumber());
			    
				paymentAmout = (int)insuranceSubscription.CalculatePremiumRateCar(car, insuranceFee);
				contract.setPaymentAmount(paymentAmout);
			}
	
			
		}else if(insuranceType.equals("Fire")) {
			float buildingPrice = customerDAO.getBuildingPrice(CustomerID);
			if(buildingPrice == 0) {		
				System.err.println("Building에 해당 고객id가 존재하지 않음");
			}else {
			paymentAmout =  (int)insuranceSubscription.CalculatePremiumRateOfFire(buildingPrice, insuranceFee);
			contract.setPaymentAmount(paymentAmout);
			}
		}else {
			ActualCost actualCost = customerDAO.getActualCost(CustomerID);
			if(actualCost == null) {		
				System.err.println("ActualCost에 해당 고객id가 존재하지 않음");
			}else {
			actualCost.setJob(personalInformation.getJob());
			actualCost.setAccidentHistory(personalInformation.getAccidentHistory());
			actualCost.setAccountNumber(personalInformation.getAccountNumber());
			actualCost.setGender(personalInformation.getGender());
			actualCost.setProperty(personalInformation.getProperty());
			actualCost.setResidentRegistrationNumber(personalInformation.getResidentRegistrationNumber());
			
			paymentAmout =  (int)insuranceSubscription.CalculatePremiumRateActual(actualCost, insuranceFee);
			contract.setPaymentAmount(paymentAmout);
			}
		}
		System.out.println("3. 월 납입금액은 "+ paymentAmout +"원 입니다");
	
		System.out.println("4. 납입날짜를 (예: 2010-01-01)형식으로 입력하세요");
		contract.setPaymentDate(SDF.parse(sc.next()));

		System.out.println("5. 납입기간을 (ex 몇 개월) 입력하세요");    
		while(true) {
			if(!sc.hasNextInt()) {
				sc.next();
				System.err.println("납입기간을 숫자로 입력하시오");
			}else {
				contract.setPaymentPeriod(sc.nextInt());
				break;
			}
		}

		//System.out.println("6. 납입 완료 상태입니다");
		contract.setPaymentStatus(true);

		System.out.println("7. 납입 방법을 입력하세요 1.creditCard, 2.e_bancking, 3.accountTransfer"); 
		int caseNum = 1;
		while(true) {
			if(!sc.hasNextInt()) {
				sc.next();
				System.err.println("숫자를 입력하시오 1.creditCard, 2.e_bancking, 3.accountTransfer");
			}else {
				caseNum = sc.nextInt();
				if (!(caseNum > 0 && caseNum < 4)) {
					System.err.println("다음 메뉴 중 선택하시오. 1.creditCard, 2.e_bancking, 3.accountTransfer");
				}else {
					break;
				}
			}
		}

		switch(caseNum) {
		case 1: contract.setPaymentType(PaymentType.creditCard);
		break;
		case 2: contract.setPaymentType(PaymentType.e_bancking);
		break;
		case 3: contract.setPaymentType(PaymentType.accountTransfer);
		break;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(ContractExpirationDate);
		cal.add(Calendar.YEAR, 3);
		Date PersonalInformationRetentionPeriod = cal.getTime();
		System.out.println("8. 개인정보 보호기간은 계약 만료일로부터 3년인 " + PersonalInformationRetentionPeriod +"입니다"); 
		contract.setPersonalInformationRetentionPeriod(PersonalInformationRetentionPeriod);

		contractListImpl.add(contract);
		this.contractDAO.createContract(contract);
		this.subscriptionDAO.insertContratIDtoSubscription(newContractID, CustomerID, InsuranceID);
		System.out.println("계약 작성 완료");
		
		System.out.println("*****청약서******");
		System.out.println("계약 만료일 : " + contract.getContractExpirationDate());
		System.out.println("납입해야할 금액 : " + contract.getPaymentAmount() +"원");
		System.out.println("납입 날짜 : " + contract.getPaymentDate());
		System.out.println("납입 방식 : " + contract.getPaymentType());
		System.out.println("개인정보 보호 기간 : " + contract.getPersonalInformationRetentionPeriod());
		System.out.println();
	}

	public void searchUnpaidCustomer(Scanner sc) {
		System.out.println();
		System.out.println("*****미납자 조회하기*****");
		this.contractDAO.searchUnpaidCustomer();
	}

	public void searchFullContractCustomer(Scanner sc) { 		//계약ID, 고객 ID, 고객 이름, 연락처, 계약만료일, 개인정보보유기간
		System.out.println();
		System.out.println("*****만기계약자 조회하기*****");
		
		CustomerListImpl customerList = new CustomerListImpl();
		ContractListImpl contractList = new ContractListImpl();

		HashMap<CustomerListImpl, ContractListImpl> CustomerAndContractList =this.contractDAO.searchFullContractCustomer(); 

		Set key = CustomerAndContractList.keySet();
		 
		for (Iterator iterator = key.iterator(); iterator.hasNext();) {
			  customerList = (CustomerListImpl) iterator.next();
			  contractList = (ContractListImpl) CustomerAndContractList.get(customerList);
		}	
	
		for(int i=0; i<customerList.getCustomerVector().size(); i++) {
			Customer customer = customerList.getCustomerVector().get(i);
			Contract contract = contractList.getContractVector().get(i);
			System.out.print("계약 ID: " + contract.getContractID());
			System.out.print(", 고객 ID: " + contract.getCustomerID());	
			System.out.print(", 고객 이름: " + customer.getCustomerName());
			System.out.print(", 연락처: " + customer.getPhoneNum());
			System.out.print(", 계약만료일: " + contract.getContractExpirationDate());
			System.out.print(", 개인정보보유기간: " + contract.getPersonalInformationRetentionPeriod());
			System.out.println();
		}
	

		System.out.println("재계약을 진행할 계약ID을 입력하세요");
		while(true) {
			if(!sc.hasNextInt()) {
				sc.next();
				System.err.println("숫자를 입력하시오. 계약ID");
			}else {
				int contractID = sc.nextInt();
				if(!insuranceDAO.CheckIntData("contractID", "Contract", contractID)) {
					System.err.println("검색 결과 CustomerID 혹은 ContractID가 Vecotor에 존재 하지 않음");
					System.out.println("재계약을 진행할 계약ID을 입력하세요");
				}else {
					Contract newcontract = contractDAO.searchContract(contractID);
					Customer newcustomer = contractDAO.findCustomer(newcontract.getCustomerID());
					ReContract(contractID, newcontract, newcustomer, sc); 
					break;
				}
			}
		}
		System.out.println();
	}   
	
	private void ReContract(int contractID, Contract contract, Customer customer, Scanner sc) {
		System.out.println("***********재계약 하기************");
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println("고객이름: "+ customer.getCustomerName());
		System.out.println("연락처: "+ customer.getPhoneNum());
		System.out.println("계약만료일: "+ contract.getContractExpirationDate());
		System.out.println("개인정보보유기간: "+ contract.getPersonalInformationRetentionPeriod());

		System.out.println("*****수정을 시작합니다****");
		System.out.println("새로운 계약만료일을 (예:2010-01-01) 형식으로 입력하세요");
		Date ContractExpirationDate = null;

		try {
			ContractExpirationDate = SDF.parse(sc.next());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		contract.setContractExpirationDate(ContractExpirationDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(ContractExpirationDate);
		cal.add(Calendar.YEAR, 3);
		Date PersonalInformationRetentionPeriod = cal.getTime();
		contract.setPersonalInformationRetentionPeriod(PersonalInformationRetentionPeriod);
		
		int menueNum = 0;
		
		while(menueNum!=4) {
			System.out.println("수정할 부분을 입력하세요 1. 계좌번호, 2. 납입일, 3. 납입방식  4. 수정 종료 " );
			
			if(!sc.hasNextInt()) {
				sc.next();
				System.err.println("숫자를 입력하시오. 1. 계좌번호, 2. 납입일, 3. 납입방식   4. 수정 종료");
			}else {
				menueNum = sc.nextInt();
				if(!(menueNum>0 && menueNum<5)) {
					System.err.println("다음 메뉴 중 선택하시오. 1. 계좌번호, 2. 납입일, 3. 납입방식   4. 수정 종료");
				}else {
					switch(menueNum) {
					case 1: 
						System.out.println("새로운 계좌번호를 입력하세요");
						while(true) {
							if(!sc.hasNextInt()) {
								sc.next();
								System.err.println("입력이 잘못되었습니다. 입력 재확인 부탁합니다");    //대안_   
							}
							else{
								this.customerDAO.updatePersonalInformation(customer.getCustomerID(), sc.nextInt());
								break;
							}                  
						}
						continue;
						
					case 2:
						System.out.println("새로운 납입일을 (예:2010-01-01) 형식으로 입력하세요");
						Date PaymentDate = null;
						try {
							PaymentDate = SDF.parse(sc.next());
							//대안_   System.out.println("입력이 잘못되었습니다. 입력 재확인 부탁합니다");
						} catch (ParseException e) {
							e.printStackTrace();
						}
						contract.setPaymentDate(PaymentDate);
						continue;

					case 3:
						System.out.println("새로운 납입방식을 입력하세요 1.creditCard, 2.e_bancking, 3.accountTransfer");
						//대안_   System.out.println("입력이 잘못되었습니다. 입력 재확인 부탁합니다");
						switch(sc.nextInt()) {
						case 1: 
							contract.setPaymentType(PaymentType.creditCard);
							break;
						case 2: contract.setPaymentType(PaymentType.e_bancking);
							break;
						case 3: contract.setPaymentType(PaymentType.accountTransfer);
							break;
						default:
							break;
						}
						continue;
						
					case 4:
						System.out.println("수정을 종료합니다");
						break;
						
					default:
						break;
					}
				}
			}
		}		
		this.contractDAO.updateContract(contract);		
		System.out.println("수정이 완료되었습니다 청약서를 발송하였습니다");  
		System.out.println();
	}

}