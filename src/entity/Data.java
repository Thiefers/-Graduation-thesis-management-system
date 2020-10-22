package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JDBCTools;

// 资料
public class Data {
	// 资料编号，自增
	private static int id = 0;
	// 资料上传者id
	private String sender_id;
	// 资料上传者姓名
	private String sender_name;
	// 资料标题
	private String title;
	// 阅读权限
	private boolean permission;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public String getSender_name() {
		return sender_name;
	}

	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", sender_id=" + sender_id + ", sender_name=" + sender_name + ", title=" + title
				+ ", permission=" + permission + "]";
	}

	@SuppressWarnings("finally")
	public static String showAllReadable(String sender_id) {
		Statement statement = null;
		ResultSet rSet = null;
		String returnMsg = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			// 拿出所有其他人可读的以及自己所有的
			String sql = "select id,sender_name,title from data where permission=0 and sender_id!='" + sender_id
					+ "' UNION select id,sender_name,title from data where sender_id='" + sender_id + "'";
			rSet = statement.executeQuery(sql);
			while (rSet.next()) {
				String tmp = rSet.getString("id") + "(" + rSet.getString("sender_name") + ")："
						+ rSet.getString("title");
				returnMsg = returnMsg + tmp + "|";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rSet);
			return returnMsg;
		}
	}
}
