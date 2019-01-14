package main;

import ui.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: HotelManagement
 * @description: 登记入住面板
 * @author: Mrs.CeYi
 * @create: 2019-01-14 19:40
 **/
public class CreatePanelForLiving implements ActionListener {
    public static JButton register_btn = new MyButton(2,"登记");


    public static JTextField name_text = new JTextField(); //姓名
    public static JComboBox sex_combox = new JComboBox(); //性别
    public static JTextField tel_text = new JTextField(); //电话号码
    public static JTextField id_text=new JTextField();//身份证号
    public static JComboBox price_status=new JComboBox();//房费支付状态
    public static JComboBox price_day=new JComboBox();//房费
    public static JComboBox sort=new JComboBox();//房间类型

    public static JComboBox days_combox=new JComboBox();//居住天数
    public static JTextField come_time=new JTextField(); //来的时间
    public static JTextField ex_left_time=new JTextField(); //预计离开时间


    //public static JTextField left_time=new JTextField(); //实际离开时间
    public static JComboBox change_status=new JComboBox();//是否换房

    public static JPanel CreatePanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 705));
        panel.setBackground(new Color(176, 224, 230));


         //姓名
        JLabel introduce = new JLabel("姓 名");
        introduce.setBounds(70, 70, 100, 25);
        //introduce.setFont(new Font("微软雅黑", 0, 14));
        name_text.setBounds(120, 65, 160, 30);
        panel.add(name_text);
        panel.add(introduce);

        //性别
        JLabel sex = new JLabel("性 别");
        sex.setBounds(70, 110, 100, 25);
        sex_combox.setBounds(120, 105, 160, 30);
        sex_combox.addItem("男");
        sex_combox.addItem("女");
        panel.add(sex);
        panel.add(sex_combox);


        //电话号码
        JLabel telphone = new JLabel("手机号码");
        telphone.setBounds(50, 150, 100, 25);
        tel_text.setBounds(120, 145, 160, 30);
        panel.add(tel_text);
        panel.add(telphone);


        //身份证号
        JLabel id = new JLabel("身份证号");
        id.setBounds(50, 190, 100, 25);
        id_text.setBounds(120, 185, 160, 30);
        panel.add(id_text);
        panel.add(id);

        //房费状态
        JLabel price = new JLabel("房费状态");
        price.setBounds(50, 230, 100, 25);
        price_status.setBounds(120, 225, 160, 30);
        price_status.addItem("未支付");
        price_status.addItem("已支付");
        panel.add(price);
        panel.add(price_status);


        //类型
        JLabel sort_label = new JLabel("房间类型");
        sort_label.setBounds(50, 270, 100, 25);
        sort.setBounds(120, 265, 160, 30);
        sort.addItem("单人房");
        sort.addItem("标间");
        sort.addItem("大床房");
        panel.add(sort);
        panel.add(sort_label);

        //房费

        JLabel room_price = new JLabel("房费单价");
        room_price.setBounds(50, 310, 100, 25);
        price_day.setBounds(120, 305, 160, 30);
        price_day.addItem("100/天");
        price_day.addItem("200/天");
        price_day.addItem("300/天");
        panel.add(room_price);
        panel.add(price_day);


        //来的时间

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String now_time=df.format(new Date());
        //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间


        JLabel come_label = new JLabel("入住时间");
        //设置入住时间
        come_label.setText(now_time);
        come_label.setBounds(50, 350, 100, 25);
        come_time.setBounds(120, 345, 160, 30);
        panel.add(come_label);
        panel.add(come_time);


        JLabel living_days=new JLabel("居住天数");
        living_days.setBounds(50, 390, 100, 25);
        days_combox.setBounds(120, 385, 160, 30);
        String[] temp=new String[10];
        for (int i=0;i<temp.length;i++){
            days_combox.addItem(temp[i]);
        }
        panel.add(living_days);
        panel.add(days_combox);




        //预期离开时间
        /*
        JLabel ex_left_label = new JLabel("预期离开时间");
        ex_left_label.setBounds(30, 390, 100, 25);
        ex_left_time.setBounds(120, 385, 160, 30);
        panel.add(ex_left_label);
        panel.add(ex_left_time);
        */


/*
        //离开时间
        JLabel left_label = new JLabel("离开时间");
        left_label.setBounds(50, 270, 100, 25);
        left_time.setBounds(120, 265, 160, 30);
        panel.add(left_label);
        panel.add(left_time);
*/

        //换房标志
        JLabel change_label = new JLabel("是否换房");
        change_label.setBounds(50, 430, 100, 25);
        change_status.setBounds(120, 425, 160, 30);
        change_status.addItem("否");
        change_status.addItem("是");
        panel.add(change_label);
        panel.add(change_status);

        register_btn.setBounds(110,500,140,35);
        panel.add(register_btn);
        panel.setLayout(null);
        register_btn.addActionListener(new CreatePanelForLiving());

        return panel;
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==register_btn){



        }

    }
}
