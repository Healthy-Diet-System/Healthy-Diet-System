import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends User {
    public int age;
    public int height;
    public int weight;
    public int nid;
    public String gender;


    public Customer(int id, String fname, String lname, String email, String password, String phoneNum) {
        super(id, fname, lname, email, password, phoneNum);
    }

    public Customer(int id, String fname, String lname, String email, String password, int weight, int height, int age, String gender, String phoneNum, int nid) {
        super(id, fname, lname, email, password, phoneNum);
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.nid = nid;
    }

}
