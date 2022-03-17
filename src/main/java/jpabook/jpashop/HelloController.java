package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //url 통해서 들어오는 요청 컨트롤하겠다는 의미(Annotation)
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { //Model은 스프링 ui 객체로 데이터 담을 수 있음
        model.addAttribute("data", "hello!!!");
        return "hello";
    }

}
