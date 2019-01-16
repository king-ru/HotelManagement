package main.chenru.room;

import main.chenru.frontdesk.FrontActivity;
import utils.JDBCUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * @program: HotelManagement
 * @description:房间查找
 * @author: Mrs.CeYi
 * @create: 2019-01-14 15:28
 **/
public class RoomSearchPanel extends JPanel implements ActionListener {

    private JTable m_view;
    private JPanel p3;
    public RoomSearchPanel(){
        JPanel p1=new JPanel(new BorderLayout());
        Map<String,Object> map=new HashMap<>();
        try{
            String temp=FrontActivity.roomNo.getText();
            List list=new ArrayList();
            list.add(temp);
            String sql="select * from Room where Rno=?";
            map=JDBCUtils.get().getQueryResult(sql,list);

            String columns[]={"房号","押金","状态","类型","价格","负责人编号","客房电话"};
            Object[][] ob=new Object[1][columns.length];
            if (map.size()==0){
                JOptionPane.showMessageDialog(null,"没有该房间号,输入错误,请重新输入","系统提示",JOptionPane.ERROR_MESSAGE);
                FrontActivity.roomNo.setText("");
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
            m_view.setSize(800,700);
            m_view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            JScrollPane sPane=new JScrollPane(m_view);
            //面板的宽度随着窗口大小变化
            sPane.setPreferredSize(new Dimension(m_view.getWidth()-300,m_view.getHeight()-300));

            p3=new JPanel(new FlowLayout());
            p1.add(p3,"Center");
            p3.add(sPane);
            this.add(p1);
            this.setVisible(true);



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
