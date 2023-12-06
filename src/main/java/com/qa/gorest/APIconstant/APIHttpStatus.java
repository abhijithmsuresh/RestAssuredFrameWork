package com.qa.gorest.APIconstant;
//abhijith===
public enum APIHttpStatus {
	
	OK_200(200,"OK"),
	CREATED_201(201,"CREATED"),
	NO_CREATED_204(204,"NO_CONTENT"),
	BAD_REQUEST_400(400,"BAD_REQUEST"),
	UNAUTHORIZED_401(401,"UNAUTHORIZED"),
	FORBIDDEN_403(403,"FORBIDDEN"),
	NOT_FOUND_404(404,"NOT FOUND"),
	INTERNAL_SERVER_ERROR_500(500,"INTERNAL SERVER ERROR");
	
	private final int code;
	private final String message;
	
	private APIHttpStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	 public String toString()
	 {
		 return code+" "+message;
	 }
}
