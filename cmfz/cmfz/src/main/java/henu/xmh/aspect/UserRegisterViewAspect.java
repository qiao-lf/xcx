package henu.xmh.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import henu.xmh.annotation.UserRegister;
import henu.xmh.pojo.MapVo;
import henu.xmh.service.CfUserService;
import io.goeasy.GoEasy;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
@Slf4j
@Aspect
@Configuration
public class UserRegisterViewAspect {
    @Autowired
    private CfUserService cfUserService;

    /**
     * 当用户的数量发生变化时，发送通知到实时图表（趋势图,用户分布图）
     * @param proceedingJoinPoint
     * @return
     */

    @Around("@annotation(henu.xmh.annotation.UserRegister)")
    public Object publish(ProceedingJoinPoint proceedingJoinPoint){
        //获取注解信息
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        UserRegister annotation = methodSignature.getMethod().getAnnotation(UserRegister.class);//获取到方法上面的UserRegister注解
        String value = annotation.value();//获取注解的值
        try {
            Object proceed = proceedingJoinPoint.proceed();
            //执行成功
            System.out.println("用户数量变化："+annotation);
            //准备通知数据
            List<MapVo> countUserLocation = cfUserService.findCountUserLocation();
            Map<String, Object> registerDevelopment = cfUserService.findRegisterDevelopment();
            String countUserLocationJsonStr = JSON.toJSONString(countUserLocation);//将数据转换成JSON字符串
            String registerDevelopmentStr = JSON.toJSONString(registerDevelopment);//将用户的注册分布数据转换成JSON数据
            //发送通知
            GoEasy goEasy = new GoEasy( "rest-hangzhou.goeasy.io", "BC-6a103e6f384541fabea52ff5f802b2ed");
            //将对应的JSON字符串信息推送到对应的通道（频道）
            goEasy.publish("cmfzLocation",countUserLocationJsonStr );
            goEasy.publish("cmfzDevelopment",registerDevelopmentStr );
            log.info("用户图表信息实时推送成功！");
            return proceed;//方法放行
        } catch (Throwable throwable) {//执行失败
            throwable.printStackTrace();
            return  null;
        }

    }
}
