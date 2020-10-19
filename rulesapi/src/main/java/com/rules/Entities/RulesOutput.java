package com.rules.Entities;

import java.io.Serializable;
public class RulesOutput implements Serializable 
{
	private static final long serialVersionUID = 1L;
	public String category;
	public String operation;
	public String condition;
	public String fees;
	
	public String toString()
	{
		return category+" "+operation+" "+condition+" "+fees;
	}
	public void setCondition(int lower,int upper, String condition)
	{
		this.condition=condition;
		if(lower!=0)
			this.condition+=": more than "+lower;
		if(upper!=Integer.MAX_VALUE)
			this.condition+=", less than "+upper;
	}
	public void setFees(double value,String type)
	{
		this.fees=value+""+type;
	}
	public RulesOutput(String cat,String ope)
	{
		this.category=cat;
		this.operation=ope;
	}
}
