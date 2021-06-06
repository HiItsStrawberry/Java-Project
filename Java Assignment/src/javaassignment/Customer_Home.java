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

public class Customer_Home extends JFrame implements ActionListener{
    private JButton rateNfeed, edit, logout;
    private JLabel username, role, space, logo, details, br;
    private JTextField usernameText, roleText;
    private JPanel ordersPanel, rightPanel;
    private JScrollPane scrollPanel;
    
    public Customer_Home(){
        setVisible(false);
        setSize(700,500);
        setTitle("Customer_Home Page");
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
        rateNfeed = new JButton("Rating and Feedback Page");
        logout = new JButton("Logout");
        space = new JLabel("");
               
        //TOP PANEL
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)
        
        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)
        
        space.setPreferredSize(new Dimension(397, 0));
        space.setBackground(orange);
        space.setForeground(orange);

        topPanel.add(rateNfeed);
        topPanel.add(space);
        topPanel.add(logout);
        this.add(topPanel, BorderLayout.PAGE_START);     
        
        rateNfeed.addActionListener(this);
        logout.addActionListener(this);
    }
    
    private void secondPanel() {
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(null);
        this.add(secondPanel, BorderLayout.CENTER);
        leftPanel();
        rightPanel();
    }
    
    public void leftPanel(){
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(280,500));
        this.add(leftPanel, BorderLayout.LINE_START);
        leftPanel.setLayout(null);
             
        logo = new JLabel();
        edit = new JButton();
        username = new JLabel("Username:");
        role = new JLabel("Role:");
        usernameText = new JTextField();        usernameText.setEditable(false);
        roleText = new JTextField("CUSTOMER");  roleText.setEditable(false);
        
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

    public void rightPanel(){
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(400,500));
        this.add(rightPanel, BorderLayout.LINE_END);
        customerOrders();
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
    
    
    public void customerOrders() {  
        ordersPanel = new JPanel();
        ordersPanel.setLayout(null);
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        ordersPanel.setBorder(LineBorder.createGrayLineBorder());
        
        scrollPanel = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(350,346));
        scrollPanel.setViewportView(ordersPanel);
      
        if(JavaAssignment.onLogin != null)
        {
            JavaAssignment.onLogin.getCusOrder().clear();
            JavaAssignment.orderData();
            if(JavaAssignment.onLogin.getCusOrder().isEmpty()) {
                details = new JLabel("<html><div style='width:345; height:341; color:yellow; background: #1abc9c; text-align:center;'><h1>There is no order</h1></div></html>"); 
                ordersPanel.add(details); 
            }
            else { 
                for(int i = 0; i < JavaAssignment.onLogin.getCusOrder().size(); i++) {
                    int orderID = JavaAssignment.onLogin.getCusOrder().get(i).getOrderID();
                    String date = JavaAssignment.onLogin.getCusOrder().get(i).getDate();
                    String status = JavaAssignment.onLogin.getCusOrder().get(i).getStatus();
                    String sender = JavaAssignment.onLogin.getCusOrder().get(i).getSender();
                    String contact = JavaAssignment.onLogin.getCusOrder().get(i).getContact1();


                    details = new JLabel("<html><div style='width:266px; padding:5px; color:#2c3e50; background: #1abc9c;'>"
                                            +"<h3 style='margin:1px;'>Order ID: <span style='color:#ffffff'>"+orderID+"</span></h3>"
                                            +"<h3 style='margin:1px;'>Created Date: <span style='color:#ffffff'>"+date+"</span><</h3>"
                                            +"<h3 style='margin:1px;'>Status: <span style='color:yellow'>"+status+"</span><</h3>"
                                            +"<h3 style='margin:1px;'>Seller: <span style='color:yellow'>"+sender+"</span><</h3>"
                                            +"<h3 style='margin:1px;'>Seller's contact: <span style='color:yellow'>"+contact+"</span><</h3>"
                                            +"</div></html>");
                    br = new JLabel("<html><br><br></html>");
                    ordersPanel.add(details);                
                    ordersPanel.add(br);        
                } 
                details = new JLabel("<html><div style='width:266px; color:yellow; background: #1abc9c; text-align:center;'><h1>There is no more order</h1></div></html>"); 
                ordersPanel.add(details);  
            }
        }      
        rightPanel.add(scrollPanel);
    }    
    
    
    public JTextField getTheName() {
        return usernameText;
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rateNfeed) {
            setVisible(false);
            Customer_RateNFeed crn = new Customer_RateNFeed();
            crn.setVisible(true);
        }
        else if (ae.getSource() == logout) {
            setVisible(false);
            JavaAssignment.login.setVisible(true);
            JavaAssignment.onLogin = null;
            JOptionPane.showMessageDialog(this, "Logout Successfully");
        }
        else {
            setVisible(false);
            JavaAssignment.customerModifyProfile.setVisible(true);
            
            JavaAssignment.customerModifyProfile.getTheName().setText(JavaAssignment.onLogin.getUsername());
            JavaAssignment.customerModifyProfile.getThePassword().setText(JavaAssignment.onLogin.getPassword());
            JavaAssignment.customerModifyProfile.getTheEmail().setText(JavaAssignment.onLogin.getEmail());
            JavaAssignment.customerModifyProfile.getTheContact().setText(JavaAssignment.onLogin.getContact());
        }
    }
}