package com.yzj.cep.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzj.cep.entity.user.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoucong
 * @since 2019-11-28
 */
public interface IUserService extends IService<User> {
    List<User> getUserById ();
}
