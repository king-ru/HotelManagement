package main.xinyan.Guest;

import main.xinyan.DateBase.DBoper;

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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.LayoutManager;

public class guestmodi extends JPanel{

    private int message = 0;

    private static JTextField[] jTextFields = new JTextField[13];
    /**
     * Create the application.
     */
    public guestmodi() {
        initialize();
    }

    /**
     * Initialize the contents of the JPane.
     */
    private void initialize() {

        JPanel panel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JLabel label = new JLabel("\u5728\u4F4F\u4FE1\u606F\u4FEE\u6539");
        label.setFont(new Font("微软雅黑", Font.BOLD, 14));

        JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u8054\u7CFB\u7535\u8BDD");
        label_1.setFont(new Font("微软雅黑", Font.BOLD, 12));

        JTextField textField = new JTextField();
        textField.setColumns(10);

        JTable table = new JTable(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        JPanel panel_1 = new JPanel();

        JPanel panel_3 = new JPanel();

        JPanel panel_4 = new JPanel();
        JButton jb = new JButton("修改");
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                if(event.getActionCommand() == "修改"){
                    int count=table.getSelectedRow();//获取你选中的行号（记录）
                    if(count==-1){
                        JOptionPane.showMessageDialog(panel, "请选择所要修改的行！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    }else{
                        String[] string = new String[13];
                        String s11 = string[11];
                        int Rp;
                        for(int i = 0;i<13;i++){
                            if(i!=11){
                                string[i] = table.getValueAt(count, i).toString();
                                jTextFields[i] = new JTextField(string[i]);
                            }else{
                                table.setValueAt(s11, count, i);
                                jTextFields[i] = new JTextField(string[i]);
                            }
                        }
                        gmodiinfo mi = new gmodiinfo(guestmodi.this);

                        panel_1.setVisible(false);
                        panel_1.removeAll();
                        panel_1.setVisible(true);
                    }
                }
            }
        });
        panel_4.add(jb);


        JButton button = new JButton("\u67E5\u8BE2");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DBoper db = new DBoper();
                try {
                    panel_3.removeAll();
                    panel_3.setVisible(true);
                    String st1 = textField.getText();
                    String st2 = "select *from Living where Ctel=?";
                    db.getConn();
                    ResultSet rSet = db.executeQuery(st2, new String[]{st1});
                    rSet.last();
                    int k = rSet.getRow();
                    rSet.beforeFirst();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if(k!=0){
                        message = 1;
                        Object ob[][] = new Object[k][13];
                        for(int i = 0;(i<k)&&(rSet.next());i++){
                            ob[i][0] = rSet.getString(1);
                            ob[i][1] = rSet.getString(2);
                            ob[i][2] = rSet.getString(3);
                            ob[i][3] = rSet.getString(4);
                            ob[i][4] = rSet.getString(5);
                            ob[i][5] = rSet.getString(6);
                            ob[i][6] = rSet.getString(7);
                            ob[i][7] = rSet.getString(8);
                            ob[i][8] = rSet.getString(9);
                            Timestamp timestamp9 = rSet.getTimestamp(10);
                            if (timestamp9 != null)
                                ob[i][9] = sdf.format(new Date(timestamp9.getTime()));
                            ob[i][10] = rSet.getInt(11);
                            Timestamp timestamp11 = rSet.getTimestamp(12);
                            if (timestamp11 != null)
                                ob[i][11] = sdf.format(new Date(timestamp11.getTime()));
                            ob[i][12] = rSet.getString(13);
                        }
                        String s1[] = {"订单编号","姓名","性别","联系电话","顾客编号","客房编号","房费状态","房费","客房类型","入住时间","预期入住天数","实际结算时间","是否换房"};
                        table.setModel(new javax.swing.table.DefaultTableModel(ob, s1));
                        table.setSize(450, 180);
                        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setPreferredSize(new Dimension(table.getWidth(),table.getHeight()));

                        panel_3.add(scrollPane);
                        panel_3.add(panel_4);
                        panel_3.setVisible(true);
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
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(16)
                                                .addComponent(label_1)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                                                .addComponent(button))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                                        .addComponent(label))
                                .addContainerGap())
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addComponent(label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(button, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_1))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
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
                                        .addComponent(panel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                                .addContainerGap())
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(5)
                                .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
        );


        panel_1.setLayout(gl_panel_1);
        panel.setLayout(gl_panel);
        setLayout(groupLayout);
        this.add(panel);
        this.setVisible(true);
    }

    public static JTextField[] getT(){

        return jTextFields;
    }

}
