package view;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.JDBCTools;

public class LoginJPanel extends JPanel implements MouseListener {
	private static final long serialVersionUID = -4034249158607103033L;
	private JPanel panel0;
	private JLabel title;
	JPanel panel1;
	JLabel username;
	JTextField usernametxt;
	JPanel panel2;
	JLabel password;
	JPasswordField passwordField;
	JPanel panel3;
	JLabel position;
	JComboBox positionCheck;
	JPanel panel4;
	JButton loginButton;

	public LoginJPanel() {
		this.setLayout(new GridLayout(5, 1));
		init();
	}

	public JPanel getPanel0() {
		return panel0;
	}

	public void setPanel0(JPanel panel0) {
		this.panel0 = panel0;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JTextField getUsernametxt() {
		return usernametxt;
	}

	public void setUsernametxt(JTextField usernametxt) {
		this.usernametxt = usernametxt;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public void setPanel3(JPanel panel3) {
		this.panel3 = panel3;
	}

	public JComboBox getPositionCheck() {
		return positionCheck;
	}

	public void setPositionCheck(JComboBox positionCheck) {
		this.positionCheck = positionCheck;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public void setPanel4(JPanel panel4) {
		this.panel4 = panel4;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}

	public void init() {
		// 标题
		panel0 = new JPanel();
		title = new JLabel("毕业论文管理系统");
		this.add(panel0);
		panel0.add(title);
		// 用户名
		panel1 = new JPanel();
		username = new JLabel("用户名");
		usernametxt = new JTextField(10);
		this.add(panel1);
		panel1.add(username);
		panel1.add(usernametxt);
		// 密码
		panel2 = new JPanel();
		password = new JLabel("密    码");
		passwordField = new JPasswordField(10);
		this.add(panel2);
		panel2.add(password);
		panel2.add(passwordField);
		// 身份选择
		panel3 = new JPanel();
		position = new JLabel("身份认证");
		positionCheck = new JComboBox();
		positionCheck.addItem("管理员");
		positionCheck.addItem("导师");
		positionCheck.addItem("学生");
		this.add(panel3);
		panel3.add(position);
		panel3.add(positionCheck);
		// 登录按钮
		panel4 = new JPanel();
		loginButton = new JButton("登录");
		this.add(panel4);
		panel4.add(loginButton);
		loginButton.addMouseListener(this);
	}

	@SuppressWarnings("finally")
	public String userQuery(String s) {
		Statement statement = null;
		ResultSet rSet = null;
		String selectPassword = "";
		try {
			statement = JDBCTools.getConnection().createStatement();
			String sql = s;
			rSet = statement.executeQuery(sql);
			while (rSet.next()) {
				selectPassword = rSet.getString("password");
//				System.out.println(selectPassword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return selectPassword;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JFrame jFrame = null;
		String username = "";
		String password = "";
		if ("管理员".equals(this.getPositionCheck().getSelectedItem())) {
			jFrame = (JFrame) (this.getRootPane().getParent());
			username = this.getUsernametxt().getText();
			password = this.getPasswordField().getText();
			if (username.length() > 0 && password.length() > 0) {
				String sql = "select password from administrator where id='" + username + "'";
				if (password.equals(userQuery(sql))) {
					jFrame.dispose();
					new AdministratorJFrame();
				} else {
					new LoginErrorJFrame();
				}
			}
		} else if ("导师".equals(this.getPositionCheck().getSelectedItem())) {
			jFrame = (JFrame) (this.getRootPane().getParent());
			username = this.getUsernametxt().getText();
			password = this.getPasswordField().getText();
			if (username.length() > 0 && password.length() > 0) {
				String sql = "select password from teacher where id='" + username + "'";
				if (password.equals(userQuery(sql))) {
					jFrame.dispose();
					new TeacherJFrame(username.trim());
				} else {
					new LoginErrorJFrame();
				}
			}
		} else if ("学生".equals(this.getPositionCheck().getSelectedItem())) {
			jFrame = (JFrame) (this.getRootPane().getParent());
			username = this.getUsernametxt().getText();
			password = this.getPasswordField().getText();
			if (username.length() > 0 && password.length() > 0) {
				String sql = "select password from student where id='" + username + "'";
				if (password.equals(userQuery(sql))) {
					jFrame.dispose();
					new StudentJFrame(username.trim());
				} else {
					new LoginErrorJFrame();
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}