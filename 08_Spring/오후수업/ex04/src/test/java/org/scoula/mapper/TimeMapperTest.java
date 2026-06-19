package org.scoula.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // JUnit5에서 Spring 테스트 지원
@ContextConfiguration(classes = { RootConfig.class }) // 테스트에 사용할 설정 클래스
@Log4j2
public class TimeMapperTest {

    @Autowired
    private TimeMapper timeMapper;

    @Test
    @DisplayName("TimeMapper의 getTime() 테스트")
    public void getTime() {
        // MyBatis가 생성한 구현체 클래스명 확인
        log.info("구현체 클래스: " + timeMapper.getClass().getName());

        // 실제 SQL 실행 및 결과 확인
        log.info("현재 시간: " + timeMapper.getTime());
    }
    @Test
    @DisplayName("TimeMapper의 getTime2() - XML 매퍼 테스트")
    public void getTime2() {
        log.info("XML 매퍼 실행");
        log.info("현재 시간: " + timeMapper.getTime2());
    }
}
