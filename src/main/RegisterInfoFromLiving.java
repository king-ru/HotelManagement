package main;

import utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static main.CreatePanelForLiving.*;

/**
 * @program: HotelManagement
 * @description: 提交从living页面获取的信息
 * @author: Mrs.CeYi
 * @create: 2019-01-14 21:32
 **/
public class RegisterInfoFromLiving {
    private String order_id;
    //Timestamp timeStamp;
    String times1,times2;
    private int message1=0;
    public RegisterInfoFromLiving(){
        //处理预期离开时间

    }
    public void initDate(){

        String temp=days_combox.getSelectedItem()+"";
        Integer day=Integer.parseInt(temp);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date date = new Date();
        //当前日期
        times1= format.format(date);
        //timeStamp = new Timestamp(date.getTime());


        //预期离开日期
        date.setDate(date.getDate()+day);
        times2=date.toLocaleString();



    }
    /*

    public Timestamp getExLeftTime(){
        String temp=days_combox.getSelectedItem()+"";
        Integer day=Integer.parseInt(temp);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date date = new Date();
        format.format(date);


        //System.out.println(day);
        Timestamp timeStamp1=timeStamp;

        timeStamp1.setTime(timeStamp1.getDate()+day);

        System.out.println(timeStamp1);
        return timeStamp1;
    }
*/
    public  int check(){
        //身份证号检查
        if (id_text.getText().length()!=18){
            return 1;
        }
        else{
            if (tel_text.getText().length()!=11){
                return 2;
            }else{
                return 0;
            }
        }

        //电话号码检查

    }
    public void generateOrder(){
        //获取当前系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String s1=df.format(new Date());

        //随机生成一个6位数字字符串
        String result="";

        Random random = new Random();

        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        order_id=result+s1;

    }

    public void check_show(){
        int flag=check();
        if (flag==1){
            JOptionPane.showMessageDialog(null,"你输入的身份证号有误,请重新输入!","系统提示",JOptionPane.ERROR_MESSAGE);
            id_text.setText("");
        }
        else if(flag==2){
            JOptionPane.showMessageDialog(null,"您输入的电话号码有误,请重新输入","系统提示",JOptionPane.ERROR_MESSAGE);
            tel_text.setText("");
        }else{
            initDate();
            generateOrder();
            check_database();
            if (message1==1){


            }
            else {
                JOptionPane.showMessageDialog(null, "入住成功", "系统提示", JOptionPane.PLAIN_MESSAGE);
                name_text.setText("");
                tel_text.setText("");
                id_text.setText("");
                come_time.setText("");


                //对话框也是一个JFrame
                Container panel_container = panel.getParent().getParent().getParent().getParent();
                Frame frame = (Frame) panel_container;
                frame.dispose();
            }

        }


    }
    public void register(){
        try{
        List list=new ArrayList();
        List list1=new ArrayList();
        list.add(order_id);
        list.add(name_text.getText());
        list.add(sex_combox.getSelectedItem()+"");
        list.add(tel_text.getText());
        list.add(id_text.getText());
        list.add(room_no.getSelectedItem()+"");

        list.add(price_status.getSelectedItem()+"");
        list.add(price_day.getSelectedItem()+"");

        list.add(sort.getSelectedItem()+"");
        list.add(times1);
        list.add(times2);
        list.add(null);
        list.add(change_status.getSelectedItem()+"");

        //System.out.println(list);
        String sql="insert into Living values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        String sql1="update Room set Rstatus=? where Rno=?";
        list1.add("1");
        list1.add(room_no.getSelectedItem()+"");

        //首先查客户表，如果没有信息就将其插入
        Map<String,Object> map2=new HashMap<>();
        List list2=new ArrayList();
        String sql2="select Cid from Customer where Cid=?";
        list2.add(id_text.getText());
        map2=JDBCUtils.get().getQueryResult(sql2,list2);
        if (map2.get("Cid")==null){
            //List list3=new ArrayList();
            list2.add(name_text.getText());
            list2.add(sex_combox.getSelectedItem()+"");
            list2.add(tel_text.getText());

            String sql3="insert into Customer values(?,?,?,?)";
            JDBCUtils.get().update(sql3,list2);
        }


        JDBCUtils.get().update(sql,list);
        JDBCUtils.get().update(sql1,list1);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void check_database(){
        try{
        String temp=CreatePanelForLiving.room_no.getSelectedItem()+"";
        String sql2="select Rstatus from Room where Rno=?";
        List list2=new ArrayList();
        list2.add(temp);


            System.out.println(sql2);
            System.out.println(list2);

        Map<String,Object> map1=new HashMap<>();
        map1=JDBCUtils.get().getQueryResult(sql2,list2);

        //JDBCUtils.get().closeConnection();
        System.out.println(map1);
        String s=(String) map1.get("Rstatus");
        if (s.equals("1")){
            JOptionPane.showMessageDialog(null,"该房间已有人,请重新选择房间!","系统提示",JOptionPane.ERROR_MESSAGE);
            //CreatePanelForLiving.room_no.
            message1=1;
        }
        else{
            register();
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
