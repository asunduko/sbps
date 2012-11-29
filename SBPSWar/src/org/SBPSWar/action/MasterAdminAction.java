package org.SBPSWar.action;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.SBPSWar.dao.AssignBillDAO;
import org.SBPSWar.dao.MasterBillDAOImpl;
import org.SBPSWar.dao.UserDAO;
import org.SBPSWar.dao.UserDAOImpl;
import org.SBPSWar.domain.AssignedBill;
import org.SBPSWar.domain.MasterBill;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.util.SBPSFormAndBeanUtility;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MasterAdminAction extends ActionSupport implements ModelDriven<MasterBill>, SessionAware {
	
	private static final long serialVersionUID = -2613425890762568273L;
	
	private MasterBill mb = new MasterBill();
	private Set<MasterBill> mbs = new HashSet <MasterBill>();
	private String masterBillId;
	private UserAssistService uaService = new UserAssistService();
	private UserDAO userDAO = new UserDAOImpl();
	private MasterBillDAOImpl mbDAO = new MasterBillDAOImpl();
	private Map session;
	private AssignBillDAO abDAO = new AssignBillDAO();
	

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
		}else{
			
			Double totalAssignedAmount = 0.0;
			
			List<AssignedBill> abs = abDAO.findAssignedBills(mb);
			
			if(abs.size()>0){
				for(AssignedBill ab :abs){
					
					totalAssignedAmount = totalAssignedAmount + ab.getAmount();
				}
				
				if(totalAssignedAmount > mb.getAmount()){
					addFieldError( "amount", "Master Bill Amount can't be lower than assigned bill(s) amount.") ;
				}
			}
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

	

	
	public MasterBill getMb() {
		return mb;
	}

	public void setMb(MasterBill mb) {
		this.mb = mb;
	}

	public String getMasterBillId() {
		return masterBillId;
	}

	public void setMasterBillId(String masterBillId) {
		this.masterBillId = masterBillId;
	}
	
	public Set<MasterBill> getMbs() {
		return mbs;
	}
	public void setMbs(Set<MasterBill> mbs) {
		this.mbs = mbs;
	}
	
	public String update(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
		}
		
		if(!this.validatePage()){
			
			return "input";
		}
		MasterBill beforeUpdateBill = uaService.loadMasterData(this.getMasterBillId());
		
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(mb);
		if (descriptors != null) {
		  for (PropertyDescriptor descriptor : descriptors) {
		    try {
		      String propertyName = descriptor.getName();
		      Object val = PropertyUtils.getProperty(mb, propertyName);
		      if (val != null) {
		        PropertyUtils.setProperty(beforeUpdateBill, propertyName, val);
		      }
		    } catch (Exception ignore) {
		      // not interested in what we can't read or write
		    }
		  }
		}

		/*try {
			BeanUtils.copyProperties(beforeUpdateBill, mb);
			
		} catch (IllegalAccessException e) {
			System.out.println("Could not copy the props");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("Could not copy the props");
			e.printStackTrace();
		}*/
		
		
		mbDAO.saveMasterBill(beforeUpdateBill);


		
		return "admin";
	}
	
	
	public String display() throws Exception {
		
		
		this.mb = uaService.loadMasterData(this.getMasterBillId());
		
		//this.addActionMessage("select record :" + mb.getMasterBillId());
		return Action.SUCCESS;
	}

	
	
	private UserProfile getSessionUser() {
		   return (UserProfile)session.get("user");
	}


	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}

	@Override
	public MasterBill getModel() {
		return mb;
	}





}
