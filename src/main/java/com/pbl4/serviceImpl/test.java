package com.pbl4.serviceImpl;

import com.pbl4.model.bean.UserModel;

import java.util.ArrayList;

import com.pbl4.model.bean.HistoryModel;
import com.pbl4.model.bean.ProfileModel;


import com.pbl4.model.DAOImpl.FriendDAO;
import com.pbl4.model.bean.FriendModel;
import com.pbl4.serviceImpl.FriendService;

import com.pbl4.serviceImpl.UserService;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        FriendService friendService = FriendService.getInstance();
        UserService u = UserService.getInstance();
//        u.insert("alo123123", "123456", "12312@");
        System.out.print(u.checkUserNameExists("alo1231234"));
//        // Test getListFriend
//        Long testUserId = 1L;
//        ArrayList<FriendModel> friendsList = friendService.getListFriend(testUserId);
//        if (friendsList != null) {
//            System.out.println("getListFriend test passed. Number of friends: " + friendsList.size());
//        } else {
//            System.out.println("getListFriend test failed.");
//        }
//
//        // Test getListSearchNewFr
//        String searchName = "Gues";
//        ArrayList<FriendModel> searchResult = friendService.getListSearchNewFr(3L, searchName);
//        if (searchResult != null && !searchResult.isEmpty()) {
//            System.out.println("getListSearchNewFr test passed. Number of friends found: " + searchResult.size());
//        } else {
//            System.out.println("getListSearchNewFr test returned no results.");
//        }
//
//        // Test addnewFriend
//        Long newFriendId = 2L;
//        friendService.addnewFriend(5l,3l);
//        System.out.println("addnewFriend test completed. Added friend with ID: " + newFriendId);
//
//        // Test deleteFriend
//        friendService.deleteFriend(testUserId, newFriendId);
//        FriendModel deletedFriend = FriendDAO.getInstance().findRelationshipById(5l, newFriendId);
//        if (deletedFriend != null && deletedFriend.getStatus() == null) {
//            System.out.println("deleteFriend test passed. Friend status is now null.");
//        } else {
//            System.out.println("deleteFriend test failed.");
//        }

        // Test acceptFriend
//        ArrayList<FriendModel> friendsList = friendService.getListFriend(2l);
//        if (friendsList != null && !friendsList.isEmpty()) {
//            System.out.println("getListFriend test passed. Number of friends: " + friendsList.size());
//            for (FriendModel friend : friendsList) {
//                System.out.println("Friend ID: " + friend.getIdFriend());
//                System.out.println("Friend Name: " + friend.getNameFriend());
//            }
//        }
//        ArrayList<FriendModel> a = friendService.getListSearchNewFr(2l,"G");
//        System.out.println("getListFriend test passed. Number of friends: " + a.size());
//        for (FriendModel friend : a) {
//            System.out.println("Friend ID: " + friend.getIdFriend());
//            System.out.println("Friend Name: " + friend.getNameFriend());
//        }
    }
}
        
    

