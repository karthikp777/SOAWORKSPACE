package com.klef.payment;

public class PaymentRequest {
	
	private int paymentid;
	private String customername;
	private String cardnumber;
	private double amount;
	private String paymentmethod;
	private  String currency;
	private  String description;
	public PaymentRequest(int paymentid, String customername, String cardnumber, double amount, String paymentmethod,
			String currency, String description) {
		super();
		this.paymentid = paymentid;
		this.customername = customername;
		this.cardnumber = cardnumber;
		this.amount = amount;
		this.paymentmethod = paymentmethod;
		this.currency = currency;
		this.description = description;
	}
	public int getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentmethod() {
		return paymentmethod;
	}
	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
