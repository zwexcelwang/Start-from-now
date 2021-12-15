package zui.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect //标注当前是个切面类
public class MyAspect {

//    配置前置通知
    @Before("execution(* zui.anno.*.*(..))")
    public void before(){
        System.out.println("前置增强..........");
    }

    @AfterReturning("execution(* zui.anno.*.*(..))")
    public void afterReturning(){
        System.out.println("后置增强..........");
    }

    @Around("execution(* zui.anno.*.*(..))")
    //Proceeding JoinPoint:  正在执行的连接点===切点
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前增强....");
        Object proceed = pjp.proceed();//切点方法
        System.out.println("环绕后增强....");
        return proceed;
    }

    @AfterThrowing("execution(* zui.anno.*.*(..))")
    public void afterThrowing(){
        System.out.println("异常抛出增强..........");
    }

    @After("MyAspect.pointcut()")
    public void after(){
        System.out.println("最终增强..........");
    }


    //定义切点表达式
    @Pointcut("execution(* zui.anno.*.*(..))")
    public void pointcut(){}
}
