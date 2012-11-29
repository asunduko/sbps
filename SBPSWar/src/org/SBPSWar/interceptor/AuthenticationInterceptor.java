package org.SBPSWar.interceptor;

import java.util.Map;
import java.util.Set;

import org.SBPSWar.domain.MasterBill;
import org.SBPSWar.domain.UserProfile;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptor implements Interceptor {


	private static final long serialVersionUID = 1L;
	
		public void destroy() {
		
		}
		public void init() {
		
		}
		public String intercept( ActionInvocation actionInvocation )throws Exception{
			Map session = actionInvocation.getInvocationContext().getSession();
			UserProfile user = (UserProfile) session.get("user" );
			
			if (user == null) {
				ValidationAware action = (ValidationAware) actionInvocation.getAction();
				action.addActionError("Must Login First");
				return Action.LOGIN;
			}
			else {
				Action action = ( Action ) actionInvocation.getAction();
				return actionInvocation.invoke();
			}
		}
		

}
