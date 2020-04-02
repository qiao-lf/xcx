package henu.xmh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 * 添加缓存的注解
 * 加在此方法上的注解将在第一次查询的时候将数据加入Redis缓存,
 * 以后的查询中将在缓存中查询数据
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AddCache {
}
