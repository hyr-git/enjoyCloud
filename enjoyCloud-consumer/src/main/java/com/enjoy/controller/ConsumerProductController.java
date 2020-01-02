package com.enjoy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.enjoy.vo.Product;

@RestController
@RequestMapping("/consumer")
public class ConsumerProductController {

	  private static final String HOST_NAME = "ENJOYCLOUD-PROVIDER-PRODUCT";
	
	  public static final String PRODUCT_GET_URL = "http://"+HOST_NAME+"/prodcut/get/";
	  public static final String PRODUCT_LIST_URL="http://"+HOST_NAME+"/prodcut/list/";
	  public static final String PRODUCT_ADD_URL = "http://"+HOST_NAME+"/prodcut/add/";
	  
	  @Autowired 
	  private RestTemplate restTemplate;

	  @Resource
	  private HttpHeaders httpHeaders;
	  
	  @Resource
	  private LoadBalancerClient loadBalancerClient;
      
	 /* @RequestMapping("/porduct/get/{id}")
	  public Object getProduct(@PathVariable("id") String id) {
		  Product product = restTemplate.getForObject(PRODUCT_GET_URL+id, Product.class);
		  return product;
	  }
	  
	  @RequestMapping("/porduct/put/{id}")
	  public Object addProduct(@PathVariable("id") String id) {
		  Boolean result = restTemplate.postForObject(PRODUCT_ADD_URL+id, Product.class,Boolean.class);
		  return result;
	  }
	  
	  @RequestMapping("/porduct/list")
	  public Object listProduct() {
		  List<Product> productList = restTemplate.getForObject(PRODUCT_LIST_URL, List.class);
		  return productList;
	  }*/
	  
	  @RequestMapping("/product/get")
	    public Object getProduct(long id) {
	        Product product = restTemplate.exchange(PRODUCT_GET_URL + id,HttpMethod.GET,new HttpEntity<Object>(httpHeaders), Product.class).getBody();
	        return  product;
	    }

	    @RequestMapping("/product/list")
	    public  Object listProduct() {
	    	
	    	 ServiceInstance serviceInstance = this.loadBalancerClient.choose("ENJOYCLOUD-PROVIDER-PRODUCT") ;
	         System.out.println(
	                 "【*** ServiceInstance ***】host = " + serviceInstance.getHost()
	                         + "、port = " + serviceInstance.getPort()
	                         + "、serviceId = " + serviceInstance.getServiceId());
	    	
	        List<Product> list = restTemplate.exchange(PRODUCT_LIST_URL,HttpMethod.GET,new HttpEntity<Object>(httpHeaders), List.class).getBody();
	        return  list;
	    }

	    @RequestMapping("/product/add")
	    public Object addPorduct(Product product) {
	        Boolean result = restTemplate.exchange(PRODUCT_ADD_URL, HttpMethod.POST,new HttpEntity<Object>(product,httpHeaders), Boolean.class).getBody();
	        return  result;
	    }

}
