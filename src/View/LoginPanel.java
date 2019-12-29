package View;

import HelperClasses.Colors;
import HelperClasses.Fonts;
import HelperComponents.NullLayoutPanel;
import HelperComponents.OpaqueButton;
import Model.CarDealership;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import static HelperClasses.Helper.*;

public class LoginPanel extends NullLayoutPanel {

    private CarDealership dealership = CarDealership.loadData();

    public JTextField idField = new JTextField();
    private JLabel idStatus = new JLabel();
    private JLabel nextString = new JLabel();


    public JTextField signUpID = new JTextField();
    public JTextField nameField = new JTextField();
    public JTextField contactField = new JTextField();
    public JPasswordField signUpPasswordField = new JPasswordField();

    public OpaqueButton signUpButton = new OpaqueButton("SIGN UP", Colors.SUCCESS_GREEN, Color.white, true);



    public JPasswordField passField = new JPasswordField();
    ActionListener al = null;



    public LoginPanel(){
        this(350, 90, 580, 471);
    }

    public LoginPanel(int x, int y, int w, int h) {
        super(x, y, w, h);
//        this = new LoginOnePanel();

        setBackground(Color.white);
        setBorder(new BorderUIResource.LineBorderUIResource(Colors.PLACE_HOLDER,1));


        OpaqueButton actionButton = new OpaqueButton("CONTINUE",Colors.DEFAULT_BLUE, Color.white, true);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(273, 120, 40, 36);
        idLabel.setFont(Fonts.mediumFont(31));
        idLabel.setForeground(Colors.BOTTOM_BAR);

        JLabel subtitleLabel = new JLabel("<html>(If you are not already a member, a new account will be created for you )</html>");
        subtitleLabel.setBounds(142, 227, 308, 40);
        subtitleLabel.setFont(Fonts.SegoeSemiBold(13));
        setForeground(Colors.BOTTOM_BAR);


        idField.setBounds(136, 164, 308, 50);
        idField.setBackground(Colors.PLACE_HOLDER);
        idField.setFont(Fonts.normalFont(20));
        idField.setHorizontalAlignment(JTextField.CENTER);
        idField.setBorder(null);

        CaretListener cr = new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                String input = idField.getText();
                if(idField.getText().length()>10){
                    idStatus.setText("ID MUST BE SHORTER THAN 11 LMAO");
//                    idStatus.setForeground(Colors.SUCCESS_GREEN);
                    nextString.setText("");
                    remove(actionButton);
                }else if(input.length()>4){
                    if(dealership.customerExists(input)){
                        idStatus.setText("ID FOUND!");
                        idStatus.setForeground(Colors.SUCCESS_GREEN);
                        nextString.setText("Press Enter to Continue!");
                    }else{
                        idStatus.setText("ID NOT FOUND!");
                        idStatus.setForeground(Colors.WARNING_RED);
                        nextString.setText("Press Enter to create NEW account!");
                    }
                    add(actionButton);
                }else{
                    idStatus.setText("ID MUST BE LONGER THAN 4");
//                    idStatus.setForeground(Colors.SUCCESS_GREEN);
                    nextString.setText("");
                    remove(actionButton);
                }
                repaint();
            }
        };

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER&&(idField.getText().length()>4)&&(idField.getText().length()<11)){
                    String input = idField.getText();
                    idField.removeCaretListener(cr);
                    idField.removeKeyListener(this);
                    if(dealership.customerExists(input)){
                        removeAll();
                        initFinalLoginStage(input);
                    }else{
                        removeAll();
                        initSignUp(input);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };


        idField.addKeyListener(keyListener);
        idField.addCaretListener(cr);


        idStatus.setBounds(136, 274, 308, 35);
        idStatus.setFont(Fonts.SegoeSemiBold(14 ));
        idStatus.setHorizontalTextPosition(JLabel.CENTER);
        idStatus.setHorizontalAlignment(SwingConstants.CENTER);

        nextString.setBounds(136, 299, 308, 25);
        nextString.setFont(Fonts.SegoeSemiBold(14));
        nextString.setHorizontalTextPosition(JLabel.CENTER);
        nextString.setHorizontalAlignment(SwingConstants.CENTER);

        actionButton.setBounds(179, 350, 222, 69);
        actionButton.setFont(Fonts.mediumFont(31));
        actionButton.setHorizontalAlignment(SwingConstants.CENTER);
        al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = idField.getText();
                idField.removeCaretListener(cr);
                idField.removeKeyListener(keyListener);
                removeAll();
                repaint();
                if(dealership.customerExists(input)){

                    initFinalLoginStage(input);
                }else{
                    initSignUp(input);
                }

            }
        };
        actionButton.addActionListener(al);


        add(idStatus);
        add(nextString);
        add(subtitleLabel);
        add(idLabel);
        add(idField);

    }


    private void initSignUp(String input) {
        removeAll();
        repaint();
        boolean allValid[] = {true, false, false, false};


        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(52, 56, 152, 24);
        idLabel.setFont(Fonts.mediumFont(20));
        idLabel.setForeground(Colors.BOTTOM_BAR);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(52, 159, 152, 24);
        nameLabel.setFont(Fonts.mediumFont(20));
        nameLabel.setForeground(Colors.BOTTOM_BAR);

        JLabel contactLabel1 = new JLabel("Contact");
        contactLabel1.setBounds(52 , 255, 152, 24);
        contactLabel1.setFont(Fonts.mediumFont(20));
        contactLabel1.setForeground(Colors.BOTTOM_BAR);

        JLabel contactLabel2 = new JLabel("Info");
        contactLabel2.setBounds(52 , 255+30, 152, 24);
        contactLabel2.setFont(Fonts.mediumFont(20));
        contactLabel2.setForeground(Colors.BOTTOM_BAR);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(52, 365, 152, 24);
        passwordLabel.setFont(Fonts.mediumFont(20));
        passwordLabel.setForeground(Colors.BOTTOM_BAR);



        JLabel labels[] = new JLabel[4];

        int y = 56;
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setBounds(192, y + 5 + 35, 288, 20);
            labels[i].setFont(Fonts.SegoeRegular(14));
            labels[i].setForeground(Colors.WARNING_RED);
            add(labels[i]);
//            repaint();
            y = y + 103;
        }


        signUpID.setText(input);
        signUpID.setBounds(192, 45, 288, 35);
        signUpID.setFont(Fonts.normalFont(18));
        signUpID.setBackground(Colors.PLACE_HOLDER);
        signUpID.setHorizontalAlignment(JTextField.CENTER);
        signUpID.setBorder(null);


        signUpButton.setBounds(179, 350, 222, 69);
        signUpButton.setFont(Fonts.mediumFont(31));
        signUpButton.setHorizontalAlignment(SwingConstants.CENTER);



        nameField.setBounds(192, 148, 288, 35);
        nameField.setFont(Fonts.normalFont(18));
        nameField.setBackground(Colors.PLACE_HOLDER);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setBorder(null);




        contactField.setBounds(192, 251, 288, 35);
        contactField.setFont(Fonts.normalFont(16));
        contactField.setBackground(Colors.PLACE_HOLDER);
        contactField.setHorizontalAlignment(JTextField.CENTER);
        contactField.setBorder(null);



        signUpPasswordField.setBounds(192, 354, 288, 35);
        signUpPasswordField.setFont(Fonts.normalFont(18));
        signUpPasswordField.setBackground(Colors.PLACE_HOLDER);
        signUpPasswordField.setHorizontalAlignment(JTextField.CENTER);
        signUpPasswordField.setBorder(null);

        Rectangle nameLabelOrg = nameLabel.getBounds();
        Rectangle nameFieldOrg = nameField.getBounds();
        Rectangle contactLabel1Org = contactLabel1.getBounds();
        Rectangle contactLabel2Org = contactLabel2.getBounds();
        Rectangle contactFieldOrg = contactField.getBounds();
        Rectangle passwordLabelOrg = passwordLabel.getBounds();
        Rectangle passwordFieldOrg = signUpPasswordField.getBounds();

        class testClass{
            void addActionButton(){
                System.out.printf("action called!");
                boolean valid = true;
                System.out.println(Arrays.toString(allValid));
                for(boolean val: allValid){
                    if (!val) {
                        valid = false;
                        break;
                    }
                }


                if(valid) {
                    int diffVar = 30;


                    nameLabel.setBounds(nameLabel.getX(), labels[0].getY() + 10, nameLabel.getWidth(), nameLabel.getHeight());
                    nameField.setBounds(nameField.getX(), labels[0].getY() + 10, nameField.getWidth(), nameField.getHeight());
                    contactLabel1.setBounds(contactLabel1.getX(), nameField.getY()+nameField.getHeight()+diffVar, contactLabel1.getWidth(), contactLabel1.getHeight());
                    contactLabel2.setBounds(contactLabel2.getX(), nameField.getY()+nameField.getHeight()+diffVar+30, contactLabel2.getWidth(), contactLabel2.getHeight());
                    contactField.setBounds(contactField.getX(), nameField.getY()+nameField.getHeight() + diffVar, contactField.getWidth(), contactField.getHeight());
                    passwordLabel.setBounds(passwordLabel.getX(), contactLabel2.getY() + contactLabel2.getHeight()+diffVar-10, passwordLabel.getWidth(), passwordLabel.getHeight());
                    signUpPasswordField.setBounds(signUpPasswordField.getX(), contactField.getY()+contactField.getHeight()+diffVar, signUpPasswordField.getWidth(), signUpPasswordField.getHeight());


//                    actionButton.setBackground(Colors.SUCCESS_GREEN);
//                    actionButton.setText("SIGN UP");
//                    actionButton.addListenerToButton(Colors.SUCCESS_GREEN, Color.white);
                    add(signUpButton);
                    repaint();
                }else {
                    remove(signUpButton);
                        nameLabel.setBounds(nameLabelOrg);
                        nameField.setBounds(nameFieldOrg);
                        contactLabel1.setBounds(contactLabel1Org);
                        contactLabel2.setBounds(contactLabel2Org);
                        contactField.setBounds(contactFieldOrg);
                        passwordLabel.setBounds(passwordLabelOrg);
                        signUpPasswordField.setBounds(passwordFieldOrg);

                    repaint();
                }
            }
        }



        testClass testObj = new testClass();

        CaretListener cr = new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if(signUpID.getText().length()<=4){
                    labels[0].setText("ID MUST BE LONGER THAN 4");
                    allValid[0] = false;
                } else if(signUpID.getText().length()>10){
                    labels[0].setText("ID MUST BE SHORTER THAN 11 LMAO");
                    allValid[0] = false;
                } else if(dealership.customerExists(signUpID.getText())){
                    labels[0].setText("ID ALREADY EXISTS!");
                    allValid[0] = false;
                }else {
                    labels[0].setText("");
                    allValid[0] = true;
                }

                testObj.addActionButton();

            }
        };
        signUpID.addCaretListener(cr);

        nameField.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if(!isValidName(nameField.getText())){
                    labels[1].setText("Name can ONLY contain alphabets");
                    allValid[1] = false;
                }else {
                    labels[1].setText("");
                    allValid[1] = true;
                }

                testObj.addActionButton();

            }
        });

        contactField.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if(!(isValidEmail(contactField.getText())||(isValidPhone(contactField.getText())))){
                    labels[2].setText("<html>Please enter a valid Phone or Email</html>");
                    allValid[2] =false;
                }else if(dealership.contactExists(contactField.getText())) {
                    labels[2].setText("<html>Phone/Email already exists</html>");
                    allValid[2] =false;
                }else {
                    labels[2].setText("");
                    allValid[2] = true;
                }
                testObj.addActionButton();

            }
        });

        signUpPasswordField.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if(signUpPasswordField.getPassword().length<=4){
                    labels[3].setText("<html> Password should be longer than 4 digits</html>");
                    allValid[3] =false;
                }else{
                    labels[3].setText("");
                    allValid[3] =true;
                }

                testObj.addActionButton();

            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initFinalLoginStage(signUpID.getText());
            }
        });

        add(idLabel);
        add(signUpID);
        add(nameLabel);
        add(nameField);
        add(contactLabel1);
        add(contactLabel2);
        add(contactField);
        add(passwordLabel);
        add(signUpPasswordField);


        repaint();



    }



    public void AddWarningLabel(){

        statusLabel.setText("Wrong Input!");
        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("");
            }
        });
        timer.setRepeats(false);
        timer.start();

    }

    //FINAL STAGE COMPONENTS

    public OpaqueButton finalLoginButton = new OpaqueButton("LOGIN", Colors.SUCCESS_GREEN, Color.white, true);
    public JLabel statusLabel = new JLabel();



    public void initFinalLoginStage(String input){
        removeAll();
        repaint();
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(273, 60, 40, 36);
        idLabel.setFont(Fonts.mediumFont(31));
        idLabel.setForeground(Colors.BOTTOM_BAR);


        idField.setText(input);
        idField.setBounds(136, 102, 308, 50);


        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(193, 199, 200, 40);
        passwordLabel.setFont(Fonts.mediumFont(31));
        passwordLabel.setForeground(Colors.BOTTOM_BAR);

        passField.setBounds(136, 244, 308, 50);
        passField.setBackground(Colors.PLACE_HOLDER);
        passField.setFont(Fonts.normalFont(20));
        passField.setHorizontalAlignment(JTextField.CENTER);
        passField.setBorder(null);


        finalLoginButton.setBounds(179, 350, 222, 69);
        finalLoginButton.setFont(Fonts.mediumFont(31));
        finalLoginButton.setHorizontalAlignment(SwingConstants.CENTER);

        statusLabel.setBounds(226, 433, 126, 16);
        statusLabel.setFont(Fonts.boldFont(15));
        statusLabel.setForeground(Colors.WARNING_RED);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(idLabel);
        add(idField);
        add(passwordLabel);
        add(passField);
        add(finalLoginButton);
        add(statusLabel);

        repaint();
    }

    public void reset(){
        idField.setText("");
        passField.setText("");
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

