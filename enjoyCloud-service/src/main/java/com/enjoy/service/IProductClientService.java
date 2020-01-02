package com.enjoy.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enjoy.config.FeignClientConfig;
import com.enjoy.service.fallback.IProductClientServiceFallbackFactory;
import com.enjoy.vo.Product;

@FeignClient(name = "ENJOYCLOUD-PROVIDER-PRODUCT",configuration = FeignClientConfig.class,fallbackFactory=IProductClientServiceFallbackFactory.class)
public interface IProductClientService {
	
    @RequestMapping("/prodcut/get/{id}")
    public Product getProduct(@PathVariable("id")long id);

    @RequestMapping("/prodcut/list")
    public  List<Product> listProduct() ;

    @RequestMapping("/prodcut/add")
    public boolean addPorduct(Product product) ;

}
