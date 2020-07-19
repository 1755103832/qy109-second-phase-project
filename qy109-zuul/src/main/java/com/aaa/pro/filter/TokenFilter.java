package com.aaa.pro.filter;

import com.aaa.pro.utils.GetParamsUtils;
import com.aaa.pro.utils.PostParamsUtils;
import com.aaa.pro.utils.SendParams2ControllerUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zyb
 * @Date Create in 2020/7/19 10:44
 * @Description 过滤登录时token中的参数
 **/
@Component
public class TokenFilter extends ZuulFilter {

    /**
     * token静态常量
     */
    private static final String TOKEN = "token";

    /**
     * http://静态常量
     */
    private static final String HTTP = "http://";

    /**
     * https://静态常量
     */
    private static final String HTTPS = "https://";

    /**
     * @Author zyb
     * @Description 过滤器的类型，一共有四个类型。
     * pre：到达路由之前执行；
     * routing：到达路由的时候执行；
     * post：到达路由之后执行；
     * error：路由中抛出错误就会执行
     * @Date 2020/7/19 10:44
     * @Param []
     * @Return java.lang.String
     **/
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @Author zyb
     * @Description filterType相同，filterOrder有作用，
     * 数字越小，越先执行。（
     * 负数也是这个规则，0和-1的话，-1先执行）
     * @Date 2020/7/19 10:45
     * @Param []
     * @Return int
     **/
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @Author zyb
     * @Description 是否需要启用过滤器，true启用，false不启用。
     * 在这里判断请求头中是否带有token，如果没有则return false，如果有则true
     * @Date 2020/7/19 10:46
     * @Param []
     * @Return boolean
     **/
    @Override
    public boolean shouldFilter() {
        // zuul无论如何都不能获取到路由中所传递的参数，必须要从请求头里获取信息
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String params = GetParamsUtils.getParams(request);
        // 判断里边是不是传了token
        if (TOKEN.contains(params) && (HTTP.contains(params) || HTTPS.contains(params))) {
            // 确定里边传了token
            return true;
        }
        // 确定没有传token，则不需要验证
        SendParams2ControllerUtils.sendParams(PostParamsUtils.postParams(currentContext), currentContext, request);
        return false;
    }

    /**
     * @Author zyb
     * @Description 过滤的业务逻辑具体细节
     * @Date 2020/7/19 11:41
     * @Param []
     * @Return java.lang.Object
     **/
    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
