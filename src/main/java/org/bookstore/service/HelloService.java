package org.bookstore.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {
	public String hello(String name) {
		return "hello " + name;
	}
}
