package HelperComponents;

import javax.swing.*;

public class NullLayoutPanel extends JPanel {
    public NullLayoutPanel(int x, int y, int w, int h){
        setLayout(null);
        setBounds(x,y,w,h);
    }
}
