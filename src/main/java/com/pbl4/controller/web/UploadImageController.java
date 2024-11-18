package com.pbl4.controller.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbl4.Cloudinary.CloudinaryUploader;
import com.pbl4.model.bean.UserModel;
import com.pbl4.serviceImpl.ProfileService;
import com.pbl4.utils.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = { "/uploadFile" })
@MultipartConfig
public class UploadImageController extends HttpServlet {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Part> parts = request.getParts();
        System.out.println("parts:"+parts);
        for (Part part : parts) {
            if (part.getSubmittedFileName() != null&&part.getName().equals("avatar")) {
                // Đây là phần tử tệp
                String fileName = part.getSubmittedFileName();
            	CloudinaryUploader upload = new CloudinaryUploader();
            	File temp = createTempFile(part);
            	String imgurl= upload.uploadImage(temp);
                System.out.println("File name: " + imgurl);
                UserModel model = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
                ProfileService.getInstance().updateImg(model.getId(), imgurl);
                model.setAvatar(imgurl);
                SessionUtil.getInstance().putValue(request, "USERMODEL", model);
                ObjectMapper obj = new ObjectMapper();
                Map<String , String > respData = new HashMap<String, String>();
                respData.put("result", "success");
                obj.writeValue(response.getOutputStream(), respData);
                break;
            } else {
                System.out.println("Tải ảnh thất bại");
            }
        }
        

    }
    // Tạo một tệp tạm từ Part
    private File createTempFile(Part part) throws IOException {
        // Tạo tệp tạm để lưu tạm tệp từ Part
        String fileName = getFileName(part);
        File tempFile = new File(System.getProperty("java.io.tmpdir"), fileName);

        try (InputStream input = part.getInputStream();
             OutputStream output = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
        return tempFile;
    }
    
    // Lấy tên tệp từ Part
    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf("=") + 2, cd.length() - 1);
            }
        }
        return null;
    }
}
