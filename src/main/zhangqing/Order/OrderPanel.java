package main.zhangqing.Order;

import main.haoda.account.DBoper;
import ui.MyButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: HotelManagement
 * @description: 预订房间
 * @author: Mrs.CeYi
 * @create: 2019-01-16 16:40
 **/
public class OrderPanel extends JPanel implements ActionListener {
    JPanel p=new JPanel(new BorderLayout());
    JPanel p1=new JPanel(new GridLayout(1,3,20,0));
    JPanel p2=new JPanel();
    //JPanel p4=new JPanel();

    public MyButton find_btn1=new MyButton(3,"查找");
    public MyButton order_btn1=new MyButton(2,"预定");
    //public JTextField room_no_text=new JTextField(10);
    public JComboBox room_sort=new JComboBox();
    public JLabel room_label=new JLabel("房间类型");

    public static JTable m_view;
    public Map<String,Object> map=new HashMap<>();

    public OrderPanel(){
        room_sort.addItem("单人房");
        room_sort.addItem("标间");
        room_sort.addItem("大床房");
        p1.add(room_label);
        p1.add(room_sort);
        p1.add(find_btn1);
        p1.add(order_btn1);
        //p4.add(account_btn);


        p.add(p1,"North");
        p.add(p2,"Center");
        //p.add(p4,"South");
        this.add(p);
        this.setVisible(true);
        find_btn1.addActionListener(this);
        order_btn1.addActionListener(this);
    }


    public  void init_table() throws SQLException {

        JTable view;
        DefaultTableModel tablemodel = new DefaultTableModel();
        JPanel panel = new JPanel();

        DBoper sql = new DBoper();
        try {
            sql.getConn();
        } catch (Exception e1) {

            e1.printStackTrace();
        }
        String a=(String) room_sort.getSelectedItem();
        ResultSet rs=sql.executeQuery("select * from Room where Rsort='"+a+"'and Rstatus='0'");
        rs.last();
        int k=0 ;
        k = rs.getRow();
        if(k==0) {
            JOptionPane.showMessageDialog(find_btn1, "您查询的表为空", "系统提示", JOptionPane.WARNING_MESSAGE);
        }
        rs.beforeFirst();
        String ob[][]=new String[k][10];

        for(int i=0;i<k&&rs.next();i++) {
            ob[i][0]=rs.getString("Rno");
            ob[i][1]=rs.getString("Rsort");
            ob[i][2]=rs.getString("Rstatus");
            ob[i][3]=rs.getString("Rprice");
            ob[i][4]=rs.getString("Rpreprice");
            ob[i][5]=rs.getString("Userno");
            ob[i][6]=rs.getString("Rtel");
        }

        String s[]={"房间号","房间类型","房间状态","价格","押金","管理员编号","房间电话"};
        tablemodel.setDataVector(ob, s);
        tablemodel.fireTableDataChanged();
        view = new JTable(tablemodel);
        TableColumn column=null;
        for (int k1=0;k<s.length;k++){
            column=view.getColumnModel().getColumn(k);
            if (k1==5 ){
                column.setPreferredWidth(100);
            }

        }



        view.setSize(2500,30);

        JScrollPane sPane = new JScrollPane(view);
        view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        panel.add(sPane);
        p2.add(sPane);
        p2.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==find_btn1){
            p2.setVisible(false);
            p2.removeAll();
            try {
                init_table();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        else {
            new OrderFrame();
        }
    }
}
