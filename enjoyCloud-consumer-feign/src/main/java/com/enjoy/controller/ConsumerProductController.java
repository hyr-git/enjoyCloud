package com.enjoy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoy.service.IProductClientService;
import com.enjoy.vo.Product;

@RestController
@RequestMapping("/consumer")
public class ConsumerProductController {

	  @Resource
	  private IProductClientService productClientService;
	  
	  
	  @RequestMapping("/product/get")
	    public Object getProduct(long id) {
	        Product product = productClientService.getProduct(id);
	        return  product;
	    }

	    @RequestMapping("/product/list")
	    public  Object listProduct() {
	    	
	        List<Product> list = productClientService.listProduct();
	        return  list;
	    }

	    @RequestMapping("/product/add")
	    public Object addPorduct(Product product) {
	        Boolean result = productClientService.addPorduct(product);
	        return  result;
	    }

}
