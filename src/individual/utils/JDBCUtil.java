package individual.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
	private static Properties prop = null;
	static{
		InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("JDBCDao.properties");
		System.out.print("输入的数据库配置文件对象："+in);
		try {
			prop = new Properties();
			prop.load(in);
			Class.forName(prop.getProperty("driverClassName"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		catch (ClassNotFoundException e1) {
			throw new RuntimeException(e1);
		}
	}
	public static Connection getCon() throws SQLException{
		return DriverManager.getConnection(prop.getProperty("url"), 
				prop.getProperty("username"), 
				prop.getProperty("password"));
	}
}
