package com.young.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        // 스프링부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정됩니다.
        /*
        SpringApplication.run으로 내장 WAS를 실행합니다.

        내장 WAS(Web Application Server, 웹 애플리케이션 서버)란 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것입니다.
        톰캣을 설치할 필요가 없게 되고 스프링 부트로 만들어진 Jar파일로 실행하면 됩니다.
         */
    }
}
