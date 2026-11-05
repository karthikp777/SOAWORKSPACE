package com.klef;

public class Student 
{
	
	private int id;
	private String name;
	private String gender;
	private int marks;
	
	public Student(int id, String name, String gender, int marks) 
	{
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.marks = marks;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public int getMarks() {
		return marks;
	}
	
	public void setMarks(int marks)
	{
		if(marks<0 || marks>100)
		{
			throw new IllegalArgumentException("Marks between 0 and 100 only");
		}
		
		this.marks = marks;
	}
	
	public boolean isPassed()
	{
		return marks>=40;
	}
	
	
}
