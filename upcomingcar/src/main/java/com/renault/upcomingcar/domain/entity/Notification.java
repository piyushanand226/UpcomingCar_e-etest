package com.renault.upcomingcar.domain.entity;



import lombok.Data;

@Data
public class Notification {
	
	String name;
	
	public Notification() {}
	
	public Notification(String name)
	{
		this.name=name;
	}
	
	
}
