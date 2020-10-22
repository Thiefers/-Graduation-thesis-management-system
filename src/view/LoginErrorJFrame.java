package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginErrorJFrame extends JFrame {
	private static final long serialVersionUID = 3371643003107725463L;

	public LoginErrorJFrame() {
		JFrame jFrame = new JFrame();
		JPanel mainPanel = (JPanel) jFrame.getContentPane();
		mainPanel.setLayout(new GridLayout(2, 1));

		JPanel panel1 = new JPanel();
		mainPanel.add(panel1);
		JLabel tip = new JLabel("用户名/密码/身份认证错误");
		panel1.add(tip);

		JPanel panel2 = new JPanel();
		mainPanel.add(panel2);
		JButton checked = new JButton("确定");
		panel2.add(checked);
		checked.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.setSize(234, 100);
		jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new LoginErrorJFrame();
	}

}
