package main.zhangqing.admin;

import ui.MyButton;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.LayoutManager;

public class delete extends JPanel{

	private int message = 0;

	private static JTextField[] jTextFields = new JTextField[7];
	/**
	 * Create the application.
	 */
	public delete() {
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
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
								.addContainerGap())
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(48, Short.MAX_VALUE))
		);

		JLabel label = new JLabel("管理员信息");
		//label.setFont(new Font("宋体", Font.BOLD, 14));

		JLabel label_1 = new JLabel("请输入账号");
		//label_1.setFont(new Font("宋体", Font.BOLD, 12));

		JTextField textField = new JTextField();
		textField.setColumns(10);

		JTable table = new JTable();

		JPanel panel_1 = new JPanel();

		JPanel panel_3 = new JPanel();

		JPanel panel_4 = new JPanel();
		JButton jButton= new JButton("删除");
		jButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if(event.getActionCommand() == "删除"){
					int count=table.getSelectedRow();//获取你选中的行号（记录）
					if(count==-1){
						JOptionPane.showMessageDialog(panel, "请选择所要删除的行！", "系统提示", JOptionPane.WARNING_MESSAGE);
					}else{
						int k = JOptionPane.showConfirmDialog(panel, "确认删除？", "系统提示", JOptionPane.YES_NO_OPTION);
						if(k == 0){
							Sql db = new Sql();
							try {
								//String sql = "delete from Admin where Userno=?";
								//String string = table.getValueAt(count, 0).toString();
								db.getConn();
								int k1 = db.executeUpdata("delete from Admin where Userno='"+table.getValueAt(count, 0).toString()+"'");
								if(k1!=0)
									JOptionPane.showMessageDialog(panel, "删除成功！", "系统提示", JOptionPane.WARNING_MESSAGE);

								panel_1.setVisible(false);
								panel_1.removeAll();
								panel_1.setVisible(true);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		});
		panel_4.add(jButton );


		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sql db = new Sql();
				try {
					panel_3.removeAll();
					panel_3.setVisible(true);
					db.getConn();
					//String st1 = textField.getText();
					//String st2 = "select *from Admin where Userno=?";
					ResultSet rSet = db.executeQuery("select *from Admin where Userno='"+textField.getText()+"'");
					rSet.last();
					int k = rSet.getRow();
					rSet.beforeFirst();
					if(k!=0){
						message = 1;
						Object ob[][] = new Object[k][5];
						for(int i = 0;(i<k)&&(rSet.next());i++){
							ob[i][0] = rSet.getString(1);
							ob[i][1] = rSet.getString(2);
							ob[i][2] = rSet.getString(3);
							ob[i][3] = rSet.getString(4);
							ob[i][4] = rSet.getString(5);
						}
						String s1[] = {"账号","密码","姓名","性别","联系电话"};
						//建表语句传参数
						table.setModel(new javax.swing.table.DefaultTableModel(ob, s1));

						table.setSize(380, 150);
						table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

						//表格添加到滚动区
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setPreferredSize(new Dimension(table.getWidth(),table.getHeight()));
						panel_3.add(scrollPane);
						panel_3.add(panel_4);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally{
					db.closeAll();
				}

				if(message == 0){
					JOptionPane.showMessageDialog(panel, "您查询的信息不存在！", "系统提示", JOptionPane.WARNING_MESSAGE);
					//panel_3.removeAll();
					panel_3.setVisible(true);
				}else{
					message = 0;
					panel_1.setVisible(false);
					panel_1.removeAll();
					panel_1.add(panel_3);
					panel_1.setVisible(true);
				}
			}
		});


		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(label)
										.addGroup(gl_panel.createSequentialGroup()
												.addContainerGap()
												.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)))
								.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
								.addGap(26)
								.addComponent(label_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
								.addComponent(button)
								.addGap(22))
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
		);

		JPanel panel_2 = new JPanel((LayoutManager) null);
		panel_2.setLayout(new BorderLayout());


		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
												.addGap(245)
												.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(panel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
								.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(5)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(55, Short.MAX_VALUE))
		);


		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		this.add(panel);
		this.setVisible(true);
	}
}
