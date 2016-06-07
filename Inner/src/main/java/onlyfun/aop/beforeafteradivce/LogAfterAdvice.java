package onlyfun.aop.beforeafteradivce;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by seasen on 2016/1/11.
 */
public class LogAfterAdvice implements AfterReturningAdvice {
    static String temp;
    private Logger logger = Logger.getLogger(temp=this.getClass().getName());
    public void afterReturning(Object object,Method method,Object[] args,Object target)throws Throwable{
        System.out.println(temp);
        logger.log(Level.INFO,"method ends..."+method);
    }
}
