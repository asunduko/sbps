package org.SBPSWar.action;

import java.util.Map;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.SBPSWar.dao.UserDAO;
import org.SBPSWar.dao.UserDAOImpl;
import org.SBPSWar.dao.VisitorDAO;
import org.SBPSWar.dao.VisitorDAOImpl;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.domain.Visitor;
import org.SBPSWar.util.EncryptionUtils;
import org.SBPSWar.util.SBPSFormAndBeanUtility;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class InviJoinAction extends ActionSupport implements ModelDriven<Visitor>, SessionAware {

	private static final long serialVersionUID = -6659925652584240539L;
	
	private Visitor visitor = new Visitor();
	private VisitorDAO visitorDAO = new VisitorDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private Map session;
	
	@Override
	public Visitor getModel() {
		
		return visitor;
	}

	public boolean validateInvitePage (){
		
		boolean validate = true;
		
		if(!SBPSFormAndBeanUtility.validateString(visitor.getEmail())){
			addFieldError( "username", "User must not be blank.") ;
			validate =false;
			
		}else if(!SBPSFormAndBeanUtility.validateEmail(visitor.getEmail())){
				addFieldError( "username", "A valid Email is required.") ;
				validate =false;		
		}else{
			if(visitor.getEmail().equals(getSessionUser().getUserName())){
				addFieldError( "username", "You can't invite yourself.") ;
				validate =false;
			}
		}
		return validate;
	}
	
	public boolean isNewFriendship(UserProfile currentSessionUser, UserProfile invitedGuest){
		
		boolean validate = true;
		
		String actionMessage = visitorDAO.validateFriendshipStatus(currentSessionUser,invitedGuest);
		
		if(!actionMessage.equals("")){
			this.addActionError(actionMessage);
			validate = false;
		}
		
		return validate;
		
	}
	
	public String inviteJoin(){
		
		UserProfile user = getSessionUser();
		
		if(this.validateInvitePage()){
			
			
			UserProfile invitedGuest = userDAO.findUserByName(visitor.getEmail());
			//invitedGuest is already an SBPS customer
			if(invitedGuest != null){
				boolean newFriend = this.isNewFriendship(user,invitedGuest);
				
				if(newFriend){
					
					//on visitor set the master
					visitor.setMUser(user);
					//on visitor save the just invited user
					visitor.setIUser(invitedGuest);
					//set the approve to no
					visitor.setIsAccepted('N');
				
					visitor.setHasMResponded('N');
					//save the visitor
					visitorDAO.saveVisitor(visitor);
					
					//email the visitor
					this.emailVisitor(visitor);
					//reset the input form
					//visitor.setEmail("");
					return SUCCESS;
					
				}else{
					return "inviteUser";
				}
				
			}//not in the system yet
			else{
				//so create
				UserProfile invitedProfile = new UserProfile();
				invitedProfile.setUserName(visitor.getEmail());
				//save invited user
				userDAO.saveUser(invitedProfile);
				
				//on visitor set the master
				visitor.setMUser(user);
				//on visitor save the just invited user
				visitor.setIUser(invitedProfile);
				//set the approve to no
				visitor.setIsAccepted('N');
				visitor.setHasMResponded('N');
				//save the visitor
				visitorDAO.saveVisitor(visitor);
				
				//email the visitor
				this.emailVisitor(visitor);
				//reset the input form
				//visitor.setEmail("");
				return SUCCESS;
			}	

		}else{
			
			return "inviteUser";
		}
	}
	
    private void emailVisitor( Visitor visitor ) {
    	
    	String addendum = "<br>"+ "You've been invited to join the SPBS Network" + "<br>";

		Session session = BaseEmailAction.getSession();
    	
	    	
	    	try {
	   
	    		
	    		InternetAddress emailAddress = new InternetAddress(visitor.getEmail());


		    	MimeMessage message = new MimeMessage(session);
		    	message.setFrom(new InternetAddress("sbpsAdmin@SBPS.com"));
		    	message.addRecipient(Message.RecipientType.TO,emailAddress);
		    		
		    	MimeMultipart multipart = new MimeMultipart("related");
		    	
		    	// first part  (the html)
	            BodyPart messageBodyPart = new MimeBodyPart();
	            
	            String htmlText = "<H3>Dear user,</H3> \n";
		    	
		    	
		    	message.setSubject("SBPS invitation");	
		    		


		    	htmlText = htmlText + "<H3>You have been invited to join Split Bill Payment System " 
		    				+ "<a href=\"http://" + BaseEmailAction.getServerName()+"/SBPSWar/linkLogin.action?"
		    				+ "visitorId=" + EncryptionUtils.encrypt(visitor.getInvitationJoinId().toString())+"\">" 
		    				+ " Follow this link to join."
		    				+ " </a>"
		    				+ " </H3>" ;
		    			 
		      		
		    	
		    	htmlText = htmlText + addendum + "<br>" ;
		    		
		    	messageBodyPart.setContent(htmlText, "text/html");

	            // add it
	            multipart.addBodyPart(messageBodyPart);
	            
	            // put everything together
	            message.setContent(multipart);

		    	
		    	Transport.send(message);
		    	System.out.println(" Exiting category e-mail sender");
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		System.out.println("e = "+e.getStackTrace());
	    		System.out.println("Exiting category e-mail sender after errr");
	    	}
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
