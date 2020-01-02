package com.enjoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * HystrixDashboard主要是对某一项微服务进行监控
 * Turbine更多的是对很多服务进行一个整体的监控
 * 未来演示功能:
 *     该模块不连接数据库,也不做安全控制
 */
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class UserHystrixApp 
{
    public static void main( String[] args )
    {
       SpringApplication.run(UserHystrixApp.class,args);
    }
}
