package com.aaa.pro.base;

import static com.aaa.pro.status.LoginStatus.*;
import static com.aaa.pro.status.CrudStatus.*;

/**
 * @Author zyb
 * @Date Create in 2020/7/8 14:51
 * @Description 统一controller
 * 也就是说所有的controller都需要继承这个controller，进行统一返回
 * <p>
 * eg:
 * 登录成功和失败
 * code:200 msg:登录成功
 * code:400 msg:登录失败，系统异常
 * code:201 msg:用户已经存在
 * code:401 msg:用户不存在
 * code:402 msg:密码错误
 * code:405 msg:用户退出异常
 **/
public class BaseController {

    /**
     * @Author zyb
     * @Description 登录成功，使用系统消息
     * @Date 2020/7/8 14:54
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData loginSuccess() {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMessage(LOGIN_SUCCESS.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 登录成功，自定义返回消息
     * @Date 2020/7/8 14:55
     * @Param [message]
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData loginSuccess(String message) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMessage(message);
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 登录成功，返回数据信息，使用系统消息
     * @Date 2020/7/8 14:58
     * @Param [data]
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData loginSuccess(Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMessage(LOGIN_SUCCESS.getMessage());
        resultData.setData(data);
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 登录成功, 返回数据信息，自定义消息
     * @Date 2020/7/8 15:00
     * @Param [message, data]
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData loginSuccess(String message, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMessage(message);
        resultData.setData(data);
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 登录失败，使用系统消息
     * @Date 2020/7/8 15:02
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData loginFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMessage(LOGIN_FAILED.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 登录失败，使用系统消息，详细解释说明
     * @Date 2020/7/8 15:04
     * @Param [detail]
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData loginFailed(String detail) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMessage(LOGIN_FAILED.getMessage());
        resultData.setDetail(detail);
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 用户已经存在，使用系统消息
     * @Date 2020/7/8 15:49
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData userExist() {
        ResultData resultData = new ResultData();
        resultData.setCode(USER_EXIST.getCode());
        resultData.setMessage(USER_EXIST.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 用户不存在，使用系统消息
     * @Date 2020/7/8 15:50
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData userNotExist() {
        ResultData resultData = new ResultData();
        resultData.setCode(USER_NOT_EXIST.getCode());
        resultData.setMessage(USER_NOT_EXIST.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 密码输入错误，使用系统消息
     * @Date 2020/7/8 15:51
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData passwordWrong() {
        ResultData resultData = new ResultData();
        resultData.setCode(PASSWORD_WRONG.getCode());
        resultData.setMessage(PASSWORD_WRONG.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 用户退出异常，使用系统消息
     * @Date 2020/7/8 15:52
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData logoutWrong() {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGOUT_WRONG.getCode());
        resultData.setMessage(LOGOUT_WRONG.getMessage());
        return resultData;
    }

    // TODO: 2020/7/8 seven code here

    /**
     * @Author zyb
     * @Description 添加数据成功，使用系统消息
     * @Date 2020/7/8 15:38
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData insertSuccess() {
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_SUCCESS.getCode());
        resultData.setMessage(INSERT_SUCCESS.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 添加数据失败，使用系统消息
     * @Date 2020/7/8 15:40
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData insertFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_FAILED.getCode());
        resultData.setMessage(INSERT_FAILED.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 删除数据成功，使用系统消息
     * @Date 2020/7/8 15:41
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData deleteSuccess() {
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_SUCCESS.getCode());
        resultData.setMessage(DELETE_SUCCESS.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 删除数据失败，使用系统消息
     * @Date 2020/7/8 15:42
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData deleteFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_FAILED.getCode());
        resultData.setMessage(DELETE_FAILED.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 修改数据成功，使用系统消息
     * @Date 2020/7/8 15:43
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData updateSuccess() {
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_SUCCESS.getCode());
        resultData.setMessage(UPDATE_SUCCESS.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 修改数据失败，使用系统消息
     * @Date 2020/7/8 15:44
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData updateFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_FAILED.getCode());
        resultData.setMessage(UPDATE_FAILED.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 查询数据成功，使用系统消息
     * @Date 2020/7/8 15:45
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData querySuccess() {
        ResultData resultData = new ResultData();
        resultData.setCode(QUERY_SUCCESS.getCode());
        resultData.setMessage(QUERY_SUCCESS.getMessage());
        return resultData;
    }

    /**
     * @Author zyb
     * @Description 查询数据失败，使用系统消息
     * @Date 2020/7/8 15:46
     * @Param []
     * @Return com.aaa.pro.base.ResultData
     **/
    protected ResultData queryFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(QUERY_FAILED.getCode());
        resultData.setMessage(QUERY_FAILED.getMessage());
        return resultData;
    }
}
