package HelperComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OpaqueButton extends JButton {


    Color backgroundColor;
    Color foreGroundColor;
    MouseListener al = null;

    public OpaqueButton(String text, Color backgroundColor, Color foreGroundColor, boolean addListener){

        super(text);

        setOpaque(true);
        setContentAreaFilled(true);
        setBorderPainted(false);
        setDefaultCapable(true);
        setFocusPainted(false);

        this.backgroundColor = backgroundColor;
        this.foreGroundColor = foreGroundColor;
        setBackground(backgroundColor);
        setForeground(foreGroundColor);



        if(addListener) {
           addListenerToButton();
        }

    }

    public void addListenerToButton() {
        al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OpaqueButton.this.setBackground(backgroundColor.darker());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                OpaqueButton.this.setBackground(backgroundColor.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                OpaqueButton.this.setBackground(backgroundColor);
                if(isLighter(backgroundColor)) {
                    OpaqueButton.this.setForeground(foreGroundColor);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(isLighter(backgroundColor)) {
                    OpaqueButton.this.setBackground(backgroundColor.darker());
                    OpaqueButton.this.setForeground(Color.white);
                }else{
                    OpaqueButton.this.setBackground(backgroundColor.brighter());
                }
            }



            @Override
            public void mouseExited(MouseEvent e) {
                OpaqueButton.this.setBackground(backgroundColor);
                if(isLighter(backgroundColor)) {
                    OpaqueButton.this.setForeground(foreGroundColor);
                }
            }
        };

        this.addMouseListener(al);
    }

    private boolean isLighter(Color color) {
        return color.getRed() + color.getBlue() + color.getGreen() > 300;
    }

    public void removeListener() {
        if(al!=null) {
            this.removeMouseListener(al);
            al = null;
        }

    }


    public void addListenerToButton(Color backgroundColor, Color foreGroundColor) {
        this.backgroundColor = backgroundColor;
        this.foreGroundColor =foreGroundColor;
        al = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OpaqueButton.this.setBackground(backgroundColor.darker());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                OpaqueButton.this.setBackground(backgroundColor.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                OpaqueButton.this.setBackground(backgroundColor);
                if(isLighter(backgroundColor)) {
                    OpaqueButton.this.setForeground(foreGroundColor);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(isLighter(backgroundColor)) {
                    OpaqueButton.this.setBackground(backgroundColor.darker());
                    OpaqueButton.this.setForeground(Color.white);
                }else{
                    OpaqueButton.this.setBackground(backgroundColor.brighter());
                }
            }



            @Override
            public void mouseExited(MouseEvent e) {
                OpaqueButton.this.setBackground(backgroundColor);
                if(isLighter(backgroundColor)) {
                    OpaqueButton.this.setForeground(foreGroundColor);
                }
            }
        };

        this.addMouseListener(al);
    }

    public void removeListener(Color borders, Color white) {
        backgroundColor = borders;
        foreGroundColor = white;
        if(al!=null) {
            this.removeMouseListener(al);
            al = null;
        }
    }
}


