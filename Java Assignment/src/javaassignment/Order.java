package javaassignment;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;


public class Order {
    
    private int orderID;
    private String address1, address2, date, status, rating, feedback, deliveryStaff, sender, recipient, contact1, contact2;

    public Order(int orderID, String date, String status, String rating, String feedback, String deliveryStaff, String sender, String recipient, String contact1, String contact2, String address1, String address2) {
        this.orderID = orderID;
        this.date = date;
        this.status = status;
        this.rating = rating;
        this.feedback = feedback;
        this.deliveryStaff = deliveryStaff;
        this.sender = sender;
        this.recipient = recipient;
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.address1 = address1;
        this.address2 = address2;              
    }

    public int getOrderID() {
        return orderID;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getRating() {
        return rating;
    }
 
    public void setRating(String rating) {
        this.rating = rating;
    }    

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDeliveryStaff() {
        return deliveryStaff;
    }

    public void setDeliveryStaff(String deliveryStaff) {
        this.deliveryStaff = deliveryStaff;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContact1() {
        return contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }
    
    public void addOrder() {
        try {
            PrintWriter p = new PrintWriter("Order.txt");
            for(int i=0; i< JavaAssignment.allOrder.size(); ++i) {
                JavaAssignment.orders = JavaAssignment.allOrder.get(i);
                Order o = JavaAssignment.allOrder.get(i);

                p.println("OrderID:" + o.getOrderID());
                p.println("Order Date:" + o.getDate());
                p.println("Order Status:" + o.getStatus());
                p.println("Order Rating:" + o.getRating());
                p.println("Order Feedback:" + o.getFeedback());
                p.println("Delivery Staff:" + o.getDeliveryStaff());
                p.println("Sender:" + o.getSender());
                p.println("Recipient:" + o.getRecipient());
                p.println("Sender's Contact:" + o.getContact1());
                p.println("Recipient's Contact:" + o.getContact2());
                p.println("Sender's Address:" + o.getAddress1());
                p.println("Recipient's Address:" + o.getAddress2());                    
                p.println();
            }
            p.close();
            JOptionPane.showMessageDialog(null, "Order created successfully!");    
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR :" + e);
        }     
    }
    

    public void assignOrder(int getID, String getSender, String getRecipient, String getAddress) {
        
        String message = "Do you want to assign the order now? \nYour order ID is: "+getID;
        
        int response = JOptionPane.showConfirmDialog(null, message, null, JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {              
            Admin_DeliveryOrder ad = new Admin_DeliveryOrder();
            JavaAssignment.customerPayment.setVisible(false);
            JavaAssignment.payments = null;
            JavaAssignment.orders = null;    
            ad.setID(getID);
            ad.setSender(getSender);
            ad.setRecipient(getRecipient);
            ad.setAddress(getAddress);
            ad.setVisible(true);
        }         
    }
}
