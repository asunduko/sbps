package org.SBPSWar.action;

import java.util.Map;

import org.SBPSWar.dao.UserDAO;
import org.SBPSWar.dao.UserDAOImpl;
import org.SBPSWar.domain.PersonalData;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.util.SBPSFormAndBeanUtility;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FullProfileAction extends ActionSupport implements ModelDriven<PersonalData>, SessionAware {

	private static final long serialVersionUID = -6659925652584240539L;
	
	private PersonalData pd = new PersonalData();
	private UserDAO userDAO = new UserDAOImpl();
	private Map session;
	
	@Override
	public PersonalData getModel() {
		return pd ;
	}
	
	public boolean validatePage (){
		
		boolean validate = true;
		
		if(!SBPSFormAndBeanUtility.validateString(pd.getFirstName())){

			addFieldError( "firstName", "Invalid First Name .") ;
			validate =false;
			
		}
		
		if(!SBPSFormAndBeanUtility.validateString(pd.getLastName())){

			addFieldError( "lastName", "Invalid Last Name .") ;
			validate =false;
			
		}
		if(!SBPSFormAndBeanUtility.validateString(pd.getSsn())){

			addFieldError( "ssn", "Invalid SSN.") ;
			validate =false;
			
		}
	
		if(!SBPSFormAndBeanUtility.validateDate(pd.getDob())){
			
			addFieldError( "dob", "Invalid DOB.") ;
			validate = false;	
		}
		


		return validate;
	}
	
	public String fullProfile(){
	
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
		}
		
		if(!this.validatePage()){
			return "input";
		}
		
		pd.setUserProfile(up);//p.269
		up.setPd(pd);
		
		userDAO.saveUser(up);
		
		session.put("showall", true);
		
		return "master";
	}
	
	public String master(){
		
		return "master";
	}

	private UserProfile getSessionUser() {
		UserProfile currSessionUser = (UserProfile)session.get("user");
		
		return currSessionUser;
	}


	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}
	
	
}
