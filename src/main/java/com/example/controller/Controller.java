package com.example.controller;

import com.example.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;

/**
 * @PackageName: com.example.controller
 * @Description: 测试Swagger
 * @author: 罗秉荣
 * @date: 2020/10/16
 */
@Api(tags = "用户相关的请求") //对controller的描述信息 使用@Api
@RestController
@RequestMapping("/user")
public class Controller {
    @ApiOperation("获取用户信息")//对方法的描述 使用@ApiOpration
    @ApiImplicitParam(name = "user",value = "用户对象")
    @GetMapping
    public String getUser(User user){
        return "张三";
    }
    @PostMapping
    public String post(String name){
        return name;
    }

}
