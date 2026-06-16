package org.scoula.config;


import org.springframework.lang.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebConfig  extends AbstractAnnotationConfigDispatcherServletInitializer {
    //스프링 설정 파일 중 위 클래스를 상속받은 클래스를 찾음.
    //다른 설정용 클래스를 지정함.


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
        // new int[] {1, 2, 3}
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { ServletConfig.class };
    }

    //프론트컨트롤러 호출 주소 설정
    //@WebServlet("/")와 같은 역할
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    // POSTbody문자인코딩필터설정-UTF-8설정
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter= new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }
}
