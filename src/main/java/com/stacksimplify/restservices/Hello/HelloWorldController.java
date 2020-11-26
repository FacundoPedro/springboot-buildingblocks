package com.stacksimplify.restservices.Hello;


import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  HelloWorldController {

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
}
