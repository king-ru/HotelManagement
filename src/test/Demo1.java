package test;

/**
 * @program: HotelManagement
 * @description:
 * @author: Mrs.CeYi
 * @create: 2019-01-16 14:33
 **/
public class Demo1 {
    public static void main(String[] args) {
        String[] s=new String[5];
        s[0]="12";
        s[1]="12";
        s[2]="12";
        s[3]="男";
        s[4]="12";
        sql db=new sql();
        try {
            String sql="insert into Admin values(?,?,?,?,?)";
            //String sql = "insert into Admin values('"+"hah"+
            //		"','"+"hah"+
            //	"','"+"hah"+
            //	"','"+"12"+
            //	"','"+"212"+
            //	"')";

            db.getConn();
            db.executeUpdata(sql, s);

        }catch(Exception e1) {
            e1.printStackTrace();
        }
    }

}
