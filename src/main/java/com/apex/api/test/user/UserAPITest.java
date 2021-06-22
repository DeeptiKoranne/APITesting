package com.apex.api.test.user;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class UserAPITest {

	
	public static void main(String[] args) throws ClientProtocolException, IOException {
	
       //step 1 : Open the browser
		HttpClient client = HttpClientBuilder.create().build();
		
		//step 2 : create and send the request 
		//step 3 : enter the data
		HttpGet requestMsg = new HttpGet("https://gorest.co.in/public-api/users/137");
		
		//step 4 : submit/send the request and get the result
		HttpResponse response = client.execute(requestMsg);
		
		//step 5 :validate that test is passed
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
	
		 HttpEntity entity = response.getEntity();
         if (entity != null) {
             // return it as a String
             String result = EntityUtils.toString(entity);
             System.out.println(result);
         }
         
         if(response.getStatusLine().getStatusCode()== 200) {
        	 System.out.println("The test passed");
         } else {
        	 System.out.println("The test fail");
         }
	}
}
////HttpClient client = HttpClientBuilder.create().build();
////HttpGet requestMsg = new HttpGet("https://gorest.co.in/public-api/users/");
////HttpResponse response = client.execute(requestMsg);
////HttpEntity entity = response.getEntity();
////
////// use org.apache.http.util.EntityUtils to read json as string
////String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
////
////JSONObject o = new JSONObject(json);
////Assert.assertNotNull(o.getJSONObject("meta"));
////int total = o.getJSONObject("meta").getJSONObject("pagination").getInt("total");
////Assert.assertNotSame(total, 0);
