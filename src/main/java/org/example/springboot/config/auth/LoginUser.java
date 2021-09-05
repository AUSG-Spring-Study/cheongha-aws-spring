package org.example.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 이 어노테이션이 생성될 수 있는 위치 지정
// PARAMETER로 지저했으니 메소드의 파라미터로 선언된 객체에서만 사용 가능
// 클래스 선언문에 쓸 수 있는 TYPE 등이 있다.
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
// IndexController에서 세션을 가져오는 부분 개선을 위해
// 메소드 인자로 세션값을 바로 받을 수 있도록 변경하는 @LoginUser 어노테이션 생성
// 이 파일을 어노테이션 클래스로 지정
// LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면된다.
public @interface LoginUser {
}
