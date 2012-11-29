package org.SBPSWar.action;

import org.SBPSWar.dao.MasterBillDAOImpl;
import org.SBPSWar.domain.MasterBill;

public class UserAssistService {
	private MasterBillDAOImpl mbDAO = new MasterBillDAOImpl();
	
	public MasterBill loadMasterData(String masterBillId) {
		return this.mbDAO.loadMasterBillById(Integer.parseInt(masterBillId));
	}

}
