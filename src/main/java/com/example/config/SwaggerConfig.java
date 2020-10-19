package com.example.config;

import io.swagger.models.properties.PropertyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @PackageName: com.example.config
 * @Description:
 * @author: 罗秉荣
 * @date: 2020/10/16
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Autowired
    Environment environment;
    @Bean
    public Docket docketHello(){
        /**
         *为了让文档看起来简洁，把apiinfo去掉。之后还剩下一些多余接口展示部分，是我们不需要的
         * 可以通过设置获取到我们想要的接口展示
         */
        //自动检查当前是否处于dev环境或者test环境 如果不是，则返回false 自动检查环境
        Profiles profiles = Profiles.of("dev", "test");
        boolean iEnable = environment.acceptsProfiles(profiles);//如果dev 或者test 返回true

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hello")//修改在swagger右上方的分组名，多个分组对应对各bean
                .ignoredParameterTypes(Integer.class,int.class)//过滤掉在swagger文档上不需要的参数，就不会被扫描到swagger文档
                .enable(iEnable)//设置swagger文档是否可以访问，在项目上线时是不允许使用的 true: 表示可以使用 false表示不能使用
                            //false 就不能访问了 😱 Could not render e, see the console.
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))//找到当前工作路径下的controller包
                .paths(PathSelectors.ant("/hello/**"))//在controller包下使用正则表达式扫描读取对应的接口
                .build();
                //.select().apis(RequestHandlerSelectors.withMethodAnnotation(PutMapping.class)).build();
               // .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build();

        /**
         * 1.RequestHandlerSelectors.any()是默认的，表示展示原来默认的
         * 2.RequestHandlerSelectors.none()不展示接口部分
         * 3.RequestHandlerSelectors.withClassAnnotation()基于类上这个注解扫描到注解类下的接口
         * 4.RequestHandlerSelectors.withMethodAnnotation()基于方法上的注解来扫描
         * 5.RequestHandlerSelectors.basePackage()基于传入的包的名字去找到接口 相对于当前路径
         * 如果需要继续在划分不同url，可以通过.paths(PathSelectors.ant("/hello/**"))继续指定
         * 其中ant()方法里面使用的是正则表达式，表示之前扫描这个url地址下的接口
        */
    }
//    private ApiInfo apiInfo(){
//        //编写文档人的几个信息
//        Contact contact = new Contact("张三", "www.baidu.com", "451@qq.com");
//        ApiInfo apiInfo = new ApiInfo("swagger",
//                "做swagger测试",
//                "version1.1",
//                "http::localhost:8080",//相当于公司url
//                contact,
//                "",
//                "",
//                new ArrayList<>()
//        );
//        return apiInfo;
//    }
    @Bean
    public Docket docketUser(){
        Profiles profiles = Profiles.of("prod");
        boolean iEnable = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2).groupName("user")
                .ignoredParameterTypes(Long.class)
                .enable(!iEnable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .paths(PathSelectors.ant("/user/**"))
                .build();

    }
    @Bean
    public Docket docketTest(){
        Profiles profiles = Profiles.of("dev", "test");
        boolean b = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2).groupName("test")
                .enable(b)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }
    @Bean
    public Docket docketEntity(){
        return new Docket(DocumentationType.SWAGGER_2);
    }
    //做全局配置,要执行某个接口之前都要去执行令牌
    @Bean
    public Docket docketGloble(){
        Parameter token = new ParameterBuilder().name("token")
                .description("这是一个手机令牌")
            //    .parameterType("header") header放在请求头前面的 在接受时需要使用注解@RequestHeader("token") String token
                .parameterType("query")//放在实体类中的  接收时只需要定义类型就ok
                .modelRef(new ModelRef("String"))//参数类型
                .build();
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(token);
        return new Docket(DocumentationType.SWAGGER_2).
                globalOperationParameters(parameters)
                .groupName("globle");
    }

}
