package org.SBPSWar.action;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.SBPSWar.dao.UserDAO;
import org.SBPSWar.dao.UserDAOImpl;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.util.SBPSFormAndBeanUtility;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.validator.Valid;

public class UserAction extends ActionSupport implements ModelDriven<UserProfile> {

	private static final long serialVersionUID = -6659925652584240539L;

	@Valid
	private UserProfile user = new UserProfile();
	private List<UserProfile> userList = new ArrayList<UserProfile>();
	private UserDAO userDAO = new UserDAOImpl();
	
	public boolean validateDuplicate (UserProfile verUser){
		
		boolean duplicate = false;

		if(verUser!=null){
			if(verUser.getEncrPswd()!=null && verUser.getSecurityQ()!=null && verUser.getSecurityA()!=null){
				duplicate =true;
			}
			else{
				duplicate = false;
			}
		}
		
		return duplicate;
	}
	
	public boolean validateRegisterPage (){
		
		boolean validate = true;
		
		if(!SBPSFormAndBeanUtility.validateString(user.getUserName())){
			//candidate for ajax, verify that the name is not already taken
			addFieldError( "username", "User must not be blank.") ;
			validate =false;
			
		}else{
			if(!SBPSFormAndBeanUtility.validateEmail(user.getUserName())){
				addFieldError( "username", "A valid Email is required.") ;
				validate =false;
			}
			
		}
		
		if(!SBPSFormAndBeanUtility.validateString(user.getEncrPswd())){
			
			addFieldError( "password", "Password must not be blank.") ;
			validate = false;	
		}else{
			if(!SBPSFormAndBeanUtility.validateLength(user.getUserName())){
				addFieldError( "password", "Your password is too short.") ;
				validate =false;
			}
		}
		
		if(!SBPSFormAndBeanUtility.validateString(user.getSecurityQ())){
			
			addFieldError( "password", "Must choose a security question.") ;
			validate = false;	
		}
		
		if(!SBPSFormAndBeanUtility.validateString(user.getSecurityA())){
			
			addFieldError( "password", "Must provide a security answer.") ;
			validate = false;	
		}else{
			if(user.getSecurityA().length()< 3){
				addFieldError( "password", "Security answer 3 char. or longer.") ;
				validate =false;
			}
		}
		
		return validate;
	}
	
	@Override
	public UserProfile getModel() {
		return user;
	}
	
	public String add()
	{
		UserProfile verUser = userDAO.findUserByName(user.getUserName());
		
		userDAO.evictUser(verUser);
		
		if(validateDuplicate(verUser)){
			addActionError ("This user already exists." );
			
			return "failRegister";
		
		}
		
		//update invited
		updateFromInvited(verUser);
		
		
		if(this.validateRegisterPage()){ 
				userDAO.saveUser(user);
				return SUCCESS;
		}else{
			return "failRegister";
		}
			
	}
	
	private void updateFromInvited(UserProfile verUser) {
		
		if(verUser!=null){
			
			user.setUserProfileId(verUser.getUserProfileId());
		}
		
	}

	public String recover(){
		 UserProfile recoveredProfile = userDAO.findUserByName(user.getUserName());
		 
		 if(recoveredProfile !=null){
			 
			 if(!SBPSFormAndBeanUtility.validateString(recoveredProfile.getSecurityA())
					 ||!SBPSFormAndBeanUtility.validateString(recoveredProfile.getSecurityQ()) ){
				//the user didn't pass the security challenge
				 addActionError ("Such security credentials were never provided for :" + user.getUserName());
				 return "failRegister";
			 }else if(recoveredProfile.getSecurityA().equalsIgnoreCase(user.getSecurityA())
				 && recoveredProfile.getSecurityQ().equalsIgnoreCase(user.getSecurityQ())){
				 	 
				 sendRecoveredCredentials(recoveredProfile);
				 //this.addActionMessage("Check Your Email");
				 return "emailCheck";
			 }else{
				 //the user didn't pass the security challenge
				 addActionError ("Security Challenge Failure." );
				 return "failRegister";
			 }
		 }else{
			 addActionError ("No such user in the system." );
			 return "failRegister";
		 }
		 
	}
	
	private void sendRecoveredCredentials(UserProfile recoveredProfile) {
		String addendum = "<br>"+ "Please, use the recovered account information to log in." + "<br>";
		
		Session session = BaseEmailAction.getSession();
    	
    	try {
   
    		
    		InternetAddress emailAddress = new InternetAddress(recoveredProfile.getUserName());


	    	MimeMessage message = new MimeMessage(session);
	    	message.setFrom(new InternetAddress("sbpsAdmin@SBPS.com"));
	    	message.addRecipient(Message.RecipientType.TO,emailAddress);
	    		
	    	MimeMultipart multipart = new MimeMultipart("related");
	    	
	    	// first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            
            String htmlText = "<H3>Dear user,</H3> \n";
	    	
	    	
	    	message.setSubject("SBPS recovered credentials");	
	    		


	    	htmlText = htmlText + "<H3>You have requested a password recovery. " 
	    				+ "Your password has been recovered: " + recoveredProfile.getEncrPswd()
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
	
	
	public String list(){
		userList = userDAO.listUser();
		return SUCCESS;
	}
		
	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	public List<UserProfile> getUserList() {
		return userList;
	}

	public void setUserList(List<UserProfile> userList) {
		this.userList = userList;
	}

}
