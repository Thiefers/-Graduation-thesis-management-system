package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JDBCTools;

// 管理员
public class Administrator extends User {
	// 办公室电话
	private String office_phone;

	public String getOffice_phone() {
		return office_phone;
	}

	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex
				+ ", office_phone=" + office_phone + ", mobile=" + "]";
	}

	// 功能gogogo
	// 查看资料，重置/查询信息，添加/删除用户，发布通知

	// 添加用户
	public static boolean studentAdd(String id, String name) {
		Statement statement = null;
		int count = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String school = "GDPU";
			String sex = "";
			String learn_class = "";
			String major = "";
			String teacher_id = "";
			String sql = "insert into student(id,name,password,school,sex,class,major,teacher_id) values(" + "'" + id
					+ "'," + "'" + name + "'," + "888888," + "'" + school + "'," + "'" + sex + "'," + "'" + learn_class
					+ "'," + "'" + major + "'," + "'" + teacher_id + "'" + ")";
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return count == 1 ? true : false;
	}

	public static boolean teacherAdd(String id, String name) {
		Statement statement = null;
		int count = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sex = "";
			String sql = "insert into teacher(id,name,password,sex) values(" + "'" + id + "'," + "'" + name + "',"
					+ "888888," + "'" + sex + "'" + ")";
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return count == 1 ? true : false;
	}

	// 删除用户
	@SuppressWarnings("finally")
	public static boolean studentDel(String id) {
		Statement statement = null;
		int count = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "delete from student where id='" + id + "'";
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, null);
			return count == 1 ? true : false;
		}
	}

	@SuppressWarnings("finally")
	public static boolean teacherDel(String id) {
		Statement statement = null;
		int count = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "delete from teacher where id='" + id + "'";
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, null);
			return count == 1 ? true : false;
		}
	}

	// 重置用户信息，比如：密码。就这样，没了,其他全空
	public static boolean studentReset(String id) {
		Statement statement = null;
		int count = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "update student set password=888888 where id='" + id + "'";
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, null);
		}
		return count == 1 ? true : false;
	}

	public static boolean teacherReset(String id) {
		Statement statement = null;
		int count = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "update teacher set password=888888 where id='" + id + "'";
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, null);
		}
		return count == 1 ? true : false;
	}

	// 查询用户信息
	@SuppressWarnings("finally")
	public static String studentQuery(String inputID) {
		Statement statement = null;
		ResultSet rs = null;
		String returnMsg = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select * from student where id='" + inputID + "'";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String sex = rs.getString("sex");
				String school = rs.getString("school");
				String learn_class = rs.getString("class");
				String major = rs.getString("major");
				String teacher_id = rs.getString("teacher_id");
				String mobile = rs.getString("mobile");
				returnMsg = id + "," + name + "," + password + "," + sex + "," + school + "," + learn_class + ","
						+ major + "," + teacher_id + "," + mobile;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rs);
			return returnMsg;
		}
	}

	public void studentAllQuery() {
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select * from student";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String sex = rs.getString("sex");
				String school = rs.getString("school");
				String learn_class = rs.getString("class");
				String major = rs.getString("major");
				String teacher_id = rs.getString("teacher_id");
				String mobile = rs.getString("mobile");
				System.out.println(id + "," + name + "," + password + "," + sex + "," + school + "," + learn_class + ","
						+ major + "," + teacher_id + "," + mobile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rs);
		}
	}

	@SuppressWarnings("finally")
	public static String teacherQuery(String inputID) {
		Statement statement = null;
		ResultSet rs = null;
		String returnMsg = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select * from teacher where id='" + inputID + "'";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String sex = rs.getString("sex");
				String office_phone = rs.getString("office_phone");
				String mobile = rs.getString("mobile");
				String max_number = rs.getString("max_number");
				returnMsg = id + "," + name + "," + password + "," + sex + "," + office_phone + "," + mobile + ","
						+ max_number;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rs);
			return returnMsg;
		}
	}

	public void teacherAllQuery() {
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select * from teacher";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String sex = rs.getString("sex");
				String office_phone = rs.getString("office_phone");
				String mobile = rs.getString("mobile");
				String max_number = rs.getString("max_number");
				System.out.println(id + "," + name + "," + password + "," + sex + "," + office_phone + "," + mobile
						+ "," + max_number);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rs);
		}
	}

	// 发布通知
	@SuppressWarnings("finally")
	public static boolean sendNotice(String content) {
		Statement statement = null;
		int count = 0;
		try {
			statement = JDBCTools.getConnection().createStatement();
			String title = "Notice";
			String receiver = "ALL";
			String sender = "BOSS";
			String sql = "insert into notice(title,receiver,sender,content) values('" + title + "','" + receiver + "','"
					+ sender + "','" + content + "')";
			count = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, null);
			return count == 1 ? true : false;
		}

	}

	// 查看资料
	@SuppressWarnings("finally")
	public static String readData(String iMsg) {
		Statement statement = null;
		ResultSet rSet = null;
		String oMsg = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = "select id,title from data where title like '%" + iMsg + "%' and permission=" + 0;
			rSet = statement.executeQuery(sql);
			while (rSet.next()) {
				String tmpMsg = "编号" + rSet.getString("id") + "：" + rSet.getString("title");
				// String tmpMsg = rSet.getString("title");
				oMsg = oMsg + tmpMsg + ";";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, statement, rSet);
			return oMsg;
		}
	}

	public static void main(String[] args) {
		Administrator administrator = new Administrator();
		// 重置学生
		// if (administrator.studentReset("1600502614")) {
		// System.out.println("修改成功");
		// } else {
		// System.out.println("修改失败");
		// }
		// 重置教师
		// if (administrator.teacherReset("1600502614")) {
		// System.out.println("修改成功");
		// } else {
		// System.out.println("修改失败");
		// }
		// 添加学生
		// if (administrator.studentAdd()) {
		// System.out.println("添加成功");
		// } else {
		// System.out.println("添加失败");
		// }
		// 添加老师
		// if (administrator.teacherAdd()) {
		// System.out.println("添加成功");
		// } else {
		// System.out.println("添加失败");
		// }
		// 删除学生

		// 删除老师

		// 查询学生
		// Administrator.studentQuery("123456");
		administrator.studentAllQuery();
		// 查询导师
		// administrator.teacherQuery();

	}
}
