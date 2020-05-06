package com.kklll.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: DeepBlue
 * @Date: 2020/4/30 21:57
 * @Description:
 */
@Controller
public class PageController {
    @GetMapping({"/","index"})
    public String index(){
        return "index3";
    }

}
