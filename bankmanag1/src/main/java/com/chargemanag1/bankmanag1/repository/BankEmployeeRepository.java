package com.chargemanag1.bankmanag1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chargemanag1.bankmanag1.entity.BankEmployeeEntity;

@Repository
public interface BankEmployeeRepository  extends JpaRepository<BankEmployeeEntity,String>{
	 // using for all database operations related to employee user
	BankEmployeeEntity findByUserid(String userid);
}
