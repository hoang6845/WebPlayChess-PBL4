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
    public HistoryModel findByUserId(long userId) {
        return HistoryDAO.getInstance().findByUserId(userId);
    }

    @Override
    public ArrayList<HistoryModel> findAllByPlayerId(long playerId) {
    	
        return HistoryDAO.getInstance().findAllByPlayerId(playerId);
    }

    @Override
    public void insert(HistoryModel history) {
    	HistoryDAO.getInstance().insert(history); // Gọi phương thức chèn từ HistoryDAO
    }
}
