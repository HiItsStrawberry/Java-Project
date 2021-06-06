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
import static javaassignment.JavaAssignment.fontFormat;
import static javaassignment.JavaAssignment.height;
import static javaassignment.JavaAssignment.orange;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Customer_RateNFeed extends JFrame implements ActionListener{
    
    private JButton home, submit;
    private JLabel orderID, status, deliveryStaff, rating, feedback, details, br;
    private JTextField orderIDText, deliveryStaffText, statusText;
    private JTextArea feedbackText;
    private JComboBox ratingText;
    private JScrollPane scrollTextArea, scrollPanel;
    private JPanel topPanel, secondPanel, leftPanel, rightPanel, feedbackPanel;
    
    public Customer_RateNFeed(){
        setVisible(false);
        setSize(700,500);
        setTitle("Customer_Rating and Feedback Page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(2,10)); //(gap)
        
        topPanel();
        secondPanel();
        bottomPanel();
        
        validate();
        repaint();
    }
    
    private void topPanel() {
        home = new JButton("Home Page");
               
        //TOP PANEL
        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)
        
        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)
    
        topPanel.add(home);
        this.add(topPanel, BorderLayout.PAGE_START);     
        
        home.addActionListener(this);
    }
    
    private void secondPanel() {
        secondPanel = new JPanel();
        secondPanel.setLayout(null);
        this.add(secondPanel, BorderLayout.CENTER);
        leftPanel();
        rightPanel();
    }
    
    private void leftPanel(){
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(320,500));
        this.add(leftPanel, BorderLayout.LINE_START);
        leftPanel.setLayout(null);
        String[] ratingChoice = {"1|Dissapointment", "2|Very Bad", "3|Average", "4|Good", "5|Satisfaction"};
       
        orderID = new JLabel("Order ID:");  
        status = new JLabel("Order Status");
        deliveryStaff = new JLabel("Delivery Staff:");      
        rating = new JLabel("Rating:"); 
        feedback = new JLabel("Feedback: (Optional)");
                         
        orderIDText = new JTextField();             orderIDText.setEditable(false);
        statusText = new JTextField();              statusText.setEditable(false);
        deliveryStaffText = new JTextField();       deliveryStaffText.setEditable(false);
        ratingText  = new JComboBox(ratingChoice);  ratingText.setSelectedIndex(2);
        feedbackText = new JTextArea();        
 
        orderID.setFont(fontFormat);
        status.setFont(fontFormat);
        deliveryStaff.setFont(fontFormat);
        rating.setFont(fontFormat);
        feedback.setFont(fontFormat);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        feedbackText.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        scrollTextArea = new JScrollPane(feedbackText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);        
        feedbackText.setLineWrap(true);
        feedbackText.setWrapStyleWord(true);
        
        orderID.setBounds(20,0,100,50);  //0
        status.setBounds(20,40,100,50); //40
        deliveryStaff.setBounds(20,80,100,50);//80
        rating.setBounds(20,120,100,50);//120
        feedback.setBounds(20,160,150,50);//160
        
        orderIDText.setBounds(130,10,180,height);//10  
        statusText.setBounds(130,50,180,height);//50 
        deliveryStaffText.setBounds(130,90,180,height);//90 
        ratingText.setBounds(130,130,180,height);//130 
        scrollTextArea.setBounds(20,205,291,140);//170 
        
        
        leftPanel.add(orderID);         leftPanel.add(orderIDText);
        leftPanel.add(status);          leftPanel.add(statusText);
        leftPanel.add(deliveryStaff);   leftPanel.add(deliveryStaffText);
        leftPanel.add(rating);          leftPanel.add(ratingText);
        leftPanel.add(feedback);        leftPanel.add(scrollTextArea);
        
        
    }

    private void rightPanel(){
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(380,500));
        this.add(rightPanel, BorderLayout.LINE_END);
        customerOrdersRate();       
    }
    
    private void bottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout()); 
        bottomPanel.setPreferredSize(new Dimension(0,50));   
        bottomPanel.setLayout(new FlowLayout(2,10,10));
        bottomPanel.setBackground(orange);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); //(top, left, bottom, right)
        this.add(bottomPanel, BorderLayout.PAGE_END); 
        
        submit = new JButton("Submit feedback");
        bottomPanel.add(submit);
        submit.addActionListener(this);
    }      
    
   
    
    private void customerOrdersRate() {  
        feedbackPanel = new JPanel();
        feedbackPanel.setLayout(null);
        feedbackPanel.setLayout(new BoxLayout(feedbackPanel, BoxLayout.Y_AXIS));
        feedbackPanel.setBorder(LineBorder.createGrayLineBorder());
        
        scrollPanel = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(350,345));
        scrollPanel.setViewportView(feedbackPanel);
        JButton[] select = null;
   
        if(JavaAssignment.onLogin != null)
        {
            JavaAssignment.onLogin.getCusOrder().clear();
            JavaAssignment.orderData();
            select = new JButton[JavaAssignment.onLogin.getCusOrder().size()];
            if(JavaAssignment.onLogin.getCusOrder().isEmpty()) {
                details = new JLabel("<html><div style='width:345; height:340; color:yellow; background: #1abc9c; text-align:center;'><h1>There is no order being delivered yet.</h1></div></html>"); 
                feedbackPanel.add(details); 
            }
            else { 
                for(int i = 0; i < JavaAssignment.onLogin.getCusOrder().size(); i++)
                {
                    if (JavaAssignment.onLogin.getCusOrder().get(i).getStatus().equals("Delivered")) {
                        int getOrderID = JavaAssignment.onLogin.getCusOrder().get(i).getOrderID();
                        String getStatus = JavaAssignment.onLogin.getCusOrder().get(i).getStatus();
                        String getDelivery = JavaAssignment.onLogin.getCusOrder().get(i).getDeliveryStaff();
                        String getRating = JavaAssignment.onLogin.getCusOrder().get(i).getRating();
                        String getFeedback = JavaAssignment.onLogin.getCusOrder().get(i).getFeedback();


                        details = new JLabel("<html><div style='width:266px; padding:5px; color:#2c3e50; background: #1abc9c;'>"
                                                +"<h3 style='margin:1px;'>Order ID: <span style='color:#ffffff'>"+getOrderID+"</span></h3>"
                                                +"<h3 style='margin:1px;'>Status: <span style='color:yellow'>"+getStatus+"</span><</h3>"
                                                +"<h3 style='margin:1px;'>Delivery Staff: <span style='color:yellow'>"+getDelivery+"</span><</h3>"
                                                +"<h3 style='margin:1px;'>Rating: <span style='color:#ffffff'>"+getRating+"</span><</h3>"
                                                +"<h3 style='margin:1px;'>Feedback: <span style='color:#ffffff'>"+getFeedback+"</span><</h3>"        
                                                +"</div></html>");
                        br = new JLabel("<html><br><br></html>");
                        select[i] = new JButton();
                        select[i].setText("Select order ID: "+getOrderID);
                        select[i].addActionListener(this);                      
                        feedbackPanel.add(details); 
                        feedbackPanel.add(select[i]);
                        feedbackPanel.add(br);                         
                    }       
                } 
                details = new JLabel("<html><div style='width:266px; color:yellow; background: #1abc9c; text-align:center;'><h1>There is no more order being delivered</h1></div></html>"); 
                feedbackPanel.add(details);  
            }
        }      
        rightPanel.add(scrollPanel);
    }    
    
    

    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            setVisible(false);
            Customer_Home ch = new Customer_Home();
            String getName = JavaAssignment.onLogin.getUsername();
            String finalName = getName.substring(0, 1).toUpperCase() + getName.substring(1);
            ch.getTheName().setText(finalName);
            ch.setVisible(true);       
        }
        else if (ae.getSource() == submit) {
            String checkID = orderIDText.getText();
            String getRate = ratingText.getSelectedItem().toString();         
            String[] feedbackTexts = feedbackText.getText().split("\n");
            String getFeedback = "" ;            
            for (String i : feedbackTexts) {
                getFeedback = getFeedback+" " + i;
            }  
            getFeedback = getFeedback.substring(1).trim();    
            boolean isSubmit = false;
            
            if (checkID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "An order must be selected to submit rating and feedback !");
            }
            else if (getFeedback.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Feedback field must be filled !");
            }
            else {
                try {
                    for(int i = 0; i < JavaAssignment.allOrder.size(); i++) {
                        int verifyID = JavaAssignment.allOrder.get(i).getOrderID();
                        
                        if(Integer.parseInt(checkID) == verifyID) {
                            int response = JOptionPane.showConfirmDialog(this, "Are you sure want to submit these rating and feedback to this Order ID: " + checkID +" ?", null, JOptionPane.YES_NO_OPTION);
                            if (response == JOptionPane.YES_OPTION) {
                                JavaAssignment.allOrder.get(i).setRating(getRate);
                                JavaAssignment.allOrder.get(i).setFeedback(getFeedback);
                                isSubmit = true;
                            }                            
                        }
                    }
                    
                    
                    if(isSubmit == true) {
                        PrintWriter p = new PrintWriter("Order.txt");
                        for(int i=0; i< JavaAssignment.allOrder.size(); ++i)
                        {
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
                        JOptionPane.showMessageDialog(this, "Feedback submitted successfully!");   
                        setVisible(false);
                        Customer_RateNFeed cr = new Customer_RateNFeed();
                        cr.setVisible(true);                                 
                    }      
                }
                catch (HeadlessException | FileNotFoundException | NumberFormatException e) {
                    System.out.println("ERROR :"+e);
                }
            }
            
            
        }
        else {
            String getIDs = ((JButton) ae.getSource()).getText().split(": ")[1]; 
            int getID = Integer.parseInt(getIDs);
            try {
                for(int i = 0; i < JavaAssignment.allOrder.size(); ++i) {
                    int fillOrderID = JavaAssignment.allOrder.get(i).getOrderID();
                    String getStatus = JavaAssignment.allOrder.get(i).getStatus();
                    String getDeliveryStaff = JavaAssignment.allOrder.get(i).getDeliveryStaff();
                    String fillFeedback = JavaAssignment.allOrder.get(i).getFeedback();
                    
                    if(getID == fillOrderID) {
                        orderIDText.setText(Integer.toString(fillOrderID));
                        statusText.setText(getStatus);
                        deliveryStaffText.setText(getDeliveryStaff);
                        feedbackText.setText(fillFeedback);
                        break;
                    }
                }
            }
            catch (Exception e) {
                System.out.println("ERROR :"+e);
            }
        }
    }
}