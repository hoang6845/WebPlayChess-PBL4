package com.pbl4.serviceImpl;

import java.util.ArrayList;
import com.pbl4.model.DAOImpl.HistoryDAO;
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
        	item.setOpponentName(UserService.getInstance().findUserNameById(item.getOpId(playerId)));
        }
        return result;
    }

    @Override
    public void insert(HistoryModel history) {
    	int k=40;
    	double mmrA = RankService.getInstance().findByUserId(history.getBlackId()).getElo();
    	double mmrB = RankService.getInstance().findByUserId(history.getWhiteId()).getElo();
    	if (mmrA<mmrB) {
    		double temp= mmrA;
    		mmrA=mmrB;
    		mmrB=temp;
    	}
    	double E = 1.0 / (1.0 + Math.pow(10, (mmrB - mmrA) / 400));
    	int elo=(int)((int)k*E);
    	if (history.getResult().equals("win"))
    		history.setEloChange(elo);
    	else if (history.getResult().equals("lose"))
    		history.setEloChange(-elo);
    	else history.setEloChange(0);
    	HistoryDAO.getInstance().insert(history); // Gọi phương thức chèn từ HistoryDAO
    }
}
