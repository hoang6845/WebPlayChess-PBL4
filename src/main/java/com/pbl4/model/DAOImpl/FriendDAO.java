package com.pbl4.model.DAOImpl;

import java.util.ArrayList;

import com.pbl4.mapper.FindMapper;
import com.pbl4.mapper.FriendMapper;
import com.pbl4.model.DAO.IFriendDAO;
import com.pbl4.model.bean.FriendModel;

public class FriendDAO extends DAOimple<FriendModel> implements IFriendDAO {

	public static FriendDAO getInstance() {
		return new FriendDAO();
	}
	
	@Override
	public ArrayList<FriendModel> getListFriend(Long id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("Select * from friend_list f ");
		sql.append(" inner join Userr on f.idFriend = USERr.id");
		sql.append(" where idUser=? ");
		ArrayList<FriendModel> ar= query(sql.toString(),new FriendMapper(), id);
		return ar.isEmpty()?null:ar;
	}

	@Override
	public ArrayList<FriendModel> getListSearchNewFr(Long Userid, String name) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("Select u2.id,u2.fullname,T.status from");
		sql.append("( select u.id,u.idRole,f.idUser,f.idFriend,f.status from userr u inner join friend_list f on u.id=f.idUser where u.id=?) as T ");
		sql.append(" right join  userr u2 on T.idfriend=u2.id");
		sql.append(" where u2.idrole=? and u2.id != ? and u2.fullname like ?");
		ArrayList<FriendModel> ar = query(sql.toString(),new FindMapper(),Userid,2,Userid,"%"+name+"%");
		return ar.isEmpty()?null:ar;
	}

}
