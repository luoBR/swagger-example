package com.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName: com.example.controller
 * @Description:
 * @author: 罗秉荣
 * @date: 2020/10/16
 */
@RestController
@Api(tags = "做swagger测试")
@RequestMapping("/hello")
public class Hello {
    @PutMapping()
    @ApiOperation("获取hello方法中的参数")
    @ApiImplicitParams({//设置多个参数值，可以使用这个注解，后面使用大括号包裹，多个参数之间使用都好隔开
            @ApiImplicitParam(name = "str",value = "str字符串",defaultValue = "设置默认属性值"),
            @ApiImplicitParam(name="id",value = "id值",defaultValue = "id默认值")
    })
    public String updateHello(String str,int id,Integer ID,Long L){
        return str;
    }
    @DeleteMapping
    public String deleteHello(){
        return "hello";
    }
}
