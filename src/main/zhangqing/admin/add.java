package main.zhangqing.admin;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.naming.InsufficientResourcesException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableColumn;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.LayoutManager;
import java.awt.TextField;
import javax.swing.JRadioButton;

public class add extends JPanel{

	private int message = 0;
	private String s = new String();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Create the application.
	 */
	public add() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGap(20)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
								.addContainerGap())
		);


		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u7528\u6237\u7F16\u53F7");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u59D3\u540D");

		JLabel lblNewLabel_2 = new JLabel("\u6027\u522B");

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\u8054\u7CFB\u65B9\u5F0F");

		textField_4 = new JTextField();
		textField_4.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("\u7528\u6237\u5BC6\u7801");

		JRadioButton radioButton = new JRadioButton("\u7537");

		JRadioButton radioButton_1 = new JRadioButton("\u5973");

		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(radioButton.isSelected()) {
					s = radioButton.getText();
				}else if(radioButton_1.isSelected()){
					s = radioButton_1.getText();
				}
				System.out.println(s);
				Sql db=new Sql();
				try {
					String sql = "insert into Admin values ('"+textField.getText()+
							"','"+textField_1.getText()+
							"','"+s+
							"','"+textField_3.getText()+
							"','"+textField_4.getText()+

							"')";
					db.getConn();
					db.executeUpdata(sql, new String[]{});
					JOptionPane.showMessageDialog(null,"添加成功","系统提示",JOptionPane.PLAIN_MESSAGE);

				}catch(Exception e1) {
					e1.printStackTrace();

				}finally {
					db.closeAll();
				}

			}
		});



		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
												.addGap(127)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel.createSequentialGroup()
																.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblNewLabel)
																		.addComponent(lblNewLabel_1)
																		.addComponent(lblNewLabel_2))
																.addGap(22)
																.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																		.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addGroup(gl_panel.createSequentialGroup()
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																						.addGroup(gl_panel.createSequentialGroup()
																								.addComponent(radioButton)
																								.addPreferredGap(ComponentPlacement.RELATED)
																								.addComponent(radioButton_1))
																						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addComponent(lblNewLabel_3)
																.addComponent(lblNewLabel_4))))
										.addGroup(gl_panel.createSequentialGroup()
												.addGap(174)
												.addComponent(button)))
								.addContainerGap(175, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_1)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_2)
										.addComponent(radioButton)
										.addComponent(radioButton_1))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_3)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_4)
										.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(25)
								.addComponent(button)
								.addContainerGap(111, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		this.add(panel);
		this.setVisible(true);
	}

}




