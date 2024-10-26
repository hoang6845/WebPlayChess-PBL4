package com.pbl4.serviceImpl;

import java.util.ArrayList;

import com.pbl4.model.DAOImpl.RankDAO;
import com.pbl4.model.bean.RankModel;
import com.pbl4.service.IRankService;

public class RankService implements IRankService {

	public static RankService getInstance() {
		return new RankService();
	}
	
	@Override
	public RankModel findByUserId(long userId) {
		// TODO Auto-generated method stub
		return RankDAO.getInstance().findByUserId(userId);
	}

	@Override
	public ArrayList<RankModel> getTop10Ranks() {
		// TODO Auto-generated method stub
		return RankDAO.getInstance().getTop10Ranks();
	}

	@Override
	public int findRankPosition(long userId) {
		// TODO Auto-generated method stub
		return RankDAO.getInstance().findRankPosition(userId);
	}

	@Override
	public double calculateRankPercentage(long userId) {
		// TODO Auto-generated method stub
		return RankDAO.getInstance().calculateRankPercentage(userId);
	}

}
