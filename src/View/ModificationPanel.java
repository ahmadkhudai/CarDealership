package View;

import HelperClasses.Colors;
import HelperClasses.Fonts;
import HelperClasses.Helper;
import HelperComponents.NullLayoutPanel;
import HelperComponents.OpaqueButton;
import Model.Customer;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ModificationPanel extends NullLayoutPanel {

    public OpaqueButton submit;
    public OpaqueButton cancel;
    public JTextField inputField = new JTextField();
    JLabel subtitleLabel = new JLabel();
    public ModificationPanel(int mode, Customer user){
        this(0, 0, 1280, 650, mode, user);
    }
    public ModificationPanel(int x, int y, int w, int h, int mode, Customer user) {
        super(x, y, w, h);


        JLabel mainLabel = new JLabel();
        mainLabel.setBounds(691, 178, 250, 34);
        mainLabel.setForeground(Colors.BOTTOM_BAR);
        mainLabel.setFont(Fonts.boldFont(28));


        subtitleLabel.setBounds(691, 217, 400, 22);
        subtitleLabel.setForeground(Colors.BOTTOM_BAR);
        subtitleLabel.setFont(Fonts.normalFont(16));


        inputField.setBounds(691, 244, 468, 64);
        inputField.setBackground(Color.white);
        inputField.setFont(Fonts.normalFont(20));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setBorder(null);

        submit = new OpaqueButton("SUBMIT", Colors.BORDERS, Color.white, true);
        submit.setBounds(691, 349, 205, 62);
        submit.setFont(Fonts.mediumFont(24));
        cancel = new OpaqueButton("CANCEL", Colors.DEFAULT_BLUE, Color.white, true);
        cancel.setBounds(954, 349, 205, 62);
        cancel.setFont(Fonts.mediumFont(24));

        switch (mode){
            case 2:
                mainLabel.setText("EDIT CONTACT INFO:");
                subtitleLabel.setText("(Previously: " + user.getContactInfo() + ")");
                break;
            case 1:
                mainLabel.setText("EDIT NAME:");
                subtitleLabel.setText("(Previously: " + user.getName() + ")");
                break;
            case 3:
                mainLabel.setText("ENTER AMOUNT");
                subtitleLabel.setText("Must be less than 1000,000");
                inputField.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        if(Helper.isValidAmount(inputField.getText())){
                            int number = Integer.parseInt(inputField.getText().replaceAll(",", ""));
                            String num = Helper.getFormattedNumberWithoutDollarSignYouIdiot(number);
                            inputField.setText(num);
                        }
                    }
                });
        }



        add(mainLabel);
        add(subtitleLabel);
        add(inputField);
        add(submit);
        add(cancel);
        repaint();
    }

    public void wrongInput(){
        String resetString = subtitleLabel.getText();
        subtitleLabel.setText(resetString + " INVALID INPUT");
        subtitleLabel.setForeground(Colors.WARNING_RED);
        subtitleLabel.setFont(Fonts.boldFont(16));
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subtitleLabel.setText(resetString);
                subtitleLabel.setForeground(Colors.BOTTOM_BAR);
                subtitleLabel.setFont(Fonts.normalFont(16));
            }
        });
        timer.setRepeats(false);
        timer.start();

    }
}
