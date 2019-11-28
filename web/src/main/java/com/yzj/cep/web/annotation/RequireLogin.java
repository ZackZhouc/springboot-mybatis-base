package com.yzj.cep.web.annotation;

import java.lang.annotation.*;

/**
 * 在方法上加入该注解 必须登录才能访问 否则返回401
 * 权限粒度 RequirePermission < RequireRole < RequireLogin
 * 同时配置多个 粒度细的生效
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireLogin {
}
