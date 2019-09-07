package me.yang.restapi.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
// 코드를 얼마나 가져갈꺼냐
@Retention(RetentionPolicy.SOURCE)
public @interface TestDescription {

    String value();

}
