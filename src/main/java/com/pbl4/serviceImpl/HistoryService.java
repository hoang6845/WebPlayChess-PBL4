package com.pbl4.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import com.pbl4.model.DAOImpl.HistoryDAO;
import com.pbl4.model.DAOImpl.RankDAO;
import com.pbl4.model.bean.HistoryModel;
import com.pbl4.service.IHistoryService;

public class HistoryService implements IHistoryService {
    public static HistoryService getInstance() {
        return new HistoryService();
    }


    @Override
    public ArrayList<HistoryModel> findAllByPlayerId(long playerId) {    	
        ArrayList<HistoryModel> result= HistoryDAO.getInstance().findAllByPlayerId(playerId);
        for (HistoryModel item: result) {
        	item.setEloChange(item.calculateEloChange(playerId));
        	
        }
        return result;
    }

    @Override
    public void insert(HistoryModel history) {
    	int k=40;
    	double A = RankService.getInstance().findByUserId(history.getBlackId()).getElo();
    	double B = RankService.getInstance().findByUserId(history.getWhiteId()).getElo();
    	double mmrA = A;
    	double mmrB = B;
    	if (mmrA<mmrB) {
    		double temp= mmrA;
    		mmrA=mmrB;
    		mmrB=temp;
    	}
    	double E = 1.0 / (1.0 + Math.pow(10, (mmrB - mmrA) / 400));
    	int elo=(int)((int)k*E);
    	if (history.getResult().equals("win")) {
    		history.setEloChange(elo);
    		RankService.getInstance().UpdateAfterGames(history.getWhiteId(), history.getBlackId(), history.getResult(), elo);
    	}
    	else if (history.getResult().equals("lose")) {
    		history.setEloChange(-elo);
    		RankService.getInstance().UpdateAfterGames(history.getWhiteId(), history.getBlackId(), history.getResult(), elo);
		}
    	else {
    		history.setEloChange(0);
    		RankService.getInstance().UpdateAfterGames(history.getWhiteId(), history.getBlackId(), history.getResult(), elo);
    	}
    	history.setCreateDate(new Date(System.currentTimeMillis()));
    	HistoryDAO.getInstance().insert(history); // Gọi phương thức chèn từ HistoryDAO
    }
}
