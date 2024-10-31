package com.pbl4.model.DAOImpl;

import com.pbl4.model.DAOImpl.ProfileDAO;
import com.pbl4.model.bean.ProfileModel;
import java.sql.Date;

public class test {
    public static void main(String[] args) {
    	int id = 1;
       ProfileModel a = ProfileDAO.getInstance().findByUserId(id);
       System.out.print(a.getEmail());
       a.setEmail("cc.hahah./cac");
       ProfileDAO.getInstance().updateProfile(a);
       System.out.print(a.getEmail());
    }
    
}
