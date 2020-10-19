package com.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @PackageName: com.example.entity
 * @Description:
 * @author: 罗秉荣
 * @date: 2020/10/16
 */
@ApiModel(" user实体类 @ApiModel注解是用来编写注解，对这个信息的描述")
public class User {
    @ApiModelProperty("用来描述每个属性的注解，用户名")
    private String name;
    //example 设置默认值
    @ApiModelProperty(value = "年龄",example = "18")
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
