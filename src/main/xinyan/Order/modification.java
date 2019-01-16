package main.xinyan.Order;

import main.chenru.frontdesk.InitSetting;
import main.xinyan.Guest.guestmodi;
import main.xinyan.Room.roommodi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class modification extends JPanel {

    /**
     * Create the application.
     */
    public modification() {
        InitSetting.init();
        setBorder(null);
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setLayout(new GridLayout(1, 0, 0, 0));

        JPanel panel = new JPanel();
        add(panel);

        JPanel panel_1 = new JPanel();

        JButton button = new JButton("\u9884\u8BA2\u4FE1\u606F\u4FEE\u6539");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel_1.setVisible(false);
                panel_1.removeAll();
                panel_1.add(new ordermodi());
                panel_1.setVisible(true);
            }
        });

        JButton button_1 = new JButton("\u5728\u4F4F\u4FE1\u606F\u4FEE\u6539");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel_1.setVisible(false);
                panel_1.removeAll();
                panel_1.add(new guestmodi());
                panel_1.setVisible(true);
            }
        });

        JButton button_2 = new JButton("\u5BA2\u623F\u4FE1\u606F\u4FEE\u6539");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel_1.setVisible(false);
                panel_1.removeAll();
                panel_1.add(new roommodi());
                panel_1.setVisible(true);
            }
        });

        GroupLayout groupLayout = new GroupLayout(panel);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(54)
                                                .addComponent(button)
                                                .addGap(51)
                                                .addComponent(button_1)
                                                .addGap(51)
                                                .addComponent(button_2))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(16)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(button)
                                        .addComponent(button_1)
                                        .addComponent(button_2))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                .addContainerGap())
        );
        panel.setLayout(groupLayout);
        this.setVisible(true);
    }


}
