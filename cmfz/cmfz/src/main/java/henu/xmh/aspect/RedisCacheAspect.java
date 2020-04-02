package henu.xmh.aspect;

import henu.xmh.util.SerializeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author XiaMH
 */
@Aspect
@Configuration
public class RedisCacheAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加缓存和使用缓存查询的方法
     * 将addCache注解添加到要使用缓存的方法上
     * 获取调用触发此切面的类名作为缓存hash的大key
     * 获取方法名+参数作为小key
     * 将查询的返回数据作为缓存数据存为value
     * @param proceedingJoinPoint
     * @return
     */
    @Around("@annotation(henu.xmh.annotation.AddCache)")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取方法名
        String name = proceedingJoinPoint.getSignature().getName();
        //获取类名（作为cache的（hash）大key）
        Class declaringType = proceedingJoinPoint.getSignature().getDeclaringType();
        //获取参数数组
        String key = name + " : ";
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            //将方法名+参数拼接成key
            System.out.println(arg);
            key+= ","+arg;
        }
        //redis操作hash的api
        HashOperations hashOperations = redisTemplate.opsForHash();
        Object o = hashOperations.get(declaringType.toString(),key);
        if(o==null){
            //添加返回数据为缓存
            Object proceed = proceedingJoinPoint.proceed();
            System.out.println("添加缓存数据："+declaringType.toString()+","+key+":"+proceed);
            hashOperations.put(declaringType.toString(),key,proceed);
            return proceed;
        }else {
            //将缓存数据返回
            System.out.println("查询缓存数据："+declaringType.toString()+","+key+":"+o);
            return o;
        }
    }

    /**
     * 删除缓存的方法,在该实体数据调用数据的写操作时（增，删，改），将该实体数据缓存删除
     * @param proceedingJoinPoint
     * @return
     */
    @Around("@annotation(henu.xmh.annotation.DelCache)")
    public Object delCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取类名（作为cache的（hash）大key）
        Class declaringType = proceedingJoinPoint.getSignature().getDeclaringType();
        //redis操作hash的api，清空缓存
        redisTemplate.delete(declaringType.toString());
        System.out.println("删除缓存数据:"+proceedingJoinPoint.getSignature().getDeclaringType().toString());
        //放行该方法将数据返回
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }

}
