package org.SBPSWar.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.SBPSWar.dao.AssignBillDAO;
import org.SBPSWar.dao.UserDAO;
import org.SBPSWar.dao.UserDAOImpl;
import org.SBPSWar.domain.AssignedBill;
import org.SBPSWar.domain.MasterBill;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.util.SBPSFormAndBeanUtility;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class PaymentAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = -6659925652584240539L;
	
	private Integer assignedBillId;
	private UserDAO userDAO = new UserDAOImpl();
	private AssignBillDAO abDAO = new AssignBillDAO();
	private AssignedBill ab = new AssignedBill();
	private MasterBill owningMasterBill = new MasterBill();
	private List<AssignedBill> slaveBills = new ArrayList<AssignedBill>();
	private List<AssignedBill> myBills = new ArrayList<AssignedBill>();
	private String approve;

	public String getApprove() {
		return approve;
	}


	public void setApprove(String approve) {
		this.approve = approve;
	}


	public Integer getAssignedBillId() {
		return assignedBillId;
	}


	public void setAssignedBillId(Integer assignedBillId) {
		this.assignedBillId = assignedBillId;
	}


	public AssignedBill getAb() {
		return ab;
	}


	public void setAb(AssignedBill ab) {
		this.ab = ab;
	}
	
	public List<AssignedBill> getMyBills() {
		return myBills;
	}


	public void setMyBills(List<AssignedBill> myBills) {
		this.myBills = myBills;
	}


	private Map session;

	
	public String findBillsAssignedToCurrentUser(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
		}
		
		this.myBills  = abDAO.findMyBills(this.getSessionUser());
	
		return "list";
	}
	/*
	 * In order to review editable bills
	 * the user need to chose a particular assigned bill and
	 * 1. see the master  bill(readonly)
	 * 2. see other assigned bills which are part of the same bill (readonly)
	 * 3. see own bill editable as far the approve/reject the amount.
	 */
	
	public String reviewMyBills(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
		}
		
		this.ab  = abDAO.findAssigneBillById(this.getAssignedBillId());
	    this.owningMasterBill= ab.getMasterBill();
	    this.slaveBills = abDAO.findAssignedBills(owningMasterBill);
		return "abDetail";
		
	}
	public String test(){
		return "restart";
	}
	
	public String evaluateAssignment(){
	
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
		}
		
		if(!this.validateApprove(this.getApprove())){
			
			return "success";
		}
		this.ab  = abDAO.findAssigneBillById(this.getAssignedBillId());
		ab.setIsPaymentAuthorized(this.getApprove().equals("Y")?'Y':'N');
		if(this.getApprove().equals("Y")){
			ab.setStatus(2);
			BaseEmailAction bea = new BaseEmailAction();
			
			bea.generalContactMessage(up, ab.getMasterBill().getBillAdmin(),
					" Assigned Bill Approved " , " an assigned amount which has been approved <br> for the Master Biil: "
					+ ab.getMasterBill().getBillName() );
		}else if (this.getApprove().equals("Disp.")){
			ab.setStatus(4);
		}
		
		abDAO.saveAssignedBill(ab);
		
		//TODO e-mail master of the bill about an assigned bill paid
		//by current user
		
		return "success";
	}

	
	private boolean validateApprove(String string) {
		boolean validate = true;
		
		if(!SBPSFormAndBeanUtility.validateString(string)){

			addActionError( "Invalid Approve Input") ;
			validate =false;
			
		}
		return validate;
	}

	public List<AssignedBill> getSlaveBills() {
		return slaveBills;
	}


	public void setSlaveBills(List<AssignedBill> slaveBills) {
		this.slaveBills = slaveBills;
	}


	private UserProfile getSessionUser() {
		   return (UserProfile)session.get("user");
	}


	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}

	
	
}

