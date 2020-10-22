package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Administrator;

public class AdministratorJFrame extends JFrame {
	private static final long serialVersionUID = -1743889892850498696L;
	private JFrame jFrame;
	private JPanel mainJPanel;
	private JPanel selectJPanel;
	private JPanel resetJPanel;
	private JPanel addJPanel;
	private JPanel delJPanel;
	private JPanel sendJPanel;
	private JPanel readJPanel;

	public AdministratorJFrame() {
		jFrame = new JFrame("管理员");
		initComponent();
		jFrame.add(mainJPanel);

		jFrame.setSize(470, 400);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jFrame.setVisible(true);

	}

	private void initComponent() {
		mainJPanel = (JPanel) this.getContentPane();
		mainJPanel.setLayout(new GridLayout(3, 2));

		JButton selectButton = new JButton("查询用户");
		JButton resetButton = new JButton("重置密码");
		JButton addButton = new JButton("添加用户");
		JButton delButton = new JButton("删除用户");
		JButton sendButton = new JButton("发布通知");
		JButton readButton = new JButton("查阅资料");

		mainJPanel.add(selectButton);
		mainJPanel.add(resetButton);
		mainJPanel.add(addButton);
		mainJPanel.add(delButton);
		mainJPanel.add(sendButton);
		mainJPanel.add(readButton);

		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initSelectJPanel();
				jFrame.add(selectJPanel);
				mainJPanel.setVisible(false);
				selectJPanel.setVisible(true);
			}
		});
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initResetJPanel();
				jFrame.add(resetJPanel);
				mainJPanel.setVisible(false);
				resetJPanel.setVisible(true);
			}
		});
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initAddJPanel();
				jFrame.add(addJPanel);
				mainJPanel.setVisible(false);
				addJPanel.setVisible(true);
			}
		});
		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initDelJPanel();
				jFrame.add(delJPanel);
				mainJPanel.setVisible(false);
				delJPanel.setVisible(true);
			}
		});
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initSendJPanel();
				jFrame.add(sendJPanel);
				mainJPanel.setVisible(false);
				sendJPanel.setVisible(true);
			}
		});
		readButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initReadJPanel();
				jFrame.add(readJPanel);
				mainJPanel.setVisible(false);
				readJPanel.setVisible(true);
			}
		});
	}

	private JButton initBackButton(String JPanelName) {
		JButton back = new JButton("返回");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (JPanelName) {
				case "selectJPanel":
					selectJPanel.setVisible(false);
					break;
				case "resetJPanel":
					resetJPanel.setVisible(false);
					break;
				case "addJPanel":
					addJPanel.setVisible(false);
					break;
				case "delJPanel":
					delJPanel.setVisible(false);
					break;
				case "sendJPanel":
					sendJPanel.setVisible(false);
					break;
				case "readJPanel":
					readJPanel.setVisible(false);
					break;
				default:
					break;
				}
				mainJPanel.setVisible(true);
			}
		});
		return back;
	}

	private void initSelectJPanel() {
		selectJPanel = new JPanel();
		selectJPanel.setLayout(new GridLayout(3, 1));
		// 顶端查询
		JPanel panel0 = new JPanel();
		selectJPanel.add(panel0);
		JLabel inputTip = new JLabel("输入学号/工号");
		panel0.add(inputTip);
		JTextField inputMsg = new JTextField(10);
		panel0.add(inputMsg);
		// 按钮
		JPanel panel1 = new JPanel();
		JButton select = new JButton("查询");
		panel1.add(select);
		selectJPanel.add(panel1);
		panel1.add(initBackButton("selectJPanel"));

		JPanel panel2 = new JPanel();
		selectJPanel.add(panel2);
		JLabel outputMsg = new JLabel();
		panel2.add(outputMsg);
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 返回信息
				String id = inputMsg.getText();
				String returnMsg = "";
				if (Administrator.studentQuery(id).trim().length() != 0) {
					returnMsg = Administrator.studentQuery(id);
				} else if (Administrator.teacherQuery(id).trim().length() != 0) {
					returnMsg = Administrator.teacherQuery(id);
				}
				if (!"".equals(outputMsg.getText())) {
					outputMsg.setText("");
				}
				if (!"".equals(returnMsg)) {
					outputMsg.setText(returnMsg);
				} else if (id.trim().length() != 0) {
					outputMsg.setText("查无此人");
				} else {
					outputMsg.setText("输入错误或不符合规范");
				}
			}
		});
	}

	private void initResetJPanel() {
		resetJPanel = new JPanel();
		resetJPanel.setLayout(new GridLayout(3, 1));
		// 输入
		JPanel panel0 = new JPanel();
		resetJPanel.add(panel0);
		JLabel inputTip = new JLabel("输入学号/工号");
		panel0.add(inputTip);
		JTextField inputMsg = new JTextField(10);
		panel0.add(inputMsg);
		// 按钮
		JPanel panel1 = new JPanel();
		JButton reset = new JButton("重置");
		panel1.add(reset);
		resetJPanel.add(panel1);
		panel1.add(initBackButton("resetJPanel"));

		JPanel panel2 = new JPanel();
		resetJPanel.add(panel2);
		JLabel outputMsg = new JLabel();
		panel2.add(outputMsg);
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 返回信息
				String id = inputMsg.getText();
				if (id.trim().length() != 0) {
					if (Administrator.studentReset(id) || Administrator.teacherReset(id)) {
						outputMsg.setText("重置密码成功");
					} else {
						outputMsg.setText("重置密码失败");
					}
				} else {
					outputMsg.setText("输入不规范");
				}
			}
		});
	}

	private void initAddJPanel() {
		addJPanel = new JPanel();
		addJPanel.setLayout(new GridLayout(4, 1));

		JPanel panel0 = new JPanel();
		addJPanel.add(panel0);
		JLabel tip = new JLabel("填入信息");
		panel0.add(tip);
		JTable table = new JTable(2, 2);
		panel0.add(table);
		table.setValueAt("学号/工号", 0, 0);
		table.setValueAt("姓名", 0, 1);

		JPanel panel1 = new JPanel();
		addJPanel.add(panel1);
		JButton addStuButton = new JButton("添加学生");
		panel1.add(addStuButton);
		JButton addTeaButton = new JButton("添加导师");
		panel1.add(addTeaButton);
		panel1.add(initBackButton("addJPanel"));

		JPanel panel2 = new JPanel();
		addJPanel.add(panel2);
		JLabel outputMsg = new JLabel();
		panel2.add(outputMsg);
		addStuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = (String) table.getValueAt(1, 0);
				String name = (String) table.getValueAt(1, 1);
				if (outputMsg.getText().length() != 0) {
					outputMsg.setText("");
				}
				if (!"".equals(id) && !"".equals(name)) {
					if (Administrator.studentAdd(id, name)) {
						outputMsg.setText("添加学生成功");
					} else {
						outputMsg.setText("添加学生失败");
					}
				} else {
					outputMsg.setText("输入非法");
				}
			}
		});
		addTeaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = (String) table.getValueAt(1, 0);
				String name = (String) table.getValueAt(1, 1);
				if (outputMsg.getText().length() != 0) {
					outputMsg.setText("");
				}
				if (Administrator.teacherAdd(id, name)) {
					outputMsg.setText("添加导师成功");
				} else {
					outputMsg.setText("添加导师失败");
				}
			}
		});
	}

	private void initDelJPanel() {
		delJPanel = new JPanel();
		delJPanel.setLayout(new GridLayout(3, 1));

		JPanel panel0 = new JPanel();
		delJPanel.add(panel0);
		JLabel tip = new JLabel("输入学号/工号");
		panel0.add(tip);
		JTextField iMsg = new JTextField(10);
		panel0.add(iMsg);

		JPanel panel1 = new JPanel();
		delJPanel.add(panel1);
		JButton delButton = new JButton("删除");
		panel1.add(delButton);
		panel1.add(initBackButton("delJPanel"));

		JPanel panel2 = new JPanel();
		delJPanel.add(panel2);
		JLabel oMsg = new JLabel();
		panel2.add(oMsg);
		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = iMsg.getText();
				if (oMsg.getText().length() != 0) {
					oMsg.setText("");
				}
				if (Administrator.studentQuery(id).trim().length() != 0) {
					if (Administrator.studentDel(id)) {
						oMsg.setText("删除成功");
					}
				} else if (Administrator.teacherQuery(id).trim().length() != 0) {
					if (Administrator.teacherDel(id)) {
						oMsg.setText("删除成功");
					}
				} else if (id.trim().length() != 0) {
					oMsg.setText("查无此人");
				} else {
					oMsg.setText("输入不规范");
				}
			}
		});
	}

	private void initSendJPanel() {
		sendJPanel = new JPanel();
		sendJPanel.setLayout(new GridLayout(3, 1));

		JPanel panel0 = new JPanel();
		sendJPanel.add(panel0);
		JLabel tip = new JLabel("编辑");
		panel0.add(tip);
		JTextArea iMsg = new JTextArea(4, 20);
		JScrollPane jsp = new JScrollPane(iMsg);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel0.add(jsp);

		JPanel panel1 = new JPanel();
		sendJPanel.add(panel1);
		JButton sendButton = new JButton("发布");
		panel1.add(sendButton);
		panel1.add(initBackButton("sendJPanel"));

		JPanel panel2 = new JPanel();
		sendJPanel.add(panel2);
		JLabel oMsg = new JLabel();
		panel2.add(oMsg);

		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String content = iMsg.getText();
				if (content.trim().length() == 0) {
					oMsg.setText("内容无效，发布失败");
				} else if (Administrator.sendNotice(content)) {
					oMsg.setText("发布成功");
				} else {
					oMsg.setText("发布失败");
				}
			}
		});
	}

	private void initReadJPanel() {
		readJPanel = new JPanel();
		readJPanel.setLayout(new GridLayout(2, 1));

		JPanel panel0 = new JPanel();
		readJPanel.add(panel0);
		JTextField iMsg = new JTextField(10);
		panel0.add(iMsg);
		JButton queryButton = new JButton("查询");
		panel0.add(queryButton);
		panel0.add(initBackButton("readJPanel"));

		JPanel panel1 = new JPanel();
		readJPanel.add(panel1);
		JLabel oMsg = new JLabel();
		panel1.add(oMsg);

		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		JScrollPane jsp = new JScrollPane(table);
		panel1.add(jsp);
		queryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String in = iMsg.getText();
				String returnMsg = "";
				String[] os = null;
				// int sum = 0;
				if (in.trim().length() != 0) {
					if (oMsg.getText().trim().length() != 0) {
						oMsg.setText("");
					}
					if (Administrator.readData(in).length() != 0) {
						returnMsg = Administrator.readData(in);
						// System.out.println(returnMsg);
						os = returnMsg.split(";");
						// returnMsg = "";
						Vector<Vector<String>> data = new Vector<>();
						Vector<String> names = new Vector<String>();
						for (int i = 0; i < os.length; i++) {
							Vector<String> row = new Vector<String>();
							row.add(os[i]);
							data.add(row);
							// if(i < os.length) {
							// System.out.println(row.get(0));
							// }

							// oMsg.setText(os[i]);
							// JLabel addOMsg = new JLabel("<html><br></html>");
							// panel1.add(addOMsg);
							// addOMsg.setText("<html><br></html>");
							// returnMsg = os[i];
							// System.out.println(returnMsg);
						}
						names.add("content");
						model.setDataVector(data, names);
						// System.out.println(os.length);
						// System.out.println(os[0]);
						// System.out.println(os[1]);
						// os = Administrator.readData(in).split(";");
						// sum = os.length;
						// System.out.println(sum);
						// int count = 0;
					} else {
						oMsg.setText("查询无果");
					}
				} else {
					oMsg.setText("输入非法");
				}
			}
		});
	}

	public static void main(String[] args) {
		new AdministratorJFrame();
	}

}
