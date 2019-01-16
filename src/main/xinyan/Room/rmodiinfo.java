package main.xinyan.Room;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.api.server.Container;
import main.chenru.frontdesk.InitSetting;
import main.xinyan.DateBase.DBoper;


public class rmodiinfo{

    private JFrame frame;
    private JTextField jTextFields[];
    /**
     * Launch the application.
     */

    public rmodiinfo(roommodi modiinfo){
        InitSetting.init();
        jTextFields = modiinfo.getT();
        initialize();
    }

    /**
     * Create the application.
     */
    public rmodiinfo() {

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("客房信息修改");

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

        pane.setPreferredSize(new Dimension(320, 260));
        jpanel1.setPreferredSize(new Dimension(0, 25));
        jpanel2.setPreferredSize(new Dimension(50, 0));
        jpanel3.setPreferredSize(new Dimension(50, 0));
        jpanel4.setPreferredSize(new Dimension(0, 25));

        pane.setLayout(new BorderLayout());

        JPanel pane1 = new JPanel(new GridLayout(7, 2));

        String s1[] = {"客房编号","类型","状态","房费","押金","负责人编码","客房电话"};
        for(int i = 0;i<7;i++){
            jLabel[i] = new JLabel(s1[i]);
            pane1.add(jLabel[i]);
            jTextFields[i] = new JTextField(jTextFields[i].getText(),10);
            pane1.add(jTextFields[i]);
            pane.add(pane1,"Center");

        }

        jTextFields[0].setEditable(false);
        jTextFields[1].setEditable(false);
        jTextFields[2].setEditable(false);

        JPanel jPanel = new JPanel(new FlowLayout(java.awt.FlowLayout.CENTER));

        JButton jButton  = new JButton("确认修改");
        jPanel.add(jButton);
        jButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                DBoper db = new DBoper();
                try {
                    db.getConn();
                    String sql = "Update Room set Rsort='"+jTextFields[1].getText()+"',Rstatus='"+jTextFields[2].getText()+
                            "',Rprice='"+jTextFields[3].getText()+"',Rpreprice='"+jTextFields[4].getText()+
                            "',Userno='"+jTextFields[5].getText()+"',Rtel='"+jTextFields[6].getText()+"'where Rno=?";
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
            }
        });

        pane.add(jPanel, "South");

        frame.setSize(500, 400);
        frame.setLocation(100, 80);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

}
