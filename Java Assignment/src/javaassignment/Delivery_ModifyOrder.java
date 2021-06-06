package javaassignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static javaassignment.JavaAssignment.fontFormat;
import static javaassignment.JavaAssignment.height;
import static javaassignment.JavaAssignment.orange;
import static javaassignment.JavaAssignment.width;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Delivery_ModifyOrder extends JFrame implements ActionListener {
    
    private JButton home, view, complete;
    private JLabel orderID, delivery, recipient, address, status;
    private JTextField orderIDText, deliveryText, recipientText, addressText, statusText;
    
    public Delivery_ModifyOrder() {
        setVisible(false);
        setSize(700,500);
        setTitle("Delivery Staff_Comeplete Order Page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(2,10)); //(gap)
        
        topPanel();
        middlePanel();
        bottomPanel();
        
        validate();
        repaint();          
    }

    
    private void topPanel() {
        home = new JButton("Home Page");
        view = new JButton("View Current Order");  

        //TOP PANEL
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)

        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)

        topPanel.add(home);
        topPanel.add(view);

        this.add(topPanel, BorderLayout.PAGE_START); 

        home.addActionListener(this);
        view.addActionListener(this);        
    }      
      
    private void middlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(null);
        this.add(middlePanel, BorderLayout.CENTER);  
        
        
        orderID = new JLabel("Order ID:");
        orderID.setFont(fontFormat);
        delivery = new JLabel("Delivery Staff:");
        delivery.setFont(fontFormat);
        recipient = new JLabel("Recipient:");
        recipient.setFont(fontFormat);
        address = new JLabel("Recipient's address:");
        address.setFont(fontFormat);
        status = new JLabel("Order status:");
        status.setFont(fontFormat);
        
        orderIDText = new JTextField();     orderIDText.setEditable(false);
        deliveryText = new JTextField();      deliveryText.setEditable(false);
        recipientText = new JTextField();   recipientText.setEditable(false);
        addressText = new JTextField();     addressText.setEditable(false);
        statusText = new JTextField();      statusText.setEditable(false); 
        
        complete = new JButton("Complete Delivering");
        
        orderID.setBounds(167, 32, 100, 50);
        delivery.setBounds(167, 72, 100, 50);
        recipient.setBounds(167, 112, 100, 50);
        address.setBounds(167, 152, 138, 50);
        status.setBounds(167, 192, 100, 50); 
        
        orderIDText.setBounds(308, 42, width, height);
        deliveryText.setBounds(308, 82, width, height);
        recipientText.setBounds(308, 122, width, height);
        addressText.setBounds(308, 162, width, height);
        statusText.setBounds(308, 202, width, height); 
        
        complete.setBounds(378, 260, 150, height);
        
        middlePanel.add(orderID);       middlePanel.add(orderIDText);
        middlePanel.add(delivery);      middlePanel.add(deliveryText);
        middlePanel.add(recipient);     middlePanel.add(recipientText);
        middlePanel.add(address);       middlePanel.add(addressText);
        middlePanel.add(status);        middlePanel.add(statusText);
        
        middlePanel.add(complete); 
        
        complete.addActionListener(this);      
    }
        
    private void bottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout()); 
        bottomPanel.setPreferredSize(new Dimension(0,50));   
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(orange);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); //(top, left, bottom, right)
        this.add(bottomPanel, BorderLayout.PAGE_END);               
    }      
     
    public JTextField getTheID() {
        return orderIDText;
    }
    
    public JTextField getTheDelivery() {
        return deliveryText;
    }    
    
    public JTextField getTheRecipient() {
        return recipientText;
    }
    
    public JTextField getTheAddress() {
        return addressText;
    }
    
    public JTextField getTheStatus() {
        return statusText;
    }
    
    private void clear() {
        orderIDText.setText("");
        deliveryText.setText("");
        recipientText.setText("");
        addressText.setText("");
        statusText.setText("");
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            setVisible(false);
            Delivery_Home dh = new Delivery_Home();
            String getName = JavaAssignment.onLogin.getUsername();
            String finalName = getName.substring(0, 1).toUpperCase() + getName.substring(1);
            dh.getTheName().setText(finalName);
            dh.setVisible(true);              
        }
        else if (ae.getSource() == view) {
            Delivery_ViewOrder dv = new Delivery_ViewOrder();
            dv.setVisible(true);            
        }        
        else {
            String getOrderID = orderIDText.getText().trim();
            String getDeliver = deliveryText.getText().trim();
            String getRecipient = recipientText.getText().trim();
            String getAddress = addressText.getText().trim();
            String getStatus = statusText.getText().trim();
            boolean isComplete = false;
            
            if(getOrderID.isEmpty() || getDeliver.isEmpty() || getRecipient.isEmpty() || getAddress.isEmpty() || getStatus.isEmpty()) {
                JOptionPane.showMessageDialog(this, "An order must be selected to complete the delivering !");
            } 
            else {
                try {
                    for(int i = 0; i < JavaAssignment.allOrder.size(); ++i) {
                        for(int j = 0; j < JavaAssignment.availableStaff.size(); ++j) {
                            String verifyDeliver = JavaAssignment.availableStaff.get(j).getOwner().getUsername();
                            if(Integer.parseInt(getOrderID) == JavaAssignment.allOrder.get(i).getOrderID()) {
                                if(getDeliver.equals(verifyDeliver)) {
                                    int response = JOptionPane.showConfirmDialog(this, "Are you sure want to complete your order ?", null, JOptionPane.YES_NO_OPTION);
                                    if (response == JOptionPane.YES_OPTION) {
                                        JavaAssignment.allOrder.get(i).setStatus("Delivered");
                                        JavaAssignment.availableStaff.get(j).setTask(0);
                                        isComplete = true;
                                    }                                      
                                }          
                            }            
                        }
                    }
                    
                    if(isComplete == true) {       
                        PrintWriter p = new PrintWriter("Order.txt");
                        for(int i=0; i< JavaAssignment.allOrder.size(); ++i) {
                            Order o = JavaAssignment.allOrder.get(i);
                            p.println("OrderID:" + o.getOrderID());
                            p.println("Order Date:" + o.getDate());
                            p.println("Order Status:" + o.getStatus());
                            p.println("Order Rating:" + o.getRating());
                            p.println("Order Feedback:" + o.getFeedback());
                            p.println("Delivery Staff:" + o.getDeliveryStaff());
                            p.println("Sender:" + o.getSender());
                            p.println("Recipient:" + o.getRecipient());
                            p.println("Sender's Contact:" + o.getContact1());
                            p.println("Recipient's Contact:" + o.getContact2());
                            p.println("Sender's Address:" + o.getAddress1());
                            p.println("Recipient's Address:" + o.getAddress2());
                            p.println();
                        }
                        p.close();
                        JOptionPane.showMessageDialog(this, "Your Order ID: "+getOrderID+" has been completed !");  
                        clear();                           

                        PrintWriter pa = new PrintWriter("DeliveryInfo.txt");
                        for(int i=0; i< JavaAssignment.availableStaff.size(); ++i) {
                            DeliveryTask dta = JavaAssignment.availableStaff.get(i);
                            pa.println("Username:" + dta.getOwner().getUsername());
                            pa.println("Role:" + dta.getRole().getRole());
                            pa.println("Task:" + dta.getTask());
                            pa.println();
                        }
                        pa.close();  
       
                        int response = JOptionPane.showConfirmDialog(this, "Do you want to send an email to the customer for notifying them the parcel is delivered ?", null, JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) { 
                            this.setVisible(false);
                            Delivery_Email de = new Delivery_Email();
                            de.setVisible(true);
                        }   
                    }
                }
                catch (HeadlessException | FileNotFoundException | NumberFormatException e) { 
                    System.out.print("ERROR :" + e);
                }
            }
        }
    }
}
