package test;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.theme.SubstanceEbonyTheme;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;

import javax.swing.*;

/**
 * @program: HotelManagement
 * @description:
 * @author: Mrs.CeYi
 * @create: 2019-01-13 16:07
 **/
public class JTattooTest {
    public static void main(String[] args) {
        try {
            // 设置外观
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
            JFrame.setDefaultLookAndFeelDecorated(true);
            // 设置主题
            SubstanceLookAndFeel.setCurrentTheme(new SubstanceEbonyTheme());
            // 设置按钮外观
            SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
            // 设置水印
            SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBinaryWatermark());
            // 设置边框
            SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
            // 设置渐变渲染
            SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
            // 设置标题
            SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
