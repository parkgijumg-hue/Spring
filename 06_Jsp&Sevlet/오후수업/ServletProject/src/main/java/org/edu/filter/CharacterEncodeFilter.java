package org.edu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/pizza/order") // 요청에 대해 필터링
public class CharacterEncodeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1. 서블릿이 실행되기 전에 처리할 작업
        servletRequest.setCharacterEncoding("UTF-8");
//        servletResponse.setCharacterEncoding("UTF-8");

        // HttpServletRequest 필요 시 ServletRequest 다운 캐스팅 필요!
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        req.getSession();
        // 2. 다음 필터 또는 서블릿으로 이동
        filterChain.doFilter(servletRequest,servletResponse);

        // 3. 서블릿이 실행된 후에 처리할 작업
    }

     /*
        - Filter : 서블릿이 실행되기 전, 후에 공통적으로 처리할 작업이 있을 때 사용하는 객체
                    (ex. 인코딩, 로깅, 보안 등등)

        - Filter는 여러 개를 등록할 수 있고, 등록된 순서대로 실행됨

        - Filter는 서블릿과 달리 doFilter() 메소드 하나만 존재
     */

}
