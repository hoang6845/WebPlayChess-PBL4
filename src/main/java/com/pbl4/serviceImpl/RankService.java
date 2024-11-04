package com.pbl4.serviceImpl;

import java.util.ArrayList;

import com.pbl4.model.DAOImpl.RankDAO;
import com.pbl4.model.bean.RankModel;
import com.pbl4.service.IRankService;
import com.pbl4.utils.SessionUtil;

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

	@Override
	public void UpdateAfterGames(long whiteId, long BlackId, String result, int eloChange) {
		if (result.equals("win")) {
			RankModel whiteRank = findByUserId(whiteId);
			RankModel blackRank = findByUserId(BlackId);
			RankDAO.getInstance().updateElo(whiteId, whiteRank.getElo()+eloChange);
			RankDAO.getInstance().updateGames(whiteId, whiteRank.getTotalMatches()+1, whiteRank.getWin()+1, whiteRank.getDraws(), whiteRank.getLose());
			RankDAO.getInstance().updateElo(BlackId, blackRank.getElo()-eloChange);
			RankDAO.getInstance().updateGames(BlackId, blackRank.getTotalMatches()+1, blackRank.getWin(), blackRank.getDraws(), blackRank.getLose()+1);
		}
		else if (result.equals("lose")) {
			RankModel whiteRank = findByUserId(whiteId);
			RankModel blackRank = findByUserId(BlackId);
			RankDAO.getInstance().updateElo(whiteId, whiteRank.getElo()-eloChange);
			RankDAO.getInstance().updateGames(whiteId, whiteRank.getTotalMatches()+1, whiteRank.getWin(), whiteRank.getDraws(), whiteRank.getLose()+1);
			RankDAO.getInstance().updateElo(BlackId, blackRank.getElo()+eloChange);
			RankDAO.getInstance().updateGames(BlackId, blackRank.getTotalMatches()+1, blackRank.getWin()+1, blackRank.getDraws(), blackRank.getLose());
		}
		else {
			RankModel whiteRank = findByUserId(whiteId);
			RankModel blackRank = findByUserId(BlackId);
			RankDAO.getInstance().updateGames(whiteId, whiteRank.getTotalMatches()+1, whiteRank.getWin(), whiteRank.getDraws()+1, whiteRank.getLose());
			RankDAO.getInstance().updateGames(BlackId, blackRank.getTotalMatches()+1, blackRank.getWin(), blackRank.getDraws()+1, blackRank.getLose());
		}
		
	}

}
