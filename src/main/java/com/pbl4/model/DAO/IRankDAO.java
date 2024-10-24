package com.pbl4.model.DAO;

import java.util.ArrayList;
import com.pbl4.model.bean.RankModel;

public interface IRankDAO extends DAOInterface<RankModel> {
    
    RankModel findByUserId(long userId);
    RankModel getTopRankAtPosition(int rankPosition);
}
