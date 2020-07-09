package com.aaa.pro.status;

/**
 * @Author project
 * @Date Create in 2020/7/9 14:44
 * @Description
 **/
public enum OperationStatus {

    // 其他操作状态码
    ZUUL_FILTER_SUCCESS("1", "路由过滤成功"),
    ZUUL_FILTER_FAILED("2", "路由过滤失败"),
    ZUUL_FILTER_TOKEN_SUCCESS("3", "token值存在"),
    ZUUL_FILTER_TOKEN_FAILED("4", "token值不存在"),
    REQUEST_IS_NULL("5", "request对象为null");

    private String code;

    private String message;

    OperationStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
