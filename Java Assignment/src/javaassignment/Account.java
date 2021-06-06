package javaassignment;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public abstract class Account {

    private final String username;
    private String password, email, contact;
    private final int role; //1 2 3 (1 = Admin, 2 = Delivery, 3 = Customer)
    
    private ArrayList<Order> cusOrder = new ArrayList<>();
    private ArrayList<Order> myOrder = new ArrayList<>();
    
    public Account(String username, String password, String email, String contact, int role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
        this.role = role;
    }
    
    public void greeting() {
        String message = "<html><body><div><p style='color:orange;'>Login Successfully</p><p style='color:green;'>Welcome "+username+"</p></div></body></html>";
        JLabel messageLabel = new JLabel(message);   
        JOptionPane.showMessageDialog(null, messageLabel);                 
    }   

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getRole() {
        return role;
    }

    public ArrayList<Order> getCusOrder() {
        return cusOrder;
    }

    public ArrayList<Order> getMyOrder() {
        return myOrder;
    }
}
