package main.chenru.frontdesk;

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
import java.util.*;
import java.util.List;

/**
 * @program: HotelManagement
 * @description: 房费结账
 * @author: Mrs.CeYi
 * @create: 2019-01-15 17:09
 **/
public class SettleAccountPanel extends JPanel implements ActionListener {
    JPanel p=new JPanel(new BorderLayout());
    JPanel p1=new JPanel(new GridLayout(1,3,20,0));
    JPanel p2=new JPanel();
    //JPanel p4=new JPanel();

    public MyButton find_btn2=new MyButton(3,"查找");
    public MyButton account_btn=new MyButton(2,"结账");
    public JTextField room_no_text=new JTextField(10);
    public JLabel room_label=new JLabel("房间编号");


    public static JTable m_view;
    public Map<String,Object> map=new HashMap<>();
    public int price_all;
    //public static JPanel p3;

   public SettleAccountPanel(){
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
       //if (room_no_text.getText()==""){

       //}


       //数据库连接与查询

       try{

           String temp=room_no_text.getText();
           String sql="select * from Living where Rno=?";
           List list=new ArrayList();
           list.add(temp);
           map=JDBCUtils.get().getQueryResult(sql,list);

           String columns[]={"入住时间","预期居住天数","性别","类型","离开时间","订单编号","姓名","是否换房","房间编号","联系方式","房间单价","房费状态","身份证号"};
           Object[][] ob=new Object[1][columns.length];
           if (map.size()==0){
               JOptionPane.showMessageDialog(null,"没有该房间号,输入错误,请重新输入","系统提示",JOptionPane.ERROR_MESSAGE);
               //FrontActivity.roomNo.setText("");
               room_no_text.setText("");
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
               column.setPreferredWidth(120);

               if (k==2 || k==3 || k==6 )
               {
                   column.setPreferredWidth(60);
               }
               else if (k==7 || k==8 || k==10 ||k==11){
                   column.setPreferredWidth(80);
               }
               else{
                   column.setPreferredWidth(150);
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
       else if (e.getSource()==account_btn){
           int price=0;
           int price_all_1=0;
           String temp1=(String) map.get("Rpricestatus");
           String temp2=(String)map.get("Exchangestatus");
           String temp3=(String)map.get("Indentno");

           //获取类型并设置price
           String room_sort1=(String)map.get("Rsort");
           if (room_sort1=="单人房"){
               price=100;
           }else if (room_sort1=="标间"){
               price=200;
           }else if (room_sort1=="大床房"){
               price=300;
           }

          //获取当前时间与入住时间之差天数
           Date date=(Date)map.get("Lcomtime");
           System.out.println("date="+date);
           long diffMinute = (date.getTime() - new Date().getTime());
           long diffDay = diffMinute;

           //String room_sort
           System.out.println("temp1="+temp1);
           System.out.println("temp2="+temp2);
           System.out.println("diffDay="+diffDay);

           if (temp1=="未支付" && temp2=="否"){
               price_all_1=(int)diffDay*price;
                String s="您一共消费"+price_all_1+"元,"+"请确认是否结账?";
                int result=JOptionPane.showConfirmDialog(null,s,"系统提示",JOptionPane.YES_NO_OPTION);
                if (result==0){
                    JOptionPane.showMessageDialog(null,"结账成功!","系统提示",JOptionPane.PLAIN_MESSAGE);

                }
                else {
                    JOptionPane.showMessageDialog(null,"结账成功!","系统提示",JOptionPane.WARNING_MESSAGE);
                }
           }
           else if (temp1=="未支付" && temp2=="是"){
               //去查Exchange表当中
               try{
                    List list=new ArrayList();
                    list.add(temp3);
                    Map map=new HashMap();
                    String sql="select * from Exchange where Indent=?";
                    map=JDBCUtils.get().getQueryResult(sql,list);

                    //获取之前房间类型和天数
                    String room_sort2=(String) map.get("Rpreno");
                    int days=(Integer)map.get("Days");



                   if (room_sort2=="单人房"){
                       price=100;
                   }else if (room_sort2=="标间"){
                       price=200;
                   }else if (room_sort2=="大床房"){
                       price=300;
                   }
                   price_all=price_all_1+price*days;
                    //弹出对话框
                   String s="您一共消费"+price_all+"元,"+"请确认是否结账?";
                   int result=JOptionPane.showConfirmDialog(null,s,"系统提示",JOptionPane.YES_NO_OPTION);
                   if (result==0){
                       JOptionPane.showMessageDialog(null,"结账成功!","系统提示",JOptionPane.PLAIN_MESSAGE);

                   }
                   else {
                       JOptionPane.showMessageDialog(null,"结账成功!","系统提示",JOptionPane.WARNING_MESSAGE);
                   }

               } catch (SQLException e1) {
                   e1.printStackTrace();
               }
           }
           else if (temp1=="已支付"){
               JOptionPane.showMessageDialog(null,"您已支付","系统提示",JOptionPane.PLAIN_MESSAGE);
               room_no_text.setText("");
           }



       }

    }
}
