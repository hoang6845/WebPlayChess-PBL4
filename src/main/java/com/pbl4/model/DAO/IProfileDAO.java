package com.pbl4.model.DAO;

import com.pbl4.model.bean.ProfileModel;

public interface IProfileDAO extends DAOInterface<ProfileModel> {
    
    ProfileModel findByUserId(long userId);
    boolean updateProfile(ProfileModel profile);
    void insert(long userId, String email);
}
