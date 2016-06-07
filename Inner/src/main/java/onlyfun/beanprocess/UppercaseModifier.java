package onlyfun.beanprocess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * Created by seasen on 2016/1/10.
 */
public class UppercaseModifier implements BeanPostProcessor{
    public Object postProcessBeforeInitialization(
            Object bean,String name) throws BeansException{
        Field[] fields =bean.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().equals(String.class)){
                fields[i].setAccessible(true);
                try{
                    String original =(String)fields[i].get(bean);
                    System.out.println(original);
                    fields[i].set(bean,original.toUpperCase());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        return bean;
    }
}
