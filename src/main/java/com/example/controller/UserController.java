package com.example.controller;

import com.example.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * @PackageName: com.example.controller
 * @Description:
 * @author: 罗秉荣
 * @date: 2020/10/16
 */
@RestController
@RequestMapping("/entity")
public class UserController {
    @DeleteMapping
    public User test(){
        User user = new User("简单工厂模式", 18);
        return user;
    }
    @PostMapping
    public String testJson(@RequestBody User user){//接受json格式字符串
        return "json";
    }
}
