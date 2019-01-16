package main.xinyan.Rchange;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.sun.corba.se.impl.presentation.rmi.ExceptionHandlerImpl.ExceptionRW;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import main.chenru.frontdesk.InitSetting;
import main.xinyan.DateBase.DBoper;
import main.xinyan.Rchange.roomchange;


public class rchangeinfo{

    private JFrame frame;
    private JTextField jTextFields[];
    private JComboBox jComboBox = new JComboBox();
    private JComboBox jComboBox1 = new JComboBox();

//	private String[] rStrings = {null,null};

    public rchangeinfo(roomchange modiinfo){
        InitSetting.init();
        jTextFields = modiinfo.getT();
        initialize();
    }

//	public rchangeinfo(searchRsort searchRsort) {
//		rStrings = searchRsort.EXCR();
//		initialize();
//	}

    public rchangeinfo() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("换房处理");

        JLabel[] jLabel = new JLabel[9];
        JLabel[] jLabels = new JLabel[9];
        JPanel pane=new JPanel();
        pane.setSize(320, 260);

        java.awt.Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(pane, "Center");

        JPanel jpanel1 = new JPanel();
        container.add(jpanel1, "North");
        JPanel jpanel2 = new JPanel();
        container.add(jpanel2, "West");
        JPanel jpanel3 = new JPanel();
        container.add(jpanel3, "East");
        JPanel jpanel4 = new JPanel();
        container.add(jpanel4, "South");

        pane.setPreferredSize(new Dimension(320, 260));
        jpanel1.setPreferredSize(new Dimension(0, 25));
        jpanel2.setPreferredSize(new Dimension(50, 0));
        jpanel3.setPreferredSize(new Dimension(50, 0));
        jpanel4.setPreferredSize(new Dimension(0, 25));

        pane.setLayout(new BorderLayout());

        JPanel pane1 = new JPanel(new GridLayout(9, 2));


//        jTextFields[3].setText(rStrings[0]);
//        jTextFields[5].setText(rStrings[1]);

//        jComboBox.setBounds(120, 25, 160, 30);
        for (int k=1;k<10;k++){
            jComboBox.addItem("000"+k);
        }
        jComboBox.addItem("00"+10);
        jComboBox.addItem("00"+11);
        jComboBox.addItem("00"+12);
        jComboBox.addItem("00"+13);
        jComboBox.addItem("00"+14);
        jComboBox.addItem("00"+15);
        jComboBox.addItem("00"+16);

        jComboBox1.addItem("标间");
        jComboBox1.addItem("单人房");
        jComboBox1.addItem("大床房");

        String s1[] = {"订单编号","姓名","原客房编号","新客房编号","原客房类型","新客房类型","原房间已住天数","换房日期","原预计入住天数"};
        for(int i = 0;i<9;i++){
            if(i!=3&&i!=5){
                jLabel[i] = new JLabel(s1[i]);
                pane1.add(jLabel[i]);
                jTextFields[i] = new JTextField(jTextFields[i].getText(),10);
                pane1.add(jTextFields[i]);
                pane.add(pane1,"Center");
            }else if(i == 3){
                jLabel[i] = new JLabel(s1[i]);
                pane1.add(jLabel[i]);
                pane1.add(jComboBox);
                pane.add(pane1,"Center");
            }else if(i == 5){
                jLabel[i] = new JLabel(s1[i]);
                pane1.add(jLabel[i]);
                pane1.add(jComboBox1);
                pane.add(pane1,"Center");
            }
        }

        jTextFields[0].setEditable(false);
        jTextFields[1].setEditable(false);
        jTextFields[2].setEditable(false);
        jTextFields[4].setEditable(false);
        jTextFields[6].setEditable(false);
        jTextFields[7].setEditable(false);
        jTextFields[8].setEditable(false);

        JPanel jPanel = new JPanel(new FlowLayout(java.awt.FlowLayout.CENTER));

        String s[] = {"查房","确认换房","取消"};
        JButton []jb = new JButton[3];
        int j = 0;
        for(j = 0;j<jb.length;j++){
            jb[j] = new JButton(s[j]);
            jb[j].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event) {
                    if(event.getActionCommand() == "查房"){
                        searchRsort mi = new searchRsort(rchangeinfo.this);
                    }else if(event.getActionCommand() == "确认换房"){
                        //添加Exchange，修改Room状态
                        int k = JOptionPane.showConfirmDialog(pane, "确认换房？", "系统提示", JOptionPane.YES_NO_OPTION);
                        if(k == 0){
                            DBoper db = new DBoper();
                            try {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String roomno = jComboBox.getSelectedItem()+"";
                                String rsort = jComboBox1.getSelectedIndex()+"";
                                String s1 = sdf.format(sdf.parse(jTextFields[7].getText()));
                                String s2 = sdf.format(sdf.parse("2000-01-01 00:00:00"));
                                String sql = "insert into Exchange values('"+jTextFields[0].getText()+
                                        "','"+jTextFields[1].getText()+
                                        "','"+jTextFields[2].getText()+
                                        "','"+roomno+
                                        "','"+jTextFields[4].getText()+
                                        "','"+rsort+
                                        "',"+jTextFields[6].getText()+
                                        ",'"+s1+
                                        "',"+jTextFields[8].getText()+
                                        ")";

                                String rno = "select Rsort,Rprice,Rstatus from Room where Rno='"+jTextFields[3].getText()+"'";
                                db.getConn();
                                ResultSet rSet = db.executeQuery(rno, new String[]{});
                                if(!rSet.next()||rSet.getString(3)!="0"||rSet.getString(1)!=jTextFields[5].getText()){
                                    JOptionPane.showConfirmDialog(pane, "房间编号或房间类型输入错误，请重新输入！", "系统提示", JOptionPane.YES_NO_OPTION);
                                }else{
                                    db.Autocommit();

                                    int k1 = db.executeUpdata(sql, new String[]{});

                                    String sql1 = "Update Room set Rstatus=2 where Rno=?";
                                    int k2 = db.executeUpdata(sql1, new String[]{jTextFields[3].getText()});

                                    String sql2 = "Update Room set Rstatus=0 where Rno=?";
                                    int k3 = db.executeUpdata(sql2, new String[]{jTextFields[2].getText()});

                                    String sql3 = "Update Living set Rno='"+ jTextFields[3].getText() + "',Rprice='"+rSet.getString(2)+"',Rsort='"+rSet.getString(1)+"',Lcomtime='"+s1+"',Exdays='"+jTextFields[8].getText()+"',Llefttime='"+s2+"',Exchangestatus='是'  where Indentid=?";
                                    int k4 = db.executeUpdata(sql3, new String[]{jTextFields[0].getText()});

                                    db.commit();

                                    if(k1!=0&&k2!=0&&k3!=0&&k4!=0)
                                        JOptionPane.showMessageDialog(pane, "换房成功！", "系统提示", JOptionPane.WARNING_MESSAGE);
                                    else{
                                        db.Back();
                                        db.Autocommit1();
                                        JOptionPane.showMessageDialog(pane, "换房失败！", "系统提示", JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }finally {
                                db.closeAll();
                            }
                        }
                    }else if(event.getActionCommand() == "取消"){
                        frame.dispose();
                    }

                }
            });
            jPanel.add(jb[j]);

            container.add(jPanel, "South");

            frame.setSize(500, 400);
            frame.setLocation(100, 80);
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);
        }



    }
}
