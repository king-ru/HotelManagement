package test;

/**
 * @program: HotelManagement
 * @description:
 * @author: Mrs.CeYi
 * @create: 2019-01-14 21:49
 **/
public class Demo{
    public static String remove(String resource,char ch) {
        StringBuffer buffer=new StringBuffer();
        int position=0;
        char currentChar;

        while(position<resource.length()){
            currentChar=resource.charAt(position++);
            //如果当前字符不是要去除的字符，则将当前字符加入到StringBuffer中
            if(currentChar!=ch) buffer.append(currentChar);
        }
        return buffer.toString();
    }
    public static void main(String args[]) {
        String str= "afdsfdf 了   2012:   0407 Alamps: 老师";
        char c=' ';
        String temp=Demo.remove(str,':');
        String s1=Demo.remove(temp, c);
        //System.out.println("结果为："+Demo.remove(str, c));
        System.out.println(s1);
        //String s3=temp+s1;
        //System.out.println(s3);
    }
}
