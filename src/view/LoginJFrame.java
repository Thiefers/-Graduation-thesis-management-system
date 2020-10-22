package view;

import javax.swing.JFrame;

public class LoginJFrame extends JFrame {
	private static final long serialVersionUID = 4219954475526887726L;

	public static void main(String[] args) {
		JFrame frame = new JFrame("µÇÂ¼");
		LoginJPanel lp = new LoginJPanel();
		frame.add(lp);

		frame.setSize(200, 222);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
