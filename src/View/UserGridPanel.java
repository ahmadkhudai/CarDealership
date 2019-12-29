package View;

import HelperClasses.Colors;
import HelperComponents.NullLayoutPanel;
import Model.Customer;

import java.util.ArrayList;

public class UserGridPanel extends NullLayoutPanel {
    ArrayList<UserViewPanel> uPanels = new ArrayList<>();

    public UserGridPanel(ArrayList<Customer> customers) {
            super(175, 91, 924,489);
            setLayout(null);
            setBackground(Colors.BASE);
            populateGrid(customers, 0);


        }

        public void populateGrid(ArrayList<Customer> customers, int startingIndex){
            this.removeAll();
            uPanels = new ArrayList<>();
            int x = 0; int y = 0; int w = 264; int h = 234;
            int j = 0;
            for(int i = startingIndex; i<(startingIndex+6)&&(i<customers.size()); i++){
                System.out.println("here here: " + i);
                UserViewPanel userViewPanel = new UserViewPanel(x, y, w, h, customers.get(i));
                UserGridPanel.this.add(userViewPanel);
                uPanels.add(userViewPanel);
                if(j<2){
                    //only x will change
                    x = x +264  + 66;
                }else{
                    if(j==2){
                        x = 0;
                        y = 234+20;
                    }else {
                        x = x +264  + 66;
                    }
                }
                j++;
            }
            revalidate();
            repaint();
        }

        public ArrayList<UserViewPanel> getuPanels() {
            return uPanels;
        }
    }



