package main.xinyan.Order;

import main.xinyan.Rchange.roomchange;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;



public class OrderY {

    private JFrame frmHotel;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
//		try
//	    {
//	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//	    }
//	    catch(Exception e)
//	    {
//	        //TODO exception
//	    }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrderY window = new OrderY();
                    window.frmHotel.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public OrderY() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmHotel =  new JFrame();
        frmHotel.getContentPane().setEnabled(false);
        frmHotel.setTitle("HOTEL");
        frmHotel.getContentPane().setBackground(Color.LIGHT_GRAY);
        frmHotel.setBounds(100, 100, 760, 534);
        frmHotel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        ImageIcon icon1 = new ImageIcon("123.jpg");

        JPanel panel_1 = new JPanel();

        JPanel panel_2 = new JPanel();

        GroupLayout groupLayout = new GroupLayout(frmHotel.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
                        .addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
                                .addGap(21))
        );

        JLabel label = new JLabel("Hotel \u7CFB\u7EDF");
        panel_2.add(label);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("微软雅黑", Font.BOLD | Font.ITALIC, 25));
        label.setBackground(Color.GRAY);

        JButton button = new JButton("\u8D26\u6237\u7BA1\u7406");

        JButton button_1 = new JButton("\u9884\u5B9A\u7BA1\u7406");

        JButton button_2 = new JButton("\u4FE1\u606F\u67E5\u8BE2");

        JButton button_3 = new JButton("\u4FE1\u606F\u4FEE\u6539");
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                panel.removeAll();
                panel.add(new modification());
                panel.setVisible(true);
            }
        });

        JButton button_4 = new JButton("\u9000\u51FA\u767B\u5F55");

        JButton button_5 = new JButton("\u6362\u623F\u5904\u7406");
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                panel.removeAll();
                panel.add(new roomchange());
                panel.setVisible(true);
            }
        });

        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(button_5, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                        .addComponent(button_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(button_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(button_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(18, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addComponent(button)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(button_1)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(button_2)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(button_3)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(button_4)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(button_5)
                                .addContainerGap(207, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);
        frmHotel.getContentPane().setLayout(groupLayout);
        //用来设置窗口随屏幕大小改变
        //FrameShow.modifyComponentSize(this, 0.6,0.6);
        //sizeWindowOnScreen(this, 0.6, 0.6);
    }
}
