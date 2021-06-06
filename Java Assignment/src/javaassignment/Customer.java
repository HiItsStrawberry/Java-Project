package javaassignment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Customer extends Account {
    
    public Customer(String username, String password, String email, String contact, int role) {
        super(username, password, email, contact, role);
    }

    public void greeting(String username) {
        String message = "<html><body><div><p style='color:orange;'>Login as Customer Successfully</p><p style='color:green;'>Welcome "+username+"</p></div></body></html>";
        JLabel messageLabel = new JLabel(message);   
        JOptionPane.showMessageDialog(null, messageLabel);
        
        Customer_Home ch = new Customer_Home();
        String getName = JavaAssignment.onLogin.getUsername();
        String finalName = getName.substring(0, 1).toUpperCase() + getName.substring(1);
        ch.getTheName().setText(finalName);
        ch.setVisible(true);        
    }    
       
}
