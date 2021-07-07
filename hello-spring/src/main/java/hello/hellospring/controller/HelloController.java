package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name="name",required = true) String name, Model model) { //ctrl+P 누르면 파라미터 정보
        model.addAttribute("name",name);
        return "hello-template"; //얘는 소스코드보기 하면 html나오고
    }

    @GetMapping("hello-string")
    @ResponseBody //이 데이터를 직접 넣어주겠다?
    public String helloString(@RequestParam("name") String name) {
        return "hello "+name; //위랑 다른점 view 이런거 없고 이문자가 그대로 내려간다? 소스코드 보면 얘는 그냥 hello spring 나옴
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) { //json방식 키:value 이렇게 나옴 json 검색해보기 최근엔 거의 xml방식보단 json쓴다
        Hello hello = new Hello(); //ctrl+shift+enter 치면 인텔리제이가 알아서 (); 같은거 쳐줌
        hello.setName(name);
        return hello;
    }

    static class Hello { //클래스안에 클래스 HelloController.Hello로 쓸수있다
        private String name;

        public String getName() { //alt+insert 누르면 getter setter 넣을수있다
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
