package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.Administrator;
import entity.Notice;
import entity.Student;
import entity.Topic;

public class StudentJFrame extends JFrame {
	private static final long serialVersionUID = 6530735986224991469L;
	private JFrame jFrame;
	private JPanel mainJPanel;
	private JPanel selectTopicJPanel;
	private JPanel changeInfoJPanel;
	private JPanel readDataJPanel;
	private JPanel readNoticeJPanel;
	private JPanel sendDataJPanel;
	private JPanel readCommandJPanel;
	private Student student;

	public StudentJFrame() {
		jFrame = new JFrame("学生");
		initComponent();
		jFrame.add(mainJPanel);
		student = new Student();
		student.setId("1600502614");
//		student.setId("123");

		jFrame.setSize(470, 400);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}

	public StudentJFrame(String stuID) {
		jFrame = new JFrame("学生");
		initComponent();
		jFrame.add(mainJPanel);
		student = new Student();
		student.setId(stuID);
		// System.out.println(student.getId());
		jFrame.setSize(470, 400);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}

	private void initComponent() {
		mainJPanel = (JPanel) this.getContentPane();
		mainJPanel.setLayout(new GridLayout(3, 2));

		JButton changeInfoButton = new JButton("修改信息");
		JButton selectTopicButton = new JButton("选题");
		JButton readDataButton = new JButton("查阅资料");
		JButton readNoticeButton = new JButton("查看通知");
		JButton sendDataButton = new JButton("上传资料");
		JButton readCommandButton = new JButton("查看评价");

		mainJPanel.add(changeInfoButton);
		mainJPanel.add(selectTopicButton);
		mainJPanel.add(readDataButton);
		mainJPanel.add(readNoticeButton);
		mainJPanel.add(sendDataButton);
		mainJPanel.add(readCommandButton);

		changeInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initChangeInfoJPanel();
				jFrame.add(changeInfoJPanel);
				mainJPanel.setVisible(false);
				changeInfoJPanel.setVisible(true);
			}
		});

		selectTopicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initSelectTopicJPanel();
				jFrame.add(selectTopicJPanel);
				mainJPanel.setVisible(false);
				selectTopicJPanel.setVisible(true);
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

		readNoticeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initReadNoticeJPanel();
				jFrame.add(readNoticeJPanel);
				mainJPanel.setVisible(false);
				readNoticeJPanel.setVisible(true);
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

		readCommandButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initReadCommandJPanel();
				jFrame.add(readCommandJPanel);
				mainJPanel.setVisible(false);
				new Play();
				readCommandJPanel.setVisible(true);
			}
		});
	}

	protected void initChangeInfoJPanel() {
		changeInfoJPanel = new JPanel();
		changeInfoJPanel.setLayout(new GridLayout(2, 1));
		// 修改密码，性别，院系，班级，专业，导师，电话
		JPanel panel0 = new JPanel();
		changeInfoJPanel.add(panel0);
		JTable infoTable = new JTable(6, 2);
		panel0.add(infoTable);
		// 获取基础信息
		String stuMsg = Administrator.studentQuery(student.getId()).trim();
		String[] stuMsgs = stuMsg.split(",");
		infoTable.setValueAt("密码", 0, 0);
		infoTable.setValueAt(stuMsgs[2], 0, 1);
		infoTable.setValueAt("性别", 1, 0);
		infoTable.setValueAt(stuMsgs[3], 1, 1);
		infoTable.setValueAt("班级", 2, 0);
		infoTable.setValueAt(stuMsgs[5], 2, 1);
		infoTable.setValueAt("专业", 3, 0);
		infoTable.setValueAt(stuMsgs[6], 3, 1);
		infoTable.setValueAt("指导老师", 4, 0);
		infoTable.setValueAt(stuMsgs[7], 4, 1);
		infoTable.setValueAt("个人电话", 5, 0);
		infoTable.setValueAt(stuMsgs[8], 5, 1);
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
				// String school = infoTable.getValueAt(2, 1).toString();
				String learn_class = infoTable.getValueAt(2, 1).toString();
				String major = infoTable.getValueAt(3, 1).toString();
				String teacher_id = infoTable.getValueAt(4, 1).toString();
				String mobile = infoTable.getValueAt(5, 1).toString();
				if (student.changeInfo(student.getId(), password, sex, learn_class, major, teacher_id, mobile)) {
					// 若修改成功，显示修改后的数据
					String[] msgs = Administrator.studentQuery(student.getId()).split(",");
					// 学号，姓名，密码，性别，学校，班级，专业，导师，电话
					String msg = "学号：" + student.getId() + "<br>姓名：" + msgs[1] + "<br>密码：" + msgs[2] + "<br>性别："
							+ msgs[3] + "<br>学校：" + msgs[4] + "<br>班级：" + msgs[5] + "<br>专业：" + msgs[6] + "<br>导师："
							+ msgs[7] + "<br>电话：" + msgs[8];
					returnMsg.setText("<html>修改成功<br>" + msg + "</html>");
				} else {
					returnMsg.setText("修改失败");
				}
			}
		});
	}

	protected void initSelectTopicJPanel() {
		selectTopicJPanel = new JPanel();
		selectTopicJPanel.setLayout(new GridLayout(2, 1));
		// 选题题号输入文本框
		JPanel panel0 = new JPanel();
		selectTopicJPanel.add(panel0);
		JLabel tip = new JLabel("输入课题题号");
		panel0.add(tip);
		JTextField iMsg = new JTextField(7);
		panel0.add(iMsg);
		// 按钮
		JButton checkButton = new JButton("选题");
		panel0.add(checkButton);
		panel0.add(initBackButton("selectTopicJPanel"));
		// 结果面板
		JPanel panel1 = new JPanel();
		selectTopicJPanel.add(panel1);
		JLabel oMsg = new JLabel();
		panel1.add(oMsg);
		oMsg.setVisible(false);
		// 课题目录 --> 选取成功则跳转至结果面板
		JLabel topicMsg = new JLabel();
		// topicMsg.setPreferredSize(new Dimension(400,300));
		JScrollPane jsp = new JScrollPane(topicMsg);
		jsp.setPreferredSize(new Dimension(333, 180));
		// JOptionPane.showMessageDialog(null, jsp);
		panel1.add(jsp);
		if (!"".equals(student.getSelect_topic())) {
			if ("null".equals(student.getSelect_topic())) {
				if (Topic.showAllTopic().trim().length() != 0) {
					topicMsg.setText("<html>" + Topic.showAllTopic() + "</html>");
				} else {
					topicMsg.setText("无课题可选");
				}
			} else {
				if (Topic.showAllTopic().trim().length() != 0) {
					topicMsg.setText("<html>" + Topic.showAllTopic() + "</html>");
				} else {
					topicMsg.setText("无课题可选");
				}
				// oMsg.setText("<html>您已经选择课题" + student.getTopic().getName() + "，出题者：" +
				// student.getTopic().getCreater()
				// + "<br>简介：" + student.getTopic().getIntroduction() + "</html>");
				// jsp.setVisible(false);
				// oMsg.setVisible(true);
			}
		} else {
			if (Topic.showAllTopic().trim().length() != 0) {
				topicMsg.setText("<html>" + Topic.showAllTopic() + "</html>");
			} else {
				topicMsg.setText("无课题可选");
			}
		}
		// 选题按钮事件
		// 更新一下：选题之后根据出题人到导师表查到该导师，拿到导师id后通过stduent.getteacherid()获取id后新建该id的teacher对象，再去将checkednum+1
		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Topic.showAllTopic().trim().length() == 0) {
					oMsg.setText("无课题可选，输入非法");
					jsp.setVisible(false);
					oMsg.setVisible(true);
				} else {
					if (!"".equals(student.getSelect_topic())) {
						if ("null".equals(student.getSelect_topic())) {
							if (student.hasTeacher(student.getId())) {
								// oMsg.setText("<html>无法再次选题<br>您已经选择课题" + student.getTopic().getName() +
								// "，出题者："
								// + student.getTopic().getCreater() + "<br>简介："
								// + student.getTopic().getIntroduction() + "</html>");
								oMsg.setText("<html>无法再次选题<br>您已经选择课题</html>");
								jsp.setVisible(false);
								oMsg.setVisible(true);
							} else {
								// 输入的题号就是学生所选课题号
								student.setSelect_topic(iMsg.getText().trim());
								Topic topic = new Topic();
								topic.setId(Integer.valueOf(iMsg.getText().trim()));
								String[] tmpTopic = Topic.showOneTopic(student.getSelect_topic()).split(",");
								topic.setName(tmpTopic[0]);
								topic.setCreater(tmpTopic[1]);
								topic.setIntroduction(tmpTopic[2]);
								topic.setChecked_num(1);
								student.setSelect_topic(iMsg.getText().trim());
								student.setTopic(topic);
								Topic.updateTopic(Integer.valueOf(iMsg.getText().trim()));
								String teacID = student.getTeacIDByTopic(Integer.valueOf(iMsg.getText().trim()));
								if (student.updateTeacID(student.getId(), teacID)
										&& Topic.updateTeacMaxNumOfTopic(teacID)) {
									oMsg.setText("选题成功");
									jsp.setVisible(false);
									oMsg.setVisible(true);
								}
							}
						} else {
							// oMsg.setText("<html>无法再次选题<br>您已经选择课题" + student.getTopic().getName() +
							// "，出题者："
							// + student.getTopic().getCreater() + "<br>简介：" +
							// student.getTopic().getIntroduction()
							// + "</html>");
							oMsg.setText("<html>无法再次选题<br>您已经选择课题</html>");
							jsp.setVisible(false);
							oMsg.setVisible(true);
						}
					} else {
						// 输入的题号就是学生所选课题号(这里其实没啥用)
						student.setSelect_topic(iMsg.getText().trim());
						Topic topic = new Topic();
						topic.setId(Integer.valueOf(iMsg.getText().trim()));
						String[] tmpTopic = Topic.showOneTopic(student.getSelect_topic()).split(",");
						topic.setName(tmpTopic[0]);
						topic.setCreater(tmpTopic[1]);
						topic.setIntroduction(tmpTopic[2]);
						topic.setChecked_num(1);
						student.setSelect_topic(iMsg.getText().trim());
						student.setTopic(topic);
						Topic.updateTopic(Integer.valueOf(iMsg.getText().trim()));
						String teacID = student.getTeacIDByTopic(Integer.valueOf(iMsg.getText().trim()));
						if (student.updateTeacID(student.getId(), teacID) && Topic.updateTeacMaxNumOfTopic(teacID)) {
							oMsg.setText("选题成功");
							jsp.setVisible(false);
							oMsg.setVisible(true);
						}
					}
				}
			}
		});
	}

	protected void initReadDataJPanel() {
		readDataJPanel = new JPanel();
		readDataJPanel.setLayout(new GridLayout(2, 1));
		// 查询文本框
		JPanel panel0 = new JPanel();
		readDataJPanel.add(panel0);
		JLabel tip = new JLabel("输入信息");
		panel0.add(tip);
		JTextField iMsg = new JTextField(10);
		panel0.add(iMsg);
		// 按钮
		JButton queryButton = new JButton("搜索");
		panel0.add(queryButton);
		panel0.add(initBackButton("readDataJPanel"));
		// 输出结果JLabel
		JPanel panel1 = new JPanel();
		readDataJPanel.add(panel1);
		JLabel oMsg = new JLabel();
		JScrollPane jsp = new JScrollPane(oMsg);
		jsp.setPreferredSize(new Dimension(333, 160));
		panel1.add(jsp);
		// 搜索按钮事件
		queryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (iMsg.getText().trim().length() > 0) {
					if (Administrator.readData(iMsg.getText()).trim().length() > 0) {
						String[] returnMsgs = Administrator.readData(iMsg.getText()).trim().split(";");
						StringBuffer returnMsg = new StringBuffer("");
						for (int i = 0; i < returnMsgs.length; i++) {
							returnMsg.append(returnMsgs[i] + "<br>");
						}
						oMsg.setText("<html>" + returnMsg + "</html>");
					} else {
						oMsg.setText("搜索无结果");
					}
				} else {
					oMsg.setText("非法输入");
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

	protected void initSendDataJPanel() {
		sendDataJPanel = new JPanel();
		sendDataJPanel.setLayout(new GridLayout(2, 1));
		JPanel panel0 = new JPanel();
		sendDataJPanel.add(panel0);
		JLabel tip = new JLabel("编辑");
		panel0.add(tip);
		JTextArea iMsg = new JTextArea(9, 38);
		JScrollPane jsp = new JScrollPane(iMsg);
		panel0.add(jsp);

		JPanel panel1 = new JPanel();
		sendDataJPanel.add(panel1);
		JButton checkButton = new JButton("提交");
		panel1.add(checkButton);
		panel1.add(initBackButton("sendDataJPanel"));

		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (iMsg.getText().trim().length() != 0) {
					if (student.sendData(student.getId(), student.getName(), iMsg.getText())) {
						new SendDataSucceed();
					} else {
						new SendDataFailed();
					}
				} else {
					new SendDataError();
				}
			}
		});
	}

	protected void initReadCommandJPanel() {
		readCommandJPanel = new JPanel();
		readCommandJPanel.setLayout(new GridLayout(2, 1));

		JPanel panel = new JPanel();
		readCommandJPanel.add(panel);
		JLabel msg = new JLabel();
		JScrollPane jsp = new JScrollPane(msg);
		jsp.setPreferredSize(new Dimension(333, 180));
		panel.add(jsp);
		panel.add(jsp);
		if (student.readCommand(student.getId()).trim().length() != 0) {
			msg.setText("<html>导师评价：<br>" + student.readCommand(student.getId()) + "</html>");
		} else {
			msg.setText("暂无评论");
		}

		JPanel panel2 = new JPanel();
		readCommandJPanel.add(panel2);
		panel2.add(initBackButton("readCommandJPanel"));
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
				case "readCommandJPanel":
					readCommandJPanel.setVisible(false);
					break;
				case "readDataJPanel":
					readDataJPanel.setVisible(false);
					break;
				case "readNoticeJPanel":
					readNoticeJPanel.setVisible(false);
					break;
				case "sendDataJPanel":
					sendDataJPanel.setVisible(false);
					break;
				case "selectTopicJPanel":
					selectTopicJPanel.setVisible(false);
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
		new StudentJFrame();
	}
}

class SendDataSucceed extends JFrame {
	private static final long serialVersionUID = 7199086764058239814L;

	public SendDataSucceed() {
		JFrame jFrame = new JFrame("ResultJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("提交成功");
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

class SendDataFailed extends JFrame {
	private static final long serialVersionUID = -679913480318532770L;

	public SendDataFailed() {
		JFrame jFrame = new JFrame("ResultJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("提交失败");
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

class SendDataError extends JFrame {
	private static final long serialVersionUID = -8065530784716405953L;

	public SendDataError() {
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

class Play extends JFrame {
	private static final long serialVersionUID = -4954314839494351664L;

	public Play() {
		JFrame jFrame = new JFrame("PlayJFrame");
		JPanel panel = new JPanel();
		jFrame.add(panel);
		JLabel tip = new JLabel("新的风暴已经来临！");
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
		jFrame.setSize(new Dimension(100, 100));
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
}