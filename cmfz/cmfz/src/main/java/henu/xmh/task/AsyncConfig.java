package henu.xmh.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync //开启异步执行支持（线程池支持）
public class AsyncConfig {
    @Bean
    public Executor executor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();//创建任务型线程池
        threadPoolTaskExecutor.setMaxPoolSize(50);//最大连接数
        threadPoolTaskExecutor.setCorePoolSize(10);//核心连接数
        threadPoolTaskExecutor.setQueueCapacity(10);//等待队列的长度
        return threadPoolTaskExecutor;
    }
}
