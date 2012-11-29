package org.SBPSWar.domain;

import java.util.List;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * <p>Pojo mapping TABLE personal_data</p>
 * <p></p>
 *
 * <p>Generated at Sun Feb 07 00:35:22 PST 2010</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
@Entity
@Table(name="personal_data")
public class PersonalData implements Serializable {


	/**
	 * Attribute personalDataId.
	 */
	//alt foreign identified generator
	@Id
	@GeneratedValue
	@Column(name="PERSONAL_DATA_ID")	
	private Integer personalDataId;
	
	/**
	 * Attribute userProfile
	 */
	@OneToOne
	@JoinColumn(name="PROFILE_ID")
	private UserProfile userProfile;	

	/**
	 * Attribute ssn.
	 */
	@Column(name="SSN")
	private String ssn;
	
	/**
	 * Attribute firstName.
	 */
	@Column(name="FIRST_NAME")
	private String firstName;
	
	/**
	 * Attribute lastName.
	 */
	@Column(name="LAST_NAME")
	private String lastName;
	
	/**
	 * Attribute dob.
	 */
	@Column(name="DOB")
	private Date dob;
	
	
	
	/**
	 * <p> 
	 * </p>
	 * @return personalDataId
	 */
	public Integer getPersonalDataId() {
		return personalDataId;
	}

	/**
	 * @param personalDataId new value for personalDataId 
	 */
	public void setPersonalDataId(Integer personalDataId) {
		this.personalDataId = personalDataId;
	}
	
	/**
	 * get userProfile
	 */
	public UserProfile getUserProfile() {
		return this.userProfile;
	}
	
	/**
	 * set userProfile
	 */
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	/**
	 * <p> 
	 * </p>
	 * @return ssn
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * @param ssn new value for ssn 
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName new value for firstName 
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName new value for lastName 
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	
	public void setDob(String dob) {
		
		System.out.println("Dob is a string: " + dob);
	}
	
}