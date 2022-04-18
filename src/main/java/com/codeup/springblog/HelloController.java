package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

//
//    @GetMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        return "Hello there!";
//    }
//
//    @RequestMapping(path = "/hello/{name}/and/{age}", method = RequestMethod.GET)
//    @ResponseBody
//    public String helloNameAge(@PathVariable String name, @PathVariable int age) {
//        return "Hey user! Thanks for letting me know your name is " + name + ". You also let me know you are " + age + " years old.";
//    }

    //USING VIEWS
    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("enteredName", name);
        return "hello";
    }


}
