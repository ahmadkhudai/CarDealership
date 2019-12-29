package View;

import HelperClasses.Colors;
import HelperClasses.Fonts;
import HelperClasses.Helper;
import HelperComponents.NullLayoutPanel;
import Model.Vehicle;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class VehicleViewPanel extends JPanel {
    Vehicle vehicle;

    NullLayoutPanel topPanel = new NullLayoutPanel(0,0, 200,173);
    NullLayoutPanel bottomPanel = new NullLayoutPanel(0, 173, 200, 26);

    private JPanel darkPanel = new JPanel();


//    VehicleViewPanel(int x, int y, int w, int h){
//
//    }

    public VehicleViewPanel(int x, int y, int w, int h, Vehicle eh) {
        setLayout(null);
        setBounds(x, y, w, h);
        setBackground(Colors.PLACE_HOLDER);
        setBorder(new BorderUIResource.LineBorderUIResource(Colors.PLACE_HOLDER,1));
        this.vehicle = eh;


        JLabel manufacturerLabel = new JLabel((vehicle.getManufacturer()));
        manufacturerLabel.setBounds(6, 38, 150, 25);
        manufacturerLabel.setFont(Fonts.mediumFont(18));
        manufacturerLabel.setForeground(Colors.BOTTOM_BAR);

        JLabel nameLabel = new JLabel(vehicle.getVehicleName());
        nameLabel.setBounds(6, 62, 188, 32);
        nameLabel.setFont(Fonts.mediumFont(26));
        nameLabel.setForeground(Colors.BOTTOM_BAR);

        NullLayoutPanel conditionPanel = new NullLayoutPanel(0, 111, 100, 34);
        conditionPanel.setBackground((vehicle.isNew()?Colors.SUCCESS_GREEN:Colors.DEFAULT_BLUE));
        JLabel conditionLabel = new JLabel((vehicle.isNew()?"NEW":"USED"));
        conditionLabel.setBounds(0, 0, 100, 34);
        conditionLabel.setHorizontalAlignment(JLabel.CENTER);
        conditionLabel.setFont(Fonts.normalFont(20));
        conditionLabel.setForeground(Color.white);

        conditionPanel.add(conditionLabel);
        topPanel.add(manufacturerLabel);
        topPanel.add(nameLabel);
        topPanel.add(conditionPanel);
        topPanel.repaint();

        JLabel priceLabel = new JLabel(Helper.getFormattedNumber(vehicle.getPrice()));
        priceLabel.setBounds(12, 6, 80, 16);
        priceLabel.setFont(Fonts.mediumFont(14));
        priceLabel.setForeground(Colors.BOTTOM_BAR);

        JLabel yearLabel = new JLabel(vehicle.getYearMade()+"");
        yearLabel.setBounds(157,6, 34, 16);
        yearLabel.setForeground(Colors.BOTTOM_BAR);
        yearLabel.setFont(Fonts.normalFont(12));
        yearLabel.setHorizontalAlignment(SwingConstants.LEFT);

        topPanel.setBackground(Colors.PLACE_HOLDER);


        bottomPanel.add(priceLabel);
        bottomPanel.add(yearLabel);

        bottomPanel.setBackground(Color.white);
        bottomPanel.repaint();

        topPanel.repaint();
        add(topPanel);
        add(bottomPanel);
        repaint();

    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void darken() {
        topPanel.setBackground(Color.white);
//        bottomPanel.setBorder(new BorderUIResource.LineBorderUIResource(Colors.PLACE_HOLDER,1));
        setBorder(null);
        repaint();
    }

    public void reset(){
        topPanel.setBackground(Colors.PLACE_HOLDER);
        setBorder(new BorderUIResource.LineBorderUIResource(Colors.PLACE_HOLDER.darker(),1));
        repaint();
    }
}
