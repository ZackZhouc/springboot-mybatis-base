package com.yzj.cep.web.controller;

import com.yzj.cep.service.redis.RedisTemplateService;
import com.yzj.cep.web.pojo.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private RedisTemplateService redisTemplateService;

    @RequestMapping("/login")
    public ResponseVO login() {
//        String token = JwtUtil.getToken("shipzhou");
//        redisTemplateService.set("token",ResponseVO.genOkResponse(token));
        ResponseVO responseVO  = redisTemplateService.get("token",ResponseVO.class);
        return responseVO;
    }
}
