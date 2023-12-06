package com.qa.gorest.tests;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import com.qa.gorest.APIconstant.APIHttpStatus;
import com.qa.gorest.POJO.User;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.utils.StringUtils;

public class CreateUserTest extends BaseTest {
	
	
	@Test
	public void getAllUsersTest() {
		//post
		User user = new User("naveen", StringUtils.getrandomEmailId(), "male", "active");
		restClient = new RestClient(prop, baseURI);
		Integer userId = restClient.post(GOREST_ENDPOINT, "json", user, true)
						.then()
						.log().all()	
							.assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
								.extract().path("id");
		System.out.println("User id is "+userId);
		
		//get call
		RestClient restClient1 = new RestClient(prop, baseURI);
		restClient1.get(GOREST_ENDPOINT+"/"+userId, true)
						.then().log().all()
						  .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
							.assertThat().body("id",equalTo(userId));
	}

}
