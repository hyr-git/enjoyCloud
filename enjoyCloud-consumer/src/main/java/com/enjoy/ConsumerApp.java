package com.enjoy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.base.config.RibbonConfig;

@SpringBootApplication
@EnableEurekaClient
//这里的name 只服务的名称，如果需要有多个服务提供方，这个时候可以使用@RibbonClients进行配置
@RibbonClient(name ="ENJOYCLOUD-PROVIDER-PRODUCT" ,configuration = RibbonConfig.class)
public class ConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class,args);
    }
}