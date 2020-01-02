package com.enjoy.service.fallback;

import java.util.List;

import org.springframework.stereotype.Component;

import com.enjoy.service.IProductClientService;
import com.enjoy.vo.Product;

import feign.hystrix.FallbackFactory;

/***
 * 服务降级
 *     当服务的提供方不可以使用的时候,程序不会出现异常,而会出现本地的操作调用,
 *     服务的降级是在服务消费方实现的,在断网情况下服务提供方的任何处理都是没有意义的。
 * @author m1832
 *
 */
@Component
public class IProductClientServiceFallbackFactory implements FallbackFactory<IProductClientService>{

	@Override
	public IProductClientService create(Throwable throwable) {
		return new IProductClientService() {

			@Override
			public Product getProduct(long id) {
				Product product = new Product();
				product.setProductDesc("feign-hystrixName");
				product.setProductName("feign-hytrixDesc");
				product.setProductId(999999L);
				return product;
			}

			@Override
			public List<Product> listProduct() {
				return null;
			}

			@Override
			public boolean addPorduct(Product product) {
				return false;
			}
			
		};
	}

}
