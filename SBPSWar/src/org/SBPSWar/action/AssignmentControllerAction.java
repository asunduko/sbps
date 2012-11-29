package org.SBPSWar.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.SBPSWar.dao.AssignBillDAO;
import org.SBPSWar.dao.UserDAO;
import org.SBPSWar.dao.UserDAOImpl;
import org.SBPSWar.domain.AssignedBill;
import org.SBPSWar.domain.MasterBill;
import org.SBPSWar.domain.PersonalData;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.util.SBPSFormAndBeanUtility;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AssignmentControllerAction extends ActionSupport implements ModelDriven<AssignedBill>,SessionAware {

	private static final long serialVersionUID = -6659925652584240539L;
	
	//Model Drive
	private AssignedBill ab= new AssignedBill();
	//session aware
	private Map session;
	//the mb for which assignment happens
	private MasterBill mb = new MasterBill();
	private String masterBillId;
	
	//possibly move to a common service 
	private UserDAO userDAO = new UserDAOImpl();
	private AssignBillDAO abDAO = new AssignBillDAO();
	
	private List<UserProfile> friends = new ArrayList<UserProfile>();
	
	private UserAssistService uaService = new UserAssistService();
	
	private List<AssignedBill> assignedBills = new ArrayList<AssignedBill>();
	
	public List<AssignedBill> getAssignedBills() {
		return assignedBills;
	}

	public void setAssignedBills(List<AssignedBill> assignedBills) {
		this.assignedBills = assignedBills;
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
	
	public List<UserProfile> getFriends() {
		return friends;
	}
	public void setFriends(List<UserProfile> friends) {
		this.friends = friends;
	}
	
	@Override
	public AssignedBill getModel() {
		return ab ;
	}

	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}
	
	public String create(){
		//this.addActionMessage("The assigned bill has been created.");
		//this.masterBillId = ab.getMasterBill().getMasterBillId();
		findAssignedBills(ab.getMasterBill());
		
		if(!validateAssignment()){
			this.addActionError("The 'assign to' is blank.");
			this.addFieldError("payer.userProfileId","The 'assign to' is blank.");
			return "rerender";
		}
	    
	    UserProfile up = userDAO.loadUserById(ab.getPayer().getUserProfileId());
	    
	    this.friends = userDAO.findApprovedFriends(this.getSessionUser());
		
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    
	    String formattedDate = df.format(new Date());
	    
	    Date issueDate = new Date(formattedDate);

	    //ab.setIssueDate(new Date(date.getDay(), date.getMonth(), date.getYear()));
	    ab.getPayer().setUserName(up.getUserName());
	    ab.setIssueDate(issueDate);
	    ab.setBeneficiary(this.getSessionUser());
	    ab.setIsPaymentAuthorized('N');
	    ab.setStatus(1);
	    
	    abDAO.saveAssignedBill(ab);
		
	    findAssignedBills(ab.getMasterBill());
	    //TODO send the email to the payer 
	    BaseEmailAction bea = new BaseEmailAction();
		
		bea.generalContactMessage(this.getSessionUser(), up,
				"Assigned Bill " , " a bill which has been assigned to you." );
		return "rerender";
	}
	
	private boolean validateAssignment() {
		boolean validate=true;
		
		if(ab.getPayer().getUserProfileId()==null || ab.getPayer().getUserProfileId()== -1){

			validate = false;
			
		}
		
		return validate;
	}

	public String selectThenAssign() throws Exception {
		
		this.mb = uaService.loadMasterData(this.getMasterBillId());
		
		this.friends = userDAO.findApprovedFriends(this.getSessionUser());

		return Action.SUCCESS;
	}
	
	public String assignedBills(){
		
		this.mb = uaService.loadMasterData(this.getMasterBillId());
		findAssignedBills(mb);
		return "rerender";
	}
	
	public String findAssignedBills(MasterBill mb){
		
		assignedBills = abDAO.findAssignedBills(mb);
		
		return "rerender";
	}

	private UserProfile getSessionUser() {
		UserProfile currSessionUser = (UserProfile)session.get("user");
		
		return currSessionUser;
	}
}
