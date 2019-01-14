package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @program: HotelManagement
 * @description: 提交从living页面获取的信息
 * @author: Mrs.CeYi
 * @create: 2019-01-14 21:32
 **/
public class RegisterInfoFromLiving {
    private String order_id;

    public RegisterInfoFromLiving(){

    }

    public void check(){


    }
    public void generateOrder(){
        //获取当前系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String s1=df.format(new Date());

        //随机生成一个6位数字字符串

        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        order_id=result+s1;
        //System.out.println(s1);// new Date()为获取当前系统时间

        //return "hah";
    }

    public void register(){
        List list=new ArrayList();


    }


}
