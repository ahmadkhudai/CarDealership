package View;

import HelperClasses.Fonts;
import HelperComponents.NullLayoutPanel;

import java.awt.*;

public class BottomPanel extends NullLayoutPanel {

    public BottomPanel(){
        this(0,630, 1280,20);
    }
    public BottomPanel(int x, int y, int w, int h) {
        super(x, y, w, h);
        setBackground(new Color(64,64,64));

        Label copyRightLabel = new Label("Copyright"+'\u00a9'+ " The Car Dealer, All Rights Reserved");
        copyRightLabel.setBounds(9,3,300,14);
        copyRightLabel.setFont(Fonts.normalFont(10));
        copyRightLabel.setForeground(Color.WHITE);

        add(copyRightLabel);
        repaint();
    }
}
