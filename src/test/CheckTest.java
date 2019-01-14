package test;

/**
 * @program: HotelManagement
 * @description:
 * @author: Mrs.CeYi
 * @create: 2019-01-13 19:40
 **/
public class CheckTest {
    public String id;

    public String password;

    public String repassword;

    public String tel;
    public CheckTest(String id,String password,String repassword,String tel){
        this.id=id;
        this.password=password;
        this.repassword=repassword;
        this.tel=tel;
    }
    public int check()
    {
        if (id.length()!=12){
            return 1;
        }
        else {
            if (password!=repassword){
            return 2; }

        else{
            if (tel.length()!=11){
            return 3;
        }
        else return 0;
    }
        }
    }

    public static void main(String[] args) {
        CheckTest checkTest=new CheckTest("123456789012","123","123","2333");
        int flag=checkTest.check();
        System.out.println(flag);
    }

}
