package com.renault.upcomingcar.domain.entity;

import lombok.Data;

@Data
public class FromUser {
	
	String content;
	
	public FromUser() {}
	
	public FromUser(String content)
	{
		this.content=content;
		
	}

}
