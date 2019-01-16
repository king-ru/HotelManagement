package main.xinyan.Rchange;

import main.xinyan.DateBase.DBoper;
import main.xinyan.Order.modiinfo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class searchRsort {

    private int message = 0;
    private JFrame frame;
    private JTable table = new JTable(){
        @Override
        public boolean isCellEditable(int row,int column){
            return false;
        }
    };

    public String[] rStrings = new String[2];
    /**
     * Launch the application.
     */

    public searchRsort(rchangeinfo modiinfo){
        initialize();
    }

    public searchRsort(modiinfo modiinfo){
        initialize();
    }

    /**
     * Create the application.
     */
    public searchRsort() {

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("换房处理——查找房间");
        java.awt.Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        JPanel jp = new JPanel();
        JLabel jLabel = new JLabel("请输入房间类型");
        JTextField jtf = new JTextField();
        jtf.setColumns(10);
        jp.add(jLabel);
        jp.add(jtf);
        container.add(jp, "North");

        JPanel panel=new JPanel();
        panel.setSize(400, 300);
        container.add(panel, "Center");
        panel.setLayout(new BorderLayout());

        JPanel pane=new JPanel();
        panel.add(pane,"Center");

        JPanel panel1=new JPanel();
        panel.add(panel1,"South");
        JButton button1 = new JButton("完成");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int count=table.getSelectedRow();//获取你选中的行号（记录）
                if(count==-1){
                    JOptionPane.showMessageDialog(panel, "请选择换房客房！", "系统提示", JOptionPane.WARNING_MESSAGE);
                }else {
                    if((table.getValueAt(count, 2).toString()).equals("0")){
                        rStrings[0] = table.getValueAt(count, 0).toString();
                        rStrings[1] = table.getValueAt(count, 1).toString();
                        frame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(panel, "该房不可换！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        JButton button2 = new JButton("取消");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                frame.dispose();
            }
        });
        panel1.add(button1);
        panel1.add(button2);

        JButton button = new JButton("\u67E5\u8BE2");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBoper db = new DBoper();
                try {
                    pane.removeAll();
                    pane.setVisible(true);
                    db.getConn();
                    String st1 = jtf.getText();
                    String st2 = "select *from Room where Rsort=?";
                    ResultSet rSet = db.executeQuery(st2, new String[]{st1});
                    rSet.last();
                    int k = rSet.getRow();
                    rSet.beforeFirst();
                    if(k!=0){
                        message = 1;
                        Object ob[][] = new Object[k][7];
                        for(int i = 0;(i<k)&&(rSet.next());i++){
                            ob[i][0] = rSet.getString(1);
                            ob[i][1] = rSet.getString(2);
                            ob[i][2] = rSet.getString(3);
                            ob[i][3] = rSet.getInt(4);
                            ob[i][4] = rSet.getInt(5);
                            ob[i][5] = rSet.getString(6);
                            ob[i][6] = rSet.getString(7);

                        }
                        String s1[] = {"编号","类型","状态","房费","押金","负责人编码","客房电话"};
                        table.setModel(new javax.swing.table.DefaultTableModel(ob, s1));
                        table.setSize(450, 180);
                        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        for(int i = 0;i<k;i++){
                            if(table.getValueAt(i, 2).toString()=="2"){
//								table.setRowSelectionAllowed (true);//设置可否被选择.默认为false
//								table.setSelectionBackground (Color.white);//设置所选择行的背景色
//								DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer();
//								backGroundColor.setBackground(Color.blue);
                                table.isCellEditable(i, 2);
                            }
                        }
                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setPreferredSize(new Dimension(table.getWidth(),table.getHeight()));
                        pane.add(scrollPane);

                    }else{
                        JOptionPane.showConfirmDialog(pane, "房间类型输入错误，请重新输入！", "系统提示", JOptionPane.YES_NO_OPTION);
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } finally{
                    db.closeAll();
                }

                if(message == 0){
                    JOptionPane.showMessageDialog(pane, "您查询的信息不存在！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    //pane.removeAll();
                    pane.setVisible(false);
                }else{
                    message = 0;
                    container.setVisible(false);
                    container.removeAll();
                    container.add(jp,"North");
                    container.add(panel,"Center");

                    container.setVisible(true);
                }
            }
        });

        jp.add(button);




        frame.setSize(550, 400);
        frame.setLocation(100, 80);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }

    public String[] EXCR(){
        return rStrings;
    }

}
