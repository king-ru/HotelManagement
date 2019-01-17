package main.xinyan.Order;

import main.xinyan.DateBase.DBoper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class modiinfo{

    private JFrame frame;
    private JTextField jTextFields[];
    private JList jList;
    //年月日的选择框
    private JComboBox cboYear = new JComboBox();
    private JComboBox cboMonth = new JComboBox();
    private JComboBox cboDay = new JComboBox();
    public modiinfo(ordermodi modiinfo){
        jTextFields = modiinfo.getT();
        initialize();
    }

    /**
     * Create the application.
     */
    public modiinfo() {

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("\u9884\u5B9A\u4FE1\u606F\u4FEE\u6539");

        JLabel[] jLabel = new JLabel[8];
        JPanel pane=new JPanel();
        pane.setSize(300, 260);

        java.awt.Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(pane, "Center");

        JPanel jpanel1 = new JPanel();
        container.add(jpanel1, "North");
        JPanel jpanel2 = new JPanel();
        container.add(jpanel2, "West");
        JPanel jpanel3 = new JPanel();
        container.add(jpanel3, "East");
        JPanel jpanel4 = new JPanel();
        container.add(jpanel4, "South");
        pane.setPreferredSize(new Dimension(300, 260));
        jpanel1.setPreferredSize(new Dimension(0, 25));
        jpanel2.setPreferredSize(new Dimension(50, 0));
        jpanel3.setPreferredSize(new Dimension(50, 0));
        jpanel4.setPreferredSize(new Dimension(0, 25));

        pane.setLayout(new BorderLayout());

        // 年的下拉选择框
        cboYear.setFont(new java.awt.Font("Dialog", Font.BOLD, 13));
        cboYear.setBounds(new Rectangle(8, 5));
        // 月的下拉选择框
        cboMonth.setFont(new java.awt.Font("Dialog", Font.BOLD, 13));
        cboMonth.setBounds(new Rectangle(8, 5));
        cboMonth.addItemListener(new DateItemAdapter(this));
        // 日的下拉选择框
        cboDay.setFont(new java.awt.Font("Dialog", Font.BOLD, 13));
        cboDay.setBounds(new Rectangle(8, 5));
        // cboDay.setEditable(true);
        //添加初始值
        AddInfo();

        JPanel pane1 = new JPanel(new GridLayout(8, 2));

        String s1[] = {"订单编号","姓名","联系方式","客房编号","客房类型","预期到时间","预定时间"};
        for(int i = 0;i<7;i++){
            jLabel[i] = new JLabel(s1[i]);
            pane1.add(jLabel[i]);
            jTextFields[i] = new JTextField(jTextFields[i].getText(),10);
            pane1.add(jTextFields[i]);
            pane.add(pane1,"Center");
        }
        jLabel[7] = new JLabel(s1[5]);
        pane1.add(jLabel[7]);
        JPanel p1 = new JPanel();
        p1.add(cboYear);
        p1.add(cboMonth);
        p1.add(cboDay);
        pane1.add(p1);
//		pane1.add(cboYear);
//		pane1.add(cboMonth);
//		pane1.add(cboDay);
        pane.add(pane1,"Center");

        jTextFields[0].setEditable(false);
        jTextFields[3].setEditable(false);
        jTextFields[4].setEditable(false);
        jTextFields[5].setEditable(false);
        jTextFields[6].setEditable(false);
//        MaskFormatter mask = null;
//        try {
//          mask = new MaskFormatter("####-##-## ##:##:##");// the # is for numeric values
//          mask.setPlaceholderCharacter('#');
//        } catch (ParseException e) {
//          e.printStackTrace();
//        }
//        final JFormattedTextField timeField = new JFormattedTextField(mask);


        JPanel jPanel = new JPanel(new FlowLayout(java.awt.FlowLayout.CENTER));


        String s[] = {"确认修改","取消"};
        JButton[] jButton  = new JButton[2];
        for(int i = 0;i<s.length;i++){
            jButton[i] = new JButton(s[i]);
            jPanel.add(jButton[i]);
            jButton[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event) {
                    if(event.getActionCommand() == "确认修改"){
                        DBoper db = new DBoper();
                        try {
                            String s = cboYear.getSelectedItem() + "-" + cboMonth.getSelectedItem()+"-"+cboDay.getSelectedItem();
                            String sql = "Update Oder set Cname='"+jTextFields[1].getText()+"',Ctel='"+jTextFields[2].getText()+
                                    "',Rno='"+jTextFields[3].getText()+"',Rsort='"+jTextFields[4].getText()+
                                    "',Excometime='"+s+"',Ordertime='"+jTextFields[6].getText()+"'where Orderid=?";
                            String str = jTextFields[0].getText();

                            db.getConn();
                            int k = db.executeUpdata(sql, new String[]{str});
                            if(k!=0)
                                JOptionPane.showMessageDialog(pane, "修改成功！", "系统提示", JOptionPane.WARNING_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(pane, "修改失败！", "系统提示", JOptionPane.WARNING_MESSAGE);
                            frame.dispose();

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }finally {
                            db.closeAll();
                        }
                    }else if(event.getActionCommand()=="取消"){
                        frame.dispose();
                    }
                }
            });
        }
        pane.add(jPanel, "South");

        frame.setSize(500, 400);
        frame.setLocation(100, 80);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    private void AddInfo() {
        //年下拉选择框
        for (int i = 2018; i < 2025; i++) {
            cboYear.addItem("" + i);
        }

        //月下拉选择框
        for (int i = 0; i < 12; i++) {
            cboMonth.addItem("" + (i + 1));
        }

        //日下拉选择框
        for (int j = 0; j < 31; j++) {
            cboDay.addItem("" + (j + 1));
        }
    }

    public void cboMonth_itemStateChanged(ItemEvent e) {

        Object obj = cboMonth.getSelectedItem();// 取得选中月份

        if (obj != null) {
            cboDay.removeAllItems();// 清空日的下拉列表框

            int month = Integer.valueOf(obj.toString());
            int days = 31;
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                days = 30;
            } else if (month == 2) {
                //取得选中年份
                int year = Integer.parseInt(cboYear.getSelectedItem()
                        .toString());
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                    // 是闰年
                    days = 29;
                } else {
                    // 不是闰年
                    days = 28;
                }
            }//if

            for (int j = 0; j < days; j++) {
                cboDay.addItem("" + (j + 1));
            }//for
        }//if
    }//if
}
