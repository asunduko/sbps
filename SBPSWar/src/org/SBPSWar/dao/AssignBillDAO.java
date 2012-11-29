package org.SBPSWar.dao;

import java.util.List;

import org.SBPSWar.domain.AssignedBill;
import org.SBPSWar.domain.MasterBill;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.domain.Visitor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class AssignBillDAO {
	
	@SessionTarget
	Session session;
	@TransactionTarget
	Transaction transaction;


	public void saveAssignedBill(AssignedBill ab) {
		try {
			session.saveOrUpdate(ab);
			session.flush();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}
	
	public List<AssignedBill> findAssignedBills(MasterBill mb){
		List<AssignedBill> assignedBills = null;
		
		String queryString = 
			" from AssignedBill ab where ab.masterBill = ?";
		
		try {
			Query queryObject  = session.createQuery(queryString);
			
			queryObject.setParameter(0, mb);

			assignedBills = queryObject.list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return assignedBills;
		
	}

	public List<AssignedBill> findMyBills(UserProfile sessionUser) {
		
		
		List<AssignedBill> assignedBills = null;
		
		String queryString = 
			" from AssignedBill ab where ab.payer = ?";
		
		try {
			Query queryObject  = session.createQuery(queryString);
			
			queryObject.setParameter(0, sessionUser);

			assignedBills = queryObject.list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return assignedBills;
		
	}

	public AssignedBill findAssigneBillById(Integer assignedBillId) {
		return  (AssignedBill) (session.get(AssignedBill.class, assignedBillId));
	}


}
