package com.neuedu.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
    * 工具类
 * @author Administrator
 */

public class DBUtils {
	//配置文件对象
	private static Properties prop;
	
	static {
		prop = new Properties();
		try {
			prop.load(DBUtils.class.getResourceAsStream("/mysql.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//拿到配置文件的url
	private static String URL = prop.getProperty("url");
	//拿到配置文件的用户名
	private static String USERNAME = prop.getProperty("username");
	//拿到配置文件的密码
	private static String PASSWORD = prop.getProperty("password");
	//获取连接
	public static Connection getConnection()
	{
		Connection conn = null;
		try{
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	
	}	
 }
