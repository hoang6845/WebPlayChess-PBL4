package com.pbl4.model.DAO;

import java.util.ArrayList;

import com.pbl4.model.bean.RankModel;
public interface IRankDAO extends DAOInterface<RankModel> {
    
    RankModel findByUserId(long userId);
    ArrayList<RankModel> getTop10Ranks();
    int findRankPosition(long userId);
    double calculateRankPercentage(long userId);
    int updateElo(long userId, int eloAfterChange);
    int updateGames(long userId,int totalMatches,int win,int draws,int lose);
    void insert(long userId);
}