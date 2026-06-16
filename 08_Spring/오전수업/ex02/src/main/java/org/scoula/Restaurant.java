package org.scoula;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 싱글톤으로 만들어주는 어노테이션
@Data
public class Restaurant {

    @Autowired // 스프링이 Chef타입의 램을 찾아서 chef필드에 주입해줌. (DI)
    private Chef chef; // chef가 램에 어디에 있는지 주소만 알면 찾을 수 있음.
}
