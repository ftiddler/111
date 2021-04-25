package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author GuoWei
 * @date 2021-3-10
 * @description
 * <p>
 *     返回数据类
 * </p>
 */
@Data
@AllArgsConstructor
public class ResultObject<T>{
    private Integer code;
    private String msg;
    private T data;

    private ResultObject() {
        this(true, ConstantSupport.SUCCESS_CODE, ConstantSupport.SUCCESS_MESSAGE);
    }

    private ResultObject(T data) {
        this(true, ConstantSupport.SUCCESS_CODE, ConstantSupport.SUCCESS_MESSAGE, data);
    }

    private ResultObject(Boolean state, Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultObject(Boolean state, Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultObject<T> success() {
        return new ResultObject<>();
    }

    public static <T> ResultObject<T> successWithData(T data) {
        return new ResultObject<T>(data);
    }

    public static <T> ResultObject<T> failWithCode(Integer code) {
        return new ResultObject<T>(false, code, null);
    }

    public static <T> ResultObject<T> failWithCodeAndMsg(Integer code, String msg) {
        return new ResultObject<T>(false, code, msg);
    }
}
