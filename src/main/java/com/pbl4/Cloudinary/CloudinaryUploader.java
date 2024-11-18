package com.pbl4.Cloudinary;

import java.io.File;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryUploader {

	private Cloudinary cloudinary;
	
	public CloudinaryUploader() {
        // Cấu hình Cloudinary với thông tin từ biến môi trường
        cloudinary = new Cloudinary(CloudinaryConfig.getURL());
    }
	
    public String uploadImage(File imageFile) {
        try {
            Map uploadResult = cloudinary.uploader().upload(imageFile, ObjectUtils.emptyMap());
            return (String) uploadResult.get("url");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
