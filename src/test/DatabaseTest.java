package test;

import utils.JDBCUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: HotelManagement
 * @description: 数据库传输数据测试
 * @author: Mrs.CeYi
 * @create: 2019-01-15 11:18
 **/
public class DatabaseTest
{
    String order_id;
    Timestamp timeStamp;

    public DatabaseTest(){

        generateOrder();
        initDate();
        getExLeftTime();
        check_database();
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
        //System.out.println(s1);// new Date()为获取当前系统时间

        //return "hah";
    }
    public void register(){
        try{

            List list=new ArrayList();
            list.add(order_id);
            list.add("zhangqing");
            list.add("女");
            list.add("23232323");
            list.add("123456789009876543");
            list.add("12345");
            list.add("未支付");
            list.add("100元/天");
            list.add("单人房");
            list.add(timeStamp);
            list.add(getExLeftTime());
            list.add("");
            list.add("否");


            String sql="insert into Living values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            JDBCUtils.get().update(sql,list);

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public Timestamp getExLeftTime(){
        //当前时间格式化
        //Date date=new Date();
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //df.format(date);


        //获取天数,是一个object对象
        //Integer day=(Integer) days_combox.getSelectedItem();
        //String s=(String)days_combox.getSelectedItem();
        //int day=Integer.parseInt(s);
        Timestamp timeStamp1=timeStamp;
        timeStamp1.setTime(timeStamp1.getDate()+2);
        //date.setDate(date.getDate()+day);

        //获取离开时间

       // String Lexlefttime=date.toString();
        return timeStamp1;
        //return date;
    }
    public void initDate(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date date = new Date();
        format.format(date);
         timeStamp = new Timestamp(date.getTime());
    }

    public void check_database(){
        try{
            //String temp=CreatePanelForLiving.room_no.getSelectedItem()+"";
            String sql="select Rstatus from Room where Rno=?";
            List list=new ArrayList();
            list.add("0001");

            Map<String,Object> map1=new HashMap<>();
            map1=JDBCUtils.get().getQueryResult(sql,list);

            JDBCUtils.get().closeConnection();
            System.out.println(map1.get("Rstatus"));
            if (map1.get("Rstatus").equals("1")){
                //JOptionPane.showMessageDialog(null,"该房间已有人,请重新选择房间!","系统提示",JOptionPane.ERROR_MESSAGE);
                //CreatePanelForLiving.room_no.
                System.out.println("error");
            }
            else{
                register();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DatabaseTest();
    }


}
