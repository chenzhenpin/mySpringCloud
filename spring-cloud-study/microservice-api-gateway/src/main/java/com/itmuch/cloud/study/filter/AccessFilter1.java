package com.itmuch.cloud.study.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessFilter1 extends ZuulFilter {
    private static Logger log= LoggerFactory.getLogger(AccessFilter1.class);
    @Override
    public String filterType() {
        //在路由之前过滤
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过滤的顺序
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        //返回true该过滤器执行
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        try {
            HttpServletRequest request = ctx.getRequest();
            System.out.println("AccessFilter1");
            log.info("send{} request to{}", request.getMethod(), request.getRequestURL().toString());
            Object accessToken = request.getParameter("accessToken");
            /*if(accessToken != null) {
                log.warn("access token is empty");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                return null;

            }*/
        } catch (Exception e) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ctx.set("error.exception", e);
            System.out.println("AccessFilter1,error");
            return null;
        }
        log.info("access token ok");
        return null;

    }
}
