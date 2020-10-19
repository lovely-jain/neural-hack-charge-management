package com.chargemanag1.bankmanag1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 *   database for storing rules information
 */

@Entity
@Table(name="bank_rules")

public class Rules {
	
	@Id  @GeneratedValue 
	 long code;

	
	@Column 
	private String category;
	
	@Column
	private String operationType;
	 	
	@Column
	private String conditionType;
	
	@Column
	private int limitFrom=0;
	
	@Column
	private int limitTo=Integer.MAX_VALUE;
	
	@Column
	private String feesType;
	
	@Column
	private double feesValue;

	@Column
	private boolean status=false;
	
	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String condition) {
		this.conditionType = condition;
	}

	public int getLimitFrom() {
		return limitFrom;
	}

	public void setLimitFrom(int limitFrom) {
		this.limitFrom = limitFrom;
	}

	public int getLimitTo() {
		return limitTo;
	}

	public void setLimitTo(int limitTo) {
		this.limitTo = limitTo;
	}

	public String getFeesType() {
		return feesType;
	}

	public void setFeesType(String feesType) {
		this.feesType = feesType;
	}

	public double getFeesValue() {
		return feesValue;
	}

	public void setFeesValue(double feesValue) {
		this.feesValue = feesValue;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Rules(String category, String operationType, String condition, int limitFrom, int limitTo, String feesType,
			double feesValue, boolean status) {
		super();
		this.category = category;
		this.operationType = operationType;
		this.conditionType = condition;
		this.limitFrom = limitFrom;
		this.limitTo = limitTo;
		this.feesType = feesType;
		this.feesValue = feesValue;
		this.status = status;
	}

	public Rules() {}
}
