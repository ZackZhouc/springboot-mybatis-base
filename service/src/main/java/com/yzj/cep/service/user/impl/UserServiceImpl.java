package com.yzj.cep.service.user.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzj.cep.dao.user.UserMapper;
import com.yzj.cep.entity.user.User;
import com.yzj.cep.service.user.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoucong
 * @since 2019-11-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @DS("slave")
    @Override
    public List<User> getUserById() {
        return super.list();
    }
}
