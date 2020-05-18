package org.koushik.javabrains.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class SimpleJdbcDaoImpl extends JdbcTemplate {
	
	public int getCircleCount() {
//		String sql = "SELECT COUNT(*) circle" ;
		 String sql="SELECT COUNT(*) FROM circle";  
		return  this.queryForObject(sql, Integer.class);
	}

}
