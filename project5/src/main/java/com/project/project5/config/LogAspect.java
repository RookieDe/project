package com.project.project5.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.project5.config.annotation.PassLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 * 切面日志
 * @author chenhongde
 * @ClassName LogAspect
 * @date 2020/4/2 14:30
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private boolean hasPassLogger = false;
    private boolean passResponse = true;
    private boolean passRequest = true;

    private final String POINT_CUT = "execution(public * com.project.project5.controller.*.*(..))";

    @Pointcut(POINT_CUT)
    public void pointCut(){}

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        logger.info("@Before通知执行");

        //获取连接点的签名对象
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Method realMethod = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());
        // 检查是否有PassLogger注释，有则跳过记录日志
        if (!realMethod.isAnnotationPresent(PassLogger.class)) {
            hasPassLogger = false;
        } else {
            hasPassLogger = true;
            PassLogger passLogger = realMethod.getAnnotation(PassLogger.class);
            passRequest = passLogger.passRequest();
            passResponse = passLogger.passResponse();
        }

        // 检查是否跳过记录入参，有则跳过记录日志
        if (!hasPassLogger || !passRequest) {
            //获取目标方法参数信息
            Object[] objects = joinPoint.getArgs();
            Arrays.stream(objects).forEach(el -> {
                try {
                    logger.info(OBJECT_MAPPER.writeValueAsString(objects));
                } catch (JsonProcessingException e) {
                    logger.info(Arrays.toString(objects));
                }
            });

            //aop代理对象
            Object aThis = joinPoint.getThis();
            logger.info(aThis.toString());

            //被代理对象
            Object target = joinPoint.getTarget();
            logger.info(target.toString());

            logger.info(signature.toLongString());
            logger.info(signature.toShortString());
            logger.info(signature.toString());
            //获取方法名
            logger.info(signature.getName());
            //获取声明类型名
            logger.info(signature.getDeclaringTypeName());
            //获取声明类型 方法所在类的class对象
            logger.info(signature.getDeclaringType().toString());
            //和getDeclaringTypeName()一样
            logger.info(signature.getDeclaringType().getName());

            //连接点类型
            String kind = joinPoint.getKind();
            logger.info(kind);

            //返回连接点方法所在类文件中的位置 打印异常
            SourceLocation sourceLocation = joinPoint.getSourceLocation();
            logger.info(sourceLocation.toString());
            //logger.info(sourceLocation.getFileName());
            //logger.info(sourceLocation.getLine()+"");
            logger.info(sourceLocation.getWithinType().toString());

            //返回连接点静态部分
            JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
            logger.info(staticPart.toLongString());

            //attributes可以获取request信息 session信息等
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            logger.info(request.getRequestURL().toString());
            logger.info(request.getRemoteAddr());
            logger.info(request.getMethod());
            //logger.info("请求开始>>>>>>", "请求接口地址: " + uri + ",请求类型: " + method + ",请求人id: " + adminId + ",请求参数: " + params);
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        logger.info("@Before通知结束");
    }

    /**
     * 后置返回
     *      如果第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法参数类型匹配时才能执行后置返回通知，否则不执行，
     *            参数为Object类型将匹配任何目标返回值
     */
    @AfterReturning(value = "pointCut()",returning = "result")
    public void doAfterReturningAdvice1(JoinPoint joinPoint, Result result){
        logger.info("第一个后置通知的返回值："+result);
    }

    @AfterReturning(value = "pointCut()",returning = "result")
    public void doAfterReturningAdvice2(Object result){
        // 检查是否跳过记录返回值，有则跳过记录日志
        if (!hasPassLogger || !passResponse) {
            logger.info("第二个后置通知返回的值：" + result);
        }
    }

    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *            对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Exception exception){
        logger.info(joinPoint.getSignature().getName());
        if (exception instanceof NullPointerException){
            logger.info("发生了空指针异常！！！");
        }
    }

    @After(value = "pointCut()")
    public void doAfterAdvice(JoinPoint joinPoint){
        logger.info("后置通知执行了！");
    }


    /**
     * 环绕通知：
     *   注意:Spring AOP的环绕通知会影响到AfterThrowing通知的运行,不要同时使用
     *
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
//    @Around(value = "pointCut()")
//    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
//        logger.info("@Around环绕通知:"+proceedingJoinPoint.getSignature().toString());
//        Object object = null;
//        try {
//            object = proceedingJoinPoint.proceed();
//            logger.info(object.toString());
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        logger.info("@Around环绕通知执行结束");
//        return object;
//    } 

}
