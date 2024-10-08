package com.pbl4.model.DAO;

import java.util.ArrayList;

import com.pbl4.model.bean.FriendModel;

public interface IFriendDAO extends DAOInterface<FriendModel> {

	ArrayList<FriendModel> getListFriend(Long id);
	
	ArrayList<FriendModel> getListSearchNewFr(Long Userid, String name);
}
