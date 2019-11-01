package com.ccx.common.lang.base;

public class ResponseUtils {

    private static final String CODE_SUCCESS = "0";
    private static final String CODE_ERROR = "-1";

    private static final String MESSAGE_SUCCESS = "SUCCESS";
    private static final String MESSAGE_ERROR = "ERROR";

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<T>(Boolean.TRUE, CODE_SUCCESS, MESSAGE_SUCCESS, null);
    }

    public static <T> BaseResponse<T> success(String message) {
        return new BaseResponse<T>(Boolean.TRUE, CODE_SUCCESS, message, null);
    }

    public static <T> BaseResponse<T> success(T attributes) {
        return new BaseResponse<T>(Boolean.TRUE, CODE_SUCCESS, MESSAGE_SUCCESS, attributes);
    }

    public static <T> BaseResponse<T> success(String code, String message) {
        return new BaseResponse<T>(Boolean.TRUE, code, message, null);
    }

    public static <T> BaseResponse<T> success(String message, T attributes) {
        return new BaseResponse<T>(Boolean.TRUE, CODE_SUCCESS, message, attributes);
    }

    public static <T> BaseResponse<T> success(String code, String message, T attributes) {
        return new BaseResponse<T>(Boolean.TRUE, code, message, attributes);
    }

    public static <T> BaseResponse<T> error() {
        return new BaseResponse<T>(Boolean.FALSE, CODE_ERROR, MESSAGE_ERROR, null);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<T>(Boolean.FALSE, CODE_ERROR, message, null);
    }

    public static <T> BaseResponse<T> error(T attributes) {
        return new BaseResponse<T>(Boolean.FALSE, CODE_ERROR, MESSAGE_ERROR, attributes);
    }

    public static <T> BaseResponse<T> error(String code, String message) {
        return new BaseResponse<T>(Boolean.FALSE, code, message, null);
    }

    public static <T> BaseResponse<T> error(String message, T attributes) {
        return new BaseResponse<T>(Boolean.FALSE, CODE_ERROR, message, attributes);
    }

    public static <T> BaseResponse<T> error(String code, String message, T attributes) {
        return new BaseResponse<T>(Boolean.FALSE, code, message, attributes);
    }

}
