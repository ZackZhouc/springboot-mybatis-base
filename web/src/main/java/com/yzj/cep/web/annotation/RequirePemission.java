package com.yzj.cep.web.annotation;

import java.lang.annotation.*;

/**
 * 在方法上加入该注解 必须拥有相应的权限才能访问 否则返回403
 * 权限粒度 RequirePermission < RequireRole < RequireLogin
 * 同时配置多个 粒度细的生效
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequirePemission {
  String  value() default "";
}
