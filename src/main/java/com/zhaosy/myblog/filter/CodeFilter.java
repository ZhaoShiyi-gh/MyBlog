package com.zhaosy.myblog.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CodeFilter implements Filter {
    public CodeFilter() {
        // TODO Auto-generated constructor stub
    }

    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rp = (HttpServletResponse) response;
        rq.setCharacterEncoding("utf-8");
        rp.setCharacterEncoding("utf-8");
        rp.setContentType("text/html;charset=utf-8");

        rp.setHeader("Cache-Control", "no-cache");
        rp.setHeader("Pragma", "no-cache");
        rp.setDateHeader("expires", -1);

        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
