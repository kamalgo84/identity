package com.identity.services.core;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("services")
public class MainServices extends Application {

	public MainServices() {
		// TODO Auto-generated constructor stub
	}
	
	public Set getClasses()
	{
		return super.getClasses();
	}

}
