package com.example.demo.common;

/**
 * 
 * @author lbyani
 * 统一结果封装
 */
public class ResponseData {

	private static int SUCCESS = 200000;
	private static int FAILED = 20001;
	private static int UPLOAD_SUCCESS = 20002;
	private static int UPLOAD_FAILED = 20003;//文件上传失败
	
	private String message;
	private int code;
	private int count = 0;
	private Object data = null;
	
	public ResponseData(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public ResponseData(int code, String message, int count, Object data) {
		this.code = code;
		this.message = message;
		this.count = count;
		this.data = data;
	}
	
	public static ResponseData buildOk() {
		return new ResponseData(20000, "success");
	}

	public static ResponseData buildOk(int count, Object object) {
		ResponseData responseData = ResponseData.buildOk();
		responseData.setCount(count);
		responseData.setData(object);
		return responseData;
	}

	public static ResponseData buildOk(Object object) {
		return ResponseData.buildOk(0, object);
	}

	public static ResponseData buildOk(String message) { return new ResponseData(20000, message); }
	
	public static ResponseData buildError() {
		return new ResponseData(20001,"error");
	}

	public static ResponseData buildError(String message) {
		return new ResponseData(20001, message);
	}

	public static ResponseData buildUploadFailed() {
		return new ResponseData(UPLOAD_FAILED,"文件上传失败!");
	}

	public static ResponseData buildUploadFailed(String message) {
		return new ResponseData(UPLOAD_FAILED, message);
	}
	public static ResponseData buildUploadOK(String fileUrl) {
		return new ResponseData(20000,"文件上传成功!",0, fileUrl);
	}
	
	public static ResponseData notFound() {
		return new ResponseData(404, "Not Found");
	}

	public static ResponseData badRequest() {
		return new ResponseData(400, "Bad Request");
	}

	public static ResponseData forbidden() {
		return new ResponseData(403, "Forbidden");
	}

	public static ResponseData unauthorized() {
		return new ResponseData(401, "unauthorized");
	}

	public static ResponseData serverInternalError() {
		return new ResponseData(500, "Server Internal Error");
	}

	public static ResponseData customerError() {
		return new ResponseData(1001, "customer Error");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
