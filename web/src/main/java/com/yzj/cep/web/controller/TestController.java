package com.yzj.cep.web.controller;

import com.yzj.cep.entity.classrecord.ClassRecord;
import com.yzj.cep.entity.user.User;
import com.yzj.cep.service.classrecord.IClassRecordService;
import com.yzj.cep.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@ApiIgnore
public class TestController {

    @Autowired
    private IClassRecordService classRecordService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/insert")
    public String test() {
        ClassRecord classRecord = new ClassRecord();
        classRecord.setStudentId(1L);
        classRecord.setTeacherId(1L);
        classRecordService.save(classRecord);
        return "ok" ;
    }

    @RequestMapping("/select")
    public List<User> find() {
        List<User> list = userService.getUserById();
        return list;
    }
}
