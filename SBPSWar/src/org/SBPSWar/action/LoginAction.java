package org.SBPSWar.action;

import java.util.Map;

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

/**
 * @author nrp0220189
 *
 */
public class LoginAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = -2613425890762568273L;
	private String username;
	private String password;
	private String visitorId;
	private Map session;
	
	public boolean validatePage (){
		
		boolean validate = true;
		
		if(!SBPSFormAndBeanUtility.validateString(username)){
			addFieldError( "username", "Username is required.") ;
			validate =false;
		}
		
		if(!SBPSFormAndBeanUtility.validateString(password)){
			addFieldError( "username", "Password is required.") ;
			validate = false;
		}
		
		return validate;
	}
	private VisitorDAO visitorDAO = new VisitorDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();


	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}
	

	public String link()
	{
		if(this.getVisitorId()!=null){
			Integer id = Integer.parseInt(EncryptionUtils.decrypt(visitorId));
			Visitor v = visitorDAO.findVisitorById(id);
			if(v != null){
				//pre-populate user name
				this.setUsername(v.getEmail());
			}
		}
		
		return "login";		
	}
	
	public String execute()
	{
		String resultComponent = "";
		if(!this.validatePage()){
			resultComponent ="login";
		}
		else {
	
			UserProfile up =userDAO.findUserByName(this.getUsername());
			if(up != null){
				if(this.getPassword().equals(up.getEncrPswd())){
					resultComponent = "loggedIn";
					session.put("user", up );
					session.put("login", true);
					if (up.getPd() != null){
						session.put("showall", true);
					}
				}else{
					addActionError ("Incorrect Credentials" );
					resultComponent ="registerRecover";
				}
			}else{
				addActionError ("Incorrect Credentials" );
				resultComponent ="registerRecover";	
			}
		}
		
		return resultComponent;
	
	}

	public void setSession(Map session) {
		this.session = session;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
