package com.pbl4.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.http.HttpServletRequest;

public class FormUtil {

	public static <T> T ToModel(Class<T> tClass,HttpServletRequest req){
		T object = null;
		 try {
			object = tClass.getDeclaredConstructor().newInstance();
			BeanUtils.populate(object, req.getParameterMap());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
}
