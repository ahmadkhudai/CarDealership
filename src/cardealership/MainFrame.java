package cardealership;

import HelperClasses.Colors;
import HelperClasses.Fonts;
import HelperClasses.Helper;
import HelperComponents.NullLayoutPanel;
import HelperComponents.OpaqueButton;
import View.*;
import View.LoginPanel;
import Model.CarDealership;
import Model.Customer;
import Model.Vehicle;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    //COMPONENTS
    JLabel titleLabel = new JLabel();
    JLabel statusLabel = new JLabel();

    //DATA
    CarDealership dealership = CarDealership.loadData();
    ArrayList<Vehicle> cars = dealership.getVehicles();
    ArrayList<Customer> customers;
    LoginPanel loginPanel = new LoginPanel();
    Customer user;


    MainFrame(){
        setLayout(null);
        setSize(1280,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.dispose();
        setUndecorated(true);
        getContentPane().setBackground(Colors.BASE);


        titleLabel.setBounds(359, 20, 860, 31);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(Fonts.mediumFont(30));
        titleLabel.setForeground(Colors.BOTTOM_BAR);

        statusLabel.setBounds(632, 578, 860, 35);
        statusLabel.setFont(Fonts.SegoeSemiBold(20));


        MoveMouseListener mml = new MoveMouseListener(this);
        addMouseListener(mml);
        addMouseMotionListener(mml);

        addTopBar();
        add(new BottomPanel());
        addLoginPanel();



        repaint();
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();

    }

    private void addTopBar() {
        TopBarPanel topBarPanel = new TopBarPanel();
        topBarPanel.closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: LOGOUT BEFORE CLOSING!!

                dealership.saveData();
                MainFrame.this. dispose();
            }
        });

        add(topBarPanel);
    }

    private void addLoginPanel() {


//        loginPanel.idField.setText(input);


        loginPanel = new LoginPanel();
        loginPanel.signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dealership.createCustomer(loginPanel.nameField.getText(), loginPanel.signUpID.getText(), loginPanel.contactField.getText(),
                        String.valueOf(loginPanel.signUpPasswordField.getPassword()), 100000);
                dealership.saveData();
            }
        });


        loginPanel.passField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    Customer customer = dealership.loginUser(loginPanel.idField.getText(), String.valueOf(loginPanel.passField.getPassword()));
                    if(customer!=null) {
                        user = customer;
                        if(dealership.isAdmin(user)){
                            customers = dealership.getCustomers();
                        }
                        login(customer);
                    }else{
                        loginPanel.AddWarningLabel();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        loginPanel.finalLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(dealership.customerExists(loginPanel.idField.getText())){
                    System.out.println("exists. I am just being a dick");
                }
                Customer customer = dealership.loginUser(loginPanel.idField.getText(), String.valueOf(loginPanel.passField.getPassword()));
                if(customer!=null) {
                    user = customer;
                    if(dealership.isAdmin(user)){
                        customers = dealership.getCustomers();
                    }
                    login(customer);
                }else{
                    loginPanel.AddWarningLabel();
                }
            }
        });
        add(loginPanel);
    }

    private void login(Customer customer) {
        loginPanel.reset();
        this.getContentPane().removeAll();
        this.user = customer;
        addBaseComponents();
        addViewGrid();
        getContentPane().setBackground(Colors.BASE);
        repaint();
    }

    private ViewGridPanel addViewGrid() {
//
        ViewGridPanel viewGridPanel = new ViewGridPanel(cars);
        populateViewGrid(viewGridPanel, cars, false);
        this.add(viewGridPanel);
        titleLabel.setText("BROWSE");
        addViewLabels();
        return viewGridPanel;
    }

    private ViewGridPanel addViewGrid(ArrayList<Vehicle> cars, String title) {
        //title and status will be both set by the caller
        ViewGridPanel viewGridPanel = new ViewGridPanel(cars);
        switch (title){
            case("HISTORY"):
                populateViewGrid(viewGridPanel, cars, true);
                break;
            case("SEARCH RESULTS"):
                populateViewGrid(viewGridPanel, cars, false);
                break;
        }

        this.add(viewGridPanel);
        titleLabel.setText(title);
        addViewLabels();
        return viewGridPanel;
    }

    private void addViewLabels() {
        NullLayoutPanel titlePanel = new NullLayoutPanel(233, 51, 890, 69);
        titlePanel.setBackground(Color.white);
        updateStatus();

        titlePanel.add(titleLabel);
        add(titlePanel);
        repaint();

    }

    private void updateStatus() {
        if(!statusLabel.getText().equals("")){
            add(statusLabel);
            Timer timer = new Timer(1500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(statusLabel);
                    statusLabel.setText("");
                    repaint();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void addBaseComponents() {
        addTopBar();
        addSideMenuPanel();
        add(new BottomPanel());
    }

    private void addSideMenuPanel() {
        SidePanel sidePanel = new SidePanel();

        sidePanel.browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.getContentPane().removeAll();
                addBaseComponents();
                addViewGrid();
                repaint();
            }
        });

        sidePanel.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.getContentPane().removeAll();
                addBaseComponents();
                addSearchPanel();
                repaint();
            }
        });

        if(!dealership.isAdmin(user)) {
            sidePanel.profileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showUserProfile(user);
                }
            });
        }else{
            sidePanel.profileButton.setText("Users");
            sidePanel.profileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainFrame.this.getContentPane().removeAll();
                    addBaseComponents();
                    addUserGrid();
                    repaint();
                }
            });
        }

        sidePanel.Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });



        this.add(sidePanel);
        repaint();
    }

    private void addUserGrid() {
        System.out.println(this.customers.toString());
        ArrayList<Customer> customers = new ArrayList<>();
        for(Customer customer: this.customers){
            if (!dealership.isAdmin(customer)) {
                customers.add(customer);
            }
        }
        UserGridPanel userGridPanel = new UserGridPanel(customers);
        populateUserGrid(userGridPanel, customers);
        this.add(userGridPanel);
    }

    private void populateUserGrid(UserGridPanel userGridPanel, ArrayList<Customer> customers) {

        int numOfPages = (int)(Math.ceil((customers.size())/6.0));
        System.out.println("Number of users: " + (customers.size()-1) + "\nPages: " + numOfPages);
        if(numOfPages>8){
            numOfPages = 8;
        }

        OpaqueButton[] pageButtons = new OpaqueButton[numOfPages];
        int x = 1174; int y = 138;


        for (int i = 0; i < pageButtons.length; i++) {
            pageButtons[i] = new OpaqueButton("" + (i + 1), Colors.PLACE_HOLDER, Colors.BOTTOM_BAR, false);
            pageButtons[i].setBounds(x, y, 45, 30);
            pageButtons[i].setFont(Fonts.boldFont(14));
            pageButtons[i].setBorder((new BorderUIResource.LineBorderUIResource(Colors.BORDERS,1)));
            pageButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = Integer.parseInt(e.getActionCommand());
                    int si = (index - 1) * 6;
                    userGridPanel.populateGrid(customers, si);

                    userPanels_addHoverListeners(userGridPanel.getuPanels());




                    for (int i1 = 0; i1 < pageButtons.length; i1++) {
                        OpaqueButton button = pageButtons[i1];
                        button.setBackground(Color.white);
                        button.setForeground(Colors.BOTTOM_BAR);
                    }
                    pageButtons[index - 1].setBackground(Colors.BOTTOM_BAR);
                    pageButtons[index - 1].setForeground(Color.white);


                }
            });


            pageButtons[i].addMouseListener(new MouseListener() {

                Color backgroundColor = null;
                Color foreGroundColor = null;

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    Component component = e.getComponent();
                    if (backgroundColor == null) {
                        backgroundColor = component.getBackground();
                        foreGroundColor = component.getForeground();
                    }
                    if (!e.getComponent().getBackground().equals(Colors.BOTTOM_BAR)) {
                        component.setForeground(component.getForeground().brighter());
                        component.setBackground(component.getBackground().darker());
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!e.getComponent().getBackground().equals(Colors.BOTTOM_BAR)) {
                        e.getComponent().setBackground(backgroundColor);
                        e.getComponent().setForeground(foreGroundColor);
                    }
                }
            });

            y = y + 35 + 20;
            add(pageButtons[i]);
        }
        if(customers.size()!=0) {
            pageButtons[0].setBackground(Colors.BOTTOM_BAR);
            pageButtons[0].setForeground(Color.white);
        }

        userPanels_addHoverListeners(userGridPanel.getuPanels());

//        if(!alreadyBought) {
//            vehiclePanels_addHoverListeners(viewGridPanel.getvPanels());
//        }

        repaint();
    }

    private void userPanels_addHoverListeners(ArrayList<UserViewPanel> customerPanels) {
            for(UserViewPanel userViewPanel: customerPanels){
                userViewPanel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        showUserProfile(userViewPanel.getUser());
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
    }

    private void showUserProfile(Customer customer) {
        MainFrame.this.getContentPane().removeAll();
        addBaseComponents();
        CustomerOptionsPanel optionsPanel = new CustomerOptionsPanel();
        optionsPanel_addListeners(optionsPanel, customer);
        addUserProfile(optionsPanel, customer);
        repaint();
    }

    private void optionsPanel_addListeners(CustomerOptionsPanel optionsPanel, Customer customer) {



        optionsPanel.viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!dealership.isAdmin(customer)){
                    statusLabel.setText("");
                    MainFrame.this.getContentPane().removeAll();
                    addBaseComponents();
                    addViewGrid(customer.getBoughtVehicles(), "HISTORY");
                    repaint();
                }else{
                    statusLabel.setText("ADMINS DON'T HAVE HISTORY");
                    login(customer);
                }
            }
        });


        optionsPanel.editNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(optionsPanel);
                ModificationPanel mp = new ModificationPanel(1, customer);
                add(mp);
                repaint();

                mp.inputField.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        String input = mp.inputField.getText();
                        if(e.getKeyCode()==KeyEvent.VK_ENTER){
                            if(Helper.isValidName(input)&&(!input.equals(customer.getName()))){
                                customer.setName(input);
                                dealership.saveData();
                                login(customer);
                            }else {
                                mp.wrongInput();

                            }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });

                mp.submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String input = mp.inputField.getText();
                        if(Helper.isValidName(input)&&(!input.equals(customer.getName()))){
                            customer.setName(input);
                            dealership.saveData();
                            login(customer);
                        }else {
                            mp.wrongInput();
                        }
                    }
                });

                mp.cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showUserProfile(customer);
                    }
                });
            }
        });

        optionsPanel.editInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(optionsPanel);
                ModificationPanel mp = new ModificationPanel(2, customer);
                add(mp);
                repaint();
                mp.inputField.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        String input = mp.inputField.getText();
                        if(e.getKeyCode()==KeyEvent.VK_ENTER){
                            if((Helper.isValidEmail(input)||Helper.isValidPhone(input))&&(!input.equals(customer.getContactInfo()))){
                                customer.setContactInfo(input);
                                dealership.saveData();
                                login(customer);
                            }else {
                                mp.wrongInput();

                            }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });

                mp.submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String input = mp.inputField.getText();
                        if((Helper.isValidEmail(input)||Helper.isValidPhone(input))&&(!input.equals(customer.getContactInfo()))){
                            customer.setContactInfo(input);
                            dealership.saveData();
                            login(customer);
                        }else {
                                mp.wrongInput();

                        }
                    }
                });
                mp.cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showUserProfile(customer);
                    }
                });
            }
        });

        if(dealership.isAdmin(user)) {
            optionsPanel.addBalanceButton.setText("ADD BALANCE");
        }
        optionsPanel.addBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(optionsPanel);
                ModificationPanel mp = new ModificationPanel(3, customer);
                add(mp);
                repaint();

                mp.submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String input = mp.inputField.getText();
                        if(Helper.isValidAmount(input)&&(Integer.parseInt(input.replaceAll(",", ""))<(1000000))){
                            dealership.paymentRequest(customer, Integer.parseInt(input.replaceAll(",", "")));
                            dealership.saveData();
                            showUserProfile(customer);
                        }else {
                            mp.wrongInput();
                        }
                    }
                });

                mp.cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showUserProfile(customer);
                    }
                });
            }
        }
        );

        optionsPanel.closeAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dealership.removeCustomer(customer);
                dealership.saveData();
                if(!dealership.isAdmin(user)) {
                    logout();
                }else{
                    MainFrame.this.getContentPane().removeAll();
                    addBaseComponents();
                    addUserGrid();
                    repaint();
                }
            }
        });


    }

    private void logout() {
        dealership.saveData();
        MainFrame.this.getContentPane().removeAll();
        user = null;
        customers = null;
        addTopBar();
        addLoginPanel();
        repaint();
    }

    private void addUserProfile(JPanel optionsPanel, Customer customer) {
        add(new CustomerDetailsPanel(customer));
        add(optionsPanel);
        repaint();
    }

    private void addSearchPanel() {
        SearchPanel searchPanel = new SearchPanel(dealership.getVehicles());
        this.add(searchPanel);
        ArrayList<Vehicle> carList = dealership.getVehicles();


        searchPanel.searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    getContentPane().removeAll();
                    addBaseComponents();
                    addViewGrid(searchPanel.getCars(), "SEARCH RESULTS");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        searchPanel.searchField.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if(!searchPanel.searchField.getText().equals("\\s+")) {
                    ArrayList<Vehicle> vehicles = dealership.searchByKeywords(searchPanel.searchField.getText());
                    searchPanel.refreshCount(vehicles);
                }else{
                    searchPanel.refreshCount(cars);
                }
            }
        });
        searchPanel.goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                addBaseComponents();
                addViewGrid(searchPanel.getCars(), "SEARCH RESULTS");
            }
        });
        repaint();
    }

    private void populateViewGrid(ViewGridPanel viewGridPanel, ArrayList<Vehicle> innerCars, boolean alreadyBought){
            int numOfPages = (int)(Math.ceil(innerCars.size()/8.0));
            System.out.println("Number of cars: " + innerCars.size() + "\nPages: " + numOfPages);
            if(numOfPages>8){
                numOfPages = 8;
            }

            OpaqueButton[] pageButtons = new OpaqueButton[numOfPages];
            int x = 1174; int y = 138;


            for (int i = 0; i < pageButtons.length; i++) {
                pageButtons[i] = new OpaqueButton("" + (i + 1), Colors.PLACE_HOLDER, Colors.BOTTOM_BAR, false);
                pageButtons[i].setBounds(x, y, 45, 30);
                pageButtons[i].setFont(Fonts.boldFont(14));
                pageButtons[i].setBorder((new BorderUIResource.LineBorderUIResource(Colors.BORDERS,1)));
                pageButtons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index = Integer.parseInt(e.getActionCommand());
                        int si = (index - 1) * 8;
                        if(!alreadyBought) {
                            vehiclePanels_addHoverListeners(viewGridPanel.populateGrid(innerCars, si));
                        }else {
                            viewGridPanel.populateGrid(innerCars, si);
                        }



                        for (int i1 = 0; i1 < pageButtons.length; i1++) {
                            OpaqueButton button = pageButtons[i1];
                            button.setBackground(Color.white);
                            button.setForeground(Colors.BOTTOM_BAR);
                        }
                        pageButtons[index - 1].setBackground(Colors.BOTTOM_BAR);
                        pageButtons[index - 1].setForeground(Color.white);


                    }
                });


                pageButtons[i].addMouseListener(new MouseListener() {

                    Color backgroundColor = null;
                    Color foreGroundColor = null;

                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        Component component = e.getComponent();
                        if (backgroundColor == null) {
                            backgroundColor = component.getBackground();
                            foreGroundColor = component.getForeground();
                        }
                        if (!e.getComponent().getBackground().equals(Colors.BOTTOM_BAR)) {
                            component.setForeground(component.getForeground().brighter());
                            component.setBackground(component.getBackground().darker());
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (!e.getComponent().getBackground().equals(Colors.BOTTOM_BAR)) {
                            e.getComponent().setBackground(backgroundColor);
                            e.getComponent().setForeground(foreGroundColor);
                        }
                    }
                });

                y = y + 35 + 20;
                add(pageButtons[i]);
            }
            if(innerCars.size()!=0) {
                pageButtons[0].setBackground(Colors.BOTTOM_BAR);
                pageButtons[0].setForeground(Color.white);
            }


            if(!alreadyBought) {
                vehiclePanels_addHoverListeners(viewGridPanel.getvPanels());
            }

            repaint();
        }

    private void vehiclePanels_addHoverListeners(ArrayList<VehicleViewPanel> vPanels) {
        ArrayList<VehicleViewPanel> vehicleViewPanels = vPanels;
        for(VehicleViewPanel viewPanel: vehicleViewPanels){
            viewPanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    MainFrame.this.getContentPane().removeAll();
                    addBaseComponents();
                    VehicleDetailsPanel detailsPanel = new VehicleDetailsPanel(viewPanel.getVehicle(), (dealership.isAdmin(user)?2:1));
                    //action listener
                    detailsPanel.buyButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            statusLabel.setText("");
                            if(dealership.isAdmin(user)){
                                dealership.removeVehilce(detailsPanel.boughtVehicle());
                            }else {
                                if(dealership.buyVehicle(user, detailsPanel.boughtVehicle())){
                                    statusLabel.setText("DONE!");
                                    statusLabel.setForeground(Colors.SUCCESS_GREEN);
                                }else{
                                    statusLabel.setText("NOT ENOUGH BALANCE!");
                                    statusLabel.setForeground(Colors.WARNING_RED);
                                }
                            }
                            dealership.saveData();
                            login(user);
                        }
                    });



                    add(detailsPanel);
                    repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    viewPanel.darken();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    viewPanel.reset();
                }
            });
        }
    }

    //////importedCode
    static class MoveMouseListener implements MouseListener, MouseMotionListener {
            MainFrame target;
            Point start_drag;
            Point start_loc;

            public MoveMouseListener(MainFrame target) {
                this.target = target;
            }

            public JFrame getFrame(Container target) {
                if (target instanceof JFrame) {
                    return (JFrame) target;
                }
                return getFrame(target.getParent());
            }

            Point getScreenLocation(MouseEvent e) {
                Point cursor = e.getPoint();
                Point target_location = this.target.getLocationOnScreen();
                return new Point((int) (target_location.getX() + cursor.getX()),
                        (int) (target_location.getY() + cursor.getY()));
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                this.start_drag = this.getScreenLocation(e);
                this.start_loc = this.getFrame(this.target).getLocation();
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                Point current = this.getScreenLocation(e);
                Point offset = new Point((int) current.getX() - (int) start_drag.getX(),
                        (int) current.getY() - (int) start_drag.getY());
                JFrame frame = this.getFrame(target);
                Point new_location = new Point(
                        (int) (this.start_loc.getX() + offset.getX()), (int) (this.start_loc
                        .getY() + offset.getY()));
                frame.setLocation(new_location);
            }

            public void mouseMoved(MouseEvent e) {
            }
        }

}



