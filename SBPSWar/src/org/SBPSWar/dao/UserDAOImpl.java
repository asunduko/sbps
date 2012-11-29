package org.SBPSWar.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.domain.Visitor;


/**
 * @author nrp0220189
 *
 */
/**
 * @author nrp0220189
 *
 */
public class UserDAOImpl implements UserDAO {

	@SessionTarget
	Session session;
	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProfile> listUser() {	
		List<UserProfile> courses = null;
		try {
			courses = session.createQuery("from UserProfile").list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return courses;
	}

	@Override
	public void saveUser(UserProfile user) {
		try {
			session.saveOrUpdate(user);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}
	
	public UserProfile findUserByName(String name){
		UserProfile user = null;
		try {
			Criteria crit = session.createCriteria(UserProfile.class);
			crit.add(Restrictions.eq("userName", name));

			user =(UserProfile) crit.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}

	@Override
	public UserProfile loadUserById(Integer userId) {
		return  (UserProfile) (session.get(UserProfile.class, userId));
	}

	public List<UserProfile> findApprovedFriends(UserProfile up){
		List<UserProfile> friends = new ArrayList<UserProfile>();
		
		String masterRel = 
			"select v.MUser from Visitor v where  v.IUser = ? and v.isAccepted = ?";
		String invRel = 
			"select v.IUser from Visitor v where  v.MUser = ? and v.isAccepted = ?";
		
		try {
			Query masterObject  = session.createQuery(masterRel);
			Query invObject  = session.createQuery(invRel);
			
			masterObject.setParameter(0, up);
			invObject.setParameter(0, up);
			masterObject.setParameter(1, "Y");
			invObject.setParameter(1, "Y");
			
			friends.addAll(masterObject.list());
			friends.addAll(invObject.list());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return friends;
		
	}
	
	/**
	 * find those who were invited by the user in the current session
	 * MUser = master user
	 */
	public List<Visitor> findInvitedUsers(UserProfile up){
		List<Visitor> invitedUsers = null;
		
		String queryString = 
			" from Visitor v where v.MUser = ? and v.isAccepted = ?";
		
		try {
			Query queryObject  = session.createQuery(queryString);
			
			queryObject.setParameter(0, up);
			queryObject.setParameter(1, "N");
			invitedUsers = queryObject.list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return invitedUsers;
		
	}
	
	/**
	 * find those who invited the user in the current session
	 * IUSer = invited user
	 *
	 */
	public List<Visitor> findInvitedByOthers(UserProfile up){
		List<Visitor> invitedByUsers = null;
		
		String queryString = 
			" from Visitor v where v.IUser = ? and v.isAccepted = ?";
		
		try {
			Query queryObject  = session.createQuery(queryString);
			
			queryObject.setParameter(0, up);
			queryObject.setParameter(1, "N");
			invitedByUsers = queryObject.list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return invitedByUsers;
		
	}

	@Override
	public UserProfile reresh(UserProfile up) {
		
		try {
			session.refresh(up);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		return up;
	}

	@Override
	public void evictUser(UserProfile verUser) {
		
		try {
			session.evict(verUser);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}
	
	
}
