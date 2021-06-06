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
import static javaassignment.JavaAssignment.orange;
import static javaassignment.JavaAssignment.fontFormat;
import static javaassignment.JavaAssignment.width;
import static javaassignment.JavaAssignment.height;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Admin_DeliveryRegister extends JFrame implements ActionListener{
    
    private JButton home, edit, order, register;
    private JLabel username, password, email, contact;
    private JTextField usernameText, emailText, contactText;
    private JPasswordField passwordText;
    
    
    public Admin_DeliveryRegister(){
        setVisible(false);
        setSize(700,500);
        setTitle("Managing Staff_Delivery Staff Register  Page");
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
        home = new JButton("Delivery Staff Page");
        edit = new JButton("Modification Page");
        order = new JButton("Assign Order Page");

        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)
        
        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)
    
        topPanel.add(home);
        topPanel.add(edit);
        topPanel.add(order);
        this.add(topPanel, BorderLayout.PAGE_START);      
        
        home.addActionListener(this);
        edit.addActionListener(this);
        order.addActionListener(this);
    }
   
    private void middlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(null);
//        middlePanel.setBorder(BorderFactory.createTitledBorder(null, "Register Infomration (Delivery Staff)", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, titleBorderFont));
        this.add(middlePanel, BorderLayout.CENTER);
 
        username = new JLabel("Username: ");
        password = new JLabel("Password: ");
        email = new JLabel("Email: ");
        contact = new JLabel("Contact: ");
        
        usernameText = new JTextField();
        passwordText = new JPasswordField();
        emailText = new JTextField();
        contactText = new JTextField();
        
        username.setFont(fontFormat);
        password.setFont(fontFormat);
        email.setFont(fontFormat);
        contact.setFont(fontFormat);
        
        username.setBounds(167,32,85,50);
        usernameText.setBounds(308,42,width,height);       
        password.setBounds(167,72,85,50);
        passwordText.setBounds(308,82,width,height);
        

        
        email.setBounds(167,152,85,50);
        emailText.setBounds(308,162,width,height);   
        contact.setBounds(167,192,85,50);
        contactText.setBounds(308,202,width,height);
        
        register = new JButton("Register");
        register.addActionListener(this);
        register.setBounds(407,260,120,height);
        
        middlePanel.add(register);
        middlePanel.add(username);middlePanel.add(usernameText);
        middlePanel.add(password);middlePanel.add(passwordText);
        middlePanel.add(email);middlePanel.add(emailText);
        middlePanel.add(contact);middlePanel.add(contactText);
    }

    private void bottomPanel() {    
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(0,50));   
        bottomPanel.setLayout(new FlowLayout(2,10,10));
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
            Admin_DeliveryHome ad = new Admin_DeliveryHome();
            ad.setVisible(true);    
        }
        else if (ae.getSource() == edit) {
            setVisible(false);
            JavaAssignment.admin_deliveryModify.setVisible(true);
        } 
        else if (ae.getSource() == order) {
            setVisible(false);
            Admin_DeliveryOrder ad = new Admin_DeliveryOrder();
            ad.setVisible(true);
        } 
        else {
            boolean flag = false;
            String getUsername = usernameText.getText();
            String getPassword = new String(passwordText.getPassword());
            String getEmail = emailText.getText();
            String getContact = contactText.getText();
            
            if(getUsername.isEmpty() || getPassword.isEmpty() ||getEmail.isEmpty() || getContact.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill in all the empty fields!");
            }
            else if(!emailValidation(emailText.getText().trim())){
                JOptionPane.showMessageDialog(this, "Email format must be example@gmail/outlook.com");
            }
            else if(!contactValidation(contactText.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Contact must be 012-(7 digits) or 012-(8 digits)");
            }           
            else{           
                for(int i=0;i<JavaAssignment.allAccount.size();++i){             
                    Account a = JavaAssignment.allAccount.get(i);  
                    if(usernameText.getText().trim().equals(a.getUsername())){
                        JOptionPane.showMessageDialog(this, "Username already exist! Please try again!");
                        usernameText.setText("");
                        flag = false;
                        break;
                    }
                    else if(emailText.getText().trim().equals(a.getEmail())){
                        JOptionPane.showMessageDialog(this, "Email already exist! Please try again!"); 
                        flag = false;
                        break;
                    }
                    else{
                        flag = true;
                    }                   
                }            
            }
            if(flag == true) {
                String line1 = usernameText.getText().trim();
                String line2 = new String(passwordText.getPassword()).trim();
                String line3 = emailText.getText().trim();
                String line4 = contactText.getText().trim();    
                DeliveryStaff dt = new DeliveryStaff(line1,line2,line3,line4,2);          
                JavaAssignment.allAccount.add(dt);
                JavaAssignment.allDeliveryStaff.add(dt);
                
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

                    for(int i = 0; i < JavaAssignment.allDeliveryStaff.size(); ++i) {
                        DeliveryTask dyt = new DeliveryTask(JavaAssignment.allDeliveryStaff.get(i), JavaAssignment.allDeliveryStaff.get(i), 0);
                        JavaAssignment.allDeliveryTask.add(dyt);    
                    }

                    PrintWriter pa = new PrintWriter("DeliveryInfo.txt");
                    for(int i=0; i< JavaAssignment.allDeliveryTask.size(); ++i) {            
                        DeliveryTask dta = JavaAssignment.allDeliveryTask.get(i);
                        pa.println("Username:" + dta.getOwner().getUsername());
                        pa.println("Role:" + dta.getRole().getRole());
                        pa.println("Task:" + dta.getTask());
                        pa.println();        
                    }                                  
                    pa.close();  
                    JavaAssignment.allDeliveryTask.clear();  
                    JavaAssignment.admin_deliveryRegister.clear();
                } 
                catch(HeadlessException | FileNotFoundException ex){
                    System.out.println("Error" + ex);
                }             
            }
        }
    }
}
