package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.scoula.ex03.dto.SampleDTOList;
import org.scoula.ex03.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// instance : 클래스 -> 객체
// 작성법 -> new 클래스명(); 객체 생성을 개발자가 직접함

// IOC(Inversion of Control) : 제어의 역전
// 객체의 생성과 관리 책임을 개발자가 아닌 Spring(프레임워크)이 담당하는 방식
// Spring이 객체를 생성하고 관리 -> 개발자는 필요한 객체(Bean)를 주입받아 사용

@Controller                    // Spring MVC 컨트롤러임을 선언 - 컴포넌트 스캔 대상
                               // 현재 클래스가 컨트롤러임을 명시 -> 요청, 응답 처리 + bean 등록

@RequestMapping("/sample")     // 클래스 레벨 - 모든 메서드의 기본 URL 경로 설정
@Log4j2                        // 로깅을 위한 Lombok 어노테이션
public class SampleController {

    @RequestMapping("")        // 메서드 레벨 - 세부 경로 ("" = 기본 경로만 사용)
    public void basic() {      // void 리턴 = RequestMapping과 동일한 이름의 JSP 자동 매핑
        log.info("basic............");
        // /sample 요청 시 /WEB-INF/views/sample.jsp로 forward
    }

    // HTTP 메서드를 명시적으로 지정하는 방식 - 다중 메서드 허용
    @RequestMapping(
            value="/basic",
            method={RequestMethod.GET, RequestMethod.POST})
    // /sample/basic 으로 GET 혹은 POST 방식으로 요청이 오면 해당 메소드와 연
    public void basicGet() {
        log.info("basic get............");
        // GET과 POST 요청 모두 처리 가능
    }

    // GET 요청만 처리 - 조회 작업에 사용 (Safe, Idempotent)
    @GetMapping("/basicOnlyGet")
    public void basicGet2() {
        log.info("basic get only get............");
        // 데이터 조회, 페이지 표시 등 안전한 작업
    }

    @GetMapping("/ex01")
    // url : /sample/ex01 GET방식 요청
    public String ex01( /* @ModelAttribute */ SampleDTO dto) {  // HandlerAdapter가 자동으로 객체 생성 및 프로퍼티 바인딩
        log.info("" + dto);   // 바인딩된 데이터 로그 출력으로 확인

        // 파라미터 전달 방법
        // 1. @ModelAttribute를 이용한 방법
        //    ex01(@ModelAttribute("dto") SampleDTO dto)
        //    -> @ModelAttribute는 생략 가능, "dto"는 뷰에서 사용할 이름

        // -  전달 받은 파라미터의 name 속성값이 같이 사용되는 DTO의 필드명과 같다면
        //    자동으로 setter를 호출해서 필드에 값 세팅

        // * @ModelAttribute를 이용해 값이 세팅된 객체를 "커맨드 객체"라고 함

        // * @ModelAttribute 사용시 주의하상 *
        // 1. 커맨드 객체(DTO)는 반드시 기본 생성자와 setter 메서드를 가져야 함
        // 2. 커맨드 객체는 JSP에서 사용하기 위해 모델(Model)에 자동으로 추가됨 (뷰에서 ${dto}로 접근 가능)
        // 3. 커맨드 객체는 요청 파라미터와 일치하는 필드가 있어야 하며, 그렇지 않으면 바인딩 오류 발생 가능


        // servlet
        /*String name = req.getParameter("name");
        int age = req.getParameter("age");

        SampleDTO sameple = new SampleDTO();
        sample.setName(name);
        sample.setAge(age);*/

        return "sample/ex01"; // ViewResolver에 의해 /WEB-INF/views/sample/ex01.jsp로 forward
    }

    @GetMapping("/ex02")
    public String ex02(
            @RequestParam("name") String name,    // 파라미터명 "name"을 String으로 바인딩
            @RequestParam("age") int age) {       // 파라미터명 "age"를 int로 자동 변환
        log.info("name: " + name);
        log.info("age: " + age);

        // 파라미터 전달 방법
        // 2. @RequestParam을 이용한 방법
        //    ex02(@RequestParam("name") String name, @RequestParam("age") int age)
        //    -> @RequestParam은 생략 가능, 파라미터의 속성값과 변수명이 같다면 자동으로 바인딩
        //    -> @RequestParam은 단일 파라미터를 개별적으로 바인딩할 때 사용, DTO보다 간단한 경우에 적합


        return "sample/ex02";
    }

    // @RequestParam 옵션 활용 - 파라미터 누락 및 기본값 처리
    @GetMapping("/ex02-advanced")
    public String ex02Advanced(
            @RequestParam(value="name", required=false, defaultValue="익명") String name,
            @RequestParam(value="age", required=false, defaultValue="10") int age) {
        // value : 전달받은 파라미터의 name 속성값
        // required=false: 파라미터가 없어도 에러 발생하지 않음 ( true가 기본값임 )
        // defaultValue: 파라미터가 없을 때 사용할 기본값 (문자열로 지정, 자동 형변환)

        log.info("name : " + name + ", age : " + age);

        return "sample/ex02";
    }

    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list) {


        log.info("list dtos: " + list);
        return "ex02Bean";
        // 복합 객체 내부의 리스트도 자동 바인딩 처리
    }

    @GetMapping("/c")
    public String ex03(TodoDTO todo) {
        log.info("todo: " + todo);
        return "ex03";
        // @DateTimeFormat에 지정된 패턴에 맞는 문자열만 Date로 변환
    }

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {

        // Servlet 에서 사용
        // int page = 1;
        // req.getParameter("page",page);
        // 기본적으로 문자열로 전달되는 파라미터를 int로 자동 변환하여 바인딩

        // @ModelAttribute의 역할
        // 1. 요청 파라미터를 객체(또는 변수)에 바인딩
        // 2. 바인딩된 객체(또는 변수)를 모델(Model)에 자동으로 추가하여 뷰에서 접근 가능하도록 함

        // @ModelAttribute("page") int page
        // @RequestParam + model.addAttribute("page", page) 역할을 동시에 수행

        log.info("dto: " + dto);
        log.info("page: " + page);
        return "sample/ex04";
        // @ModelAttribute로 기본 자료형도 Model에 추가하여 뷰에서 접근 가능
    }

    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06........");
        // 리다이렉트 시에도 데이터 전달 가능 (일회성)

        // RedirectAttributes : 리다이렉트 시 데이터를 전달하기 위한 객체

        // forward : 서버 내부 이동 (요청 1번, 주소 유지)
        // redirect : 브라우저에게 새 URL로 다시 요청하라고 지시 (요청 2번, 주소 변경)

        // ra.addAttribute() : URL 쿼리스트링으로 데이터 전달
        // ra.addFlashAttribute() : 리다이렉트 시 데이터를 requests scope로 전달
        //                          (세션에 잠깐 저장 후 request scope로 복원)

        ra.addFlashAttribute("name", "AAA");  // URL 파라미터로 전달
        ra.addAttribute("age", 10);
        return "redirect:/sample/ex06-2";  // 클라이언트에게 새로운 URL로 재요청 지시
    }

    // 방법 1. @RequestParam으로 각각 받는 방식
/*
@GetMapping("/ex06-2")
public String ex062(@RequestParam("name") String name,
                    @RequestParam("age") int age) {
    log.info("name: " + name);
    log.info("age: " + age);

    return "sample/ex06-2";
    // 리다이렉트 후 URL에 쿼리스트링으로 전달된 데이터 확인
}
*/

    // 방법 2. DTO로 한 번에 받는 방식
    @GetMapping("/ex06-2")
    public String ex062(@ModelAttribute("dto") SampleDTO dto) {
        log.info("name: " + dto.getName());
        log.info("age: " + dto.getAge());

        return "sample/ex06-2";
        // name, age 파라미터가 SampleDTO의 필드에 자동으로 바인딩됨
    }
}