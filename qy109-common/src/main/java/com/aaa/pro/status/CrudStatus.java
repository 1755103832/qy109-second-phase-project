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
    QUERY_FAILED("410", "查询失败"),
    QUERY_FIND_NOT_DATA("30010","查询不到数据"),
    QUERY_SUCCESS_DATA("20010","查询成功并且返回数据"),
    QUERY_DATA_NOT_EXIST("14002","数据不存在！"),
    QUERY_DATA_BY_ID_SUCCESS("24002","根据ID查询数据成功！"),
    QUERY_DATA_BY_ID_FAILED("14003","根据ID查询数据失败！"),
    USER_DATA_FAILED("14005","用户信息数据导出失败！"),
    USER_DATA_ERROR("14007","用户信息数据导出错误！");

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
