package javaassignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javaassignment.JavaAssignment.orange;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class Delivery_ViewOrder extends JFrame implements ActionListener {

    private JButton close;
    private JLabel details;
    private JPanel topPanel, middlePanel, orderPanel, middlePanel2, bottomPanel, space1, space2;
    private JScrollPane scrollPanel;
    
    public Delivery_ViewOrder() {
        setVisible(false);
        setSize(new Dimension(700,400));          
        setTitle("Delivery Staff_View Order Page");     
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(2,10)); 
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        space1 = new JPanel();
        space2 = new JPanel();
        space1.setPreferredSize(new Dimension(100,0));
        space2.setPreferredSize(new Dimension(100,0));  
        this.add(space1, BorderLayout.EAST);
        this.add(space2, BorderLayout.WEST);
        
        topPanel();
        middlePanel();
        bottomPanel();

        validate();
        repaint();        
    }
    
    
    private void topPanel() {
        close = new JButton("Close");
               
        //TOP PANEL
        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)
        
        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)
        
        topPanel.add(close);
        this.add(topPanel, BorderLayout.PAGE_START);     
        
        close.addActionListener(this);
    }    
    
    private void middlePanel() {
        middlePanel = new JPanel();
        this.add(middlePanel, BorderLayout.CENTER);
        middlePanel2();
    }    
    
    private void middlePanel2() {
        middlePanel2 = new JPanel();
        middlePanel2.setPreferredSize(middlePanel.getMaximumSize());
        middlePanel.add(middlePanel2, BorderLayout.CENTER);  
        currentOrder();
    }
        
    private void bottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout()); 
        bottomPanel.setPreferredSize(new Dimension(0,50));   
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(orange);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); //(top, left, bottom, right)
        this.add(bottomPanel, BorderLayout.PAGE_END);               
    }      
    
    private void currentOrder() {
        orderPanel = new JPanel();
        orderPanel.setLayout(null);
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderPanel.setBorder(LineBorder.createGrayLineBorder());
       
        scrollPanel = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(490,240));
        scrollPanel.setViewportView(orderPanel);

        if(JavaAssignment.onLogin != null) {
            JavaAssignment.onLogin.getMyOrder().clear();
            JavaAssignment.orderData();
            JavaAssignment.availableStaff();
            if(JavaAssignment.onLogin.getMyOrder().isEmpty() || JavaAssignment.deliveryTask.getTask() < 1) {
                details = new JLabel("<html><div style='width:485; height:235; color:yellow; background: #1abc9c; text-align:center;'><h1>There is no order being assigned to you currently</h1></div></html>");
                orderPanel.add(details);
            }
            else {

                for(int i = 0; i < JavaAssignment.onLogin.getMyOrder().size(); ++i) {                  
                    if(JavaAssignment.onLogin.getMyOrder().get(i).getStatus().equals("Delivering")) {
                        int orderID = JavaAssignment.onLogin.getMyOrder().get(i).getOrderID();   
                        String deliveryStaff = JavaAssignment.onLogin.getMyOrder().get(i).getDeliveryStaff();
                        String recipient = JavaAssignment.onLogin.getMyOrder().get(i).getSender();
                        String contact = JavaAssignment.onLogin.getMyOrder().get(i).getContact2();
                        String address = JavaAssignment.onLogin.getMyOrder().get(i).getAddress2();

                        details = new JLabel("<html><div style='width:485; height:235; padding:5px; color:#2c3e50; background: #1abc9c;'>"
                                                +"<h2 style='margin:2px; font-size:24px;'>Order ID: <span style='color:yellow'>"+orderID+"</span></h2>"
                                                +"<h3 style='margin:2px; font-size:12px;'>Delivery Staff: <span style='color:#ffffff'>"+deliveryStaff+"</span><</h3>"
                                                +"<h3 style='margin:2px; font-size:12px;'>Parcel Receiver: <span style='color:#ffffff'>"+recipient+"</span><</h3>"
                                                +"<h3 style='margin:2px; font-size:12px;'>Contact: <span style='color:#ffffff'>"+contact+"</span><</h3>"
                                                +"<h3 style='margin:2px; font-size:12px;'>Address: <span style='color:yellow'>"+address+"</span><</h3>"        
                                                +"</div></html>");   
                        orderPanel.add(details);                                        
                    }
                }
            }
        }
        middlePanel2.add(scrollPanel);        
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == close) {
            setVisible(false);
        }       
    } 
}
