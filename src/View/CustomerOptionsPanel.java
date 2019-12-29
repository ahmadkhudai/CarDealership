package View;

import HelperClasses.Fonts;
import HelperComponents.NullLayoutPanel;
import HelperComponents.OpaqueButton;

import javax.swing.*;
import java.awt.*;

import static HelperClasses.Colors.*;

public class CustomerOptionsPanel extends NullLayoutPanel {

    public OpaqueButton addBalanceButton = new OpaqueButton("REQUEST BALANCE", Color.white, BOTTOM_BAR, true);
    public OpaqueButton editNameButton = new OpaqueButton("EDIT NAME", Color.white, BOTTOM_BAR, true);
    public OpaqueButton editInfoButton = new OpaqueButton("EDIT INFO", Color.white, BOTTOM_BAR, true);
    public OpaqueButton viewHistoryButton = new OpaqueButton("VIEW HISTORY", Color.white, BOTTOM_BAR, true);
    public OpaqueButton closeAccount = new OpaqueButton("CLOSE ACCOUNT", WARNING_RED, Color.white, true);



    public CustomerOptionsPanel(){
        this(602, 37, 678, 593);
    }
    public CustomerOptionsPanel(int x, int y, int w, int h) {
        super(x, y, w, h);
        setBackground(PLACE_HOLDER);


        int yy = 129;
        addBalanceButton.setBounds(226,yy , 225, 49);
        addBalanceButton.setFont(Fonts.boldFont(16));

        yy +=72;
        editNameButton.setBounds(226, yy, 225, 49);
        editNameButton.setFont(Fonts.boldFont(20));

        yy +=72;
        editInfoButton.setBounds(226, yy, 225, 49);
        editInfoButton.setFont(Fonts.boldFont(20));

        yy +=72;
        viewHistoryButton.setBounds(226, yy, 225, 49);
        viewHistoryButton.setFont(Fonts.boldFont(20));

        yy +=72;
        closeAccount.setBounds(226, yy, 225, 49);
        closeAccount.setFont(Fonts.boldFont(18));


        JLabel optionsLabel  = new JLabel("OPTIONS");
        optionsLabel.setBounds(215, 57, 400, 52);
        optionsLabel.setFont(Fonts.normalFont(50));
        optionsLabel.setForeground(BOTTOM_BAR);


        add(optionsLabel);
        add(addBalanceButton);
        add(editNameButton);
        add(editInfoButton);
        add(viewHistoryButton);
        add(closeAccount);
        repaint( );

    }
}
