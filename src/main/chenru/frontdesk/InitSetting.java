package main.chenru.frontdesk;

import javax.swing.*;
import java.awt.*;


/**
 * @program: HotelManagement
 * @description:
 * @author: Mrs.CeYi
 * @create: 2019-01-14 12:46
 **/
public class InitSetting {
    public static String[] DEFAULT_FONT  = new String[]{
            "Table.font"
            ,"TableHeader.font"
            ,"CheckBox.font"
            ,"Tree.font"
            ,"Viewport.font"
            ,"ProgressBar.font"
            ,"RadioButtonMenuItem.font"
            ,"ToolBar.font"
            ,"ColorChooser.font"
            ,"ToggleButton.font"
            ,"Panel.font"
            ,"TextArea.font"
            ,"Menu.font"
            ,"TableHeader.font"
            // ,"TextField.font"
            ,"OptionPane.font"
            ,"MenuBar.font"
            ,"Button.font"
            ,"Label.font"
            ,"PasswordField.font"
            ,"ScrollPane.font"
            ,"MenuItem.font"
            ,"ToolTip.font"
            ,"List.font"
            ,"EditorPane.font"
            ,"Table.font"
            ,"TabbedPane.font"
            ,"RadioButton.font"
            ,"CheckBoxMenuItem.font"
            ,"TextPane.font"
            ,"PopupMenu.font"
            ,"TitledBorder.font"
            ,"ComboBox.font"
    };

    public static void init()
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                // 国人牛逼主题，值得学习
                // 初始化字体
                UIManager.put("RootPane.setupButtonVisible",false);
                for (int i = 0; i < DEFAULT_FONT.length; i++)
                {UIManager.put(DEFAULT_FONT[i],new Font("微软雅黑", Font.PLAIN,14));}
                // 设置主题为BeautyEye
                try {

                    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
