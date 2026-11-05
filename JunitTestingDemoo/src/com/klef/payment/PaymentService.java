package com.klef.payment;

public class PaymentService 
{
	public boolean validatePaymentDetails(PaymentRequest request) {
		
		
		if(request == null)
		{
			return false;
		}
		
		if(request.getCustomername() == null ||
		   request.getCustomername().isBlank()) {
			return false;
		}
		
		if(request.getCardnumber() == null ||
		   request.getCardnumber().isBlank()) {
			return false;
		}
		
		if(request.getAmount() <= 0) {
			return false;
		}
		
		if(request.getPaymentmethod() == null ||
				request.getPaymentmethod().isBlank()) {
			return false;
		}
		
		if(request.getCurrency() == null ||
			request.getCurrency().isBlank()) {
			return false;
		}
		
			return true;
		
	}
	
	public String processPayment(PaymentRequest request) {
		
		if(!validatePaymentDetails(request)) {
			return "Payment Failed";
		}
		
		return "Payment Successfull";
	}

}
