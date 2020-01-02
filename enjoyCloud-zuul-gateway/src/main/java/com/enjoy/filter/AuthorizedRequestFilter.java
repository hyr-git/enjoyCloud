package com.enjoy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import java.nio.charset.Charset;
import java.util.Base64;


public class AuthorizedRequestFilter extends ZuulFilter{
    
	/****
	 * filterType过滤的类型,用于设置zuul过滤时只需的位置,一般包括:
	 * pre:请求发出之前执行,若要进行访问,在请求前设置头信息
	 * route:在进行路由请求的时候被调用
	 * post:在路由后请求信息时被调用
	 * error:出现错误之后进行调用
	 */
	@Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
    
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext() ; // 获取当前请求的上下文
        String auth = "admin:enjoy"; // 认证的原始信息
        byte[] encodedAuth = Base64.getEncoder()
                .encode(auth.getBytes(Charset.forName("US-ASCII"))); // 进行一个加密的处理
        String authHeader = "Basic " + new String(encodedAuth);
        currentContext.addZuulRequestHeader("Authorization", authHeader);
        return null;
    }
}
