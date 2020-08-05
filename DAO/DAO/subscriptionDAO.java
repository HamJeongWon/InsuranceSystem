package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import Subscription.Subscription;
import Customer.Customer;
import Insurance.Insurance;
import Insurance.Insurance.InsuranceType;

public class subscriptionDAO extends DAO{
	public Vector<Integer> showAcceptanceAprove() {
        this.sql = "select insuranceID, customerID from subscription where subscriptionStatus = 0;";
           
           
        Vector<Integer> IDvector = new Vector<Integer>();
          try {
                 this.connect = getConnection();
                 this.statement = this.connect.prepareStatement(sql);
                 this.resultSet= this.statement.executeQuery();
   
                 while(this.resultSet.next()) {
                    IDvector.add(this.resultSet.getInt("insuranceID"));
                    IDvector.add(this.resultSet.getInt("customerID"));         
                 }            
              }catch(SQLException e) {
                 throw new RuntimeException("InsuranceDAO.showAcceptanceAprove :" + e.getMessage());
              }finally {
                 closeConnection(this.connect);
              }
          return IDvector;
             
     }

	 public Vector<Integer> showSubscriptionCustomer() {
         this.sql = "select insuranceID, customerID from subscription where subscriptionStatus = 1 and contractID !='null'";            
            
         Vector<Integer> IDvector = new Vector<Integer>();
           try {
                  this.connect = getConnection();
                  this.statement = this.connect.prepareStatement(sql);
                  
                  this.resultSet= this.statement.executeQuery();
    
                  while(this.resultSet.next()) {
                     IDvector.add(this.resultSet.getInt("insuranceID"));
                     IDvector.add(this.resultSet.getInt("customerID"));         
                  }            
               }catch(SQLException e) {
                  throw new RuntimeException("InsuranceDAO.showSubscriptionCustomer :" + e.getMessage());
               }finally {
                  closeConnection(this.connect);
               }
           return IDvector;
              
      }
	 
	 public int showAllCustomerID() {
		  int index = 0;
         this.sql = "select distinct(customerID),distinct(subscription.insuranceID), insuranceType" + 
               " from subscription" + 
               " join insurance on subscription.insuranceid = subscription.insuranceid" + 
               " where subscriptionstatus = 1;";
         Vector<Integer> fireVector = new Vector<Integer>();
         Vector<Integer> carVector = new Vector<Integer>();
         Vector<Integer> actualCostVector = new Vector<Integer>();
         try {
            this.connect = getConnection();
            this.statement = this.connect.prepareStatement(sql);
            this.resultSet= this.statement.executeQuery();
            while(this.resultSet.next()) {
               if(Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType")).equals(Insurance.InsuranceType.Fire)) {
                  fireVector.add(this.resultSet.getInt("customerID"));
               }else if(Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType")).equals(Insurance.InsuranceType.Car)) {
                  carVector.add(this.resultSet.getInt("customerID"));      
               }else if(Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType")).equals(Insurance.InsuranceType.ActualCost)) {
                  actualCostVector.add(this.resultSet.getInt("customerID"));
               }
            }
            
            if(fireVector.size() == 0 && actualCostVector.size() == 0 && carVector.size() == 0) {
           	 System.out.println("���� ���Ե� ���� �����ϴ�");
            }else {
           	 System.out.print("(ȭ�纸�� ��:");
           	 for(int i=0; i<fireVector.size(); i++) {

           		 System.out.print(fireVector.get(i) + " ");
           	 }
           	 System.out.print(") (�Ǻ��� ��:");
           	 for(int i=0; i<actualCostVector.size(); i++) {
           		 System.out.print(actualCostVector.get(i) + " ");
           	 }
           	 System.out.print(") (�ڵ������� ��:");
           	 for(int i=0; i<carVector.size(); i++) {
           		 System.out.print(carVector.get(i) + " ");
           	 }
           	 System.out.print(")" + "\n");
           	 index = 1;
            }
         }catch(SQLException e) {
       	  throw new RuntimeException("InsuranceDAO.showAllCustomerID :" + e.getMessage());
         }finally {
       	  closeConnection(this.connect);
         }
         return index;
	  }
	 
	   public void deleteSubscription(int customerID, int insuranceID) {
           this.sql = "delete from subscription where customerID= ? and insuranceID = ?";
            try {
                 this.connect = getConnection();
                 this.statement = this.connect.prepareStatement(this.sql);
                
                 this.statement.setInt(1, customerID);
                 this.statement.setInt(2, insuranceID);
                 this.statement.executeUpdate();
                 

              }catch(SQLException e) {
                 throw new RuntimeException("InsuranceDAO.deleteSubscription :" + e.getMessage());
              }finally {
                 closeConnection(this.connect);
              }
           }
	   
	   public void updateSubscriptionStatus(Customer customer, int insuranceID) {
	         this.sql = "update subscription set subscriptionStatus = ? where customerID = ? and insuranceID = ?";

	         try {
	            this.connect = getConnection();
	            this.statement = this.connect.prepareStatement(this.sql);
	            this.statement.setBoolean(1, customer.isSubscriptionStatus());
	            this.statement.setInt(2, customer.getCustomerID());
	            this.statement.setInt(3, insuranceID);
	            this.statement.executeUpdate();
	            

	         }catch(SQLException e) {
	            throw new RuntimeException("InsuranceDAO.updateSubscriptionStatus :" + e.getMessage());
	         }finally {
	            closeConnection(this.connect);
	         }
	      }
	   
	   
	   public void updateSubscriptionStatusFromWeb(int customerID, int insuranceID) {
	         this.sql = "update subscription set subscriptionStatus = true where customerID = ? and insuranceID = ?";

	         try {
	            this.connect = getConnection();
	            this.statement = this.connect.prepareStatement(this.sql);
	            this.statement.setInt(1, customerID);
	            this.statement.setInt(2, insuranceID);
	            this.statement.executeUpdate();
	            

	         }catch(SQLException e) {
	            throw new RuntimeException("InsuranceDAO.updateSubscriptionStatus :" + e.getMessage());
	         }finally {
	            closeConnection(this.connect);
	         }
	      }
	   
		public boolean insertSubscription(int insuranceID, int customerID) {
			this.sql = "insert into Subscription values(?, ?, null, false)";

			try {
				this.connect = this.getConnection();
				this.statement = connect.prepareStatement(sql);
				this.statement.setInt(1, insuranceID);
				this.statement.setInt(2, customerID);
				statement.execute();
				return true;
			} catch (SQLException e) {
				//throw new RuntimeException("InsuranceDAO.insertSubscription :" + e.getMessage());
				System.err.println("insertSubscription ����ID �ߺ�");
			} finally {
				closeConnection(connect);
			}
			return false;
		}
		
		 public void insertContratIDtoSubscription(int contractID, int customerID, int InsuracneID) {
	         this.sql = "update subscription set contractID = ? where customerID = ? and insuranceID = ?";

	         try {
	            this.connect = getConnection();
	            this.statement = this.connect.prepareStatement(this.sql);
	            this.statement.setInt(1, contractID);
	            this.statement.setInt(2, customerID);
	            this.statement.setInt(3, InsuracneID);
	            this.statement.executeUpdate();
	            

	         }catch(SQLException e) {
	            throw new RuntimeException("InsuranceDAO.insertContratIDtoSubscription :" + e.getMessage());
	         }finally {
	            closeConnection(this.connect);
	         }
	      }
		public void showAllSubscription() {
			this.sql = "select insuranceID, customerID from Subscription where subscriptionStatus = true and contractID is null";
			try {
				this.connect = this.getConnection();
				PreparedStatement statement = connect.prepareStatement(this.sql);
				ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
					int insuranceID = resultSet.getInt("insuranceID");
					int customerID = resultSet.getInt("customerID");
					System.out.println("����ID :" + insuranceID + "  ��ID: " + customerID );
				}

			} catch (SQLException e) {
				throw new RuntimeException("InsuranceDAO.showAllSubscription :" + e.getMessage());
			} finally {
				closeConnection(connect);
			}
		}

		public Vector<Integer> getInsuranceID(int customerID) {
			this.sql = "select insuranceID from Subscription where customerID = ?";
			Vector<Integer> InsuranceIDs = new Vector<Integer> ();
			
			try {
				this.connect = this.getConnection();
				PreparedStatement statement = connect.prepareStatement(this.sql);
				statement.setInt(1, customerID);
				ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
					int insuranceID = resultSet.getInt("insuranceID");
					InsuranceIDs.add(insuranceID);
				}
			} catch (SQLException e) {
				throw new RuntimeException("SubscriptionDAO.getInsuranceID :" + e.getMessage());
			} finally {
				closeConnection(connect);
			}
			return InsuranceIDs;
		}
		
		public Vector<Subscription> SubscriptionVector() {
	        this.sql = "select insuranceID, customerID, contractID from subscription where subscriptionStatus = true";   
	        Vector<Subscription> Vecsubscription = new Vector<Subscription>();
	        
	          try {
	                 this.connect = getConnection();
	                 this.statement = this.connect.prepareStatement(sql);
	                 this.resultSet= this.statement.executeQuery();
	   
	                 while(this.resultSet.next()) {
	         	        Subscription subscription = new Subscription(); 
	                	subscription.setInsuranceID(this.resultSet.getInt("insuranceID"));
	                	subscription.setContractID(this.resultSet.getInt("contractID"));
	                	subscription.setCustomerID(this.resultSet.getInt("customerID"));
	                	Vecsubscription.add(subscription);         
	                 }            
	              }catch(SQLException e) {
	                 throw new RuntimeException("InsuranceDAO.SubscriptionVector :" + e.getMessage());
	              }finally {
	                 closeConnection(this.connect);
	              }
	          return Vecsubscription;          
	     }

		public Vector<InsuranceType> InsuranceTypeVector(int CustomerID) {
	        this.sql = "select insuranceType from insurance where insuranceID in"
	        		+ "(select insuranceID from subscription where subscriptionStatus = true and customerID = ?)";
	       
	        Vector<InsuranceType> InsuranceTypeVector = new Vector<InsuranceType>();
	        
	          try {
	                 this.connect = getConnection();
	                 this.statement = this.connect.prepareStatement(sql);
	                 statement.setInt(1, CustomerID);
	                 this.resultSet= this.statement.executeQuery();
	                 
	                 while(this.resultSet.next()) {
	                	InsuranceType InsuranceType = Insurance.InsuranceType.valueOf(this.resultSet.getString("insuranceType"));
	                	InsuranceTypeVector.add(InsuranceType);
	                 }            
	              }catch(SQLException e) {
	                 throw new RuntimeException("InsuranceDAO.SubscriptionVector :" + e.getMessage());
	              }finally {
	                 closeConnection(this.connect);
	              }
	          return InsuranceTypeVector;
	     }
}
