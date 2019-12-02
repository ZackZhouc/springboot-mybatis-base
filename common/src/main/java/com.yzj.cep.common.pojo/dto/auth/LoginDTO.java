package com.yzj.cep.common.pojo.dto.auth;

import lombok.Data;

@Data
public class LoginDTO {
    private String mobile;
    private String account;
    private String password;
}
