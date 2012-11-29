package org.SBPSWar.interceptor;

import java.util.Map;
import java.util.Set;

import org.SBPSWar.domain.MasterBill;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.util.SBPSFormAndBeanUtility;
import org.apache.struts2.interceptor.ParameterAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class FullProfileInterceptor implements Interceptor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		
		public void destroy() {
		
		}
		public void init() {
		
		}
		public String intercept( ActionInvocation actionInvocation )throws Exception{
			Map session = actionInvocation.getInvocationContext().getSession();
			Map parameters = ActionContext.getContext().getParameters();
			String[] params = ((String[]) parameters.get("fullProfile"));
			String profileParam = "";
			if(params!=null && params.length > 0){
				profileParam = params[0];
			}
			UserProfile user = (UserProfile) session.get("user" );
			
			if (user.getPd() == null && ! SBPSFormAndBeanUtility.validateString(profileParam)) {
				ValidationAware action = (ValidationAware) actionInvocation.getAction();
				action.addActionError("Must Complete Personal Data First");
				return "fullProfile";
			}
			else {
				Action action = ( Action ) actionInvocation.getAction();
				return actionInvocation.invoke();
			}
		}
		

}


