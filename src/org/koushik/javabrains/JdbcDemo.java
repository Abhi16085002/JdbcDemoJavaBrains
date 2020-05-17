package org.koushik.javabrains;

import org.koushik.javabrains.dao.JdbcDaoImpl;
import org.koushik.javabrains.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		JdbcDaoImpl dao = context.getBean("jdbcDaoImpl",JdbcDaoImpl.class);
		Circle circle = dao.getCircle(2);
		System.out.println(circle.getName());
		System.out.println(dao.getcircleName(2));
	}

}
