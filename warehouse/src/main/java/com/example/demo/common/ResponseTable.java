package com.example.demo.common;

/**
 * 
 * @author lbyani
 *
 * @param <T>
 */
public class ResponseTable<T> {
	
	public static int SUCCESS = 20000; //成功
	public static int TABLE_ERROR = 100;//参数错误
	public static int FAILED = 20001;//出错
	private int code;
	private String msg;
	private int count;
	private T data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public ResponseTable(int code, String msg, int count, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
	
	public ResponseTable() {}  //构造函数

	public static <T> ResponseTable<T> buildOk(int count,T t){
		return new ResponseTable<T>(SUCCESS, "", count, t);
	}
	
	public static <T> ResponseTable<T> buildSuccess(int count,String msg){
		return new ResponseTable<T>(SUCCESS, msg, count, null);
	}
	
	public static <T> ResponseTable<T> buildError(String msg){
		return new ResponseTable<T>(FAILED, msg, 0, null);
	}
}
