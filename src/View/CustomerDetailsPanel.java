package View;

import HelperClasses.Colors;
import HelperClasses.Fonts;
import HelperClasses.Helper;
import HelperComponents.NullLayoutPanel;
import Model.Customer;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class CustomerDetailsPanel extends NullLayoutPanel {

    private Customer customer;
    private JLabel nameValueLabel;
    private JLabel contactInfoValueLabel;
    private JLabel idValueLabel;
    private JLabel balanceValueLabel;
    private JLabel transactionsValueLabel;

    public CustomerDetailsPanel(Customer ust){
        //BOUNDS WRT MainFrame
        this(175, 109, 372, 432, ust);
    }
    public CustomerDetailsPanel(int x, int y, int w, int h, Customer ust) {
        super(x, y, w, h);
        setBackground(Color.white);
        setBorder(new BorderUIResource.LineBorderUIResource(Colors.BORDERS,1));

        customer = ust;

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(29, 151-109, 200, 30);
        nameLabel.setFont(Fonts.mediumFont(26));
        nameLabel.setForeground(Colors.BOTTOM_BAR);

        nameValueLabel = new JLabel(customer.getName());
        nameValueLabel.setBounds(29, 175-109 , 200,22);
        nameValueLabel.setFont(Fonts.normalFont(15));
        nameValueLabel.setForeground(Colors.BOTTOM_BAR);

        JLabel contactInfoLabel = new JLabel("Contact Info:");
        contactInfoLabel.setBounds(29, 226-109, 200, 30);
        contactInfoLabel.setFont(Fonts.mediumFont(26));
        contactInfoLabel.setForeground(Colors.BOTTOM_BAR);

        contactInfoValueLabel = new JLabel(customer.getContactInfo());
        contactInfoValueLabel.setBounds(29, 253-109, 200, 22);
        contactInfoValueLabel.setFont(Fonts.normalFont(15));
        contactInfoValueLabel.setForeground(Colors.BOTTOM_BAR);


        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(29, 302-109, 200, 30);
        idLabel.setFont(Fonts.mediumFont(26));
        idLabel.setForeground(Colors.BOTTOM_BAR);

        idValueLabel = new JLabel(customer.getID());
        idValueLabel.setBounds(29, 331-109, 200, 22);
        idValueLabel.setFont(Fonts.normalFont(15));
        idValueLabel.setForeground(Colors.BOTTOM_BAR);

        JLabel balanceLabel = new JLabel("Balance");
        balanceLabel.setBounds(29, 378-109, 200, 30);
        balanceLabel.setFont(Fonts.mediumFont(26));
        balanceLabel.setForeground(Colors.BOTTOM_BAR);

        balanceValueLabel = new JLabel(Helper.getFormattedNumber(customer.getCurrentBalance()));
        balanceValueLabel.setBounds(29, 408-109, 200, 22);
        balanceValueLabel.setFont(Fonts.normalFont(15));
        balanceValueLabel.setForeground(Colors.BOTTOM_BAR);

        JLabel transactionsLabel = new JLabel("Transactions:");
        transactionsLabel.setBounds(29, 454-109, 200, 30);
        transactionsLabel.setFont(Fonts.mediumFont(26));
        transactionsLabel.setForeground(Colors.BOTTOM_BAR);

        transactionsValueLabel = new JLabel(""+customer.getTransactionsRecord().size()+ " Cars Bought");
        transactionsValueLabel.setBounds(29, 487-109, 200, 22);
        transactionsValueLabel.setFont(Fonts.normalFont(15));
        transactionsValueLabel.setForeground(Colors.BOTTOM_BAR);


        add(nameLabel);
        add(nameValueLabel);
        add(contactInfoLabel);
        add(contactInfoValueLabel);
        add(idLabel);
        add(idValueLabel);
        add(balanceLabel);
        add(balanceValueLabel);
        add(transactionsLabel);
        add(transactionsValueLabel);
        repaint();

    }

    public void updateAll(){
        nameValueLabel.setText(customer.getName());
        contactInfoValueLabel.setText(customer.getContactInfo());
        idValueLabel.setText(customer.getID());
        balanceValueLabel.setText(Helper.getFormattedNumber(customer.getCurrentBalance()));
        transactionsValueLabel.setText(""+customer.getTransactionsRecord().size()+ " Cars Bought");
    }
}
