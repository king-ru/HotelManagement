package main.Order;


import javax.swing.*;
import java.awt.*;

/**
 * @program: HotelManagement
 * @description: 预定管理
 * @author: Mrs.CeYi
 * @create: 2019-01-16 16:11
 **/
public class OrderFrame extends JFrame {
    public static final String TITLE="预定";
    public static final int WIDTH=400;
    public static final int HEIGHT=600;
    public static final int X=440;
    public static final int Y=100;
    public OrderFrame(){
        this.setSize(WIDTH,HEIGHT);
        this.setLocation(X,Y);
        this.setTitle(TITLE);

        BorderLayout borderlayout=new BorderLayout();
        this.setLayout(borderlayout);

        JPanel panel_sou=new CreatePanelForOrder().CreatePanel();
        this.add(panel_sou);
        //this.setContentPane(panel_sou);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setResizable(false); //禁止改变窗口大小
        this.setVisible(true);
    }

}
