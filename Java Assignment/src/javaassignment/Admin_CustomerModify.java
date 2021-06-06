package javaassignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin_CustomerModify extends JFrame implements ActionListener {

    private JButton home, register, order, payment, search, update, delete; 
    private JLabel username, password, email, contact;
    private JTextField searchText, usernameText, passwordText, emailText, contactText;
    
    public Admin_CustomerModify() {
        setVisible(false);
        setSize(700,500);
        setTitle("Managing Staff_Customer Modify Page");
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
        register = new JButton("Registration Page");    
        order = new JButton("Create Order Page");
        payment = new JButton("Payment Page");

        //TOP PANEL
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)

        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)

        topPanel.add(home);
        topPanel.add(register);   
        topPanel.add(order);
        topPanel.add(payment);
        this.add(topPanel, BorderLayout.PAGE_START); 
        
        home.addActionListener(this);
        register.addActionListener(this);       
        order.addActionListener(this);
        payment.addActionListener(this);
    }      
    
    private void middlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(null);
//        middlePanel.setBackground(Color.yellow);
//        middlePanel.setBorder(BorderFactory.createTitledBorder(null, "Sender Infomration", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, titleBorderFont));     
        this.add(middlePanel, BorderLayout.CENTER);

     
        username = new JLabel("Username:");
        username.setFont(fontFormat);
        password = new JLabel("Password:");
        password.setFont(fontFormat);
        email = new JLabel("Email:");
        email.setFont(fontFormat);
        contact = new JLabel("Contact:");
        contact.setFont(fontFormat);

        
        searchText = new JTextField();
        usernameText = new JTextField();    usernameText.setEditable(false);
        passwordText = new JTextField();    passwordText.setEditable(false);
        emailText = new JTextField();
        contactText = new JTextField();
        
        search = new JButton("");
        update = new JButton("Update");
        delete = new JButton("Delete");
                
        username.setBounds(167, 72, 100, 50);
        password.setBounds(167, 112, 100, 50);
        email.setBounds(167, 152, 100, 50);
        contact.setBounds(167, 192, 100, 50);

        searchText.setBounds(208, 32, width, height);
        usernameText.setBounds(308, 82, width, height);
        passwordText.setBounds(308, 122, width, height);
        emailText.setBounds(308, 162, width, height);
        contactText.setBounds(308, 202, width, height);  
        
        
        ImageIcon searchLogo = new ImageIcon(new ImageIcon("images/searchLogo.png").getImage().getScaledInstance(30, 29, Image.SCALE_DEFAULT));
        search.setIcon(searchLogo); 
        search.setBounds(438, 32, 30, 29);
        update.setBounds(337, 260, 90, height);
        delete.setBounds(437, 260, 90, height);
        

        middlePanel.add(searchText);    middlePanel.add(search);
        
        middlePanel.add(username);      middlePanel.add(usernameText);
        middlePanel.add(password);      middlePanel.add(passwordText);
        middlePanel.add(email);         middlePanel.add(emailText);
        middlePanel.add(contact);       middlePanel.add(contactText);
        
        middlePanel.add(update); middlePanel.add(delete);
        
        search.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);
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
        searchText.setText("");
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
        else if (ae.getSource() == register) {
            setVisible(false);
            JavaAssignment.customerRegister.setVisible(true);      
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
        else if (ae.getSource() == search) {           
            try {
                String searchName = searchText.getText().trim();
                boolean find = false;
                int found = 0;
                for(int i = 0; i < JavaAssignment.allAccount.size(); ++i) {
                    
                    String name = JavaAssignment.allAccount.get(i).getUsername();
                    int checkRole = JavaAssignment.allAccount.get(i).getRole();
                    
                    if (searchName.isEmpty()) {                  
                        found = 1;
                        break;
                    }
                    else if (searchName.equals("admin")) {                   
                        found = 2;
                        break;                        
                    }
                    else if (!searchName.isEmpty() && !searchName.equals(name)) {                     
                        System.out.println(find);
                        found = 2;                          
                    }
                    else if (searchName.equals(name)) {
                        find = true;
                        if (find == true) {
                            if(checkRole == 3) {
                                System.out.println(find);  
                                String getName = JavaAssignment.allAccount.get(i).getUsername();
                                String getPassword = JavaAssignment.allAccount.get(i).getPassword();
                                String getEmail = JavaAssignment.allAccount.get(i).getEmail();
                                String getContact = JavaAssignment.allAccount.get(i).getContact();

                                usernameText.setText(getName);
                                passwordText.setText(getPassword);
                                emailText.setText(getEmail);
                                contactText.setText(getContact);   
                                find = true;
                                found = 0;
                                break;
                            }
                            else {
                                find = false; 
                                found = 2;
                                break;
                            }                            
                        }
                    }
                } 
                if (found == 1) {
                    JOptionPane.showMessageDialog(this, "Please type something to search !");
                }   
                else if(found == 2) {
                    JOptionPane.showMessageDialog(this, "There is no result of customer: " + searchName);
                    clear();
                }
            }
            catch (HeadlessException e) {
                System.out.println("ERROR :" + e);
            }
        }
        else if (ae.getSource() == update) {
            try {
                String getName = usernameText.getText();
                String getEmail = emailText.getText().trim();
                String getContact = contactText.getText().trim();
                boolean isUpdate = false;
                
                for(int i = 0; i < JavaAssignment.allAccount.size(); ++i) {
                    String name = JavaAssignment.allAccount.get(i).getUsername();
                    
                    if (getName.equals(name)) {
                        if (!emailValidation(getEmail)) {
                            JOptionPane.showMessageDialog(this, "Email format must be example@gmail/outlook.com");
                            isUpdate = false;
                        }
                        else if (!contactValidation(getContact)) {
                            JOptionPane.showMessageDialog(this, "Contact must be 012-(7 digits) or 012-(8 digits)");
                            isUpdate = false;                            
                        }
                        else {
                            int response = JOptionPane.showConfirmDialog(this, "Are you sure want to modify customer: " + name +" ?", null, JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                JavaAssignment.allAccount.get(i).setEmail(getEmail);
                                JavaAssignment.allAccount.get(i).setContact(getContact);
                                isUpdate = true;
                            }                              
                        }                   
                    }
                }
                
                if (isUpdate == true) {
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
                    JOptionPane.showMessageDialog(this, "Customer: "+getName+" has been modified !");
                    clear();                    
                }                        
            }
            catch (HeadlessException | FileNotFoundException e) {
                System.out.println("ERROR :" + e); 
            }
        }
        else {
            try {
                String getName = usernameText.getText();
                boolean isDelete = false;
                for(int i = 0; i < JavaAssignment.allAccount.size(); ++i) {
                    String name = JavaAssignment.allAccount.get(i).getUsername();                   
                    if(getName.equals(name)) {
                        int response = JOptionPane.showConfirmDialog(this, "Are you sure want to delete customer: " + name +" ?", null, JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) {
                            JavaAssignment.allAccount.remove(i);   
                            isDelete = true;
                        }
                    }                   
                }
                
                if (isDelete == true) {
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
                    JOptionPane.showMessageDialog(this, "Customer: "+getName+" has been deleted !");
                    clear();                                        
                }
            }
            catch (HeadlessException | FileNotFoundException e) {
               System.out.println("ERROR :" + e); 
            }               
        }
    }  
}
