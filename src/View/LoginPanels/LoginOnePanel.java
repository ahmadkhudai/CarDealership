package View.LoginPanels;

import HelperClasses.Colors;
import HelperClasses.Fonts;
import HelperComponents.OpaqueButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginOnePanel extends BaseLoginPanel {
    public JTextField idField = new JTextField();
    public OpaqueButton actionButton = new OpaqueButton("CONTINUE",Colors.DEFAULT_BLUE, Color.white, true);
    public JLabel idStatus = new JLabel();
    public JLabel nextString = new JLabel();



    public LoginOnePanel(){

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(273, 120, 40, 36);
        idLabel.setFont(Fonts.mediumFont(31));
        idLabel.setForeground(Colors.BOTTOM_BAR);

        idField.setBounds(136, 164, 308, 50);
        idField.setBackground(Colors.PLACE_HOLDER);
        idField.setFont(Fonts.normalFont(20));
        idField.setHorizontalAlignment(JTextField.CENTER);
        idField.setBorder(null);

        idStatus.setBounds(244, 274, 308, 35);
        idStatus.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25 ));
        idStatus.setBounds(194, 299, 308, 25);
        idStatus.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14 ));


        actionButton.setBounds(179, 350, 222, 69);
        actionButton.setFont(Fonts.mediumFont(31));
        actionButton.setHorizontalAlignment(SwingConstants.CENTER);


        add(idLabel);
        add(idField);
        add(actionButton);
    }

//    public voi

    public void createWarning(){
        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(226, 433, 126, 16);
        statusLabel.setFont(Fonts.boldFont(15));
        statusLabel.setForeground(Colors.WARNING_RED);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel);
        statusLabel.setText("Wrong Input! ID must be longer than 4 characters.");
        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("");
            }
        });
        timer.setRepeats(false);
        timer.start();

    }
}
