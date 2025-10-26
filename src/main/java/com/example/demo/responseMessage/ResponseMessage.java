package com.example.demo.responseMessage;

import lombok.Data;

@Data
public class ResponseMessage {

	private Integer statusCode;
	private String message;
	private String status;
	private Object data;

	public ResponseMessage(Integer statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;

	}

	public ResponseMessage(Integer statusCode, String message, Object data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public ResponseMessage(Integer statusCode, String message, String status, Object data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.status = status;
		this.data = data;
	}

}
