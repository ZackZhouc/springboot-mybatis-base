package com.yzj.cep.web.controller;

import com.yzj.cep.common.pojo.dto.auth.LoginDTO;
import com.yzj.cep.common.pojo.vo.ResponseVO;
import com.yzj.cep.service.auth.IAuthService;
import com.yzj.cep.web.annotation.RequireLogin;
import com.yzj.cep.web.annotation.RequirePemission;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "登录测试接口",notes = "获取token 参数中account和mobile至少传一个",httpMethod = "POST")
    @ApiImplicitParam(dataType = "LoginDTO",name = "loginDTO",value = "登录参数",required = true)
    public ResponseVO login(@RequestBody LoginDTO loginDTO) {
        ResponseVO responseVO;
        try {
            System.out.println(loginDTO.toString());
            responseVO  = authService.login(loginDTO);
        }catch (Exception e) {
            e.printStackTrace();
          return ResponseVO.genErrorResponse();
        }
        return responseVO;
    }

    @RequestMapping("/resource")
    @RequireLogin
    @RequirePemission("admin")
    @ApiOperation(value = "获取资源接口",notes = "测试获取资源",httpMethod = "GET")
    public ResponseVO getResource() {
        return ResponseVO.genOkResponse("hello");
    }

    @RequestMapping("/test")
    @ApiIgnore
    public ResponseVO test(@RequestParam(required = true) String id) {
        return ResponseVO.genOkResponse("hello");
    }
}
