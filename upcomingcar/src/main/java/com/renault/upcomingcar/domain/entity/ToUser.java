package com.renault.upcomingcar.domain.entity;



import lombok.Data;

@Data
public class ToUser {
	
	String content;
	
	public ToUser() {}
	
	public ToUser(String content)
	{
		this.content=content;
	}
	
	

}
