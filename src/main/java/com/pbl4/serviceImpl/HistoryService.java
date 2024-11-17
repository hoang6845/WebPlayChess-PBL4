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
        ArrayList<HistoryModel> result1= HistoryDAO.getInstance().findAllByWhiteId(playerId);
        ArrayList<HistoryModel> result2= HistoryDAO.getInstance().findAllByBlackId(playerId);
        ArrayList<HistoryModel> finalResult = new ArrayList<HistoryModel>();
        int index1=0,index2=0;
        while (index1<result1.size()&&index2<result2.size()) {
        	if (result1.get(index1).getCreateDate().compareTo(result2.get(index2).getCreateDate())>0) {
    			result1.get(index1).setEloChange(result1.get(index1).calculateEloChange(playerId));
    			result1.get(index1).setResult(result1.get(index1).getResult(playerId));
    			finalResult.add(result1.get(index1));
    			index1++;
    		}else if (result1.get(index1).getCreateDate().compareTo(result2.get(index2).getCreateDate())<0){
    			result2.get(index2).setEloChange(result2.get(index2).calculateEloChange(playerId));
    			result2.get(index2).setResult(result2.get(index2).getResult(playerId));
    			finalResult.add(result2.get(index2));
    			index2++;
    		}else {
    			if (result1.get(index1).getId()>result2.get(index2).getId()) {
    				result1.get(index1).setEloChange(result1.get(index1).calculateEloChange(playerId));
    				result1.get(index1).setResult(result1.get(index1).getResult(playerId));
        			finalResult.add(result1.get(index1));
        			index1++;
    			}else {
    				result2.get(index2).setEloChange(result2.get(index2).calculateEloChange(playerId));
    				result2.get(index2).setResult(result2.get(index2).getResult(playerId));
        			finalResult.add(result2.get(index2));
        			index2++;
    			}
    		}
        }
        while (index1<result1.size()) {
        	result1.get(index1).setEloChange(result1.get(index1).calculateEloChange(playerId));
        	result1.get(index1).setResult(result1.get(index1).getResult(playerId));
			finalResult.add(result1.get(index1));
			index1++;
        }
        while (index2<result2.size()) {
        	result2.get(index2).setEloChange(result2.get(index2).calculateEloChange(playerId));
        	result2.get(index2).setResult(result2.get(index2).getResult(playerId));
			finalResult.add(result2.get(index2));
			index2++;
        }
        return finalResult;
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
