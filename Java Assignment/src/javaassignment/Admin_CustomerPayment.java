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
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class Admin_CustomerPayment extends JFrame implements ActionListener {
    
    private JButton home,register,modify,order,payment, search; 
    private JLabel paymentID, sender, recipient, fee;
    private JTextField paymentIDText, senderText, recipientText, feeText, searchText;
    private JPanel middlePanel;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    Date date = new Date();  
    private final String getDate = formatter.format(date);
    private int verifyID;
    
    
    public Admin_CustomerPayment() {
        setSize(700, 500);
        setLocationRelativeTo(null);
        setTitle("Managing Staff_Customer Payment Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(2,10)); //(gap)        
        setVisible(false);
                
        topPanel();
        middlePanel();
        bottomPanel();
        validate();
        repaint();       
    }
    
    
    
    
    private void topPanel() {
        home = new JButton("Customer Page");
        register = new JButton("Registration Page");
        modify = new JButton("Modification Page");
        order = new JButton("Create Order Page");

        //TOP PANEL
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)

        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)

        topPanel.add(home);
        topPanel.add(register);
        topPanel.add(modify);
        topPanel.add(order);
        this.add(topPanel, BorderLayout.PAGE_START); 
        
        home.addActionListener(this);
        register.addActionListener(this);
        modify.addActionListener(this);
        order.addActionListener(this);
    }        

    private void middlePanel() {
        middlePanel = new JPanel();
        middlePanel.setLayout(null);
//        middlePanel.setBackground(Color.yellow);
//        middlePanel.setBorder(BorderFactory.createTitledBorder(null, "Sender Infomration", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, titleBorderFont));     
        this.add(middlePanel, BorderLayout.CENTER);

     
        paymentID = new JLabel("Payment ID:");
        paymentID.setFont(fontFormat);
        sender = new JLabel("Sender:");
        sender.setFont(fontFormat);
        recipient = new JLabel("Recipient:");
        recipient.setFont(fontFormat);
        fee = new JLabel("Shipping Fee (RM):");
        fee.setFont(fontFormat);

        
        searchText = new JTextField();
        paymentIDText = new JTextField(); paymentIDText.setEditable(false);
        senderText = new JTextField(); senderText.setEditable(false);
        recipientText = new JTextField(); recipientText.setEditable(false);
        feeText = new JTextField();
        

        paymentID.setBounds(167, 72, 100, 50);
        sender.setBounds(167, 112, 100, 50);
        recipient.setBounds(167, 152, 100, 50);
        fee.setBounds(167, 192, 150, 50);

        searchText.setBounds(238, 32, width, height);
        paymentIDText.setBounds(308, 82, width, height);
        senderText.setBounds(308, 122, width, height);
        recipientText.setBounds(308, 162, width, height);
        feeText.setBounds(308, 202, width, height);  
        
        payment = new JButton("Make Payment");
        payment.setBounds(398, 260, 130, height);
        search = new JButton("Get");
        search.setBounds(468, 32, 60, height);
        

        middlePanel.add(searchText);    middlePanel.add(search);
        
        middlePanel.add(paymentID);     middlePanel.add(paymentIDText);
        middlePanel.add(sender);        middlePanel.add(senderText);
        middlePanel.add(recipient);     middlePanel.add(recipientText);
        middlePanel.add(fee);           middlePanel.add(feeText);
        middlePanel.add(payment);
        
        payment.addActionListener(this);
        search.addActionListener(this);
        

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
    
    private void clear() {
        paymentIDText.setText("");
        senderText.setText("");
        recipientText.setText("");
        feeText.setText("");
    }
    
    public void setID(int ID) {    
        paymentIDText.setText(Integer.toString(ID));
    }
    
    public void setSender(String sender) {
        senderText.setText(sender);
    }
        
    public void setRecipient(String recipient) {
        recipientText.setText(recipient);
    }
            
    public void setFee(double fee) {
        feeText.setText(Double.toString(fee));   
    }
    

           
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            setVisible(false);
            Admin_CustomerHome ac = new Admin_CustomerHome();
            ac.setVisible(true);
        }
        else if (ae.getSource() == register) {
            setVisible(false);
            JavaAssignment.customerRegister.setVisible(true);
        }
        else if (ae.getSource() == modify) {
            setVisible(false);
            JavaAssignment.customerModify.setVisible(true);            
        }
        else if (ae.getSource() == order) {
            setVisible(false);
            Admin_CustomerOrder aco = new Admin_CustomerOrder();
            aco.setVisible(true);
        }
        else if (ae.getSource() == payment) {      
            String getID = paymentIDText.getText();            
            String getSender = senderText.getText();
            String getRecipient = recipientText.getText();
            String getFee = feeText.getText().trim();  
            
            JavaAssignment ja = new JavaAssignment();
            ja.randomString();
            String randomString = JavaAssignment.randomString;
            ja.randomString();
            String randomString2 = JavaAssignment.randomString2;
            boolean update = false;  
            
            if(getID.isEmpty() || getSender.isEmpty() || getRecipient.isEmpty() || getFee.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the empty fields!");
            }
            else {
                try {
                    verifyID = Integer.parseInt(getID);
                    for(int i = 0; i < JavaAssignment.allPayment.size(); i++) {
                        int checkID = JavaAssignment.allPayment.get(i).getPaymentID();

                        if (verifyID == checkID) {                        
                            if (Double.parseDouble(getFee) <= 4.99) {
                                JOptionPane.showMessageDialog(this, "Minimum shipping fee is RM 5.00");
                                update = false; 
                                feeText.setText("5.0");
                                break;
                            }
                            else {
                                int response = JOptionPane.showConfirmDialog(this, "Are you sure want to pay for: " + getID +" ?", null, JOptionPane.YES_NO_OPTION);
                                if (response == JOptionPane.YES_OPTION) {
                                    JavaAssignment.allPayment.get(i).setDate(getDate);
                                    JavaAssignment.allPayment.get(i).setFee(Double.parseDouble(getFee));
                                    JavaAssignment.allPayment.get(i).setPaid(1);
                                    update = true;                                                                  
                                } 
                                else {
                                    update = false;
                                    break;
                                }
                            }
                        }
                    }

                    if (update == true) {
                        PrintWriter p = new PrintWriter("Payment.txt");
                        for(int i=0; i< JavaAssignment.allPayment.size(); ++i) {
                            Payment pa = JavaAssignment.allPayment.get(i);
                            p.println("PaymentID:"+pa.getPaymentID());
                            p.println("Date:"+pa.getDate());
                            p.println("OrderID:"+pa.getMyOrderID().getOrderID());
                            p.println("Sender:"+pa.getSender().getSender());
                            p.println("Recipient:"+pa.getRecipient().getRecipient());
                            p.println("Shipping Fee:"+pa.getFee());
                            p.println("Paid:"+pa.getPaid());                        
                            p.println();                                            
                        }
                        p.close();                    
                        JOptionPane.showMessageDialog(this, "PaymentID: "+getID+" is paid successfully !\nPlease come again !");
                        clear();   

                        
                        for(int i = 0; i < JavaAssignment.allPayment.size(); ++i) {
                            JavaAssignment.payments = JavaAssignment.allPayment.get(i);  
                            int checkID = JavaAssignment.payments.getPaymentID();
                            String checkSender = JavaAssignment.payments.getSender().getSender();

                            if(verifyID == checkID && getSender.equals(checkSender)) {                                     
                                Receipt receipt = new Receipt(randomString+randomString2, JavaAssignment.payments, JavaAssignment.payments, JavaAssignment.payments, JavaAssignment.payments, JavaAssignment.payments);
                                JavaAssignment.allReceipt.add(receipt); 
                                receipt.printReceipt(); 
                                break;
                            }
                        }  

                        for(int i = 0; i < JavaAssignment.allOrder.size(); ++i) {
                            JavaAssignment.orders = JavaAssignment.allOrder.get(i); 
                            if(verifyID == JavaAssignment.orders.getOrderID()) {
                                JavaAssignment.orders.assignOrder(Integer.parseInt(getID), JavaAssignment.orders.getSender(), JavaAssignment.orders.getRecipient(), JavaAssignment.orders.getAddress2());
                                break;
                            }  
                        }                                               
                    }                        
                }
                catch (HeadlessException | FileNotFoundException | NumberFormatException e) {
                    System.out.println("ERROR :" + e); 
                } 
            }
        }
        else {
            try {
                String searchIDs = searchText.getText().trim();
                boolean find = false;
                int found = 0;
                for(int i = 0; i < JavaAssignment.allPayment.size(); ++i) {
                    int getPaymentID1 = JavaAssignment.allPayment.get(i).getPaymentID();
                    int checkPaid = JavaAssignment.allPayment.get(i).getPaid();
        
                    if (searchIDs.isEmpty()) {                     
                        found = 1;
                        break;
                    }                  
                    else if(Integer.parseInt(searchIDs) == getPaymentID1) {                        
                        find = true;                        
                        if (find == true && checkPaid == 0) {                           
                            int getPaymentID2 = JavaAssignment.allPayment.get(i).getPaymentID();
                            String getSender = JavaAssignment.allPayment.get(i).getSender().getSender();
                            String getRecipient = JavaAssignment.allPayment.get(i).getRecipient().getRecipient();
                            
                            paymentIDText.setText(Integer.toString(getPaymentID2));
                            senderText.setText(getSender);
                            recipientText.setText(getRecipient);
                            feeText.setText(Double.toString(5));
                            searchText.setText("");
                            found = 3;
                            find = true;
                            break;
                        }
                        else if (find == true && checkPaid == 1) { 
                            find = false;                                
                            found = 2;                           
                            break; 
                        }
                    }         
                }                
                
                switch (found) {
                    case 0:
                        JOptionPane.showMessageDialog(this, "There is no result of payment: " + searchIDs);
                        clear();
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(this, "Please type something to search !");
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(this, "There is no result of payment: " + searchIDs);                    
                        clear();
                        break;
                    default:
                        break;
                }
            }
            catch (HeadlessException | NumberFormatException e) {
                System.out.println("ERROR :" + e);
            }            
        }
    } 

}
