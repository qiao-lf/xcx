package henu.xmh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 */
@Retention(RetentionPolicy.RUNTIME)//运行时
@Target(ElementType.METHOD)//加在方法上面的注解
public @interface LogAnnotation {
    String value();//value属性
}
