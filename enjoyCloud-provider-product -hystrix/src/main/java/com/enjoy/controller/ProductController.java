package com.enjoy.controller;
import javax.annotation.Resource;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import com.enjoy.service.IProductService;
import com.enjoy.vo.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/prodcut")
public class ProductController {

    @Resource
    private IProductService iProductService;

    @Resource
    private DiscoveryClient client ; // 进行Eureka的发现服务

    
    /***
     * 一旦get方法抛出了错误的信息,就认为该服务有问题,
     * 会默认使用"HyStrixCommand"注解之中配置好的fallbackMethod调用勒种的指定方法返回相应数据。
     * HystrixCommand定义了一个断路器,封装了一个断路器get方法,当访问get方法失败达到阀值后,会使用fallbackMethod中声明的方法进行替代返回
     * 
     * @param id
     * @return
     */
    @RequestMapping(value="/get/{id}")
    @HystrixCommand(fallbackMethod = "serviceFailureFallBack")
    public Object get(@PathVariable("id") long id) {
    	Product product = this.iProductService.get(id) ;
    	if(null == product) {
    		throw new RuntimeException("该产品已下架！");
    	}
        return product;
    }
    
    /****
     * fallBackMethod的返回值和参数类型需要和被@HystrixCommand注解的方法完全一致.
     * 当服务的调用失败达到一个特定的阀值,该链路就会被处于open状态,之后对所有服务的调用都不会被执行,
     * 取带的是断路器提供的一个表示链路open的fallback消息。
     * @param id
     * @return
     */
    public Object serviceFailureFallBack(@PathVariable("id") long id) {
    	Product product = new Product();
    	product.setProductName("HystrixName");
    	product.setProductDesc("HytrixDesc");
    	product.setProductId(0L);
    	return product;
    }
    
    @RequestMapping(value="/add")
    public Object add(@RequestBody Product product) {
        return this.iProductService.add(product) ;
    }
    
    @RequestMapping(value="/list")
    public Object list() {
        return this.iProductService.list() ;
    }


   @RequestMapping("/discover")
    public Object discover() { // 直接返回发现服务信息
        return this.client ;
    }
}