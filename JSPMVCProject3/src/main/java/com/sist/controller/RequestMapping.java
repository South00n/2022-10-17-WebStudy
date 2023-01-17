package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) // 저장기간 => 프로그램 종료시까지 유지
@Target(METHOD) // Method구분
/*
 *    @RequestMapping("a") ==> 사용자가 a를 보내면 display()를 호출 ==> index(찾기)
 *	  public void display() {
 *
 * 	  }
 */
public @interface RequestMapping {
	public String value();
}
