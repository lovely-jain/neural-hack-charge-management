package com.chargemanag1.bankmanag1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * this is sample database for employee details
 */
@Entity(name="bank_employee")
@Table(name="employees")
public class BankEmployeeEntity {
  @Id
  private String userid;
  
  @Column
  private String password;
  
  @Column
  private String role;
  
  @Column
  private String name;

  public BankEmployeeEntity(String userid, String password, String role, String name) {
	super();
	this.userid = userid;
	this.password = password;
	this.role = role;
	this.name = name;
}
 public BankEmployeeEntity()
  {}

		public String getUserid() {
			return userid;
		}
		
		public void setUserid(String userid) {
			this.userid = userid;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getRole() {
			return role;
		}
		
		public void setRole(String role) {
			this.role = role;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		    
}
