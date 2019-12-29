package View;

import HelperClasses.Colors;
import HelperClasses.Fonts;
import HelperClasses.Helper;
import HelperComponents.NullLayoutPanel;
import HelperComponents.OpaqueButton;
import Model.Vehicle;

import javax.swing.*;
import java.awt.*;

public class VehicleDetailsPanel extends NullLayoutPanel {
    Vehicle vehicle;
    public OpaqueButton buyButton;
    //JLabel label = new JLabel("<html>"+test+"</html>");
    public VehicleDetailsPanel(Vehicle eh, int mode){
        this(0,0, 1280, 650, eh, mode);
    }

    public VehicleDetailsPanel(int x, int y, int w, int h, Vehicle eh, int mode) {
        super(x, y, w, h);
        vehicle = eh;

        JLabel vehicleYearLabel = new JLabel(""+vehicle.getYearMade());
        vehicleYearLabel.setBounds(175, 149, 150, 50);
        vehicleYearLabel.setFont(Fonts.boldFont(28));
        vehicleYearLabel.setForeground(Colors.BOTTOM_BAR);

        JLabel vehicleNameLabel = new JLabel(vehicle.getManufacturer() + " " + vehicle.getVehicleName());
        vehicleNameLabel.setBounds(175, 194, 500, 50);
        vehicleNameLabel.setFont(Fonts.boldFont(28));
        vehicleNameLabel.setForeground(Colors.BOTTOM_BAR);

        String vehicleDetails = "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Minus debitis tempore qui harum libero deserunt et, ab,\n" +
                "ratione voluptatibus provident accusantium saepe officia, enim impedit labore asperiores voluptas.";
        JLabel vehicleDetailsLabel = new JLabel("<html>"+vehicleDetails+"</html>");
        vehicleDetailsLabel.setBounds(175, 249, 532, 166);
        vehicleDetailsLabel.setFont(Fonts.normalFont(20));
        vehicleDetailsLabel.setForeground(Colors.BOTTOM_BAR);

        JLabel priceLabel = new JLabel("Price: " + Helper.getFormattedNumber(vehicle.getPrice()));
        priceLabel.setBounds(175, 441, 400, 38);
        priceLabel.setForeground(Colors.BOTTOM_BAR);
        priceLabel.setFont(Fonts.mediumFont(36));

        JLabel transmissionLabel = new JLabel("TRANSMISSION");
        transmissionLabel.setBounds(881, 171, 300, 30);
        transmissionLabel.setForeground(Colors.BOTTOM_BAR);
        transmissionLabel.setFont(Fonts.mediumFont(24));

        JLabel transmissionInfoLabel = new JLabel((vehicle.isAuto()?"AUTOMATIC":"MANUAL"));
        transmissionInfoLabel.setBounds(881, 203, 300, 25);
        transmissionInfoLabel.setForeground(Colors.BOTTOM_BAR);
        transmissionInfoLabel.setFont(Fonts.mediumFont(18));



        JLabel colorLabel = new JLabel("COLOR");
        colorLabel.setBounds(881, 244, 300, 30);
        colorLabel.setForeground(Colors.BOTTOM_BAR);
        colorLabel.setFont(Fonts.mediumFont(24));

        JLabel colorInfoLabel = new JLabel(vehicle.getColor().toUpperCase());
        colorInfoLabel.setBounds(881, 277, 300, 25);
        colorInfoLabel.setForeground(Colors.BOTTOM_BAR);
        colorInfoLabel.setFont(Fonts.mediumFont(18));


        JLabel conditionLabel = new JLabel("CONDITION");
        conditionLabel.setBounds(881, 317, 300, 30);
        conditionLabel.setForeground(Colors.BOTTOM_BAR);
        conditionLabel.setFont(Fonts.mediumFont(24));

        JLabel conditionInfoLabel = new JLabel(vehicle.isNew()?"NEW":"USED");
        conditionInfoLabel.setBounds(881, 350, 300, 25);
        conditionInfoLabel.setForeground(Colors.BOTTOM_BAR);
        conditionInfoLabel.setFont(Fonts.mediumFont(18));

        switch (mode){
            //user
            case 1:
                buyButton = new OpaqueButton("BUY!", Colors.SUCCESS_GREEN, Color.white, true);
                buyButton.setFont(Fonts.mediumFont(31));
                break;
            //admin
            case 2:
                buyButton = new OpaqueButton("REMOVE", Colors.WARNING_RED, Color.white, true);
                buyButton.setFont(Fonts.boldFont(28));
                break;

        }

        buyButton.setBounds(881, 411, 222, 69);



        add(vehicleYearLabel);
        add(vehicleNameLabel);
        add(vehicleDetailsLabel);
        add(priceLabel);
        add(transmissionLabel);
        add(transmissionInfoLabel);
        add(colorLabel);
        add(colorInfoLabel);
        add(conditionLabel);
        add(conditionInfoLabel);
        add(buyButton);

        repaint();
    }

    public Vehicle boughtVehicle(){
        return vehicle;
    }
}
