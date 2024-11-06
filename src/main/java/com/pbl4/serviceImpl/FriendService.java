package com.pbl4.serviceImpl;

import java.util.ArrayList;

import com.pbl4.model.DAOImpl.FriendDAO;
import com.pbl4.model.bean.FriendModel;
import com.pbl4.service.IFriendService;

public class FriendService implements IFriendService{
	public static FriendService getInstance() {
		return new FriendService();
	}

	@Override
	public ArrayList<FriendModel> getListFriend(Long id) {
		// TODO Auto-generated method stub
		if (id != null)
			return FriendDAO.getInstance().getListFriend(id);
		else return null;
	}

	@Override
	public ArrayList<FriendModel> getListSearchNewFr(Long Userid, String name) {
		// TODO Auto-generated method stub
		return FriendDAO.getInstance().getListSearchNewFr(Userid, name);
	}

	@Override
	public void addnewFriend(Long UserId, Long FriendId) {
		FriendDAO.getInstance().addNewFriend(UserId, FriendId);
		
	}

    @Override
    public void deleteFriend(Long userId, Long friendId) {
    	FriendModel friend = FriendDAO.getInstance().findRelationshipById(userId, friendId);
    	friend.setStatus(null);
    	FriendDAO.getInstance().updateFriend(friend);
    }

    @Override
    public void acceptFriend(Long userId, Long friendId) {
    	FriendModel friend1 = FriendDAO.getInstance().findRelationshipById(userId, friendId);
    	FriendModel friend2 = FriendDAO.getInstance().findRelationshipById(friendId, userId);
    	friend1.setStatus("accepted");
    	friend2.setStatus("accepted");
    	FriendDAO.getInstance().updateFriend(friend1);
    	FriendDAO.getInstance().updateFriend(friend2);
    }

	
}
