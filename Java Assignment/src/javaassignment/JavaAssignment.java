package javaassignment;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaAssignment {
   
    // Global Variable
    public static int width = 220;
    public static int height = 30;
    public static Color orange = new Color(255,165,0);
    public static Font fontFormat = new Font("Serif", Font.BOLD, 16);
    public static Font titleBorderFont = new Font("Serif", Font.ROMAN_BASELINE, 18);
        
    public static ArrayList<Account> allAccount = new ArrayList<Account>();
    public static ArrayList<DeliveryStaff> allDeliveryStaff = new ArrayList<DeliveryStaff>();
    public static ArrayList<DeliveryTask> availableStaff = new ArrayList<DeliveryTask>();
    public static ArrayList<DeliveryTask> allDeliveryTask = new ArrayList<DeliveryTask>();
    public static ArrayList<Order> allOrder = new ArrayList<Order>();
    public static ArrayList<Payment> allPayment = new ArrayList<Payment>(); 
    public static ArrayList<Receipt> allReceipt = new ArrayList<Receipt>(); 
    public static Admin_CustomerPayment customerPayment = new Admin_CustomerPayment();
    
    // ArrayList for All Delivery Staff
    public static DeliveryTask deliveryTask = null;
    public static Account onLogin = null;
    public static Order orders = null;
    public static Payment payments = null;
    public static String randomString = null;
    public static String randomString2 = null;
    public static String reportID = null;
    
    // -------------------------------------------------+             
    public static int totalDelivery; 
    public static int totalCustomer;    
    public static int totalOrders;
    public static double totalPayments;
    public static int totalRating1;
    public static int totalRating2;
    public static int totalRating3;
    public static int totalRating4;
    public static int totalRating5;   
    public static double totalRates;
    public static int totalRate;
    public static double averageRate;
    // -------------------------------------------------              
    public static int totalDeliveryWithin; 
    public static int totalCustomerWithin;    
    public static int totalOrdersWithin;
    public static double totalPaymentsWithin;
    public static int totalRating1Within;
    public static int totalRating2Within;
    public static int totalRating3Within;
    public static int totalRating4Within;
    public static int totalRating5Within;
    public static double totalRatesWithin;
    public static int totalRateWithin;
    public static double averageRateWithin;
    
    public static String[] allValues = new String[6];
    public static String[] allValuesWithin = new String[6];
    // -------------------------------------------------
    
    
    // Login page for all users
    public static Login login = new Login();

    // Admin_Delivery Staff Pages
    public static Admin_Home admin_home = new Admin_Home();
    public static Admin_DeliveryHome admin_deliveryHome = new Admin_DeliveryHome();
    public static Admin_DeliveryRegister admin_deliveryRegister = new Admin_DeliveryRegister();
    public static Admin_DeliveryModify admin_deliveryModify = new Admin_DeliveryModify();
    
    // Admin_Customer Pages
    public static Admin_CustomerHome admin_customerHome = new Admin_CustomerHome();
    public static Admin_CustomerOrder customerOrder = new Admin_CustomerOrder();
    public static Admin_DeliveryOrder admin_deliveryOrder = new Admin_DeliveryOrder();  
    public static Admin_CustomerModify customerModify = new Admin_CustomerModify();
    public static Admin_CustomerRegister customerRegister = new Admin_CustomerRegister();
    
    // Admin_Report Page
    public static Admin_Report adminReport = new Admin_Report();
    public static Admin_Receipt adminReceipt = new Admin_Receipt();
       
    // Delivery Staff Pages
    public static Delivery_Home deliveryHome = new Delivery_Home();
    public static Delivery_ModifyProfile deliveryModifyProfile = new Delivery_ModifyProfile();
    public static Delivery_ModifyOrder deliveryModifyOrder = new Delivery_ModifyOrder();
    public static Delivery_ViewOrder deliveryViewOrder = new Delivery_ViewOrder();
    public static Delivery_ViewReceipt deliveryViewReceipt = new Delivery_ViewReceipt();
    public static Delivery_Email deliveryEmail = new Delivery_Email();
    
    // Customer Staff Pages
    public static Customer_Home customerHome = new Customer_Home();
    public static Customer_ModifyProfile customerModifyProfile = new Customer_ModifyProfile();
    public static Customer_RateNFeed customerRateNFeed = new Customer_RateNFeed();    
    
     
    
    public static void main(String[] args) {   
        orderData();
        accountData();
        availableStaff();
    }
        
    public static boolean emailValidation(String email){
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern emailPat = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher match = emailPat.matcher(email);
        return match.find();
    }
    
    public static boolean contactValidation(String input) {
        String format = "^(01\\d\\-\\d{7,8})$";
        Pattern formatPat = Pattern.compile(format, Pattern.CASE_INSENSITIVE);
        Matcher match = formatPat.matcher(input);
        return match.find();         
    }   
    
    public static void orderData() {
        try 
        { 
//            System.out.println("Order/Payment/Receipt Data Initializing...\n");
            allOrder.clear();  
            allPayment.clear();     
            allReceipt.clear();
            
            Scanner s = new Scanner(new File("Order.txt"));
            while (s.hasNext())
            {
                int line1 = Integer.parseInt(s.nextLine().split(":")[1]);
                String line2 = s.nextLine().split(":")[1];
                String line3 = s.nextLine().split(":")[1];
                String line4 = s.nextLine().split(":")[1];
                String line5 = s.nextLine().split(":")[1];
                String line6 = s.nextLine().split(":")[1];
                String line7 = s.nextLine().split(":")[1];
                String line8 = s.nextLine().split(":")[1];
                String line9 = s.nextLine().split(":")[1];
                String line10 = s.nextLine().split(":")[1];
                String line11 = s.nextLine().split(":")[1];
                String line12 = s.nextLine().split(":")[1];
                String emptyLine = s.nextLine();

                Order order = new Order(line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12);
                allOrder.add(order);       
                
                for(int i = 0; i < allAccount.size(); ++i) {           
                    if(line8.equals(allAccount.get(i).getUsername())) {
                        allAccount.get(i).getCusOrder().add(order);
                    }
                    else if (line6.equals(allAccount.get(i).getUsername())) {
                        allAccount.get(i).getMyOrder().add(order);
                        
                    }
                }
            } 
            
            s = new Scanner(new File("Payment.txt"));
            while(s.hasNext()) {
                int line1 = Integer.parseInt(s.nextLine().split(":")[1]);
                String line2 = s.nextLine().split(":")[1];
                int line3 = Integer.parseInt(s.nextLine().split(":")[1]);
                String line4 = s.nextLine().split(":")[1];
                String line5 = s.nextLine().split(":")[1];
                double line6 = Double.parseDouble(s.nextLine().split(":")[1]);
                int line7 = Integer.parseInt(s.nextLine().split(":")[1]);
                String emptyLine = s.nextLine();
                
                for(int i = 0; i < allOrder.size(); i++) {
                    orders = allOrder.get(i);        
                    if(line3 == allOrder.get(i).getOrderID()) {
                        Payment payment = new Payment(line1, line2, allOrder.get(i), allOrder.get(i), allOrder.get(i), line6, line7);
                        allPayment.add(payment);
                    }
                }     
            }     
            
            s = new Scanner(new File("Receipt.txt"));
            while(s.hasNext()) {
                String line1 = s.nextLine().split(":")[1];
                int line2 = Integer.parseInt(s.nextLine().split(":")[1]);
                String line3 = s.nextLine().split(":")[1];
                String line4 = s.nextLine().split(":")[1];
                String line5 = s.nextLine().split(":")[1];
                double line6 = Double.parseDouble(s.nextLine().split(":")[1]);
                String emptyLine = s.nextLine();
                
                for(int i = 0; i < allPayment.size(); i++) {
                    if(line2 == allPayment.get(i).getPaymentID()) {
                        Receipt receipt = new Receipt(line1, allPayment.get(i), allPayment.get(i), allPayment.get(i), allPayment.get(i), allPayment.get(i));
                        JavaAssignment.allReceipt.add(receipt);     
                    }
                }
            }                        
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(JavaAssignment.class.getName()).log(Level.SEVERE, null, ex);
        }     
    } 
    
    public static void accountData() {
        try {        
//            System.out.println("Account Data Initializing...\n");
            allAccount.clear();
            allDeliveryStaff.clear();
            Scanner s = new Scanner(new File("Account.txt"));
            while(s.hasNext()) {
                String line1 = s.nextLine().split(":")[1];
                String line2 = s.nextLine().split(":")[1];
                String line3 = s.nextLine().split(":")[1];
                String line4 = s.nextLine().split(":")[1];
                int line5 = Integer.parseInt(s.nextLine().split(":")[1]);    
                String emptyline = s.nextLine();
                
                switch (line5) {
                    case 1:
                        ManagingStaff ms = new ManagingStaff(line1, line2, line3, line4, line5);
                        allAccount.add(ms);
                        break;
                    case 2:
                        DeliveryStaff ds = new DeliveryStaff(line1, line2, line3, line4, line5);
                        allAccount.add(ds);
                        break;
                    default: 
                        Customer c = new Customer(line1, line2, line3, line4, line5);
                        allAccount.add(c);
                        break;
                }
                            
                             
            }  
            
            for(int i = 0; i < allAccount.size(); ++i) {
                if(allAccount.get(i).getRole() == 2) {
                    String username = allAccount.get(i).getUsername();
                    String password = allAccount.get(i).getPassword();
                    String email = allAccount.get(i).getEmail();
                    String contact = allAccount.get(i).getContact();
                    DeliveryStaff ds = new DeliveryStaff(username, password, email, contact, 2);
                    allDeliveryStaff.add(ds);     
                } 
            }        
        }
        catch(FileNotFoundException | NumberFormatException e) {
            Logger.getLogger(JavaAssignment.class.getName()).log(Level.SEVERE, null, e);
        }  
    } 
    
    public static void availableStaff() {
        try {
            availableStaff.clear();
//            System.out.println("Available Staff Initializing...\n");
            Scanner s = new Scanner(new File("DeliveryInfo.txt"));
            while(s.hasNext()) {
                String line1 = s.nextLine().split(":")[1];
                int line2 = Integer.parseInt(s.nextLine().split(":")[1]);
                int line3 = Integer.parseInt(s.nextLine().split(":")[1]);
                String emptyLine = s.nextLine();
                
                for(int i = 0; i < allDeliveryStaff.size(); ++i) {             
                    if(line1.equals(allDeliveryStaff.get(i).getUsername())) {
                        DeliveryTask dt = new DeliveryTask(allDeliveryStaff.get(i), allDeliveryStaff.get(i), line3);               
                        availableStaff.add(dt);                            
                    }  
                }               
            }    
            
            for(int i = 0; i < availableStaff.size(); ++i) {
                if(onLogin != null) {
                    String checkOwner = onLogin.getUsername();
                    if(checkOwner.equals(availableStaff.get(i).getOwner().getUsername())) {
                        deliveryTask = availableStaff.get(i);
                    }
                }
            } 
        }
        catch(FileNotFoundException | NumberFormatException e) {
            Logger.getLogger(JavaAssignment.class.getName()).log(Level.SEVERE, null, e);
        }     
    }
    
    public void randomString() {
        int size = JavaAssignment.allReceipt.size();
        int ID;

        if(size == 0){
            ID = 10001;
        }
        else {
            ID = 10001 + size;
        }
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ`~!@#$%^&*()abcdefghijklmnopqrstuvwxyz0123456789-+}]/|\\=[{;?<>_";
        String numbers = "0123456789";
        // create random string builder
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        // create an object of Random class
        Random random = new Random();       
        // specify length of random string
        int length = 10;

        for(int i = 0; i < length; i++) {
          // generate random index number
            int index = random.nextInt(alphabet.length());
            int index2 = random.nextInt(numbers.length());
          // get character specified by index
          // from the string
            char randomChar = alphabet.charAt(index);
            char randomID = numbers.charAt(index2);
          // append the character to string builder
            sb.append(randomChar);
            sb2.append(randomID);
        }
        String getRandomString = sb.toString();
        String getRandomID = sb2.toString();
        randomString = getRandomString+ID;
        randomString2 = getRandomString;
        reportID = "RE"+getRandomID;
    }
    
    public void countReport() {    
        
        totalDelivery = 0; //
        totalCustomer = 0; //
        totalOrders = 0; //
        totalPayments = 0; //
        totalRating1 = 0; //
        totalRating2 = 0; //
        totalRating3 = 0; //
        totalRating4 = 0; //
        totalRating5 = 0; //
        totalRates = 0; //
        totalRate = 0; //
        averageRate = 0; //
        // -------------------------------------------------           
        totalDeliveryWithin = 0; //
        totalCustomerWithin = 0; //
        totalOrdersWithin = 0; //
        totalPaymentsWithin = 0; //
        totalRating1Within = 0; //
        totalRating2Within = 0; //
        totalRating3Within = 0; //
        totalRating4Within = 0; //
        totalRating5Within = 0; //
        totalRatesWithin = 0; //
        totalRateWithin = 0; //
        averageRateWithin = 0; //
        
        try {         
            for(int i = 0; i < allAccount.size(); ++i) {
                if(allAccount.get(i).getRole() == 2) {
                    totalDelivery++;
                }
                else if(allAccount.get(i).getRole() == 3) {
                    totalCustomer++;
                }
            }      

            for(int i = 0; i < allOrder.size(); ++i) {
                totalOrders = allOrder.size();
                String orderDate = allOrder.get(i).getDate();
                int getCountDays = countDays(orderDate);             
                if(getCountDays <= 30 && getCountDays > 0) {
                    if(!allOrder.get(i).getDeliveryStaff().equals("None")) {
                        totalDeliveryWithin++;
                    }     
                    totalCustomerWithin++;
                    totalOrdersWithin++;
                    switch (allOrder.get(i).getRating()) {
                        case "1|Dissapointment":
                            totalRating1Within++;
                            break;
                        case "2|Very Bad":
                            totalRating2Within++;
                            break;
                        case "3|Average":
                            totalRating3Within++;
                            break;
                        case "4|Good":
                            totalRating4Within++;
                            break;
                        case "5|Satisfaction":
                            totalRating5Within++;
                            break;
                        default:
                            break;
                    }                  
                }
                switch (allOrder.get(i).getRating()) {
                    case "1|Dissapointment":
                        totalRating1++;
                        break;
                    case "2|Very Bad":
                        totalRating2++;
                        break;
                    case "3|Average":
                        totalRating3++;
                        break;
                    case "4|Good":
                        totalRating4++;
                        break;
                    case "5|Satisfaction":
                        totalRating5++;
                        break;
                    default:
                        break;
                }                     
            }
                
            totalRates = ((1*totalRating1)+(2*totalRating2)+(3*totalRating3)+(4*totalRating4)+(5*totalRating5));
            totalRate = totalRating1+totalRating2+totalRating3+totalRating4+totalRating5;
            averageRate = Math.floor((totalRates/totalRate)*100)/100;
            
            totalRatesWithin = ((1*totalRating1Within)+(2*totalRating2Within)+(3*totalRating3Within)+(4*totalRating4Within)+(5*totalRating5Within));
            totalRateWithin = totalRating1Within+totalRating2Within+totalRating3Within+totalRating4Within+totalRating5Within;
            averageRateWithin = Math.floor((totalRatesWithin/totalRateWithin)*100)/100;
            

            for(int i = 0; i < allPayment.size(); ++i) {
                String paymentDate = allPayment.get(i).getDate();
                if(!paymentDate.equals("None")){
                    int getCountDays = countDays(paymentDate);
                    
                    totalPayments = totalPayments + allPayment.get(i).getFee();            
                    if(getCountDays <= 30 && getCountDays > 0 ) {
                        totalPaymentsWithin = totalPaymentsWithin + allPayment.get(i).getFee();
                    }                
                }                    
            }       

            String getValue0 = Integer.toString(totalDelivery);         
            String getValue1 = Integer.toString(totalCustomer);                                                                                                                                                                         
            String getValue2 = Integer.toString(totalOrders);
            String getValue3 = Double.toString(totalPayments);
            String getValue4 = Integer.toString(totalRate);
            String getValue5 = Integer.toString(totalRating1);
            String getValue6 = Integer.toString(totalRating2);
            String getValue7 = Integer.toString(totalRating3);
            String getValue8 = Integer.toString(totalRating4);
            String getValue9 = Integer.toString(totalRating5);
            String getValue10 = Double.toString(averageRate);           
            
            allValues = new String[]{getValue0, getValue1, getValue2, getValue3, getValue4, getValue5,
                                     getValue6, getValue7, getValue8, getValue9, getValue10};
            
                        
            String getValueWithin0 = Integer.toString(totalDeliveryWithin);         
            String getValueWithin1 = Integer.toString(totalCustomerWithin);                                                                                                                                                                         
            String getValueWithin2 = Integer.toString(totalOrdersWithin);
            String getValueWithin3 = Double.toString(totalPaymentsWithin);
            String getValueWithin4 = Integer.toString(totalRateWithin);
            String getValueWithin5 = Integer.toString(totalRating1Within);
            String getValueWithin6 = Integer.toString(totalRating2Within);
            String getValueWithin7 = Integer.toString(totalRating3Within);
            String getValueWithin8 = Integer.toString(totalRating4Within);
            String getValueWithin9 = Integer.toString(totalRating5Within);
            String getValueWithin10 = Double.toString(averageRateWithin);           
            
            allValuesWithin = new String[]{getValueWithin0, getValueWithin1, getValueWithin2, getValueWithin3, getValueWithin4, 
                                     getValueWithin5, getValueWithin6, getValueWithin7, getValueWithin8, getValueWithin9, 
                                     getValueWithin10};            

            
        }
        catch (ParseException e) {
            System.out.println("REPORT ERROR :"+e); 
        }
    }
    
    public int countDays(String orderDate) throws ParseException {
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date();  
        String currentDate = formatter.format(date);

        Date todayDate = formatter.parse(currentDate);
        cal1.setTime(todayDate);
        Date orderDates = formatter.parse(orderDate);      
        cal2.setTime(orderDates);

        int daysCount = daysCount(cal1.getTime(), cal2.getTime()); 
        return daysCount;
    }   
    
    public int daysCount(Date d1, Date d2) {
        return (int)((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
    }
}








  