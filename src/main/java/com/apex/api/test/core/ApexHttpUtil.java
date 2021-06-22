package com.apex.api.test.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;

public class ApexHttpUtil {
	@DataProvider(name = "dp")
	public static Object[][] getData() {
		String[][] data = { { "137", "Raj", "Raj@gmail.com" }, { "135", "test", "test@gmail.com" },
				{ "122", "ram", "ram@gmail.com" } };
		return data;
	}

	public static HttpResponse sendAndReceiveGetRequest(String URL) throws IOException, ClientProtocolException {
		// step 1 : Open the browser
		HttpClient client = HttpClientBuilder.create().build();

		// step 2 : create and send the request
		// step 3 : enter the data
		HttpGet requestMsg = new HttpGet(URL);

		// step 4 : submit/send the request and get the result
		HttpResponse response = client.execute(requestMsg);
		return response;
	}
	
	public static String getResponseString(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		String result = "";
		if (entity != null) {
			// return it as a String
			result = EntityUtils.toString(entity);
		}
		return result;
	}

	public static String sendandReceivePostRequest(String url, String name, String gender, String email, String status)
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		String result = "";
		HttpPost post = new HttpPost(url);

		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("gender", gender);
		json.put("email", email);
		json.put("status", status);

		// send a JSON data
		post.setEntity(new StringEntity(json.toString()));
		String auth = "68c13dd13ca686719047fd48d6a3dd9480aac273def3667ed1528d10051f842f";
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + auth);
		post.setHeader(HttpHeaders.ACCEPT, "application/json");
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(post);

		result = EntityUtils.toString(response.getEntity());
		System.out.println("The Result is :" + result);

		return result;
	}

	public static HttpResponse sendAndReceivePutRequest(String URL, String Message) {

		return null;
	}

	public static HttpResponse sendAndReceiveDeleteRequest(String url) throws IOException, ClientProtocolException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpDelete request = new HttpDelete(url);
		String auth = "68c13dd13ca686719047fd48d6a3dd9480aac273def3667ed1528d10051f842f";
		request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + auth);
		request.setHeader(HttpHeaders.ACCEPT, "application/json");
		request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		HttpResponse response = client.execute(request);
		System.out.println("The delete response is : " + response);
		return response;
	}

	public static String sendandReceivePatchRequest(String url, String name, String email, String status)
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		String result = "";
		HttpPatch patch = new HttpPatch(url);

		JSONObject json = new JSONObject();
		json.put("name", name);

		json.put("email", email);
		json.put("status", status);

		// send a JSON data
		patch.setEntity(new StringEntity(json.toString()));
		String auth = "68c13dd13ca686719047fd48d6a3dd9480aac273def3667ed1528d10051f842f";
		patch.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + auth);
		patch.setHeader(HttpHeaders.ACCEPT, "application/json");
		patch.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(patch);

		System.out.println("The response is : " + response.getStatusLine().getStatusCode());

		result = EntityUtils.toString(response.getEntity());
		System.out.println("The Result is :" + result);

		return result;
	}

}
