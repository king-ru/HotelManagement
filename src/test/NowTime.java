package test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: HotelManagement
 * @description: 当前系统时间
 * @author: Mrs.CeYi
 * @create: 2019-01-14 21:42
 **/
public class NowTime {

        public static void main(String[] args) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        }


}
