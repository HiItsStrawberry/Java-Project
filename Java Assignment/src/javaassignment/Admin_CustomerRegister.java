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
import static javaassignment.JavaAssignment.contactValidation;
import static javaassignment.JavaAssignment.emailValidation;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Admin_CustomerRegister extends JFrame implements ActionListener {
    
    private JButton home, modify, order, payment, register; 
    private JLabel username, password, email, contact;
    private JTextField usernameText, emailText, contactText;
    private JPasswordField passwordText;

    public Admin_CustomerRegister() {
        setVisible(false);
        setSize(700,500);
        setTitle("Managing Staff_Customer Register Page");
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
        home = new JButton("Customer Page");
        modify = new JButton("Modification Page");    
        order = new JButton("Create Order Page");
        payment = new JButton("Payment Page");

        //TOP PANEL
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)

        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)

        topPanel.add(home);
        topPanel.add(modify);   
        topPanel.add(order);
        topPanel.add(payment);
        this.add(topPanel, BorderLayout.PAGE_START); 
        
        home.addActionListener(this);
        modify.addActionListener(this);       
        order.addActionListener(this);
        payment.addActionListener(this);
    }      
    
    private void middlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(null);
        this.add(middlePanel, BorderLayout.CENTER);

     
        username = new JLabel("Username:");
        username.setFont(fontFormat);
        password = new JLabel("Password:");
        password.setFont(fontFormat);                
        email = new JLabel("Email:");
        email.setFont(fontFormat);
        contact = new JLabel("Contact:");
        contact.setFont(fontFormat);

        
        usernameText = new JTextField();
        passwordText = new JPasswordField();
        emailText = new JTextField();
        contactText = new JTextField();
        
        register = new JButton("Register");
              
        username.setBounds(167, 32, 100, 50);
        password.setBounds(167, 72, 100, 50);
        email.setBounds(167, 152, 100, 50);
        contact.setBounds(167, 192, 100, 50);

        usernameText.setBounds(308, 42, width, height);
        passwordText.setBounds(308, 82, width, height);
        emailText.setBounds(308, 162, width, height);
        contactText.setBounds(308, 202, width, height);  
        
        register.setBounds(407, 260, 120, height);
        
        
        middlePanel.add(username);      middlePanel.add(usernameText);
        middlePanel.add(password);      middlePanel.add(passwordText);
        middlePanel.add(email);         middlePanel.add(emailText);
        middlePanel.add(contact);       middlePanel.add(contactText);
        
        middlePanel.add(register); 
        
        register.addActionListener(this);
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
        usernameText.setText("");
        passwordText.setText("");
        emailText.setText("");
        contactText.setText("");       
    }
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            setVisible(false);
            Admin_CustomerHome ac = new Admin_CustomerHome();
            ac.setVisible(true);
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
            setVisible(false);
            JavaAssignment.customerPayment.setVisible(true);              
        }
        else {
            boolean flag = false;
            if(usernameText.getText().isEmpty() || passwordText.getPassword().equals("") || emailText.getText().isEmpty() || contactText.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill in all the empty fields!");
            }
            else if(!emailValidation(emailText.getText().trim())){
                JOptionPane.showMessageDialog(this, "Email format must be example@gmail/outlook.com");
            }
            else if(!contactValidation(contactText.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Contact must be 012-(7 digits) or 012-(8 digits)");
            }
            else{           
                for(int i=0; i<JavaAssignment.allAccount.size(); ++i){
                    
                    Account a = JavaAssignment.allAccount.get(i);
                    
                    if(usernameText.getText().trim().equals(a.getUsername())){
                        JOptionPane.showMessageDialog(this, "Username already exist! Please try again!");
                        usernameText.setText("");
                        flag=false;
                        break;
                    }
                    else if(emailText.getText().trim().equals(a.getEmail())){
                        JOptionPane.showMessageDialog(this, "Email already exist! Please try again!"); 
                        flag=false;
                        break;
                    }
                    else{
                        flag=true;
                    }                   
                }            
            }
            
            if(flag == true){
                String line1 = usernameText.getText().trim();
                String line2 = new String(passwordText.getPassword()).trim();
                String line3 = emailText.getText().trim();
                String line4 = contactText.getText().trim();
                int line5 = 3;
                Customer customer = new Customer(line1,line2,line3,line4,line5);
                JavaAssignment.allAccount.add(customer);
                

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
                    JOptionPane.showMessageDialog(null, "Register successfully!");
                    JavaAssignment.customerRegister.clear();                        
                }
                catch(HeadlessException | FileNotFoundException e){
                    System.out.println("ERROR :"+ e);
                }  
            }
        }
    }
}
