package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {

    // get or post 방식과 상관없이 모든 방식에 동일한 처리를 하고 싶은 경우 @RequestMapping을 사용한다.
    @RequestMapping("")  // url : /sample
    public void basic() {
        log.info("basic............");
    }
    @RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST}) // url: /sample/basic
    public void basicGet(){
        log.info("basic get............");
    }
    @GetMapping("/basicOnlyGet") // url: /sample/basicOnlyGet
    public void basicGet2(){
        log.info("basic get only get............");
    }

    @GetMapping("/ex01")
    public String ex01(SampleDTO sampleDTO) {
        log.info("" + sampleDTO);
        return "ex01";
    }

    @GetMapping("/ex02")
    public void ex02(@RequestParam("name") String name,
                     int age) {
        // @RequestParam("변수명") : url에서 name이라는 이름으로 넘어오는 값을 변수 name에 넣어준다.
        // @RequestParam은 생략 가능하다. url에서 넘어오는 변수명과 메서드의 매개변수명이 같으면 자동으로 매핑된다.
        log.info("name: " + name);
        log.info("age: " + age);

    } // view 역할의 jsp는 /sample/ex01 을 가지고 만든다.

    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
        log.info("ids: " + ids);
        return "ex02List";
    }
}
