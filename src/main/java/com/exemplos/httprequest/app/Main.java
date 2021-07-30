package com.exemplos.httprequest.app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.exemplos.httprequest.dto.ResponseDTO;
import com.exemplos.httprequest.entity.User;
import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		try {
			httpGet();
			httpPost();
			httpPut();
			httpDelete();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void httpGet() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("https://httpbin.org/get");
		
		request.setHeader("Content-type", "application/json");
		
		HttpResponse response = client.execute(request);
		String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(responseBody);
	}

	private static void httpPost() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("https://httpbin.org/post");
		Gson gson = new Gson();
		
		request.setHeader("Content-type", "application/json");
		User user = new User("admin","123456");
		
		StringEntity stringEntity = new StringEntity(gson.toJson(user));
		request.setEntity(stringEntity);
		
		HttpResponse response = client.execute(request);
		String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
		ResponseDTO responseDto = gson.fromJson(responseBody, ResponseDTO.class);

		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(responseBody);
		System.out.println(responseDto.getJson().toString());
	}

	private static void httpPut() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPut request = new HttpPut("https://httpbin.org/put");
		Gson gson = new Gson();
		
		request.setHeader("Content-type", "application/json");
		User user = new User("admin","123456");
		
		StringEntity stringEntity = new StringEntity(gson.toJson(user));
		request.setEntity(stringEntity);
		
		HttpResponse response = client.execute(request);
		String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
		ResponseDTO responseDto = gson.fromJson(responseBody, ResponseDTO.class);

		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(responseBody);
		System.out.println(responseDto.getJson().toString());
	}

	private static void httpDelete() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpDelete request = new HttpDelete("https://httpbin.org/delete");
		
		request.setHeader("Content-type", "application/json");		

		HttpResponse response = client.execute(request);
		String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(responseBody);
	}

}
