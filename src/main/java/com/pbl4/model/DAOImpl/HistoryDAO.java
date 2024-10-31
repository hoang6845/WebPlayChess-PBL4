package com.pbl4.model.DAOImpl;

import java.util.ArrayList;
import com.pbl4.mapper.HistoryMapper;
import com.pbl4.mapper.RankMapper;
import com.pbl4.model.DAO.IHistoryDAO;
import com.pbl4.model.bean.HistoryModel;
import com.pbl4.model.bean.RankModel;

public class HistoryDAO extends DAOimple<HistoryModel> implements IHistoryDAO {

    public static HistoryDAO getInstance() {
        return new HistoryDAO();
    }
    @Override
    public HistoryModel findByUserId(long userId) {
        String sql = "SELECT * FROM match_history WHERE playerId = ?";
        ArrayList<HistoryModel> HList = query(sql, new HistoryMapper(), userId);
        return HList.isEmpty() ? null : HList.get(0);
    }
    @Override
    public ArrayList<HistoryModel> findAllByPlayerId(long playerId) {
        String sql = "SELECT * FROM match_history WHERE playerId = ? OR opponentId = ?";
        return query(sql, new HistoryMapper(), playerId, playerId);
    }
    @Override
    public void insert(HistoryModel history) {
    	
        String sql = "INSERT INTO match_history (playerId, opponentId, result,  eloChange ,createdate, createby, modifieddate, modifiedby) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        insert(sql, history.getPlayerId(), history.getOpponentId(), history.getResult(),history.getEloChange(),
               history.getCreateDate(), history.getCreateBy(), 
               history.getModifiedDate(), history.getModifiedBy() 
               );
    }
}
