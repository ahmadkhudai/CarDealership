package View.LoginPanels;

import HelperClasses.Colors;
import HelperComponents.NullLayoutPanel;

import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class BaseLoginPanel extends NullLayoutPanel {



    public BaseLoginPanel(){
        this(350, 90, 580, 471);
    }
    public BaseLoginPanel(int x, int y, int w, int h) {
        super(x, y, w, h);
        setBackground(Color.white);
        setBorder(new BorderUIResource.LineBorderUIResource(Colors.PLACE_HOLDER,1));


    }

}
