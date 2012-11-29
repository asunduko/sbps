package org.SBPSWar.domain;

import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * <p>Pojo mapping TABLE visitor</p>
 * <p></p>
 *
 * <p>Generated at Sun Feb 07 00:35:22 PST 2010</p>
 * @author Salto-db Generator v1.0.16 / Hibernate pojos and xml mapping files.
 * 
 */
@Entity
@Table(name="invitation_join")
public class Visitor implements Serializable {
	
	
	/**
	 * Attribute invitationJoinId.
	 */
	@Id
	@GeneratedValue
	@Column(name="INVITATION_JOIN_ID")
	private Integer invitationJoinId;
	
	/**
	 * Attribute email.
	 */
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="HAS_M_RESPONDED")
	private char hasMResponded;
	
	
	public char getHasMResponded() {
		return hasMResponded;
	}

	public void setHasMResponded(char hasMResponded) {
		this.hasMResponded = hasMResponded;
	}

	@Column(name="IS_ACCEPTED")
	private char isAccepted;
	
	@ManyToOne
	@JoinColumn(name="M_USER",nullable =true)
	private UserProfile MUser;
	
	@ManyToOne
	@JoinColumn(name="I_USER",nullable =true)
	private UserProfile IUser;
	
	@Column(name="I_CHALLENGE")
	private String IChallenge;
	
	@Column(name="M_RESPONSE")
	private String MResponse;

	public Integer getInvitationJoinId() {
		return invitationJoinId;
	}

	public void setInvitationJoinId(Integer invitationJoinId) {
		this.invitationJoinId = invitationJoinId;
	}



	public char getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(char isAccepted) {
		this.isAccepted = isAccepted;
	}

	
	public UserProfile getMUser() {
		return MUser;
	}

	public void setMUser(UserProfile mUser) {
		MUser = mUser;
	}

	public UserProfile getIUser() {
		return IUser;
	}

	public void setIUser(UserProfile iUser) {
		IUser = iUser;
	}

	public String getIChallenge() {
		return IChallenge;
	}

	public void setIChallenge(String iChallenge) {
		IChallenge = iChallenge;
	}

	public String getMResponse() {
		return MResponse;
	}

	public void setMResponse(String mResponse) {
		MResponse = mResponse;
	}

	/**
	 * <p> 
	 * </p>
	 * @return email
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * @param email new value for email 
	 */
	public void setEmail(String email) {
		this.email = email;
	}



}