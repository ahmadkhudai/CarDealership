package View;

import HelperClasses.Colors;
import HelperComponents.NullLayoutPanel;
import Model.Vehicle;

import java.util.ArrayList;

    public class ViewGridPanel extends NullLayoutPanel {

    ArrayList<VehicleViewPanel> vPanels = new ArrayList<>();

    public ViewGridPanel(ArrayList<Vehicle> vehicles){
        super(233, 138, 900,424);
        setLayout(null);
        setBackground(Colors.BASE);


        populateGrid(vehicles, 0);


    }

    public ArrayList<VehicleViewPanel> populateGrid(ArrayList<Vehicle> vehicles, int startingIndex){
        this.removeAll();
        vPanels = new ArrayList<>();
        int x = 0; int y = 0; int w = 201; int h = 201;
        int j = 0;
        for(int i = startingIndex; i<(startingIndex+8)&&(i<vehicles.size()); i++){
            System.out.println("here here: " + i);
            VehicleViewPanel vehicleViewPanel = new VehicleViewPanel(x, y, w, h, vehicles.get(i));
            ViewGridPanel.this.add(vehicleViewPanel);
            vPanels.add(vehicleViewPanel);
            if(j<3){
                //only x will change
                x = x +202 + 27;
            }else{
                if(j==3){
                    x = 0;
                    y = 202+20;
                }else {
                    x = x + 202 + 27;
                }
            }
            j++;
        }
        revalidate();
        repaint();
        return vPanels;
    }

        public ArrayList<VehicleViewPanel> getvPanels() {
            return vPanels;
        }
    }
