package com.pbl4.model.DAOImpl;

import java.util.ArrayList;
import com.pbl4.mapper.HistoryMapper;
import com.pbl4.mapper.HistoryMapperAndName;
import com.pbl4.model.DAO.IHistoryDAO;
import com.pbl4.model.bean.HistoryModel;

public class HistoryDAO extends DAOimple<HistoryModel> implements IHistoryDAO {

    public static HistoryDAO getInstance() {
        return new HistoryDAO();
    }
    @Override
    public ArrayList<HistoryModel> findAllByPlayerId(long playerId) {
        String sql = "SELECT history.*,userr.fullname FROM history inner join userr on history.blackId=userr.id WHERE whiteId = ? OR blackId = ? ORDER BY history.createdate DESC";
        return query(sql, new HistoryMapperAndName(), playerId, playerId);
    }
    @Override
    public void insert(HistoryModel history) {
        String sql = "INSERT INTO history (whiteId, blackId, result,  eloChange ,createdate, createby) VALUES (?, ?, ?, ?, ?, ?)";
        insert(sql, history.getWhiteId(), history.getBlackId(), history.getResult(),history.getEloChange(),
               history.getCreateDate(), history.getCreateBy()  
               );
    }
}
