package com.pbl4.serviceImpl;

import java.util.ArrayList;

import com.pbl4.model.DAOImpl.UserDAO;
import com.pbl4.model.DAOImpl.RankDAO;
import com.pbl4.model.DAOImpl.ProfileDAO;
import com.pbl4.model.bean.RankModel;
import com.pbl4.model.bean.UserModel;
import com.pbl4.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;


public class UserService implements IUserService {

	public static UserService getInstance() {
		return new UserService();
	}

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password) {
	    // Tìm người dùng theo username
	    UserModel user = UserDAO.getInstance().findByUserName(userName);
	    if (user == null) {
	        return null; 
	    }
	    if (BCrypt.checkpw(password, user.getPassword())) {
	        return user; 
	    }
	    return null; 
	}

	@Override
	public ArrayList<UserModel> getListUser() {
		// TODO Auto-generated method stub
		return UserDAO.getInstance().getListUser();
	}

	@Override
	public ArrayList<UserModel> findUserByName(String mname) {
		// TODO Auto-generated method stub
		return UserDAO.getInstance().findUserByName(mname);
	}

	@Override
	public String findFullnameById(long id) {
		// TODO Auto-generated method stub
		return UserDAO.getInstance().findFullnameById(id);
	}

	@Override
	public UserModel FindUserById(long id) {
		UserModel result = UserDAO.getInstance().FindUserById(id);
		RankModel rankModel = RankService.getInstance().findByUserId(id);
		result.setElo(rankModel.getElo());
		result.setTotalMatches(rankModel.getTotalMatches());
		return result;
	}

	@Override
	public ArrayList<UserModel> getTop10UserRanks() {
		// TODO Auto-generated method stub
		ArrayList<RankModel> ar = RankService.getInstance().getTop10Ranks();
		ArrayList<UserModel> result = new ArrayList<UserModel>();
		for (RankModel a : ar) {
			UserModel temp = new UserModel();
			temp = UserService.getInstance().FindUserById(a.getUserId());
			temp.setElo(a.getElo());
			result.add(temp);
		}
		return result;
	}

	@Override
	public boolean updatePassword(long userId, String newPassword) {
		UserModel user = FindUserById(userId);
		if (user != null) {
			String hashedPassword = hashPassword(newPassword);
			user.setPassword(hashedPassword);
			return UserDAO.getInstance().updateUser(user);
		}
		return false;
	}

	@Override
	public boolean updateName(long userId, String newName) {
		UserModel user = FindUserById(userId);
		if (user != null) {
			user.setFullname(newName);
			return UserDAO.getInstance().updateUser(user);
		}
		return false;
	}

	@Override
	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	@Override 
	public boolean insert(String username, String password, String email) {
		UserModel user = UserDAO.getInstance().findByUserName(username);
		if(user != null) return false;
		String hashpass = hashPassword(password);
	    UserDAO.getInstance().insert(username, hashpass);
	    long userId = UserDAO.getInstance().findByUserName(username).getId();
	    RankDAO.getInstance().insert(userId);
	    ProfileDAO.getInstance().insert(userId, email);
	    return true;
	}

}
