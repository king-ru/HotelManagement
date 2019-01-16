package main.xinyan.Rchange;

import main.xinyan.DateBase.DBoper;

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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.LayoutManager;

public class roomchange extends JPanel{

    private int message = 0;

    private static JTextField[] jTextFields = new JTextField[9];
    /**
     * Create the application.
     */
    public roomchange() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        JPanel panel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                                .addContainerGap())
        );

        JLabel label = new JLabel("\u6362\u623F\u5904\u7406");
        label.setFont(new Font("微软雅黑", Font.BOLD, 14));

        JLabel label_1 = new JLabel("请输入客房编号");
        label_1.setFont(new Font("微软雅黑", Font.BOLD, 13));

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
        JButton jb = new JButton("换房");
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                if(event.getActionCommand() == "换房"){
                    int count=table.getSelectedRow();//获取你选中的行号（记录）
                    if(count==-1){
                        JOptionPane.showMessageDialog(panel, "请选择换房信息！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    }else if((table.getValueAt(count, 12).toString()).equals("是")){
                        JOptionPane.showMessageDialog(panel, "该住客已换过一次，不能再换！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    }else{
                        String[] string = new String[6];
                        string[0] = table.getValueAt(count, 0).toString();
                        string[1] = table.getValueAt(count, 1).toString();
                        string[2] = table.getValueAt(count, 5).toString();
                        string[3] = table.getValueAt(count, 8).toString();
                        string[4] = table.getValueAt(count, 9).toString();
                        string[5] = table.getValueAt(count, 10).toString();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String exchtime = sdf.format(new Date());
                        try{

                            Date d1 = sdf.parse(exchtime);
                            Date d2 = (Date)sdf.parse(string[4]);
                            long diff = d1.getTime() - d2.getTime();//这样得到的差值是毫秒级别
                            long days = diff / (1000 * 60 * 60 * 24);
                            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
                            if(hours != 0){
                                days += 1;
                            }
                            string[4] = String.valueOf(days);
                        }catch (Exception e)  {
                            e.printStackTrace();
                        }

                        jTextFields[0] = new JTextField(string[0]);
                        jTextFields[1] = new JTextField(string[1]);
                        jTextFields[2] = new JTextField(string[2]);
                        jTextFields[3] = new JTextField("null");
                        jTextFields[4] = new JTextField(string[3]);
                        jTextFields[5] = new JTextField("null");
                        jTextFields[6] = new JTextField(string[4]);
                        jTextFields[7] = new JTextField(exchtime);
                        jTextFields[8] = new JTextField(string[5]);

                        rchangeinfo mi = new rchangeinfo(roomchange.this);

                        panel_1.setVisible(false);
                        panel_1.removeAll();
                        panel_1.setVisible(true);
                    }
                }
            }
        });
        panel_4.add(jb);


        JButton button = new JButton("查询");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DBoper db = new DBoper();
                try {
                    panel_3.removeAll();
                    panel_3.setVisible(true);
                    db.getConn();
                    String st1 = textField.getText();
                    String st2 = "select *from Living where Rno=?";
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
                            ob[i][10] = rSet.getString(11);
                            Timestamp timestamp11 = rSet.getTimestamp(12);
                            if (timestamp11 != null)
                                ob[i][11] = sdf.format(new Date(timestamp11.getTime()));
                            ob[i][12] = rSet.getString(13);
                        }
                        String s1[] = {"订单编号","姓名","性别","联系电话","顾客编号","客房编号","房费状态","房费","客房类型","入住时间","预期入住天数","实际结算时间","是否换房"};
                        table.setModel(new javax.swing.table.DefaultTableModel(ob, s1));
                        table.setSize(490, 190);
                        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setPreferredSize(new Dimension(table.getWidth(),table.getHeight()));
                        JLabel jLabel = new JLabel("此时入住信息");
                        panel_3.add(jLabel);
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
                                .addContainerGap()
                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(26)
                                .addComponent(label_1)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                                .addComponent(button)
                                .addGap(22))
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label)
                                .addContainerGap(425, Short.MAX_VALUE))
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
                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPanel panel_2 = new JPanel((LayoutManager) null);
        panel_2.setLayout(new BorderLayout());


        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(263)
                                .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(242, Short.MAX_VALUE))
                        .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(5)
                                .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
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
