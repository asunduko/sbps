package org.SBPSWar.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogOutAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -2613425890762568273L;
	private Map session;
	
	public void setSession(Map session) {
		this.session = session;
	}
	public String logout(){
		//alternative Map session = ActionContext.getContext().getSession();
		session.remove("login");
		session.remove("user");
		session.remove("showall");
		return SUCCESS;
	}

}
