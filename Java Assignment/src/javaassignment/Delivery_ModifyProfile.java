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

public class Delivery_ModifyProfile extends JFrame implements ActionListener {
    
    private JButton home, update; 
    private JLabel username, password, email, contact;
    private JTextField usernameText, emailText, contactText;
    private JPasswordField passwordText;

    public Delivery_ModifyProfile() {
        setVisible(false);
        setSize(700,500);
        setTitle("Delivery Staff_Modify Profile Page");
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
        home = new JButton("Home Page");  
        //TOP PANEL
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)

        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)

        topPanel.add(home);
        this.add(topPanel, BorderLayout.PAGE_START);         
        home.addActionListener(this);
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

        
        usernameText = new JTextField(); usernameText.setEditable(false);
        passwordText = new JPasswordField();
        emailText = new JTextField();
        contactText = new JTextField();
        
        update = new JButton("Update");
              
        username.setBounds(167, 32, 100, 50);
        password.setBounds(167, 72, 100, 50);
        email.setBounds(167, 152, 100, 50);
        contact.setBounds(167, 192, 100, 50);

        usernameText.setBounds(308, 42, width, height);
        passwordText.setBounds(308, 82, width, height);
        emailText.setBounds(308, 162, width, height);
        contactText.setBounds(308, 202, width, height);  
        
        update.setBounds(407, 260, 120, height);
        
        
        middlePanel.add(username);      middlePanel.add(usernameText);
        middlePanel.add(password);      middlePanel.add(passwordText);
        middlePanel.add(email);         middlePanel.add(emailText);
        middlePanel.add(contact);       middlePanel.add(contactText);
        
        middlePanel.add(update); 
        
        update.addActionListener(this);
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
    
    public JPasswordField getThePassword() {
        return passwordText;
    }  
    
    public JTextField getTheEmail() {
        return emailText;
    }  
        
    public JTextField getTheContact() {
        return contactText;
    }      
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            setVisible(false);
            Delivery_Home dh = new Delivery_Home();
            String getName = JavaAssignment.onLogin.getUsername();
            String finalName = getName.substring(0, 1).toUpperCase() + getName.substring(1);
            dh.getTheName().setText(finalName);
            dh.setVisible(true);            
        }
        else {
            try {
                String getName = usernameText.getText();
                String getPassword = new String(passwordText.getPassword()).trim();
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
                            int response = JOptionPane.showConfirmDialog(this, "Are you sure want to modify your profile ?", null, JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                JavaAssignment.allAccount.get(i).setPassword(getPassword);
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
                    JOptionPane.showMessageDialog(this, "Your profile has been modified !");                    
                }                
                
            }
            catch (HeadlessException | FileNotFoundException e) {
                System.out.println("ERROR :" + e); 
            }            
        }
    }    
}
