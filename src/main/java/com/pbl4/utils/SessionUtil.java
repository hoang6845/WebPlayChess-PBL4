package com.pbl4.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {

	private static SessionUtil sessionUtil = null;
	
	
	public static SessionUtil getInstance() {
		if (sessionUtil == null) {
			sessionUtil = new SessionUtil();
		}
		return sessionUtil;
	}
	public void putValue(HttpServletRequest req, String key, Object value){
		req.getSession().setAttribute(key, value);
	}
	
	public Object getValue(HttpServletRequest req, String key) {
		return req.getSession().getAttribute(key);
	}
	
	public void removeValue(HttpServletRequest req, String key) {
		if (req.getSession().getAttribute(key)!=null) {
			req.getSession().removeAttribute(key);	
		}else {
			System.out.println("SESSIONUNTIL removeValue lỗi");
		}
	}
	
	
	public void putValueBySession(HttpSession session,String key, Object value) {
		if (session != null) {
			session.setAttribute(key, value);
			System.out.println("Set "+key+" thành công ở session "+session );
		}
		else System.out.println("bi null roi nhe");
	}
}
