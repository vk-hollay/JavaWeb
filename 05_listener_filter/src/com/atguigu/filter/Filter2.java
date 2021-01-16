package com.atguigu.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Hollay
 * @create 2020-11-27-23:28
 * @description
 */
public class Filter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter2 前置代码");
        //System.out.println("Filter2 线程：" + Thread.currentThread().getName());

        chain.doFilter(request,response);

        //System.out.println("Filter2 线程：" + Thread.currentThread().getName());
        System.out.println("Filter2 后置代码");
    }

    @Override
    public void destroy() {

    }
}
