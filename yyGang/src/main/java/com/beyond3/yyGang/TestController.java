package com.beyond3.yyGang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/example")
@RestController
public class TestController {

    @GetMapping("test")
    public void getTest(){
        log.info("테스트 컨트롤러");
    }
}