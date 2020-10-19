package com.chargemanag1.bankmanag1.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.chargemanag1.bankmanag1.LoginBankEmployee;
import com.chargemanag1.bankmanag1.entity.BankEmployeeEntity;
import com.chargemanag1.bankmanag1.repository.BankEmployeeRepository;

@Service
public class BankEmployeeServiceImpl implements BankEmployeeService {
	
	@Autowired
	private BankEmployeeRepository emp;

	public BankEmployeeEntity finduser(String username)
	{
		return emp.findByUserid(username);
	}

	public BankEmployeeEntity save(BankEmployeeEntity user) {
		return emp.save(user);
	}
	
	@Override
	public ResponseEntity<BankEmployeeEntity> loginEmployee(LoginBankEmployee login) {
		BankEmployeeEntity user = emp.findByUserid(login.getEmail());
		if(user == null) {
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED) ;
		}
		if(!user.getPassword().equals(login.getPassword())){
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED) ;
		}

		return new ResponseEntity<BankEmployeeEntity>(user,HttpStatus.FOUND) ;

	}

	public List<BankEmployeeEntity> findAll()
	{
		return emp.findAll();
	}

}