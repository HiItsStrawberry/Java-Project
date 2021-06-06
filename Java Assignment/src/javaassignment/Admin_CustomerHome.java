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
import static javaassignment.JavaAssignment.orange;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class Admin_CustomerHome extends JFrame implements ActionListener {

    private JButton main, register, modify, order, payment;
    private JLabel space, details, br, message;
    private JPanel topPanel, middlePanel, customerPanel, middlePanel2, bottomPanel, space1, space2;
    private JScrollPane scrollPanel;
    
    public Admin_CustomerHome() {
        setVisible(false);
        setSize(new Dimension(700,500));          
        setTitle("Managing Staff_Customer Page");     
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
        main = new JButton("Home Page");
        register = new JButton("Registration Page");
        modify = new JButton("Modification Page");
        order = new JButton("Create Order Page");
        payment = new JButton("Payment Page");
        space = new JLabel("");
               
        //TOP PANEL
        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)
        
        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)
    
        space.setPreferredSize(new Dimension(43, 0));
        space.setBackground(orange);
        space.setForeground(orange);
        
        topPanel.add(main);
        topPanel.add(register);
        topPanel.add(modify);
        topPanel.add(order);
        topPanel.add(payment);
        topPanel.add(space);
        this.add(topPanel, BorderLayout.PAGE_START);     
        
        main.addActionListener(this);
        register.addActionListener(this);
        modify.addActionListener(this);
        order.addActionListener(this);
        payment.addActionListener(this);
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
        JavaAssignment.accountData();
        int total = JavaAssignment.allAccount.size();
        JButton[] delete = null;
        delete = new JButton[total];
        customerPanel = new JPanel();
        customerPanel.setLayout(null);
        customerPanel.setLayout(new BoxLayout(customerPanel, BoxLayout.Y_AXIS));
        customerPanel.setBorder(LineBorder.createGrayLineBorder());
       
        scrollPanel = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(490,336));
        scrollPanel.setViewportView(customerPanel);
        
        for(int i = 0; i < total; i++) {
            if(JavaAssignment.allAccount.get(i).getRole() == 3) {
                String getName = JavaAssignment.allAccount.get(i).getUsername();
                String username = getName.substring(0, 1).toUpperCase() + getName.substring(1);
                String password = JavaAssignment.allAccount.get(i).getPassword();
                String email = JavaAssignment.allAccount.get(i).getEmail();
                String contact = JavaAssignment.allAccount.get(i).getContact(); 
                
                details = new JLabel("<html><div style='width:485; padding:10px; color:#2c3e50; background: #1abc9c;'>"
                                        +"<h3 style='margin:2px; font-size:24px;'>Username: <span style='color:yellow'>"+username+"</span></h3>"
                                        +"<h3 style='margin:2px; font-size:12px;'>Password: <span style='color:#ffffff'>"+password+"</span><</h3>"
                                        +"<h3 style='margin:2px; font-size:12px;'>Email Address: <span style='color:#ffffff'>"+email+"</span><</h3>"
                                        +"<h3 style='margin:2px; font-size:12px;'>Contact: <span style='color:#ffffff'>"+contact+"</span><</h3>"
                                        +"</div></html>");     
                br = new JLabel("<html><br><br></html>");
                delete[i] = new JButton();
                delete[i].setText("Remove Customer: "+getName);
                delete[i].addActionListener(this);     
                customerPanel.add(details);
                customerPanel.add(delete[i]);
                customerPanel.add(br);                      
            }          
        }
        details = new JLabel("<html><div style='width:485; color:yellow; background: #1abc9c; text-align:center;'><h1>End Result of Customer</h1></div></html>"); 
        customerPanel.add(details);          
        middlePanel2.add(scrollPanel);        
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
            Admin_CustomerOrder ac = new Admin_CustomerOrder();
            ac.setVisible(true);
        }
        else if (ae.getSource() == payment) {
            setVisible(false);
            JavaAssignment.customerPayment.setVisible(true);
        }         
        else {
            String getUsername = ((JButton) ae.getSource()).getText().split(": ")[1];  
            boolean isDelete = false;
            
            for(int i = 0; i < JavaAssignment.allAccount.size(); ++i) {   
                if(getUsername.equals(JavaAssignment.allAccount.get(i).getUsername())) {
                    message = new JLabel("<html>Are you sure want to remove customer: <span style='color:red; font-size:12px;'>"+getUsername+"</span> from the system ?</html>");
                    int response = JOptionPane.showConfirmDialog(this, message, null, JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        JavaAssignment.allAccount.remove(i);
                        isDelete = true; 
                    }                      
                }  
            }  
            
            if(isDelete == true) {
                try {
                    PrintWriter p = new PrintWriter("Account.txt");
                    for(int i=0; i< JavaAssignment.allAccount.size(); ++i) {
                        Account a = JavaAssignment.allAccount.get(i);
                        p.println("Username:" + a.getUsername());
                        p.println("Password:" + a.getPassword());
                        p.println("Email:" + a.getEmail());
                        p.println("Contact:" + a.getContact());
                        p.println("Role:" + a.getRole());
                        p.println();
                    }
                    p.close();
                    setVisible(false);
                    Admin_CustomerHome ac = new Admin_CustomerHome();
                    ac.setVisible(true);
                    JOptionPane.showMessageDialog(this, "Customer: "+getUsername+" has been removed !"); 
                }
                catch (HeadlessException | FileNotFoundException e) {
                    System.out.println("ERROR :" + e);
                }                                           
            }
        }        
    } 
}
