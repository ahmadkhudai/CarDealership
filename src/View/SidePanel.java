package View;

import HelperClasses.Colors;
import HelperClasses.Fonts;
import HelperComponents.NullLayoutPanel;
import HelperComponents.OpaqueButton;

import java.awt.*;
import java.awt.geom.Line2D;

public class SidePanel extends NullLayoutPanel {

    public OpaqueButton browseButton = new OpaqueButton("Browse", Color.WHITE,Colors.BOTTOM_BAR, true);
    public OpaqueButton searchButton = new OpaqueButton("Search", Color.WHITE,Colors.BOTTOM_BAR, true);
    public OpaqueButton profileButton = new OpaqueButton("Profile", Color.WHITE,Colors.BOTTOM_BAR, true);
    public OpaqueButton Logout = new OpaqueButton("Logout", Color.WHITE, Colors.BOTTOM_BAR,true);
    OpaqueButton nButton = new OpaqueButton("Test", Colors.BORDERS, Colors.BOTTOM_BAR,true);

    public SidePanel(){
        this(Color.white);
    }
    public SidePanel(Color color){
        super(0,37,106 , 593);
        setBackground(color);

        browseButton.setBounds(0,118, 106,45);
        searchButton.setBounds(0,162, 106,45);
        profileButton.setBounds(0,414, 106,45);
        nButton.setBounds(0,(414+50), 106,45);
        Logout.setBounds(0,530,106,45);

        browseButton.setFont(Fonts.mediumFont());
        searchButton.setFont(Fonts.mediumFont());
        profileButton.setFont(Fonts.mediumFont());
        nButton.setFont(Fonts.mediumFont());
        Logout.setFont(Fonts.mediumFont());


        add(browseButton);
        add(searchButton);
        add(profileButton);
//        add(nButton);
        add(Logout);
        repaint();
    }


    //Draw lines on the panel
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(112,112,112,50));
//        int y = 496; int x = 0;
        Line2D lin2 = new Line2D.Float(0 , 396, 106, 396);
        Line2D lin = new Line2D.Float(0 , 510, 106, 510);
        g2.setStroke(new BasicStroke(1));
        g2.draw(lin);
        g2.draw(lin2);
    }
}
