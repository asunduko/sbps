package org.SBPSWar.dao;

import java.util.List;

import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.domain.Visitor;

public interface VisitorDAO {
	
	public void saveVisitor(Visitor visitor);
	public List<Visitor> listVisitor();
	public Visitor findVisitorById(Integer visitorId);
	public String validateFriendshipStatus(UserProfile currentSessionUser,
			UserProfile invitedGuest);
}
