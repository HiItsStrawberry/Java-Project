package javaassignment;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static javaassignment.JavaAssignment.allAccount;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javaassignment.JavaAssignment.fontFormat;
import static javaassignment.JavaAssignment.width;
import static javaassignment.JavaAssignment.height;
import javax.swing.JOptionPane;


public class Login extends JFrame implements ActionListener {
    private JButton login;
    private JLabel username, password, logo;
    private JTextField usernameText;
    private JPasswordField passwordText;
    private JCheckBox check;
    
    public Login(){
        setVisible(true);
        setSize(500,500);
        setTitle("Login Page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        this.getRootPane().setDefaultButton(login);
        
        
        mainPanel(); 
        validate();
        repaint();
        
        //CHECKBOX SELECTED EVENT
        check.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if(ie.getStateChange() == ItemEvent.SELECTED){
                    passwordText.setEchoChar((char)0);  
                }
                else{
                    passwordText.setEchoChar('\u2022');  
                }
            }
        });
    }

    
    private void mainPanel(){
        logo = new JLabel();
        login = new JButton("Login");
        username = new JLabel("Username:");
        password = new JLabel("Password:");
        usernameText = new JTextField();
        passwordText = new JPasswordField(10);
        check = new JCheckBox();    
        
        username.setFont(fontFormat);password.setFont(fontFormat);
        
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/avatar3.png").getImage().getScaledInstance(170, 160, Image.SCALE_DEFAULT));
        logo.setIcon(imageIcon);  
        logo.setBounds(160,30,170,160);
//        Border border = BorderFactory.createLineBorder(Color.BLACK);
//        logo.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        
        username.setBounds(80,220,85,height);  
        usernameText.setBounds(175,220,width,height);
        usernameText.setFont(new Font("Serif", Font.ITALIC, 18));
        usernameText.setHorizontalAlignment(JTextField.CENTER);
        
        password.setBounds(80,270,85,height);  
        passwordText.setBounds(175,270,width,height);
        passwordText.setFont(new Font("Serif", Font.ITALIC, 17));
        passwordText.setHorizontalAlignment(JTextField.CENTER);        
        
        check.setBounds(400,270,30,height);  
        
        
        login.setBounds(294,320,100,height);
        
        add(logo);add(login);
        add(username);add(usernameText);
        add(password);add(passwordText);
        add(check);
        
        login.addActionListener(this);
        login.requestFocus();
    }
    
    private void reset() {
        String greetingName = usernameText.getText().trim();
        String passwords = JavaAssignment.onLogin.getPassword();
        String email = JavaAssignment.onLogin.getEmail();
        String contact = JavaAssignment.onLogin.getContact();
        int greetingRole = JavaAssignment.onLogin.getRole();
        ManagingStaff ms = new ManagingStaff(greetingName, passwords, email, contact, greetingRole);
        DeliveryStaff ds = new DeliveryStaff(greetingName, passwords, email, contact, greetingRole);
        Customer c = new Customer(greetingName, passwords, email, contact, greetingRole);
        switch (greetingRole) {
            case 1:
                ms.greeting(greetingName);
                break;
            case 2:
                ds.greeting(greetingName);
                break;
            default:
                c.greeting(greetingName);
                break;
        }
        usernameText.setText("");
        passwordText.setText("");    
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login){   
            String getUsername = usernameText.getText().trim();
            String getPassword = new String(passwordText.getPassword());
            boolean flag = false;
            OUTER:
            for (int i = 0; i<allAccount.size(); i++) {
                Account a = allAccount.get(i);
                if (getUsername.equals(a.getUsername()) && getPassword.equals(a.getPassword())) {
                    flag = true;
                    if (flag == true) {
                        switch (a.getRole()) {
                            case 1:
                                setVisible(false);
                                JavaAssignment.onLogin = a;
                                reset();
                                break OUTER;
                            case 2:
                                setVisible(false);
                                JavaAssignment.onLogin = a;
                                reset();

                                break OUTER;
                            default:
                                JavaAssignment.onLogin = a;
                                setVisible(false);
                                reset();
                                break OUTER;
                        }
                    }
                } else {
                    JavaAssignment.onLogin = null;
                    flag=false;         
                }
            }
            if(flag==false){
                JOptionPane.showMessageDialog(this, "Incorrect Username or Password ! Please try again !");            
            }
        }
    }    
}
