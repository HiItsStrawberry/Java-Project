package javaassignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaassignment.JavaAssignment.fontFormat;
import static javaassignment.JavaAssignment.height;
import static javaassignment.JavaAssignment.orange;
import static javaassignment.JavaAssignment.width;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Delivery_Email extends JFrame implements ActionListener {
    
    private JButton home, send;
    private JLabel recipient, cc, subject, orderID;
    private JTextField recipientText, ccText, subjectText;
    private JPanel topPanel, middlePanel, bottomPanel;
    private JComboBox orderIDText;
    private static final String adminEmail = "ynqcourierservice@gmail.com";
    private static final String emailPassword = "ynqadmin123";
    
    public Delivery_Email(){
        setVisible(false);
        setSize(700,500);
        setTitle("Delivery Staff_Send Email Page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(2,10)); //(gap)
        
        topPanel();
        middlePanel();
        bottomPanel();
        getData();
        
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
      
    private void middlePanel() {
        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        this.add(middlePanel, BorderLayout.CENTER);  
        
        
        recipient = new JLabel("To:");
        recipient.setFont(fontFormat);
        cc = new JLabel("CC:");
        cc.setFont(fontFormat);
        subject = new JLabel("Subject:");
        subject.setFont(fontFormat);
        orderID = new JLabel("Order ID:");
        orderID.setFont(fontFormat);
        
        
        recipientText = new JTextField();    
        ccText = new JTextField("admin@hotmail.com");       ccText.setEditable(false);
        subjectText = new JTextField("Parcel Delivery");    subjectText.setEditable(false);
        
        send = new JButton("Send");
        
        recipient.setBounds(167, 32, 100, 50);
        cc.setBounds(167, 72, 100, 50);
        subject.setBounds(167, 112, 100, 50);
        orderID.setBounds(167, 152, 138, 50);
        
        
        recipientText.setBounds(308, 42, width, height);
        ccText.setBounds(308, 82, width, height);
        subjectText.setBounds(308, 122, width, height);
        
        send.setBounds(378, 260, 150, height);
        
        middlePanel.add(recipient);     middlePanel.add(recipientText);
        middlePanel.add(cc);            middlePanel.add(ccText);
        middlePanel.add(subject);       middlePanel.add(subjectText);
        middlePanel.add(orderID);       
        
        middlePanel.add(send); 
        
        send.addActionListener(this);      
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
    
    private static void email(String recipient, String subject, String orderID) throws Exception {           
        Properties props = new Properties();  
        props.setProperty("mail.transport.protocol", "smtp");     
        props.setProperty("mail.host", "smtp.gmail.com");  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.smtp.port", "465");  
        props.put("mail.debug", "true");  
        props.put("mail.smtp.socketFactory.port", "465");  
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
        props.put("mail.smtp.socketFactory.fallback", "false");  
        Session session = Session.getDefaultInstance(props,
                
        new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(adminEmail,emailPassword);  
            }  
        });
        

        try (Transport transport = session.getTransport()) {
            InternetAddress from = new InternetAddress(adminEmail);

            MimeMessage message = new MimeMessage(session);
            message.setSender(from);
            message.setSubject(subject);
            String html = "Dear Customer,<br><br>Thanks for using YNQ Courier Service<br>Your order <b>"+ orderID +"</b> has been delivered sucessfully!"
                          + "<br>Hope you enjoy with our services !<br><br>Please do leave feedback and rating to us !";
            message.setContent(html,"text/html");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            transport.connect();
            Transport.send(message);
        }
        catch(MessagingException e) {
            System.out.println("ERROR :"+e);
        }
    }
    
    private void getData() {   
        try {
            orderIDText = new JComboBox();              
            orderIDText.removeAllItems();
            orderIDText.addItem("None"); 
            int orderIDs;
          
            if(JavaAssignment.onLogin != null) {
                for(int i = 0; i < JavaAssignment.onLogin.getMyOrder().size(); ++i) {
                    if(JavaAssignment.onLogin.getMyOrder().get(i).getStatus().equals("Delivered")) {
                        orderIDs = JavaAssignment.onLogin.getMyOrder().get(i).getOrderID();

                        orderIDText.addItem(orderIDs);                   
                    }
                }               
            }     
            orderIDText.setBounds(308, 162, width, height);
            middlePanel.add(orderIDText);
        }
        catch (Exception e) {
            System.out.println("ERROR(getData) :"+e);
        }  
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == home) {
            setVisible(false);
            Delivery_Home dh = new Delivery_Home();
            String getName = JavaAssignment.onLogin.getUsername();
            String finalName = getName.substring(0, 1).toUpperCase() + getName.substring(1);
            dh.getTheName().setText(finalName);
            dh.setVisible(true);          
        }     
        else {
            String getRecipient = recipientText.getText().trim();
            String getSubject = subjectText.getText().trim();
            String getCC = ccText.getText().trim();
            String getOrderID = orderIDText.getSelectedItem().toString().trim();
            
            if (getRecipient.isEmpty() || getSubject.isEmpty() || getCC.isEmpty() || getOrderID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the empty fields !");
            } 
            else if (!JavaAssignment.emailValidation(getRecipient)) {
                JOptionPane.showMessageDialog(this, "Email format must be example@gmail/outlook.com");
            }
            else if (getRecipient.equals("admin@hotmail.com")) {
                JOptionPane.showMessageDialog(this, "Managing Staff/Admin email cannot be recipient !");
            }
            else if (getOrderID.equals("None")) {
                JOptionPane.showMessageDialog(this, "An order must be selected !");
            }
            else {
                try {       
                    send.setText("Sending...");
                    send.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "Sending Email...\n"
                                                        + "Please wait for a while...");  
                    email(getRecipient, getSubject, getOrderID);
                    JOptionPane.showMessageDialog(this, "Email has been sent successfully !\n\n"
                                                        + "Mail From:\n"+adminEmail+"\n\n"
                                                        + "Recipient To:\n"+getRecipient);
                    send.setText("Send");
                    send.setEnabled(true);
                } 
                catch (Exception ex) {
                    Logger.getLogger(Delivery_Email.class.getName()).log(Level.SEVERE, null, ex);
                }     
            } 
        }
    }
}
