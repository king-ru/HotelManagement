package main.haoda.account;

/**
 * @program: HotelManagement
 * @description: 结账
 * @author: Mrs.CeYi
 * @create: 2019-01-16 18:52
 **/
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.event.ActionEvent;

public class closingtActivity extends JPanel {
    private JTextField in;

    public closingtActivity() {

        JLabel label = new JLabel("输入结账房间号:");

        in = new JTextField();
        in.setColumns(10);

        JButton button = new JButton("修改数据库并结账");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int b = 1;
                b = JOptionPane.showConfirmDialog(button, "确认结账?", "系统提示", JOptionPane.YES_NO_OPTION);// 否=1 是=0
                if (b == 0) {
                    int nday = 0, rday = 0, nexday = 0, rexday = 0, price = 0, nprice = 0, rprice = 0;
                    String nsort = null, rsort = null, cname = null, rnowno = null, rpreno = null, extime = null;
                    DBoper sql = new DBoper();
                    try {
                        sql.getConn();
                    } catch (Exception e1) {

                        e1.printStackTrace();
                    }
                    String s = in.getText();
                    String Exchangestatus = null;
                    ResultSet rs = sql.executeQuery("select Exchangestatus from Living where Rno='" + s
                            + "'and Lcomtime=(select max(Lcomtime) from Living where Rno='" + s + "')");
                    try {

                        while (rs.next())
                            Exchangestatus = rs.getString(1);
                    } catch (SQLException e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                    // 空表

                    if (Exchangestatus == null) {

                        JOptionPane.showMessageDialog(button, "输入的房间号有误", "系统提示", JOptionPane.WARNING_MESSAGE);
                    }
                    // 换房
                    else {
                        if (Exchangestatus.equals("是")) {
                            sql.executeUpdata(
                                    "update Living set Llefttime=(SELECT GETDATE() AS CurrentDateTime) where Rno='" + s
                                            + "'and Lcomtime=(select max(Lcomtime) from Living where Rno='" + s + "')");
                            sql.executeUpdata("update Room set Rstatus='0' where Rno='" + s + "'");
                            ResultSet y = sql.executeQuery(
                                    "select datediff(day,Lcomtime,Llefttime),Living.Exdays,Days,Exchange.Exdays,Exchange.Rnowsort,Exchange.Rpresort,living.Cname,Exchange.Rnowno,Exchange.Rpreno,Exchtime from Living,Room,Exchange where Room.Rno='"
                                            + s + "'and Lcomtime=(select max(Lcomtime) from Living where Rno='" + s
                                            + "')and Living.Indentid=Exchange.Indentid");
                            try {
                                while (y.next()) {
                                    nday = y.getInt(1);
                                    nexday = y.getInt(2);
                                    rday = y.getInt(3);
                                    rexday = y.getInt(4);
                                    nsort = y.getString(5);
                                    rsort = y.getString(6);
                                    cname = y.getString(7);
                                    rnowno = y.getString(8);
                                    rpreno = y.getString(9);
                                    extime = y.getString(10);
                                }
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            if (nday == 0)
                                nday = 1;
                            if (rday == 0)
                                rday = 1;

                            ResultSet y1 = sql.executeQuery("select Rprice from room where  Rsort='" + nsort + "'");
                            ResultSet y2 = sql.executeQuery("select Rprice from room where  Rsort='" + rsort + "'");
                            try {
                                y1.next();
                                nprice = y1.getInt(1);
                                y2.next();
                                rprice = y2.getInt(1);
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            price = (nprice * nexday + rprice * rexday + 50) - (nprice * nday + rprice * rday);
                            JOptionPane.showMessageDialog(button,
                                    "客户" + cname + ",在" + rsort + rpreno + "住房" + nday + "天后,于" + extime + "换房到" + nsort
                                            + rnowno + "住房" + nday + "天,退还押金和房费共" + price + "元",
                                    "系统提示", JOptionPane.WARNING_MESSAGE);
                        } else {

                            // 不换房
                            sql.executeUpdata(
                                    "update Living set Llefttime=(SELECT GETDATE() AS CurrentDateTime) where Rno='" + s
                                            + "'and Lcomtime=(select max(Lcomtime) from Living where Rno='" + s + "')");
                            sql.executeUpdata("update Room set Rstatus='0' where Rno='" + s + "'");
                            ResultSet n = sql.executeQuery(
                                    "select datediff(day,Lcomtime,Llefttime),Room.Rprice,Exdays,Cname,living.Rno,living.Rsort from Living,Room where Room.Rno='"
                                            + s + "'and Lcomtime=(select max(Lcomtime) from Living where Rno='" + s
                                            + "')");

                            try {
                                while (n.next()) {
                                    nday = n.getInt(1);
                                    nprice = n.getInt(2);
                                    nexday = n.getInt(3);
                                    cname = n.getString(4);
                                    rnowno = n.getString(5);
                                    nsort = n.getString(6);
                                }
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            if (nday == 0)
                                nday = 1;
                            if (nday < nexday) {
                                price = (nexday - nday) * nprice + 50;
                                JOptionPane.showMessageDialog(button, "客户" + cname + "在" + nsort + rnowno + "住房" + nday
                                        + "天后,提前退房,返还房费和押金共" + price + "元", "系统提示", JOptionPane.WARNING_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(button,
                                        "客户" + cname + "在" + nsort + rnowno + "住房" + nday + "天后,正常退房,退还押金50元", "系统提示",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                } else
                    JOptionPane.showMessageDialog(button, "取消退房", "系统提示", JOptionPane.WARNING_MESSAGE);

            }

        });

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup().addGap(53).addComponent(label).addGap(18)
                                        .addComponent(in, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup().addGap(141).addComponent(button)))
                        .addContainerGap(174, Short.MAX_VALUE)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup().addGap(34)
                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(
                                in, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(32).addComponent(button).addContainerGap(190, Short.MAX_VALUE)));
        setLayout(groupLayout);
    }
}
