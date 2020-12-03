package com.stacksimplify.restservices.Hello;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class  HelloWorldController {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    //URI - /helloworld
    //GET
    //@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
    @GetMapping("/helloworld1")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean(){
        return new UserDetails("Facundo", "Aguero", "Montevideo");
    }

    @GetMapping("/hello-int")
    public String getMessageIn18nFormat(@RequestHeader(name = "Accept-Language", required = false) String locale){
        return messageSource.getMessage("label.hello", null, new Locale(locale));

    }

    @GetMapping("/hello-int2")
    public String getMessageIn18nFormat2( ){
        return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());

    }
}
