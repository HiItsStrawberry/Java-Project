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

public class Admin_Home extends JFrame implements ActionListener {
    private JButton deliveryStaff, customer, viewReceipt, assignOrder, report, logout;
    private JLabel username, role, logo, space, details;
    private JTextField usernameText, roleText;
    private JPanel feedbackPanel, rightPanel;
    private JScrollPane scrollPanel;
        
    public Admin_Home(){
        setVisible(false);
        setSize(700,500);
        setTitle("Managing Staff_Home Page");
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
        deliveryStaff = new JButton("Delivery Staff");
        customer = new JButton("Customer");
        viewReceipt = new JButton("View Receipt");
        assignOrder = new JButton("Assign Order");
        report = new JButton("Report");
        space = new JLabel("");
        logout = new JButton("Logout");
               
        //TOP PANEL
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)
        
        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)
    
        space.setPreferredSize(new Dimension(53, 0));
        space.setBackground(orange);
        space.setForeground(orange);
        
        topPanel.add(deliveryStaff);
        topPanel.add(customer);
        topPanel.add(viewReceipt);
        topPanel.add(assignOrder);
        topPanel.add(report);
        topPanel.add(space);
        topPanel.add(logout);
        this.add(topPanel, BorderLayout.PAGE_START);     
        
        deliveryStaff.addActionListener(this);
        customer.addActionListener(this);
        viewReceipt.addActionListener(this);
        assignOrder.addActionListener(this);
        report.addActionListener(this);
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
        username = new JLabel("Username:");
        role = new JLabel("Role:");
        usernameText = new JTextField();                usernameText.setEditable(false);
        roleText = new JTextField("MANAGING STAFF");    roleText.setEditable(false);
        
        username.setFont(fontFormat);role.setFont(fontFormat);
        
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/avatar3.png").getImage().getScaledInstance(190, 170, Image.SCALE_DEFAULT));
        logo.setIcon(imageIcon);  
        logo.setBounds(55,35,190,170);
//        Border border = BorderFactory.createLineBorder(Color.BLACK);
//        logo.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        
        username.setBounds(25,215,85,height);  
        usernameText.setBounds(105,215,170,height);
        usernameText.setFont(new Font("Serif", Font.BOLD, 12));
        usernameText.setHorizontalAlignment(JTextField.CENTER);
        
        role.setBounds(25,265,85,height);  
        roleText.setBounds(105,265,170,height);
        roleText.setFont(new Font("Serif", Font.BOLD, 12));
        roleText.setHorizontalAlignment(JTextField.CENTER);
        
        leftPanel.add(logo);
        leftPanel.add(username);leftPanel.add(usernameText);
        leftPanel.add(role);leftPanel.add(roleText);    

    }

    private void rightPanel(){
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(400,500));
        this.add(rightPanel, BorderLayout.LINE_END);
        adminFnR();    
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

    public JTextField getTheName() {
        return usernameText;
    }

    private void adminFnR() {
        JavaAssignment.orderData();
        JavaAssignment.accountData();
        JavaAssignment.availableStaff();
        feedbackPanel = new JPanel();
        feedbackPanel.setLayout(null);
        feedbackPanel.setLayout(new BoxLayout(feedbackPanel, BoxLayout.Y_AXIS));
        feedbackPanel.setBorder(LineBorder.createGrayLineBorder());
        
        scrollPanel = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(350,346));
        scrollPanel.setViewportView(feedbackPanel);
        
        if (JavaAssignment.allOrder.isEmpty()) {
            details = new JLabel("<html><div style='width:345; height:341; color:yellow; background: #1abc9c; text-align:center;'><h1>There is no order</h1></div></html>"); 
            feedbackPanel.add(details);             
        }
        else {
            for(int i = 0; i < JavaAssignment.allOrder.size(); i++) {           
                int orderID = JavaAssignment.allOrder.get(i).getOrderID();
                String date = JavaAssignment.allOrder.get(i).getDate();
                String status = JavaAssignment.allOrder.get(i).getStatus();
                String rating = JavaAssignment.allOrder.get(i).getRating();
                String feedback = JavaAssignment.allOrder.get(i).getFeedback();
                String dStaff = JavaAssignment.allOrder.get(i).getDeliveryStaff();
                String recipient = JavaAssignment.allOrder.get(i).getRecipient();

                details = new JLabel("<html><div style='width:345; padding:10px; color:#2c3e50; background: #1abc9c;'>"
                                        +"<h3 style='margin:1px;'>Order ID: <span style='color:#ffffff'>"+orderID+"</span></h3>"
                                        +"<h3 style='margin:1px;'>Date: <span style='color:#ffffff'>"+date+"</span><</h3>"
                                        +"<h3 style='margin:1px;'>Status: <span style='color:yellow'>"+status+"</span><</h3>"
                                        +"<h3 style='margin:1px;'>Rating: <span style='color:yellow'>"+rating+"</span><</h3>"
                                        +"<h3 style='margin:1px;'>Feedback: <span style='color:yellow'>"+feedback+"</span><</h3>"
                                        +"<h3 style='margin:1px;'>Delivery Staff: <span style='color:yellow'>"+dStaff+"</span><</h3>"
                                        +"<h3 style='margin:1px;'>Recipient: <span style='color:#ffffff'>"+recipient+"</span><</h3>"
                                        +"</div><br><br></html>");
                feedbackPanel.add(details);          
            }
            details = new JLabel("<html><div style='width:330; color:yellow; background: #1abc9c; text-align:center;'><h1>End Result of Order</h1></div></html>"); 
            feedbackPanel.add(details); 
        }
        rightPanel.add(scrollPanel);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deliveryStaff) {
            setVisible(false);
            Admin_DeliveryHome ad = new Admin_DeliveryHome();
            ad.setVisible(true);
        }
        else if (ae.getSource() == customer) {
            setVisible(false);
            Admin_CustomerHome ac = new Admin_CustomerHome();
            ac.setVisible(true);           
        }
        else if (ae.getSource() == viewReceipt) {
            setVisible(false);
            Admin_Receipt ar = new Admin_Receipt();
            ar.setVisible(true);
        }
        else if (ae.getSource() == assignOrder)  {
            setVisible(false);
            Admin_DeliveryOrder ad = new Admin_DeliveryOrder();
            ad.setVisible(true);
        }
        else if (ae.getSource() == report) {
            setVisible(false);
            JavaAssignment.adminReport.setVisible(true);
        }
        else if (ae.getSource() == logout) {        
            setVisible(false);
            JavaAssignment.login.setVisible(true);
            JavaAssignment.onLogin = null;
            JOptionPane.showMessageDialog(this, "Logout Successfully");
        }
    }
}
