package org.koushik.javabrains;

import org.koushik.javabrains.dao.JdbcDaoImpl;
import org.koushik.javabrains.dao.SimpleJdbcDaoImpl;
import org.koushik.javabrains.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		SimpleJdbcDaoImpl dao = context.getBean("simpleJdbcDaoImpl",SimpleJdbcDaoImpl.class);
		System.out.println(dao.getCircleCount());
//		Circle circle = dao.getCircle(2);
//		System.out.println(circle.getName());
//		dao.insertCircle(new Circle(6,"sixth Circle"));
//		System.out.println(dao.getAllCircles().size());
//		dao.createTriangle();
	}

}
