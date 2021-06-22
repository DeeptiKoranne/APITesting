package com.apex.api.test.core;

import org.apache.http.HttpResponse;
import org.testng.Assert;

public class ApexHttpMessageValidator {

	public static void performBasicValidations(HttpResponse response, int httpcode, String statusMessage) {
		Assert.assertEquals(response.getStatusLine().getStatusCode(), httpcode);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), statusMessage);
	}

}
