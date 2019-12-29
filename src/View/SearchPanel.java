package View;

import HelperClasses.Colors;
import HelperClasses.Fonts;
import HelperComponents.NullLayoutPanel;
import HelperComponents.OpaqueButton;
import Model.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchPanel extends NullLayoutPanel {

    public ArrayList<Vehicle> cars = null;
    public JTextField searchField = new JTextField();
    public OpaqueButton goButton = new OpaqueButton("GO!", Colors.BORDERS, Color.white, true);
    public JLabel foundVehiclesNumber;


    public SearchPanel(ArrayList<Vehicle> cars){
        this(107, 37, 1173, 593, cars);
    }

    public SearchPanel(int x, int y, int w, int h, ArrayList<Vehicle> cars) {
        super(x, y, w, h);
        setBackground(Color.white);
        this.cars = cars;

        JLabel searchLabel = new JLabel("SEARCH");
        JLabel foundLabel = new JLabel("FOUND");
        JLabel vehiclesLabel = new JLabel("VEHICLES");
        foundVehiclesNumber = new JLabel();
        foundVehiclesNumber.setText(""+cars.size());

        searchLabel.setBounds(326, 142, 240, 50);
        searchLabel.setFont(Fonts.mediumFont(50));
        searchLabel.setForeground(Colors.BORDERS);

        searchField.setBounds(182, 239, 508, 68);
        searchField.setBackground(Colors.PLACE_HOLDER);
        searchField.setFont(Fonts.normalFont(20));
        searchField.setHorizontalAlignment(JTextField.CENTER);
        searchField.setBorder(null);

        goButton.setBounds(182, 355, 508, 79);
        goButton.setFont(Fonts.boldItalicFont(45));


        foundLabel.setBounds(903, 98, 240, 50);
        foundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        foundLabel.setFont(Fonts.boldFont(36));
        foundLabel.setForeground(Colors.BOTTOM_BAR);

        vehiclesLabel.setBounds(917, 152, 240, 50);
        vehiclesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        vehiclesLabel.setFont(Fonts.boldFont(36));
        vehiclesLabel.setForeground(Colors.BOTTOM_BAR);

        foundVehiclesNumber.setBounds(974, 409, 97, 75);
        foundVehiclesNumber.setHorizontalAlignment(SwingConstants.CENTER);
        foundVehiclesNumber.setFont(Fonts.mediumItalicFont(60));
        foundVehiclesNumber.setBackground(Colors.BOTTOM_BAR);

//        NullLayoutPanel sideInfoPanel = new NullLayoutPanel(871, 0, 302, 593);
//        sideInfoPanel.setBackground(Color.white);

        add(searchLabel);
        add(searchField);
        add(goButton);
        add(foundLabel);
        add(vehiclesLabel);
        add(foundVehiclesNumber);
        repaint();

    }

    public void refreshCount(ArrayList<Vehicle> vehicles) {
        foundVehiclesNumber.setText(""+vehicles.size());
        cars = vehicles;
        repaint();
    }

    public ArrayList<Vehicle> getCars(){
        return this.cars;
    }
}
