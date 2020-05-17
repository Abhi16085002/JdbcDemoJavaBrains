package org.koushik.javabrains.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import java.sql.DriverManager;

import org.koushik.javabrains.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class JdbcDaoImpl {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate ;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public int getCircleCount() {
		String sql = "SELECT COUNT(*) circle" ;
		return  jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public String getcircleName(int circleId) {
		String sql = "Select name from circle Where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { circleId } , String.class);
	}
	
	public Circle getCircleForId (int circleId) {
		String sql = "Select * from circle where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object [] {circleId }, new CircleMapper() ) ;
	}
	
	public List<Circle> getAllCircles () {
		String sql = "Select * from circle";
		return jdbcTemplate.query(sql, new CircleMapper());
	}

//	public void insertCircle(Circle circle) {
//		String sql = "Insert into circle (ID,name) values (?,?) ";
//		jdbcTemplate.update(sql, new Object[] {circle.getId(), circle.getName()} );
//	}
	
	public void insertCircle(Circle circle) {
		String sql = "Insert into circle (ID,name) values (:id,:name) ";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id",circle.getId())
				.addValue("name", circle.getName());
		namedParameterJdbcTemplate.update(sql, namedParameters);	
	}
	
	public void createTriangle() {
		String sql = "Create Table triangle (ID INTEGER ,NAME VARCHAR(50)) ";
		jdbcTemplate.execute(sql);
	}
	
	private static final class CircleMapper implements RowMapper<Circle> {

		@Override
		public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			
			Circle circle = new Circle() ;
			circle.setId(resultSet.getInt("ID"));
			circle.setName(resultSet.getString("name"));
			
			return circle;
		}
		
	}
}
