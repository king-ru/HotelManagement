package test;

import utils.JDBCUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: HotelManagement
 * @description: 当前系统时间
 * @author: Mrs.CeYi
 * @create: 2019-01-14 21:42
 **/
public class NowTime {

  public static   Timestamp timeStamp;
        public static void main(String[] args) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

            Date date=new Date();
            df.format(date);
            //Integer day=(Integer) days_combox.getSelectedItem();
            Integer addDay=2;
            date.setDate(date.getDate()+addDay);
            System.out.println(date.toLocaleString());
            initDate();
            System.out.println("timestamp="+timeStamp);

        }

    public static void initDate(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date date = new Date();
        format.format(date);
        timeStamp = new Timestamp(date.getTime());
        testDatabase();

    }
    public static void testDatabase()  {
            try{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Date date = new Date();
        String times=format.format(date);
        List list=new ArrayList();
        list.add(times);
        String sql="insert Test values(?)";
        JDBCUtils.get().update(sql,list);} catch (SQLException e) {
                e.printStackTrace();
            }

    }


}
