package main.xinyan.Order;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.sun.xml.internal.ws.api.server.Container;


import main.chenru.frontdesk.InitSetting;
import main.xinyan.DateBase.DBoper;
import main.xinyan.Order.ordermodi;

public class modiinfo{

    private JFrame frame;
    private JTextField jTextFields[];

    public modiinfo(ordermodi modiinfo){
        InitSetting.init();
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

        JLabel[] jLabel = new JLabel[7];
        JLabel[] jLabels = new JLabel[7];
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

        JPanel pane1 = new JPanel(new GridLayout(7, 2));

        String s1[] = {"订单编号","姓名","联系方式","客房编号","客房类型","预期到时间","预定时间"};
        for(int i = 0;i<7;i++){
            jLabel[i] = new JLabel(s1[i]);
            pane1.add(jLabel[i]);
            jTextFields[i] = new JTextField(jTextFields[i].getText(),10);
            pane1.add(jTextFields[i]);
            pane.add(pane1,"Center");

        }

        jTextFields[0].setEditable(false);
        jTextFields[3].setEditable(false);
        jTextFields[4].setEditable(false);
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
                            db.getConn();
                            String sql = "Update Oder set Cname='"+jTextFields[1].getText()+"',Ctel='"+jTextFields[2].getText()+
                                    "',Rno='"+jTextFields[3].getText()+"',Rsort='"+jTextFields[4].getText()+
                                    "',Excometime='"+jTextFields[5].getText()+"',Ordertime='"+jTextFields[6].getText()+"'where Orderid=?";
                            String str = jTextFields[0].getText();
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

}
