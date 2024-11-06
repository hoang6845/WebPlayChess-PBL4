package com.pbl4.model.DAO;

import java.util.ArrayList;

import com.pbl4.model.bean.FriendModel;
import com.pbl4.model.bean.UserModel;

public interface IFriendDAO extends DAOInterface<FriendModel> {
	
	FriendModel findRelationshipById(Long userId, Long friendId);

	ArrayList<FriendModel> getListFriend(Long id);
	
	ArrayList<FriendModel> getListSearchNewFr(Long Userid, String name);
	
	void addNewFriend(Long Userid, Long FriendId);
	
	boolean updateFriend(FriendModel friend);
}
