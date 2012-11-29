package org.SBPSWar.domain;

import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * <p>Pojo mapping TABLE funding_option</p>
 * <p></p>
 *
 * <p>Generated at Sun Feb 07 00:35:22 PST 2010</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
@Entity
@Table(name="funding_option")
public class FundingOption implements Serializable {


	/**
	 * Attribute fundingOption.
	 */
	@Id
	@GeneratedValue
	@Column(name="FUNDING_OPTION_ID")
	private Integer fundingOptionId;
	
	/**
	 * Attribute fundingType.
	 */
	@Column(name="FUNDING_TYPE")
	private String fundingType;
	
	/**
	 * Attribute userProfile
	 */
	 @OneToOne(optional=true)
	 @JoinColumn(name="PROFILE_ID",nullable =true)
	 private UserProfile funder;	

	/**
	 * Attribute authorized.
	 */
	@Column(name="HAS_AUTHORIZED") 
	private char hasAuthorized;
	
	/**
	 * Attribute bAccRouting.
	 */
	@Column(name="B_ACC_ROUTING") 
	private Integer bAccRouting;
	
	/**
	 * Attribute bPersAcc.
	 */
	@Column(name="B_PERS_ACC")
	private Integer bPersAcc;
	
	

	
	public Integer getFundingOptionId() {
		return fundingOptionId;
	}

	public void setFundingOptionId(Integer fundingOptionId) {
		this.fundingOptionId = fundingOptionId;
	}

	/**
	 * <p> 
	 * </p>
	 * @return fundingType
	 */
	public String getFundingType() {
		return fundingType;
	}

	/**
	 * @param fundingType new value for fundingType 
	 */
	public void setFundingType(String fundingType) {
		this.fundingType = fundingType;
	}
	
	
	public UserProfile getFunder() {
		return funder;
	}

	public void setFunder(UserProfile funder) {
		this.funder = funder;
	}

	public char getHasAuthorized() {
		return hasAuthorized;
	}

	public void setHasAuthorized(char hasAuthorized) {
		this.hasAuthorized = hasAuthorized;
	}

	/**
	 * <p> 
	 * </p>
	 * @return bAccRouting
	 */
	public Integer getBAccRouting() {
		return bAccRouting;
	}

	/**
	 * @param bAccRouting new value for bAccRouting 
	 */
	public void setBAccRouting(Integer bAccRouting) {
		this.bAccRouting = bAccRouting;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return bPersAcc
	 */
	public Integer getBPersAcc() {
		return bPersAcc;
	}

	/**
	 * @param bPersAcc new value for bPersAcc 
	 */
	public void setBPersAcc(Integer bPersAcc) {
		this.bPersAcc = bPersAcc;
	}
	

	@Transient
	private String bankAccountNumber;
	@Transient
	private String personalAccountNumber;
	@Transient
	private Boolean authorized;

	public Boolean getAuthorized() {
		return authorized;
	}

	public void setAuthorized(Boolean authorized) {
		this.authorized = authorized;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getPersonalAccountNumber() {
		return personalAccountNumber;
	}

	public void setPersonalAccountNumber(String personalAccountNumber) {
		this.personalAccountNumber = personalAccountNumber;
	}

}