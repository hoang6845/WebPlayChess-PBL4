package com.pbl4.serviceImpl;

import com.pbl4.model.bean.UserModel;

import java.util.ArrayList;

import com.pbl4.model.bean.HistoryModel;
import com.pbl4.model.bean.ProfileModel;
public class test {

    public static void main(String[] args) {
		/*
		 * UserService userService = UserService.getInstance(); ProfileService hehe =
		 * ProfileService.getInstance(); // Thay đổi ID người dùng tương ứng String
		 * scription ="cacac 123123t";
		 * 
		 * long userIdToUpdate = 1;
		 * 
		 * // Test đổi tên String newName = "New Name"; boolean isNameUpasd =
		 * hehe.updateScription(2, scription); boolean isNameUpdated =
		 * userService.updateName(userIdToUpdate, newName);
		 * System.out.println("Update Name: " + isNameUpasd );
		 * 
		 * // Test đổi mật khẩu String newPassword = "NewPassword123"; // Mật khẩu mới
		 * boolean isPasswordUpdated = userService.updatePassword(userIdToUpdate,
		 * newPassword); System.out.println("Update Password: " + (isPasswordUpdated ?
		 * "Success" : "Failed"));
		 * 
		 * // Test mã hóa mật khẩu String plainPassword = "123456"; String
		 * hashedPassword = userService.hashPassword(plainPassword);
		 * System.out.println("Plain Password: " + plainPassword);
		 * System.out.println("Hashed Password: " + hashedPassword);
		 * 
		 * // Mã hóa lại để kiểm tra String hashed1Password =
		 * userService.hashPassword(plainPassword);
		 * System.out.println("Hashed Password (Second Time): " + hashed1Password);
		 * 
		 * // Kiểm tra xem hai mật khẩu đã mã hóa có giống nhau không (chúng sẽ khác
		 * nhau) boolean isSameHash = hashedPassword.equals(hashed1Password);
		 * System.out.println("Are both hashed passwords the same? " + (isSameHash ?
		 * "Yes" : "No"));
		 */
    	ArrayList<HistoryModel> a = HistoryService.getInstance().findAllByPlayerId(1);
    	System.out.println(a.get(0).getEloChange());
    }
}
