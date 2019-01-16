package main.xinyan.Guest;

import main.chenru.frontdesk.InitSetting;
import main.xinyan.DateBase.DBoper;
import main.xinyan.Guest.guestmodi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class gmodiinfo{

    private JFrame frame;
    private JTextField jTextFields[];
    /**
     * Launch the application.
     */

    public gmodiinfo(guestmodi modiinfo){
        InitSetting.init();
        jTextFields = modiinfo.getT();
        initialize();
    }

    /**
     * Create the application.
     */
    public gmodiinfo() {

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("在住信息修改");

        JLabel[] jLabel = new JLabel[13];
        JLabel[] jLabels = new JLabel[13];
        JPanel pane=new JPanel();
        pane.setSize(300, 260);

        java.awt.Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(pane, "Center");

        pane.setLayout(new BorderLayout());

        JPanel pane1 = new JPanel(new GridLayout(13, 2));

        String s1[] = {"订单编号","姓名","性别","联系电话","顾客编号","客房编号","房费状态","房费","客房类型","入住时间","预期入住天数","实际结算时间","是否换房"};
        for(int i = 0;i<13;i++){
            jLabel[i] = new JLabel(s1[i]);
            pane1.add(jLabel[i]);
            jTextFields[i] = new JTextField(jTextFields[i].getText(),12);
            pane1.add(jTextFields[i]);
            pane.add(pane1,"Center");

        }

        jTextFields[0].setEditable(false);
        jTextFields[4].setEditable(false);
        jTextFields[5].setEditable(false);
        jTextFields[6].setEditable(false);
        jTextFields[7].setEditable(false);
        jTextFields[8].setEditable(false);
        jTextFields[9].setEditable(false);
        jTextFields[10].setEditable(false);
        jTextFields[11].setEditable(false);
        jTextFields[12].setEditable(false);

        JPanel jPanel = new JPanel(new FlowLayout(java.awt.FlowLayout.CENTER));

        JButton jButton  = new JButton("确认修改");
        jPanel.add(jButton);
        jButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                DBoper db = new DBoper();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String s11 = jTextFields[11].getText();
                    if (null == s11 || "".equals(s11)){ s11 = "2000-01-01 00:00:00";  }
                    Date d1 = (Date)sdf.parse(jTextFields[9].getText());
                    Date d3 = (Date)sdf.parse(s11);
                    String s1 = sdf.format(d1);
                    String s3 = sdf.format(d3);

                    String sql = "Update Living set Cname='"+jTextFields[1].getText()+"',Csex='"+jTextFields[2].getText()+
                            "',Ctel='"+jTextFields[3].getText()+"',Cid='"+jTextFields[4].getText()+
                            "',Rno='"+jTextFields[5].getText()+
                            "',Rpricestatus='"+jTextFields[6].getText()+"',Rprice="+jTextFields[7].getText()+
                            ",Rsort='"+jTextFields[8].getText()+"',Lcomtime='"+s1+
                            "',Exdays='"+jTextFields[10].getText()+"',Llefttime='"+s3+
                            "',Exchangestatus='"+jTextFields[12].getText()+"'where Indentid=?";
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
            }
        });
        JButton jButton1  = new JButton("取消");
        jPanel.add(jButton1);
        jButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
            }
        });
        pane.add(jPanel, "South");

        frame.setSize(500, 400);
        frame.setLocation(100, 80);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

}
