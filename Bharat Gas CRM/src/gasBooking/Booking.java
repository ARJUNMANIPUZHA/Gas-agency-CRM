package gasBooking;
import customers.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Booking extends GasConnection {

    public double otp = 5678;
    public double amount = 820;
    public double refund = 0;
    public String delDate;
    public String status;
    public  String delMobNo = "9383427321";
    public String dt;
    public Date lastDate;
    public Date dt_1;
    public Date dt_2;

    public Booking(String name, String street, String area, String pincode, String mobile, int numberOfCylinders) {
        super(name, street, area, pincode, mobile, numberOfCylinders);
    }

    public void getDates(){
        System.out.println("enter booking date");
        dt = new Scanner(System.in).nextLine();
        dt_1 = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            dt_1 = dateFormat.parse(dt);
        }catch(ParseException e) {
            System.out.println("the error in getDates() : " + e);
        }

//        Delivery details
        System.out.println("Enter delivery date : ");
        delDate = new Scanner(System.in).nextLine();
        try{
            dt_2 = dateFormat.parse(delDate);
        }catch (Exception exp){
            System.out.println("Error parsing date dt_2 : " + exp);
        }

//        Find the difference between the two dates
        try{
            long difference = dt_2.getTime() - dt_1.getTime();

//        Difference in days
            long newDifference = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);

            if(newDifference > 7){
                status = "P";
            }
        }catch (Exception e){
            System.out.println("Error while finding difference : " + e);
        }

    }

    public void validate(){
//        Get difference between two dates
        long elapsedms = dt_2.getTime() - dt_1.getTime();
        long diff = TimeUnit.DAYS.convert(elapsedms,TimeUnit.MILLISECONDS);

        System.out.println("The difference between two date is : " + diff);

        if(numberOfCylinders == 1){
            if(diff < 30){
                System.out.println("Booking cannot be done");
//                Numberofcylinders = 0;
                status = "C";
            } else {
//                System.out.println("Booked");
                status = "B";
                lastDate = dt_1;
            }
        }
        else if(numberOfCylinders == 2){
            if(diff < 50){
                System.out.println("Booking cannot be done");
                status = "C";
            } else {
                status = "B";
                lastDate = dt_1;
            }
        }
    }

}
