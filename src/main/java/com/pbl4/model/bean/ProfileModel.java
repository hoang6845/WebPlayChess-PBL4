package com.pbl4.model.bean;

import java.sql.Date;

public class ProfileModel extends AbstractModel<ProfileModel> {
    private long userId;           // ID của người dùng
    private String imageOfUser;    // Ảnh đại diện của người dùng
    private String description;    // Mô tả về người dùng
    private String email;          // Email của người dùng

    public ProfileModel() {
    }

    public ProfileModel(long id, Date createDate, String createBy, Date modifiedDate, String modifiedBy, long userId, 
                        String imageOfUser, String description, String email) {
        super(id, createDate, createBy, modifiedDate, modifiedBy);
        this.userId = userId;
        this.imageOfUser = imageOfUser;
        this.description = description;
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getImageOfUser() {
        return imageOfUser;
    }

    public void setImageOfUser(String imageOfUser) {
        this.imageOfUser = imageOfUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
