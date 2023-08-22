package x.y.z.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect // 告诉Spring这是一个AOP类(需要识别AOP注解),否则会当成一个普通的Bean
public class MyAdvice {
    // 切入点定义依托一个不具有实际意义的方法进行，即无参数、无返回值、方法体无实际逻辑
    @Pointcut("execution(void x.y.z.dao.BookDao.update())")
    private void method(){}

    @Pointcut("execution(int x.y.z.dao.BookDao.select())")
    private void method2(){}

    // 前置通知
    //@Before("method()")
    public void before(){
        System.out.println("before advice");
    }

    // 前置通知(获取参数)
    //@Before("method()")
    public void before(JoinPoint jp){
        Object[] args = jp.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("before advice");
    }

    // 后置通知
    //@After("method()")
    public void after(){
        System.out.println("after advice");
    }

    // 后置通知(获取参数)
    //@After("method()")
    public void after(JoinPoint jp){
        Object[] args = jp.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("after advice");
    }

    // 环绕通知
    //@Around("method()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice");
        Object ret = pjp.proceed();
        System.out.println("around after advice");
        return ret;
    }

    // 环绕通知返回值固定为Object
    // 异常可以抛出,也可以捕获
    //@Around("method2()")
    public Object aroundSelect(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        System.out.println(signature.getDeclaringTypeName());
        System.out.println(signature.getName());
        Object[] args = pjp.getArgs();
        System.out.println(Arrays.toString(args));
        // Object ret = pjp.proceed(args);
        System.out.println("around before advice");
        Object ret = pjp.proceed(); // 调用原始方法
        System.out.println("around after advice");
        return ret; // 原始方法结果返回
    }

    // 只有方法正常结束后才会执行(后置通知无论方法是否正常结束都会执行)
    //@AfterReturning("method2()")
    public void afterReturning(){
        System.out.println("afterReturning advice");
    }

    // 获取返回值,并把返回值装入ret中
    // 如果有JoinPoint参数,JoinPoint必须为第一个参数
    //@AfterReturning(value = "method2()",returning = "ret")
    public void afterReturning(JoinPoint jp, Object ret){
        System.out.println("afterReturning advice");
    }

    // 只有方法抛出异常时才会执行
    //@AfterThrowing("method2()")
    public void afterThrowing(){
        System.out.println("afterThrowing advice");
    }

    // 获取异常,并把异常装入t中
    // 如果有JoinPoint参数,JoinPoint必须为第一个参数
    // @AfterThrowing(value = "method2()",throwing = "t")
    public void afterThrowing(Throwable t){
        System.out.println("afterThrowing advice");
    }
}
