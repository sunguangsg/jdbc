package org.dc.jdbc.example;

import org.dc.jdbc.core.ConnectionManager;
import org.dc.jdbc.core.DBHelper;
import org.dc.jdbc.example.entity.User;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 1111111111
 * 注释
 * @author 1
 *
 */
public class MainTest {
	public static void main(String[] args) {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("jdbc:mysql://127.0.0.1:3306/myjdbc_test");
		dataSource.setName("root");
		dataSource.setPassword("root");
		/*HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/myjdbc_test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("root");*/
		
		DBHelper testDbHelper = new DBHelper(dataSource);
		ConnectionManager.setTransaction(true);//设置开启事务
		try {
			User user = testDbHelper.selectOne("select * from user where id = ? and real_name = ?",User.class,3,"dc");
			System.out.println(user.toString());
			
			ConnectionManager.commitAll();
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionManager.rollbackAll();
		}finally {
			ConnectionManager.closeConnectionAll();
		}
	}
}
