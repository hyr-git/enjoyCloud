package com.enjoy.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoy.vo.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/get/{name}")
	public Object get(@PathVariable("name")String name) {
		User user = new User();
		user.setName(name);
		user.setAge(18);
        user.setSex("F");
		return user;
	}
}
