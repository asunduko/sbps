package org.SBPSWar.dao;

import java.util.List;
import org.SBPSWar.domain.UserProfile;
import org.SBPSWar.domain.Visitor;


public interface UserDAO {

	public void saveUser(UserProfile user);
	public List<UserProfile> listUser();
	public UserProfile findUserByName(String name);
	public UserProfile loadUserById(Integer userId);
	public List<UserProfile> findApprovedFriends(UserProfile up);
	public List<Visitor> findInvitedUsers(UserProfile up);
	public List<Visitor> findInvitedByOthers(UserProfile up);
	public UserProfile reresh(UserProfile up);
	public void evictUser(UserProfile verUser);
}
