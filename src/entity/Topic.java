package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JDBCTools;

// 课题
public class Topic {
	// 课题编号，自增
	private int id = 0;
	// 课题题目
	private String name;
	// 课题提出者
	private String creater;
	// 课题简介/要求
	private String introduction;
	// 课题已选人数-->达到上限则不可再选取
	private static int checked_num;
	// 课题最多可选人数
	private static final int MAX_NUM = 1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getChecked_num() {
		return checked_num;
	}

	public void setChecked_num(int checked_num) {
		this.checked_num = checked_num;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", name=" + name + ", creater=" + creater + ", introduction=" + introduction
				+ ", checked_num=" + checked_num + "]";
	}

	@SuppressWarnings("finally")
	public static String showAllTopic() {
		Statement statement = null;
		ResultSet rSet = null;
		String returnMsg = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select * from topic where checked_num<" + MAX_NUM;
			rSet = statement.executeQuery(sql);
			while (rSet.next()) {
				String tmpMsg = "课题" + rSet.getString("id") + "：" + rSet.getString("name") + "，出题者："
						+ rSet.getString("creater") + "，已选人数：" + rSet.getString("checked_num") + "<br>" + "课题简介："
						+ rSet.getString("introduction") + "<br>";
				returnMsg = returnMsg + tmpMsg;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rSet);
			return returnMsg;
		}
	}

	@SuppressWarnings("finally")
	public static String showOneTopic(String iStuID) {
		Statement statement = null;
		ResultSet rSet = null;
		String returnMsg = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select name,creater,introduction from topic where id=" + Integer.valueOf(iStuID);
			rSet = statement.executeQuery(sql);
			while (rSet.next()) {
				String tmpMsg = rSet.getString("name") + "," + rSet.getString("creater") + ","
						+ rSet.getString("introduction");
				returnMsg = returnMsg + tmpMsg;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rSet);
			return returnMsg;
		}
	}

	public static void updateTopic(int id) {
		Statement statement = null;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "update topic set checked_num=1 where id=" + id;
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("finally")
	public static boolean addTopic(String name, String creater, String introduction) {
		Statement statement = null;
		int count = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "insert into topic(name,creater,introduction,checked_num) values('" + name + "','" + creater
					+ "','" + introduction + "'," + 0 + ")";
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, null);
			return count > 0 ? true : false;
		}
	}

	@SuppressWarnings("finally")
	public static String showGrantableTopic(String sender_id) {
		Statement statement = null;
		ResultSet rSet = null;
		String returnMsg = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select id,title from data where sender_id='" + sender_id + "' and permission=" + 1;
			rSet = statement.executeQuery(sql);
			while (rSet.next()) {
				String tmp = "课题" + rSet.getString("id") + "：" + rSet.getString("title");
				returnMsg = returnMsg + tmp + "|";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rSet);
			return returnMsg;
		}
	}

	@SuppressWarnings("finally")
	public static String showRevokeableTopic(String sender_id) {
		Statement statement = null;
		ResultSet rSet = null;
		String returnMsg = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select id,title from data where sender_id='" + sender_id + "' and permission=" + 0;
			rSet = statement.executeQuery(sql);
			while (rSet.next()) {
				String tmp = "课题" + rSet.getString("id") + "：" + rSet.getString("title");
				returnMsg = returnMsg + tmp + "|";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rSet);
			return returnMsg;
		}
	}

	@SuppressWarnings("finally")
	public static boolean updateTeacMaxNumOfTopic(String teacID) {
		Statement statement = null;
		ResultSet rSet = null;
		int count = 0;
		int num = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select max_number from teacher where id='" + teacID + "'";
			rSet = statement.executeQuery(sql);
			while (rSet.next()) {
				num = Integer.valueOf(rSet.getString("max_number"));
			}
			if (num == 0) {
				count = 0;
			} else {
				sql = "update teacher set max_number=max_number-1 where id='" + teacID + "'";
				count = statement.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rSet);
			return count > 0 ? true : false;
		}
	}
}
