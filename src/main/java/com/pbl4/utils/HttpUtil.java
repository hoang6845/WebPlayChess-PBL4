package com.pbl4.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	
	private String value;
	
	public HttpUtil(String value) {
		this.value = value;
	}
	
	public <T> T ToModel(Class<T> tClass){
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static HttpUtil of (BufferedReader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine())!=null) {
			sb.append(line);
		}
		return new HttpUtil(sb.toString());
	}
}