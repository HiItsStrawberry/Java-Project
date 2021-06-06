package javaassignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javaassignment.JavaAssignment.orange;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class Admin_Receipt extends JFrame implements ActionListener {

    private JButton home;
    private JLabel details, br;
    private JPanel topPanel, middlePanel, receiptPanel, middlePanel2, bottomPanel, space1, space2;
    private JScrollPane scrollPanel;
    
    public Admin_Receipt() {
        setVisible(false);
        setSize(new Dimension(700,500));          
        setTitle("Managing Staff_Receipt Page");     
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(2,10)); 
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        space1 = new JPanel();
        space2 = new JPanel();
        space1.setPreferredSize(new Dimension(100,0));
        space2.setPreferredSize(new Dimension(100,0));  
        this.add(space1, BorderLayout.EAST);
        this.add(space2, BorderLayout.WEST);
        
        topPanel();
        middlePanel();
        bottomPanel();

        validate();
        repaint();        
    }
    
    
    private void topPanel() {
        home = new JButton("Home Page");
               
        //TOP PANEL
        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)
        
        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)
        
        topPanel.add(home);
        this.add(topPanel, BorderLayout.PAGE_START);     
        
        home.addActionListener(this);
    }    
    
    private void middlePanel() {
        middlePanel = new JPanel();
        this.add(middlePanel, BorderLayout.CENTER);
        middlePanel2();
    }    
    
    private void middlePanel2() {
        middlePanel2 = new JPanel();
        middlePanel2.setPreferredSize(middlePanel.getMaximumSize());
        middlePanel.add(middlePanel2, BorderLayout.CENTER);  
        allCustomers();
    }
        
    private void bottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout()); 
        bottomPanel.setPreferredSize(new Dimension(0,50));   
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(orange);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); //(top, left, bottom, right)
        this.add(bottomPanel, BorderLayout.PAGE_END);               
    }      
    
    private void allCustomers() {
        receiptPanel = new JPanel();
        receiptPanel.setLayout(null);
        receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
        receiptPanel.setBorder(LineBorder.createGrayLineBorder());
       
        scrollPanel = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(490,336));
        scrollPanel.setViewportView(receiptPanel);

        if(JavaAssignment.allReceipt.isEmpty()) {
            details = new JLabel("<html><div style='width:485; height:331; padding:10px; color:yellow; background: #1abc9c; text-align:center;'><h1>There is no receipt</h1></div></html>"); 
            receiptPanel.add(details);                 
        }   
        else {
            for(int i = 0; i < JavaAssignment.allReceipt.size(); i++) {
                String getReceiptID = JavaAssignment.allReceipt.get(i).getReceiptID();
                int getPaymentID = JavaAssignment.allReceipt.get(i).getPaymentID().getPaymentID();
                String getDate = JavaAssignment.allReceipt.get(i).getDate().getDate();
                String getSender = JavaAssignment.allReceipt.get(i).getSender().getSender().getSender();
                String getRecipient = JavaAssignment.allReceipt.get(i).getRecipient().getRecipient().getRecipient();
                double getFee =  JavaAssignment.allReceipt.get(i).getFee().getFee();

                details = new JLabel("<html><div style='width:485; padding:10px; color:#2c3e50; background: #1abc9c;'>"
                                        +"<h3 style='margin:2px; font-size:20px;'><span style='color:yellow'>YNQ SERVER COURIER SYSTEM</span></h3>"
                                        +"<h3 style='margin:2px; font-size:12px;'>Receipt ID: <span style='color:#ffffff'>"+getReceiptID+"</span></h3>"
                                        +"<h3 style='margin:2px; font-size:12px;'>Payment ID: <span style='color:#ffffff'>"+getPaymentID+"</span><</h3>"
                                        +"<h3 style='margin:2px; font-size:12px;'>Paid Date: <span style='color:#ffffff'>"+getDate+"</span><</h3>"
                                        +"<h3 style='margin:2px; font-size:12px;'>Payer(Sender): <span style='color:#ffffff'>"+getSender+"</span><</h3>"
                                        +"<h3 style='margin:2px; font-size:12px;'>Recipient: <span style='color:#ffffff'>"+getRecipient+"</span><</h3>"        
                                        +"<h3 style='margin:2px; font-size:12px;'>Shipping Fee: <span style='color:#ffffff'>RM "+getFee+"</span><</h3>"        
                                        +"</div></html>");     
                br = new JLabel("<html><br><br></html>");   
                receiptPanel.add(details);
                receiptPanel.add(br);                       
            }
            details = new JLabel("<html><div style='width:485; color:yellow; background: #1abc9c; text-align:center;'><h1>End Result of Receipt</h1></div></html>"); 
            receiptPanel.add(details);            
        }
        middlePanel2.add(scrollPanel);        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            setVisible(false);
            Admin_Home ah = new Admin_Home();
            String getName = JavaAssignment.onLogin.getUsername();
            String finalName = getName.substring(0, 1).toUpperCase() + getName.substring(1);
            ah.getTheName().setText(finalName);
            ah.setVisible(true);    
        }       
    } 
}
