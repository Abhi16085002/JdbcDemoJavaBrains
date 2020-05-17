package org.koushik.javabrains.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import java.sql.DriverManager;

import org.koushik.javabrains.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcDaoImpl {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate = new JdbcTemplate() ;
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getCircleCount() {
		String sql = "SELECT COUNT(*) circle" ;
		return  jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public String getcircleName(int circleId) {
		String sql = "Select name from circle Where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { circleId } , String.class);
	}
	
//	public Circle getCircleForId (int circleId) {
//		String sql = "Select * from circle where id = ?";
//		return jdbcTemplate.queryForObject(sql, new Object [] {circleId }, )
//	}

	public Circle getCircle(int circleId) {
	
	
	Connection conn = null;
	try {
	conn = dataSource.getConnection();
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
