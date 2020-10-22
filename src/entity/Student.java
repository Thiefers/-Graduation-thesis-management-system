package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.JDBCTools;

// 学生
public class Student extends User {
	// 学院
	private String school;
	// 班级
	private String class_name;
	// 专业
	private String major;
	// 导师
	private String teacher_id;
	// 联系电话
	private String mobile;
	// 课题辅助量
	private String select_topic = "null";
	// 课题
	private Topic topic;
	// 发送出去的资料ID
	private ArrayList<Integer> dataIDList;

	public ArrayList<Integer> getDataIDList() {
		return dataIDList;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSelect_topic() {
		return select_topic;
	}

	public void setSelect_topic(String select_topic) {
		this.select_topic = select_topic;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", school=" + school
				+ ", class_name=" + class_name + ", major=" + major + ", teacher_id=" + teacher_id + ", mobile="
				+ mobile + "]";
	}

	// 功能gogogo
	// 修改信息
	@SuppressWarnings("finally")
	public boolean changeInfo(String iid, String password, String sex, String learn_class, String major,
			String teacher_id, String mobile) {
		Statement statement = null;
		int count = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			// password,sex,school,class,major,teacher_id,mobile
			String sql = "update student set password='" + password + "',sex='" + sex + "',class='" + learn_class
					+ "',major='" + major + "',teacher_id='" + teacher_id + "',mobile='" + mobile + "' where id='" + iid
					+ "'";
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, null);
			return count > 0 ? true : false;
		}

	}

	// 选题
	// public void selectTopic() {
	//
	// }

	// 查看通知
	// public void readNotice() {
	//
	// }

	// 查看资料
	// public void readData() {
	//
	// }

	// 上传资料
	@SuppressWarnings("finally")
	public boolean sendData(String sid, String sname, String content) {
		Statement statement = null;
		int count = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			int permission = 0;
			String sql = "insert into data(sender_id,sender_name,title,permission) values('" + sid + "','" + sname
					+ "','" + content + "'," + permission + ")";
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, null);
			return count > 0 ? true : false;
		}
	}

	// 查看评价
	@SuppressWarnings("finally")
	public String readCommand(String stuID) {
		Statement statement = null;
		ResultSet rSet = null;
		String returnMsg = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select guider_command from comment where sid='" + stuID + "'";
			rSet = statement.executeQuery(sql);
			while (rSet.next()) {
				String tmp = rSet.getString("guider_command") + "<br>";
				returnMsg = returnMsg + tmp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rSet);
			return returnMsg;
		}
	}

	@SuppressWarnings("finally")
	public String getTeacIDByTopic(int topicID) {
		// 连接topic表，拿到creater
		// 连接teacher表，拿到id
		// 拿到id后new一个teacher对象
		// 然后更改checknum + 1
		// select a.id from teacher a,topic b where a.name=b.creater and b.id=iMsg
		Statement statement = null;
		ResultSet rSet = null;
		String returnMsg = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			// select a.id from teacher a,topic b where a.name=b.creater and b.id=iMsg
			String sql = "select a.id from teacher a,topic b where a.name=b.creater and b.id=" + topicID;
			rSet = statement.executeQuery(sql);
			while (rSet.next()) {
				returnMsg = rSet.getString("a.id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rSet);
			return returnMsg;
		}
	}

	@SuppressWarnings("finally")
	public boolean updateTeacID(String stuID, String teacID) {
		Statement statement = null;
		int count = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "update student set teacher_id='" + teacID + "' where id='" + stuID + "'";
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, null);
			return count > 0 ? true : false;
		}
	}

	@SuppressWarnings("finally")
	public boolean hasTeacher(String stuID) {
		Statement statement = null;
		ResultSet rSet = null;
		String msg = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select teacher_id from student where id='" + stuID + "'";
			rSet = statement.executeQuery(sql);
			while (rSet.next()) {
				msg = rSet.getString("teacher_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rSet);
			return msg.trim().length() > 6 ? true : false;
		}
	}
}
