package com.pbl4.serviceImpl;

import java.util.ArrayList;

import com.pbl4.model.DAOImpl.UserDAO;
import com.pbl4.model.bean.RankModel;
import com.pbl4.model.bean.UserModel;
import com.pbl4.service.IUserService;

public class UserService implements IUserService {

	public static UserService getInstance() {
		return new UserService();
	}

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String UserName, String password) {
		// TODO Auto-generated method stub
		return UserDAO.getInstance().findByUserNameAndPasswordAndStatus(UserName, password);
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
	public String findUserNameById(long id) {
		// TODO Auto-generated method stub
		return UserDAO.getInstance().findUserNameById(id);
	}

	@Override
	public UserModel FindUserById(long id) {
		UserModel result= UserDAO.getInstance().FindUserById(id);
		RankModel rankModel= RankService.getInstance().findByUserId(id);
		result.setElo(rankModel.getElo());
		result.setTotalMatches(rankModel.getTotalMatches());
		return result;
	}

	@Override
	public ArrayList<UserModel> getTop10UserRanks() {
		// TODO Auto-generated method stub
		ArrayList<RankModel> ar= RankService.getInstance().getTop10Ranks();
		ArrayList<UserModel> result= new ArrayList<UserModel>();
		for (RankModel a : ar) {
			UserModel temp = new UserModel();
			temp = UserService.getInstance().FindUserById(a.getUserId());
			temp.setElo(a.getElo());
			result.add(temp);
		}
		return result;
	}

}
