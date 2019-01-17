package main.xinyan.Order;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DateItemAdapter implements ItemListener {

    private modiinfo adaptee;

    DateItemAdapter(modiinfo adaptee) {
        this.adaptee = adaptee;
    }
    public void itemStateChanged(ItemEvent e) {
        adaptee.cboMonth_itemStateChanged(e);
    }
}