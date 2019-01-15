package main;

import utils.JDBCUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @program: HotelManagement
 * @description:今日订单展示
 * @author: Mrs.CeYi
 * @create: 2019-01-14 15:58
 **/
public class TodayIndentPanel extends JPanel {
    private JTable m_view;
    private JPanel p3;
    public TodayIndentPanel(){
        JPanel p1=new JPanel(new BorderLayout());
        //数据库连接与查询
        List<Map<String, Object>> mapList;
        try{
            String sql="select * from Living";
            mapList=JDBCUtils.get().getQueryResults(sql,null);
            String columns[]={"入住时间","性别","订单号","类型","离开时间","姓名","是否换房","房间号","联系方式","房费","是否支付","预期离开时间","身份证号"};
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
                if (k==0 || k==2 || k==4 ||k==8 || k==11 )
                {
                    column.setPreferredWidth(120);
                }
                else if(k==12){
                    column.setPreferredWidth(150);
                }
                else if (k==1 || k==6){
                    column.setPreferredWidth(60);
                }
                /*
                if ( k==4 ||k==9 || k==10 ||k==3 ||k==5 ||k==6){
                    column.setPreferredWidth(100);
                }*/
                else {
                    column.setPreferredWidth(80);
                }
            }
            m_view.setSize(1750,700);
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


}
