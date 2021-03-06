package com.aaa.pro.status;

/**
 * @Author project
 * @Date Create in 2020/7/8 14:44
 * @Description
 **/
public enum LoginStatus {

    // 登录状态码
    LOGIN_SUCCESS("201", "登录成功"),
    LOGIN_FAILED("401", "登录失败,系统异常"),
    USER_EXIST("202", "用户已经存在"),
    USER_NOT_EXIST("402", "用户不存在"),
    PASSWORD_WRONG("405", "密码错误"),
    LOGOUT_WRONG("406", "用户退出异常"),
    SYSTEM_EXCEPTION("406", "系统异常"),
    LOGIN_TIMEOUT_EXIT("10006","超时自动退出，请重新登录"),

    // ftp上传状态码
    UPLOAD_SUCCESS("20005", "文件上传成功"),
    UPLOAD_FAILED("10005", "文件上传失败");

    private String code;
    private String message;

    LoginStatus(String code, String message) {
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
