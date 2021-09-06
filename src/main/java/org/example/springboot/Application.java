package org.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing // JPA Auditing 활성화
// @EnableJpaAuditing를 사용하기 위해선 최소 하나의 @Entity 클래스가 필요
// @WebMvcTest이다 본 당연히 X
// 분리.. 뭔말이여
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
