package com.rules.Entities;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChargeManagment implements Serializable {
	private static final Logger LOGGER = LoggerFactory.getLogger(ChargeManagment.class);
	private static final long serialVersionUID = 1L;
	public String bank_type;
	public int operation_count;
	public String period;
	public int acc_no;
	public double amount;
	public double tax;
	public String acc_name;
	public String operation_type;

	public ChargeManagment(){
	}
	public String toString()
	{
		return "bank_type :"+bank_type+",transfer_count: "+operation_count+
				",period: "+period+",amount: "+amount
				+",opera_type: "+operation_type;
	}
	public void setTaxValue(double tax, String symbol) {
		if("%".equals(symbol))
		{
			LOGGER.info("type: %");
			double charge= (tax*this.amount)/100;
			this.tax+=charge;
		}
		else if("RS".equalsIgnoreCase(symbol))
		{
			LOGGER.info("type: Rs");
			this.tax+=tax;
		}
		else
		{
			LOGGER.info("other type");
			this.tax = tax;
		}
	}
	public ChargeManagment(String bank_type, int operation_count, String period,  int acc_no,
			double amount, double tax, String acc_name, String operation_type) {
		super();
		this.bank_type = bank_type;
		this.operation_count = operation_count;
		this.period = period;
		this.acc_no = acc_no;
		this.amount = amount;
		this.tax = tax;
		this.acc_name = acc_name;
		this.operation_type = operation_type;
	}
	public ChargeManagment(String bank_type,String operation_type,String period,int transfer_count){
		this.bank_type=bank_type;
		this.operation_type=operation_type;
		this.period=period;
		this.operation_count=transfer_count;
	}


	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
	public int getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(int acc_no) {
		this.acc_no = acc_no;
	}

	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getOperation_type() {
		return operation_type;
	}
	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}
	public int getTransfer_count() {
		return operation_count;
	}
	public void setTransfer_count(int transfer_count) {
		this.operation_count = transfer_count;
	}
}