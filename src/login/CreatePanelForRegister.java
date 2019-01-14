package login;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: HotelManagement
 * @description:
 * @author: Mrs.CeYi
 * @create: 2019-01-11 22:29
 **/

    class CreatePanelForRegister implements ActionListener {
    public static JButton registerbtn = new JButton("注册");


    //用户名
    public static JTextField user_text = new JTextField();
    public static JPasswordField pwd_text = new JPasswordField();

    //确认密码框
    public static JPasswordField repwd_text = new JPasswordField();

    public static JTextField name_text = new JTextField(); //姓名
    public static JComboBox sex_combox = new JComboBox(); //性别
    public static JTextField tel_text = new JTextField(); //电话号码

    public  int message;

    /**
     * 创建面板
     */

    public static JPanel CreatePanel() {

        JPanel panel = new JPanel();

        //JLabel label_sou = new JLabel();


        panel.setPreferredSize(new Dimension(0, 505));

        panel.setBackground(new Color(176, 224, 230));

        //label_sou.setBounds(0, 0, 400, 300);

        //panel.add(label_sou);

        // MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1 , true);

/**

 * 添加昵称和输入框

 */

        JLabel user = new JLabel("账号");

        user.setBounds(70, 70, 100, 25);

        user.setFont(new Font("微软雅黑", 0, 14));

        user_text.setBounds(120, 65, 160, 30);

        //user_text.setBorder(myLineBorder);

        panel.add(user);

        panel.add(user_text);

/**

 * 密码及密码框

 */

        JLabel pwd = new JLabel("密 码");

        pwd.setBounds(70, 110, 100, 25);

        pwd.setFont(new Font("微软雅黑", 0, 14));

        pwd_text.setBounds(120, 105, 160, 30);

        //pwd_text.setBorder(myLineBorder);

        panel.add(pwd_text);

        panel.add(pwd);

/**

 * 确认密码及确认密码框

 */

        JLabel repwd = new JLabel("确认密码");

        repwd.setBounds(50, 150, 100, 25);

        repwd.setFont(new Font("微软雅黑", 0, 14));

        repwd_text.setBounds(120, 145, 160, 30);

        //repwd_text.setBorder(myLineBorder);

        panel.add(repwd_text);

        panel.add(repwd);

/**

 *姓名

 */

        JLabel introduce = new JLabel("姓名");

        introduce.setBounds(70, 190, 100, 25);


        introduce.setFont(new Font("微软雅黑", 0, 14));

        name_text.setBounds(120, 185, 160, 30);

        //name_text.setBorder(myLineBorder);

        panel.add(name_text);

        panel.add(introduce);

/**

 * 性别

 */

        JLabel sex = new JLabel("性 别");

        sex.setBounds(70, 230, 100, 25);

        sex.setFont(new Font("微软雅黑", 0, 14));

        sex_combox.setBounds(120, 225, 160, 30);

        sex_combox.addItem("男");

        sex_combox.addItem("女");

        sex_combox.setFont(new Font("微软雅黑", 0, 14));

        panel.add(sex);

        panel.add(sex_combox);


//手机号码

        JLabel telphone = new JLabel("手机号码");

        telphone.setBounds(50, 270, 100, 25);

        telphone.setFont(new Font("微软雅黑", 0, 14));

        tel_text.setBounds(120, 265, 160, 30);

        panel.add(tel_text);

        panel.add(telphone);

        panel.add(telphone);

        //panel.add(pwdprotect_combox);

/**

 * 注册按钮

 */
        registerbtn.setBounds(110, 370, 140, 35);
        registerbtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));

        //registerbtn.addActionListener(new CreatePanelForRegeist());

        panel.add(registerbtn);

        panel.setLayout(null);
        registerbtn.addActionListener(new CreatePanelForRegister());

        return panel;

    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==registerbtn){
            RegisterUser registerUser=new RegisterUser();

            try {
                 message=registerUser.register();

            }catch (Exception e1)
            {
                e1.printStackTrace();
            }
            if (message==1){
                JOptionPane.showMessageDialog(null,"您输入的账号有误,请重新输入","系统提示",JOptionPane.ERROR_MESSAGE);
                user_text.setText("");
                pwd_text.setText("");
                repwd_text.setText("");
                tel_text.setText("");

            }
            else if (message==2)
            {
                JOptionPane.showMessageDialog(null,"您两次输入的密码不同,请重新输入","系统提示",JOptionPane.ERROR_MESSAGE);
                pwd_text.setText("");
                repwd_text.setText("");
                tel_text.setText("");
            }
            else if (message==3)
            {
                JOptionPane.showMessageDialog(null,"您输入的电话号码有误,请重新输入","系统提示",JOptionPane.ERROR_MESSAGE);
                tel_text.setText("");
            }
            else if(message==0)
            {
                JOptionPane.showMessageDialog(null,"注册成功","系统提示",JOptionPane.PLAIN_MESSAGE);

                new Login1();
            }

        }

/**

 * 将用户信息注册保存到数据库

 */


    }

    /**
     * @program: HotelManagement
     * @description:
     * @author: Mrs.CeYi
     * @create: 2019-01-11 22:23
     **/
    public static class RegisterUser {
        public String id;
        public String password;
       // public String repassword;
        public String name;
        public String sex;
        public String tel;
        public List list=new ArrayList();
        public void update(String id,String password, String name,String sex,String tel) throws SQLException {
            String sql="insert into Admin values(?,?,?,?,?)";
            list.add(id);
            list.add(password);
            //list.add(repassword);
            list.add(name);
            list.add(sex);
            list.add(tel);

            JDBCUtils.get().update(sql,list);
        }

        public int register() throws SQLException {
            GetInfoFromRegisterWnd register=new GetInfoFromRegisterWnd();
            int flag=register.check();
            if (flag==0){
            id=register.getNo();
            password=register.getPassword();
            name=register.getName();
            sex=register.getSex();
            tel=register.getTel();
            update(id,password,name,sex,tel);
            return flag;

            }
            else return flag;
        }

    }
}


