package javaassignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaassignment.JavaAssignment.height;
import static javaassignment.JavaAssignment.orange;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Admin_Report extends JFrame implements ActionListener{

    private JButton home,generate,print;
    private JLabel title;
    private JTextArea report;
    private JScrollPane scrollReport;
    
    public Admin_Report() {
        setVisible(false);
        setSize(700,500);
        setTitle("Managing Staff_Report Page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(2,10)); //(gap)
        
        topPanel();
        middlePanel();
        bottomPanel();
        
        validate();
        repaint();          
    }
    
    
    private void topPanel() {
        home = new JButton("Home Page");

        //TOP PANEL
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); //(top, left, bottom, right)

        topPanel.setBackground(orange);
        topPanel.setLayout(new FlowLayout(3,10,10)); // (1=center/2=float_right/3=float_left,margin_left,margin_top)
        topPanel.setPreferredSize(new Dimension(0,45)); //(width,height)

        topPanel.add(home);
        this.add(topPanel, BorderLayout.PAGE_START); 
        
        home.addActionListener(this);
    }        
    
    private void middlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(null);
//        middlePanel.setBackground(Color.yellow);
//        middlePanel.setBorder(BorderFactory.createTitledBorder(null, "Sender Infomration", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, titleBorderFont));        
        this.add(middlePanel, BorderLayout.CENTER);  
        
        report = new JTextArea();
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        report.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(1, 1, 1, 1))); 
        report.setEditable(false);
        scrollReport = new JScrollPane(report, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        
        report.setLineWrap(true);
        report.setWrapStyleWord(true);       
        title = new JLabel("Monthly Summary Sales Revenue Report");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        
        generate = new JButton("Generate");
        print = new JButton("Print");
        
        title.setBounds(170, 0, 350, 30);
        scrollReport.setBounds(100, 40, 500, 270);
        
        generate.setBounds(409, 320, 90, height);
        print.setBounds(509, 320, 90, height);
            
        middlePanel.add(title);
        middlePanel.add(scrollReport); 
        middlePanel.add(generate); 
        middlePanel.add(print); 
        
        generate.addActionListener(this);
        print.addActionListener(this);
    }

    private void bottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout()); 
        bottomPanel.setPreferredSize(new Dimension(0,50));   
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(orange);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); //(top, left, bottom, right)
        this.add(bottomPanel, BorderLayout.PAGE_END);               
    }      
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            setVisible(false);
            Admin_Home ah = new Admin_Home();
            String getName = JavaAssignment.onLogin.getUsername();
            String finalName = getName.substring(0, 1).toUpperCase() + getName.substring(1);
            ah.getTheName().setText(finalName);
            ah.setVisible(true);          
        }
        else if (ae.getSource() == generate) {       
            JavaAssignment ja = new JavaAssignment();
            ja.randomString();
            ja.countReport();
            report.setText("");
            report.append(
             "\tSummary Report of YNQ Courier Service System\n\n"
            +"\tReport No: "+JavaAssignment.reportID+"\n"
            +"\t=============================================\n"
            +"\t--------------------------------------------------------------------------------\n"        
            +"\t1. Delivery Staff, Customer, Order, Payment, etc (Present)\n"
            +"\t--------------------------------------------------------------------------------\n"
            +"\tTotal Delivery Staff:\t\t"+JavaAssignment.allValues[0]+"\n"
            +"\tTotal Customer:\t\t"+JavaAssignment.allValues[1]+"\n"                    
            +"\tTotal Order:\t\t\t"+JavaAssignment.allValues[2]+"\n"
            +"\tTotal Payment RM:\t\t"+JavaAssignment.allValues[3]+"\n"
            +"\tTotal Rating:\t\t\t"+JavaAssignment.allValues[4]+"\n\n"
            +"\tTotal 1|Dissapointment Rate:\t\t"+JavaAssignment.allValues[5]+"\n"
            +"\tTotal 2|Very Bad Rate:\t\t"+JavaAssignment.allValues[6]+"\n"
            +"\tTotal 3|Average Rate:\t\t"+JavaAssignment.allValues[7]+"\n"
            +"\tTotal 4|Good Rate:\t\t"+JavaAssignment.allValues[8]+"\n"
            +"\tTotal 5|Satisfaction Rate:\t\t"+JavaAssignment.allValues[9]+"\n\n"
            +"\tTotal Average Rating:\t\t"+JavaAssignment.allValues[10]+"\n\n\n" 
            +"\t--------------------------------------------------------------------------------\n"
            +"\t2. Monthly Sales Revenue (Past 30 Days)\n"
            +"\t--------------------------------------------------------------------------------\n"
            +"\tTotal Delivery Staff:\t\t"+JavaAssignment.allValuesWithin[0]+"\n"
            +"\tTotal Customer:\t\t"+JavaAssignment.allValuesWithin[1]+"\n"                    
            +"\tTotal Order:\t\t\t"+JavaAssignment.allValuesWithin[2]+"\n"
            +"\tTotal Payment RM:\t\t"+JavaAssignment.allValuesWithin[3]+"\n"
            +"\tTotal Rating:\t\t\t"+JavaAssignment.allValuesWithin[4]+"\n\n"
            +"\tTotal 1|Dissapointment Rate:\t\t"+JavaAssignment.allValuesWithin[5]+"\n"
            +"\tTotal 2|Very Bad Rate:\t\t"+JavaAssignment.allValuesWithin[6]+"\n"
            +"\tTotal 3|Average Rate:\t\t"+JavaAssignment.allValuesWithin[7]+"\n"
            +"\tTotal 4|Good Rate:\t\t"+JavaAssignment.allValuesWithin[8]+"\n"
            +"\tTotal 5|Satisfaction Rate:\t\t"+JavaAssignment.allValuesWithin[9]+"\n\n"
            +"\tTotal Average Rating:\t\t"+JavaAssignment.allValuesWithin[10]+"\n"          
            +"\t=============================================\n"
            +"\tApproved by: Yau Zhi Ming & Quek Weng Hang\n");   
        }
        else {
            try {
                report.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Admin_Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
}
