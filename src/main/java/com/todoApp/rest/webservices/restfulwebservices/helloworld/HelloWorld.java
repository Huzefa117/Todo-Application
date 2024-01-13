package com.todoApp.rest.webservices.restfulwebservices.helloworld;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//REST API
@RestController
public class HelloWorld {

  private MessageSource messageSource;

  public HelloWorld(MessageSource messageSource){
    this.messageSource = messageSource;
  }

  //Basic-Auth
  @GetMapping(path = "/basicauth")
  public String basicAuthCheck(){
    return "Success";
  }
  //hello-world
  @GetMapping(path = "/hello-world")
  public String helloWorld(){
    return "Hello World";
  }
  //hello-world
  @GetMapping(path = "/hello-world-bean")
  public HelloWorldBean helloWorldBean(){
    return new HelloWorldBean("Hello World Bean");
  }

  @GetMapping(path = "/hello-world-internationalized")
  public String helloWorldInternationalized(){
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
  }


}
