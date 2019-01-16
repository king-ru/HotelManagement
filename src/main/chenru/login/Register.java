package main.chenru.login;

/**
 * @program: HotelManagement
 * @description:
 * @author: Mrs.CeYi
 * @create: 2019-01-11 22:20
 **/

import main.chenru.frontdesk.InitSetting;

import java.awt.*;

import javax.swing.*;

public class Register extends JFrame{

    public static final String TITLE="注册";
    public static final int WIDTH=400;
    public static final int HEIGHT=600;
    public static final int X=440;
    public static final int Y=100;


    //public static int flag1=0;


    public Register() {

        this.setSize(WIDTH,HEIGHT);
        this.setLocation(X,Y);
        this.setTitle(TITLE);

        BorderLayout borderlayout=new BorderLayout();
        this.setLayout(borderlayout);

        JPanel panel_sou=CreatePanelForRegister.CreatePanel();
        this.add(panel_sou);
        //this.setContentPane(panel_sou);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setResizable(false); //禁止改变窗口大小

        this.setVisible(true);


    }

    public static void main(String[] args) {
        InitSetting.init();
        new Register();

    }

}



