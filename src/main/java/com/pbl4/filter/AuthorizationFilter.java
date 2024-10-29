package com.pbl4.filter;

import java.io.IOException;

import com.pbl4.SystemConstant.SystemConstant;
import com.pbl4.model.bean.UserModel;
import com.pbl4.utils.SessionUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class AuthorizationFilter implements Filter {

	private ServletContext context;
	private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
    }
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURI();
		if (url.startsWith("/chess-game/admin")) {
			System.out.println("ChecklinkAdmin"+url);
			UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL"); 
			if (model !=null) {
				System.out.print("model!=null");
				if (model.getRole().getCodeRole().equals(SystemConstant.ADMIN)) {
					chain.doFilter(request, response);
				}else if(model.getRole().getCodeRole().equals(SystemConstant.PLAYER)) {
					res.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=not_permission&alert=danger");
				}
			}else {
				res.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
			}
		}
		else {	
			System.out.println("checkLinkNormal"+url);
			//khoong co /admin thi cho vao thoai mai
			chain.doFilter(request, response);
		}
		
	}
	   public static HttpServletRequest getCurrentRequest() {
	        return currentRequest.get();
	    }

}
