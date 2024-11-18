package com.pbl4.Cloudinary;

import io.github.cdimascio.dotenv.Dotenv;

public class CloudinaryConfig {
	private static Dotenv dotenv = Dotenv.load();
	
	  public static String getCloudName() {
	        return dotenv.get("CLOUDINARY_CLOUD_NAME");
	    }

	    public static String getApiKey() {
	        return dotenv.get("CLOUDINARY_API_KEY");
	    }

	    public static String getApiSecret() {
	        return dotenv.get("CLOUDINARY_API_SECRET");
	    }
	    
	    public static String getURL() {
	    	return dotenv.get("CLOUDINARY_URL");
	    }
}
