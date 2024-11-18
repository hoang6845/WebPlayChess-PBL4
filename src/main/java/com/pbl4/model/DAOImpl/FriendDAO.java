package com.pbl4.model.DAOImpl;

import java.util.ArrayList;

import com.pbl4.SystemConstant.SystemConstant;
import com.pbl4.mapper.FindMapper;
import com.pbl4.mapper.FriendMapper;
import com.pbl4.mapper.UserMapper;
import com.pbl4.model.DAO.IFriendDAO;
import com.pbl4.model.bean.FriendModel;
import com.pbl4.model.bean.UserModel;

public class FriendDAO extends DAOimple<FriendModel> implements IFriendDAO {

	public static FriendDAO getInstance() {
		return new FriendDAO();
	}
	
	@Override 
	public FriendModel findRelationshipById(Long userId, Long friendId) {
		StringBuilder sql = new StringBuilder("Select * from friend_list f ");
		sql.append(" inner join Userr on f.idFriend = USERr.id");
		sql.append(" inner join profile on USERr.id=profile.userId");
		sql.append(" where idUser=? and idFriend=?");
	    ArrayList<FriendModel> ar = query(sql.toString(), new FriendMapper(), userId, friendId);
	    return ar.isEmpty() ? null : ar.get(0);
	}

	@Override
	public ArrayList<FriendModel> getListFriend(Long id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("Select * from friend_list f ");
		sql.append(" inner join Userr on f.idFriend = USERr.id");
		sql.append(" inner join profile on USERr.id=profile.userId");
		sql.append(" where idUser=?");
		ArrayList<FriendModel> ar= query(sql.toString(),new FriendMapper(), id);
		return ar.isEmpty()?null:ar;
	}

	@Override
	public ArrayList<FriendModel> getListSearchNewFr(Long Userid, String name) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("Select u2.id,u2.fullname,T.status,Profile.imageOfUser from");
		sql.append("( select u.id,u.idRole,f.idUser,f.idFriend,f.status from userr u inner join friend_list f on u.id=f.idUser where u.id=?) as T ");
		sql.append(" right join  userr u2 on T.idfriend=u2.id");
		sql.append(" inner join Profile on u2.id=Profile.userId");
		sql.append(" where u2.idrole=? and u2.id != ? and u2.fullname like ?");
		ArrayList<FriendModel> ar = query(sql.toString(),new FindMapper(),Userid,2,Userid,"%"+name+"%");
		return ar.isEmpty()?null:ar;
	}

	@Override
	public void addNewFriend(Long Userid, Long FriendId) {
		StringBuilder sql1 = new StringBuilder("insert into friend_list(idUser,idfriend,status) values (?,?,'pended')");
		StringBuilder sql2 = new StringBuilder("insert into friend_list(idUser,idfriend,status) values (?,?,'pending')");
		update(sql1.toString(), Userid,FriendId);
		update(sql2.toString(), FriendId,Userid);
	}
	@Override
	public boolean updateFriend(FriendModel friend) {
	    if (friend.getStatus() != null) {
	        String sql = "UPDATE friend_list SET status = ?, createdate = ?, modifieddate = ?, createby = ?, modifiedby = ? "+
	        "WHERE idUser = ? AND idFriend = ?";
	        int rowsAffected = update(sql, friend.getStatus(),friend.getCreateDate(),friend.getModifiedDate(),friend.getCreateBy(),friend.getModifiedBy(), friend.getIdUser(), friend.getIdFriend());
	        return rowsAffected > 0; 
	    } else {
	    	String sql = "DELETE FROM friend_list WHERE (idUser = ? AND idFriend = ?) OR (idUser = ? AND idFriend = ?)";
	    	int result = update(sql, friend.getIdUser(), friend.getIdFriend(), friend.getIdFriend(), friend.getIdUser());
	    	return result > 0;
	    }
	}

}
