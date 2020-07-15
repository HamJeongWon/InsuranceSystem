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
		System.out.println("*******�μ� ���� �Ϸ�� ���� ����Ʈ*********");
		subscriptionDAO.showAllSubscription();
		System.out.println();
		System.out.println("��༭ �ۼ��� ���ϴ� ����ID�� ��ID�� �Է��Ͻÿ�");
		
		while(true) {
			if(!sc.hasNextInt()) {
				sc.next();
				System.err.println("��ID ���ڷ� �Է��Ͻÿ�");
			}else {
				InsuranceID = sc.nextInt();
				CustomerID = sc.nextInt();
			
				if(!(insuranceDAO.CheckIntData("customerID", "Subscription", CustomerID))||
						!(insuranceDAO.CheckIntData("InsuranceID", "Subscription", InsuranceID))) {
					System.err.println("�ش��ϴ� ���� ID�� ��ID�� �������� �ʽ��ϴ�. ���Է��Ͻÿ�");
				}else {
					break;
				}			
			}
		}
			
		System.out.println("******���� ��� ���� �ۼ�******");

		Contract contract = new Contract();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

		contract.setCustomerID(CustomerID);
			
		int newContractID = insuranceDAO.SelectMaxID("contractID", "Contract") + 1;
		System.out.println("1. ��� ID��"+ newContractID + "�Դϴ�");
		contract.setContractID(newContractID);

		System.out.println("2. ��� �������� (��:2010-01-01) �������� �Է��ϼ���");
		Date ContractExpirationDate = SDF.parse(sc.next());
		contract.setContractExpirationDate(ContractExpirationDate);
			
		//���Է�
		int paymentAmout = 0;
		float insuranceFee = insuranceDAO.getInsuranceFee(InsuranceID);
		String insuranceType = customerDAO.getInsuranceType(InsuranceID).toString();
		PersonalInformation personalInformation = customerDAO.findPersonalInformation2(CustomerID);
	
		if(insuranceType.equals("Car")) {
			Car car = customerDAO.getCar(CustomerID);	   
			if(car == null) {	
				System.err.println("Car�� �ش� ��id�� �������� ����");
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
				System.err.println("Building�� �ش� ��id�� �������� ����");
			}else {
			paymentAmout =  (int)insuranceSubscription.CalculatePremiumRateOfFire(buildingPrice, insuranceFee);
			contract.setPaymentAmount(paymentAmout);
			}
		}else {
			ActualCost actualCost = customerDAO.getActualCost(CustomerID);
			if(actualCost == null) {		
				System.err.println("ActualCost�� �ش� ��id�� �������� ����");
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
		System.out.println("3. �� ���Աݾ��� "+ paymentAmout +"�� �Դϴ�");
	
		System.out.println("4. ���Գ�¥�� (��: 2010-01-01)�������� �Է��ϼ���");
		contract.setPaymentDate(SDF.parse(sc.next()));

		System.out.println("5. ���ԱⰣ�� (ex �� ����) �Է��ϼ���");    
		while(true) {
			if(!sc.hasNextInt()) {
				sc.next();
				System.err.println("���ԱⰣ�� ���ڷ� �Է��Ͻÿ�");
			}else {
				contract.setPaymentPeriod(sc.nextInt());
				break;
			}
		}

		//System.out.println("6. ���� �Ϸ� �����Դϴ�");
		contract.setPaymentStatus(true);

		System.out.println("7. ���� ����� �Է��ϼ��� 1.creditCard, 2.e_bancking, 3.accountTransfer"); 
		int caseNum = 1;
		while(true) {
			if(!sc.hasNextInt()) {
				sc.next();
				System.err.println("���ڸ� �Է��Ͻÿ� 1.creditCard, 2.e_bancking, 3.accountTransfer");
			}else {
				caseNum = sc.nextInt();
				if (!(caseNum > 0 && caseNum < 4)) {
					System.err.println("���� �޴� �� �����Ͻÿ�. 1.creditCard, 2.e_bancking, 3.accountTransfer");
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
		System.out.println("8. �������� ��ȣ�Ⱓ�� ��� �����Ϸκ��� 3���� " + PersonalInformationRetentionPeriod +"�Դϴ�"); 
		contract.setPersonalInformationRetentionPeriod(PersonalInformationRetentionPeriod);

		contractListImpl.add(contract);
		this.contractDAO.createContract(contract);
		this.subscriptionDAO.insertContratIDtoSubscription(newContractID, CustomerID, InsuranceID);
		System.out.println("��� �ۼ� �Ϸ�");
		
		System.out.println("*****û�༭******");
		System.out.println("��� ������ : " + contract.getContractExpirationDate());
		System.out.println("�����ؾ��� �ݾ� : " + contract.getPaymentAmount() +"��");
		System.out.println("���� ��¥ : " + contract.getPaymentDate());
		System.out.println("���� ��� : " + contract.getPaymentType());
		System.out.println("�������� ��ȣ �Ⱓ : " + contract.getPersonalInformationRetentionPeriod());
		System.out.println();
	}

	public void searchUnpaidCustomer(Scanner sc) {
		System.out.println();
		System.out.println("*****�̳��� ��ȸ�ϱ�*****");
		this.contractDAO.searchUnpaidCustomer();
	}

	public void searchFullContractCustomer(Scanner sc) { 		//���ID, �� ID, �� �̸�, ����ó, ��ุ����, �������������Ⱓ
		System.out.println();
		System.out.println("*****�������� ��ȸ�ϱ�*****");
		
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
			System.out.print("��� ID: " + contract.getContractID());
			System.out.print(", �� ID: " + contract.getCustomerID());	
			System.out.print(", �� �̸�: " + customer.getCustomerName());
			System.out.print(", ����ó: " + customer.getPhoneNum());
			System.out.print(", ��ุ����: " + contract.getContractExpirationDate());
			System.out.print(", �������������Ⱓ: " + contract.getPersonalInformationRetentionPeriod());
			System.out.println();
		}
	

		System.out.println("������ ������ ���ID�� �Է��ϼ���");
		while(true) {
			if(!sc.hasNextInt()) {
				sc.next();
				System.err.println("���ڸ� �Է��Ͻÿ�. ���ID");
			}else {
				int contractID = sc.nextInt();
				if(!insuranceDAO.CheckIntData("contractID", "Contract", contractID)) {
					System.err.println("�˻� ��� CustomerID Ȥ�� ContractID�� Vecotor�� ���� ���� ����");
					System.out.println("������ ������ ���ID�� �Է��ϼ���");
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
		System.out.println("***********���� �ϱ�************");
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println("���̸�: "+ customer.getCustomerName());
		System.out.println("����ó: "+ customer.getPhoneNum());
		System.out.println("��ุ����: "+ contract.getContractExpirationDate());
		System.out.println("�������������Ⱓ: "+ contract.getPersonalInformationRetentionPeriod());

		System.out.println("*****������ �����մϴ�****");
		System.out.println("���ο� ��ุ������ (��:2010-01-01) �������� �Է��ϼ���");
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
			System.out.println("������ �κ��� �Է��ϼ��� 1. ���¹�ȣ, 2. ������, 3. ���Թ��  4. ���� ���� " );
			
			if(!sc.hasNextInt()) {
				sc.next();
				System.err.println("���ڸ� �Է��Ͻÿ�. 1. ���¹�ȣ, 2. ������, 3. ���Թ��   4. ���� ����");
			}else {
				menueNum = sc.nextInt();
				if(!(menueNum>0 && menueNum<5)) {
					System.err.println("���� �޴� �� �����Ͻÿ�. 1. ���¹�ȣ, 2. ������, 3. ���Թ��   4. ���� ����");
				}else {
					switch(menueNum) {
					case 1: 
						System.out.println("���ο� ���¹�ȣ�� �Է��ϼ���");
						while(true) {
							if(!sc.hasNextInt()) {
								sc.next();
								System.err.println("�Է��� �߸��Ǿ����ϴ�. �Է� ��Ȯ�� ��Ź�մϴ�");    //���_   
							}
							else{
								this.customerDAO.updatePersonalInformation(customer.getCustomerID(), sc.nextInt());
								break;
							}                  
						}
						continue;
						
					case 2:
						System.out.println("���ο� �������� (��:2010-01-01) �������� �Է��ϼ���");
						Date PaymentDate = null;
						try {
							PaymentDate = SDF.parse(sc.next());
							//���_   System.out.println("�Է��� �߸��Ǿ����ϴ�. �Է� ��Ȯ�� ��Ź�մϴ�");
						} catch (ParseException e) {
							e.printStackTrace();
						}
						contract.setPaymentDate(PaymentDate);
						continue;

					case 3:
						System.out.println("���ο� ���Թ���� �Է��ϼ��� 1.creditCard, 2.e_bancking, 3.accountTransfer");
						//���_   System.out.println("�Է��� �߸��Ǿ����ϴ�. �Է� ��Ȯ�� ��Ź�մϴ�");
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
						System.out.println("������ �����մϴ�");
						break;
						
					default:
						break;
					}
				}
			}
		}		
		this.contractDAO.updateContract(contract);		
		System.out.println("������ �Ϸ�Ǿ����ϴ� û�༭�� �߼��Ͽ����ϴ�");  
		System.out.println();
	}

}