package main.xinyan.Order;

import main.xinyan.DateBase.DBoper;
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

public class ordermodi extends JPanel{

    private int message = 0;

    private static JTextField[] jTextFields = new JTextField[7];
    /**
     * Create the application.
     */
    public ordermodi() {
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
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                                .addContainerGap())
        );

        JLabel label = new JLabel("\u9884\u8BA2\u4FE1\u606F\u4FEE\u6539");
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
        String s[] = {"修改","删除"};
        JButton []jb = new JButton[2];
        int j = 0;
        for(j = 0;j<jb.length;j++){
            jb[j] = new JButton(s[j]);
            jb[j].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event) {
                    if(event.getActionCommand() == "修改"){
                        int count=table.getSelectedRow();//获取你选中的行号（记录）
                        if(count==-1){
                            JOptionPane.showMessageDialog(panel, "请选择所要修改的行！", "系统提示", JOptionPane.WARNING_MESSAGE);
                        }else{
                            String[] string = new String[7];
                            for(int i = 0;i<7;i++){
                                string[i] = table.getValueAt(count, i).toString();
                                jTextFields[i] = new JTextField(string[i]);
                            }
                            modiinfo mi = new modiinfo(ordermodi.this);

                            panel_1.setVisible(false);
                            panel_1.removeAll();
                            panel_1.setVisible(true);
                        }
                    }else if(event.getActionCommand() == "删除"){
                        int count=table.getSelectedRow();//获取你选中的行号（记录）
                        if(count==-1){
                            JOptionPane.showMessageDialog(panel, "请选择所要删除的行！", "系统提示", JOptionPane.WARNING_MESSAGE);
                        }else{
                            int k = JOptionPane.showConfirmDialog(panel, "确认删除？", "系统提示", JOptionPane.YES_NO_OPTION);
                            if(k == 0){
                                DBoper db = new DBoper();
                                try {
                                    String sql = "delete from COrder where Indentid=?";
                                    String string = table.getValueAt(count, 0).toString();
                                    db.getConn();
                                    db.executeUpdata(sql, new String[]{string});
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
            panel_4.add(jb[j]);
        }

        JButton button = new MyButton(3,"查询");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DBoper db = new DBoper();
                try {
                    panel_3.removeAll();
                    panel_3.setVisible(true);
                    db.getConn();
                    String st1 = textField.getText();
                    String st2 = "select *from Oder where Ctel=?";
                    ResultSet rSet = db.executeQuery(st2, new String[]{st1});
                    rSet.last();
                    int k = rSet.getRow();
                    rSet.beforeFirst();
                    if(k!=0){
                        message = 1;
                        Object ob[][] = new Object[k][7];
                        for(int i = 0;(i<k)&&(rSet.next());i++){
                            ob[i][0] = rSet.getString(1);
                            ob[i][1] = rSet.getString(2);
                            ob[i][2] = rSet.getString(3);
                            ob[i][3] = rSet.getString(4);
                            ob[i][4] = rSet.getString(5);
                            ob[i][5] = rSet.getString(6);
                            ob[i][6] = rSet.getString(7);
                        }
                        String s1[] = {"预定编号","姓名","联系方式","客房编号","客房类型","预期到时间","预定时间"};
                        table.setModel(new javax.swing.table.DefaultTableModel(ob, s1));
                        table.setSize(450, 180);
                        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
