package com.yzj.cep.service.auth.impl;

import com.yzj.cep.common.pojo.dto.auth.LoginDTO;
import com.yzj.cep.common.pojo.vo.ResponseVO;
import com.yzj.cep.service.auth.IAuthService;
import com.yzj.cep.service.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ResponseVO login(LoginDTO loginDTO) throws Exception {
        log.info("auth request...");
        String token = redisTemplate.opsForValue().get(loginDTO.getAccount());
        if (token == null)
            token = JwtUtil.getToken(loginDTO.getAccount());
        return ResponseVO.genOkResponse(token);
    }
}
