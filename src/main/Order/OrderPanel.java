package main.Order;

import sun.swing.table.DefaultTableCellHeaderRenderer;
import ui.MyButton;
import utils.JDBCUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public MyButton find_btn2=new MyButton(3,"查找");
    public MyButton account_btn=new MyButton(2,"预定");
    //public JTextField room_no_text=new JTextField(10);
    public JComboBox room_no_text=new JComboBox();
    public JLabel room_label=new JLabel("房间类型");

    public static JTable m_view;
    public Map<String,Object> map=new HashMap<>();

    public OrderPanel(){
        room_no_text.addItem("单人房");
        room_no_text.addItem("标间");
        room_no_text.addItem("大床房");
        p1.add(room_label);
        p1.add(room_no_text);
        p1.add(find_btn2);
        p1.add(account_btn);
        //p4.add(account_btn);


        p.add(p1,"North");
        p.add(p2,"Center");
        //p.add(p4,"South");
        this.add(p);
        this.setVisible(true);
        find_btn2.addActionListener(this);
        account_btn.addActionListener(this);
    }


    public  void init_table(){


        try{

            String temp=room_no_text.getSelectedItem()+"";
            String sql="select * from Room where Rstatus=? and Rsort=?";
            List list=new ArrayList();
            list.add("0");
            list.add(temp);
            map=JDBCUtils.get().getQueryResult(sql,list);

            String columns[]={"房号","押金","状态","类型","价格","负责人编号","客房电话"};
            Object[][] ob=new Object[1][columns.length];
            if (map.size()==0){
                JOptionPane.showMessageDialog(null,"没有该房间号,输入错误,请重新输入","系统提示",JOptionPane.ERROR_MESSAGE);
                //FrontActivity.roomNo.setText("");

            }
            else{
                int i=0;
                for (Object value:map.values()){
                    ob[0][i]=value;
                    i++;
                }

                System.out.println(ob);

            }
            DefaultTableModel dtm = new DefaultTableModel(ob,columns){
                public boolean isCellEditable(int row, int column) {
                    // TODO Auto-generated method stub
                    return false;//返回true表示能编辑，false表示不能编辑
                }
            };
            TableColumn column=null;
            m_view=new JTable(dtm);

            for (int k=0;k<columns.length;k++){
                column=m_view.getColumnModel().getColumn(k);
                if (k==5 || k==6 ){
                    column.setPreferredWidth(100);
                }
                else {
                    column.setPreferredWidth(50);
                }
            }

            //设置表头数据居中显示
            //设置表数据居中显示

            DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
            cr.setHorizontalAlignment(JLabel.CENTER);
            m_view.setDefaultRenderer(Object.class, cr);


            //设置表头居中显示
            DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
            hr.setHorizontalAlignment(JLabel.CENTER);
            m_view.getTableHeader().setDefaultRenderer(hr);



            m_view.setSize(2500,30);
            m_view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            JScrollPane sPane=new JScrollPane(m_view);
            //面板的宽度随着窗口大小变化
            //sPane.setPreferredSize(new Dimension(m_view.getWidth()-300,m_view.getHeight()-300));

            //p3=new JPanel(new FlowLayout());
            //p1.add(p3,"Center");
            p2.add(sPane);
            p2.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==find_btn2){
            p2.setVisible(false);
            p2.removeAll();
            init_table();
        }
        else {
            new OrderFrame();
        }
    }
}
