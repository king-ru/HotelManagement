package main;

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
 * @description: 房状态列表查看
 * @author: Mrs.CeYi
 * @create: 2019-01-14 09:58
 **/
public class RoomStatusPanel extends JPanel implements ActionListener {
    private JTable m_view;
    private JPanel p3;

    public RoomStatusPanel(){
        JPanel p1=new JPanel(new BorderLayout());
        //数据库连接与查询
        List<Map<String, Object>> mapList;
        try{
            String sql="select * from Room";
            mapList=JDBCUtils.get().getQueryResults(sql,null);
            String columns[]={"房号","押金","状态","类型","价格","负责人编号","客房电话"};
            Object[][] ob=new Object[mapList.size()][columns.length];

            int i=0;
            int j=0;

            for (Map<String, Object> map1: mapList) {
                //一个map集合即为一个list
                Iterator<Map.Entry<String, Object>> iterator = map1.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = iterator.next();
                    ob[i][j]=entry.getValue();
                    j++;

                }

                i++;
                j=0;

            }
            System.out.println(ob);
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

        }

        catch (SQLException e1) {
            e1.printStackTrace();
        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
