package main.haoda.account;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;


public class closingtActivity extends JPanel {


    private JTextField in;
    private JTable view;
    //private DefaultTableModel tablemodel = new DefaultTableModel();

    JPanel panel = new JPanel();

    public closingtActivity() {

        JLabel label = new JLabel("输入结账房间号:");

        in = new JTextField();
        in.setColumns(10);

        JButton button = new JButton("结账");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                panel.removeAll();

                int b = 1;
                b = JOptionPane.showConfirmDialog(button, "确认结账?", "系统提示", JOptionPane.YES_NO_OPTION);// 否=1 是=0
                if (b == 0) {
                    panel.setVisible(false);
                    panel.removeAll();

                    int nday = 0, rday = 0, nexday = 0, rexday = 0, price = 0, nprice = 0, rprice = 0;
                    String nsort = null, rsort = null, cname = null, rnowno = null, rpreno = null, extime = null,indentid=null,ctel=null,lefttime=null,ju=null;
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

                    //查到空表
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
                                    "select datediff(day,Lcomtime,Llefttime),Living.Exdays,Days,Exchange.Exdays,Exchange.Rnowsort,Exchange.Rpresort,living.Cname,Exchange.Rnowno,Exchange.Rpreno,Exchtime,Living.indentid,Ctel,Living.Llefttime from Living,Room,Exchange where Room.Rno='"
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
                                    indentid=y.getString(11);
                                    ctel=y.getString(12);
                                    lefttime=y.getString(13);
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
                            //插入到统计表
                            ResultSet judge=sql.executeQuery("select Indentid from Coun where Indentid='"+indentid+"'");
                            try {
                                while (judge.next())
                                    ju = judge.getString(1);
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            if(ju==null) {




                                sql.executeUpdata("insert into Coun values('"+indentid+"','"+cname+"','"+ctel+"',"+(nprice * nexday + rprice * rexday + 50)+","+(nprice * nday + rprice * rday)+",'"+lefttime+"')");

                            //System.out.println("insert into Coun values('"+indentid+"','"+cname+"','"+ctel+"',"+(nprice * nexday + rprice * rexday + 50)+","+(nprice * nday + rprice * rday)+",'"+lefttime+"')");

                            JOptionPane.showMessageDialog(button,
                                    "客户" + cname + ",在" + rsort + rpreno + "住房" + rday + "天后,于" + extime + "换房到" + nsort
                                            + rnowno + "住房" + nday + "天,退还押金和房费共" + price + "元",
                                    "系统提示", JOptionPane.WARNING_MESSAGE);

                            String ob[][] = new String[1][9];
                            ob[0][0] = cname;
                            ob[0][1] = rsort;
                            ob[0][2] = rpreno;
                            ob[0][3] = String.valueOf(rday);
                            ob[0][4] = extime;
                            ob[0][5] = nsort;
                            ob[0][6] = rnowno;
                            ob[0][7] = String.valueOf(nday);
                            ob[0][8] = String.valueOf(price);

                            String x[] = { "客户姓名", "原房间类型", "原房间号", "原房间住房时间", "换房时间", "现房间类型", "现房间号", "现房间住房时间", "退还金额" };

                            //tablemodel.setDataVector(ob, x);
                            //tablemodel.fireTableDataChanged();
                            //tablemodel = new DefaultTableModel(ob, x);

                            view = new JTable(new DefaultTableModel(ob,x));

                            view.setSize(1800,800);
                            view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                            JScrollPane sPane=new JScrollPane(view);

                            //面板的宽度随着窗口大小变化
                            //sPane.setPreferredSize(new Dimension(m_view.getWidth()-300,m_view.getHeight()-300));

                            panel.add(sPane);
                            panel.setVisible(true);}
                            else
                                JOptionPane.showMessageDialog(button,"该房间已经结过账,请重试","系统提示", JOptionPane.WARNING_MESSAGE);

                        } else {

                            // 不换房
                            sql.executeUpdata(
                                    "update Living set Llefttime=(SELECT GETDATE() AS CurrentDateTime) where Rno='" + s
                                            + "'and Lcomtime=(select max(Lcomtime) from Living where Rno='" + s + "')");
                            sql.executeUpdata("update Room set Rstatus='0' where Rno='" + s + "'");
                            ResultSet n = sql.executeQuery(
                                    "select datediff(day,Lcomtime,Llefttime),Room.Rprice,Exdays,Cname,living.Rno,living.Rsort,Living.indentid,Ctel,Living.Llefttime from Living,Room where Room.Rno='"
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
                                    indentid=n.getString(7);
                                    ctel=n.getString(8);
                                    lefttime=n.getString(9);
                                }
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            if (nday == 0)
                                nday = 1;
                            if (nday < nexday) {
                                price = (nexday - nday) * nprice + 50;
                                ResultSet judge=sql.executeQuery("select Indentid from Coun where Indentid='"+indentid+"'");
                                try {
                                    while (judge.next())
                                        ju = judge.getString(1);
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                                if(ju==null) {

                                    sql.executeUpdata("insert into Coun values('"+indentid+"','"+cname+"','"+ctel+"',"+(nprice * nexday +50)+","+(nprice * nday )+",'"+lefttime+"')");
                                JOptionPane.showMessageDialog(button, "客户" + cname + "在" + nsort + rnowno + "住房" + nday
                                        + "天后,提前退房,返还房费和押金共" + price + "元", "系统提示", JOptionPane.WARNING_MESSAGE);

                                String ob[][] = new String[1][5];
                                ob[0][0] = cname;
                                ob[0][1] = nsort;
                                ob[0][2] = String.valueOf(rnowno);
                                ob[0][3] = String.valueOf(nday);
                                ob[0][4] = String.valueOf(price);

                                String x[] = { "客户姓名", "房间类型", "房间号", "房间住房时间", "退还金额" };
                                //tablemodel.setDataVector(ob, x);
                                //tablemodel.fireTableDataChanged();
                                view = new JTable(new DefaultTableModel(ob,x));

                                view.setSize(1800,800);
                                view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                JScrollPane sPane=new JScrollPane(view);
                                panel.add(sPane);
                                panel.setVisible(true);}
                                else
                                    JOptionPane.showMessageDialog(button,"该房间已经结过账,请重试","系统提示", JOptionPane.WARNING_MESSAGE);


                            } else {

                                //多住情况没写

                                    ResultSet judge=sql.executeQuery("select Indentid from Coun where Indentid='"+indentid+"'");
                                    try {
                                        while (judge.next())
                                            ju = judge.getString(1);
                                    } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                    if(ju==null) {

                                        sql.executeUpdata("insert into Coun values('"+indentid+"','"+cname+"','"+ctel+"',"+(nprice * nexday+50 )+","+(nprice * nday )+",'"+lefttime+"')");
                                JOptionPane.showMessageDialog(button,
                                        "客户" + cname + "在" + nsort + rnowno + "住房" + nday + "天后,正常退房,退还押金50元", "系统提示",
                                        JOptionPane.WARNING_MESSAGE);

                                String ob[][] = new String[1][5];
                                ob[0][0] = cname;
                                ob[0][1] = nsort;
                                ob[0][2] = String.valueOf(rnowno);
                                ob[0][3] = String.valueOf(nday);
                                ob[0][4] = String.valueOf(50);

                                String x[] = { "客户姓名", "房间类型", "房间号", "房间住房时间", "退还金额" };
                                view = new JTable(new DefaultTableModel(ob,x));

                                view.setSize(1800,800);
                                view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                JScrollPane sPane=new JScrollPane(view);
                                panel.add(sPane);
                                panel.setVisible(true);}
                                    else
                                        JOptionPane.showMessageDialog(button,"该房间已经结过账,请重试","系统提示", JOptionPane.WARNING_MESSAGE);

                            }
                        }
                    }
                } else
                    JOptionPane.showMessageDialog(button, "取消退房", "系统提示", JOptionPane.WARNING_MESSAGE);

            }

        });

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                .addGap(53)
                                .addComponent(label)
                                .addGap(18)
                                .addComponent(in, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                .addGap(50)
                                .addComponent(button)
                                .addContainerGap(126, Short.MAX_VALUE))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap(33, Short.MAX_VALUE)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(34)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(in, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }
}