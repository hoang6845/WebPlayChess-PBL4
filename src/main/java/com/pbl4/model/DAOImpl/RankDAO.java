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
    public RankModel getTopRankAtPosition(int rankPosition) {
        String sql = "SELECT * FROM Rank ORDER BY elo DESC LIMIT 1 OFFSET ?";
        ArrayList<RankModel> rankList = query(sql, new RankMapper(), rankPosition - 1);
        return rankList.isEmpty() ? null : rankList.get(0);
    }

}
