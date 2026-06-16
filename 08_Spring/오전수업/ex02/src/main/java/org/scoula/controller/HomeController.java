package org.scoula.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 싱글톤으로 만들어주고, 클래스 안에 어노테이션(메서드+url --> 컨트롤러이름.메서드()를 등록)
@Log4j2 //콘솔에 내가 원하는 메시지로 출력 가능.
public class HomeController {

    //어떤 주소로(/), 어떤 방법으로(get)
    //하나의 http요청당 서버에서는 메서드 하나랑 정의

    @GetMapping("/")
    public String home(){
        log.info("첫페이지 호출됨..------------------");
        return "home";
    }
}
