package InsuranceSubscription;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

import Acceptance.AcceptanceGuide;
import Acceptance.AcceptanceGuide.RiskEvaluation;
import Acceptance.AcceptanceListImpl;
import ContractManagement.ContractManagement;
import Customer.CustomerListImpl;
import Customer.PersonalInformation;
import Customer.ActualCost;
import Customer.Building;
import Customer.Car;
import Customer.Customer;
import Insurance.ActualCostInsurance;
import Insurance.CarInsurance;
import Insurance.DamageInformation;
import Insurance.FireInsurance;
import Insurance.Goods_Personal;
import Insurance.Goods_Personal.GSeparation;
import Insurance.Injury;
import Insurance.Insurance;
import Insurance.InsuranceListImpl;
import Insurance.SelfVehicleDamage;
import Insurance.SelfVehicleDamage.SSeparation;
import InsuranceTreatment.InsuranceTreatment;
import Customer.PersonalInformation.Job;
import DAO.DAO;
import DAO.contractDAO;
import DAO.insuranceDAO;
import DAO.acceptanceDAO;
import DAO.subscriptionDAO;
import DAO.customerDAO;
import Customer.Car.LicenseType;
import Customer.Car.CarType;
import Customer.ActualCost.BloodType;
import Customer.ActualCost.DiseaseHistory;

public class InsuranceSubscription {

	// associate
	private contractDAO contractDAO;
	private insuranceDAO insuranceDAO;
	private acceptanceDAO acceptanceDAO;
	private subscriptionDAO subscriptionDAO;
	private customerDAO customerDAO;

	@SuppressWarnings("unused")
	private ContractManagement contractManagement;
	@SuppressWarnings("unused")
	private InsuranceTreatment insuranceTreatment;

	private InsuranceListImpl insuranceListImpl;
	private AcceptanceListImpl acceptanceListImpl;
	private CustomerListImpl customerListImpl;

	private float insurancePremiumRate;

	public InsuranceListImpl getInsuranceListImpl() {
		return insuranceListImpl;
	}

	public AcceptanceListImpl getAcceptanceListImpl() {
		return acceptanceListImpl;
	}

	public CustomerListImpl getCustomerListImpl() {
		return customerListImpl;
	}

	public InsuranceSubscription() {
		this.insurancePremiumRate = 0;
		this.contractDAO = null;

		this.insuranceListImpl = new InsuranceListImpl();
		this.acceptanceListImpl = new AcceptanceListImpl();
		this.customerListImpl = new CustomerListImpl();
	}

	public void associate(InsuranceTreatment insuranceTreatment, ContractManagement contractManagement, DAO DAO,
			DAO subscriptionDAO2, DAO contractDAO2, DAO acceptanceDAO2, DAO customerDAO2) {

		this.insuranceTreatment = insuranceTreatment;
		this.contractManagement = contractManagement;

		this.contractDAO = (contractDAO) contractDAO2;
		this.acceptanceDAO = (acceptanceDAO) acceptanceDAO2;
		this.insuranceDAO = (insuranceDAO) DAO;
		this.customerDAO = (customerDAO) customerDAO2;
		this.subscriptionDAO = (subscriptionDAO) subscriptionDAO2;
	}

	public Customer CreateCustomerContent(Scanner scanner) {
		System.out.println("1. ������ �� ���� �Է�, 2. ���ο� �� ���� �Է�");
		int menuNum = 0;

		while (true) {
			if (!scanner.hasNextInt()) {
				scanner.next();
				System.err.println("���ڸ� �Է��Ͻÿ� 1. ������ �� ���� �Է�, 2. ���ο� �� ���� �Է�");
			} else {
				menuNum = scanner.nextInt();
				if (!(menuNum > 0 && menuNum < 3)) {
					System.err.println("������ ������ϴ� 1. ������ �� ���� �Է�, 2. ���ο� �� ���� �Է�");
				} else {
					break;
				}
			}
		}

		while (true) {
			if (menuNum == 1) {
				System.out.println("������ �� ���� �Է� �����մϴ�");
				System.out.println("*******���� �� ����Ʈ******");
				//	customerDAO.showAllCustomerIDList();
				System.out.println("���� ���� ID�� �Է��Ͻÿ�");
				int OldcustomerID = scanner.nextInt();

				if (insuranceDAO.CheckIntData("customerID", "Customer", OldcustomerID)) {

					String InsuranceType = CreateSubscription(OldcustomerID, 1, scanner);
					Customer Oldcustomer = contractDAO.findCustomer(OldcustomerID);
					CreatePersonalInsuranceInformation(Oldcustomer, InsuranceType, scanner);

					return Oldcustomer;
				} else {
					System.out.println("�ش��ϴ� ���� ID�� �������� �ʽ��ϴ�.");
				}
			} else if (menuNum == 2) {
				System.out.println("���ο� �� ���� �Է� �����մϴ�");

				Customer customer = new Customer();

				System.out.println("******�� ������ �ۼ��Ͻʽÿ�.******");
				// ����_System.out.println("�� ���� �Է¿� �����Ͽ����ϴ�. �ٽ� �õ��Ͽ� �ֽʽÿ�.");

				int newCustomerID = insuranceDAO.SelectMaxID("customerID", "Customer") + 1;
				System.out.println("��ID��" + newCustomerID + "�Դϴ�.");
				customer.setCustomerID(newCustomerID);

				System.out.println("2. ���̸��� �Է��Ͻÿ�.");
				customer.setCustomerName(scanner.next());

				System.out.println("3. �� ����ó�� �Է��Ͻÿ�.");
				customer.setPhoneNum(scanner.next());

				// insuranceDAO ///////////////////////////
				customerDAO.insertCustomer(customer);

				// PersonalInfomation ����
				System.out.println("----���������� �ۼ��Ͻʽÿ�.----");
				PersonalInformation personalInformation = new PersonalInformation();

				System.out.println("������ �����Ͻÿ� 1.soldier, 2.driver, 3.officeWorker, "
						+ "4.constructionLaborer, 5.fireman, 6.policeman, 7.theOther");

				switch (scanner.nextInt()) {
				case 1:
					personalInformation.setJob(Job.soldier);
					break;
				case 2:
					personalInformation.setJob(Job.driver);
					break;
				case 3:
					personalInformation.setJob(Job.officeWorker);
					break;
				case 4:
					personalInformation.setJob(Job.constructionLaborer);
					break;
				case 5:
					personalInformation.setJob(Job.fireman);
					break;
				case 6:
					personalInformation.setJob(Job.policeman);
					break;
				case 7:
					personalInformation.setJob(Job.theOther);
					break;
				default:
					break;
				}

				System.out.println("����̷��� �Է��Ͻÿ�");
				personalInformation.setAccidentHistory(scanner.next());

				System.out.println("���¹�ȣ�� �Է��Ͻÿ�");
				while (true) {
					if (!scanner.hasNextInt()) {
						scanner.next();
						System.err.println("���ڷ� �Է��Ͻÿ�");
					} else {
						personalInformation.setAccountNumber(scanner.nextInt());
						break;
					}
				}

				System.out.println("������ �Է��Ͻÿ� M, W");
				while (true) {
					String YorN = scanner.next();
					if (YorN.equals("M") || YorN.equals("m")) {
						personalInformation.setGender(true);
						break;
					} else if (YorN.equals("W") || YorN.equals("w")) {
						personalInformation.setGender(false);
						break;
					} else {
						System.err.println("���� M�� W�� �Է��Ͻÿ�");
					}
				}

				System.out.println("�ڻ��� �Է��Ͻÿ�");
				while (true) {
					if (!scanner.hasNextInt()) {
						scanner.next();
						System.err.println("���ڷ� �Է��Ͻÿ�");
					} else {
						personalInformation.setProperty(scanner.nextInt());
						break;
					}
				}

				System.out.println("�ֹι�ȣ�� �Է��Ͻÿ�");
				personalInformation.setResidentRegistrationNumber(scanner.next());

				// Subscription�� ������ ����
				String InsuranceType = CreateSubscription(newCustomerID, 2, scanner);
				// ���� ���� �Է�
				Customer Fcustomer = CreatePersonalInsuranceInformation(customer, InsuranceType, scanner);

				// insuranceDAO /////////
				customerDAO.insertPersonalInformation(personalInformation, customer.getCustomerID());
				customerListImpl.add(Fcustomer);
				System.out.println();
				return Fcustomer;

			}
		}
	}

	private String CreateSubscription(int CustomerID, int Num, Scanner scanner) {
		insuranceDAO.showAllInsuranceID();
		System.out.println("****������ ����ID�� �Է��ϼ���*****");
		int InsuranceID = 0;
		
		while (true) {
			if (!scanner.hasNextInt()) {
				scanner.next();
				System.err.println("���ڸ� �Է��Ͻÿ�");
			} else {
				InsuranceID = scanner.nextInt();
				if (!insuranceDAO.CheckIntData("InsuranceID", "Insurance", InsuranceID)) {
					System.err.println("�ش��ϴ� ���� ID �������� �ʽ��ϴ�. ���Է��Ͻÿ�");
				} else {
					break;
				}
			}
		}
		String insuranceType = customerDAO.getInsuranceType(InsuranceID).toString();
		
		switch(Num) {
		case 1:
		Vector<Integer> InsuranceIDs = subscriptionDAO.getInsuranceID(CustomerID);
		
		for(int i=0; i<InsuranceIDs.size(); i++) {
			int FinsuranceID = InsuranceIDs.get(i);
			if(customerDAO.getInsuranceType(FinsuranceID).toString().equals(insuranceType)) {
				System.err.println("�ش� ���� ���� Ÿ���� ���迡 �̹� �����Ͽ����ϴ�");
				insuranceType = "not";
				break;
			}
		}
		
		if(!insuranceType.equals("not")) {subscriptionDAO.insertSubscription(InsuranceID, CustomerID);}
		return insuranceType;
	
		case 2:
		subscriptionDAO.insertSubscription(InsuranceID, CustomerID);	
		return insuranceType;
		
		default:
		return "not";	
		}
	}

	private Customer CreatePersonalInsuranceInformation(Customer Scustomer, String insuranceType, Scanner scanner) {
		Customer customer = Scustomer;

		switch (insuranceType) {
		case "Fire":
			System.out.println("*******ȭ�� ���� ������ �Է��մϴ�*********");
			Building building = new Building();

			System.out.println("�ּҸ� �Է��Ͻÿ�");
			building.setBuildingAddress(scanner.next());

			System.out.println("�ǹ� �ü��� �Է��Ͻÿ�");
			building.setBuildingPrice(scanner.nextInt());

			System.out.println("�ǹ� �Ը� �Է��Ͻÿ�");
			building.setBuildingScale(scanner.next());

			customer.setPersonalInformation(building);
			customerDAO.insertBuilding(building, customer.getCustomerID());

			break;

		case "Car":
			System.out.println("*******�ڵ��� ���� ������ �Է��մϴ�*********");
			Car car = new Car();

			System.out.println("���� ��ȣ�� �Է��Ͻÿ�");
			car.setCarNumber(scanner.next());

			System.out.println("���� ������ �Է��Ͻÿ� 1.����, 2.����, 3.����");
			switch (scanner.nextInt()) {
			case 1:
				car.setCarType(CarType.Compact);
				break;
			case 2:
				car.setCarType(CarType.Midsize);
				break;
			case 3:
				car.setCarType(CarType.FullSize);
				break;
			default:
				break;
			}

			System.out.println("���� ����� (ex �� ��) �Է��Ͻÿ� ");
			car.setDrivingCareer(scanner.nextInt());

			System.out.println("���� ���� ������ �Է��Ͻÿ� 1. 1�� ����, 2. 2�� ���� , 3. ����");
			switch (scanner.nextInt()) {
			case 1:
				car.setLicenseType(LicenseType.Class1);
				break;
			case 2:
				car.setLicenseType(LicenseType.Class2);
				break;
			case 3:
				car.setLicenseType(LicenseType.HGV);
				break;
			default:
				break;
			}

			customer.setPersonalInformation(car);
			customerDAO.insertCar(car, customer.getCustomerID());
			break;

		case "ActualCost":
			System.out.println("*******�Ǻ� ���� ������ �Է��մϴ�*********");

			ActualCost actualCost = new ActualCost();

			System.out.println("�������� �Է��Ͻÿ� 1. A, 2. B, 3. O, 4. AB, 5. RHMinus");
			switch (scanner.nextInt()) {
			case 1:
				actualCost.setBloodType(BloodType.A);
				break;
			case 2:
				actualCost.setBloodType(BloodType.B);
				break;
			case 3:
				actualCost.setBloodType(BloodType.O);
				break;
			case 4:
				actualCost.setBloodType(BloodType.AB);
				break;
			case 5:
				actualCost.setBloodType(BloodType.RHMinus);
				break;
			default:
				break;
			}

			System.out.println("������ �����Ͻÿ� 1. ��, 2. �索, 3. ������,  4. �ɱٰ��, 5. ������, 6. ������");
			switch (scanner.nextInt()) {
			case 1:
				actualCost.setDiseaseHistory(DiseaseHistory.Cancer);
				break;
			case 2:
				actualCost.setDiseaseHistory(DiseaseHistory.Diabetes);
				break;
			case 3:
				actualCost.setDiseaseHistory(DiseaseHistory.Hypertension);
				break;
			case 4:
				actualCost.setDiseaseHistory(DiseaseHistory.MyocardialInfarction);
				break;
			case 5:
				actualCost.setDiseaseHistory(DiseaseHistory.Hyperlipidemia);
				break;
			case 6:
				actualCost.setDiseaseHistory(DiseaseHistory.Stroke);
				break;
			default:
				break;
			}

			System.out.println("���� ������ �Է��մϴ�. ���� ���踦 �Է��Ͻÿ�");
			String Family = scanner.next();
			System.out.println("������ ������ �����Ͻÿ� 1. ��, 2. �索, 3. ������,  4. �ɱٰ��, 5. ������, 6. ������");
			DiseaseHistory diseaseHistory = null;

			switch (scanner.nextInt()) {
			case 1:
				diseaseHistory = DiseaseHistory.Cancer;
				break;
			case 2:
				diseaseHistory = DiseaseHistory.Diabetes;
				break;
			case 3:
				diseaseHistory = DiseaseHistory.Hypertension;
				break;
			case 4:
				diseaseHistory = DiseaseHistory.MyocardialInfarction;
				break;
			case 5:
				diseaseHistory = DiseaseHistory.Hyperlipidemia;
				break;
			case 6:
				diseaseHistory = DiseaseHistory.Stroke;
				break;
			default:
				break;
			}

			HashMap<String, String> A = new HashMap<String, String>();
			A.put(Family, diseaseHistory.toString());
			actualCost.setFamilyHistory(A);

			customer.setPersonalInformation(actualCost);
			customerDAO.insertActualCost(actualCost, customer.getCustomerID());
			break;

		case "not":
			System.err.println("�̹� �ش� ������ ������ ���̹Ƿ� �� ���� ���� �Է� ���� �Դϴ�");
			break;

		default:
			break;
		}
		System.out.println();
		return customer;
	}


	public float CalculatePremiumRateOfFire(float buildingP, float insuranceFee) {
		float insurancePremiumRate = insuranceFee;
		// �ǹ��� ������ 1������� ���.
		float buildingPrice = buildingP / 100000000;
		if (buildingPrice > 50) {
			insurancePremiumRate = (float) (insurancePremiumRate * 6.0);
		} else if (buildingPrice > 45) {
			insurancePremiumRate = (float) (insurancePremiumRate * 5.5);
		} else if (buildingPrice > 40) {
			insurancePremiumRate = (float) (insurancePremiumRate * 5.0);
		} else if (buildingPrice > 35) {
			insurancePremiumRate = (float) (insurancePremiumRate * 4.5);
		} else if (buildingPrice > 30) {
			insurancePremiumRate = (float) (insurancePremiumRate * 4.0);
		} else if (buildingPrice > 25) {
			insurancePremiumRate = (float) (insurancePremiumRate * 3.5);
		} else if (buildingPrice > 20) {
			insurancePremiumRate = (float) (insurancePremiumRate * 3.0);
		} else if (buildingPrice > 15) {
			insurancePremiumRate = (float) (insurancePremiumRate * 2.5);
		} else if (buildingPrice > 10) {
			insurancePremiumRate = (float) (insurancePremiumRate * 2.0);
		} else if (buildingPrice > 5) {
			insurancePremiumRate = (float) (insurancePremiumRate * 1.5);
		} else if (buildingPrice <= 5) {
			insurancePremiumRate = (float) (insurancePremiumRate * 1.0);
		}
		return insurancePremiumRate;
	}

	public float CalculatePremiumRateCar(Car car, float insuranceFee) {
		float insurancePremiumRate = insuranceFee;

		switch (car.getJob()) {
		case soldier:
			insurancePremiumRate = (float) (insurancePremiumRate * 0.7);
			break;
		case constructionLaborer:
			insurancePremiumRate = (float) (insurancePremiumRate * 0.8);
			break;
		case driver:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.5);
			break;
		case fireman:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.1);
			break;
		case officeWorker:
			insurancePremiumRate = (float) (insurancePremiumRate * 0.7);
			break;
		case policeman:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.2);
			break;
		case theOther:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.0);
			break;
		default:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.0);
			break;
		}
		if (car.getGender()) {
			insurancePremiumRate = (float) (insurancePremiumRate * 0.8);
		} else if (!car.getGender()) {
			insurancePremiumRate = (float) (insurancePremiumRate * 1.2);
		}

		switch (car.getCarType()) {
		case FullSize:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.3);
			break;
		case Compact:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.0);
			break;
		case Midsize:
			insurancePremiumRate = (float) (insurancePremiumRate * 0.7);
			break;
		default:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.0);
			break;
		}

		switch (car.getLicenseType()) {
		case Class1:
			insurancePremiumRate = (float) (insurancePremiumRate * 0.8);
			break;
		case Class2:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.1);
			break;
		case HGV:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.1);
			break;
		default:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.0);
			break;
		}

		if (car.getDrivingCareer() > 20) {
			insurancePremiumRate = (float) (insurancePremiumRate * 0.5);
		} else if (car.getDrivingCareer() > 15) {
			insurancePremiumRate = (float) (insurancePremiumRate * 0.75);
		} else if (car.getDrivingCareer() > 10) {
			insurancePremiumRate = (float) (insurancePremiumRate * 1.0);
		} else if (car.getDrivingCareer() > 5) {
			insurancePremiumRate = (float) (insurancePremiumRate * 1.25);
		} else if (car.getDrivingCareer() <= 5) {
			insurancePremiumRate = (float) (insurancePremiumRate * 1.5);
		}

		return insurancePremiumRate;
	}

	public float CalculatePremiumRateActual(ActualCost actualCost, float insuranceFee) {
		float insurancePremiumRate = insuranceFee;

		switch (actualCost.getJob()) {
		case soldier:
			insurancePremiumRate = (float) (insurancePremiumRate * 0.7);
			break;
		case constructionLaborer:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.3);
			break;
		case driver:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.1);
			break;
		case fireman:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.2);
			break;
		case officeWorker:
			insurancePremiumRate = (float) (insurancePremiumRate * 0.9);
			break;
		case policeman:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.3);
			break;
		case theOther:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.0);
			break;
		default:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.0);
			break;
		}
		if (actualCost.getGender()) {
			insurancePremiumRate = (float) (insurancePremiumRate * 1.1);
		} else if (!actualCost.getGender()) {
			insurancePremiumRate = (float) (insurancePremiumRate * 0.9);
		}

		switch (actualCost.getBloodType()) {
		case A:
			insurancePremiumRate = (float) (insurancePremiumRate * 0.8);
			break;
		case AB:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.2);
			break;
		case B:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.1);
			break;
		case O:
			insurancePremiumRate = (float) (insurancePremiumRate * 0.9);
			break;
		case RHMinus:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.5);
			break;
		default:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.0);
			break;
		}

		switch (actualCost.getDiseaseHistory()) {
		case Cancer:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.7);
			break;
		case Diabetes:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.5);
			break;
		case Hyperlipidemia:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.2);
			break;
		case Hypertension:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.6);
			break;
		case MyocardialInfarction:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.5);
			break;
		case Stroke:
			insurancePremiumRate = (float) (insurancePremiumRate * 1.5);
			break;
		default:
			insurancePremiumRate = (float) (insurancePremiumRate * 0.8);
			break;
		}

		if (actualCost.getFamilyHistory().containsKey("��")) {
			insurancePremiumRate = (float) (insurancePremiumRate * 1.5);
		} else if (actualCost.getFamilyHistory().containsKey("��")) {
			insurancePremiumRate = (float) (insurancePremiumRate * 1.5);
		} else {
			insurancePremiumRate = (float) (insurancePremiumRate * 0.8);
		}
		return insurancePremiumRate;
	}

	// �Ķ���ͷ� PremiumRate�� ������ ���� insurancePremiumRate�� �������� ���� �� ���Ƽ� ����.
	public int CalculatePaymentAmount(int InsuranceID) {
		int PaymentAmount = 1;
		return PaymentAmount;
	}

	public float CalculatePremiumRate(int customerID, int FactorGuideID) {
		return this.insurancePremiumRate;
	}

	public boolean Accept(int insuranceID, int customerID, Scanner scanner) {
		AcceptanceGuide acceptanceGuide = this.acceptanceDAO.findAcceptance(insuranceID);
		Customer customer = contractDAO.findCustomer(customerID);

		// ���� ������ ���� �� ���� �Ʒ� ������ ��������
		switch (this.customerDAO.getInsuranceType(insuranceID)) {
		case Fire: // ȭ�纸��
			Building building = (Building) FirstAccept(acceptanceGuide, customer, insuranceID);
			this.customerDAO.findBuildingCustomer(building, customerID);
			System.out.println("�ǹ��ּ�:" + building.getBuildingAddress() + " �ǹ�����:" + building.getBuildingPrice()
					+ " �ǹ��Ը�:" + building.getBuildingScale());
			FinalAccept(customer, scanner, insuranceID);
			return true;

		case Car:// �ڵ�������
			Car car = (Car) FirstAccept(acceptanceGuide, customer, insuranceID);
			this.customerDAO.findCarCustomer(car, customerID);
			System.out.println("�� ��ȣ:" + car.getCarNumber() + " �� ����:" + car.getCarType() + " �������: "
					+ car.getDrivingCareer() + " ��������:" + car.getLicenseType());
			FinalAccept(customer, scanner, insuranceID);
			return true;

		case ActualCost:// �Ǻ���
			ActualCost actualCost = (ActualCost) FirstAccept(acceptanceGuide, customer, insuranceID);
			this.customerDAO.findActualCostCustomer(actualCost, customerID);
			System.out.println("����:" + actualCost.getDiseaseHistory() + " ��������:" + actualCost.getFamilyHistory()
					+ " ������:" + actualCost.getBloodType());
			FinalAccept(customer, scanner, insuranceID);
			return true;

		default:
			return false;
		}
	}

	// ���� ����, �� �⺻ ���� �������� �޼ҵ�
	private PersonalInformation FirstAccept(AcceptanceGuide acceptanceGuide, Customer customer, int insuranceID) {
		if (acceptanceGuide.getAcceptanceID() == 0) {
			acceptanceGuide.setAcceptanceID(5001);
		}
		System.out.println("�μ�ID:" + acceptanceGuide.getAcceptanceID());
		System.out.println(acceptanceGuide.getScamCase());
		System.out.println("������:" + acceptanceGuide.getRiskEvaluation());

		// ���� ���� �⺻ ���� ���� ���
		System.out.println("��ID:" + customer.getCustomerID() + " ���̸�:" + customer.getCustomerName() + " ����ȭ��ȣ:"
				+ customer.getPhoneNum());
		PersonalInformation personalInformation = this.customerDAO.findPersonalInformation(customer, insuranceID);
		System.out.println("�����Ż���̷�:" + personalInformation.getAccidentHistory() + " �����¹�ȣ:"
				+ personalInformation.getAccountNumber() + " ������:" + personalInformation.getJob() + " �����:"
				+ personalInformation.getProperty() + " ���ֹε�Ϲ�ȣ:"
				+ personalInformation.getResidentRegistrationNumber());

		return personalInformation;
	}

	// �μ� ���� or �ź� �޼ҵ�
	private void FinalAccept(Customer customer, Scanner scanner, int insuranceID) {
		// ���� �μ���å�� �� ������ ���� ���� or �ź� ����
		System.out.println("1.����, 2.�ź�");
		while (!scanner.hasNextInt()) {
			scanner.next();
			System.err.println("���ڸ� �Է��ؾ� �մϴ�.");
		}
		switch (scanner.nextInt()) {
		case 1:
			// �μ� ���� ��ٸ� ���� ���������� true(true, false�� ���� ����� �ۼ����� ���ƾ� ���� �����ؾ��� - alternate)
			customer.setSubscriptionStatus(true);
			this.subscriptionDAO.updateSubscriptionStatus(customer, insuranceID);
			break;
		case 2:

			System.out.println("���� �Ұ� ������ �ۼ��ϼ���.");
			String notAgree = scanner.next();
			System.out.println("���� �Ұ� ������ '" + notAgree + "'�Դϴ�.");
			// ���� ���� ��ư�� ��������

		}
	}

	public void ShowMenual(Scanner sc) {
		System.out.println("*******�޴��� Ȯ���ϱ�****");
		System.out.println("1. �Ǹ� �޴��� Ȯ���ϱ�, 2. ��ǰ ���� Ȯ���ϱ�");
		int menuNum = 0;
		while (true) {
			if (!sc.hasNextInt()) {
				sc.next();
				System.err.println("���ڸ� �Է��Ͻÿ�.  1. �Ǹ� �޴��� Ȯ���ϱ�, 2. ��ǰ ���� Ȯ���ϱ�");
			} else {
				menuNum = sc.nextInt();
				if (!(menuNum > 0 && menuNum < 3)) {
					System.err.println("���� �޴� �� �����Ͻÿ�.  1. �Ǹ� �޴��� Ȯ���ϱ�, 2. ��ǰ ���� Ȯ���ϱ�");
				} else {
					break;
				}
			}
		}
		// saleManual, manual �Ѵ� ���⼭ �Ǵ�
		if (menuNum == 1) {
			System.out.println("*******�Ǹ� �޴��� Ȯ���ϱ�********");
		} else {
			System.out.println("*******��ǰ �޴��� Ȯ���ϱ�********");
		}
		this.InsuranceManual(sc, menuNum);
	}

	public void InsuranceManual(Scanner sc, int menuNum) {
		this.insuranceDAO.searchInsuranceIDandName();
		System.out.println("Ȯ���� ���� ID�� �Է��Ͻÿ�.");
		int InsuranceID = 0;
		while (true) {
			if (!sc.hasNextInt()) {
				sc.next();
				System.err.println("���ڸ� �Է��Ͻÿ�.");
			} else {
				InsuranceID = sc.nextInt();
				if (!(insuranceDAO.searchInsuranceIDforManual(InsuranceID))) {
					System.err.println("�ش��ϴ� ����ID Ȥ�� �޴����� �������� �ʽ��ϴ�");
				} else {
					break;
				}
			}
		}
		// DAO///////////////
		if (menuNum == 1) {
			insuranceDAO.searchInsuranceSalesManual(InsuranceID);
		} else {
			insuranceDAO.searchInsuranceManual(InsuranceID);
		}
	}

}