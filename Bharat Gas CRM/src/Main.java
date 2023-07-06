import customers.*;
import gasBooking.*;
import gasSupplier.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static gasSupplier.gasAgency.*;

public class Main {
    static int count;
    static int bcount = 0 ,ccount = 0, dcount = 0, pcount = 0;
    static String dpname;

    public static void cylinderCount(Delivery[] obj){
        String[] months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
        for(Delivery delivery:obj){
            count = 0;
            System.out.println("In the month of " + (months[delivery.dt_2.getMonth()]) + " : ");
            System.out.println(" * In " + delivery.area);
            if(delivery.status.equals("D")){
                count += delivery.numberOfCylinders;
            }
            System.out.println(" - " + count + " cylinders delivered");
        }
        System.out.println("\n");
    }

    public static void checkLateDel(Delivery[] obj){
        String[] months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
        int[] month = new int[12];

        for(Delivery delivery : obj){
            if(delivery.status.equals("D") && delivery.amount == 783.75){
                month[delivery.dt_2.getMonth()] += 1;
            }
        }
        System.out.println("-------------Late delivery-------------");
        for(int i=0; i<12 ;i++){
            if(month[i] != 0){
                System.out.println("* In " + months[i] + " there are " + month[i]);
            }
        }
        System.out.println("\n");
    }

    public static void numOfSingleCylinders(Delivery[] obj){
        System.out.println("-------------Single cylinder holders-------------");
        for(int i=0 ;i< obj.length ;i++){
            if(obj[i].numberOfCylinders == 1){
                System.out.println("* Customer Name : " + obj[i].name);
                System.out.println("* Mobile No. : " + obj[i].delMobNo);
                System.out.println("* Gas Connection No. : " + (i + 101));

            }
            System.out.println("\n");
        }

    }



    public static void DeliveryDetails(Delivery[] obj){
        System.out.println("-------------Delivery Details-------------");
        System.out.println("enter the name of delivery person : ");
        dpname = new Scanner(System.in).next();
        for(Delivery delivery : obj){
            if(delivery.status.equals("D") && delivery.delPersonName.equals(dpname)){
                System.out.println("* Customer Name : " + delivery.name);
                System.out.println(" - " + delivery.street + " , " + delivery.area + ", " + delivery.pincode);
            }
        }
    }

    public static void printReport(Delivery[] obj){
        System.out.println("-------------Delivery Report-------------");
        for(int i=0 ; i< obj.length; i++){
            if(obj[i].status.equals("D")){
                dcount++;
            }
            else if(obj[i].status.equals("B")){
                bcount++;
            }
            else if(obj[i].status.equals("C")){
                ccount++;
            }
            else if(obj[i].status.equals("P")){
                pcount++;
            }
            else{
                System.out.println("Status invalid");
            }

        }
        System.out.println("* Booked");
        System.out.println(" - " + bcount + " booked");
        System.out.println("* Delivered");
        System.out.println(" - " + dcount + " delivered");
        System.out.println("* Cancelled");
        System.out.println(" - " + ccount + " cancelled");
        System.out.println("* Pending");
        System.out.println(" - " + pcount + " pending");
        System.out.println("\n");
    }

    public static void printInvoice(Delivery[] obj){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String invoiceDate = sdf.format(d);

        for(int i=0 ;i<obj.length; i++){
            if(obj[i].status.equals("D")){
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("                                 INVOICE                                  ");
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Gas Agency Code : " + agencyCode + "\t\t\t" + " Date of Invoice : " + invoiceDate );
                System.out.println("Gas Agency Name : " + agencyName + "\t\t" + " Agency Phone No. : " + phNumber );
                System.out.println("Gas Connection No. : " + (i+101) + "\t\t\t" + " Customer Name :  " + obj[i].name);
                System.out.println("Booking Date : " + sdf.format(obj[i].dt_1) + "\t\t" + "Customer Mobile No. : " + obj[i].mobile);
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Amount : " + obj[i].amount);
                System.out.println("Refund : " + obj[i].refund);
                System.out.println("Total Amount : " + (obj[i].amount - obj[i].refund));
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Delivery Person Name : " + obj[i].delPersonName + "\t\t" + " Delivery Person Mobile : " + obj[i].delMobNo);
                System.out.println("Delivery Date : " + sdf.format(obj[i].dt_2));
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("\n\n");
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("****************************************************************");
        System.out.println("                       BHARAT GAS AGENCY                        ");
        Delivery[] deliveryObject = new Delivery[5];
        deliveryObject[0] = new Delivery("Arjun Krishna M","4th Avenue", "Alakkad" ,"670307", "9446773554", 1);
        deliveryObject[1] = new Delivery("Parvathy","Gandhi Nagar", "Nilambur" ,"670821", "9682371834", 2);
        deliveryObject[2] = new Delivery("Nandhu","4th Avenue", "Kankol" ,"670307", "8396427581", 1);
        deliveryObject[3] = new Delivery("Shukkur","8th lane", "Perumba" ,"670363", "9238169320", 2);
        deliveryObject[4] = new Delivery("Raju","Andheri Col", "Mathil" ,"670397", "3691478523", 1);
        for (Delivery delivery : deliveryObject){
            delivery.delPersonDetails();
            delivery.getLastDate();
            delivery.getDates();
            delivery.validate();
            delivery.amountCalc();
            delivery.verifyOtp();
        }
        System.out.println();
        cylinderCount(deliveryObject);
        checkLateDel(deliveryObject);
        numOfSingleCylinders(deliveryObject);
        DeliveryDetails(deliveryObject);
        printReport(deliveryObject);
        printInvoice(deliveryObject);
    }
}
