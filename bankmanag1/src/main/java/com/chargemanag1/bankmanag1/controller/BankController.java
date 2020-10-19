package com.chargemanag1.bankmanag1.controller;

import com.rules.rulesapi.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.chargemanag1.bankmanag1.JwtRequestFilter;
import com.chargemanag1.bankmanag1.JwtResponse;
import com.chargemanag1.bankmanag1.JwtTokenUtil;
import com.chargemanag1.bankmanag1.LoginBankEmployee;
import com.chargemanag1.bankmanag1.entity.BankEmployeeEntity;
import com.chargemanag1.bankmanag1.entity.Rules;
import com.chargemanag1.bankmanag1.service.BankEmployeeServiceImpl;
import com.chargemanag1.bankmanag1.service.RulesService;
import com.rules.Entities.ChargeManagment;
import com.rules.Entities.RulesOutput;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BankController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BankController.class);

	@Autowired
	private BankEmployeeServiceImpl empser;

	@Autowired
	private RulesService ruler;

	JwtRequestFilter filter= new JwtRequestFilter();

	@GetMapping("/getrules")
	public ResponseEntity<List<RulesOutput>> getRules()
	{
		try
		{
			return ResponseEntity.ok(Program.getRules());
		}
		catch(Exception e)
		{
			LOGGER.error("Some excpetion occurred while getting all rules. " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody(required=false) LoginBankEmployee login,HttpServletRequest req,
			HttpServletResponse res)throws Exception
	{
		ResponseEntity<BankEmployeeEntity> employee=empser.loginEmployee(login);
		BankEmployeeEntity user= employee.getBody();
		if(user==null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		else
		{
			JwtTokenUtil jwtToken= new JwtTokenUtil();
			String token= jwtToken.generateToken(user);
			LOGGER.info("Login successful");
			return ResponseEntity.ok(new JwtResponse(token,login.getEmail(),user.getRole()));
		}
	}


	@GetMapping("/approve")
	public ResponseEntity approveRule(@RequestParam String paramname, HttpServletRequest req)
	{
		try
		{
			String role = filter.doFilterInternal(req);
			if(role==null )
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if( "approver".equals(role)==false)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			long code=Long.valueOf(paramname);
			if(ruler.approveRule(code))
				return ResponseEntity.ok().build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/reject")
	public ResponseEntity rejectRule(@RequestParam String paramname,HttpServletRequest req)
	{
		try
		{
			String role = filter.doFilterInternal(req);
			if(role==null )
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if( "approver".equals(role)==false)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			long code=Long.valueOf(paramname);
			if(ruler.rejectRule(code))
				return ResponseEntity.ok().build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity deleteRule(@RequestParam String paramname,HttpServletRequest req) 
	{
		try
		{
			String role = filter.doFilterInternal(req);
			if(role==null )
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if( "admin".equals(role)==false)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			long code=Long.valueOf(paramname);
			if(ruler.deleteRule(code))
				return ResponseEntity.ok().build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/createrule")
	public ResponseEntity<Rules> addRule(@RequestBody(required=false) Rules rule,HttpServletRequest req)
	{
		try 
		{
			String role = filter.doFilterInternal(req);
			if(role==null )
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if("creator".equals(role)==false)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			if(ruler.addRule(rule))
				return ResponseEntity.ok().build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/updaterule")
	public ResponseEntity<Rules> updateRuleData(@RequestBody(required=false) Rules rule,HttpServletRequest req)
	{
		try 
		{
			String role = filter.doFilterInternal(req);
			if(role==null )
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if( "admin".equals(role)==false)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			if(ruler.updateRuleData(rule))
				return ResponseEntity.ok().build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/list")
	public ResponseEntity allRules(HttpServletRequest req, HttpServletResponse res) 
	{
		try 
		{
			String role=filter.doFilterInternal(req);
			if(role==null)
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			return ResponseEntity.ok(ruler.allRules(true));
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/accountfees")
	public ResponseEntity<ChargeManagment> getFees(@RequestBody ChargeManagment account)
	{
		try
		{
			Program p=new Program();
			account=p.getFees(account);
			if(account==null)
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			return ResponseEntity.ok(account);
		}
		catch(Exception ex)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@GetMapping("/pendinglist")
	public ResponseEntity pendingRules(HttpServletRequest req) 
	{
		try
		{
			String role=filter.doFilterInternal(req);
			if(role==null)
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if(!("creator".equals(role) || "approver".equals(role)))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			return ResponseEntity.ok(ruler.allRules(false));
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/update")
	public ResponseEntity<Rules> update(@RequestParam String paramname, HttpServletRequest req)
	{
		try
		{
			String role = filter.doFilterInternal(req);
			if(role==null )
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if( "admin".equals(role)==false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
			long code=Long.valueOf(paramname);
			Rules rule=ruler.updateCodeExists(code);
			if(rule==null)
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			return ResponseEntity.ok(rule); 
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}