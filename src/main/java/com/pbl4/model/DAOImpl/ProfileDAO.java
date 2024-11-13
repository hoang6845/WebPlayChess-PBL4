package com.pbl4.model.DAOImpl;

import java.util.ArrayList;
import com.pbl4.mapper.ProfileMapper;
import com.pbl4.model.DAO.IProfileDAO;
import com.pbl4.model.bean.ProfileModel;

public class ProfileDAO extends DAOimple<ProfileModel> implements IProfileDAO {

    public static ProfileDAO getInstance() {
        return new ProfileDAO();
    }

    @Override
    public ProfileModel findByUserId(long userId) {
        String sql = "SELECT * FROM Profile WHERE userId = ?";
        ArrayList<ProfileModel> profileList = query(sql, new ProfileMapper(), userId);
        return profileList.isEmpty() ? null : profileList.get(0);
    }
    @Override
    public boolean updateProfile(ProfileModel profile) {
        String sql = "UPDATE Profile SET imageOfUser = ?, description = ?, email = ?, createdate = ?, modifieddate = ?, createby = ?, modifiedby = ? " +
                     "WHERE userId = ?";
        return update(sql, profile.getImageOfUser(), profile.getDescription(), profile.getEmail(),
                      profile.getCreateDate(), profile.getModifiedDate(), profile.getCreateBy(), profile.getModifiedBy(), profile.getUserId()) > 0;
    }
    @Override
    public void insert(long userId, String email) {
        String sql = "INSERT INTO Profile (userId, imageOfUser, description, email, createdate) " +
                     "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
        insert(sql, userId, null, null, email);
    }
}
