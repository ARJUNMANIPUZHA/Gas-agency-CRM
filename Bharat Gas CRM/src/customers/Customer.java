package customers;
import gasSupplier.gasAgency;

public class Customer implements gasAgency {
    public String name;
    public String street;
    public String area;
    public String pincode;
    public String mobile;

    //Constructor
    public Customer(String name, String street, String area, String pincode, String mobile) {
        this.name = name;
        this.street = street;
        this.area = area;
        this.pincode = pincode;
        this.mobile = mobile;
    }
}
