package com.pbl4.Cloudinary;

import java.io.File;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(CloudinaryConfig.getApiKey());
		CloudinaryUploader upload = new CloudinaryUploader();
		String img="C:\\Users\\ASUS-PR0\\Downloads\\defaultAvatar.png";
		String imgurl= upload.uploadImage(new File(img));
		if (imgurl != null) {
            System.out.println("Image uploaded successfully! URL: " + imgurl);
        } else {
            System.out.println("Upload failed.");
        }
	}

}
