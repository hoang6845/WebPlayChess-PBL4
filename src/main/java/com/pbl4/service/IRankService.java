package com.pbl4.service;

import java.util.ArrayList;

import com.pbl4.model.bean.RankModel;

public interface IRankService extends IService {
	RankModel findByUserId(long userId);

	ArrayList<RankModel> getTop10Ranks();

	int findRankPosition(long userId);

	double calculateRankPercentage(long userId);
}
