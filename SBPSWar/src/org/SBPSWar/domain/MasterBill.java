package org.SBPSWar.domain;

import java.util.List;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.SBPSWar.util.SBPSFormAndBeanUtility;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;


/**
 * <p>Pojo mapping TABLE master_bill</p>
 * <p></p>
 *
 * <p>Generated at Sun Feb 07 00:35:22 PST 2010</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
@Entity
@Table(name="master_bill")
public class MasterBill implements Serializable {

	/**
	 * Attribute masterBillId.
	 */
	@Id
	@GeneratedValue
	@Column(name="MASTER_BILL_ID")
	private Integer masterBillId;
	
	/**
	 * Attribute amount.
	 */
	@Column(name="AMOUNT")
	private Double amount;
	
	/**
	 * Attribute createDate.
	 * Generated
	 */
	@Column(name="CREATE_DATE")
	private Timestamp createDate;
	
	

	/**
	 * Attribute dueDate.
	 */
	@Column(name="DUE_DATE")
	private Date dueDate;
	
	/**
	 * Attribute issueDate.
	 */
	@Column(name="ISSUE_DATE")
	private Date issueDate;
	
	

	/**
	 * Attribute settlementDate.
	 */
	@Column(name="SETTLEMENT_DATE")
	private Date settlementDate;
	
	/**
	 * Attribute userProfile
	 */
	@OneToOne(optional=true, fetch = FetchType.EAGER)
	@JoinColumn(name="BILL_ADMIN_ID",nullable =true)
	private UserProfile billAdmin;	

	/**
	 * Attribute userProfile
	 * The beneficiary can be the same party as the admin.
	 */
	@OneToOne(optional=true, fetch = FetchType.EAGER)
	@JoinColumn(name="BENEFICIARY_ID",nullable =true)
	private UserProfile beneficiary;	
	
	@Column(name="BILL_NAME")
	private String billName;

	
	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName ;
	}

	/**
	 * <p> 
	 * </p>
	 * @return masterBillId
	 */
	public Integer getMasterBillId() {
		return masterBillId;
	}

	/**
	 * @param masterBillId new value for masterBillId 
	 */
	public void setMasterBillId(Integer masterBillId) {
		this.masterBillId = masterBillId;
	}
	
	/**
	 * <p> 
	 * </p>
	 * @return amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount new value for amount 
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}


	/**
	 * @param settlementDate new value for settlementDate 
	 */
	public void setSettlementDate(Timestamp settlementDate) {
		this.settlementDate = settlementDate;
	}

	public UserProfile getBillAdmin() {
		return billAdmin;
	}

	public void setBillAdmin(UserProfile billAdmin) {
		this.billAdmin = billAdmin;
	}

	public UserProfile getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(UserProfile beneficiary) {
		this.beneficiary = beneficiary;
	}


	public Date getDueDate() {
		return SBPSFormAndBeanUtility.truncateDate(dueDate);
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Date getSettlementDate() {
		return SBPSFormAndBeanUtility.truncateDate(settlementDate);
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Date getIssueDate() {
		return SBPSFormAndBeanUtility.truncateDate(issueDate);
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

}