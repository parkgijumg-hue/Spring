package org.scoula.config;

import org.springframework.lang.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        // WebSocketConfig를 서블릿 컨텍스트에 등록
        return new Class[] { ServletConfig.class, WebSocketConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    /**
     * 🔧 서블릿 필터 설정 (HTTP 요청/응답 전처리)
     * - DispatcherServlet으로 전달되기 전에 HTTP 요청/응답을 전처리할 필터들을 등록하고 설정
     * <p>
     * 🎯 현재 설정: 문자 인코딩 필터 (CharacterEncodingFilter)
     * - setEncoding("UTF-8")
     * → 요청 데이터의 문자 인코딩을 UTF-8로 설정
     * → 한글, 특수문자 등 다국어 지원
     * → POST 요청의 파라미터 인코딩에 주로 영향
     * <p>
     * - setForceEncoding(true)
     * → 응답 데이터에도 UTF-8 인코딩 강제 적용
     * → 요청과 응답 모두 일관된 인코딩 보장
     * → 클라이언트가 다른 인코딩을 요청해도 UTF-8로 응답
     * <p>
     * <p>
     * 📊 웹 요청 처리 흐름:
     * <p>
     * 클라이언트 요청
     * ↓
     * 서블릿 컨테이너 (Tomcat)
     * ↓
     * CharacterEncodingFilter ← 현재 설정
     * ↓
     * [기타 필터들]
     * ↓
     * DispatcherServlet
     * ↓
     * HandlerMapping → Controller → ViewResolver
     * ↓
     * JSP 렌더링 → 클라이언트 응답
     */
    protected Filter[] getServletFilters() {
        // UTF-8 문자 인코딩 필터 생성 및 설정
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");       // 요청 데이터 UTF-8 디코딩
        characterEncodingFilter.setForceEncoding(true);     // 응답 데이터도 UTF-8 강제 인코딩

        return new Filter[]{characterEncodingFilter};
    }
}
