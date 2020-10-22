package view;

import java.awt.Dimension;
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
import entity.Data;
import entity.Notice;
import entity.Teacher;
import entity.Topic;

public class TeacherJFrame extends JFrame {
	private static final long serialVersionUID = -8196403134983832890L;
	private JFrame jFrame;
	private JPanel mainJPanel;
	private JPanel sendTopicJPanel;
	private JPanel changeInfoJPanel;
	private JPanel readDataJPanel;
	private JPanel sendDataJPanel;
	private JPanel readNoticeJPanel;
	private JPanel sendCommandJPanel;
	private JPanel grantJPanel;
	private JPanel revokeJPanel;
	// 给与评价
	private Teacher teacher;

	public TeacherJFrame() {
		jFrame = new JFrame("导师");
		initComponent();
		jFrame.add(mainJPanel);
		teacher = new Teacher();
//		teacher.setId("11300502101");
//		11003021211
		teacher.setId("2300104101");

		jFrame.setSize(470, 400);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}

	public TeacherJFrame(String teaID) {
		jFrame = new JFrame("导师");
		initComponent();
		jFrame.add(mainJPanel);
		teacher = new Teacher();
		teacher.setId(teaID);

		jFrame.setSize(470, 400);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}

	private void initComponent() {
		mainJPanel = new JPanel();
		mainJPanel.setLayout(new GridLayout(4, 2));

		JButton sendTopicButton = new JButton("出题");
		JButton changeInfoButton = new JButton("修改信息");
		JButton readDataButton = new JButton("查阅资料");
		JButton sendDataButton = new JButton("上传资料");
		JButton readNoticeButton = new JButton("查看通知");
		JButton sendCommandButton = new JButton("评价");
		JButton grantButton = new JButton("授权");
		JButton revokeButton = new JButton("回收权限");

		mainJPanel.add(sendTopicButton);
		mainJPanel.add(changeInfoButton);
		mainJPanel.add(readDataButton);
		mainJPanel.add(sendDataButton);
		mainJPanel.add(readNoticeButton);
		mainJPanel.add(sendCommandButton);
		mainJPanel.add(grantButton);
		mainJPanel.add(revokeButton);

		sendTopicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initSendTopicJPanel();
				jFrame.add(sendTopicJPanel);
				mainJPanel.setVisible(false);
				sendTopicJPanel.setVisible(true);
			}
		});
		changeInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initChangeInfoJPanel();
				jFrame.add(changeInfoJPanel);
				mainJPanel.setVisible(false);
				changeInfoJPanel.setVisible(true);
			}
		});
		readDataButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initReadDataJPanel();
				jFrame.add(readDataJPanel);
				mainJPanel.setVisible(false);
				readDataJPanel.setVisible(true);
			}
		});
		sendDataButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initSendDataJPanel();
				jFrame.add(sendDataJPanel);
				mainJPanel.setVisible(false);
				sendDataJPanel.setVisible(true);
			}
		});
		readNoticeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initReadNoticeJPanel();
				jFrame.add(readNoticeJPanel);
				mainJPanel.setVisible(false);
				readNoticeJPanel.setVisible(true);
			}
		});
		sendCommandButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initSendCommandJPanel();
				jFrame.add(sendCommandJPanel);
				mainJPanel.setVisible(false);
				sendCommandJPanel.setVisible(true);
			}
		});
		grantButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initGrantJPanel();
				jFrame.add(grantJPanel);
				mainJPanel.setVisible(false);
				grantJPanel.setVisible(true);
			}
		});
		revokeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initRevokeJPanel();
				jFrame.add(revokeJPanel);
				mainJPanel.setVisible(false);
				revokeJPanel.setVisible(true);
			}
		});
	}

	protected void initSendTopicJPanel() {
		sendTopicJPanel = new JPanel();
		sendTopicJPanel.setLayout(new GridLayout(4, 1));

		JPanel panel = new JPanel();
		sendTopicJPanel.add(panel);
		JLabel tip1 = new JLabel("题目");
		JTextField iMsg1 = new JTextField(10);
		panel.add(tip1);
		panel.add(iMsg1);
		JLabel tip2 = new JLabel("出题人");
		JTextField iMsg2 = new JTextField(10);
		panel.add(tip2);
		panel.add(iMsg2);

		JPanel panel2 = new JPanel();
		sendTopicJPanel.add(panel2);
		JLabel tip3 = new JLabel("简介");
		JTextArea iMsg3 = new JTextArea(4, 28);
		panel2.add(tip3);
		JScrollPane jsp = new JScrollPane(iMsg3);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel2.add(jsp);

		JPanel panel3 = new JPanel();
		sendTopicJPanel.add(panel3);
		JButton sendButton = new JButton("发布");
		panel3.add(sendButton);
		panel3.add(initBackButton("sendTopicJPanel"));

		JPanel panel4 = new JPanel();
		sendTopicJPanel.add(panel4);
		JLabel oMsg = new JLabel();
		panel4.add(oMsg);

		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (iMsg1.getText().trim().length() != 0 && iMsg2.getText().trim().length() != 0
						&& iMsg3.getText().trim().length() != 0) {
					String title_name = iMsg1.getText();
					String creater = iMsg2.getText();
					String introduction = iMsg3.getText();
					if (Topic.addTopic(title_name, creater, introduction)) {
						oMsg.setText("发布成功");
					} else {
						oMsg.setText("发布失败");
					}
				} else {
					oMsg.setText("发布失败");
				}
			}
		});
	}

	protected void initChangeInfoJPanel() {
		changeInfoJPanel = new JPanel();
		changeInfoJPanel.setLayout(new GridLayout(2, 1));

		JPanel panel0 = new JPanel();
		changeInfoJPanel.add(panel0);
		JTable infoTable = new JTable(5, 2);
		panel0.add(infoTable);
		// 获取基础信息
		String teaMsg = Administrator.teacherQuery(teacher.getId()).trim();
		String[] teaMsgs = teaMsg.split(",");
		infoTable.setValueAt("密码", 0, 0);
		infoTable.setValueAt(teaMsgs[2], 0, 1);
		infoTable.setValueAt("性别", 1, 0);
		infoTable.setValueAt(teaMsgs[3], 1, 1);
		infoTable.setValueAt("办公室电话", 2, 0);
		infoTable.setValueAt(teaMsgs[4], 2, 1);
		infoTable.setValueAt("个人电话", 3, 0);
		infoTable.setValueAt(teaMsgs[5], 3, 1);
		infoTable.setValueAt("指导学生数", 4, 0);
		infoTable.setValueAt(teaMsgs[6], 4, 1);
		// 按钮面板
		JPanel panel1 = new JPanel();
		changeInfoJPanel.add(panel1);
		JButton checkChangeButton = new JButton("提交");
		panel1.add(checkChangeButton);
		panel1.add(initBackButton("changeInfoJPanel"));
		// 显示修改后的结果
		JPanel panel2 = new JPanel();
		changeInfoJPanel.add(panel2);
		JLabel returnMsg = new JLabel();
		panel2.add(returnMsg);
		// 提交按钮事件
		checkChangeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取表格填写的数据
				String password = infoTable.getValueAt(0, 1).toString();
				String sex = infoTable.getValueAt(1, 1).toString();
				String office_phone = infoTable.getValueAt(2, 1).toString();
				String mobile = infoTable.getValueAt(3, 1).toString();
				String max_number = infoTable.getValueAt(4, 1).toString();
				if (teacher.changeInfo(teacher.getId(), password, sex, office_phone, mobile, max_number)) {
					// 若修改成功，显示修改后的数据
					String[] msgs = Administrator.teacherQuery(teacher.getId()).split(",");
					// 工号，姓名，密码，性别，办公室电话，个人电话，指导学生数
					String msg = "工号：" + teacher.getId() + "<br>姓名：" + msgs[1] + "<br>密码：" + msgs[2] + "<br>性别："
							+ msgs[3] + "<br>办公室电话：" + msgs[4] + "<br>个人电话：" + msgs[5] + "<br>指导学生数：" + msgs[6];
					returnMsg.setText("<html>修改成功<br>" + msg + "</html>");
				} else {
					returnMsg.setText("修改失败");
				}
			}
		});
	}

	protected void initReadDataJPanel() {
		readDataJPanel = new JPanel();
		readDataJPanel.setLayout(new GridLayout(2, 1));

		JPanel panel = new JPanel();
		readDataJPanel.add(panel);
		JLabel boringTip = new JLabel("不想看了，那我们");
		panel.add(boringTip);
		panel.add(initBackButton("readDataJPanel"));

		JPanel panel2 = new JPanel();
		readDataJPanel.add(panel2);
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(440, 180));
		panel2.add(jsp);
		JLabel tip2 = new JLabel();
		panel2.add(tip2);
		tip2.setVisible(false);
		if (Data.showAllReadable(teacher.getId()).trim().length() != 0) {
			String[] topics = Data.showAllReadable(teacher.getId()).split("\\|");
			Vector<Vector<String>> data = new Vector<>();
			Vector<String> names = new Vector<String>();
			for (int i = 0; i < topics.length; i++) {
				Vector<String> row = new Vector<String>();
				row.add(topics[i]);
				data.add(row);
			}
			names.add("DataArea");
			model.setDataVector(data, names);
		} else {
			tip2.setText("暂无资料");
			jsp.setVisible(false);
			tip2.setVisible(true);
		}
	}

	protected void initSendDataJPanel() {
		sendDataJPanel = new JPanel();
		sendDataJPanel.setLayout(new GridLayout(3, 1));
		JPanel panel0 = new JPanel();
		sendDataJPanel.add(panel0);
		JLabel tip = new JLabel("编辑");
		panel0.add(tip);
		JTextArea iMsg = new JTextArea(9, 38);
		JScrollPane jsp = new JScrollPane(iMsg);
		panel0.add(jsp);

		JPanel panel1 = new JPanel();
		sendDataJPanel.add(panel1);
		JLabel tip1 = new JLabel("阅读授权(是:0/否:1)");
		JTextField iMsg2 = new JTextField(10);
		panel1.add(tip1);
		panel1.add(iMsg2);

		JPanel panel2 = new JPanel();
		sendDataJPanel.add(panel2);
		JButton checkButton = new JButton("发布");
		panel2.add(checkButton);
		panel2.add(initBackButton("sendDataJPanel"));

		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (iMsg.getText().trim().length() != 0) {
					if (teacher.sendData(teacher.getId(), teacher.getName(), iMsg.getText(),
							Integer.valueOf(iMsg2.getText().trim()))) {
						new SendDataSucceed2();
					} else {
						new SendDataFailed2();
					}
				} else {
					new SendDataError2();
				}
			}
		});
	}

	protected void initReadNoticeJPanel() {
		readNoticeJPanel = new JPanel();
		readNoticeJPanel.setLayout(new GridLayout(2, 1));

		JPanel panel0 = new JPanel();
		readNoticeJPanel.add(panel0);
		JLabel tip = new JLabel("不想看通知？那让我们");
		panel0.add(tip);
		panel0.add(initBackButton("readNoticeJPanel"));

		JPanel panel1 = new JPanel();
		readNoticeJPanel.add(panel1);
		JLabel notice = new JLabel();
		JScrollPane jsp = new JScrollPane(notice);
		jsp.setPreferredSize(new Dimension(333, 180));
		panel1.add(jsp);
		if (Notice.showAllNotice().trim().length() != 0) {
			notice.setText("<html>" + Notice.showAllNotice() + "</html>");
		} else {
			notice.setText("暂无通知");
		}
	}

	protected void initSendCommandJPanel() {
		// TODO 生成的方法存根
		sendCommandJPanel = new JPanel();
		sendCommandJPanel.setLayout(new GridLayout(3, 1));

		JPanel panel = new JPanel();
		sendCommandJPanel.add(panel);
		JLabel tip = new JLabel("编辑TO(学号)");
		panel.add(tip);
		JTextField iMsg = new JTextField(10);
		panel.add(iMsg);
		JTextArea iMsg2 = new JTextArea(4, 41);
		JScrollPane jsp = new JScrollPane(iMsg2);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(jsp);

		JPanel panel2 = new JPanel();
		sendCommandJPanel.add(panel2);
		panel2.setLayout(new GridLayout(2, 1));
		JButton checkButton = new JButton("发布");
		panel2.add(checkButton);
		panel2.add(initBackButton("sendCommandJPanel"));

		// 一个结果；外加一个显示提交成功的jframe,用sendData就好了
		JPanel panel3 = new JPanel();
		sendCommandJPanel.add(panel3);
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		JScrollPane jsp2 = new JScrollPane(table);
		jsp2.setPreferredSize(new Dimension(440, 118));
		panel3.add(jsp2);

		JLabel tip2 = new JLabel("asd");
		panel3.add(tip2);
//		tip2.setVisible(false);
		if (teacher.readMyComment(teacher.getId()).trim().length() != 0) {
			String[] comments = teacher.readMyComment(teacher.getId()).split("\\|");
			Vector<Vector<String>> data = new Vector<>();
			Vector<String> names = new Vector<String>();
			for (int i = 0; i < comments.length; i++) {
				Vector<String> row = new Vector<String>();
				row.add(comments[i]);
				data.add(row);
			}
			names.add("CommentArea");
			model.setDataVector(data, names);
		} else {
			tip2.setText("暂无评价");
			jsp2.setVisible(false);
			tip2.setVisible(true);
		}

		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (iMsg.getText().trim().length() != 0 && iMsg2.getText().trim().length() != 0) {
					if (teacher.sendComment(iMsg.getText().trim(), iMsg2.getText())) {
						new SendDataSucceed2();
						iMsg.setText("");
						iMsg2.setText("");
						if (teacher.readMyComment(teacher.getId()).trim().length() != 0) {
							String[] comments = teacher.readMyComment(teacher.getId()).split("\\|");
							Vector<Vector<String>> data = new Vector<>();
							Vector<String> names = new Vector<String>();
							for (int i = 0; i < comments.length; i++) {
								Vector<String> row = new Vector<String>();
								row.add(comments[i]);
								data.add(row);
							}
							names.add("CommentArea");
							model.setDataVector(data, names);
						} else {
							tip2.setText("暂无评价");
							jsp.setVisible(false);
							tip2.setVisible(true);
						}
					} else {
						new SendDataFailed2();
					}
				} else {
					new SendDataError2();
				}
			}
		});
	}

	protected void initGrantJPanel() {
		// 查看所有属于自己的未开放的资料
		grantJPanel = new JPanel();
		grantJPanel.setLayout(new GridLayout(2, 1));

		JPanel panel = new JPanel();
		grantJPanel.add(panel);
		JLabel tip = new JLabel("授权题号");
		panel.add(tip);
		JTextField iMsg = new JTextField(5);
		panel.add(iMsg);
		JButton grantCheckButton = new JButton("授权");
		panel.add(grantCheckButton);
		panel.add(initBackButton("grantJPanel"));

		JPanel panel2 = new JPanel();
		grantJPanel.add(panel2);
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(440, 180));
		panel2.add(jsp);
		JLabel tip2 = new JLabel();
		panel2.add(tip2);
		tip2.setVisible(false);
		if (Topic.showGrantableTopic(teacher.getId()).trim().length() != 0) {
			String[] topics = Topic.showGrantableTopic(teacher.getId()).split("\\|");
			Vector<Vector<String>> data = new Vector<>();
			Vector<String> names = new Vector<String>();
			for (int i = 0; i < topics.length; i++) {
				Vector<String> row = new Vector<String>();
				row.add(topics[i]);
				data.add(row);
			}
			names.add("Topics");
			model.setDataVector(data, names);
		} else {
			tip2.setText("暂无可授权课题");
			jsp.setVisible(false);
			tip2.setVisible(true);
		}
		grantCheckButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (iMsg.getText().trim().length() != 0) {
					if (teacher.grantData(Integer.valueOf(iMsg.getText().trim()))) {
						new GrantSucceed();
						if (Topic.showGrantableTopic(teacher.getId()).trim().length() != 0) {
							String[] topics = Topic.showGrantableTopic(teacher.getId()).split("\\|");
							Vector<Vector<String>> data = new Vector<>();
							Vector<String> names = new Vector<String>();
							for (int i = 0; i < topics.length; i++) {
								Vector<String> row = new Vector<String>();
								row.add(topics[i]);
								data.add(row);
							}
							names.add("Topics");
							model.setDataVector(data, names);
						} else {
							tip2.setText("暂无可授权课题");
							jsp.setVisible(false);
							tip2.setVisible(true);
						}
					} else {
						new GrantFailed();
					}
				} else {
					new GrantError();
				}
			}
		});
	}

	protected void initRevokeJPanel() {
		revokeJPanel = new JPanel();
		revokeJPanel.setLayout(new GridLayout(2, 1));

		JPanel panel = new JPanel();
		revokeJPanel.add(panel);
		JLabel tip = new JLabel("撤回授权题号");
		panel.add(tip);
		JTextField iMsg = new JTextField(5);
		panel.add(iMsg);
		JButton revokeCheckButton = new JButton("撤回");
		panel.add(revokeCheckButton);
		panel.add(initBackButton("revokeJPanel"));

		JPanel panel2 = new JPanel();
		revokeJPanel.add(panel2);
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(440, 180));
		panel2.add(jsp);
		JLabel tip2 = new JLabel();
		panel2.add(tip2);
		tip2.setVisible(false);
		if (Topic.showRevokeableTopic(teacher.getId()).trim().length() != 0) {
			String[] topics = Topic.showRevokeableTopic(teacher.getId()).split("\\|");
			Vector<Vector<String>> data = new Vector<>();
			Vector<String> names = new Vector<String>();
			for (int i = 0; i < topics.length; i++) {
				Vector<String> row = new Vector<String>();
				row.add(topics[i]);
				data.add(row);
			}
			names.add("Topics");
			model.setDataVector(data, names);
		} else {
			tip2.setText("暂无可回收课题");
			jsp.setVisible(false);
			tip2.setVisible(true);
		}
		revokeCheckButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (iMsg.getText().trim().length() != 0) {
					if (teacher.revokeData(Integer.valueOf(iMsg.getText().trim()))) {
						new RevokeSucceed();
						if (Topic.showRevokeableTopic(teacher.getId()).trim().length() != 0) {
							String[] topics = Topic.showRevokeableTopic(teacher.getId()).split("\\|");
							Vector<Vector<String>> data = new Vector<>();
							Vector<String> names = new Vector<String>();
							for (int i = 0; i < topics.length; i++) {
								Vector<String> row = new Vector<String>();
								row.add(topics[i]);
								data.add(row);
							}
							names.add("Topics");
							model.setDataVector(data, names);
						} else {
							tip2.setText("暂无可回收课题");
							jsp.setVisible(false);
							tip2.setVisible(true);
						}
					} else {
						new RevokeFailed();
					}
				} else {
					new RevokeError();
				}
			}
		});
	}

	private JButton initBackButton(String JPanelName) {
		JButton back = new JButton("返回");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (JPanelName) {
				case "changeInfoJPanel":
					changeInfoJPanel.setVisible(false);
					break;
				case "sendCommandJPanel":
					sendCommandJPanel.setVisible(false);
					break;
				case "readDataJPanel":
					readDataJPanel.setVisible(false);
					break;
				case "sendDataJPanel":
					sendDataJPanel.setVisible(false);
					break;
				case "readNoticeJPanel":
					readNoticeJPanel.setVisible(false);
					break;
				case "sendTopicJPanel":
					sendTopicJPanel.setVisible(false);
					break;
				case "revokeJPanel":
					revokeJPanel.setVisible(false);
					break;
				case "grantJPanel":
					grantJPanel.setVisible(false);
					break;
				default:
					break;
				}
				mainJPanel.setVisible(true);
			}
		});
		return back;
	}

	public static void main(String[] args) {
		new TeacherJFrame();
	}

}

class SendDataSucceed2 extends JFrame {

	public SendDataSucceed2() {
		JFrame jFrame = new JFrame("ResultJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("发布成功");
		panel.add(tip);
		JButton check = new JButton("确认");
		panel.add(check);
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.setResizable(false);
		jFrame.setSize(new Dimension(100, 70));
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}

class SendDataFailed2 extends JFrame {

	public SendDataFailed2() {
		JFrame jFrame = new JFrame("ResultJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("发布失败");
		panel.add(tip);
		JButton check = new JButton("确认");
		panel.add(check);
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.setResizable(false);
		jFrame.setSize(new Dimension(100, 70));
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}

class SendDataError2 extends JFrame {

	public SendDataError2() {
		JFrame jFrame = new JFrame("ResultJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("非法输入");
		panel.add(tip);
		JButton check = new JButton("确认");
		panel.add(check);
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.setResizable(false);
		jFrame.setSize(new Dimension(100, 70));
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}

class GrantSucceed extends JFrame {
	public GrantSucceed() {
		JFrame jFrame = new JFrame("ResultJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("授权成功");
		panel.add(tip);
		JButton check = new JButton("确认");
		panel.add(check);
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.setResizable(false);
		jFrame.setSize(new Dimension(100, 70));
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}

class GrantFailed extends JFrame {
	public GrantFailed() {
		JFrame jFrame = new JFrame("ResultJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("授权失败");
		panel.add(tip);
		JButton check = new JButton("确认");
		panel.add(check);
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.setResizable(false);
		jFrame.setSize(new Dimension(100, 70));
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}

class GrantError extends JFrame {
	public GrantError() {
		JFrame jFrame = new JFrame("ResultJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("非法输入");
		panel.add(tip);
		JButton check = new JButton("确认");
		panel.add(check);
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.setResizable(false);
		jFrame.setSize(new Dimension(100, 70));
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}

class RevokeSucceed extends JFrame {
	public RevokeSucceed() {
		JFrame jFrame = new JFrame("ResultJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("撤回成功");
		panel.add(tip);
		JButton check = new JButton("确认");
		panel.add(check);
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.setResizable(false);
		jFrame.setSize(new Dimension(100, 70));
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}

class RevokeFailed extends JFrame {
	public RevokeFailed() {
		JFrame jFrame = new JFrame("ResultJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("撤回失败");
		panel.add(tip);
		JButton check = new JButton("确认");
		panel.add(check);
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.setResizable(false);
		jFrame.setSize(new Dimension(100, 70));
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}

class RevokeError extends JFrame {
	public RevokeError() {
		JFrame jFrame = new JFrame("ResultJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("非法输入");
		panel.add(tip);
		JButton check = new JButton("确认");
		panel.add(check);
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});

		jFrame.setResizable(false);
		jFrame.setSize(new Dimension(100, 70));
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}