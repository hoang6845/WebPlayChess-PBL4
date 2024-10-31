package com.pbl4.service;

import com.pbl4.model.bean.ProfileModel;
public interface IProfileService extends IService {
    ProfileModel findByUserId(long userId);
    boolean updateProfile(ProfileModel profile);
}
