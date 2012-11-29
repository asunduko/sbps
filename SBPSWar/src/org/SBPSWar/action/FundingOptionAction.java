package org.SBPSWar.action;

import java.util.Map;

import org.SBPSWar.dao.UserDAO;
import org.SBPSWar.dao.UserDAOImpl;
import org.SBPSWar.domain.FundingOption;
import org.SBPSWar.domain.MasterBill;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.util.SBPSFormAndBeanUtility;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FundingOptionAction  extends ActionSupport implements ModelDriven<FundingOption>, SessionAware{

	private FundingOption fo = new FundingOption();
	private Map session;
	private UserDAO userDAO = new UserDAOImpl();
	@Override
	public FundingOption getModel() {

		return fo;
	}

	public boolean validatePage (){
		
		boolean validate = true;
		
		if(!SBPSFormAndBeanUtility.validateString(fo.getFundingType())){

			addFieldError( "fundingType", "Invalid Funding Type .") ;
			validate =false;
			
		}
		
		if(fo.getAuthorized()==null || fo.getAuthorized()== false ){

			addFieldError( "authorized", "Must Authorize Funding Source.") ;
			validate =false;
			
		}else{
			
			fo.setHasAuthorized('Y');
			
		}
		

		if(!SBPSFormAndBeanUtility.validateString(fo.getBankAccountNumber())){

			addFieldError( "bankAccountNumber", "Invalid Bank Account Number .") ;
			validate =false;
			
		}else {
			try{
				fo.setBAccRouting(Integer.parseInt(fo.getBankAccountNumber()));
			}catch (Exception e){
				addFieldError( "bankAccountNumber", "Bank Account# must contain only numbers.") ;
			}

		}
		if(!SBPSFormAndBeanUtility.validateString(fo.getPersonalAccountNumber())){

			addFieldError( "personalAccountNumber", "Invalid Personal Account Number .") ;
			validate =false;
			
		}else {
			try{
				fo.setBAccRouting(Integer.parseInt(fo.getPersonalAccountNumber()));
			}catch (Exception e){
				addFieldError( "personalAccountNumber", "Personal Account# must contain only numbers.") ;
			}

		}

		return validate;
	}
	
	
	private UserProfile getSessionUser() {
		   return (UserProfile)session.get("user");
	}

	public String addOption(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
		}
		
		if(!this.validatePage()){
			return "input";
		}
		
		//pd.setUserProfile(up);//p.269
		//up.setPd(pd);
		fo.setFunder(up);
		
		up.getFos().add(fo);
		
		userDAO.saveUser(up);
		
		return "success";
	}
	
	
	
	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}
}
