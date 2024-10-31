package com.pbl4.service;

import java.util.ArrayList;

import com.pbl4.model.bean.HistoryModel;

public interface IHistoryService extends IService{
	ArrayList<HistoryModel> findAllByPlayerId(long playerId);
	void insert(HistoryModel history);
}
