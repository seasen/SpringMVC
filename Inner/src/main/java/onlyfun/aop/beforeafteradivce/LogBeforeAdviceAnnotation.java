package onlyfun.aop.beforeafteradivce;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by seasen on 2016/1/16.
 */
@Aspect
public class LogBeforeAdviceAnnotation {
    private Logger logger =
            Logger.getLogger(this.getClass().getName());
    @Before("execution(* onlyfun.aop.beforeafteradivce.IHello.*(..))")
    public void before(JoinPoint join){
        logger.log(Level.INFO,"method starts..."+
                join.getSignature().getDeclaringTypeName()+"."+join.getSignature().getName());
    }
}
