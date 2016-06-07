package onlyfun.aop.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by seasen on 2016/1/16.
 */
public class Deledating extends DelegatingIntroductionInterceptor implements ILockable{
    private boolean locked;

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        if(isLocked()&&mi.getMethod().getName().indexOf("set")==0){
            System.out.println("对象被锁定");
            throw new AopConfigException("对象被锁定");
        }
        return super.invoke(mi);
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }

    public boolean isLocked() {
        return locked;
    }
}
