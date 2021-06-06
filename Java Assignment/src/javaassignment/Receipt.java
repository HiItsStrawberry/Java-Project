package javaassignment;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Receipt {
    
    private final String receiptID;
    private final Payment sender, recipient, paymentID, date, fee;

    public Receipt(String receiptID, Payment sender, Payment recipient, Payment paymentID, Payment date, Payment fee) {
        this.receiptID = receiptID;
        this.sender = sender;
        this.recipient = recipient;
        this.paymentID = paymentID;
        this.date = date;
        this.fee = fee;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public Payment getSender() {
        return sender;
    }

    public Payment getRecipient() {
        return recipient;
    }

    public Payment getPaymentID() {
        return paymentID;
    }

    public Payment getDate() {
        return date;
    }

    public Payment getFee() {
        return fee;
    }

    public void printReceipt() {
        try {      
            PrintWriter p = new PrintWriter("Receipt.txt");
            for(int i = 0; i < JavaAssignment.allReceipt.size(); i++) {
                Receipt pa = JavaAssignment.allReceipt.get(i);               
                p.println("ReceiptID:"+pa.getReceiptID());
                p.println("PaymentID:"+pa.getPaymentID().getPaymentID());
                p.println("Paid Date:"+pa.getDate().getDate());
                p.println("Sender:"+pa.getSender().getSender().getSender());
                p.println("Recipient:"+pa.getRecipient().getRecipient().getRecipient());
                p.println("Shipping Fee:"+pa.getFee().getFee());
                p.println();                  
            }
            p.close();
            JOptionPane.showMessageDialog(null, "Receipt for payment ID: "+paymentID.getPaymentID()+"\nhas printed into the system !");
        }
        catch (HeadlessException | FileNotFoundException e) {
            System.out.println("RECEIPT ERROR :"+e);
        }
    }      
}
