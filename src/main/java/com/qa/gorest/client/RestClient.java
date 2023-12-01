package com.qa.gorest.client;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.FrameworkException.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

//	public static final String BASE_URI = "https://gorest.co.in";
//	public static final String BEARER_TOKEN = "b75921753141d75b2b51d40b4db111c8e9ec7b480969270d58293e4b2cb129ca";

	private static RequestSpecBuilder specBuilder;
	private Properties prop;
	private String baseURI;
	private boolean isAutherizationHeaderAdded = false;
	public RestClient(Properties prop, String baseURI) {
		specBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
	}

	private void setRequestContentType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;

		default:
			System.out.println("plz pass correct content type");
			throw new APIFrameworkException("INVALID_CONTENT_TYPE");

		}
	}

	private void addAuthorizationHeader() {
		if(!isAutherizationHeaderAdded) {
		specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
		isAutherizationHeaderAdded = true;
		}
	}

	private RequestSpecification createRequestSpec() {
		specBuilder.setBaseUri(baseURI);
		addAuthorizationHeader();
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap) {
		specBuilder.setBaseUri(baseURI);
		addAuthorizationHeader();
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryparams) {
		specBuilder.setBaseUri(baseURI);
		addAuthorizationHeader();
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (queryparams != null) {
			specBuilder.addQueryParams(queryparams);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType) {
		specBuilder.setBaseUri(baseURI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	private RequestSpecification createRequestSpec(Object requestBody, String contentType,Map<String,String>headersMap) {
		specBuilder.setBaseUri(baseURI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		if (headersMap != null) {
			specBuilder.setBody(headersMap);
		}
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	//HTTP method util
	public Response get(String serviceUrl, boolean log) {
		if(log) {
		return RestAssured.given(createRequestSpec()).log().all()
		.when()
		.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec()).when().get(serviceUrl);
		
	}
	public Response get(String serviceUrl, Map<String, String> headersMap, boolean log) {
		if(log) {
		return RestAssured.given(createRequestSpec(headersMap)).log().all()
		.when()
		.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap)).when().get(serviceUrl);
		
	}
	public Response get(String serviceUrl, Map<String, String> queryparams, Map<String, String> headersMap, boolean log) {
		if(log) {
		return RestAssured.given(createRequestSpec(headersMap, queryparams)).log().all()
		.when()
		.get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap,queryparams)).when().get(serviceUrl);
		
	}
	public Response post(String serviceUrl, String contentType, Object requestBody,Map<String,String>headersMap,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap)).log().all()
			.when()
			.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap))
				.when()
				.post(serviceUrl);
	}
	public Response post(String serviceUrl, String contentType, Object requestBody,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType)).log().all()
			.when()
			.post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType))
				.when()
				.post(serviceUrl);
	}
	
	public Response put(String serviceUrl, String contentType, Object requestBody,Map<String,String>headersMap,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap)).log().all()
			.when()
			.put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType,headersMap))
				.when()
				.put(serviceUrl);
	}
	public Response put(String serviceUrl, String contentType, Object requestBody,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody,contentType)).log().all()
			.when()
			.put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody,contentType))
				.when()
				.put(serviceUrl);
	}
	public Response delete(String serviceUrl,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec()).log().all()
			.when()
			.delete(serviceUrl);
		}
		return RestAssured.given(createRequestSpec())
				.when()
				.delete(serviceUrl);
	}

}
