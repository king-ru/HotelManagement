package main.zhangqing.Order;

import main.haoda.account.DBoper;
import ui.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;


public class CreatePanelForOrder implements ActionListener {

    public JTextField name_text = new JTextField(); //姓名
    public JTextField tel_text = new JTextField(); //电话号码
    public JComboBox room_no = new JComboBox();  //房间号
    public JComboBox sort = new JComboBox();//房间类型
    public JTextField ex_come_time = new JTextField();//预期来的时间
    public JTextField order_time = new JTextField(); //预定的时间

    public MyButton order_btn = new MyButton(3, "预定");
    public static JPanel panel = new JPanel();

    private String order_id;//随机生成一个订单号

    public JPanel CreatePanel() {

        panel.setPreferredSize(new Dimension(0, 505));
        panel.setBackground(new Color(176, 224, 230));

        //姓名
        JLabel introduce = new JLabel("姓名");
        introduce.setBounds(70, 70, 100, 25);
        name_text.setBounds(120, 65, 160, 30);
        //name_text.setBorder(myLineBorder);

        panel.add(name_text);

        panel.add(introduce);


        //联系方式
        JLabel telphone = new JLabel("手机号码");
        telphone.setBounds(50, 110, 100, 25);
        tel_text.setBounds(120, 105, 160, 30);
        panel.add(tel_text);
        panel.add(telphone);
        panel.add(telphone);

        //房间号
        JLabel room_label = new JLabel("房间编号");
        //room_label.setBounds(50,30,100,25);
        //room_no.setBounds(120,25,160,30);
        room_label.setBounds(50, 150, 100, 25);
        //introduce.setFont(new Font("微软雅黑", 0, 14));
        room_no.setBounds(120, 145, 160, 30);
        for (int k = 1; k < 10; k++) {
            room_no.addItem("000" + k);
        }
        room_no.addItem("00" + 10);
        room_no.addItem("00" + 11);
        room_no.addItem("00" + 12);
        room_no.addItem("00" + 13);
        room_no.addItem("00" + 14);
        room_no.addItem("00" + 15);
        room_no.addItem("00" + 16);
        panel.add(room_label);
        panel.add(room_no);

        //类型
        JLabel sort_label = new JLabel("房间类型");
        sort_label.setBounds(50, 190, 100, 25);
        sort.setBounds(120, 185, 160, 30);
        sort.addItem("单人房");
        sort.addItem("标间");
        sort.addItem("大床房");
        panel.add(sort);
        panel.add(sort_label);


        //设置预定来的时间
        JLabel come_label = new JLabel("预定入住时间");
        //设置入住时间
        //ex_come_time.setText(now_time);
        come_label.setBounds(35, 230, 100, 25);
        ex_come_time.setBounds(120, 225, 160, 30);
        panel.add(come_label);
        panel.add(ex_come_time);


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String now_time = df.format(new Date());
        JLabel order_label = new JLabel("预定时间");
        //设置预定时候的时间
        order_time.setText(now_time);
        order_label.setBounds(50, 270, 100, 25);
        order_time.setBounds(120, 265, 160, 30);
        panel.add(order_label);
        panel.add(order_time);


        order_btn.setBounds(110, 340, 140, 35);
        panel.add(order_btn);
        panel.setLayout(null);
        order_btn.addActionListener(this);


        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == order_btn) {

            //先查数据库当中该房间的状态
            //可以自己设置一下check
            //JOptionPane.showMessageDialog(null,"预订成功","系统提示",JOptionPane.PLAIN_MESSAGE);
            databaseCommit();
            JOptionPane.showMessageDialog(null, "预订成功", "系统提示", JOptionPane.PLAIN_MESSAGE);

            name_text.setText("");
            tel_text.setText("");
            ex_come_time.setText("");
            order_time.setText("");



            Container panel_container = panel.getParent().getParent().getParent().getParent();
            //
            //System.out.println(panel_container);
            Frame frame = (Frame) panel_container;
            frame.dispose();


        }

    }

    public void generateOrder() {
        //获取当前系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String s1 = df.format(new Date());

        //随机生成一个6位数字字符串
        String result = "";

        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        //将其拼接
        order_id = result + s1;

    }

    public void databaseCommit() {
            generateOrder();
            DBoper sql = new DBoper();
            try {
                sql.getConn();
            } catch (Exception e1) {

                e1.printStackTrace();
            }
            String name, tel, roomno, sort1, excome, order;
            name = name_text.getText();
            tel = tel_text.getText();
            roomno = (String) room_no.getSelectedItem();
            sort1 = (String) sort.getSelectedItem();
            excome = ex_come_time.getText();
            order = order_time.getText();
            //String Exchangestatus = null;
            sql.executeUpdata("insert into Oder values('" + order_id + "','" + name + "','" + tel + "','" + roomno + "','" + sort1 + "','" + excome + "','" + order + "')");
            sql.executeUpdata("update Room set Rstatus='1' where Rno='" + roomno + "'");




    }
}