package com.pbl4.model.DAOImpl;

import java.util.ArrayList;
import com.pbl4.mapper.HistoryMapper;
import com.pbl4.model.DAO.IHistoryDAO;
import com.pbl4.model.bean.HistoryModel;

public class HistoryDAO extends DAOimple<HistoryModel> implements IHistoryDAO {

    public static HistoryDAO getInstance() {
        return new HistoryDAO();
    }
    @Override
    public ArrayList<HistoryModel> findAllByPlayerId(long playerId) {
        String sql = "SELECT * FROM history WHERE whiteId = ? OR blackId = ? ORDER BY createdate DESC";
        return query(sql, new HistoryMapper(), playerId, playerId);
    }
    @Override
    public void insert(HistoryModel history) {
    	
        String sql = "INSERT INTO history (playerId, opponentId, result,  eloChange ,createdate, createby, modifieddate, modifiedby) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        insert(sql, history.getWhiteId(), history.getBlackId(), history.getResult(),history.getEloChange(),
               history.getCreateDate(), history.getCreateBy(), 
               history.getModifiedDate(), history.getModifiedBy() 
               );
    }
}
