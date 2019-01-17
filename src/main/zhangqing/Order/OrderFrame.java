package main.zhangqing.Order;


import javax.swing.*;
import java.awt.*;


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

        JPanel panel1=new CreatePanelForOrder().CreatePanel();
        this.add(panel1);
        //this.setContentPane(panel_sou);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setResizable(false); //禁止改变窗口大小
        this.setVisible(true);
    }

}
