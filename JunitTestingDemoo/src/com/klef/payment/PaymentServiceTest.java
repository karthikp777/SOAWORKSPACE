package com.klef.payment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

	
	private PaymentService paymentService;
	
	@BeforeEach
	void setup() {
		System.out.println("***Before Each TestCase");
		paymentService = new PaymentService();
		}
	
	
	//1.Valid Payment
	@Test
	void testcase1() {
		PaymentRequest request = new PaymentRequest(101, "John","123456789654", 5000, "CARD", "INR", "Online Course Payment");
				
		String result =paymentService.processPayment(request);
		
		assertEquals("Payment Successfull", result);
	}
	
	
	//Negative amount 
	@Test
	void testcase2() {
		PaymentRequest request = new PaymentRequest(101, "John","123456789654", -1, "CARD", "INR", "Online Course Payment");
				
		String result =paymentService.processPayment(request);
		
		assertEquals("Payment Failed", result);
	}
	
	//Zero amount
	@Test
	void testcase3() {
		PaymentRequest request = new PaymentRequest(101, "John","123456789654", 0, "CARD", "INR", "Online Course Payment");
				
		String result =paymentService.processPayment(request);
		
		assertEquals("Payment Failed", result);
	}
	
	//4. customer name
	@Test
	void testcase4() {
		PaymentRequest request = new PaymentRequest(101, "","123456789654", 5000, "CARD", "INR", "Online Course Payment");
				
		String result =paymentService.processPayment(request);
		
		assertEquals("Payment Failed", result);
	}
	
	//5. Missing card number
	@Test
	void testcase5() {
		PaymentRequest request = new PaymentRequest(105, "John","", 5000, "CARD", "INR", "Online Course Payment");
				
		String result =paymentService.processPayment(request);
		
		assertEquals("Payment Failed", result);
	}
	//6. Null Payment request
	@Test
	void testcase6() {
				
		String result =paymentService.processPayment(null);
		
		assertEquals("Payment Failed", result);
	}
	//7. Blank Customer name
	@Test
	void testcase7() {
		PaymentRequest request = new PaymentRequest(101, "  ","123456789654", 5000, "CARD", "INR", "Online Course Payment");
				
		String result =paymentService.processPayment(request);
		
		assertEquals("Payment Failed", result);
	}
	//8. null customer name
	@Test
	void testcase8() {
		PaymentRequest request = new PaymentRequest(101,null,"123456789654", 5000, "CARD", "INR", "Online Course Payment");
				
		String result =paymentService.processPayment(request);
		
		assertEquals("Payment Failed", result);
	}
	
	//9. Null Card Number
	@Test
	void testcase9() {
		PaymentRequest request = new PaymentRequest(101, "John",null, 5000, "CARD", "INR", "Online Course Payment");
				
		String result =paymentService.processPayment(request);
		
		assertEquals("Payment Failed", result);
	}
	
	//10. Missing Payment Method
	@Test
	void testcase10() {
		PaymentRequest request = new PaymentRequest(101, "John","123456789654", 5000, "", "INR", "Online Course Payment");
				
		String result =paymentService.processPayment(request);
		
		assertEquals("Payment Failed", result);
	}
	
	//11. Missing Currency
	@Test
	void testcase11() {
		PaymentRequest request = new PaymentRequest(101, "John","123456789654", 5000, "CARD", "", "Online Course Payment");
				
		String result =paymentService.processPayment(request);
		
		assertEquals("Payment Failed", result);
	}
	
	//12.
	@Test
	void testcase12() {
		PaymentRequest request = new PaymentRequest(101, "John","123456789654", 5000, "CARD", "INR", "Online Course Payment");
				
		boolean result =paymentService.validatePaymentDetails(request);
		
	   assertTrue(result);
	}
}
