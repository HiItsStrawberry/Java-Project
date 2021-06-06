package javaassignment;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Payment {
    
    private final int paymentID;
    private String date;
    private final Order myOrderID, sender, recipient;
    private double fee;
    private int paid;
    
    public Payment(int paymentID, String date, Order myOrderID, Order sender, Order recipient, double fee, int paid) {
        this.paymentID = paymentID;
        this.date = date;
        this.myOrderID = myOrderID;
        this.sender = sender;
        this.recipient = recipient;
        this.fee = fee;
        this.paid = paid;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }    

    public Order getMyOrderID() {
        return myOrderID;
    }

    public Order getSender() {
        return sender;
    }

    public Order getRecipient() {
        return recipient;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }
    
    public void addPayment() {
        try {
            PrintWriter p = new PrintWriter("Payment.txt");
            for(int j=0; j < JavaAssignment.allPayment.size(); j++) {
                Payment pa = JavaAssignment.allPayment.get(j);
                p.println("PaymentID:"+pa.getPaymentID());
                p.println("Date:"+pa.getDate());
                p.println("OrderID:"+pa.getMyOrderID().getOrderID());
                p.println("Sender:"+pa.getSender().getSender());
                p.println("Recipient:"+pa.getRecipient().getRecipient());
                p.println("Shipping Fee:"+pa.getFee());
                p.println("Paid:"+pa.getPaid());                        
                p.println();                                                  
            }   
            p.close();
            JavaAssignment.orders = null;          
            
        } 
        catch (FileNotFoundException e) {
            System.out.println("ERROR :" + e);
        }
    }
    
    public int makePayment(int getID, String getSender, String getRecipient, double getFee) {
        
        String message = "Do you want to proceed payment now? Your paymentID is: "+paymentID;
        JLabel messageLabel = new JLabel(message);
        
        int response = JOptionPane.showConfirmDialog(null, messageLabel, null, JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) { 

            JavaAssignment.customerPayment.setID(getID);
            JavaAssignment.customerPayment.setSender(getSender);
            JavaAssignment.customerPayment.setRecipient(getRecipient);
            JavaAssignment.customerPayment.setFee(getFee); 
            JavaAssignment.customerPayment.setVisible(true); 
        }         
        return response;
    }
}
