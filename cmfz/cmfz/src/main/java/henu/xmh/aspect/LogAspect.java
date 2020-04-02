package henu.xmh.aspect;

import henu.xmh.annotation.LogAnnotation;
import henu.xmh.pojo.LogAction;
import henu.xmh.service.LogActionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Aspect
@Configuration
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogActionService logActionService;
    /**
     * 添加日志的调用方法
     * @param proceedingJoinPoint
     * @return
     */
    @Around("@annotation(henu.xmh.annotation.LogAnnotation)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
        HttpSession session = request.getSession();
        String  admin = (String) session.getAttribute("admin");//获取用户名
        Date date = new Date();//获取时间
        String name = proceedingJoinPoint.getSignature().getName();//获取方法名
        //获取注解信息
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        LogAnnotation annotation = signature.getMethod().getAnnotation(LogAnnotation.class);
        System.out.println(annotation.value());
        Class declaringType = proceedingJoinPoint.getSignature().getDeclaringType();//获取类名
        //向下执行
        try {
            Object proceed = proceedingJoinPoint.proceed();
            //执行成功
            System.out.println(admin+"  "+date+"  "+annotation.value() +"  "+name+" "+declaringType);
            LogAction logAction = new LogAction().setAdmin(admin).setDate(date)
                    .setAction(annotation.value()).setClazzName(declaringType.toString()).setMethod(name);
            //添加logAction
            logActionService.add(logAction);
            return proceed;//方法放行
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            //执行失败
            return  null;
        }
    }
}
