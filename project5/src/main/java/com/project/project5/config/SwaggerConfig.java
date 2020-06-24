package com.project.project5.config;

import com.project.project5.enums.ExceptionEnums;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.common.SwaggerPluginSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName SwaggerConfig
 * @date 2020/4/10 17:15
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${project.swagger.open}")
    private Boolean openSwagger;

    List<ResponseMessage> swaggerList = new ArrayList<ResponseMessage>(){
        {
            add(new ResponseMessageBuilder().code(ExceptionEnums.SUCCESS.getCode()).message(ExceptionEnums.SUCCESS.getMsg()).build());
            add(new ResponseMessageBuilder().code(ExceptionEnums.FAILED.getCode()).message(ExceptionEnums.FAILED.getMsg()).build());
            add(new ResponseMessageBuilder().code(ExceptionEnums.SERVER_ERROR.getCode()).message(ExceptionEnums.SERVER_ERROR.getMsg()).build());
        }
    };

    /**
     * 公共head头信息配置
     *
     * @return
     */
    private List<Parameter> getPars() {
        List<Parameter> pars = new ArrayList<>();

        // 增加token验证header
        ParameterBuilder ticketPar1 = new ParameterBuilder();
        ticketPar1.name(CommonConstant.HEADER_AUTHORIZATION)
                .description("user token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER - 2)
                .required(false)//header中的Authorization参数非必填，传空也可以
                .build();
        pars.add(ticketPar1.build());    //根据每个方法名也知道当前方法在设置什么参数

        return pars;
    }

    /**
     * 登陆模块
     * @return
     */
    @Bean
    public Docket loginApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("登陆Api接口")
                .enable(openSwagger)
                 // 不使用默认状态码定义
                .useDefaultResponseMessages(false)
                //自定义全局错误码
                .globalResponseMessage(RequestMethod.GET, swaggerList)
                .globalResponseMessage(RequestMethod.POST, swaggerList)
                .globalResponseMessage(RequestMethod.PUT, swaggerList)
                .globalResponseMessage(RequestMethod.DELETE, swaggerList)
                //描述
                .apiInfo(projectInfo("登陆模块接口"))//apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
                //select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.project.project5.controller.login"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(getPars());
    }

    /**
     * 测试模块
     * @return
     */
    @Bean
    public Docket testApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试Api接口")
                .enable(openSwagger)
                // 不使用默认状态码定义
                .useDefaultResponseMessages(false)
                //自定义全局错误码
                .globalResponseMessage(RequestMethod.GET, swaggerList)
                .globalResponseMessage(RequestMethod.POST, swaggerList)
                .globalResponseMessage(RequestMethod.PUT, swaggerList)
                .globalResponseMessage(RequestMethod.DELETE, swaggerList)
                //描述
                .apiInfo(projectInfo("测试模块接口"))//apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
                //select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.project.project5.controller.test"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(getPars());
    }


    /**
     * 接口描述
     * @param des String 描述
     * @return
     */
    private ApiInfo projectInfo(String des) {
        Contact contact = new Contact("RookieDe", "https://github.com/RookieDe", "***@123.com");
        return new ApiInfoBuilder().title("种子模板项目").description(des)
                .contact(contact)
                .version("0.0.1")
                .build();
    }

}
