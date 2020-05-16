package org.koushik.javabrains.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import org.koushik.javabrains.model.Circle;

public class JdbcDaoImpl {
	
	public Circle getCircle(int circleId) {
	
	String dbUrl="jdbc:mysql://localhost:3306/mytestdb";
    String username="root";
    String password="Abhi2212";
	
	Connection conn = null;
	try {
	conn = DriverManager.getConnection(dbUrl,username,password);
	PreparedStatement ps = conn.prepareStatement("SELECT * FROM circle where id = ? " );
	ps.setInt(1,circleId);
	
	Circle circle = null ;
	ResultSet rs = ps.executeQuery();
	if(rs.next()) {
		circle = new Circle(circleId,rs.getString("name"));
	}
	rs.close();
	ps.close();
	return circle;
	} catch(Exception e) {
		throw new RuntimeException(e);
	}
	finally {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
}
