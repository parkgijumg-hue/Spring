package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    final private BoardService service;

//    public BoardController(BoardService service) {
//        this.service = service;
//    }

    @GetMapping("/list") // /board/list
    public void list(Model model){

        //jsp로 결과를 넘기자.--> Model
        model.addAttribute("list", service.getList());
        //호출할 jsp를 지정하지 않으면, 요청된 주소를 사용합니다.
        // /board/list를 이용함.
        // view resolver가 /WEB-INF/views/ + /board/list + .jsp 만들어줌.
        // /WEB-INF/views/board/list.jsp
    }

    @GetMapping("/create") //글쓰기 jsp화면 보여주세요.
    public void create() {
        log.info("create");
    }

    @PostMapping("/create")
    public String create(BoardDTO board) {
        //글쓴 내용을 dto로 받아서 service전달
        log.info("create: " + board);
        service.create(board);
        //db처리후 list를 보여달라고 다시 요청하게 브라우저에 명령
        return "redirect:/board/list";
    }
}
