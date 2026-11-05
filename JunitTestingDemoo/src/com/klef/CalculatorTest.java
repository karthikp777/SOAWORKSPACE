package com.klef;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculatorTest 
{
	
	Calculator c = new Calculator();
	
	@Test
	public void testaddition()
	{
		int result = c.addition(-5, -7);
		
		assertEquals(-12, result);
	}
	
	@Test
	public void testsubtraction()
	{
		int result = c.subtraction(20, 30);
		
		assertEquals(-10, result);
	}
	
	@Test
	public void testmultiplication()
	{
		int result = c.multiplication(5, 5);
		
		assertEquals(25, result);
	}
	
	@Test
	public void testdivision()
	{
		int result = c.division(4, 2);
		
		assertEquals(2, result);
	}
	
	@Test
	public void testdivisionbyzero1()
	{
		assertThrows(IllegalArgumentException.class,
				() -> c.division(2, 0));
	}
	
	@Test
	public void testdivisionbyzero2()
	{
		assertThrows(IllegalArgumentException.class,
				() -> c.division(2, 1));
	}
	
}
