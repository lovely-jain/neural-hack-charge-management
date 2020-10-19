package com.rules.rulesapi;
public class AlertDecision {
    private Boolean doAlert;
    private Double res;
    public AlertDecision() {
        this.doAlert = false;
    }

    public Boolean getDoAlert() {
        return doAlert;
    }

    public void setDoAlert(Boolean doAlert) {
        this.doAlert = doAlert;
    }

	public Double getRes() {
		return res;
	}

	public void setRes(Double res) {
		this.res = res;
	}

	
}