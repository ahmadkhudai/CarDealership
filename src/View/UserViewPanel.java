package View;

import HelperClasses.Colors;
import HelperClasses.Fonts;
import HelperComponents.NullLayoutPanel;
import Model.Customer;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserViewPanel extends NullLayoutPanel {

    Customer customer;
    public UserViewPanel(int x, int y, int w, int h, Customer ust) {
        super(x, y, w, h);
        setBackground(Color.white);
        setBorder(new BorderUIResource.LineBorderUIResource(Colors.PLACE_HOLDER,1));
        customer = ust;
        JLabel idLabel = new JLabel("ID: " + customer.getID());
        idLabel.setBounds(0,43,264, 22);
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setHorizontalTextPosition(JLabel.CENTER);
        idLabel.setFont(Fonts.mediumFont(18));
        idLabel.setForeground(Colors.BOTTOM_BAR);

        JLabel nameLabel = new JLabel(customer.getName());
        nameLabel.setBounds(0,99,264, 22);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setHorizontalTextPosition(JLabel.CENTER);
        nameLabel.setFont(Fonts.mediumFont(18));
        nameLabel.setForeground(Colors.BOTTOM_BAR);



        NullLayoutPanel bottomPanel = new NullLayoutPanel(0, 164, 264, 70);
        bottomPanel.setBackground(Colors.BOTTOM_BAR);
        JLabel viewLabel = new JLabel("VIEW PROFILE");
        viewLabel.setBounds(0, 0, 264, 70);
        viewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        viewLabel.setHorizontalTextPosition(JLabel.CENTER);
        viewLabel.setFont(Fonts.SegoeRegular(18));
        viewLabel.setForeground(Color.white);
        bottomPanel.add(viewLabel);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(getBackground().darker());
                bottomPanel.setBackground(bottomPanel.getBackground().darker());
                idLabel.setForeground(Color.white);
                nameLabel.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.white);
                bottomPanel.setBackground(Colors.BOTTOM_BAR);
                idLabel.setForeground(Colors.BOTTOM_BAR);
                nameLabel.setForeground(Colors.BOTTOM_BAR);
            }
        });


        add(idLabel);
        add(nameLabel);
        add(bottomPanel);
        repaint();


    }

    public Customer getUser() {
        return customer;
    }


}
