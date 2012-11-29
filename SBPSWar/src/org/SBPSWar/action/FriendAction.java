package org.SBPSWar.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.SBPSWar.dao.UserDAO;
import org.SBPSWar.dao.UserDAOImpl;
import org.SBPSWar.dao.VisitorDAO;
import org.SBPSWar.dao.VisitorDAOImpl;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.domain.Visitor;
import org.SBPSWar.util.SBPSFormAndBeanUtility;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class FriendAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = -6659925652584240539L;
	
	private VisitorDAO visitorDAO = new VisitorDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private List<UserProfile> approvedFriends = new ArrayList<UserProfile>();
	private List<Visitor>invitedUsers = new ArrayList<Visitor>();
	private List<Visitor>invitedByUsers = new ArrayList<Visitor>();
	private Integer invitationJoinId;
	private String challenge;
	private String response;
    private String approve;

	public String getApprove() {
		return approve;
	}


	public void setApprove(String approve) {
		this.approve = approve;
	}


	public String getChallenge() {
		return challenge;
	}


	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}


	public String getResponse() {
		return response;
	}


	public void setResponse(String response) {
		this.response = response;
	}


	private Map session;

	public Visitor getVisitorInvitation() {
		return visitorInvitation;
	}


	public void setVisitorInvitation(Visitor visitorInvitation) {
		this.visitorInvitation = visitorInvitation;
	}


	private Visitor visitorInvitation;

	public Integer getInvitationJoinId() {
		return invitationJoinId;
	}


	public void setInvitationJoinId(Integer invitationJoinId) {
		this.invitationJoinId = invitationJoinId;
	}




	public List<Visitor> getInvitedUsers() {
		return invitedUsers;
	}


	public void setInvitedUsers(List<Visitor> invitedUsers) {
		this.invitedUsers = invitedUsers;
	}


	public List<Visitor> getInvitedByUsers() {
		return invitedByUsers;
	}


	public void setInvitedByUsers(List<Visitor> invitedByUsers) {
		this.invitedByUsers = invitedByUsers;
	}


	public List<UserProfile> getApprovedFriends() {
		return approvedFriends;
	}


	public void setApprovedFriends(List<UserProfile> approvedFriends) {
		this.approvedFriends = approvedFriends;
	}


	public String findAllTypesOfFriends(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
		}
		this.findApprovedFriends();
		
		this.findInvitedUsers();
			
		this.findInvitedByOthers();	
		
		return SUCCESS;
	}
	
	public String findApprovedFriends(){
		
		this.approvedFriends  = userDAO.findApprovedFriends(this.getSessionUser());
	
		return SUCCESS;
	}
	
	public String findInvitedUsers(){
		
		this.invitedUsers = userDAO.findInvitedUsers (this.getSessionUser());
		
		return SUCCESS;
	}	
	
	public String findInvitedByOthers(){
		
		this.invitedByUsers = userDAO.findInvitedByOthers(this.getSessionUser());
		
		return SUCCESS;
	}

	public String respond(){
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
		}
		visitorInvitation = visitorDAO.findVisitorById(this.getInvitationJoinId());
			
		return "respond";
	}
	
	public String provideResponse(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
			
		}
		
		visitorInvitation = visitorDAO.findVisitorById(this.getInvitationJoinId());
		
		if(!validateResponse(this.getResponse())){
			
			return "friends";
		}
		
		
		visitorInvitation.setHasMResponded('Y');
		visitorInvitation.setMResponse(this.getResponse());
		visitorDAO.saveVisitor(visitorInvitation);
		//TODO email the invited user about the provided response
		BaseEmailAction bea = new BaseEmailAction();
		
		bea.generalContactMessage(up, visitorInvitation.getIUser(),
				"Response to Challenge " , " a response to your challenge." );
			
		return "friends";
		
	}
	
	public String reviewAnswer(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
			
		}
		
		
		visitorInvitation = visitorDAO.findVisitorById(this.getInvitationJoinId());
		
		if(!this.validateApprove(this.getApprove())){
			
			return "friends";
		}
		
		visitorInvitation.setIsAccepted(this.getApprove().equals("Yes")?'Y':'N');
		visitorDAO.saveVisitor(visitorInvitation);
		
		BaseEmailAction bea = new BaseEmailAction();
		
		bea.generalContactMessage(up, visitorInvitation.getMUser(),
				"Reviewed Answer " , " your answer to the challenge." );
			
		return "friends";
		
	}
	
	public String reviewAnswerPrepare(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
			
		}
		this.clearErrorsAndMessages();
			
		visitorInvitation = visitorDAO.findVisitorById(this.getInvitationJoinId());	
			
		return "reviewAnswerPrepare";
		
	}
	
	
	public String createChallenge(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
			
		}
		
		
		visitorInvitation = visitorDAO.findVisitorById(this.getInvitationJoinId());
		
		if(!validateChallenge(this.getChallenge())){
			
			return "friends";
		}
		
		visitorInvitation.setHasMResponded('N');
		visitorInvitation.setIChallenge(this.getChallenge());
		visitorDAO.saveVisitor(visitorInvitation);
		
		BaseEmailAction bea = new BaseEmailAction();
		
		bea.generalContactMessage(up, visitorInvitation.getMUser(),
				"Challenge created " , " a challenge created in response to your invitation." );
		//TODO email the master about the challenge created. 	
		return "friends";
		
	}

	public String createChallengePrepare(){
		
		UserProfile up = this.getSessionUser();
		if(up == null){
			return "restart";
			
		}
			
		visitorInvitation = visitorDAO.findVisitorById(this.getInvitationJoinId());
		 	
		return "createChallengePrepare";
		
	}
	private boolean validateResponse(String string) {
		boolean validate = true;
		
		if(!SBPSFormAndBeanUtility.validateString(string)){

			//addFieldError( "response", "Invalid Response Input.") ;
			validate =false;
			
		}
		return validate;
	}
	
	private boolean validateChallenge(String string) {
		boolean validate = true;
		
		if(!SBPSFormAndBeanUtility.validateString(string)){

			//addFieldError( "challenge", "Invalid Challenge Input.") ;
			validate =false; 
			
		}
		return validate;
	}
	
	private boolean validateApprove(String string) {
		boolean validate = true;
		
		if(!SBPSFormAndBeanUtility.validateString(string)){

			//addFieldError( "approve", "Invalid Approve Input") ;
			validate =false;
			
		}
		return validate;
	}


	private UserProfile getSessionUser() {
		   return (UserProfile)session.get("user");
	}


	@Override
	public void setSession(Map session) {
		this.session = session;
		
	}

	
	
}
