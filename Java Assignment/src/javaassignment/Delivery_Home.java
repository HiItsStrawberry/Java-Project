package javaassignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javaassignment.JavaAssignment.fontFormat;
import static javaassignment.JavaAssignment.height;
import static javaassignment.JavaAssignment.orange;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class Delivery_Home extends JFrame implements ActionListener{
    private JButton view, receipt, modify, edit, logout, email;
    private JLabel username, role, logo, space, details, br;
    private JTextField usernameText, roleText;
    private JPanel feedbackPanel, rightPanel;
    private JScrollPane scrollPanel;
    
    public Delivery_Home(){
        setVisible(false);
        setSize(700,500);
        setTitle("Delivery Staff_Home Page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(2,10)); //(gap)
        
        topPanel();
        secondPanel();
        bottomPanel();
        
        validate();
        repaint();
    }
    
    private void topPanel() {
        view = new JButton("View Current Order");
        modify = new JButton("Complete Order");
        receipt = new JButton("View Receipt");
        email = new JButton("Send Email");
        logout = new JButton("Logout");
        space = new JLabel("");
               
        //TOP PANEL
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)
        
        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)
    
        space.setPreferredSize(new Dimension(79, 0));
        space.setBackground(orange);
        space.setForeground(orange);
        
        topPanel.add(view);
        topPanel.add(modify);
        topPanel.add(receipt);
        topPanel.add(email);
        topPanel.add(space);
        topPanel.add(logout);
        this.add(topPanel, BorderLayout.PAGE_START);     
        
        view.addActionListener(this);
        modify.addActionListener(this);
        receipt.addActionListener(this);
        email.addActionListener(this);
        logout.addActionListener(this);
    }
    
    private void secondPanel() {
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(null);
        this.add(secondPanel, BorderLayout.CENTER);
        leftPanel();
        rightPanel();
    }
    
    private void leftPanel(){
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(280,500));
        this.add(leftPanel, BorderLayout.LINE_START);
        leftPanel.setLayout(null);
             
        logo = new JLabel();
        edit = new JButton();
        username = new JLabel("Username:");
        role = new JLabel("Role:");
        usernameText = new JTextField();                usernameText.setEditable(false);
        roleText = new JTextField("DELIVERY STAFF");    roleText.setEditable(false);
        
        username.setFont(fontFormat);
        role.setFont(fontFormat);
        
        
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/avatar3.png").getImage().getScaledInstance(190, 170, Image.SCALE_DEFAULT));
        logo.setIcon(imageIcon);  
        logo.setBounds(55,35,190,170);
//        Border border = BorderFactory.createLineBorder(Color.BLACK);
//        logo.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        ImageIcon editIcon = new ImageIcon(new ImageIcon("images/editLogo.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        edit.setIcon(editIcon);  
        edit.setBounds(242,35,30,30);
        
        username.setBounds(25,215,85,height);  
        usernameText.setBounds(105,215,170,height);
        usernameText.setFont(new Font("Serif", Font.BOLD, 12));
        usernameText.setHorizontalAlignment(JTextField.CENTER);
        
        role.setBounds(25,265,85,height);  
        roleText.setBounds(105,265,170,height);
        roleText.setFont(new Font("Serif", Font.BOLD, 12));
        roleText.setHorizontalAlignment(JTextField.CENTER);
        
        leftPanel.add(logo);
        leftPanel.add(edit);
        leftPanel.add(username);leftPanel.add(usernameText);
        leftPanel.add(role);leftPanel.add(roleText);  
        
        edit.addActionListener(this);

    }

    private void rightPanel(){
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(400,500));
        this.add(rightPanel, BorderLayout.LINE_END);
        ownRatingNFeedback();
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
    
    private void ownRatingNFeedback() {
        feedbackPanel = new JPanel();
        feedbackPanel.setLayout(null);
        feedbackPanel.setLayout(new BoxLayout(feedbackPanel, BoxLayout.Y_AXIS));
        feedbackPanel.setBorder(LineBorder.createGrayLineBorder());
        
        scrollPanel = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(350,346));
        scrollPanel.setViewportView(feedbackPanel);  
        
        if(JavaAssignment.onLogin != null) {
            JavaAssignment.onLogin.getMyOrder().clear();
            JavaAssignment.orderData();
            if(JavaAssignment.onLogin.getMyOrder().isEmpty()) {
                details = new JLabel("<html><div style='width:345; height:341; color:yellow; background: #1abc9c; text-align:center;'><h1>There is no order</h1></div></html>");
                feedbackPanel.add(details);
            }
            else {

                for(int i = 0; i < JavaAssignment.onLogin.getMyOrder().size(); ++i) {                  
                    if(JavaAssignment.onLogin.getMyOrder().get(i).getStatus().equals("Delivered")) {
                        int orderID = JavaAssignment.onLogin.getMyOrder().get(i).getOrderID();       
                        String recipient = JavaAssignment.onLogin.getMyOrder().get(i).getRecipient();
                        String rating = JavaAssignment.onLogin.getMyOrder().get(i).getRating();
                        String feedback = JavaAssignment.onLogin.getMyOrder().get(i).getFeedback();

                        details = new JLabel("<html><div style='width:266px; padding:5px; color:#2c3e50; background: #1abc9c;'>"
                                                +"<h3 style='margin:1px;'>Order ID: <span style='color:#ffffff'>"+orderID+"</span></h3>"
                                                +"<h3 style='margin:1px;'>Recipient: <span style='color:yellow'>"+recipient+"</span><</h3>"     
                                                +"<h3 style='margin:1px;'>Rating: <span style='color:yellow'>"+rating+"</span><</h3>"
                                                +"<h3 style='margin:1px;'>Feedback: <span style='color:yellow'>"+feedback+"</span><</h3>"
                                                +"</div></html>");
                        br = new JLabel("<html><br><br></html>");    
                        feedbackPanel.add(details);                
                        feedbackPanel.add(br);                           
                    }
                }
                details = new JLabel("<html><div style='width:266px; color:yellow; background: #1abc9c; text-align:center;'><h1>There is no more order has been delivered</h1></div></html>"); 
                feedbackPanel.add(details);
            }
        }
        rightPanel.add(scrollPanel);   
    }
    
    public JTextField getTheName() {
        return usernameText;
    }

    
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == view) {
            Delivery_ViewOrder dv = new Delivery_ViewOrder();
            dv.setVisible(true);
        }
        else if (ae.getSource() == modify) {     
            for(int i = 0; i < JavaAssignment.onLogin.getMyOrder().size(); ++i) {
                if(JavaAssignment.onLogin.getMyOrder().get(i).getStatus().equals("Delivering")) {
                    int getOrderID = JavaAssignment.onLogin.getMyOrder().get(i).getOrderID();
                    String getDeliveryStaff = JavaAssignment.onLogin.getUsername();
                    String getRecipient = JavaAssignment.onLogin.getMyOrder().get(i).getRecipient();
                    String getAddress = JavaAssignment.onLogin.getMyOrder().get(i).getAddress2();
                    String getStatus = JavaAssignment.onLogin.getMyOrder().get(i).getStatus();
                    
                    JavaAssignment.deliveryModifyOrder.getTheID().setText(Integer.toString(getOrderID));
                    JavaAssignment.deliveryModifyOrder.getTheDelivery().setText(getDeliveryStaff);
                    JavaAssignment.deliveryModifyOrder.getTheRecipient().setText(getRecipient);
                    JavaAssignment.deliveryModifyOrder.getTheAddress().setText(getAddress);
                    JavaAssignment.deliveryModifyOrder.getTheStatus().setText(getStatus);
                }
            }
            setVisible(false);
            JavaAssignment.deliveryModifyOrder.setVisible(true);  
        }
        else if (ae.getSource() == email) {
            setVisible(false);
            Delivery_Email de = new Delivery_Email();
            de.setVisible(true);
        }
        else if (ae.getSource() == logout) {
            setVisible(false);
            JavaAssignment.login.setVisible(true);
            JavaAssignment.onLogin = null;
            JOptionPane.showMessageDialog(this, "Logout Successfully");
        }
        else if (ae.getSource() == receipt) {
            Delivery_ViewReceipt dv = new Delivery_ViewReceipt();
            dv.setVisible(true);     
        }        
        else {
            setVisible(false);
            JavaAssignment.deliveryModifyProfile.setVisible(true);
            JavaAssignment.deliveryModifyProfile.getTheName().setText(JavaAssignment.onLogin.getUsername());
            JavaAssignment.deliveryModifyProfile.getThePassword().setText(JavaAssignment.onLogin.getPassword());
            JavaAssignment.deliveryModifyProfile.getTheEmail().setText(JavaAssignment.onLogin.getEmail());
            JavaAssignment.deliveryModifyProfile.getTheContact().setText(JavaAssignment.onLogin.getContact());            
        }
    }
}