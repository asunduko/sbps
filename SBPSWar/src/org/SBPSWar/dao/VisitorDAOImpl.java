package org.SBPSWar.dao;

import java.util.ArrayList;
import java.util.List;

import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.domain.Visitor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class VisitorDAOImpl implements VisitorDAO{
	
	@SessionTarget
	Session session;
	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	@Override
	public List<Visitor> listVisitor() {	
		List<Visitor> visitors = null;
		try {
			visitors = session.createQuery("from Visitor").list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return visitors;
	}

	@Override
	public void saveVisitor(Visitor visitor) {
		try {
			session.saveOrUpdate(visitor);
			//session.evict(visitor);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}
	
	public Visitor findVisitorById(Integer visitorId) {	
		Visitor visitor = null;
		try {
			visitor = (Visitor)session.get(Visitor.class,visitorId );
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return visitor;
	}

	@Override
	public String validateFriendshipStatus(UserProfile currentSessionUser,
			UserProfile invitedGuest) {
	
		String actionMessage = "";
		
		String alreadyFriends1 = 
			"select count(v) from Visitor v where " +
			"(v.MUser = ? and v.IUser =?) " +
			"and v.isAccepted = ?";
		
		String alreadyFriends2 = 
			"select count(v) from Visitor v where " +
			"(v.MUser = ? and v.IUser =?) " +
			"and v.isAccepted = ?";
		
		String alreadyInvitedByMaster = 
			"select count(v) from Visitor v where " +
			"(v.MUser = ? and v.IUser =?) " +
			"and v.isAccepted = ?";
		
		String masterAlreadyInvited = 
			"select count(v) from Visitor v where " +
			"(v.MUser = ? and v.IUser =?) " +
			"and v.isAccepted = ?";
		

		
		try {
			Query biObject1  = session.createQuery(alreadyFriends1);
			Query biObject2  = session.createQuery(alreadyFriends2);
			Query masterObject  = session.createQuery(alreadyInvitedByMaster);
			Query invitedObject =  session.createQuery(masterAlreadyInvited);
			
			biObject1.setParameter(0, currentSessionUser);
			biObject1.setParameter(1, invitedGuest);
			biObject2.setParameter(0, invitedGuest);
			biObject2.setParameter(1, currentSessionUser);
			biObject1.setParameter(2, "Y");
			biObject2.setParameter(2, "Y");
			
			masterObject.setParameter(0, currentSessionUser);
			masterObject.setParameter(1, invitedGuest);
			masterObject.setParameter(2, "N");
			
			
			invitedObject.setParameter(0, invitedGuest);
			invitedObject.setParameter(1, currentSessionUser);
			invitedObject.setParameter(2, "N");
			
			if( (Long)biObject1.list().get(0) > 0 || (Long)biObject2.list().get(0) > 0 ){
				actionMessage ="Already Friends. ";
			}else if( (Long)masterObject.list().get(0) > 0){
				actionMessage ="Already Invited this User. ";
			}else if ((Long)invitedObject.list().get(0) > 0){
				actionMessage ="Already Invited by this User. ";
			}else{
				actionMessage = "";
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} 
	
		return actionMessage;
	}


}
