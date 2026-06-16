package org.scoula;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class RestaurantTest {
    @Autowired // 생성자(객체) 만들어주는 어노테이션
    private Restaurant restaurant; // 주소가 들어있어야함.

    @Test
    void getChef() {
        assertNotNull(restaurant); // null이 맞는지 아닌지 확인하는 메서드
        log.info(restaurant);
        log.info("-------------------------------");
        log.info(restaurant.getChef());
    }
}