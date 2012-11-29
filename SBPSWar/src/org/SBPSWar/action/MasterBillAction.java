package org.SBPSWar.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.SBPSWar.dao.MasterBillDAOImpl;
import org.SBPSWar.dao.UserDAO;
import org.SBPSWar.dao.UserDAOImpl;
import org.SBPSWar.domain.MasterBill;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.util.SBPSFormAndBeanUtility;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MasterBillAction extends ActionSupport implements ModelDriven<MasterBill>, SessionAware {

	private static final long serialVersionUID = -2613425890762568273L;
	
	private MasterBill mb = new MasterBill();
	private MasterBillDAOImpl mbDAO = new MasterBillDAOImpl();
	private Set<MasterBill> mbs = new HashSet <MasterBill>();
	private UserDAO userDAO = new UserDAOImpl();

	private Map session;
	@Override
	public MasterBill getModel() {
		return mb;
	}
	
	public boolean validatePage (){
		
		boolean validate = true;
		boolean issue=  true;
		boolean due= true;
		
		if(!SBPSFormAndBeanUtility.validateString(mb.getBillName())){
			//candidate for ajax, verify that the name is not already taken
			addFieldError( "billName", "Bill Name Must not be blank.") ;
			validate =false;
			
		}
		if(!SBPSFormAndBeanUtility.validateNumeric(mb.getAmount())){
			addFieldError( "amount", "Invalid amount.") ;
			validate =false;
		}
			
		if(!SBPSFormAndBeanUtility.validateDate(mb.getIssueDate())){
			
			addFieldError( "issueDate", "Issue Date must not be blank.") ;
			validate = false;	
			issue= false;
		}
		
		if(!SBPSFormAndBeanUtility.validateDate(mb.getDueDate())){
			
			addFieldError( "dueDate", "Password must not be blank.") ;
			validate = false;	
			due=false;
		}
		
		if(issue && due){
			if(!SBPSFormAndBeanUtility.validateDates(mb.getIssueDate(), mb.getDueDate())){
				
				addActionError( "Issue date must occur before the due date.") ;
				validate = false;	
			}
		}
		return validate;
	}
	
	
	public Set<MasterBill> getMbs() {
		return mbs;
	}
	public void setMbs(Set<MasterBill> mbs) {
		this.mbs = mbs;
	}

	public String add(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
		}
		
		if(!this.validatePage()){
			
			return "input";
		}
		mb.setBillAdmin(up);
		
		mbDAO.saveMasterBill(mb);

		
		/*UserProfile reloadedUp = (UserProfile)sessionHib.get(UserProfile.class, up.getUserProfileId());
		
		Set<MasterBill> mbs = reloadedUp.getMbl();
		for(MasterBill mb: mbs){
			
			mb.getAmount();
		}*/
		
		this.showUserMBS();
		
		return "master";
	}
	
	
	public String showUserMBS(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
		}
		
		UserProfile reloadedUp = userDAO.loadUserById(up.getUserProfileId());
			
		Set<MasterBill> mbs = reloadedUp.getMbl();

		this.mbs  = mbs;
		
		return "success";
	}
	
	private UserProfile getSessionUser() {
		   return (UserProfile)session.get("user");
	}


	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}


}