package com.klef;

public class StringUtil
{
	public String stringreverse(String value)
	{
		return new StringBuilder(value).reverse().toString();
	}
	
	public String stringlowercase(String value)
	{
		return value.toLowerCase();
	}
	
	public String stringuppercase(String value)
	{
		return value.toUpperCase();
	}
	
	public int stringlength(String value)
	{
		return value.length();
	}
	
	public boolean isEmpty(String value)
	{
		return value == null || value.isEmpty();
	}
	
	
	
	
	
	
	
}
