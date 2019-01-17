package main.haoda.account;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

public class countActivity extends JPanel {

    private JTable view;
    //private DefaultTableModel tablemodel = new DefaultTableModel();

    JPanel panel = new JPanel();
    public countActivity() throws Exception {
        this.setLayout(new BorderLayout());
       // panel.setVisible(false);
        //panel.removeAll();
        DBoper sql = new DBoper();
        try {
            sql.getConn();
        } catch (Exception e1) {

            e1.printStackTrace();
        }
        ResultSet rs=sql.executeQuery("select * from Coun");

        rs.last();

        int k=0 ;

        k = rs.getRow();

        if(k==0) {
            JOptionPane.showMessageDialog(this, "您查询的表为空", "系统提示", JOptionPane.WARNING_MESSAGE);
        }

        rs.beforeFirst();

        String ob[][]=new String[k][10];

        for(int i=0;i<k&&rs.next();i++) {
            ob[i][0]=rs.getString("Indentid");
            ob[i][1]=rs.getString("Cname");
            ob[i][2]=rs.getString("Ctel");
            ob[i][3]=rs.getString("Getmoney");
            ob[i][4]=rs.getString("Reamoney");
            ob[i][5]=rs.getString("Llefttime");

        }

        //String s[]={"订单编号","客户姓名","联系方式","收取金额","收入","离开时间"};

       //tablemodel.setDataVector(ob, s);
        //tablemodel.fireTableDataChanged();
        //tablemodel = new DefaultTableModel(ob, s);
        //view = new JTable(tablemodel);
        //view.setSize(2500,30);

        //JScrollPane sPane = new JScrollPane(view);
        //view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //sPane.setPreferredSize(new Dimension(view.getWidth() - 200, view.getHeight() - 200));
        String s[]={"订单编号","客户姓名","联系方式","收取金额","收入","离开时间"};


        view = new JTable(new DefaultTableModel(ob,s));
        view.setSize(1000,800);
        view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane sPane=new JScrollPane();
        sPane.setViewportView(view);
        sPane.setPreferredSize(new Dimension(view.getWidth() - 200, view.getHeight() - 200));
        panel.add(sPane);
        panel.setVisible(true);


        //panel.add(sPane);
        //panel
    }
}
