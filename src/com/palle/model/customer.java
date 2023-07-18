package com.palle.model;

public class customer
{
	private int id;
	private String name;
	private String email;
	private long mobile;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public long getMobile()
	{
		return mobile;
	}
	public void setMobile(long mobile) 
	{
		this.mobile = mobile;
	}
	//(U)update operation and(R) Dispaly Operation
	public customer(int id, String name, String email, long mobile)
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
	//(c)Insert operation
	public customer(String name, String email, long mobile) 
	{
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
	//delete operation -->we don't need customer
	
	
	
	
}
