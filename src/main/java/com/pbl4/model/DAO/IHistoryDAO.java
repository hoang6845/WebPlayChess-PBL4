package com.pbl4.model.DAO;

import com.pbl4.model.bean.HistoryModel;
import java.util.ArrayList;

public interface IHistoryDAO extends DAOInterface<HistoryModel> {
    ArrayList<HistoryModel> findAllByPlayerId(long playerId); 
    void insert(HistoryModel history);
}
