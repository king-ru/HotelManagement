package login;

import main.InitSetting;
import main.MainActivity;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static com.sun.glass.ui.Cursor.setVisible;

/**
 * @program: HotelManagement
 * @description:
 * @author: Mrs.CeYi
 * @create: 2019-01-11 21:12
 **/
public class Login1 implements ActionListener {

    private JFrame jFrame = new JFrame("登录");
    private Container c = jFrame.getContentPane();
    private JLabel a1 = new JLabel("账号");
    private JTextField username = new JTextField();
    private JLabel a2 = new JLabel("密码");
    private JLabel titlelabel=new JLabel("Hotel 登录系统");
    private JPasswordField password = new JPasswordField();
    private JButton loginbtn = new JButton("登录");
    private JButton registerbtn = new JButton("注册");
    private int message=0;
    private BackgroundPanel bgp;


    public Login1() {
        //设置窗体的位置及大小
        jFrame.setBounds(600, 200, 400, 350);
        //设置一层相当于桌布的东西
        c.setLayout(new BorderLayout());//布局管理器
        //设置按下右上角X号后关闭
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //初始化--往窗体里放其他控件
        init();
        //设置窗体可见
        jFrame.setVisible(true);
    }
    public void init() {
        /*标题部分--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        titlePanel.add(titlelabel);
        titlelabel.setFont(new java.awt.Font("Dialog", 1, 18));

        c.add(titlePanel, "North");


        /*输入部分--Center*/
        //重新绘制背景图片
        bgp=new BackgroundPanel((new ImageIcon("D:\\ideaProject\\HotelManagement\\src\\resources\\images\\login_background.jpg")).getImage()); //参数是一个Image对象,
        bgp.setBounds(0,0,400,350);
        bgp.setLayout(null);

        a1.setBounds(60, 50, 50, 30);
        a2.setBounds(60, 90, 50, 30);
        bgp.add(a1);
        bgp.add(a2);
        username.setBounds(100, 50, 160, 30);
        password.setBounds(100, 90, 160, 30);
        bgp.add(username);
        bgp.add(password);
        c.add(bgp, "Center");

        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        FlowLayout f=new FlowLayout();
        buttonPanel.setLayout(f);
        loginbtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        registerbtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        buttonPanel.add(loginbtn);
        //loginbtn.setBounds(93,159,93,23);
        buttonPanel.add(registerbtn);
        f.setHgap(50);
        c.add(buttonPanel, "South");




       loginbtn.addActionListener(this);
        registerbtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginbtn){
            Connection con;
            Statement stmt;
            ResultSet rs;
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }catch (ClassNotFoundException f){
                System.out.println("sqlException"+f.getLocalizedMessage());
            }
            try{
                con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Room","sa","140103ru");
                stmt=con.createStatement();
                rs=stmt.executeQuery("select * from Admin");
                while (rs.next()){
                    String st1=rs.getString("Userno");
                    String st2=rs.getString("Userpassword");
                    char [] ps=password.getPassword();
                    String st3="";
                    for (int i=0;i<ps.length;i++) {
                        st3 += ps[i];
                    }

                    if (username.getText().equals(st1) && st3.equals(st2)){
                        message=1;
                        /////直接new下一个窗口
                        //该窗口不可见
                        jFrame.setVisible(false);
                        new MainActivity();

                        rs.close();
                        stmt.close();
                        con.close();

                        break;
                    }

                }

                if (message==0){
                    JOptionPane.showMessageDialog(jFrame,"您输入的账号或密码错误，请重新输入!","系统提示",JOptionPane.ERROR_MESSAGE);

                }
                con.close();



            }catch (SQLException f){
                System.out.println(f);
            }

        }
        if (e.getSource()==registerbtn)
        {
            //new 一个注册类
            try {
                new Register();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            jFrame.setVisible(false);
        }
    }

    //测试
    public static void main(String[] args) {

        try {

            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Login1();
    }
}
class BackgroundPanel extends JPanel
{
    Image im;
    public BackgroundPanel(Image im)
    {
        this.im=im;
        this.setOpaque(false);                    //设置控件不透明,若是false,那么就是透明
    }
    //Draw the background again,继承自Jpanle,是Swing控件需要继承实现的方法,而不是AWT中的Paint()
    public void paintComponent(Graphics g)       //绘图类
    {
        super.paintComponents(g);
        g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);  //绘制指定图像中当前可用的图像。图像的左上角位于该图形上下文坐标空间的 (x, y)。图像中的透明像素不影响该处已存在的像素

    }
}

