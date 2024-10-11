package com.pbl4.service;

import java.util.ArrayList;

import com.pbl4.model.bean.FriendModel;

public interface IFriendService extends IService{
	ArrayList<FriendModel> getListFriend(Long id);
	
	ArrayList<FriendModel> getListSearchNewFr(Long Userid,String name);
	
	void addnewFriend(Long UserId, Long FriendId);
}
