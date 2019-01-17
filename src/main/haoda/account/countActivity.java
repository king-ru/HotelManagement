package main.haoda.account;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class countActivity extends JPanel {
    public JFrame jf = new JFrame();
    public countActivity() throws SQLException  {

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
        String s[]={"订单编号","客户姓名","联系方式","收取金额","收入","离开时间"};

        JTable table=new JTable(ob,s);
        JScrollPane JSP=new JScrollPane(table);
        jf.getContentPane().add(JSP);

        JPanel panel = new JPanel();
        jf.getContentPane().add(panel, BorderLayout.NORTH);

        JLabel label1 = new JLabel("今日收入:");
        panel.add(label1);
        ResultSet a=sql.executeQuery("select sum(reamoney)from Coun where DATEDIFF ( DAY,Llefttime,(SELECT GETDATE() AS CurrentDateTime))=0");
        a.next();
        JLabel label2 = new JLabel(a.getString(1)+"元");
        panel.add(label2);

        jf.setBounds(280,200,500,400);
        jf.setVisible(true);



    }
}
