package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Insurance.ActualCostInsurance;
import Insurance.CarInsurance;
import Insurance.DamageInformation;
import Insurance.FireInsurance;
import Insurance.Goods_Personal;
import Insurance.Injury;
import Insurance.Insurance;
import Insurance.SelfVehicleDamage;
import Insurance.Goods_Personal.GSeparation;
import Insurance.SelfVehicleDamage.SSeparation;

public class insuranceDAO extends DAO {
	
	
	public Vector<Insurance> searchInsuranceIDandName() {
		this.sql = "select insuranceID, insuranceName, insuranceType from Insurance";
		Vector<Insurance> VecInsurance = new Vector<Insurance>();
		
		try {
			this.connect = getConnection();
			this.statement = this.connect.prepareStatement(this.sql);
			this.resultSet = this.statement.executeQuery();
			while (this.resultSet.next()) {
				Insurance insurance = new Insurance();
				insurance.setInsuranceID(resultSet.getInt("insuranceID"));	
				insurance.setInsuranceName(resultSet.getString("insuranceName"));
				insurance.setInsuranceType(Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType")));	
				VecInsurance.add(insurance);
			}
			return VecInsurance;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("InsuranceDAO.searchInsuranceIDandName : " + e.getMessage());
		} finally {
			closeConnection(this.connect);
		}
	}
	
	public Vector<Insurance> InsuranceNameVector(String Insurancetype){
		this.sql = "select insuranceID, insuranceName from Insurance where insuranceType = ?";
		Vector<Insurance> VecInsurance = new Vector<Insurance>();
		
		try {
			this.connect = getConnection();
			this.statement = this.connect.prepareStatement(this.sql);
			this.statement.setString(1, Insurancetype);
			this.resultSet = this.statement.executeQuery();
			while (this.resultSet.next()) {
				Insurance insurance = new Insurance();
				insurance.setInsuranceID(resultSet.getInt("insuranceID"));	
				insurance.setInsuranceName(resultSet.getString("insuranceName"));
				VecInsurance.add(insurance);
			}
			return VecInsurance;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("InsuranceDAO.InsuranceNameVector : " + e.getMessage());
		} finally {
			closeConnection(this.connect);
		}
	}
		
//	//조건에 해당하는 보험명 가져오기
//	public Vector<Insurance> InsuranceVector(String whereColumn, String whereValue){
//		this.sql = "select insuranceID, insuranceName, insuranceType from Insurance where ? = ?";
//		Vector<Insurance> VecInsurance = new Vector<Insurance>();
//
//		try {
//			this.connect = getConnection();
//			this.statement = this.connect.prepareStatement(this.sql);
//			this.statement.setString(1, whereColumn);
//			this.statement.setString(2, whereValue);
//			this.resultSet = this.statement.executeQuery();
//			while (this.resultSet.next()) {
//				Insurance insurance = new Insurance();
//				insurance.setInsuranceID(resultSet.getInt("insuranceID"));	
//				insurance.setInsuranceName(resultSet.getString("insuranceName"));
//				insurance.setInsuranceType(Insurance.InsuranceType.valueOf(resultSet.getString("insuranceType")));
//				VecInsurance.add(insurance);
//			}
//			return VecInsurance;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException("InsuranceDAO.InsuranceNameVector : " + e.getMessage());
//		} finally {
//			closeConnection(this.connect);
//		}
//	}
		
	//보험 id에 해당하는 보험 가져오기
	public Insurance InsuranceForID(int InsuranceID){
		this.sql = "select insuranceID, insuranceName, insuranceType from Insurance where InsuranceID = ?";
		Insurance insurance = new Insurance();
		
		try {
			this.connect = getConnection();
			this.statement = this.connect.prepareStatement(this.sql);
			this.statement.setInt(1, InsuranceID);
			this.resultSet = this.statement.executeQuery();
			if (this.resultSet.next()) {
				insurance.setInsuranceID(resultSet.getInt("insuranceID"));
				insurance.setInsuranceName(resultSet.getString("insuranceName"));
				insurance.setInsuranceType(Insurance.InsuranceType.valueOf(resultSet.getString("insuranceType")));
			}
			return insurance;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("InsuranceDAO.InsuranceForID : " + e.getMessage());
		} finally {
			closeConnection(this.connect);
		}
	}
	
	public void InsertInsurance(Insurance insuracne) {
		this.sql = "insert into insurance values (?, ?, ?, ?, ?, ?)";
		try {
			this.connect = getConnection();
			this.statement = this.connect.prepareStatement(this.sql);

			this.statement.setInt(1, insuracne.getInsuranceID());
			this.statement.setString(2, insuracne.getInsuranceName());
			this.statement.setInt(3, insuracne.getInsuranceFee());
			this.statement.setString(4, insuracne.getInsuranceType().toString());
			this.statement.setString(5, insuracne.getInsuranceManual());
			this.statement.setString(6, insuracne.getInsuranceSalesManual());
			this.statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("InsuranceDAO.insertInsurance : " + e.getMessage());
		} finally {
			closeConnection(this.connect);
		}
	}

	public void InsertFireInsurance(FireInsurance insurance) {
		this.sql = "insert into Fireinsurance (FinsuranceID, directGuaranteedAmount, directGuaranteedContent, fireGuaranteedAmount, fireGuaranteedContent, refugeGuaranteedAmount, refugeGuaranteedContent) values (?, ?, ?, ?, ?, ?, ?)";
		try {
			this.connect = getConnection();
			this.statement = this.connect.prepareStatement(this.sql);

			this.statement.setInt(1, insurance.getInsuranceID());
			this.statement.setInt(2, insurance.getDirectDamage().getDamageGuaranteedAmount());
			this.statement.setString(3, insurance.getDirectDamage().getDamageGuaranteedContent());
			this.statement.setInt(4, insurance.getFireDamage().getDamageGuaranteedAmount());
			this.statement.setString(5, insurance.getFireDamage().getDamageGuaranteedContent());
			this.statement.setInt(6, insurance.getRefugeDamage().getDamageGuaranteedAmount());
			this.statement.setString(7, insurance.getRefugeDamage().getDamageGuaranteedContent());
			this.statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("InsuranceDAO.insertInsurance : " + e.getMessage());
		} finally {
			closeConnection(this.connect);
		}
	}

	public void InsertCarInsurance(CarInsurance insurance) {
		this.sql = "insert into Carinsurance (CinsuranceID, goodsGuaranteeContent, goodsGuaranteeLimit, goodsSeparation, personalGuaranteeContent, personalProvisionLimit, personalSeparation, subscriptionFeeForAccidentalInjuries, subscriptionFeeForInjury, selfVehicleSeparation) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			this.connect = getConnection();
			this.statement = this.connect.prepareStatement(this.sql);

			this.statement.setInt(1, insurance.getInsuranceID());
			this.statement.setString(2, insurance.getGoodsIndemnification().getGuaranteeContent());
			this.statement.setInt(3, insurance.getGoodsIndemnification().getProvisionLimit());
			this.statement.setString(4, insurance.getGoodsIndemnification().getSeparation().toString());
			this.statement.setString(5, insurance.getPersonalIndemnification().getGuaranteeContent());
			this.statement.setInt(6, insurance.getPersonalIndemnification().getProvisionLimit());
			this.statement.setString(7, insurance.getPersonalIndemnification().getSeparation().toString());
			this.statement.setInt(8, insurance.getSelfVehicleDamage().getSubscriptionFeeForAccidentalInjuries());
			this.statement.setInt(9, insurance.getSelfVehicleDamage().getSubscriptionFeeForInjury());
			this.statement.setString(10, insurance.getSelfVehicleDamage().getSeparation().toString());
			this.statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("InsuranceDAO.insertInsurance : " + e.getMessage());
		} finally {
			closeConnection(this.connect);
		}
	}

	public void InsertActualCostInsurance(ActualCostInsurance insurance) {
		this.sql = "insert into ActualCostinsurance (AinsuranceID, hospitalizationFee, hospitalizationReason, outpatientFee, outpatientReason) values (?, ?, ?, ?, ?)";
		try {
			this.connect = getConnection();
			this.statement = this.connect.prepareStatement(this.sql);

			this.statement.setInt(1, insurance.getInsuranceID());
			this.statement.setInt(2, insurance.getInjuryHospitalization().getProvisionFee());
			this.statement.setString(3, insurance.getInjuryHospitalization().getProvisionReason());
			this.statement.setInt(4, insurance.getInjuryOutpatient().getProvisionFee());
			this.statement.setString(5, insurance.getInjuryOutpatient().getProvisionReason());
			this.statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("InsuranceDAO.insertInsurance : " + e.getMessage());
		} finally {
			closeConnection(this.connect);
		}
	}
	
	public Vector<Insurance> SearchNullAcceptanceInsuranceID() {
		this.sql = "select Insurance.InsuranceID, insuranceName, insuranceType from Insurance LEFT JOIN Acceptance ON Insurance.insuranceID = Acceptance.insuranceID where Acceptance.Acceptanceid is null";
		Insurance insurance;
		Vector<Insurance> insurances = new Vector<Insurance>();
		try {
			this.connect = getConnection();
			this.statement = this.connect.prepareStatement(sql);
			this.resultSet = this.statement.executeQuery();
			while (this.resultSet.next()) {
				insurance = new Insurance();
				insurance.setInsuranceID(this.resultSet.getInt("insuranceID"));
				insurance.setInsuranceName(this.resultSet.getString("insuranceName"));
				insurance.setInsuranceType(Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType")));	
				insurances.add(insurance);
			}
		} catch (SQLException e) {
			throw new RuntimeException("InsuranceDAO.showBeforeAcceptanceInsuranceID :" + e.getMessage());
		} finally {
			closeConnection(this.connect);
		}
		return insurances;
	}
	
	
	
	public void showAllInsuranceID() {
		this.sql = "select Insurance.InsuranceID, insuranceType from Insurance, Acceptance where Insurance.insuranceID = Acceptance.insuranceID";
		Vector<Integer> fireVector = new Vector<Integer>();
		Vector<Integer> carVector = new Vector<Integer>();
		Vector<Integer> actualCostVector = new Vector<Integer>();
		try {
			this.connect = getConnection();
			this.statement = this.connect.prepareStatement(sql);
			this.resultSet = this.statement.executeQuery();
			while (this.resultSet.next()) {
				if (Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType"))
						.equals(Insurance.InsuranceType.Fire)) {
					fireVector.add(this.resultSet.getInt("insuranceID"));
				} else if (Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType"))
						.equals(Insurance.InsuranceType.Car)) {
					carVector.add(this.resultSet.getInt("insuranceID"));
				} else if (Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType")).equals(Insurance.InsuranceType.ActualCost)) {
					actualCostVector.add(this.resultSet.getInt("insuranceID"));
				}
			}
			System.out.print("(화占썹보占쏙옙:");
			for (int i = 0; i < fireVector.size(); i++) {

				System.out.print(fireVector.get(i) + " ");
			}
			System.out.print(") (占실븝옙占쏙옙:");
			for (int i = 0; i < actualCostVector.size(); i++) {
				System.out.print(actualCostVector.get(i) + " ");
			}
			System.out.print(") (占쌘듸옙占쏙옙占쏙옙占쏙옙:");
			for (int i = 0; i < carVector.size(); i++) {
				System.out.print(carVector.get(i) + " ");
			}
			System.out.print(")" + "\n");

		} catch (SQLException e) {
			throw new RuntimeException("InsuranceDAO.showAllInsuranceID :" + e.getMessage());
		} finally {
			closeConnection(this.connect);
		}
	}
	
	public boolean searchInsuranceIDforManual(int insuranceID) {
	      this.sql = "select * from Insurance where insuranceID";

	      try {
	         this.connect = this.getConnection();
	         this.statement = connect.prepareStatement(sql);
	         this.resultSet = this.statement.executeQuery();

	         if (this.resultSet.next()) {
	            return true;
	         }
	      } catch (SQLException e) {
	         throw new RuntimeException("InsuranceDAO.searchInsuranceIDforManual :" + e.getMessage());
	      } finally {
	         closeConnection(connect);
	      }
	      return false;
	   }

	 public String searchInsuranceSalesManual(int insuranceID) {
	      this.sql = "select insuranceSalesManual from Insurance where insuranceID = ?";

	      try {
	         this.connect = this.getConnection();
	         this.statement = connect.prepareStatement(this.sql);
	         this.statement.setInt(1, insuranceID);
	         this.resultSet = this.statement.executeQuery();

	         if (this.resultSet.next()) {
	            String InsuranceSalesManual = resultSet.getString("insuranceSalesManual");
	            return InsuranceSalesManual;
	         } else {
	        	return "";
	            //System.out.println("�빐�떦�븯�뒗 �뙋留� 硫붾돱�뼹 議댁옱 �븡�쓬");
	         }
	      } catch (SQLException e) {
	         throw new RuntimeException("InsuranceDAO.searchInsuranceSalesManual :" + e.getMessage());
	      } finally {
	         closeConnection(connect);
	      }
	   }

	   public String searchInsuranceManual(int insuranceID) {
	      this.sql = "select insuranceManual from Insurance where InsuranceID = ?";

	      try {
	         this.connect = this.getConnection();
	         this.statement = connect.prepareStatement(sql);
	         this.statement.setInt(1, insuranceID);
	         this.resultSet = this.statement.executeQuery();
	         if (this.resultSet.next()) {
	            String InsuranceManual = resultSet.getString("insuranceManual");
	            return InsuranceManual;
	         } else {
	            return "";
	        	 //System.out.println("�빐�떦�븯�뒗 �긽�뭹 硫붾돱�뼹 議댁옱 �븡�쓬");
	         }
	      } catch (SQLException e) {
	         throw new RuntimeException("InsuranceDAO.searchInsuranceManual :" + e.getMessage());
	      } finally {
	         closeConnection(connect);
	      }
	   }
	   
	   public float getInsuranceFee(int insuranceID) {
		      this.sql = "select insuranceFee from Insurance where insuranceID = ?";
		      float insuranceFee = 0;
		      
		      try {
		         this.connect = this.getConnection();
		         PreparedStatement statement = connect.prepareStatement(this.sql);
		         
		         statement.setInt(1, insuranceID);
		         ResultSet resultSet = statement.executeQuery();

		      if (resultSet.next()) {
		            insuranceFee = resultSet.getInt("insuranceFee");
		         }
		         
		      } catch (SQLException e) {
		         throw new RuntimeException("InsuranceDAO.getInsurance :" + e.getMessage());
		      } finally {
		         closeConnection(connect);
		      }
		      return insuranceFee;
		   }
	   
	   public FireInsurance getFireInsurance(int insurnceID) {
			this.sql = "select insuranceID, insuranceName, insuranceFee, insuranceType, insuranceManual, insuranceSalesManual,"
					+ " directGuaranteedAmount, directGuaranteedContent, fireGuaranteedAmount, fireGuaranteedContent, refugeGuaranteedAmount, "
					+ "refugeGuaranteedContent from Insurance, FireInsurance "
					+ "where Insurance.insuranceID = FireInsurance.FinsuranceID AND insuranceID = " + insurnceID ;
			FireInsurance insurance = new FireInsurance();
			try {
				this.connect = getConnection();
				this.statement = this.connect.prepareStatement(sql);
				this.resultSet = this.statement.executeQuery();
				while (this.resultSet.next()) {
					insurance.setInsuranceID(this.resultSet.getInt("insuranceID"));
					insurance.setInsuranceName(this.resultSet.getString("insuranceName"));
					insurance.setInsuranceFee(this.resultSet.getInt("insuranceFee"));
					insurance.setInsuranceType(Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType")));	
					insurance.setInsuranceManual(this.resultSet.getString("insuranceManual"));
					insurance.setInsuranceSalesManual(this.resultSet.getString("insuranceSalesManual"));
					
					DamageInformation directDamage, fireDamage, refugeDamage;
					
					directDamage = new DamageInformation();
					directDamage.setDamageGuaranteedAmount(this.resultSet.getInt("directGuaranteedAmount"));
					directDamage.setDamageGuaranteedContent(this.resultSet.getString("directGuaranteedContent"));
					insurance.setDirectDamage(directDamage);
					
					fireDamage = new DamageInformation();
					fireDamage.setDamageGuaranteedAmount(this.resultSet.getInt("fireGuaranteedAmount"));
					fireDamage.setDamageGuaranteedContent(this.resultSet.getString("fireGuaranteedContent"));
					insurance.setFireDamage(fireDamage);
					
					refugeDamage = new DamageInformation();
					refugeDamage.setDamageGuaranteedAmount(this.resultSet.getInt("refugeGuaranteedAmount"));
					refugeDamage.setDamageGuaranteedContent(this.resultSet.getString("refugeGuaranteedContent"));
					insurance.setRefugeDamage(refugeDamage);
				}
			} catch (SQLException e) {
				throw new RuntimeException("InsuranceDAO.getFireInsurance :" + e.getMessage());
			} finally {
				closeConnection(this.connect);
			}
			return insurance;
	   }
	   
	   public CarInsurance getCarInsurance(int insurnceID) {
			this.sql = "select insuranceID, insuranceName, insuranceFee, "
					+ "insuranceType, insuranceManual, insuranceSalesManual, "
					+ "goodsGuaranteeContent,goodsGuaranteeLimit, goodsSeparation, "
					+ "personalGuaranteeContent, personalProvisionLimit, personalSeparation, "
					+ "subscriptionFeeForAccidentalInjuries, "
					+ "subscriptionFeeForInjury, selfVehicleSeparation "
					+ "from Insurance, CarInsurance where Insurance.insuranceID = CarInsurance.CinsuranceID AND Insurance.insuranceID = " + insurnceID;

			CarInsurance insurance = new CarInsurance();
			try {
				this.connect = getConnection();
				this.statement = this.connect.prepareStatement(sql);
				this.resultSet = this.statement.executeQuery();
				while (this.resultSet.next()) {
					insurance.setInsuranceID(this.resultSet.getInt("insuranceID"));
					insurance.setInsuranceName(this.resultSet.getString("insuranceName"));
					insurance.setInsuranceFee(this.resultSet.getInt("insuranceFee"));
					insurance.setInsuranceType(Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType")));	
					insurance.setInsuranceManual(this.resultSet.getString("insuranceManual"));
					insurance.setInsuranceSalesManual(this.resultSet.getString("insuranceSalesManual"));
					
		        	
		            Goods_Personal goods, pesonal;  
		            goods = new Goods_Personal();
		            goods.setSeparation(GSeparation.valueOf(this.resultSet.getString("goodsSeparation")));
		            goods.setProvisionLimit(this.resultSet.getInt("goodsGuaranteeLimit"));
		            goods.setGuaranteeContent(this.resultSet.getString("goodsGuaranteeContent"));
		            insurance.setGoodsIndemnification(goods);
		            
		            pesonal = new Goods_Personal();
		            pesonal.setSeparation(GSeparation.valueOf(this.resultSet.getString("personalSeparation")));
		            pesonal.setProvisionLimit(this.resultSet.getInt("personalProvisionLimit"));
		            pesonal.setGuaranteeContent(this.resultSet.getString("personalGuaranteeContent"));
		            insurance.setPersonalIndemnification(pesonal);

		            SelfVehicleDamage selfVehicleDamage = new SelfVehicleDamage();
		    		selfVehicleDamage.setSeparation(SSeparation.valueOf(this.resultSet.getString("selfVehicleSeparation")));
		            selfVehicleDamage.setSubscriptionFeeForInjury(this.resultSet.getInt("subscriptionFeeForInjury"));
		            selfVehicleDamage.setSubscriptionFeeForAccidentalInjuries(this.resultSet.getInt("subscriptionFeeForAccidentalInjuries"));
		            insurance.setSelfVehicleDamage(selfVehicleDamage);
				}
			} catch (SQLException e) {
				throw new RuntimeException("InsuranceDAO.getCarInsurance :" + e.getMessage());
			} finally {
				closeConnection(this.connect);
			}
			return insurance;
	   }
	   
	   public ActualCostInsurance getActualCostInsurance(int insurnceID) {
			this.sql = "select insuranceID, insuranceName, insuranceFee, insuranceType, insuranceManual, insuranceSalesManual, "
					+ "hospitalizationFee, hospitalizationReason, outpatientFee, outpatientReason "
					+ "from Insurance, ActualCostInsurance where Insurance.insuranceID = ActualCostInsurance.AinsuranceID "
					+ "AND insuranceID = " + insurnceID ;
			ActualCostInsurance insurance = new ActualCostInsurance();
			try {
				this.connect = getConnection();
				this.statement = this.connect.prepareStatement(sql);
				this.resultSet = this.statement.executeQuery();
				while (this.resultSet.next()) {
					insurance.setInsuranceID(this.resultSet.getInt("insuranceID"));
					insurance.setInsuranceName(this.resultSet.getString("insuranceName"));
					insurance.setInsuranceFee(this.resultSet.getInt("insuranceFee"));
					insurance.setInsuranceType(Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType")));	
					insurance.setInsuranceManual(this.resultSet.getString("insuranceManual"));
					insurance.setInsuranceSalesManual(this.resultSet.getString("insuranceSalesManual"));
					
		            Injury hospitalization, outpatient;
		            
		            hospitalization = new Injury();        
		            hospitalization.setProvisionFee(this.resultSet.getInt("insuranceID"));
		            hospitalization.setProvisionReason(this.resultSet.getString("insuranceName"));
		            insurance.setInjuryHospitalization(hospitalization);
		            
		            outpatient = new Injury();
		            outpatient.setProvisionFee(this.resultSet.getInt("insuranceID"));
		            outpatient.setProvisionReason(this.resultSet.getString("insuranceName"));
		            insurance.setInjuryOutpatient(outpatient);
					
				}
			} catch (SQLException e) {
				throw new RuntimeException("InsuranceDAO.getActualCostInsurance :" + e.getMessage());
			} finally {
				closeConnection(this.connect);
			}
			return insurance;
	   }



}
