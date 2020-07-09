package com.aaa.pro.status;

/**
 * @Author project
 * @Date Create in 2020/7/8 15:16
 * @Description
 **/
public enum CrudStatus {

    // 增删改查状态码
    INSERT_SUCCESS("203", "添加成功"),
    INSERT_FAILED("407", "添加失败"),
    DELETE_SUCCESS("204", "删除成功"),
    DELETE_FAILED("408", "删除失败"),
    UPDATE_SUCCESS("205", "更新成功"),
    UPDATE_FAILED("409", "更新失败"),
    QUERY_SUCCESS("206", "查询成功"),
    QUERY_FAILED("410", "查询失败");

    private String code;
    private String message;

    CrudStatus(String code, String message) {
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
