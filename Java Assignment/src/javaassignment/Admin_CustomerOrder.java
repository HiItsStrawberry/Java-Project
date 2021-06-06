package javaassignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import static javaassignment.JavaAssignment.fontFormat;
import static javaassignment.JavaAssignment.titleBorderFont;
import static javaassignment.JavaAssignment.width;
import static javaassignment.JavaAssignment.height;
import static javaassignment.JavaAssignment.orange;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Admin_CustomerOrder extends JFrame implements ActionListener {
    
    private JButton home,register,modify,payment,createOrder;
    private JLabel username, contact, address;
    private JTextField contactText1, contactText2;
    private JTextArea addressText1, addressText2;
    private JScrollPane scrollTextArea1, scrollTextArea2;
    private JComboBox sender, recipient;
    private JPanel secondPanel, leftPanel, rightPanel;
    
    public Admin_CustomerOrder() {
        setVisible(false);
        setSize(700,500);
        setTitle("Managing Staff_Customer Order Page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(2,10)); //(gap)
 
        topPanel();
        secondPanel(); 
        getSender();
        validate();
        repaint();
    }
    
    
    private void topPanel() {
        home = new JButton("Customer Page");
        register = new JButton("Registration Page");
        modify = new JButton("Modification Page");
        payment = new JButton("Payment Page");
               
        //TOP PANEL
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)
        
        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)
    
        topPanel.add(home);
        topPanel.add(register);
        topPanel.add(modify);
        topPanel.add(payment);
        this.add(topPanel, BorderLayout.PAGE_START); 
        
        home.addActionListener(this);
        register.addActionListener(this);
        modify.addActionListener(this);
        payment.addActionListener(this);
    }
   
    private void secondPanel() {
        secondPanel = new JPanel();
        secondPanel.setLayout(null);
        this.add(secondPanel, BorderLayout.CENTER);
        leftPanel();
        rightPanel();
        bottomPanel();      
    }

    private void leftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder(null, "Sender Infomration", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, titleBorderFont));
        leftPanel.setPreferredSize(new Dimension(340,500));
        this.add(leftPanel, BorderLayout.LINE_START);
        leftPanel.setLayout(null);
          
        username = new JLabel("Username: ");
        contact = new JLabel("Contact: ");
        address = new JLabel("Address: (Please fill in the address in details!)");
        
        contactText1 = new JTextField();    contactText1.setEditable(false);
        addressText1 = new JTextArea();
        
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        addressText1.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        scrollTextArea1 = new JScrollPane(addressText1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        username.setFont(fontFormat);
        contact.setFont(fontFormat);
        address.setFont(fontFormat);

        username.setBounds(10, 20, 100, 50);

        contact.setBounds(10, 60, 100, 50);
        contactText1.setBounds(100, 72, width, height);

        address.setBounds(10, 100, 310, 50);
        scrollTextArea1.setBounds(10, 148, 310, 180);

        addressText1.setLineWrap(true);
        addressText1.setWrapStyleWord(true);

                 
        // Labels:   TextFields
        leftPanel.add(username);
        leftPanel.add(contact);leftPanel.add(contactText1);
        leftPanel.add(address);leftPanel.add(scrollTextArea1);           
   }

    private void rightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder(null, "Recipient Infomration", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, titleBorderFont));
        rightPanel.setPreferredSize(new Dimension(340,500));
        this.add(rightPanel, BorderLayout.LINE_END);
        rightPanel.setLayout(null);

        username = new JLabel("Username: ");
        contact = new JLabel("Contact: ");
        address = new JLabel("Address: (Please fill in the address in details!)");
                
        contactText2 = new JTextField();
        addressText2 = new JTextArea();
        
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        addressText2.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        scrollTextArea2 = new JScrollPane(addressText2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        username.setFont(fontFormat);
        contact.setFont(fontFormat);
        address.setFont(fontFormat);
                
        username.setBounds(10, 20, 100, 50);     
        contact.setBounds(10, 60, 100, 50);
        contactText2.setBounds(100, 72, width, height);
        contactText2.setEditable(false);
        
        address.setBounds(10, 100, 310, 50);
        scrollTextArea2.setBounds(10, 148, 310, 180);
        
        addressText2.setLineWrap(true);
        addressText2.setWrapStyleWord(true);
        
        
        // Labels:   TextFields
        rightPanel.add(username);
        rightPanel.add(contact);rightPanel.add(contactText2);
        rightPanel.add(address);rightPanel.add(scrollTextArea2);
    }
    
    private void bottomPanel() {
        createOrder = new JButton("Create Order");
        createOrder.setPreferredSize(new Dimension(150, 30));
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout()); 
        bottomPanel.setPreferredSize(new Dimension(0,50));   
        bottomPanel.setLayout(new FlowLayout(2,10,10));

        bottomPanel.setBackground(orange);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); //(top, left, bottom, right)
        bottomPanel.add(createOrder);

        this.add(bottomPanel, BorderLayout.PAGE_END);       
        createOrder.addActionListener(this); 

    }
 
    private void getSender() {   
        try {
            int total = JavaAssignment.allAccount.size();
            sender = new JComboBox();
            recipient = new JComboBox();                              
            sender.removeAllItems();
            recipient.removeAllItems();
            sender.addItem("None");
            recipient.addItem("None");
            String names;
            
            
            for(int i = 0; i < total; ++i) {
                if(JavaAssignment.allAccount.get(i).getRole() == 3) {
                    String getName = JavaAssignment.allAccount.get(i).getUsername();
                    names = getName;
                    sender.addItem(names);             
                    recipient.addItem(names);
                }
            }
            sender.setBounds(100, 32, width, height);
            recipient.setBounds(100, 32, width, height);
            leftPanel.add(sender);
            rightPanel.add(recipient);
        }
        catch (Exception e) {
            System.out.println("ERROR :"+e);
        }  
        senderBox();
        recipientBox();
    }    
    
    private void senderBox() {
        ItemListener itemListener = (ItemEvent e) -> {
            String getSender = sender.getSelectedItem().toString();
            String getRecipient = recipient.getSelectedItem().toString();
            for(int i = 0; i < JavaAssignment.allAccount.size(); ++i) {
                if (getSender.equals("None")) {
                    contactText1.setText("");
                }
                else {
                    contactText1.setText(JavaAssignment.allAccount.get(i).getContact());
                }
            }
        };
        sender.addItemListener(itemListener); 
    }
    
        private void recipientBox() {
        ItemListener itemListener = (ItemEvent e) -> {
            String getRecipient = recipient.getSelectedItem().toString();
            for(int i = 0; i < JavaAssignment.allAccount.size(); ++i) {
                if (getRecipient.equals("None")) {
                    contactText2.setText("");
                }
                else {
                    contactText2.setText(JavaAssignment.allAccount.get(i).getContact());
                }    
            }
        };
        recipient.addItemListener(itemListener); 
    }

    private void clear() {
        sender.setSelectedItem("None");
        recipient.setSelectedItem("None");
        contactText1.setText("");
        contactText2.setText("");
        addressText1.setText("");
        addressText2.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == home) {
            setVisible(false);
            Admin_CustomerHome ac = new Admin_CustomerHome();
            ac.setVisible(true);             
        }
        else if (e.getSource() == register) {
            setVisible(false);
            JavaAssignment.customerRegister.setVisible(true);                  
        }
        else if (e.getSource() == modify) {
            setVisible(false);
            JavaAssignment.customerModify.setVisible(true);                  
        }
        else if (e.getSource() == payment) {
            setVisible(false);
            JavaAssignment.customerPayment.setVisible(true);                
        }
        else {
            String getSender = sender.getSelectedItem().toString().split("\\|")[0];
            String getContact1 = contactText1.getText();
            String getRecipient = recipient.getSelectedItem().toString().split("\\|")[0];
            String getContact2 = contactText2.getText();
            
            String[] address1 = addressText1.getText().split("\n");
            String getAddress1 = "" ;
            String[] address2 = addressText2.getText().split("\n");
            String getAddress2 = "" ;

            for (String i : address1) {
                getAddress1 = getAddress1+" " + i;
            }  
            getAddress1 = getAddress1.substring(1);
            
            for (String i : address2) {
                getAddress2 = getAddress2+" " + i;
            }  
            getAddress2 = getAddress2.substring(1);
            
            boolean flag = false;
            if (getContact1.isEmpty() || getContact2.isEmpty() || getAddress1.isEmpty() || getAddress2.isEmpty()) {                
                JOptionPane.showMessageDialog(this, "Please fill in all the empty fields !");
                flag = false;
            }
            else if (getSender.equals(getRecipient) || getRecipient.equals(getSender)) {
                JOptionPane.showMessageDialog(this, "Sender and recipient cannot be the same !");
                flag = false;      
            }
            else {
                flag = true;
            }
            
            if (flag == true) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
                Date date = new Date();  
                int size = JavaAssignment.allOrder.size();
                int ID;
                if(size == 0) {
                    ID = 10001;
                }
                else {
                    ID = 10001 + size;
                }
               
                String getDate = formatter.format(date);
                String status = "Processing";
                String rating = "None";
                String feedback = "None";
                String deliveryStaff = "None";
                Order order = new Order(ID, getDate, status, rating, feedback, deliveryStaff, getSender, getRecipient, getContact1, getContact2, getAddress1.trim(), getAddress2.trim());
                JavaAssignment.allOrder.add(order);
                order.addOrder();                      
                Payment payments = new Payment(ID, "None", JavaAssignment.orders, JavaAssignment.orders, JavaAssignment.orders, 0, 0);
                JavaAssignment.allPayment.add(payments);
                payments.addPayment();
                int response = payments.makePayment(ID, getSender, getRecipient, 5);
                if(response == 0) {
                    this.setVisible(false);
                }
                clear();
            }
        }
    }
}
