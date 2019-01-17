package main.zhangqing.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import Guest.guestmodi;
//import Room.roommodi;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Asum extends JPanel {

	/**
	 * Create the application.
	 */
	public Asum() {
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

		JButton button = new JButton("\u6DFB\u52A0\u7BA1\u7406\u5458\u7528\u6237");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				panel_1.setVisible(false);
				panel_1.removeAll();
				panel_1.add(new add());
				panel_1.setVisible(true);
			}
		});
		
		JButton button_1 = new JButton("\u5220\u9664\u7BA1\u7406\u5458\u7528\u6237");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_1.removeAll();
				panel_1.add(new delete());
				panel_1.setVisible(true);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(panel);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
					.addComponent(button_1)
					.addGap(87))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(groupLayout);
		this.setVisible(true);
	}

	
}
