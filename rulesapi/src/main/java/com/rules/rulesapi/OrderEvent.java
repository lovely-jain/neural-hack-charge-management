package com.rules.rulesapi;

public class OrderEvent implements Event {

    private Integer transfer_count;
    private String operation_type;
    private String bankATM;
    private Float tax;
    private Integer amount;

	public String getBankATM() {
		return bankATM;
	}

	public void setBankATM(String bankATM) {
		this.bankATM = bankATM;
	}

	public Integer getTransfer_count() {
		return transfer_count;
	}

	public void setTransfer_count(Integer transfer_count) {
		this.transfer_count = transfer_count;
	}

	public String getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}

	public Float getTax() {
		return tax;
	}

	public void setTax(Float tax) {
		this.tax = tax;
	}

	public  Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

  
}
