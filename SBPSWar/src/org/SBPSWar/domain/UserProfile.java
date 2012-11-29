package org.SBPSWar.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.*;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;

/**
 * <p>Pojo mapping TABLE user_profile</p>
 * <p></p>
 *
 * <p>Generated at Sun Feb 07 00:35:22 PST 2010</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
@Entity
@Table(name="user_profile")
public class UserProfile implements Serializable {

	/**
	 * Attribute userProfileId.
	 */
	@Id
	@GeneratedValue
	@Column(name="USER_PROFILE_ID")
	 
	private Integer userProfileId;
	
	/**
	 * Attribute userName.
	 */
	@Column(name="USER_NAME")
	private String userName;
	
	/**
	 * Attribute encrPswd.
	 */
	@Column(name="ENCR_PSWD")
	private String encrPswd;
	
	@Column(name="CHALLENGE_Q")
	private String securityQ;
	
	@Column(name="CHALLENGE_A")
	private String securityA;
	
	public String getSecurityQ() {
		return securityQ;
	}

	public void setSecurityQ(String securityQ) {
		this.securityQ = securityQ;
	}

	public String getSecurityA() {
		return securityA;
	}
	
	public void setSecurityA(String securityA) {
		this.securityA = securityA;
	}

	/**
	 * Attribute visitor
	 */
	
	//@OneToMany(mappedBy="MUser",fetch = FetchType.EAGER, targetEntity=Visitor.class)
	@Transient
	 private Set<UserProfile> invitedFriends = new HashSet<UserProfile>();	

	public Set<UserProfile> getInvitedFriends() {
		return invitedFriends;
	}

	public void setInvitedFriends(Set<UserProfile> invitedFriends) {
		this.invitedFriends = invitedFriends;
	}

	@OneToOne(mappedBy="userProfile", 
			cascade= {CascadeType.PERSIST, CascadeType.ALL})
	private PersonalData pd;
	
	//TODO initialize the set somehow and change back to LAZY
	@OneToMany(mappedBy="billAdmin",fetch = FetchType.EAGER)
	private Set<MasterBill> mbl = new HashSet<MasterBill>();
	
	@OneToMany(mappedBy="funder",fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.ALL})
	private Set<FundingOption> fos = new HashSet<FundingOption>();
	
	
	public Set<FundingOption> getFos() {
		return fos;
	}

	public void setFos(Set<FundingOption> fos) {
		this.fos = fos;
	}

	public Set<MasterBill> getMbl() {
		return mbl;
	}

	public void setMbl(Set<MasterBill> mbl) {
		this.mbl = mbl;
	}

	public void addMasterBill(MasterBill mb){
		
		mbl.add(mb);
	}

	public PersonalData getPd() {
		return pd;
	}

	public void setPd(PersonalData pd) {
		this.pd = pd;
	}

	/**
	 * <p> 
	 * </p>
	 * @return userProfileId
	 */
	public Integer getUserProfileId() {
		return userProfileId;
	}

	/**
	 * @param userProfileId new value for userProfileId 
	 */
	public void setUserProfileId(Integer userProfileId) {
		this.userProfileId = userProfileId;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName new value for userName 
	 */
	
	@EmailValidator(message="Wrong Email Format")
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return encrPswd
	 */
	public String getEncrPswd() {
		return encrPswd;
	}

	/**
	 * @param encrPswd new value for encrPswd 
	 */
	public void setEncrPswd(String encrPswd) {
		this.encrPswd = encrPswd;
	}
	


	/**
	 * List of AssignedBill
	 *//*
	private List<AssignedBill> assignedBills = null;

	*//**
	 * List of FundingOption
	 *//*
	private List<FundingOption> fundingOptions = null;

	*//**
	 * List of MasterBill
	 *//*
	private List<MasterBill> masterBills = null;

	*//**
	 * List of PersonalData
	 *//*
	private List<PersonalData> personalData = null;*/

	
	

/*	public List<AssignedBill> getAssignedBills() {
		return assignedBills;
	}

	public void setAssignedBills(List<AssignedBill> assignedBills) {
		this.assignedBills = assignedBills;
	}

	public List<MasterBill> getMasterBills() {
		return masterBills;
	}

	public void setMasterBills(List<MasterBill> masterBills) {
		this.masterBills = masterBills;
	}

	public List<PersonalData> getPersonalData() {
		return personalData;
	}

	public void setPersonalData(List<PersonalData> personalData) {
		this.personalData = personalData;
	}

	*//**
	 * Get the list of FundingOption
	 *//*
	 public List<FundingOption> getFundingOptions() {
	 	return this.fundingOptions;
	 }
	 
	*//**
	 * Set the list of FundingOption
	 *//*
	 public void setFundingOptions(List<FundingOption> fundingOptions) {
	 	this.fundingOptions = fundingOptions;
	 }
	*//**
	 * Get the list of MasterBill
	 */



}