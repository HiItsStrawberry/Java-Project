package javaassignment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DeliveryStaff extends Account {
    

    public DeliveryStaff(String username, String password, String email, String contact, int role) {
        super(username, password, email, contact, role);
    }
    
    public void greeting(String username) {
        String message = "<html><body><div ><p style='color:orange;'>Login as Delivery Staff Successfully</p><p style='color:green;'>Welcome "+username+"</p></div></body></html>";
        JLabel messageLabel = new JLabel(message);   
        JOptionPane.showMessageDialog(null, messageLabel);
        
        Delivery_Home dh = new Delivery_Home();
        String getName = JavaAssignment.onLogin.getUsername();
        String finalName = getName.substring(0, 1).toUpperCase() + getName.substring(1);
        dh.getTheName().setText(finalName);
        JavaAssignment.availableStaff();
        dh.setVisible(true);  
    }
}
