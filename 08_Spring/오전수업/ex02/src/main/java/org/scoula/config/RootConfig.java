package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.scoula"}) // RootConfig는 서비스와 레포지토리 빈을 등록하는 역할
public class RootConfig {
    //프로젝트 전체에서 사용할 중요한 싱글톤 빈 생성 정의
}
