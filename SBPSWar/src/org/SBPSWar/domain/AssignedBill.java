package org.SBPSWar.domain;

import java.util.Date;
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

import org.SBPSWar.util.BillStatusUtility;
import org.SBPSWar.util.SBPSFormAndBeanUtility;


/**
 * <p>Pojo mapping TABLE assigned_bill</p>
 * <p></p>
 *
 * <p>Generated at Sun Feb 07 00:35:22 PST 2010</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
@Entity
@Table(name="assigned_bill")
public class AssignedBill implements Serializable {

	/**
	 * Attribute assignedBillId.
	 */
	@Id
	@GeneratedValue
	@Column(name="ASSIGNED_BILL_ID")
	private Integer assignedBillId;
	
	/**
	 * Attribute amount.
	 * from ui
	 */
	@Column(name="AMOUNT")
	private Double amount;
	
	/**
	 * Attribute dueDate.
	 * from ui
	 */
	@Column(name="DUE_DATE")
	private Date dueDate;
	
	/**
	 * Attribute issueDate.
	 * internal
	 */
	@Column(name="ISSUE_DATE")
	private Date issueDate;
	
	

	/**
	 * Attribute settlementDate.
	 * upon completion
	 */
	@Column(name="SETTLEMENT_DATE")
	private Date settlementDate;
	
	/**
	 * Attribute userProfile
	 */
	 @OneToOne(optional=true)
	 @JoinColumn(name="BENEFICIARY_ID",nullable =true)
	 private UserProfile beneficiary;	

	/**
	 * Attribute userProfile
	 * from ui
	 */
	 @OneToOne(optional=true)
	 @JoinColumn(name="PAYER_ID",nullable =true)
	 private UserProfile payer;	

	/**
	 * Attribute masterBill
	 * internal
	 */
	 @OneToOne(optional=true)
	 @JoinColumn(name="MASTER_BILL_ID",nullable =true)
	 private MasterBill masterBill;	

	/**
	 * Attribute paymentAuthorized.
	 * upon approval
	 */
	@Column(name="IS_PAYMENT_AUTHORIZED")
	private char isPaymentAuthorized;
	
	/**
	 * Attribute status.
	 * variable
	 */
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String humanReadableStatus;
	
	
	
	public String getHumanReadableStatus() {
		
		if(status!=null){
			return BillStatusUtility.getStringStatus(status);
		}else{
			return "";
		}
			
	}

	public void setHumanReadableStatus(String humanReadableStatus) {
		this.humanReadableStatus = humanReadableStatus;
	}

	/**
	 * <p> 
	 * </p>
	 * @return assignedBillId
	 */
	public Integer getAssignedBillId() {
		return assignedBillId;
	}

	/**
	 * @param assignedBillId new value for assignedBillId 
	 */
	public void setAssignedBillId(Integer assignedBillId) {
		this.assignedBillId = assignedBillId;
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
	
	public Date getDueDate() {
		return SBPSFormAndBeanUtility.truncateDate(dueDate);
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getIssueDate() {
		return SBPSFormAndBeanUtility.truncateDate(issueDate);
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getSettlementDate() {
		return SBPSFormAndBeanUtility.truncateDate(settlementDate);
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public UserProfile getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(UserProfile beneficiary) {
		this.beneficiary = beneficiary;
	}

	public UserProfile getPayer() {
		return payer;
	}

	public void setPayer(UserProfile payer) {
		this.payer = payer;
	}

	/**
	 * get masterBill
	 */
	public MasterBill getMasterBill() {
		return this.masterBill;
	}
	
	/**
	 * set masterBill
	 */
	public void setMasterBill(MasterBill masterBill) {
		this.masterBill = masterBill;
	}


	
	public char getIsPaymentAuthorized() {
		return isPaymentAuthorized;
	}

	public void setIsPaymentAuthorized(char isPaymentAuthorized) {
		this.isPaymentAuthorized = isPaymentAuthorized;
	}

	/**
	 * <p> 
	 * </p>
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status new value for status 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	


}