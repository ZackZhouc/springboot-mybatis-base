package com.yzj.cep.service.auth;

import com.yzj.cep.common.pojo.dto.auth.LoginDTO;
import com.yzj.cep.common.pojo.vo.ResponseVO;

public interface IAuthService {
    ResponseVO login(LoginDTO loginDTO) throws Exception;
}
