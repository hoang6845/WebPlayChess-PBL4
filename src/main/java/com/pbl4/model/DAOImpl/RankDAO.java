package com.pbl4.model.DAOImpl;

import java.util.ArrayList;

import com.pbl4.mapper.RankMapper;
import com.pbl4.model.DAO.IRankDAO;
import com.pbl4.model.bean.RankModel;

public class RankDAO extends DAOimple<RankModel> implements IRankDAO {
    
    public static RankDAO getInstance() {
        return new RankDAO();
    }

    @Override
    public RankModel findByUserId(long userId) {
        String sql = "SELECT * FROM Rank WHERE userId = ?";
        ArrayList<RankModel> rankList = query(sql, new RankMapper(), userId);
        return rankList.isEmpty() ? null : rankList.get(0);
    }
    @Override
    public ArrayList<RankModel> getTop10Ranks() {
        String sql = "SELECT TOP 10 * FROM Rank " +
                     "ORDER BY elo DESC";
        ArrayList<RankModel> rankList = query(sql, new RankMapper());
        return rankList;
    }
    @Override
    public int findRankPosition(long userId) {
		StringBuilder sql =  new StringBuilder("Select * from Rank");
		sql.append(" Where elo>");
		sql.append("(select elo from rank where userId=?)");
		ArrayList<RankModel> arr = query(sql.toString(), new RankMapper(),userId );
		return arr.size()+1;
    }

    @Override
    public double calculateRankPercentage(long userId) {
        int mytopRank = findRankPosition(userId);
        String sql = "SELECT COUNT(*) FROM rank WHERE userId IN (SELECT id FROM userr WHERE idRole = 2)";
        long allRank = countItems(sql);
        return (double) mytopRank / allRank * 100;
    }

    
}