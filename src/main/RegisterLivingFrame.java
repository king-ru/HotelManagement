package main;


import javax.swing.*;
import java.awt.*;

/**
 * @program: HotelManagement
 * @description: 登记入住面板
 * @author: Mrs.CeYi
 * @create: 2019-01-14 16:57
 **/
public class RegisterLivingFrame extends JFrame {
    public static final String TITLE="登记入住";
    public static final int WIDTH=400;
    public static final int HEIGHT=650;
    public static final int X=440;
    public static final int Y=100;
    public RegisterLivingFrame(){
        this.setSize(WIDTH,HEIGHT);
        this.setLocation(X,Y);
        this.setTitle(TITLE);

        BorderLayout borderlayout=new BorderLayout();
        this.setLayout(borderlayout);

        JPanel panel_sou=CreatePanelForLiving.CreatePanel();
        this.add(panel_sou);
        //this.setContentPane(panel_sou);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false); //禁止改变窗口大小

        this.setVisible(true);
    }

    public static void main(String[] args) {
        InitSetting.init();
        new RegisterLivingFrame();
    }

}
