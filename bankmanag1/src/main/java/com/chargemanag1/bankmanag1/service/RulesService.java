package com.chargemanag1.bankmanag1.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chargemanag1.bankmanag1.entity.Rules;
import com.chargemanag1.bankmanag1.repository.RuleRepository;

@Service
public class RulesService implements RuleServiceInterface {

	private static final Logger LOGGER = LoggerFactory.getLogger(RulesService.class);

	@Autowired
	RuleRepository rulerepo;

	public boolean addRule(Rules rule)
	{
		try
		{
			rulerepo.save(rule);
			LOGGER.info("Successfully added rule");
			return true;
		}
		catch(Exception ex)
		{
			LOGGER.error("Error occured while adding rule. " + ex.getMessage());
			return false;
		}
	}

	public Rules updateCodeExists(long code)
	{
		try
		{
			ArrayList<Long> list=new ArrayList<Long>();
			list.add(code);
			LOGGER.info("Rule found for code " + code);
			return rulerepo.findByCodeAndStatus(code,true).get(0);
		}
		catch(Exception e)
		{
			LOGGER.error("Some error occurred while updating or looking rule. " + e.getMessage());
			return null;
		}
	}

	public boolean deleteRule(long code)
	{
		try {
			List<Rules> rule=rulerepo.findByCodeAndStatus(code, true);
			if(rule.size()!=0)
			{
				rulerepo.deleteById(code);
				LOGGER.info("Successfully deleted rule for code " + code);
				return true;
			}
			return false;
		}
		catch(Exception ex)
		{
			LOGGER.error("Some error occurred while deleting rule." + ex.getMessage());
			return false;
		}
	}

	public List<Rules> allRules(boolean status)
	{
		return rulerepo.findAllByStatus(status);
	}

	public boolean rejectRule(long code)
	{
		try 
		{
			rulerepo.deleteById(code);
			LOGGER.info("Successfully rejected rule for code " + code);
			return true;
		}
		catch(Exception ex)
		{
			LOGGER.error("Some error occurred while rejecting rule." + ex.getMessage());
			return false;
		}
	}
	public boolean updateRuleData(Rules rule)
	{
		try
		{
			long code=rule.getCode();
			Rules ruleobj=rulerepo.getOne(code);
			ruleobj.setCategory(rule.getCategory());
			ruleobj.setConditionType(rule.getConditionType());
			ruleobj.setLimitFrom(rule.getLimitFrom());
			ruleobj.setLimitTo(rule.getLimitTo());
			ruleobj.setFeesType(rule.getFeesType());
			ruleobj.setFeesValue(rule.getFeesValue());
			ruleobj.setOperationType(rule.getOperationType());
			rulerepo.save(ruleobj);
			LOGGER.info("Successfully updated rule data for code "+ code);
			return true;
		}
		catch(Exception ex)
		{
			LOGGER.error("Some error occurred while updating  rule data." + ex.getMessage());
			return false;
		}
	}

	public boolean approveRule(long code)
	{
		try
		{
			Rules ruleobj=rulerepo.getOne(code);
			ruleobj.setStatus(true);
			rulerepo.save(ruleobj);
			LOGGER.info("Successfully approved rule for code " + code);
			return true;
		}
		catch(Exception ex)
		{
			LOGGER.error("Some error occurred while approving rule." + ex.getMessage());
			return false;
		}
	}
}
