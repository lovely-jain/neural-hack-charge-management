package com.chargemanag1.bankmanag1.service;

import org.springframework.http.ResponseEntity;
import com.chargemanag1.bankmanag1.LoginBankEmployee;
import com.chargemanag1.bankmanag1.entity.BankEmployeeEntity;

public interface BankEmployeeService {
	public ResponseEntity<BankEmployeeEntity> loginEmployee(LoginBankEmployee login);
}
