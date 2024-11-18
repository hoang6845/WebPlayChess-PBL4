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
        return ProfileDAO.getInstance().findByUserId(userId);
    }

    @Override
    public boolean updateProfile(ProfileModel profile) {
        return ProfileDAO.getInstance().updateProfile(profile);
    }

    @Override
    public boolean updateDescription(long userId, String newScription) {
        ProfileModel profile = findByUserId(userId);
        if (profile != null) {
            profile.setDescription(newScription);
            return ProfileDAO.getInstance().updateProfile(profile);
        }
        return false;
    }

	@Override
	public boolean updateImg(long userId, String newImg) {
		ProfileModel profile = findByUserId(userId);
        if (profile != null) {
            profile.setImageOfUser(newImg);
            return ProfileDAO.getInstance().updateProfile(profile);
        }
        return false;
	}

}
