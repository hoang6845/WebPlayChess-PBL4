package com.pbl4.serviceImpl;

import com.pbl4.model.DAOImpl.ProfileDAO;
import com.pbl4.model.bean.ProfileModel;
import com.pbl4.service.IProfileService;

public class ProfileService implements IProfileService {
	public static ProfileService getInstance() {
		return new ProfileService();
	}

	@Override
	public ProfileModel findByUserId(long userId) {
		// TODO Auto-generated method stub
		return ProfileDAO.getInstance().findByUserId(userId);
	}

	@Override
	public boolean updateProfile(ProfileModel profile) {
		// TODO Auto-generated method stub
		return ProfileDAO.getInstance().updateProfile(profile);
	}
}
