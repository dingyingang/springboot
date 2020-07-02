package com.example.demo.config.swagger;

import com.example.demo.enums.ExceptionMsg;
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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${hrs.swagger.open}")
    private Boolean openSwagger;

    /**
     * 获取head头信息配置
     *
     * @return
     */
    private List<Parameter> getPars() {
        List<Parameter> pars = new ArrayList<>();

        // 增加token验证header
        ParameterBuilder ticketPar1 = new ParameterBuilder();
        ticketPar1.name("Authorization")
                .description("user token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER - 2)
                .required(false)//header中的Authorization参数非必填，传空也可以
                .build();
        pars.add(ticketPar1.build());    //根据每个方法名也知道当前方法在设置什么参数

        // 增加traceId header
        ParameterBuilder ticketPar2 = new ParameterBuilder();
        ticketPar2.name("traceId")
                .description("日志接口标识")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER - 1)
                .required(false)
                .build();
        pars.add(ticketPar2.build());


        return pars;
    }

    @Bean
    public Docket userApi() {

        List<Parameter> pars = getPars();

        return new Docket(DocumentationType.SWAGGER_2).groupName("用户模块")
                .enable(openSwagger)
                // 不使用默认状态码定义
                .useDefaultResponseMessages(false)
                // 自定义全局状态码
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                // 描述
                .apiInfo(apiInfo("用户模块1"))//apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
                .select()//select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.web.user"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    @Bean
    public Docket billApi() {

        List<Parameter> pars = getPars();

        return new Docket(DocumentationType.SWAGGER_2).groupName("账单模块")
                .enable(openSwagger)
                // 不使用默认状态码定义
                .useDefaultResponseMessages(false)
                // 自定义全局状态码
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                // 描述
                .apiInfo(apiInfo("账单模块1"))//apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
                .select()//select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.web.bill"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    //自定义后台Response异常信息
    List<ResponseMessage> responseMessageList =  new ArrayList<ResponseMessage>() {
        {
            add(new ResponseMessageBuilder().code(ExceptionMsg.ParamError.getCode())
                    .message(ExceptionMsg.ParamError.getMsg()).build());
            add(new ResponseMessageBuilder().code(ExceptionMsg.FAILED.getCode())
                    .message(ExceptionMsg.FAILED.getMsg()).build());
            add(new ResponseMessageBuilder().code(ExceptionMsg.FriendTrip.getCode())
                    .message(ExceptionMsg.FriendTrip.getMsg()).build());
            add(new ResponseMessageBuilder().code(ExceptionMsg.SERVER_ERROR.getCode())
                    .message(ExceptionMsg.SERVER_ERROR.getMsg()).build());
            add(new ResponseMessageBuilder().code(ExceptionMsg.LOGINOUT.getCode())
                    .message(ExceptionMsg.LOGINOUT.getMsg()).build());
        }
    };


    /**
     * 接口描述
     * @param des String 描述
     * @return
     */
    private ApiInfo apiInfo(String des) {
        Contact contact = new Contact("yy", "#", null);
        return new ApiInfoBuilder().title("未知项目").description(des)
                .contact(contact)
                .version("0.0.1")
                .build();
    }
}
