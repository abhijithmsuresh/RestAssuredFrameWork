package com.qa.gorest.tests;

import org.testng.annotations.Test;
import java.util.*;

import com.qa.gorest.APIconstant.APIHttpStatus;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import static org.hamcrest.Matcher.*;
public class GetUserTest extends BaseTest {
	
	
	@Test
	public void getAllUsersTest() {
		//restClient = new RestClient(prop, baseURI);
		restClient.get(GOREST_ENDPOINT, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		
	}
	@Test
	public void getUsersTest() {
		//restClient = new RestClient(prop, baseURI);
		restClient.get(GOREST_ENDPOINT+"/"+"5372337", true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		
	}
	@Test
	public void getUsersWithQueryParamsTest() {
		//restClient = new RestClient(prop, baseURI);
		Map<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("name", "naveen");
		queryParams.put("status","active");
		restClient.get(GOREST_ENDPOINT+"/", queryParams, null, false)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		
	}
	

}
