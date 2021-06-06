package javaassignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static javaassignment.JavaAssignment.orange;
import static javaassignment.JavaAssignment.fontFormat;
import static javaassignment.JavaAssignment.width;
import static javaassignment.JavaAssignment.height;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin_DeliveryOrder extends JFrame implements ActionListener{
    private JButton main, home, edit, register, assign;
    private JLabel orderid, deliverystaff, sender, recipient, recipient_address;
    private JTextField orderIDText, senderText, recipientText, recipient_addressText;
    private JComboBox searchText,deliverystaffText;
    private JPanel middlePanel;
    
    public Admin_DeliveryOrder(){
        setVisible(false);
        setSize(700,500);
        setTitle("Managing Staff_Delivery Staff Order Page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(2,10)); //(gap)
                
        topPanel();
        middlePanel();
        bottomPanel();     
        getData();
        
        validate();
        repaint();
    }
    private void topPanel() {
        main = new JButton("Home Page");
        home = new JButton("Delivery Staff Page");
        register = new JButton("Registration Page");
        edit = new JButton("Modification Page");
        
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)
        
        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)
    
        topPanel.add(main);
        topPanel.add(home);
        topPanel.add(register);
        topPanel.add(edit);
        
        main.addActionListener(this);
        home.addActionListener(this);
        register.addActionListener(this);
        edit.addActionListener(this);
        this.add(topPanel, BorderLayout.PAGE_START);         
    }
   
    private void middlePanel() {
        middlePanel = new JPanel();
        middlePanel.setLayout(null);
//        middlePanel.setBorder(BorderFactory.createTitledBorder(null, "Assign Order Information", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, titleBorderFont));
        this.add(middlePanel, BorderLayout.CENTER);
 
        orderid = new JLabel("Order ID:");
        orderid.setFont(fontFormat);
        deliverystaff = new JLabel("Delivery Staff:");
        deliverystaff.setFont(fontFormat);
        sender = new JLabel("Sender:");
        sender.setFont(fontFormat);
        recipient = new JLabel("Recipient:");
        recipient.setFont(fontFormat);
        recipient_address = new JLabel("Recipient's Address:");
        recipient_address.setFont(fontFormat);
        
//        searchText = new JComboBox();
        orderIDText = new JTextField();             orderIDText.setEditable(false);
         
        senderText = new JTextField();              senderText.setEditable(false);
        recipientText = new JTextField();           recipientText.setEditable(false);
        recipient_addressText = new JTextField();   recipient_addressText.setEditable(false);
        
        orderid.setBounds(167,90,120,height);
        deliverystaff.setBounds(167,130,120,height);
        sender.setBounds(167,170,120,height);
        recipient.setBounds(167,210,120,height);
        recipient_address.setBounds(167,250,145,height);
        
//        searchText.setBounds(240,38,width,height);       
        orderIDText.setBounds(308,90,width,height);
        
        senderText.setBounds(308,170,width,height);
        recipientText.setBounds(308,210,width,height);
        recipient_addressText.setBounds(308,250,width,height);
        
        assign = new JButton("Assign Order");
        assign.addActionListener(this);
        assign.setBounds(398,300,130,height);
        
//        middlePanel.add(searchText);
        middlePanel.add(assign);
        middlePanel.add(orderid);           middlePanel.add(orderIDText);
        middlePanel.add(deliverystaff);
        middlePanel.add(sender);            middlePanel.add(senderText);
        middlePanel.add(recipient);         middlePanel.add(recipientText);
        middlePanel.add(recipient_address); middlePanel.add(recipient_addressText);
    }

    private void bottomPanel() {       
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(0,50));   
        bottomPanel.setLayout(new FlowLayout(2,10,10));
        bottomPanel.setBackground(orange);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); //(top, left, bottom, right)
        this.add(bottomPanel, BorderLayout.PAGE_END);
    }   
     
    private void getData() {   
        try {
            JavaAssignment.orderData();
            JavaAssignment.availableStaff();
            searchText = new JComboBox();              
            deliverystaffText = new JComboBox();
            searchText.removeAllItems();
            deliverystaffText.removeAllItems();
            searchText.addItem("None");
            deliverystaffText.addItem("None");
            int getID;
            String getNames;
            
            for(int i = 0; i < JavaAssignment.allOrder.size(); ++i) {
                for(int j = 0; j < JavaAssignment.allPayment.size(); ++j) {
                    if(JavaAssignment.allOrder.get(i).getOrderID() == JavaAssignment.allPayment.get(j).getMyOrderID().getOrderID()) {
                        if(JavaAssignment.allOrder.get(i).getStatus().equals("Processing") && JavaAssignment.allPayment.get(j).getPaid() == 1) {
                            getID = JavaAssignment.allOrder.get(i).getOrderID();
                            searchText.addItem(getID);                   
                        }                             
                    }                  
                }                 
            }
            
          
            for(int i = 0; i < JavaAssignment.availableStaff.size(); ++i) {
                if(JavaAssignment.availableStaff.get(i).getTask() < 1) {
                    getNames = JavaAssignment.availableStaff.get(i).getOwner().getUsername();
                    deliverystaffText.addItem(getNames);                   
                }
            }
            
            searchText.setBounds(240,38,width,height);
            deliverystaffText.setBounds(308,130,width,height);
            middlePanel.add(deliverystaffText);
            middlePanel.add(searchText);
        }
        catch (Exception e) {
            System.out.println("ERROR :"+e);
        }  
        searchBox();
    }
  
    public void searchBox() {   
        ItemListener itemListener = new ItemListener() {     
            public void itemStateChanged(ItemEvent e) {
                String getItem = searchText.getSelectedItem().toString();          
                for(int i = 0; i < JavaAssignment.allOrder.size(); i++) {
                    if(getItem.equals("None")) {
                        clear();
                    }
                    else if(Integer.parseInt(getItem) == (JavaAssignment.allOrder.get(i).getOrderID())) {
                        int getID = JavaAssignment.allOrder.get(i).getOrderID();
                        String getSender = JavaAssignment.allOrder.get(i).getSender();
                        String getRecipient = JavaAssignment.allOrder.get(i).getRecipient();
                        String getAddress = JavaAssignment.allOrder.get(i).getAddress2();
                        orderIDText.setText(Integer.toString(getID));
                        senderText.setText(getSender);
                        recipientText.setText(getRecipient);
                        recipient_addressText.setText(getAddress);
                    }                    
                }
            }
        };
        searchText.addItemListener(itemListener); 
    }
    
    private void clear() {
        orderIDText.setText("");
        senderText.setText("");
        recipientText.setText("");
        recipient_addressText.setText("");     
    }    
    
    public void setID(int ID) {    
        orderIDText.setText(Integer.toString(ID));
    }
    
    public void setSender(String sender) {
        senderText.setText(sender);
    }
        
    public void setRecipient(String recipient) {
        recipientText.setText(recipient);
    }
            
    public void setAddress(String address) {
        recipient_addressText.setText(address);   
    }    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == main) {
            setVisible(false);
            Admin_Home ah = new Admin_Home();
            String getName = JavaAssignment.onLogin.getUsername();
            String finalName = getName.substring(0, 1).toUpperCase() + getName.substring(1);
            ah.getTheName().setText(finalName);
            ah.setVisible(true);    
        }        
        else if (ae.getSource() == home) {
            setVisible(false);
            Admin_DeliveryHome ad = new Admin_DeliveryHome();
            ad.setVisible(true);
        }
        else if (ae.getSource() == register) {
            setVisible(false);
            JavaAssignment.admin_deliveryRegister.setVisible(true);
        }
        else if (ae.getSource() == edit) {
            setVisible(false);
            JavaAssignment.admin_deliveryModify.setVisible(true);              
        }        
        else {
            try {
                String getOrderID = orderIDText.getText();
                String getDeliver = deliverystaffText.getSelectedItem().toString();
                boolean update = false;
                
                if(getOrderID.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "An order must be selected to assign !");
                }
                else if(getDeliver.equals("None")) {
                    JOptionPane.showMessageDialog(this, "A delivery staff must be selected !");
                }
                else {
                    for(int i = 0; i < JavaAssignment.allOrder.size(); ++i) {
                        for(int j = 0; j < JavaAssignment.availableStaff.size(); ++j) {
                            String getDeliverName = JavaAssignment.availableStaff.get(j).getOwner().getUsername();
                            if (Integer.parseInt(getOrderID) == JavaAssignment.allOrder.get(i).getOrderID()) { 
                                if(getDeliver.equals(getDeliverName)) {
                                    int response = JOptionPane.showConfirmDialog(this, "Are you sure want to assign order: " + getOrderID +" to "+ getDeliver +" delivery staff ?", null, JOptionPane.YES_NO_OPTION);
                                    if (response == JOptionPane.YES_OPTION) {
                                        JavaAssignment.allOrder.get(i).setStatus("Delivering");
                                        JavaAssignment.allOrder.get(i).setDeliveryStaff(getDeliver);
                                        JavaAssignment.availableStaff.get(j).setTask(1);
                                        update = true;
                                    }                           
                                }
                            }                          
                        }      
                    }                    
                }
                
                if (update == true) {
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
                    JOptionPane.showMessageDialog(this, "Order: "+getOrderID+" assigned to "+getDeliver+" successfully !"); 
                    clear();
                    searchText.setSelectedItem("None");
                    deliverystaffText.setSelectedItem("None");                      
                    
                    PrintWriter pa = new PrintWriter("DeliveryInfo.txt");
                    for(int i=0; i< JavaAssignment.availableStaff.size(); ++i) {
                        DeliveryTask dt = JavaAssignment.availableStaff.get(i);                                                                                         
                        pa.println("Username:" + dt.getOwner().getUsername());
                        pa.println("Role:" + dt.getRole().getRole());
                        pa.println("Task:" + dt.getTask());
                        pa.println();  
                    }
                    pa.close();
                    setVisible(false);
                    Admin_DeliveryOrder ad = new Admin_DeliveryOrder();
                    ad.setVisible(true);  
                }
            }
            catch(HeadlessException | FileNotFoundException | NumberFormatException e) {
                System.out.println("ERROR :"+e);
            }
        }        
    }
}
