package onlyfun.aop.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

/**
 * Created by seasen on 2016/1/16.
 */
public class OtherIntroduction implements IntroductionInterceptor,IOther {
    public boolean implementsInterface(Class clazz) {
        return clazz.isAssignableFrom(IOther.class);
    }

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if (implementsInterface(methodInvocation.getMethod().getDeclaringClass())) {
            return methodInvocation.getMethod().invoke(this, methodInvocation.getArguments());
        } else {
            return methodInvocation.proceed();
        }
    }
    public void doOther() {
        System.out.println("增加的职责");
    }
}
