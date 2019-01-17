package main;

import main.zhangqing.Order.OrderPanel;
import main.chenru.frontdesk.FrontActivity;
import main.chenru.frontdesk.InitSetting;
import main.haoda.account.closingtActivity;
import main.xinyan.Order.modification;
import main.xinyan.Rchange.roomchange;
import main.zhangqing.admin.Asum;
import ui.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @program: HotelManagement
 * @description:主界面
 * @author: Mrs.CeYi
 * @create: 2019-01-13 20:47
 **/

//import recept_btn.*;
    public class MainActivity extends JFrame implements ActionListener
    {
        //分为前台操作，预定管理，信息find_btn，信息修改，账户管理

        private JButton recept_btn,order_btn,find_btn,change_btn,admin_btn,closing_btn,change_room_btn;
        private JPanel p3;

        public MainActivity()
        {
            InitSetting.init();
            JFrame app=new JFrame("Hotel管理界面");
            Container c=app.getContentPane();
            c.setLayout(new BorderLayout());




            JPanel p1=new JPanel();
            p1.setBackground(Color.WHITE);
            c.add(p1,"West");
            //GridLayout
            p1.setLayout(new GridLayout(2,1));


            JPanel p2=new JPanel(new GridLayout(7,1));
            p1.add(p2);



            recept_btn=new MyButton(1,"前台操作");
            p2.add(recept_btn);
            recept_btn.addActionListener(this);

            closing_btn=new JButton("结账");
            p2.add(closing_btn);
            closing_btn.addActionListener(this);

            find_btn=new JButton("信息查询");
            p2.add(find_btn);
            find_btn.addActionListener(this);

            change_room_btn=new JButton("换房");
            p2.add(change_room_btn);
            change_room_btn.addActionListener(this);

            change_btn=new JButton("信息修改");
            p2.add(change_btn);
            change_btn.addActionListener(this);

            order_btn=new JButton("预定管理");
            p2.add(order_btn);
            order_btn.addActionListener(this);

            admin_btn=new JButton("账户管理");
            p2.add(admin_btn);
            admin_btn.addActionListener(this);


            //exit_btn=new JButton("退出系统");
            //p2.add(exit_btn);
            //exit_btn.addActionListener(this);

            p3=new JPanel();
            ImageIcon icon1=new ImageIcon("D:\\ideaProject\\HotelManagement\\src\\resources\\images\\mainActivity.gif");
            JLabel cp1=new JLabel(icon1);

            cp1.setSize(500,300);
            p3.add(cp1);

            JScrollPane spane=new JScrollPane(p3);
            c.add(spane,"Center");


            JPanel p5=new JPanel(new GridLayout(2,1));
            p5.setBackground(Color.LIGHT_GRAY);
            c.add(p5,"South");
            p5.add(new JLabel("大数据学院",JLabel.CENTER));
            p5.add(new JLabel("made by 陈茹 黄鑫艳 王浩达 张青",JLabel.CENTER));


            JPanel p6=new JPanel(new FlowLayout(FlowLayout.CENTER));
            p6.setBackground(Color.YELLOW);
            c.add(p6,"North");

            JLabel huanying=new JLabel("欢迎使用Hotel管理系统");
            huanying.setFont(new Font("微软雅黑",1,30));
            huanying.setForeground(Color.RED);
            p6.add(huanying,JLabel.CENTER);
            app.setSize(700,640);
            app.setLocation(100,80);
            app.setVisible(true);
        }
        public void actionPerformed(ActionEvent e)
        {

            if(e.getSource()==recept_btn)
            {

                p3.setVisible(false);
                p3.removeAll();
                p3.add(new FrontActivity());
                p3.setVisible(true);
            }

            if(e.getSource()==order_btn)
            {
                p3.setVisible(false);
                p3.removeAll();
                p3.add(new OrderPanel());
                p3.setVisible(true);
            }
            if (e.getSource()==closing_btn){
                p3.setVisible(false);
                p3.removeAll();
                p3.add(new closingtActivity());
                p3.setVisible(true);
            }

            if (e.getSource()==change_room_btn){
                p3.setVisible(false);
                p3.removeAll();
                p3.add(new roomchange());
                p3.setVisible(true);
            }
            if(e.getSource()==change_btn)
            {
                p3.setVisible(false);
                p3.removeAll();
                p3.add(new modification());
                p3.setVisible(true);
            }

            if(e.getSource()==admin_btn)
            {
                p3.setVisible(false);
                p3.removeAll();
                p3.add(new Asum());
                p3.setVisible(true);
            }
            /*
            if(e.getSource()==find_btn)
            {
                p3.setVisible(false);
                p3.removeAll();
                p3.add(new find_btn());
                p3.setVisible(true);
            }
            if(e.getSource()==change_btn)
            {
                p3.setVisible(false);
                p3.removeAll();
                p3.add(new change_btn());
                p3.setVisible(true);
            }
            if(e.getSource()==admin_btn)
            {
                p3.setVisible(false);
                p3.removeAll();
                p3.add(new admin_btn());
                p3.setVisible(true);
            }
            if(e.getSource()==用户自服务)
            {
                p3.setVisible(false);
                p3.removeAll();
                p3.add(new 用户自服务());
                p3.setVisible(true);
            }
            if(e.getSource()==exit_btn)
                System.exit(10);*/

        }

        public static void main(String args[])
        {
                InitSetting.init();
              new MainActivity();

        }
    }


