package org.SBPSWar.dao;

import java.util.List;

import org.SBPSWar.domain.MasterBill;
import org.SBPSWar.domain.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class MasterBillDAOImpl {

	@SessionTarget
	Session session;
	@TransactionTarget
	Transaction transaction;

	
	public List<MasterBill> listMasterBills() {	
		List<MasterBill> courses = null;
		try {
			courses = session.createQuery("from MasterBill").list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return courses;
	}


	public void saveMasterBill(MasterBill mb) {
		try {
			session.saveOrUpdate(mb);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}
	
	public MasterBill findMasterBill(String name){
		MasterBill mb = null;
		try {
			Criteria crit = session.createCriteria(MasterBill.class);
			crit.add(Restrictions.eq("userName", name));

			mb =(MasterBill) crit.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return mb;
	}
	
	public MasterBill loadMasterBillById(Integer masterBillId) {
		return  (MasterBill) (session.get(MasterBill.class, masterBillId));
	}
	
}
