package com.apex.api.test.user;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.apex.api.test.core.ApexHttpMessageValidator;
import com.apex.api.test.core.ApexHttpUtil;
//My testing project
public class UserAPITest_NG {

	@Test(dataProvider = "dp_ids", dataProviderClass = ApexHttpUtil.class)
	public void testGetwithCorrectUserID(String ID, String Name, String email) throws ParseException, IOException {
		String url = "https://gorest.co.in/public-api/users/" + ID;
		// Msg Sending
		HttpResponse response = ApexHttpUtil.sendAndReceiveGetRequest(url);
		// step 5 : Get the HTTPResponse Status
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		// Msg getting
		String responseMsg = ApexHttpUtil.getResponseString(response);
		System.out.println(responseMsg);
		// Msg Validating
		ApexHttpMessageValidator.performBasicValidations(response, 200, "OK");
	}
	@Test // 404 is not found
	public void testGetwith404() throws ClientProtocolException, IOException  {

		String url = "https://gorest.co.in/public-api/use/";
		HttpResponse response = ApexHttpUtil.sendAndReceiveGetRequest(url);
		
		String responseMsg = ApexHttpUtil.getResponseString(response);
		System.out.println(responseMsg);
		
		ApexHttpMessageValidator.performBasicValidations(response, 404,"Not Found");	
	}
	@Test
	public void testGetwithBlankUserID() throws ClientProtocolException, IOException {
//	HttpClient client = HttpClientBuilder.create().build();
//	HttpGet requestMsg = new HttpGet("https://gorest.co.in/public-api/users/");
//	HttpResponse response = client.execute(requestMsg);
//	HttpEntity entity = response.getEntity();
//	
//	// use org.apache.http.util.EntityUtils to read json as string
//	String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
//	
//	JSONObject o = new JSONObject(json);
//	Assert.assertNotNull(o.getJSONObject("meta"));
//	int total = o.getJSONObject("meta").getJSONObject("pagination").getInt("total");
//	Assert.assertNotSame(total, 0);
		
		String url = "https://gorest.co.in/public-api/users/";
		HttpResponse response = ApexHttpUtil.sendAndReceiveGetRequest(url);
		
		String responseMsg = ApexHttpUtil.getResponseString(response);
		System.out.println(responseMsg);
		
		ApexHttpMessageValidator.performBasicValidations(response, 200,"OK");	
	}	
       @Test
    	public void testGetwithInvalidCharacters() throws ClientProtocolException, IOException {
		String url = "https://gorest.co.in/public-api/users/w3";
	    HttpResponse response = ApexHttpUtil.sendAndReceiveGetRequest(url);
	    
	    String responseMsg = ApexHttpUtil.getResponseString(response);
		System.out.println(responseMsg);

		ApexHttpMessageValidator.performBasicValidations(response, 200,"OK");
	}	
	@Test
	public void testGetwithInvalidUserID () throws ClientProtocolException, IOException {
        String url = "https://gorest.co.in/public-api/users/166";
       HttpResponse response = ApexHttpUtil.sendAndReceiveGetRequest(url);
		
		String responseMsg = ApexHttpUtil.getResponseString(response);
		System.out.println(responseMsg);

		ApexHttpMessageValidator.performBasicValidations(response, 200,"OK");
	}
	@Test
	public void testGetwithResponseHeader () throws ClientProtocolException, IOException {
//		HttpGet request = new HttpGet("https://gorest.co.in/public-api/users/");
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = httpClient.execute(request); 
//
//       HttpEntity entity = response.getEntity();
//       Header headers = entity.getContentType();
//       System.out.println(headers);
   
	}
	
//	public void testGetwithResponseCookies() {
//		
//	}

	@Test

	public void testPostwithValidData() throws ParseException, IOException {

		String url = "https://gorest.co.in/public-api/users";
		String result = ApexHttpUtil.sendandReceivePostRequest(url, "Test1234", "Male", "abcdABCD@gmail.com", "Active");
		System.out.println("The result is :" + result);

	}

	@Test
	public void testDeletewithCorrectId() throws IOException, ClientProtocolException {
		String url = "https://gorest.co.in/public-api/users/1368";
		HttpResponse response = ApexHttpUtil.sendAndReceiveDeleteRequest(url);
		System.out.println("The delete response is : " + response);
		System.out.println("The status code : " + response.getStatusLine().getStatusCode());
	}

	@Test 
	  public void testPatchwithValidData() throws ParseException,IOException{ 
		  
	  String url = "https://gorest.co.in/public-api/users/1368";
	  String result = ApexHttpUtil.sendandReceivePatchRequest(url,"deept","deep@KKJH.com","Active"); 
	  
	  System.out.println("The result is :" +result); }
	  
	  

}
