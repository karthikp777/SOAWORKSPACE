package com.klef;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest 
{
	private Student s;
	
	@BeforeAll
	static void beforeAll()
	{
		System.out.println("***** Testing Started *****");
	}
	
	@BeforeEach
	public void setData()
	{
		System.out.println("*** Before Each Test Case ***");
		s = new Student(1, "klu", "female", 50);
		
//		s = null;
		
	}

	@Test
	public void testcase1()
	{
		assertNotNull(s);
	}
	 
	@Test
	public void testcase2()
	{
		assertNull(s);
	}
	
	@Test
	public void testcase3()
	{
		assertEquals(1, s.getId());
	}
	
	@Test
	public void testcase4()
	{
		assertNotEquals("klef", s.getName());
	}
	
	@Test
	public void testcase5()
	{
		assertNotEquals(81, s.getMarks());
	}
	
	@Test
	public void testcase6()
	{
		Student s1 = new Student(2, "ram", "male", 30);
		
		assertTrue(s1.isPassed());
	}
	
	@Test
	public void testcase7()
	{
		assertFalse(s.isPassed());
	}
	
	@Test
	public void testcase8()
	{
		assertThrows(IllegalArgumentException.class, 
				() -> s.setMarks(110) );
	}
	
	
	
	
	
}
