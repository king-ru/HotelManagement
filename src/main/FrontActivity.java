package main;

import ui.MyButton;
import utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.util.List;

import static utils.JDBCUtils.get;

/**
 * @program: HotelManagement
 * @description:用户管理
 * @author: Mrs.CeYi
 * @create: 2019-01-13 22:01
 **/
public class FrontActivity extends JPanel implements ActionListener
{
    private JButton intake_btn,pay_btn,diaplay_btn1,diaplay_btn2,find_btn;

    public static JTextField roomNo;        //文本框
    private JPanel p1,p2,p3,p4,p5;     //面板
    private JButton []jb=new JButton[3];
    private JScrollPane jsp1;          //滚动窗口
    private int message=0;
    private JLabel jLabel;

    public FrontActivity()
    {
        p1=new JPanel(new BorderLayout());
        p2=new JPanel();
        p1.add(p2,"North");
        p2.setLayout(new GridLayout(1,7,10,0));


        diaplay_btn1=new MyButton(0,"房间状态");
        p2.add(diaplay_btn1);
        diaplay_btn1.addActionListener(this);


        intake_btn=new MyButton(0,"开房");
        intake_btn.addActionListener(this);
        p2.add(intake_btn);

        pay_btn=new MyButton(0,"退房");
        p2.add(pay_btn);
        pay_btn.addActionListener(this);

        diaplay_btn2=new MyButton(0,"今日订单");
        p2.add(diaplay_btn2);
        diaplay_btn2.addActionListener(this);


        jLabel=new JLabel("房间号");
        //jLabel.setFont(new Font("微软雅黑",0));
        p2.add(jLabel);


        roomNo=new JTextField();
        p2.add(roomNo);

        find_btn=new MyButton(1,"查询");
        p2.add(find_btn);
        find_btn.addActionListener(this);

        p3=new JPanel();
        p1.add(p3,"Center");
        p3.add(new RoomStatusPanel());
        this.add(p1);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            /*
           Vector data_list=new Vector();

            String s2=roomNo.getText();
            String sql="select * from Room where Rno=?";
            List list=new ArrayList();
            list.add(s2);
            Map map=get().getQueryResult(sql,list);

            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();

                data_list.add(entry.getValue());
            }
            Vector s=new Vector();
            s.add("房间编号");
            s.add("类型");
            s.add("状态");
            s.add("价格");
            s.add("押金");
            s.add("负责人编号");
            s.add("客房电话");
            JTable table1 = new JTable(data_list, s);
            table1.setSize(500, 500);
            jsp1 = new JScrollPane(table1);
            jsp1.setPreferredSize(new Dimension(table1.getWidth(), table1.getHeight()));
            p4.add(jsp1, "Center");*/

            } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (e.getSource()==diaplay_btn1){
            if (message==0)
            {JOptionPane.showMessageDialog(null,"已经是最新信息","系统提示",JOptionPane.PLAIN_MESSAGE);}
            else{
                p3.setVisible(false);
                p3.removeAll();
                p3.add(new RoomStatusPanel());
                p3.setVisible(true);

            }

        }
        if (e.getSource()==diaplay_btn2){
            p3.setVisible(false);
            p3.removeAll();
            p3.add(new TodayIndentPanel());
            message=1;
            p3.setVisible(true);


        }
        if (e.getSource()==intake_btn){
            new RegisterLivingFrame();

        }
        if (e.getSource()==pay_btn){

        }
        if (e.getSource()==find_btn){


            p3.setVisible(false);
            p3.removeAll();
            p3.add(new RoomSearchPanel());
            message=1;
            p3.setVisible(true);

        }

    }


}