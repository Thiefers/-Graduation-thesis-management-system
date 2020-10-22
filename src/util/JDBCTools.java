package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCTools {
	static ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
	static String driver = bundle.getString("driver");
	static String url = bundle.getString("url");
	static String user = bundle.getString("user");
	static String password = bundle.getString("password");

	@SuppressWarnings("finally")
	public static Connection getConnection() {
		// 注册、连接
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return connection;
		}
	}

	public static void release(Connection connection, Statement statement, ResultSet resultSet) {
		// 释放资源
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
