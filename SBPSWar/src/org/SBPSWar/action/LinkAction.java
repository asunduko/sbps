package org.SBPSWar.action;

import com.opensymphony.xwork2.ActionSupport;

public class LinkAction extends ActionSupport {

	private static final long serialVersionUID = -2613425890762568273L;

	public String login()
	{
		return "login";		
	}
	
	public String inviteUser()
	{
		return "inviteUser";		
	}
	
	public String addUser()
	{
		return "addUser";		
	}
	
	public String welcome()
	{
		return "welcome";		
	}
	
	public String friends()
	{
		return "friends";		
	}
	
	public String myPaymentsList()
	{
		return "myPaymentsList";		
	}
	
	public String masterList()
	{
		return "masterList";		
	}
}
