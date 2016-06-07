package onlyfun.aop.namematch;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by seasen on 2016/1/11.
 */
public class LogBeforeAdvice implements MethodBeforeAdvice {
    private Logger logger =Logger.getLogger(this.getClass().getName());
    public void before(Method method,Object[] args,Object target)throws Throwable{
        logger.log(Level.INFO,"method starts..."+method);
    }
}
