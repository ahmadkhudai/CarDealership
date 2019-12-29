package View;

import HelperClasses.Colors;
import HelperClasses.Fonts;
import HelperComponents.NullLayoutPanel;
import HelperComponents.OpaqueButton;

import javax.swing.*;
import java.awt.*;

public class TopBarPanel extends NullLayoutPanel {

    public OpaqueButton closeButton = new OpaqueButton("X", Color.WHITE, Colors.BOTTOM_BAR, true);

    public TopBarPanel(){
        this(0,0, 1280,37);
    }
    public TopBarPanel(int x, int y, int w, int h) {
        super(x, y, w, h);
        setBackground(Color.white);

        JLabel companyLogoLabel = new JLabel("The Car Dealer INC");
        companyLogoLabel.setBounds(543, 7, 242, 24);
        companyLogoLabel.setFont(Fonts.normalFont(24));
        companyLogoLabel.setForeground(Colors.BOTTOM_BAR);

        closeButton.setBounds(1212, 0, 68,37);
        closeButton.setFont(Fonts.normalFont(20));

        add(companyLogoLabel);
        add(closeButton);
        repaint();

    }
}
