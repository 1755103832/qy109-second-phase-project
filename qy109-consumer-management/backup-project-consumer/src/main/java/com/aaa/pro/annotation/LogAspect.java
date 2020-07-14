package com.aaa.pro.annotation;

import com.aaa.pro.model.User;
import com.aaa.pro.service.IProjectService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author zyb
 * @Date Create in 2020/7/14 15:20
 * @Description
 **/
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author zyb
     * @Description 定义一个切面
     * 你们之前学过的切面一般的情况下切类，还可以使用通配符，或者切整个包
     * com.aaa.*.controller
     * 所以今天要切这个咱们自定义的注解
     * <p>
     * 也就是说当检测到这个注解存在的时候，aop才会生效
     * @Date 2020/7/14 15:24
     * @Param []
     * @Return void
     **/
    @Pointcut(value = "@annotation(LoginLogAnnotation))")
    public void pointcut() {

    }

    /**
     * @Author zyb
     * @Description 定义环形切点
     * ProceedingJoinPoint:
     * 里面封装了目标路径中的所有参数
     * 可以任意获取
     * @Date 2020/7/14 15:32
     * @Param [proceedingJoinPoint]
     * @Return java.lang.Object
     **/
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        // 定义返回值
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        // 获取username信息,其实username信息在方法的参数中，也就是说只要获取到了目标方法的参数就能拿到username的值
        Object[] args = proceedingJoinPoint.getArgs();
        return null;
    }
}
