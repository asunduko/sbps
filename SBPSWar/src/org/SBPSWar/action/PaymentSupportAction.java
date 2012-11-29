package org.SBPSWar.action;

import com.opensymphony.xwork2.ActionSupport;

public class PaymentSupportAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String GREETING = "Hello ";
	public String execute() {
		//setCustomGreeting( GREETING + getName() );
		return "SUCCESS";
	}
	private String name;
	private String customGreeting;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomGreeting()
	{
		return customGreeting;
	}
	public void setCustomGreeting( String customGreeting ){
		this.customGreeting = customGreeting;
	}
}